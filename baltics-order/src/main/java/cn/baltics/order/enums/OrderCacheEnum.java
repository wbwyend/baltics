package cn.baltics.order.enums;
/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/06/05 
 */
public enum OrderCacheEnum {
    ORDER_SUBMIT_CUSTOMER_SUBMIT_TIMES_COUNT("order:submit:customer:submit:times:count:");

    private final String value;

    OrderCacheEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
