package cn.baltics.shop.aggregation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopReviewReply implements Serializable {
    private long id;
    private long customerId;
    private long reviewId;
    private long repliedCustomerId;
    private String replyBody;
}
