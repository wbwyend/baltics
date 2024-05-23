package cn.baltics.shop.dto.resp;

import cn.baltics.shop.aggregation.ShopInfo;
import cn.baltics.shop.aggregation.ShopScore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopCardRespDTO implements Serializable {
    private ShopInfo shopInfo;
    private ShopScore shopScore;
}
