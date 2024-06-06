package cn.baltics.shop.dao.mapper;

import cn.baltics.shop.aggregation.ShopReviewPicture;
import cn.baltics.shop.dao.entity.ShopReviewPictureDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/24 
 */
@Mapper
public interface ShopReviewPictureRepositoryMapper {
    List<ShopReviewPictureDO> getByReviewIds(List<Long> ids);

    void addBatch(List<ShopReviewPictureDO> pictures);
}
