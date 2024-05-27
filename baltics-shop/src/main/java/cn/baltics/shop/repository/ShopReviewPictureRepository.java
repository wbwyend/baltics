package cn.baltics.shop.repository;

import cn.baltics.shop.aggregation.ShopReviewPicture;

import java.util.List;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/27 
 */
public interface ShopReviewPictureRepository {
    /**
     * 通过ID集合查询图片
     * @param reviewIds 评价ID
     * @return {@link ShopReviewPicture}
     */
    List<ShopReviewPicture> getByReviewIds(List<Long> reviewIds);

    /**
     * 添加评价图片
     * @param pictures 图片
     */
    void add(List<ShopReviewPicture> pictures);
}
