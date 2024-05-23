package cn.baltics.shop.dao.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Data
@Builder
public class ShopReviewReplyDO implements Serializable {
    private long id;
    private long customerId;
    private long reviewId;
    private long repliedCustomerId;
    private String replyBody;
}
