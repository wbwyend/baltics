package cn.baltics.shop.repository.impl;

import cn.baltics.shop.aggregation.ShopReview;
import cn.baltics.shop.aggregation.ShopReviewReply;
import cn.baltics.shop.dao.entity.ShopReviewDO;
import cn.baltics.shop.dao.mapper.ShopReviewReplyRepositoryMapper;
import cn.baltics.shop.dao.mapper.ShopReviewRepositoryMapper;
import cn.baltics.shop.repository.ShopReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

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
    private ShopReviewReplyRepositoryMapper shopReviewReplyRepositoryMapper;
    @Override
    public List<ShopReview> getShopReviewDefaultSort(int shopId) {
        List<ShopReviewDO> result = shopReviewRepositoryMapper.getShopReviewDefaultSort(shopId);
        List<Long> ids = result.stream().map(ShopReviewDO::getShopId).collect(Collectors.toList());

//        shopReviewReplyRepositoryMapper.get();

        return null;
    }
}
