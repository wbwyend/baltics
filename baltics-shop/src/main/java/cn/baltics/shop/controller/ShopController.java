package cn.baltics.shop.controller;

import cn.baltics.shop.dto.req.ShopReviewReqDTO;
import cn.baltics.shop.dto.resp.ShopCardRespDTO;
import cn.baltics.shop.dto.resp.ShopReviewRespDTO;
import cn.baltics.shop.service.ShopService;
import cn.baltics.springboot.starter.convention.result.Results;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

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
    public Results<ShopReviewRespDTO> getPartialReview(@RequestBody ShopReviewReqDTO requestParam) {
        ShopReviewRespDTO result = shopService.getPartialReview(requestParam);
        return Results.success(result);
    }
}
