package cn.baltics.shop.aggregation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *@func 商店评分
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopScore implements Serializable {
    /**
     * 五星制评分
     */
    private String fiveScore;
    /**
     * 口味评分
     */
    private String tasteScore;
    /**
     * 环境评分
     */
    private String environmentScore;
    /**
     * 服务评分
     */
    private String serviceScore;
}
