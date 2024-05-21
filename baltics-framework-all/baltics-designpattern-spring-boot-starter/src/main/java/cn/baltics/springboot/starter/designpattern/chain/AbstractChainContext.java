package cn.baltics.springboot.starter.designpattern.chain;

import cn.baltics.springboot.starter.base.ApplicationContextHolder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 *@name AbstractChainContext 抽象责任链上下文
 *
 *@author wbwyend
 *@date 2024/05/17 
 */
public class AbstractChainContext implements CommandLineRunner {
    private final Map<String, List<AbstractChainHandler>> chainMap = new HashMap<>();

    /**
     * 执行责任链
     *
     * @param mark          组件标识
     * @param requestParam  请求参数
     */
    public <T> void handler(String mark, T requestParam) {
        List<AbstractChainHandler> abstractChainHandlers = chainMap.get(mark);
        if (CollectionUtils.isEmpty(abstractChainHandlers)) {
            throw new RuntimeException(String.format("[%s] Chain of Responsibility ID is undefined.", mark));
        }
        abstractChainHandlers.forEach(each -> each.handler(requestParam));
    }

    /**
     * 实例化{@link AbstractChainContext}
     *
     * @param args incoming main method arguments
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        Map<String, AbstractChainHandler> abstractChainHandlerMap = ApplicationContextHolder.getBeansOfType(AbstractChainHandler.class);
        abstractChainHandlerMap.forEach((beanName, bean) -> {
            List<AbstractChainHandler> abstractList = chainMap.get(bean.mask());
            if (CollectionUtils.isEmpty(abstractList)) {
                abstractList = new ArrayList<>();
            }
            abstractList.add(bean);
            List<AbstractChainHandler> actualList = abstractList.stream()
                    .sorted(Comparator.comparing(Ordered::getOrder))
                    .collect(Collectors.toList());
            chainMap.put(bean.mask(), actualList);
        });
    }
}
