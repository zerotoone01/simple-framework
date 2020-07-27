package com.demo.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
// jad
public class EnumStarvingSingleton {
    private EnumStarvingSingleton(){}
    public static EnumStarvingSingleton getInstance(){
        return ContainerHolder.HOLDER.instance;
    }
    private enum ContainerHolder{
        HOLDER;
        private EnumStarvingSingleton instance;
        private ContainerHolder(){
            instance=new EnumStarvingSingleton();
        }
    }

    // 执行 javac的时候，去掉main代码
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class<ContainerHolder> containerHolderClass = ContainerHolder.class;
        // 枚举对反射具有安全防护作用
//        Constructor<ContainerHolder> constructor = containerHolderClass.getDeclaredConstructor();
        // java.lang.Enum.Enum
        // Exception in thread "main" java.lang.IllegalArgumentException: Cannot reflectively create enum objects
        Constructor<ContainerHolder> constructor = containerHolderClass.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        System.out.println(EnumStarvingSingleton.getInstance());
        System.out.println(constructor.newInstance());
    }
}
