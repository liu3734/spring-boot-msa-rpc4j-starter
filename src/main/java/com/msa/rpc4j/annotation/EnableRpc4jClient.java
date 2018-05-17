package com.msa.rpc4j.annotation;

import com.msa.rpc4j.autoconfig.Rpc4jClientAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName: EnableRpc4jClient
 * @Description: 开启rpc4j客户端自动配置
 * @Author: sxp
 * @Date: 14:11 2018/5/2
 * @Version: 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({Rpc4jClientAutoConfiguration.class})
public @interface EnableRpc4jClient {
}
