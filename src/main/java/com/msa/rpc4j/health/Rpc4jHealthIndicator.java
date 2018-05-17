package com.msa.rpc4j.health;

import com.msa.rpc4j.autoconfig.Rpc4jServerProperties;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

import java.net.InetAddress;

/**
 * The type Rpc 4 j health indicator.
 *
 * @ClassName: Rpc4jHealthIndicator
 * @Description: rpc框架服务端心跳检测
 * @Author: sxp
 * @Date: 17 :03 2018/5/2
 * @Version: 1.0.0
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
     * Actual health check logic.
     *
     * @param builder the {@link Builder} to report health status and details
     * @throws Exception any {@link Exception} that should create a {@link Status#DOWN}                   system status.
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up();
        builder.withDetail("server.host", InetAddress.getLocalHost().getAddress())
                .withDetail("server.port", rpc4jServerProperties.getPort());
    }
}
