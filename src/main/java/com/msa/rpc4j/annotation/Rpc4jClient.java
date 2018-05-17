package com.msa.rpc4j.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * The interface Rpc 4 j client.
 *
 * @ClassName: Rpc4jClient
 * @Description: rpc4j client标记注解
 * @Author: sxp
 * @Date: 15 :57 2018/5/17
 * @Version: 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Rpc4jClient {
    /**
     * Value string.
     *
     * @return the string
     */
    @AliasFor("name")
    String value() default "";

    /**
     * 服务名
     * Name string.
     *
     * @return the string
     */
    @AliasFor("value")
    String name() default "";
}
