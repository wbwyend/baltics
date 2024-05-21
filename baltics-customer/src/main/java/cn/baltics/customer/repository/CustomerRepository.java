package cn.baltics.customer.repository;

import cn.baltics.customer.aggregation.Customer;

/**
 *@name CustomerRepository
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
public interface CustomerRepository {
    /**
     * 检查用户名和邮箱是否重复
     * @param customer 用户聚合
     * @return 重复结果
     */
    boolean registerCheckDuplication(Customer customer);

    /**
     * 通过用户名获取用户聚合
     * @param username 用户名
     * @return 用户聚合
     */
    Customer getCustomerByUsername(String username);

    /**
     * 保存注册用户信息
     * @param customer 用户聚合
     */
    void saveRegisterCustomer(Customer customer);
}
