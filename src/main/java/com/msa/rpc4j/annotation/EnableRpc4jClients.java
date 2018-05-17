package com.msa.rpc4j.annotation;

import com.msa.rpc4j.Rpc4jClientsRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * The interface Enable rpc 4 j clients.
 *
 * @ClassName: EnableRpc4jClients
 * @Description: 开启rpc4j客户端自动注入为spring bean
 * @Author: sxp
 * @Date: 15 :55 2018/5/17
 * @Version: 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(Rpc4jClientsRegistrar.class)
public @interface EnableRpc4jClients {

    /**
     * 扫描包路径
     * Base packages string [ ].
     *
     * @return the string [ ]
     */
    String[] basePackages() default {};
}