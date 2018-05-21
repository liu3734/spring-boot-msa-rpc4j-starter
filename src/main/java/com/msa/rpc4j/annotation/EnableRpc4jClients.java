package com.msa.rpc4j.annotation;

import com.msa.rpc4j.Rpc4jClientsRegistrar;
import com.msa.rpc4j.autoconfig.Rpc4jClientAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * The interface Enable rpc 4 j clients.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({Rpc4jClientsRegistrar.class, Rpc4jClientAutoConfiguration.class})
public @interface EnableRpc4jClients {

    /**
     * Base packages string [ ].
     *
     * @return the string [ ]
     */
    String[] basePackages() default {};
}