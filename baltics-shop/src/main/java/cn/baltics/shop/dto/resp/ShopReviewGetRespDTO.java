package cn.baltics.shop.dto.resp;

import cn.baltics.shop.aggregation.ShopReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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
public class ShopReviewGetRespDTO implements Serializable {
    List<ShopReview> shopReviewList;
}
