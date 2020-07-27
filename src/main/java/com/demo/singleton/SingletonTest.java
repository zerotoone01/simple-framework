package com.demo.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println(StarvingSingleton.getInstance());
        System.out.println(StarvingSingleton.getInstance());
        System.out.println(LazyDoubleCheckSingleton.getInstance());
        System.out.println(LazyDoubleCheckSingleton.getInstance());

        //普通单例模式反射 会重新创建类
        Class<StarvingSingleton> clazz = StarvingSingleton.class;
        Constructor<StarvingSingleton> constructor = clazz.getDeclaredConstructor();
//        System.out.println(constructor.newInstance());
        constructor.setAccessible(true);
        StarvingSingleton starvingSingleton = constructor.newInstance();
        System.out.println(starvingSingleton.getInstance());



        System.out.println(EnumStarvingSingleton.getInstance());
        Class<EnumStarvingSingleton> enumStarvingSingletonClass = EnumStarvingSingleton.class;
        Constructor<EnumStarvingSingleton> enumStarvingSingletonClassDeclaredConstructor = enumStarvingSingletonClass.getDeclaredConstructor();
        enumStarvingSingletonClassDeclaredConstructor.setAccessible(true);
        EnumStarvingSingleton enumStarvingSingleton = enumStarvingSingletonClassDeclaredConstructor.newInstance();
        System.out.println(enumStarvingSingleton.getInstance());

    }

}
