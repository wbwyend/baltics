package cn.baltics.shop.service.impl;

import cn.baltics.customer.service.CustomerService;
import cn.baltics.shop.aggregation.*;
import cn.baltics.shop.dto.req.ShopReviewAddReqDTO;
import cn.baltics.shop.dto.req.ShopReviewGetReqDTO;
import cn.baltics.shop.dto.req.ShopReviewLikeReqDTO;
import cn.baltics.shop.dto.req.ShopReviewReplyAddReqDTO;
import cn.baltics.shop.dto.resp.ShopCardRespDTO;
import cn.baltics.shop.dto.resp.ShopReviewGetRespDTO;
import cn.baltics.shop.repository.*;
import cn.baltics.shop.service.ShopService;
import cn.baltics.springboot.starter.cache.CacheService;
import cn.baltics.springboot.starter.convention.errorcode.BaseErrorCode;
import cn.baltics.springboot.starter.convention.exception.ClientException;
import cn.baltics.springboot.starter.designpattern.strategy.AbstractStrategySelector;
import cn.baltics.springboot.starter.distributedid.SnowflakeIdUtil;
import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Service
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {
    private ShopInfoRepository shopInfoRepository;
    private ShopScoreRepository shopScoreRepository;
    private ShopReviewRepository shopReviewRepository;
    private ShopReviewPictureRepository shopReviewPictureRepository;
    private ShopReviewReplyRepository shopReviewReplyRepository;
    private AbstractStrategySelector abstractStrategySelector;
    private CacheService cacheService;
    private ShopReviewLikeRepository shopReviewLikeRepository;
    private CustomerService customerService;
    private static final String SHOP_REVIEW_LIKE_SET = "SHOP_REVIEW_LIKE_SET_";

    @Override
    public ShopCardRespDTO getShopCard(Integer id) {
        ShopInfo info = shopInfoRepository.getShopInfo(id);
        ShopScore score = shopScoreRepository.getShopScore(id);
        return new ShopCardRespDTO(info, score);
    }

    @Override
    public ShopReviewGetRespDTO getReview(ShopReviewGetReqDTO requestParam) {
        ShopReviewGetRespDTO result = abstractStrategySelector.chooseAndExecuteResp(requestParam.buildMask(), requestParam);
        List<ShopReview> reviews = result.getShopReviewList();
        if (CollectionUtils.isEmpty(reviews)) {
            throw new ClientException(BaseErrorCode.RESULT_IS_NULL);
        }
        List<Long> reviewIds = reviews.stream()
                .map(ShopReview::getId)
                .collect(Collectors.toList());
        List<ShopReviewReply> replies = shopReviewReplyRepository.getByReviewIds(reviewIds);
        List<ShopReviewPicture> pictures = shopReviewPictureRepository.getByReviewIds(reviewIds);
        Set<Long> isLikeSet = shopReviewLikeRepository.isLike(requestParam.getCustomerId());
        // 聚合
        reviews.forEach(review -> {
            review.setReviewReplyList(replies.stream().filter(reply -> reply.getReviewId() == review.getId()).collect(Collectors.toList()));
            review.setReviewPicList(pictures.stream().filter(pic -> pic.getReviewId() == review.getId()).collect(Collectors.toList()));
            review.setCustomer(customerService.getById(review.getCustomerId()));
            review.setLike(isLikeSet.contains(review.getId()));
        });
        return ShopReviewGetRespDTO.builder()
                .shopReviewList(reviews)
                .build();
    }

    @Override
    @Transactional
    public void addReview(ShopReviewAddReqDTO requestParam) {
        ShopReview review = BeanUtil.copyProperties(requestParam, ShopReview.class);
        review.setId(SnowflakeIdUtil.nextId());
        List<ShopReviewPicture> pictures = BeanUtil.copyToList(requestParam.getReviewPicList(), ShopReviewPicture.class);
        pictures.forEach(picture -> picture.setId(SnowflakeIdUtil.nextId()));
        shopReviewRepository.add(review);
        shopReviewPictureRepository.add(pictures);
    }

    @Override
    @Transactional
    public void addReply(ShopReviewReplyAddReqDTO requestParam) {
        ShopReviewReply reply = BeanUtil.copyProperties(requestParam, ShopReviewReply.class);
        reply.setId(SnowflakeIdUtil.nextId());
        shopReviewReplyRepository.add(reply);
        shopReviewRepository.updateReplyCount(reply.getReviewId());
    }

    @Override
    @Transactional
    public void addLike(ShopReviewLikeReqDTO requestParam) {
        if (cacheService.setPutIfAbsent(SHOP_REVIEW_LIKE_SET + requestParam.getReviewId(), requestParam.getCustomerId())) {
            shopReviewRepository.updateLikeCount(requestParam.getReviewId());
            shopReviewLikeRepository.add(BeanUtil.copyProperties(requestParam, ShopReviewLike.class));
        }
    }
}
