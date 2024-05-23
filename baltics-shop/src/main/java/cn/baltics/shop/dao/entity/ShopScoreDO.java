package cn.baltics.shop.dao.entity;

import lombok.Builder;
import lombok.Data;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Data
@Builder
public class ShopScoreDO {
    private long id;
    /**
     * 打分总人数
     */
    private long scores;
    /**
     * 五星制评分
     */
    private long fiveScoreCount;
    /**
     * 口味评分
     */
    private long tasteScoreCount;
    /**
     * 环境评分
     */
    private long environmentScoreCount;
    /**
     * 服务评分
     */
    private long serviceScoreCount;
}
