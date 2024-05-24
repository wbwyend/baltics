package cn.baltics.shop.dto.req;

import cn.baltics.customer.aggregation.Customer;
import cn.baltics.shop.aggregation.ShopReviewPicture;
import cn.baltics.shop.aggregation.ShopReviewReply;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/24 
 */
@Data
public class ShopReviewAddReqDTO implements Serializable {
    private long id;
    private long customerId;
    private long shopId;
    private String reviewBody;
    private List<ShopReviewPicture> reviewPicList;
    private long addTime;
    private long modTime;
}
