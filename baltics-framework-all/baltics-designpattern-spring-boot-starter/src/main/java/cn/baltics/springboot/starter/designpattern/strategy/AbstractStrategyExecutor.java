package cn.baltics.springboot.starter.designpattern.strategy;
/**
 *@name AbstractStrategyExecutor 抽象策略执行接口
 *
 *@author wbwyend
 *@date 2024/05/17 
 */
public interface AbstractStrategyExecutor<Request, Response> {
    /**
     * 无返回值执行
     *
     * @param requestParam 请求参数
     */
    void execute(Request requestParam);

    /**
     * 有返回值执行
     *
     * @param requestParam 请求参数
     * @return 执行结果
     */
    Response executeResp(Request requestParam);

    /**
     * 策略标识
     *
     */
    String mask();
}
