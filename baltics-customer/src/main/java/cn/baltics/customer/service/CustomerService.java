package cn.baltics.customer.service;

import cn.baltics.customer.dto.req.CustomerRegisterCommitReqDTO;
import cn.baltics.customer.dto.req.CustomerRegisterVerifyReqDTO;

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

    void registerVerify(CustomerRegisterVerifyReqDTO requestParam);
}
