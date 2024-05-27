package cn.baltics.shop.repository.impl;

import cn.baltics.shop.aggregation.ShopReviewLike;
import cn.baltics.shop.dao.entity.ShopReviewLikeDO;
import cn.baltics.shop.dao.mapper.ShopReviewLikeRepositoryMapper;
import cn.baltics.shop.repository.ShopReviewLikeRepository;
import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/27 
 */
@Repository
@AllArgsConstructor
public class ShopReviewLikeRepositoryImpl implements ShopReviewLikeRepository {
    private ShopReviewLikeRepositoryMapper shopReviewLikeRepositoryMapper;
    @Override
    public Set<Long> isLike(long customerId) {
        List<Long> result = shopReviewLikeRepositoryMapper.getLikeList(customerId);
        return new HashSet<>(result);
    }

    @Override
    public void add(ShopReviewLike like) {
        shopReviewLikeRepositoryMapper.add(BeanUtil.copyProperties(like, ShopReviewLikeDO.class));
    }
}
