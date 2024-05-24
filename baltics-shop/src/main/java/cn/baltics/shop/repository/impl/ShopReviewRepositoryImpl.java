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
    private ShopReviewReplyRepositoryMapper shopReviewReplyRepositoryMapper;
    private ShopReviewPictureRepositoryMapper shopReviewPictureRepositoryMapper;
    private CustomerRepositoryMapper customerRepositoryMapper;

    /**
     * 为评价添加回复和图片
     * @param reviewDOList 评价表
     * @return {@link ShopReview}
     */
    private List<ShopReview> addReplyAndPicToReview(List<ShopReviewDO> reviewDOList) {
        if (CollectionUtils.isEmpty(reviewDOList)) {
            return new ArrayList<>();
        }
        List<Long> ids = reviewDOList.stream().map(ShopReviewDO::getShopId).collect(Collectors.toList());
        List<ShopReviewReplyDO> replyDOList = shopReviewReplyRepositoryMapper.getByReviewIds(ids);
        List<ShopReviewPictureDO> pictureDOList = shopReviewPictureRepositoryMapper.getByReviewIds(ids);
        List<CustomerDO> customerDOList = customerRepositoryMapper.getCustomerByIds(reviewDOList.stream()
                .map(ShopReviewDO::getCustomerId)
                .collect(Collectors.toList()));
        List<ShopReview> result = BeanUtil.copyToList(reviewDOList, ShopReview.class);
        result.forEach(review -> {
            review.setReviewReplyList(BeanUtil.copyToList(replyDOList.stream()
                            .filter(replyDO -> replyDO.getReviewId() == review.getId())
                            .collect(Collectors.toList()), ShopReviewReply.class));
            review.setReviewPicList(BeanUtil.copyToList(pictureDOList.stream()
                            .filter(pictureDO -> pictureDO.getReviewId() == review.getId())
                            .collect(Collectors.toList()), ShopReviewPicture.class));
            review.setCustomer(BeanUtil.copyProperties(customerDOList.stream()
                            .filter(customerDO -> customerDO.getId() == review.getCustomerId())
                            .collect(Collectors.toList())
                            .get(0), Customer.class));
        });
        return result;
    }
    @Override
    public List<ShopReview> getShopReviewDefaultSort(int shopId) {
        List<ShopReviewDO> result = shopReviewRepositoryMapper.getShopReviewDefaultSort(shopId);
        return addReplyAndPicToReview(result);
    }

    @Override
    public List<ShopReview> getShopReviewLatestSort(int shopId) {
        List<ShopReviewDO> result = shopReviewRepositoryMapper.getShopReviewLatestSort(shopId);
        return addReplyAndPicToReview(result);
    }

    @Override
    public List<ShopReview> getDefaultPage(int shopId, int page, int size) {
        int start = (page - 1) * size;
        List<ShopReviewDO> result = shopReviewRepositoryMapper.getDefaultPage(shopId, start, size);
        return addReplyAndPicToReview(result);
    }

    @Override
    public List<ShopReview> getLatestPage(int shopId, int page, int size) {
        int start = (page - 1) * size;
        List<ShopReviewDO> result = shopReviewRepositoryMapper.getLatestPage(shopId, start, size);
        return addReplyAndPicToReview(result);
    }
}
