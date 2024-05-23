package cn.baltics.shop.aggregation;

import cn.baltics.customer.aggregation.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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
public class ShopReview implements Serializable {
    private long id;
    private long customerId;
    private long shopId;
    private String reviewBody;
    private List<ShopReviewPicture> reviewPicList;
    private int likeCount;
    private int replyCount;
    private int viewCount;
    private long addTime;
    private long modTime;
    private Customer customer;
    private List<ShopReviewReply> reviewReplyList;
}
