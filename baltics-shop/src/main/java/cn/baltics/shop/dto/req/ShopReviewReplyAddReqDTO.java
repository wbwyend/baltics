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
public class ShopReviewReplyAddReqDTO {
    private long reviewId;
    private long customerId;
    private String customerName;
    private long repliedCustomerId;
    private String repliedCustomerName;
    private String replyBody;
}
