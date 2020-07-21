package com.demo.reflect;


/**
 * 反射获取
 */
public class ReflectTarget {

    public ReflectTarget() {
        System.out.println("无参构造");
    }
    public ReflectTarget(String name) {
        System.out.println("有参构造， string类型="+name);
    }

    public ReflectTarget(String name, int index) {
        System.out.println("有参构造， string类型="+name+", int类型="+index);
    }
    ReflectTarget(String name, String code) {
        System.out.println("默认有参构造， string类型="+name+", string类型="+code);
    }

    private ReflectTarget(int code) {
        System.out.println("私有有参构造， int类型="+code);
    }
    // 字段  //
    public String name;
    private String targetInfo;
    protected int index;
    char type;

    @Override
    public String toString() {
        return "ReflectTarget{" +
                "name='" + name + '\'' +
                ", targetInfo='" + targetInfo + '\'' +
                ", index=" + index +
                ", type=" + type +
                '}';
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ReflectTarget reflectTarget = new ReflectTarget();
        //1.实例来获取
        Class<? extends ReflectTarget> targetClass1 = reflectTarget.getClass();
        System.out.println("class1: "+targetClass1.getName());
        //2.获取class对象
        Class<ReflectTarget> targetClass2 = ReflectTarget.class;
        System.out.println("class2: "+targetClass2.getName());
        //3.根据类包来获取 -- 常用
        Class targetClass3 = Class.forName("com.demo.reflect.ReflectTarget");
        System.out.println("class3: "+targetClass2.getName());
    }
}
