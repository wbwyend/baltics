package cn.baltics.springboot.starter.base.init;

import cn.baltics.springboot.starter.base.event.ApplicationInitializingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

/**
 *@name ApplicationInitializationPostProcessor 应用初始化后置处理器，防止Spring事件被多次执行
 *
 *@author wbwyend
 *@date 2024/05/17 
 */
public class ApplicationInitializationPostProcessor implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    ApplicationContext applicationContext;

    private volatile static boolean executeOnlyOnce = true;

    /**
     * 监听应用就绪事件，发布应用初始化事件
     *
     * @param event the event to respond to
     * @publisher Application
     * @listener AbstractStrategySelector
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        synchronized (ApplicationInitializationPostProcessor.class) {
            if (executeOnlyOnce) {
                applicationContext.publishEvent(new ApplicationInitializingEvent(this));
                ApplicationInitializationPostProcessor.executeOnlyOnce = false;
            }
        }
    }
}
