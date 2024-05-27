package cn.baltics.shop.repository;

import cn.baltics.shop.aggregation.ShopReviewLike;

import java.util.Set;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/27 
 */
public interface ShopReviewLikeRepository {
    /**
     * 获取点赞集合
     * @param customerId 用户ID
     */
    Set<Long> isLike(long customerId);

    /**
     * 点赞
     * @param like 点赞实体
     */
    void add(ShopReviewLike like);
}
