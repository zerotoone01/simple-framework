package org.simpleframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class ClassUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    public static final String FILE_PROTOCOL = "file";

    /**
     * 获取包下类的集合
     *  1.获取到类加载器；
     *  2.通过类加载器获取到加载的资源信息；
     *  3.依据不同的资源类型，采用不同的方式获取资源的集合；
     * @param packageName 包名
     * @return 类的集合
     */
    public static Set<Class<?>> extractPackageClass(String packageName){
        ClassLoader classLoader = getClassLoader();
        URL url = classLoader.getResource(packageName.replace(".", "/"));
        if(url==null){
            LOGGER.warn("unable to retrieve anything from package:[{}]",packageName);
            return null;
        }
        Set<Class<?>> classSet = null;
        if(url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)){
            classSet=new HashSet<Class<?>>();
            File packageDirectory = new File(url.getPath());
            extractClassFile(classSet,packageDirectory,packageName);
        }
        //TODO 可以加入其它资源路径的处理
        return classSet;
    }

    /**
     * 递归获取目标package里面的所有class文件（包括package里的class文件）
     * @param emptyClassSet 装载目标类的集合
     * @param fileSource 文件或者目录
     * @param packageName 包名
     */
    private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
        //递归： 目标  中止条件
        if(!fileSource.isDirectory()){
            return;
        }
        //
        File[] files = fileSource.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if(file.isDirectory()){
                    return true;
                }else{
                    //获取文件绝对路径
                    String absoluteFilePath = file.getAbsolutePath();
                    if(absoluteFilePath.endsWith(".class")){
                        //若是class文件，则直接加载
                        addToClassSet(absoluteFilePath);
                    }
                }
                return false;
            }

            //根据class文件的绝对路径，获取并生成class对象，并放入classSet中
            private void addToClassSet(String absoluteFilePath) {
                // 1.从class文件的绝对中路径里提取出包含了package的类名
                // 2.通过反射机制获取对应的class对象并加入到classSet里： 获取路径为"/",要替换成"."
                // 如 G:\github\simple-framework\target\classes\com\demo\reflect\ReflectTarget.class 替换成 com.demo.reflect.ReflectTarget
                absoluteFilePath = absoluteFilePath.replace(File.separator,".");
                String className = absoluteFilePath.substring(absoluteFilePath.indexOf(packageName));
                className = className.substring(0, className.lastIndexOf("."));
                Class<?> targetClass = loadClass(className);
                emptyClassSet.add(targetClass);
            }
        });

        if(files!=null){
            for (File f:files){
                extractClassFile(emptyClassSet,f,packageName);
            }
        }
    }

    /**
     * 通过反射获取类
     * @param className
     * @return
     */
    public static Class<?> loadClass(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class error:{}",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 实例化class
     * @param clazz
     * @param accessible 是否支持创建出私有对象的实例
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<?> clazz, boolean accessible){
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(accessible);
            return (T)constructor.newInstance();
        } catch (Exception e) {
            LOGGER.error("get class instance error",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取类加载器
     * @return 当前的类加载器
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 设置类的属性值
     * @param field 成员变量
     * @param target 类实例
     * @param value 成员变量的值
     * @param accessible 是否允许设置私有属性
     */
    public static void setField(Field field, Object target, Object value, boolean accessible){
        field.setAccessible(accessible);
        try {
            field.set(target,value);
        } catch (IllegalAccessException e) {
            LOGGER.error("field=[{}] set value error:",field.getName(),e);
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {

        extractPackageClass("com.huangxi");
    }
}
