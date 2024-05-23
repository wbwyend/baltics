package cn.baltics.shop.dao.entity;

import cn.baltics.customer.aggregation.Customer;
import cn.baltics.shop.aggregation.ShopReviewPicture;
import cn.baltics.shop.aggregation.ShopReviewReply;
import lombok.Builder;
import lombok.Data;

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
public class ShopReviewDO implements Serializable {
    private long id;
    private long customerId;
    private long shopId;
    private String reviewBody;
    private int likeCount;
    private int replyCount;
    private int viewCount;
    private long addTime;
    private long modTime;
}
