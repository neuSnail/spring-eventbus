package com.example.demo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;
import java.util.Map;

@Component
public class SpringContextUtil implements ApplicationContextAware, EmbeddedValueResolverAware {

    private static ApplicationContext applicationContext;

    private static StringValueResolver stringValueResolver;

    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return applicationContext.getBean(clazz);
    }

    public static Map<String, Object> getBeansByAnnotation(Class<? extends Annotation> clazz) {
        return applicationContext.getBeansWithAnnotation(clazz);
    }

    public static String getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }

    public static Environment getEnv() {
        return applicationContext.getEnvironment();
    }

    public static String getProperty(String name) {
        name = "${" + name + "}";
        return stringValueResolver.resolveStringValue(name);
    }

    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        SpringContextUtil.stringValueResolver = stringValueResolver;
    }
}