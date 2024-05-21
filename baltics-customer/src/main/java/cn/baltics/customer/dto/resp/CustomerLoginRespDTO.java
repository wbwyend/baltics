package cn.baltics.customer.dto.resp;

import lombok.Builder;
import lombok.Data;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/20 
 */
@Data
@Builder
public class CustomerLoginRespDTO {
    private String token;
}
