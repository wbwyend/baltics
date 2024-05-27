package cn.baltics.shop.repository.impl;

import cn.baltics.shop.aggregation.ShopReviewReply;
import cn.baltics.shop.dao.entity.ShopReviewReplyDO;
import cn.baltics.shop.dao.mapper.ShopReviewReplyRepositoryMapper;
import cn.baltics.shop.repository.ShopReviewReplyRepository;
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
public class ShopReviewReplyRepositoryImpl implements ShopReviewReplyRepository {
    private ShopReviewReplyRepositoryMapper shopReviewReplyRepositoryMapper;
    @Override
    public List<ShopReviewReply> getByReviewIds(List<Long> reviewIds) {
        List<ShopReviewReplyDO> result = shopReviewReplyRepositoryMapper.getByReviewIds(reviewIds);
        return BeanUtil.copyToList(result, ShopReviewReply.class);
    }

    @Override
    public void add(ShopReviewReply reply) {
        shopReviewReplyRepositoryMapper.add(BeanUtil.copyProperties(reply, ShopReviewReplyDO.class));
    }
}
