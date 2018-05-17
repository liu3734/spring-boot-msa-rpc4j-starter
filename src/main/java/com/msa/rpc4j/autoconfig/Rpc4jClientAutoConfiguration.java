package com.msa.rpc4j.autoconfig;

import com.msa.api.regcovery.discovery.ServiceDiscovery;
import com.msa.api.regcovery.discovery.ZkServiceDiscovery;
import com.msa.rpc.client.RpcClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Rpc 4 j client auto configuration.
 *
 * @ClassName: Rpc4jClientAutoConfiguration
 * @Description: rpc4j框架客户端自动配置
 * @Author: sxp
 * @Date: 14 :14 2018/5/2
 * @Version: 1.0.0
 */
@Configuration
@EnableConfigurationProperties(Rpc4jClientProperties.class)
public class Rpc4jClientAutoConfiguration {
    /**
     * Client rpc client.
     *
     * @param discovery the discovery
     * @return the rpc client
     */
    @Bean
    @ConditionalOnMissingBean
    public RpcClient client(ServiceDiscovery discovery) {
        RpcClient client = new RpcClient();
        client.setServiceDiscovery(discovery);
        return client;
    }

    /**
     * Discovery service discovery.
     *
     * @param rpc4jClientProperties the rpc 4 j client properties
     * @return the service discovery
     */
    @Bean
    @ConditionalOnMissingBean
    public ServiceDiscovery discovery(Rpc4jClientProperties rpc4jClientProperties) {
        ZkServiceDiscovery discovery = new ZkServiceDiscovery();
        discovery.setZkAddress(rpc4jClientProperties.getRegistryAddress());
        return discovery;
    }
}
