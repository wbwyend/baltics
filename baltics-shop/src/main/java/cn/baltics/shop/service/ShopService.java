package cn.baltics.shop.service;

import cn.baltics.shop.dto.req.ShopReviewAddReqDTO;
import cn.baltics.shop.dto.req.ShopReviewGetReqDTO;
import cn.baltics.shop.dto.resp.ShopCardRespDTO;
import cn.baltics.shop.dto.resp.ShopReviewGetRespDTO;

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
     * @param requestParam 请求参数
     * @return {@link ShopReviewGetRespDTO}
     */
    ShopReviewGetRespDTO getPartialReview(ShopReviewGetReqDTO requestParam);

    /**
     * 分页获取全部评论
     * @param requestParam 请求参数
     * @return {@link ShopReviewGetRespDTO}
     */
    ShopReviewGetRespDTO getReview(ShopReviewGetReqDTO requestParam);

    /**
     * 新增评论
     * @param requestParam 请求参数
     */
    void addReview(ShopReviewAddReqDTO requestParam);
}
