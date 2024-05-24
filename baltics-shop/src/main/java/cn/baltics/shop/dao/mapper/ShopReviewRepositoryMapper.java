package cn.baltics.shop.dao.mapper;

import cn.baltics.shop.dao.entity.ShopReviewDO;
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
public interface ShopReviewRepositoryMapper {
    @Select("select * from shop_review where shop_id = #{shopId} limit 0, 10")
    List<ShopReviewDO> getShopReviewDefaultSort(int shopId);

    @Select("select * from shop_review where shop_id = #{shopId} order by add_time desc limit 0, 10")
    List<ShopReviewDO> getShopReviewLatestSort(int shopId);

    @Select("select * from shop_review where shop_id = #{shopId} limit #{start}, #{size}")
    List<ShopReviewDO> getDefaultPage(int shopId, int start, int size);

    @Select("select * from shop_review where shop_id = #{shopId} limit #{start}, #{size}")
    List<ShopReviewDO> getLatestPage(int shopId, int start, int size);
}
