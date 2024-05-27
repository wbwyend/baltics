package cn.baltics.shop.service.handler;

import cn.baltics.customer.service.CustomerService;
import cn.baltics.shop.aggregation.ShopReview;
import cn.baltics.shop.aggregation.ShopReviewPicture;
import cn.baltics.shop.aggregation.ShopReviewReply;
import cn.baltics.shop.dto.req.ShopReviewGetReqDTO;
import cn.baltics.shop.dto.resp.ShopReviewGetRespDTO;
import cn.baltics.shop.repository.ShopReviewLikeRepository;
import cn.baltics.shop.repository.ShopReviewPictureRepository;
import cn.baltics.shop.repository.ShopReviewReplyRepository;
import cn.baltics.shop.repository.ShopReviewRepository;
import cn.baltics.shop.service.handler.enums.ShopReviewStrategyEnum;
import cn.baltics.springboot.starter.convention.errorcode.BaseErrorCode;
import cn.baltics.springboot.starter.convention.exception.ClientException;
import cn.baltics.springboot.starter.designpattern.strategy.AbstractStrategyExecutor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *@func 分页查询默认评论
 *
 *@author wbwyend
 *@date 2024/05/24
 */
@Component
@AllArgsConstructor
public class ShopReviewPageDefaultSortExecutor implements AbstractStrategyExecutor<ShopReviewGetReqDTO, ShopReviewGetRespDTO> {
    private ShopReviewRepository shopReviewRepository;

    @Override
    public ShopReviewGetRespDTO executeResp(ShopReviewGetReqDTO requestParam) {
        return ShopReviewGetRespDTO.builder()
                .shopReviewList(shopReviewRepository.getLatestPage(requestParam.getShopId(), requestParam.getPage(), requestParam.getSize()))
                .build();
    }

    @Override
    public String mask() {
        return ShopReviewStrategyEnum.PAGE_DEFAULT.name();
    }
}
