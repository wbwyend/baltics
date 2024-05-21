package cn.baltics.customer.repository.impl;

import cn.baltics.customer.aggregation.Customer;
import cn.baltics.customer.dao.entity.CustomerDO;
import cn.baltics.customer.dao.mapper.CustomerRepositoryMapper;
import cn.baltics.customer.repository.CustomerRepository;
import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@name CustomerRepositoryImpl
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
@Repository
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    private CustomerRepositoryMapper customerRepositoryMapper;

    @Override
    public boolean registerCheckDuplication(Customer customer) {
        CustomerDO customerDO = BeanUtil.copyProperties(customer, CustomerDO.class);
        List<CustomerDO> result = customerRepositoryMapper.checkUsernameAndMail(customerDO);
        return result != null && !result.isEmpty();
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        CustomerDO customerDO = customerRepositoryMapper.getCustomerByUsername(username);
        return BeanUtil.copyProperties(customerDO, Customer.class);
    }

    @Override
    public void saveRegisterCustomer(Customer customer) {
        customerRepositoryMapper.insert(BeanUtil.copyProperties(customer, CustomerDO.class));
    }
}
