package cn.baltics.shop.repository.impl;

import cn.baltics.shop.aggregation.ShopReviewPicture;
import cn.baltics.shop.dao.entity.ShopReviewPictureDO;
import cn.baltics.shop.dao.mapper.ShopReviewPictureRepositoryMapper;
import cn.baltics.shop.repository.ShopReviewPictureRepository;
import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/27 
 */
@Repository
@AllArgsConstructor
public class ShopReviewPictureRepositoryImpl implements ShopReviewPictureRepository {
    private ShopReviewPictureRepositoryMapper shopReviewPictureRepositoryMapper;
    @Override
    public List<ShopReviewPicture> getByReviewIds(List<Long> reviewIds) {
        List<ShopReviewPictureDO> result = shopReviewPictureRepositoryMapper.getByReviewIds(reviewIds);
        return BeanUtil.copyToList(result, ShopReviewPicture.class);
    }

    @Override
    public void add(List<ShopReviewPicture> pictures) {
        shopReviewPictureRepositoryMapper.addBatch(BeanUtil.copyToList(pictures, ShopReviewPictureDO.class));
    }
}
