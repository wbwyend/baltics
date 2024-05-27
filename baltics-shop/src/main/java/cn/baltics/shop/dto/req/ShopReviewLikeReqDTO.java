package cn.baltics.shop.dto.req;

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
public class ShopReviewLikeReqDTO {
    private long customerId;
    private long reviewId;
}
