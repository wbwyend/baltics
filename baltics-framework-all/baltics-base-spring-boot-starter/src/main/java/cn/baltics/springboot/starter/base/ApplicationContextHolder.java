package cn.baltics.springboot.starter.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 *@name ApplicationContextHolder spring上下文容器
 *
 *@author wbwyend
 *@date 2024/05/17 
 */
public class ApplicationContextHolder implements ApplicationContextAware {
    /**
     * 应用上下文
     */
    private static ApplicationContext CONTEXT;

    /**
     * 设置应用上下文
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.CONTEXT = applicationContext;
    }

    /**
     * 通过类型获取Bean
     *
     * @param tClass 类型
     * @return tClass类型的Bean
     * @param <T> 泛型
     */
    public static <T> T getBean(Class<T> tClass) {
        return CONTEXT.getBean(tClass);
    }

    /**
     * 通过Bean名和类型获取Bean
     *
     * @param name Bean名
     * @param tClass 类型
     * @return tClass类型的名为name的Bean
     * @param <T> 泛型
     */
    public static <T> T getBean(String name, Class<T> tClass) {
        return CONTEXT.getBean(name, tClass);
    }

    /**
     * 通过类型获取Bean集合
     *
     * @param tClass 类型
     * @return tClass类型的Bean集合
     * @param <T> 泛型
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> tClass) {
        return CONTEXT.getBeansOfType(tClass);
    }

    /**
     * 获取应用上下文实例
     *
     * @return 应用上下文实例
     */
    public static ApplicationContext getInstance() {
        return CONTEXT;
    }
}
