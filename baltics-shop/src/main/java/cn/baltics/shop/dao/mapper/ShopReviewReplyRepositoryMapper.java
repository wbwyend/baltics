package cn.baltics.shop.dao.mapper;

import cn.baltics.shop.dao.entity.ShopReviewReplyDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Mapper
public interface ShopReviewReplyRepositoryMapper {
    List<ShopReviewReplyDO> getByReviewIds(List<Long> ids);

    @Insert("insert into shop_review_reply " +
            "(id, customer_id, customer_name, review_id, replied_customer_id, replied_customer_name, reply_body) " +
            "values " +
            "(#{id}, #{customerId}, #{customerName}, #{reviewId}, #{repliedCustomerId}, #{repliedCustomerName}, #{replyBody})")
    void add(ShopReviewReplyDO copyProperties);
}
