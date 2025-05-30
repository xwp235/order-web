package com.xweb.starter.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Map;

@Component
public class SpringUtil implements ApplicationContextAware {

    /**
     * -- GETTER --
     *  获取applicationContext
     */
    @Getter
    private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
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
  public static <T> T getBean(String name) {
    return (T) applicationContext.getBean(name);
  }

  /**
   * 通过class获取Bean
   *
   * @param <T>   Bean类型
   * @param clazz Bean类
   * @return Bean对象
   */
  public static <T> T getBean(Class<T> clazz) {
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
  public static <T> T getBean(String name, Class<T> clazz) {
    return applicationContext.getBean(name, clazz);
  }

  /**
   * 通过类型参考返回带泛型参数的Bean
   *
   * @param reference 类型参考，用于持有转换后的泛型类型
   * @param <T>       Bean类型
   * @return 带泛型参数的Bean
   */
  @SuppressWarnings("unchecked")
  public static <T> T getBean(TypeReference<T> reference) {
    var parameterizedType = (ParameterizedType) reference.getType();
    var rawType = (Class<T>) parameterizedType.getRawType();
    var genericTypes = Arrays.stream(parameterizedType.getActualTypeArguments()).map(type -> (Class<?>) type).toArray(Class[]::new);
    var beanNames = applicationContext.getBeanNamesForType(ResolvableType.forClassWithGenerics(rawType, genericTypes));
    return getBean(beanNames[0], rawType);
  }

  /**
   * 获取指定类型对应的所有Bean，包括子类
   *
   * @param <T>  Bean类型
   * @param type 类、接口，null表示获取所有bean
   * @return 类型对应的bean，key是bean注册的name，value是Bean
   */
  public static <T> Map<String, T> getBeansOfType(Class<T> type) {
    return applicationContext.getBeansOfType(type);
  }

  /**
   * 获取指定类型对应的Bean名称，包括子类
   *
   * @param type 类、接口，null表示获取所有bean名称
   * @return bean名称
   */
  public static String[] getBeanNamesForType(Class<?> type) {
    return applicationContext.getBeanNamesForType(type);
  }

  /**
   * 获取配置文件配置项的值
   *
   * @param key 配置项key
   * @return 属性值
   */
  public static String getProperty(String key) {
    return applicationContext.getEnvironment().getProperty(key);
  }

  /**
   * 获取当前的环境配置，无配置返回null
   *
   * @return 当前的环境配置
   */
  public static String[] getActiveProfiles() {
    return applicationContext.getEnvironment().getActiveProfiles();
  }

  /**
   * 获取当前的环境配置，当有多个环境配置时，只获取第一个
   *
   * @return 当前的环境配置
   */
  public static String getActiveProfile() {
    var activeProfiles = getActiveProfiles();
    return activeProfiles.length > 0 ? activeProfiles[0] : null;
  }

  /**
   * 动态向Spring注册Bean
   * <p>
   * 由{@link org.springframework.beans.factory.BeanFactory} 实现，通过工具开放API
   *
   * @param <T>      Bean类型
   * @param beanName 名称
   * @param bean     bean
   */
  public static <T> void registerBean(String beanName, T bean) {
    var context = (ConfigurableApplicationContext) applicationContext;
    context.getBeanFactory().registerSingleton(beanName, bean);
  }


}
