package com.msa.rpc4j;

import com.google.common.collect.Sets;
import com.msa.rpc4j.annotation.EnableRpc4jClients;
import com.msa.rpc4j.annotation.Rpc4jClient;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The type Rpc 4 j clients registrar.
 *
 * @ClassName: Rpc4jClientsRegistrar
 * @Description: rpc4j客户端注册类
 * @Author: sxp
 * @Date: 15 :53 2018/5/17
 * @Version: 1.0.0
 */
public class Rpc4jClientsRegistrar implements ImportBeanDefinitionRegistrar,
        ResourceLoaderAware, BeanClassLoaderAware, EnvironmentAware {
    /**
     * The Resource loader.
     */
    private ResourceLoader resourceLoader;

    /**
     * The Environment.
     */
    private Environment environment;

    /**
     * Sets bean class loader.
     *
     * @param classLoader the class loader
     */
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {}

    /**
     * Sets environment.
     *
     * @param environment the environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * Sets resource loader.
     *
     * @param resourceLoader the resource loader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * Register bean definitions.
     *
     * @param importingClassMetadata the importing class metadata
     * @param registry               the registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        registerRpc4jClients(importingClassMetadata, registry);
    }

    /**
     * Register rpc 4 j clients.
     *
     * @param metadata the metadata
     * @param registry the registry
     */
    private void registerRpc4jClients(AnnotationMetadata metadata,
                                      BeanDefinitionRegistry registry) {
        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        scanner.setResourceLoader(this.resourceLoader);

        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(
                Rpc4jClient.class);
        scanner.addIncludeFilter(annotationTypeFilter);
        Set<String> basePackages = getBasePackages(metadata);
        basePackages.forEach(basePackage -> {
            Set<BeanDefinition> candidateComponents = scanner
                    .findCandidateComponents(basePackage);
            if (Objects.isNull(candidateComponents)) {
                return;
            }
            candidateComponents.forEach(candidateComponent -> {
                if (candidateComponent instanceof AnnotatedBeanDefinition) {
                    AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) candidateComponent;
                    AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();
                    Assert.isTrue(annotationMetadata.isInterface(),
                            "@Rpc4jClient class must be a interface");
                    Map<String, Object> attributes = annotationMetadata
                            .getAnnotationAttributes(
                                    Rpc4jClient.class.getCanonicalName());
                    registerRpc4jClient(registry, annotationMetadata, attributes);
                }
            });
        });
    }

    /**
     * Register rpc 4 j client.
     *
     * @param registry           the registry
     * @param annotationMetadata the annotation metadata
     * @param attributes         the attributes
     */
    private void registerRpc4jClient(BeanDefinitionRegistry registry,
                                     AnnotationMetadata annotationMetadata, Map<String, Object> attributes) {
        String beanName = annotationMetadata.getClassName();
        Class rpc4jClientClazz;
        try {
            rpc4jClientClazz = this.resourceLoader.getClassLoader().loadClass(beanName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(annotationMetadata.getClassName() + " in classpath not find");
        }
        Class<?>[] classes = rpc4jClientClazz.getInterfaces();
        if (Objects.nonNull(classes)) {
            if (classes.length > 1) {
                throw new IllegalArgumentException(rpc4jClientClazz.getName() + " more than 1 interfaces");
            }
            rpc4jClientClazz = classes[0];
        }
        String target = rpc4jClientClazz.getName();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Rpc4jClientFactoryBean.class);
        builder.addPropertyValue("type", beanName);
        builder.addPropertyValue("target", target);
        registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
    }

    @Deprecated
    private Object getAttrVal(String attrName, Map<String, Object> attributes) {
        return attributes.get(attrName);
    }

    /**
     * Gets scanner.
     *
     * @return the scanner
     */
    protected ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false, this.environment) {
            @Override
            protected boolean isCandidateComponent(
                    AnnotatedBeanDefinition beanDefinition) {
                if (beanDefinition.getMetadata().isIndependent()) {
                    if (beanDefinition.getMetadata().isInterface()) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /**
     * Gets base packages.
     *
     * @param importingClassMetadata the importing class metadata
     * @return the base packages
     */
    private Set<String> getBasePackages(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> attributes = importingClassMetadata
                .getAnnotationAttributes(EnableRpc4jClients.class.getCanonicalName());

        Set<String> basePackages = Sets.newHashSet();
        Arrays.asList((String[]) attributes.get("basePackages")).forEach(
                pkg -> {
                    if (StringUtils.hasText(pkg)) {
                        basePackages.add(pkg);
                    }
                }
        );

        if (basePackages.isEmpty()) {
            basePackages.add(
                    ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        }
        return basePackages;
    }
}
