package com.tianqi.common.util;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.ArrayUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/11 17:41
 * @Description:
 */
public class SpringUtil {

    private static ApplicationContext applicationContext;

    /**
     * 获取applicationContext
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(final ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }

    //通过name获取 Bean.

    /**
     * 通过name获取 Bean
     *
     * @param <T>  Bean类型
     * @param name Bean名称
     * @return Bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(final String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 通过class获取Bean
     *
     * @param <T>   Bean类型
     * @param clazz Bean类
     * @return Bean对象
     */
    public static <T> T getBean(final Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param <T>   bean类型
     * @param name  Bean名称
     * @param clazz bean类型
     * @return Bean对象
     */
    public static <T> T getBean(final String name, final Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    /**
     * 通过类型参考返回带泛型参数的Bean
     *
     * @param reference 类型参考，用于持有转换后的泛型类型
     * @param <T>       Bean类型
     * @return 带泛型参数的Bean
     * @since 5.4.0
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(final ParameterizedTypeReference<T> reference) {
        final ParameterizedType type = (ParameterizedType) reference.getType();
        final Class<T> rawType = (Class<T>) type.getRawType();
        final String[] beanNamesForType =
                applicationContext.getBeanNamesForType(ResolvableType.forType(reference));
        return getBean(beanNamesForType[0], rawType);
    }

    public static <T> T getBean(final TypeReference<T> typeReference) {
        final ParameterizedType parameterizedType =
                (ParameterizedType) typeReference.getType();
        final Class<T> rawType = (Class<T>) parameterizedType.getRawType();
        final Class<?>[] genericTypes =
                Arrays.stream(parameterizedType.getActualTypeArguments())
                        .map(Type::getClass).toArray(Class[]::new);
        final String[] beanNames = applicationContext.getBeanNamesForType(
                ResolvableType.forClassWithGenerics(rawType, genericTypes));
        return getBean(beanNames[0], rawType);
    }

    /**
     * 获取指定类型对应的所有Bean，包括子类
     *
     * @param <T>  Bean类型
     * @param type 类、接口，null表示获取所有bean
     * @return 类型对应的bean，key是bean注册的name，value是Bean
     * @since 5.3.3
     */
    public static <T> Map<String, T> getBeansOfType(final Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    /**
     * 获取指定类型对应的Bean名称，包括子类
     *
     * @param type 类、接口，null表示获取所有bean名称
     * @return bean名称
     * @since 5.3.3
     */
    public static String[] getBeanNamesForType(final Class<?> type) {
        return applicationContext.getBeanNamesForType(type);
    }

    /**
     * 获取配置文件配置项的值
     *
     * @param key 配置项key
     * @return 属性值
     * @since 5.3.3
     */
    public static String getProperty(final String key) {
        return applicationContext.getEnvironment().getProperty(key);
    }

    /**
     * 获取当前的环境配置，无配置返回null
     *
     * @return 当前的环境配置
     * @since 5.3.3
     */
    public static String[] getActiveProfiles() {
        return applicationContext.getEnvironment().getActiveProfiles();
    }

    /**
     * 获取当前的环境配置，当有多个环境配置时，只获取第一个
     *
     * @return 当前的环境配置
     * @since 5.3.3
     */
    public static String getActiveProfile() {
        final String[] activeProfiles = getActiveProfiles();
        return ArrayUtil.isNotEmpty(activeProfiles) ? activeProfiles[0] : null;
    }

    /**
     * 动态向Spring注册Bean
     * <p>
     * 由{@link org.springframework.beans.factory.BeanFactory} 实现，通过工具开放API
     *
     * @param <T>      Bean类型
     * @param beanName 名称
     * @param bean     bean
     * @author shadow
     * @since 5.4.2
     */
    public static <T> void registerBean(final String beanName, final T bean) {
        final ConfigurableApplicationContext context =
                (ConfigurableApplicationContext) applicationContext;
        context.getBeanFactory().registerSingleton(beanName, bean);
    }
}
