package cn.baltics.shop.dao.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 *@func 商店数据对象
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Data
@Builder
public class ShopInfoDO implements Serializable {
    private long id;
    private int shopType;
    private String shopName;
    private String address;
    private String phone;
    private String openHours;
}
