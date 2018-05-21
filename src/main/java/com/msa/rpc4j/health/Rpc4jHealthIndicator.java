package com.msa.rpc4j.health;

import com.msa.rpc4j.autoconfig.Rpc4jServerProperties;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

import java.net.InetAddress;

/**
 * The type Rpc 4 j health indicator.
 */
public class Rpc4jHealthIndicator extends AbstractHealthIndicator {

    /**
     * The Rpc 4 j server properties.
     */
    private final Rpc4jServerProperties rpc4jServerProperties;

    /**
     * Instantiates a new Rpc 4 j health indicator.
     *
     * @param rpc4jServerProperties the rpc 4 j server properties
     */
    public Rpc4jHealthIndicator(Rpc4jServerProperties rpc4jServerProperties) {
        this.rpc4jServerProperties = rpc4jServerProperties;
    }

    /**
     * Do health check.
     *
     * @param builder the builder
     * @throws Exception the exception
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up();
        builder.withDetail("server.host", InetAddress.getLocalHost().getAddress())
                .withDetail("server.port", rpc4jServerProperties.getPort());
    }
}
