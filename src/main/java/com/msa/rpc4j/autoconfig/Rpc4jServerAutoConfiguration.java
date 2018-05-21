package com.msa.rpc4j.autoconfig;

import com.msa.api.regcovery.registry.ServiceRegistry;
import com.msa.rpc.server.RpcServer;
import com.msa.rpc4j.health.Rpc4jHealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Rpc 4 j server auto configuration.
 */
@Configuration
@EnableConfigurationProperties(Rpc4jServerProperties.class)
public class Rpc4jServerAutoConfiguration {
    /**
     * Rpc server rpc server.
     *
     * @param registry              the registry
     * @param rpc4jServerProperties the rpc 4 j server properties
     * @return the rpc server
     */
    @Bean
    @ConditionalOnMissingBean
    public RpcServer rpcServer(ServiceRegistry registry, Rpc4jServerProperties rpc4jServerProperties) {
        RpcServer server = new RpcServer();
        server.setPort(rpc4jServerProperties.getPort());
        server.setRegistry(registry);
        return server;
    }

    /**
     * Registry service registry.
     *
     * @param rpc4jServerProperties the rpc 4 j server properties
     * @return the service registry
     */
    @Bean
    @ConditionalOnMissingBean
    public ServiceRegistry registry(Rpc4jServerProperties rpc4jServerProperties) {
        ServiceRegistry registry = new ServiceRegistry();
        registry.setZkAddress(rpc4jServerProperties.getRegistryAddress());
        return registry;
    }

    /**
     * Health indicator rpc 4 j health indicator.
     *
     * @param rpc4jServerProperties the rpc 4 j server properties
     * @return the rpc 4 j health indicator
     */
    @Bean
    @ConditionalOnMissingBean
    public Rpc4jHealthIndicator healthIndicator(Rpc4jServerProperties rpc4jServerProperties) {
        return new Rpc4jHealthIndicator(rpc4jServerProperties);
    }
}
