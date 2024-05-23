package cn.baltics.shop.dao.mapper;

import cn.baltics.shop.dao.entity.ShopScoreDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Mapper
public interface ShopScoreRepositoryMapper {
    @Select("select * from shop_score where id = #{id}}")
    ShopScoreDO get(Integer id);
}
