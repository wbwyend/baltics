package cn.baltics.shop.service.impl;

import cn.baltics.shop.aggregation.ShopInfo;
import cn.baltics.shop.aggregation.ShopReview;
import cn.baltics.shop.aggregation.ShopScore;
import cn.baltics.shop.dto.req.ShopReviewAddReqDTO;
import cn.baltics.shop.dto.req.ShopReviewGetReqDTO;
import cn.baltics.shop.dto.resp.ShopCardRespDTO;
import cn.baltics.shop.dto.resp.ShopReviewGetRespDTO;
import cn.baltics.shop.repository.ShopInfoRepository;
import cn.baltics.shop.repository.ShopReviewRepository;
import cn.baltics.shop.repository.ShopScoreRepository;
import cn.baltics.shop.service.ShopService;
import cn.baltics.springboot.starter.designpattern.strategy.AbstractStrategySelector;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    private AbstractStrategySelector abstractStrategySelector;

    @Override
    public ShopCardRespDTO getShopCard(Integer id) {
        ShopInfo info = shopInfoRepository.getShopInfo(id);
        ShopScore score = shopScoreRepository.getShopScore(id);
        return new ShopCardRespDTO(info, score);
    }

    @Override
    public ShopReviewGetRespDTO getPartialReview(ShopReviewGetReqDTO requestParam) {
        return abstractStrategySelector.chooseAndExecuteResp(requestParam.buildMask(), requestParam);
    }

    @Override
    public ShopReviewGetRespDTO getReview(ShopReviewGetReqDTO requestParam) {
        return abstractStrategySelector.chooseAndExecuteResp(requestParam.buildMask(), requestParam);
    }

    @Override
    public void addReview(ShopReviewAddReqDTO requestParam) {
//        shopReviewRepository.add();
    }
}
