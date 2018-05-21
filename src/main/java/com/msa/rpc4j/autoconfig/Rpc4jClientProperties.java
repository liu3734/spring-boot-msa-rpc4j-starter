package com.msa.rpc4j.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The type Rpc 4 j client properties.
 *
 */
@Data
@ConfigurationProperties(prefix = "spring.rpc.client")
public class Rpc4jClientProperties {
    /**
     * 服务注册中心地址
     * The Registry address.
     */
    private String registryAddress = "localhost:2181";

}
