package com.nowcoder.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 写这个注解的目的就是为了 让我们需要强制拦截的方法上面加上这个注解 就可以使拦截器及时的拦截下来调用这个方法的请求
 */
//这个注解可以写在方法之上
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {

}
