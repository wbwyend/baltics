package cn.baltics.shop.repository.impl;

import cn.baltics.shop.aggregation.ShopInfo;
import cn.baltics.shop.dao.entity.ShopInfoDO;
import cn.baltics.shop.dao.mapper.ShopInfoRepositoryMapper;
import cn.baltics.shop.repository.ShopInfoRepository;
import cn.hutool.core.bean.BeanUtil;
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
public class ShopInfoRepositoryImpl implements ShopInfoRepository {
    private ShopInfoRepositoryMapper shopInfoRepositoryMapper;

    @Override
    public ShopInfo getShopInfo(Integer id) {
        ShopInfoDO result = shopInfoRepositoryMapper.get(id);
        return BeanUtil.copyProperties(result, ShopInfo.class);
    }
}
