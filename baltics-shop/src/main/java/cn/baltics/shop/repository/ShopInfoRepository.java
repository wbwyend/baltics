package cn.baltics.shop.repository;

import cn.baltics.shop.aggregation.ShopInfo;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
public interface ShopInfoRepository {
    /**
     * 获得商店信息
     * @param id 商店ID
     * @return {@link ShopInfo}
     */
    ShopInfo getShopInfo(Integer id);
}

