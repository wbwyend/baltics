package cn.baltics.springboot.starter.base.event;

import org.springframework.context.ApplicationEvent;

/**
 *@name ApplicationInitializingEvent 应用初始化事件
 *
 *@author wbwyend
 *@date 2024/05/17 
 */
public class ApplicationInitializingEvent extends ApplicationEvent {
    public ApplicationInitializingEvent(Object source) {
        super(source);
    }
}
