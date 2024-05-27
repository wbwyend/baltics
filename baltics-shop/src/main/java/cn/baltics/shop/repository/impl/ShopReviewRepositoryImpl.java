package cn.baltics.shop.repository.impl;

import cn.baltics.customer.aggregation.Customer;
import cn.baltics.customer.dao.entity.CustomerDO;
import cn.baltics.customer.dao.mapper.CustomerRepositoryMapper;
import cn.baltics.shop.aggregation.ShopReview;
import cn.baltics.shop.aggregation.ShopReviewPicture;
import cn.baltics.shop.aggregation.ShopReviewReply;
import cn.baltics.shop.dao.entity.ShopReviewDO;
import cn.baltics.shop.dao.entity.ShopReviewPictureDO;
import cn.baltics.shop.dao.entity.ShopReviewReplyDO;
import cn.baltics.shop.dao.mapper.ShopReviewPictureRepositoryMapper;
import cn.baltics.shop.dao.mapper.ShopReviewReplyRepositoryMapper;
import cn.baltics.shop.dao.mapper.ShopReviewRepositoryMapper;
import cn.baltics.shop.repository.ShopReviewRepository;
import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Repository
@AllArgsConstructor
public class ShopReviewRepositoryImpl implements ShopReviewRepository {
    private ShopReviewRepositoryMapper shopReviewRepositoryMapper;

    @Override
    public List<ShopReview> getDefaultPart(long shopId) {
        List<ShopReviewDO> result = shopReviewRepositoryMapper.getShopReviewDefaultSort(shopId);
        return BeanUtil.copyToList(result, ShopReview.class);
    }

    @Override
    public List<ShopReview> getLatestPart(long shopId) {
        List<ShopReviewDO> result = shopReviewRepositoryMapper.getShopReviewDefaultSort(shopId);
        return BeanUtil.copyToList(result, ShopReview.class);
    }

    @Override
    public List<ShopReview> getDefaultPage(long shopId, int page, int size) {
        int start = (page - 1) * size;
        List<ShopReviewDO> result = shopReviewRepositoryMapper.getDefaultPage(shopId, start, size);
        return BeanUtil.copyToList(result, ShopReview.class);
    }

    @Override
    public List<ShopReview> getLatestPage(long shopId, int page, int size) {
        int start = (page - 1) * size;
        List<ShopReviewDO> result = shopReviewRepositoryMapper.getLatestPage(shopId, start, size);
        return BeanUtil.copyToList(result, ShopReview.class);
    }

    @Override
    public void add(ShopReview review) {
        shopReviewRepositoryMapper.add(BeanUtil.copyProperties(review, ShopReviewDO.class));
    }

    @Override
    public void updateReplyCount(long reviewId) {
        shopReviewRepositoryMapper.plusReplyCount(reviewId);
    }

    @Override
    public void updateLikeCount(long reviewId) {
        shopReviewRepositoryMapper.plusLikeCount(reviewId);
    }
}
