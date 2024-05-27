package cn.baltics.shop.aggregation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/27 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopReviewLike {
    private long reviewId;
    private long customerId;
}
