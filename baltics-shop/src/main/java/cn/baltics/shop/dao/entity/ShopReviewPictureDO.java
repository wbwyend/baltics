package cn.baltics.shop.dao.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/24 
 */
@Data
@Builder
public class ShopReviewPictureDO implements Serializable {
    private String id;
    private String url;
    private long reviewId;
    private int status;
}
