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
    List<ShopReview> getDefaultPart(long shopId);

    /**
     * 最新排序获取评价
     * @param shopId 商店ID
     * @return {@link ShopReview}
     */
    List<ShopReview> getLatestPart(long shopId);

    /**
     * 分页查询默认排序评价
     * @param shopId 商店ID
     * @param page 页号
     * @param size 页大小
     * @return {@link ShopReview}
     */
    List<ShopReview> getDefaultPage(long shopId, int page, int size);

    /**
     * 分页查询最新排序评价
     * @param shopId 商店ID
     * @param page 页号
     * @param size 页大小
     * @return {@link ShopReview}
     */
    List<ShopReview> getLatestPage(long shopId, int page, int size);

    /**
     * 添加新评价
     * @param review 评价
     */
    void add(ShopReview review);

    /**
     * 回复数加一
     * @param reviewId 评价ID
     */
    void updateReplyCount(long reviewId);

    /**
     * 点赞数家一
     * @param reviewId 评价ID
     */
    void updateLikeCount(long reviewId);
}
