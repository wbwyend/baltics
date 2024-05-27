package cn.baltics.shop.dao.entity;

import lombok.Builder;
import lombok.Data;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/27 
 */
@Data
@Builder
public class ShopReviewLikeDO {
    private long reviewId;
    private long customerId;
}
