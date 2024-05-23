package cn.baltics.shop.repository.impl;

import cn.baltics.shop.aggregation.ShopScore;
import cn.baltics.shop.dao.entity.ShopScoreDO;
import cn.baltics.shop.dao.mapper.ShopScoreRepositoryMapper;
import cn.baltics.shop.repository.ShopScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Repository
@AllArgsConstructor
public class ShopScoreRepositoryImpl implements ShopScoreRepository {
    private ShopScoreRepositoryMapper shopScoreRepositoryMapper;
    @Override
    public ShopScore getShopScore(Integer id) {
        ShopScoreDO result = shopScoreRepositoryMapper.get(id);
        return ShopScore.builder()
                .fiveScore(conventScore(result.getFiveScoreCount(), result.getScores()))
                .tasteScore(conventScore(result.getTasteScoreCount(), result.getScores()))
                .environmentScore(conventScore(result.getEnvironmentScoreCount(), result.getScores()))
                .serviceScore(conventScore(result.getServiceScoreCount(), result.getScores()))
                .build();
    }

    /**
     * 转换总评分为具体评分
     * @param count 评分总和
     * @param num 评分次数
     * @return {@link ShopScore} 评分项
     */
    private String conventScore(long count, long num) {
        return String.valueOf((double) count / num).substring(0, 3);
    }
}
