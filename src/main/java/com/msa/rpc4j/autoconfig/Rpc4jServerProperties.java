package com.msa.rpc4j.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The type Rpc 4 j server properties.
 *
 * @ClassName: Rpc4jServerProperties
 * @Description: rpc框架服务端属性
 * @Author: sxp
 * @Date: 10 :37 2018/5/2
 * @Version: 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "spring.rpc.server")
public class Rpc4jServerProperties {
    /**
     * 服务端端口号
     * The Port.
     */
    private int port = 121210;

    /**
     * 服务注册中心地址
     * The Registry address.
     */
    private String registryAddress = "localhost:2181";

}
