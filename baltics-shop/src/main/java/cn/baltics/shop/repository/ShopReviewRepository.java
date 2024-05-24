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
    /**
     * 默认排序获取评价
     * @param shopId 商店ID
     * @return {@link ShopReview}
     */
    List<ShopReview> getShopReviewDefaultSort(int shopId);

    /**
     * 最新排序获取评价
     * @param shopId 商店ID
     * @return {@link ShopReview}
     */
    List<ShopReview> getShopReviewLatestSort(int shopId);

    /**
     * 分页查询默认排序评价
     * @param shopId 商店ID
     * @param page 页号
     * @param size 页大小
     * @return {@link ShopReview}
     */
    List<ShopReview> getDefaultPage(int shopId, int page, int size);

    /**
     * 分页查询最新排序评价
     * @param shopId 商店ID
     * @param page 页号
     * @param size 页大小
     * @return {@link ShopReview}
     */
    List<ShopReview> getLatestPage(int shopId, int page, int size);
}
