package cn.baltics.shop.dao.mapper;

import cn.baltics.shop.dao.entity.ShopReviewLikeDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/27 
 */
@Mapper
public interface ShopReviewLikeRepositoryMapper {
    @Select("select * from shop_review_like where customer_id = #{customerId}")
    List<Long> getLikeList(long customerId);

    @Insert("insert into shop_review_like " +
            "(customer_id, review_id) " +
            "values " +
            "(#{customerId}, #{reviewId})")
    void add(ShopReviewLikeDO shopReviewLikeDO);
}
