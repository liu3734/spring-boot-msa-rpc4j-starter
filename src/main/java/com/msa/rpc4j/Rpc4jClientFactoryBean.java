package com.msa.rpc4j;

import com.msa.rpc.client.RpcClient;
import com.msa.rpc.client.support.DefaultInvocationProxy;
import com.msa.rpc.client.support.RpcClientFactory;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;


/**
 * The type Rpc 4 j client factory bean.
 */
@Data
public class Rpc4jClientFactoryBean implements FactoryBean<Object>, InitializingBean,
        ApplicationContextAware {
    /**
     * The Type.
     */
    private Class<?> type;
    /**
     * The Target.
     */
    private Class<?> target;
    /**
     * The Application context.
     */
    private ApplicationContext applicationContext;
    /**
     * The Rpc client.
     */
    private RpcClient rpcClient;
    /**
     * factory
     */
    private RpcClientFactory factory;

    /**
     * Gets object.
     *
     * @return the object
     * @throws Exception the exception
     */
    @Override
    public Object getObject() throws Exception {
        return factory.newClient(type, target);
    }

    /**
     * Gets object type.
     *
     * @return the object type
     */
    @Override
    public Class<?> getObjectType() {
        return this.type;
    }

    /**
     * Is singleton boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isSingleton() {
        return true;
    }

    /**
     * After properties set.
     *
     * @throws Exception the exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.type, "type must be required");
        Assert.notNull(this.target, "target must be required");
        rpcClient = applicationContext.getBean(RpcClient.class);
        factory = new RpcClientFactory(new DefaultInvocationProxy(rpcClient));
    }

    /**
     * Sets application context.
     *
     * @param applicationContext the application context
     * @throws BeansException the beans exception
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
