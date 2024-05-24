package cn.baltics.shop.dao.mapper;

import cn.baltics.shop.dao.entity.ShopReviewReplyDO;
import org.apache.ibatis.annotations.Mapper;

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
}
