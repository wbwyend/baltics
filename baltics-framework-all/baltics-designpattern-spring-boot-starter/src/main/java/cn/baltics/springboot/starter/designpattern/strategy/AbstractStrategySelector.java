package cn.baltics.springboot.starter.designpattern.strategy;

import cn.baltics.springboot.starter.base.ApplicationContextHolder;
import cn.baltics.springboot.starter.base.event.ApplicationInitializingEvent;
import cn.baltics.springboot.starter.base.init.ApplicationInitializationPostProcessor;
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
public class AbstractStrategySelector implements ApplicationListener<ApplicationInitializingEvent> {
    private final Map<String, AbstractStrategyExecutor> strategyMap = new HashMap<>();

    /**
     * 选择策略
     *
     * @param mask 策略标识
     * @return actual strategy
     */
    private AbstractStrategyExecutor choose(String mask) {
        return Optional.ofNullable(strategyMap.get(mask)).orElseThrow(RuntimeException::new);
    }

    /**
     * 选择策略并执行
     *
     * @param mask 策略标识
     * @param requestParam 请求参数
     * @param <Request>
     */
    public <Request> void chooseAndExecute(String mask, Request requestParam) {
        AbstractStrategyExecutor executeStrategy = choose(mask);
        executeStrategy.execute(requestParam);
    }

    /**
     * 选择策略并执行，有返回值
     *
     * @param mask 策略标识
     * @param requestParam 请求参数
     * @return 执行结果
     * @param <Request>
     * @param <Response>
     */
    public <Request, Response> Response chooseAndExecuteResp(String mask, Request requestParam) {
        AbstractStrategyExecutor executeStrategy = choose(mask);
        return (Response) executeStrategy.executeResp(requestParam);
    }

    /**
     * 监听应用初始化事件，初始化{@link AbstractStrategySelector}
     *
     * @param event the event to respond to
     * @publisher {@link ApplicationInitializationPostProcessor}
     */
    @Override
    public void onApplicationEvent(ApplicationInitializingEvent event) {
        Map<String, AbstractStrategyExecutor> abstractStrategyExecutorMap = ApplicationContextHolder.getBeansOfType(AbstractStrategyExecutor.class);
        abstractStrategyExecutorMap.forEach((beanName, bean) -> {
            strategyMap.putIfAbsent(bean.mask(), bean);
        });
    }
}
