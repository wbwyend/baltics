package cn.baltics.customer.service;

import cn.baltics.customer.aggregation.Customer;
import cn.baltics.customer.dto.req.CustomerLoginReqDTO;
import cn.baltics.customer.dto.req.CustomerRegisterCommitReqDTO;
import cn.baltics.customer.dto.req.CustomerRegisterVerifyReqDTO;
import cn.baltics.customer.dto.resp.CustomerLoginRespDTO;

/**
 *@name CustomerService
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
public interface CustomerService {
    /**
     * 提交注册请求
     * @param requestParam 请求参数
     */
    void registerCommit(CustomerRegisterCommitReqDTO requestParam);

    /**
     * 验证注册请求
     *
     * @param requestParam 请求参数
     */
    CustomerLoginRespDTO registerVerify(CustomerRegisterVerifyReqDTO requestParam);

    /**
     * 登录
     * @param requestParam 请求参数
     */
    CustomerLoginRespDTO login(CustomerLoginReqDTO requestParam);

    /**
     * 通过ID查询用户
     * @param customerId 用户ID
     * @return {@link Customer}
     */
    Customer getById(long customerId);
}
