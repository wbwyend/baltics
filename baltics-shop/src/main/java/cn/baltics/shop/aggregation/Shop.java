package cn.baltics.shop.aggregation;

import cn.baltics.framework.core.domain.AggregateRoot;

import java.util.List;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
public class Shop implements AggregateRoot {
    private ShopInfo shopInfo;
    private ShopScore shopScore;
    private List<ShopReview> shopReviewList;
}
