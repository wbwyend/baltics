package cn.baltics.shop.dao.mapper;

import cn.baltics.shop.aggregation.ShopInfo;
import cn.baltics.shop.dao.entity.ShopInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Mapper
public interface ShopInfoRepositoryMapper {

    @Select("select * from shop_info where id = #{id}")
    ShopInfoDO get(Integer id);
}
