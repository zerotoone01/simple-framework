package org.simpleframework.aop;

import org.simpleframework.aop.aspect.AspectInfo;
import org.simpleframework.aop.aspect.DefaultAspect;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;

public class AspectWeaver {

    private BeanContainer beanContainer;

    public AspectWeaver(){
        this.beanContainer = BeanContainer.getInstance();
    }

    public void doAop(){
        //1.获取所有的切面类
        //2.将切面类按照不同的织入目标进行切分
        //3.按照不同的织入目标分别取按序织入 Aspect 的逻辑
        Set<Class<?>> aspectSet = beanContainer.getClassesByAnnotation(Aspect.class);
        Map<Class<? extends Annotation>, List<AspectInfo>> categorizedMap = new HashMap<>();
        if(ValidationUtil.isEmpty(aspectSet)){
            return;
        }
        for (Class<?> aspectClass : aspectSet){
            if(verifyAspect(aspectClass)){
                categorizeAspect(categorizedMap,aspectClass);
            }else {
                throw new RuntimeException("@Aspect and @Order have not been added to the Aspect.class, "+
                        "or Aspect class does not extend from DefaultAspect, or the value in Aspect Tag equals @Aspect")
            }
        }

        if(ValidationUtil.isEmpty(categorizedMap)){return;}
        for (Class<? extends Annotation> category: categorizedMap.keySet()){
            weaveByCategory(category, categorizedMap.get(category));
        }

    }

    private void weaveByCategory(Class<? extends Annotation> category, List<AspectInfo> aspectInfos) {
        //1.获取被代理类的集合
        //2.遍历被代理类，分别为每个被代理生成动态代理实例
        //3.将动态代理对象实例添加到容器里，取代未被代理前的类实例
        Set<Class<?>> classSet = beanContainer.getClassesByAnnotation(category);
        if (ValidationUtil.isEmpty(classSet)){return;}
        for(Class<?> targetClass: classSet){
            AspectListExecutor aspectListExecutor = new AspectListExecutor(targetClass,aspectInfos);
            Object proxyBean = ProxyCreator.creatProxy(targetClass, aspectListExecutor);
            beanContainer.addBean(targetClass,proxyBean);
        }
    }

    private void categorizeAspect(Map<Class<? extends Annotation>, List<AspectInfo>> categorizedMap, Class<?> aspectClass) {
        Order orderTag = aspectClass.getAnnotation(Order.class);
        Aspect aspectTag = aspectClass.getAnnotation(Aspect.class);
        DefaultAspect aspect = (DefaultAspect)beanContainer.getBean(aspectClass);
        AspectInfo aspectInfo = new AspectInfo(orderTag.value(),aspect);
        if(!categorizedMap.containsKey(aspectTag.value())){
            List<AspectInfo> aspectInfoList = new ArrayList<>();
            aspectInfoList.add(aspectInfo);
            categorizedMap.put(aspectTag.value(),aspectInfoList);
        }else{
            List<AspectInfo> aspectInfoList = categorizedMap.get(aspectTag.value());
            aspectInfoList.add(aspectInfo);
        }

    }

    /**
     * 框架中一定要遵守给Aspect类添加@Aspect和@Order标签的规范，同时，必须继承自DefaultAspect.class
     * 此外，@Aspect 的属性值不能是它本身 >> 针对切面本身去执行切面，会陷入死循环
     * @param aspectClass
     * @return
     */
    private boolean verifyAspect(Class<?> aspectClass) {
        return aspectClass.isAnnotationPresent(Aspect.class) &&
                aspectClass.isAnnotationPresent(Order.class) &&
                DefaultAspect.class.isAssignableFrom(aspectClass) &&
                aspectClass.getAnnotation(Aspect.class).value()!=Aspect.class;
    }
}
