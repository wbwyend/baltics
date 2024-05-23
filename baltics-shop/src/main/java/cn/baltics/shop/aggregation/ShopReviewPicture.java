package cn.baltics.shop.aggregation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopReviewPicture implements Serializable {
    private String id;
    private String url;
    private String reviewId;
    private int status;
}
