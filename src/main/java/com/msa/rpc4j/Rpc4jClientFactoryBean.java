package com.msa.rpc4j;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName: Rpc4jClientFactoryBean
 * @Description: rpc4j client工厂类
 * @Author: sxp
 * @Date: 16:52 2018/5/17
 * @Version: 1.0.0
 */
public class Rpc4jClientFactoryBean implements FactoryBean<Object>, InitializingBean,
        ApplicationContextAware {

    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
