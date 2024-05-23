package cn.baltics.shop.aggregation;

import cn.baltics.framework.core.domain.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *@func 商店信息
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopInfo implements Serializable {
    private long id;
    private int shopType;
    private String shopName;
    private String address;
    private String phone;
    private String openHours;
}
