package com.msa.rpc4j;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName: EnableRpc4jServer
 * @Description: 开启rpc4j服务端自动配置
 * @Author: sxp
 * @Date: 11:29 2018/5/2
 * @Version: 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({Rpc4jServerAutoConfiguration.class})
public @interface EnableRpc4jServer {
}
