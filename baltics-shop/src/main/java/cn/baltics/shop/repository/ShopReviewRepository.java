package cn.baltics.shop.repository;

import cn.baltics.shop.aggregation.ShopReview;

import java.util.List;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
public interface ShopReviewRepository {
    List<ShopReview> getShopReviewDefaultSort(int shopId);
}
