package cn.baltics.shop.service.handler;

import cn.baltics.shop.dto.req.ShopReviewReqDTO;
import cn.baltics.shop.dto.resp.ShopReviewRespDTO;
import cn.baltics.shop.repository.ShopReviewRepository;
import cn.baltics.springboot.starter.designpattern.strategy.AbstractStrategyExecutor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Component
@AllArgsConstructor
public class ShopReviewDefaultSortExecutor implements AbstractStrategyExecutor<ShopReviewReqDTO, ShopReviewRespDTO> {
    private ShopReviewRepository shopReviewRepository;
    @Override
    public ShopReviewRespDTO executeResp(ShopReviewReqDTO requestParam) {
        shopReviewRepository.getShopReviewDefaultSort(requestParam.getShopId());
        return AbstractStrategyExecutor.super.executeResp(requestParam);
    }

    @Override
    public String mask() {
        return null;
    }
}
