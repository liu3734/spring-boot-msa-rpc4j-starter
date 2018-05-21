package com.msa.rpc4j.annotation;

import com.msa.rpc4j.autoconfig.Rpc4jServerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * The interface Enable rpc 4 j server.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({Rpc4jServerAutoConfiguration.class})
public @interface EnableRpc4jServer {
}
