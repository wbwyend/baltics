package cn.baltics.springboot.starter.designpattern.chain;

import org.springframework.core.Ordered;

/**
 *@name AbstractChainHandler 抽象责任链执行者接口
 *
 *@author wbwyend
 *@date 2024/05/17 
 */
public interface AbstractChainHandler<T> extends Ordered {
    /**
     * 执行责任链
     *
     * @param requestParam 执行参数
     */
    void handler(T requestParam);

    /**
     * 责任链标识
     *
     */
    String mask();
}
