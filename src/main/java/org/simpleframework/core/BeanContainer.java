package org.simpleframework.core;


import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取容器实例
 *
 *  容器组成部分--保存class对象及其实例的载体；容器的加载；容器的操作方式；
 */
public class BeanContainer {
    public static final Logger LOGGER = LoggerFactory.getLogger(BeanContainer.class);

    //存放所有被配置标记的目标对象的Map
    private final Map<Class<?>,Object> beanMap = new ConcurrentHashMap<>();
    //容器是否已经加载过bean
    private boolean loaded = false;

    //加载bean的注解列表
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION
            = Arrays.asList(Component.class, Controller.class, Service.class, Repository.class);

    private BeanContainer(){}
    public static BeanContainer getInstance(){
        return ContainerHolder.HOLDER.instance;
    }
    private enum ContainerHolder{
        HOLDER;
        private BeanContainer instance;
        ContainerHolder(){
            instance = new BeanContainer();
        }
    }


    /**
     * 扫描所有的Bean
     * @param basePackageName 包名
     */
    public synchronized void loadBeans(String basePackageName){
        //容器加载比较费时，已加载过就不需要再加载
        if(loaded){
            LOGGER.warn("BeanContainer has been loaded");
            return;
        }

        Set<Class<?>> classSet = ClassUtil.extractPackageClass(basePackageName);
        if (ValidationUtil.isEmpty(classSet)){
            LOGGER.warn("extract nothing from package: [{}]",basePackageName);
            return;
        }
        for (Class<?> clazz: classSet){
            for (Class<? extends Annotation> annotation: BEAN_ANNOTATION){
                if(clazz.isAnnotationPresent(annotation)){
                    //将目标本身作为键，目标类的实例作为值，放入到beanMap中
                    beanMap.put(clazz,ClassUtil.newInstance(clazz,true));
                }
            }
        }
        loaded=true;
    }

    /**
     * 容器是否加载过
     * @return
     */
    public boolean isLoaded(){
        return this.loaded;
    }

    /**
     * 获取已加载类的数量
     * @return
     */
    public int size(){
        return beanMap.size();
    }

    /**
     * 添加一个class对象及其bean实例
     * @param clazz
     * @param bean
     * @return
     */
    public Object addBean(Class<?> clazz, Object bean){
        return beanMap.put(clazz,bean);
    }

    /**
     * 删除一个IOC容器管理的对象
     * @param clazz
     * @return
     */
    public Object removeBean(Class<?> clazz){
        return beanMap.remove(clazz);
    }

    /**
     * 获取容器实例
     * @param clazz
     * @return
     */
    public Object getBean(Class<?> clazz){
        return beanMap.get(clazz);
    }

    /**
     * 获取容器管理的所有Class对象集合
     * @return
     */
    public Set<Class<?>> getClasses(){
       return beanMap.keySet();
    }

    /**
     * 获取容器管理的所有Bean集合
     * @return
     */
    public Set<Object> getBeans(){
        return new HashSet<>(beanMap.values());
    }

    /**
     * 根据注解筛选出Bean的class集合
     * @param annotation
     * @return
     */
     public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation){
        //1. 获取beanMap的所有class对象; 2.通过注解筛选被标记的class对象，并添加到classSet里 ;
         Set<Class<?>> keySet = getClasses();
         if (ValidationUtil.isEmpty(keySet)){
             LOGGER.warn("nothing in beanMap");
             return null;
         }
         Set<Class<?>> classSet = new HashSet<>();
         for (Class<?> clazz:keySet){
             if (clazz.isAnnotationPresent(annotation)){
                 classSet.add(clazz);
             }
         }
         return classSet.size()>0?classSet:null;
     }

    /**
     * 通过接口或者父类获取实现类或者子类的class集合，不包括其本身
     * @param interfaceOrClass
     * @return
     */
    public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass){
        //1. 获取beanMap的所有class对象; 2.判断keySet里面的元素是否是传入的接口或者类的子类，如果是就添加到classSet里 ;
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)){
            LOGGER.warn("nothing in beanMap");
            return null;
        }
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz:keySet){
            if (interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)){
                classSet.add(clazz);
            }
        }
        return classSet.size()>0?classSet:null;
    }

}
