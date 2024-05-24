package cn.baltics.shop.service.handler.enums;

import com.fasterxml.jackson.annotation.JsonSubTypes;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/24 
 */
public enum ShopReviewStrategyEnum {
    PARTIAL_DEFAULT,
    PARTIAL_LATEST,
    PAGE_DEFAULT,
    PAGE_LATEST
}
