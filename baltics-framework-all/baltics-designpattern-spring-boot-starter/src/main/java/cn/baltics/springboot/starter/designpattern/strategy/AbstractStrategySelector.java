package cn.baltics.springboot.starter.designpattern.strategy;

import cn.baltics.springboot.starter.base.ApplicationContextHolder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *@name AbstractStrategySelector 抽象策略选择器
 *
 *@author wbwyend
 *@date 2024/05/17 
 */
public class AbstractStrategySelector implements ApplicationListener<ApplicationEvent> {
    private final Map<String, AbstractStrategyExecutor> strategyMap = new HashMap<>();

    private AbstractStrategyExecutor choose(String mask) {
        return Optional.ofNullable(strategyMap.get(mask)).orElseThrow(RuntimeException::new);
    }

    public <Request> void chooseAndExecute(String mask, Request requestParam) {
        AbstractStrategyExecutor executeStrategy = choose(mask);
        executeStrategy.execute(requestParam);
    }

    public <Request, Response> Response chooseAndExecuteResp(String mask, Request requestParam) {
        AbstractStrategyExecutor executeStrategy = choose(mask);
        return (Response) executeStrategy.executeResp(requestParam);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Map<String, AbstractStrategyExecutor> abstractStrategyExecutorMap = ApplicationContextHolder.getBeansOfType(AbstractStrategyExecutor.class);
        abstractStrategyExecutorMap.forEach((beanName, bean) -> {
            strategyMap.putIfAbsent(bean.mask(), bean);
        });
    }
}
