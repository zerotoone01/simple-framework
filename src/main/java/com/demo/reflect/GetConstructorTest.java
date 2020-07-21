package com.demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class GetConstructorTest {
    /**
     * 通过class对象获取某个类的 构造方法 ：
     * 获取构造参数的两种方法：
     * 1).批量获取：
     *      java.lang.Class#getConstructors() 所有公有的构造方法
     *      java.lang.Class#getDeclaredConstructors() 获取所有的构造方法，包括私有、受保护、默认、公有
     * 2). 获取单个构造方法，并调用
     *      java.lang.Class#getConstructor(java.lang.Class[]) 获取单个公有的构造方法
     *      java.lang.Class#getDeclaredConstructor(java.lang.Class[]) 获取单个私有的构造方法
     *
     *  调用构造方法：
     *          java.lang.reflect.Constructor#newInstance(java.lang.Object...)
     *
     *
     *  反射获取成员变量，并调用
     *
     *
     *  成员变量设值
     *
     */


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<?> aClass = Class.forName("com.demo.reflect.ReflectTarget");
        System.out.println("获取公有构造方法>>>>>>>>>>>>>");
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor cons: constructors) {
            System.out.println(cons);
        }
        System.out.println("获取全部构造方法<<<<<<<<<<<<");
        Constructor<?>[] declaredConstructor = aClass.getDeclaredConstructors();
        for (Constructor cons: declaredConstructor) {
            System.out.println(cons);
        }

        Constructor<?> constructor = aClass.getConstructor(String.class);
        System.out.println("con="+constructor);
        constructor=aClass.getDeclaredConstructor(int.class);
        System.out.println("con="+constructor);

        System.out.println("调用构造方法创建实例");
        constructor.setAccessible(true);

        ReflectTarget reflectTarget = (ReflectTarget)constructor.newInstance(1);


        //字段
        Field[] fields = aClass.getFields();
        Field[] declaredFields = aClass.getDeclaredFields();
        Field name = aClass.getField("name");
        name.set(reflectTarget,"name赋值");
        System.out.println("name=="+reflectTarget.name);

        //TODO 反射获取成员方法
    }
}
