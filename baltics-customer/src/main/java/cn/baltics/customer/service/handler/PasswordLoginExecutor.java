package cn.baltics.customer.service.handler;

import cn.baltics.customer.aggregation.Customer;
import cn.baltics.customer.dto.req.CustomerLoginReqDTO;
import cn.baltics.customer.dto.resp.CustomerLoginRespDTO;
import cn.baltics.customer.enums.CustomerErrorEnum;
import cn.baltics.customer.enums.CustomerLoginMethodEnum;
import cn.baltics.customer.repository.CustomerRepository;
import cn.baltics.springboot.starter.common.util.StringUtil;
import cn.baltics.springboot.starter.common.util.TokenUtil;
import cn.baltics.springboot.starter.convention.exception.ClientException;
import cn.baltics.springboot.starter.designpattern.strategy.AbstractStrategyExecutor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *@func 密码登录策略执行器
 *
 *@author wbwyend
 *@date 2024/05/20 
 */
@Component
@AllArgsConstructor
public class PasswordLoginExecutor implements AbstractStrategyExecutor<CustomerLoginReqDTO, CustomerLoginRespDTO> {

    private CustomerRepository customerRepository;

    @Override
    public CustomerLoginRespDTO executeResp(CustomerLoginReqDTO requestParam) {
        Customer result = customerRepository.getCustomerByUsername(requestParam.getUsername());
        if (result == null || StringUtil.isNullOrBlank(result.getPassword()) || !requestParam.getPassword().equals(result.getPassword())) {
            throw  new ClientException(CustomerErrorEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        return CustomerLoginRespDTO.builder()
                .token(TokenUtil.createToken(result.getId().toString(), result.getPassword()))
                .build();
    }

    @Override
    public String mask() {
        return CustomerLoginMethodEnum.PASSWORD.name();
    }
}
