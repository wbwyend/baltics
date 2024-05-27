package cn.baltics.shop.repository;

import cn.baltics.shop.aggregation.ShopReviewReply;

import java.util.List;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/27 
 */
public interface ShopReviewReplyRepository {
    /**
     * 通过ID集合查询回复
     * @param shopIds Shop ID 集合
     * @return {@link ShopReviewReply}
     */
    List<ShopReviewReply> getByReviewIds(List<Long> shopIds);

    /**
     * 新增回复
     * @param reply 回复
     */
    void add(ShopReviewReply reply);
}
