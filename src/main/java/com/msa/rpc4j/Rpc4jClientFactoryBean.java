package com.msa.rpc4j;

import com.msa.rpc.client.RpcClient;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * The type Rpc 4 j client factory bean.
 *
 * @ClassName: Rpc4jClientFactoryBean
 * @Description: rpc4j client工厂类
 * @Author: sxp
 * @Date: 16 :52 2018/5/17
 * @Version: 1.0.0
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
     * Gets object.
     *
     * @return the object
     * @throws Exception the exception
     */
    @Override
    public Object getObject() throws Exception {
        // 动态代理生成bean
        return rpcClient.newInstance(type, target);
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
