package cn.baltics.shop.repository;

import cn.baltics.shop.aggregation.ShopScore;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
public interface ShopScoreRepository {
    /**
     * 获取商店评价
     * @param id 商店ID
     * @return
     */
    ShopScore getShopScore(Integer id);
}
