package cn.baltics.shop.service.handler;

import cn.baltics.shop.dto.req.ShopReviewGetReqDTO;
import cn.baltics.shop.dto.resp.ShopReviewGetRespDTO;
import cn.baltics.shop.repository.ShopReviewRepository;
import cn.baltics.shop.service.handler.enums.ShopReviewStrategyEnum;
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
public class ShopReviewPartialDefaultSortExecutor implements AbstractStrategyExecutor<ShopReviewGetReqDTO, ShopReviewGetRespDTO> {
    private ShopReviewRepository shopReviewRepository;
    @Override
    public ShopReviewGetRespDTO executeResp(ShopReviewGetReqDTO requestParam) {
        return ShopReviewGetRespDTO.builder()
                .shopReviewList(shopReviewRepository.getShopReviewDefaultSort(requestParam.getShopId()))
                .build();
    }

    @Override
    public String mask() {
        return ShopReviewStrategyEnum.PARTIAL_DEFAULT.name();
    }
}
