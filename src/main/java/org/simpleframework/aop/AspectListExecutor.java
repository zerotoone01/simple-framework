package org.simpleframework.aop;

import lombok.Getter;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.simpleframework.aop.aspect.AspectInfo;
import org.simpleframework.util.ValidationUtil;


public class AspectListExecutor implements MethodInterceptor {
    //被代理的类
    private Class<?> targetClass;
    //成员变量的 order 顺序排序， 得到排序好饿 Aspect 列表
    @Getter
    private List<AspectInfo> sortedAspectInfoList;

    public AspectListExecutor(Class<?> targetClass, List<AspectInfo> aspectInfoList){
        this.targetClass = targetClass;
        this.sortedAspectInfoList = sortAspectInfoList(aspectInfoList);
    }

    /**
     * 按照 order 的值进行排序，确保 order 值小的 aspect 先被织入
     * @param aspectInfoList
     * @return
     */
    private List<AspectInfo> sortAspectInfoList(List<AspectInfo> aspectInfoList) {
        Collections.sort(aspectInfoList, new Comparator<AspectInfo>() {
            @Override
            public int compare(AspectInfo o1, AspectInfo o2) {
                return o1.getOrderIndex()-o2.getOrderIndex();
            }
        });
        return  aspectInfoList;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //1.按照 order 的顺序执行完毕所有 Aspect 的 before 方法
        //2.执行被代理类的方法
        //3.如果被代理方法正常返回，则按照 order 的顺序降序执行完所有 Aspect 的 afterReturning 方法
        //4.如果被代理方法抛出异常，则按照 order 的顺序降序执行完毕所有 Aspect 的 afterThrowing 方法
        Object returnValue = null;
        if(ValidationUtil.isEmpty(sortedAspectInfoList)){
            return null;
        }
        invokeBeforeAdvices(method,args);
        try{
            returnValue = methodProxy.invokeSuper(proxy,args);
            returnValue = invokeAfterReturningAdvices(method,args,returnValue);
        }catch (Exception e){
            invokeAfterThrowingAdvices(method,args,e);
        }

        return returnValue;
    }

    private void invokeAfterThrowingAdvices(Method method, Object[] args, Exception e) throws Throwable{
        for (int i = sortedAspectInfoList.size()-1; i >=0 ; i--) {
            sortedAspectInfoList.get(i).getAspectObject().afterThrowing(targetClass,method,args,e);

        }
    }

    private Object invokeAfterReturningAdvices(Method method, Object[] args, Object returnValue) throws Throwable{
        Object result = null;
        //TODO 这个有问题？？
        for (int i = sortedAspectInfoList.size()-1; i >=0 ; i--) {
            result = sortedAspectInfoList.get(i).getAspectObject().afterReturning(targetClass,method,args,returnValue);
        }
        return result;
    }

    private void invokeBeforeAdvices(Method method, Object[] args) throws Throwable{
        for (AspectInfo aspectInfo: sortedAspectInfoList){
            aspectInfo.getAspectObject().before(targetClass,method,args);
        }
    }
}
