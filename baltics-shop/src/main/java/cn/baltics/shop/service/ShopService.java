package cn.baltics.shop.service;

import cn.baltics.shop.dto.req.ShopReviewAddReqDTO;
import cn.baltics.shop.dto.req.ShopReviewGetReqDTO;
import cn.baltics.shop.dto.req.ShopReviewLikeReqDTO;
import cn.baltics.shop.dto.req.ShopReviewReplyAddReqDTO;
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
     * 获取评论
     * @param requestParam 请求参数
     * @return {@link ShopReviewGetRespDTO}
     */
    ShopReviewGetRespDTO getReview(ShopReviewGetReqDTO requestParam);

    /**
     * 新增评论
     * @param requestParam 请求参数
     */
    void addReview(ShopReviewAddReqDTO requestParam);

    /**
     * 新增回复
     * @param requestParam 请求参数
     */
    void addReply(ShopReviewReplyAddReqDTO requestParam);

    /**
     * 点赞
     * @param requestParam 请求参数
     */
    void addLike(ShopReviewLikeReqDTO requestParam);
}
