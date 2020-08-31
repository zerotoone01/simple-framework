package org.simpleframework.aop.annotation;

import java.lang.annotation.*;

// 元注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    // 织入到类
    Class<? extends Annotation> value();
}
