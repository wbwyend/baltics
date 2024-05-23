package cn.baltics.shop.service;

import cn.baltics.shop.dto.req.ShopReviewReqDTO;
import cn.baltics.shop.dto.resp.ShopCardRespDTO;
import cn.baltics.shop.dto.resp.ShopReviewRespDTO;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
public interface ShopService {
    /**
     * 获取商店主页卡片信息
     * @param id 商品ID
     */
    ShopCardRespDTO getShopCard(Integer id);

    /**
     * 获取精选评论
     * @param shopId 商店ID
     * @param sort 排序方式
     * @return {@link ShopReviewRespDTO}
     */
    ShopReviewRespDTO getPartialReview(ShopReviewReqDTO requestParam);
}
