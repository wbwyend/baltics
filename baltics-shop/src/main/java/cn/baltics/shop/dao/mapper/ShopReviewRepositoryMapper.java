package cn.baltics.shop.dao.mapper;

import cn.baltics.shop.dao.entity.ShopReviewDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Mapper
public interface ShopReviewRepositoryMapper {
    @Select("select * from shop_review where shop_id = #{shopId} limit 0, 10")
    List<ShopReviewDO> getShopReviewDefaultSort(long shopId);

    @Select("select * from shop_review where shop_id = #{shopId} order by add_time desc limit 0, 10")
    List<ShopReviewDO> getShopReviewLatestSort(long shopId);

    @Select("select * from shop_review where shop_id = #{shopId} limit #{start}, #{size}")
    List<ShopReviewDO> getDefaultPage(long shopId, int start, int size);

    @Select("select * from shop_review where shop_id = #{shopId} limit #{start}, #{size}")
    List<ShopReviewDO> getLatestPage(long shopId, int start, int size);

    @Insert("insert into shop_review" +
            "(id, customer_id, shop_id, review_body, add_time, mod_time) " +
            "values (#{id}, #{customerId}, #{shopId}, #{reviewBody}, #{addTime}, #{modTime})")
    void add(ShopReviewDO copyProperties);

    @Update("update shop_review set view_count = view_count + 1")
    void plusReplyCount(long reviewId);

    @Update("update shop_review set like_count = like_count + 1")
    void plusLikeCount(long reviewId);
}
