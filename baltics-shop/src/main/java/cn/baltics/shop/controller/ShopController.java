package cn.baltics.shop.controller;

import cn.baltics.shop.dto.req.ShopReviewAddReqDTO;
import cn.baltics.shop.dto.req.ShopReviewGetReqDTO;
import cn.baltics.shop.dto.req.ShopReviewLikeReqDTO;
import cn.baltics.shop.dto.req.ShopReviewReplyAddReqDTO;
import cn.baltics.shop.dto.resp.ShopCardRespDTO;
import cn.baltics.shop.dto.resp.ShopReviewGetRespDTO;
import cn.baltics.shop.service.ShopService;
import cn.baltics.springboot.starter.convention.result.Results;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *@func 商店服务接口
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@RestController
@AllArgsConstructor
public class ShopController {
    private ShopService shopService;

    @GetMapping("/shop/get/card")
    public Results<ShopCardRespDTO> getShopCard(@RequestBody int id) {
        ShopCardRespDTO result = shopService.getShopCard(id);
        return Results.success(result);
    }

    @GetMapping("/shop/get/review/partial")
    public Results<ShopReviewGetRespDTO> getPartialReview(@RequestBody ShopReviewGetReqDTO requestParam) {
        ShopReviewGetRespDTO result = shopService.getReview(requestParam);
        return Results.success(result);
    }

    @GetMapping("/shop/get/review")
    public Results<ShopReviewGetRespDTO> getReview(@RequestBody ShopReviewGetReqDTO requestParam) {
        ShopReviewGetRespDTO result = shopService.getReview(requestParam);
        return Results.success(result);
    }

    @PostMapping("/shop/add/review")
    public Results<Void> addReview(@RequestBody ShopReviewAddReqDTO requestParam) {
        shopService.addReview(requestParam);
        return Results.success();
    }

    @PostMapping("/shop/add/reply")
    public Results<Void> addReview(@RequestBody ShopReviewReplyAddReqDTO requestParam) {
        shopService.addReply(requestParam);
        return Results.success();
    }

    @PostMapping("/shop/add/like")
    public Results<Void> addLike(@RequestBody ShopReviewLikeReqDTO requestParam) {
        shopService.addLike(requestParam);
        return Results.success();
    }
}
