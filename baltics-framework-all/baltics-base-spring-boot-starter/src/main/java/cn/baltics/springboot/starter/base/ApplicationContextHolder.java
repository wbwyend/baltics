package cn.baltics.springboot.starter.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *@name ApplicationContextHolder spring上下文容器
 *
 *@author wbwyend
 *@date 2024/05/17 
 */
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.CONTEXT = applicationContext;
    }

    public static <T> T getBean(Class<T> tClass) {
        return CONTEXT.getBean(tClass);
    }

    public static <T> T getBean(String name, Class<T> tClass) {
        return CONTEXT.getBean(name, tClass);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> tClass) {
        return CONTEXT.getBeansOfType(tClass);
    }

    public static ApplicationContext getInstance() {
        return CONTEXT;
    }
}
