package org.simpleframework.inject;

import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Set;

public class DependencyInjector {
    public static final Logger LOGGER = LoggerFactory.getLogger(DependencyInjector.class);

    private BeanContainer beanContainer;
    public DependencyInjector(){
        beanContainer=BeanContainer.getInstance();
    }

    public void doIoc(){
        //1.遍历Bean容器中所有的Class对象 2.变量Class对象的所有成员变量
        //3.找出被Autowired标记的成员变量  4.获取这些成员变量的类型
        //5.获取这些成员变量的类型在容器里对应的实例  6.通过反射将对应的成员变量实例注入到成员变量所在类的实例
        if(ValidationUtil.isEmpty(beanContainer.getClasses())){
            LOGGER.warn("empty classSet in BeanContainer!");
            return;
        }
        for (Class<?> clazz:beanContainer.getClasses()){
            Field[] declaredFields = clazz.getDeclaredFields();
            if(ValidationUtil.isEmpty(declaredFields)){
                continue;
            }
            for(Field field:declaredFields){
                if(field.isAnnotationPresent(Autowired.class)){
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();

                    Class<?> fieldClass = field.getType();
                    Object fieldValue = getFieldInstance(fieldClass, autowiredValue);
                    if(fieldValue==null){
                        throw new RuntimeException("Unable to inject relevant type, target fieldClass is:"+fieldClass.getName()+", autowired is:"+autowiredValue);
                    }else {
                        Object targetBean = beanContainer.getBean(clazz);
                        ClassUtil.setField(field,targetBean,fieldValue,true);
                    }
                }

            }
        }

    }

    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {
        Object fieldValue = beanContainer.getBean(fieldClass);
        if(fieldValue!=null){
            return fieldValue;
        }else {
            Class<?> implementClass = getImplementClass(fieldClass, autowiredValue);
            if(implementClass!=null){
                return beanContainer.getBean(implementClass);
            }else{
                return null;
            }
        }
    }

    private Class<?> getImplementClass(Class<?> fieldClass, String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if(!ValidationUtil.isEmpty(classSet)){
            if (ValidationUtil.isEmpty(autowiredValue)){
                if(classSet.size()==1){
                    return classSet.iterator().next();
                }else{
                    throw new RuntimeException("multiple implemented classes for "+fieldClass.getName()+", please set @Autowired value to pick one");
                }
            }else{
                for(Class<?> clazz:classSet){
                    if (autowiredValue.equals(clazz.getSimpleName())){
                        return clazz;
                    }
                }
            }
        }
        return null;
    }
}
