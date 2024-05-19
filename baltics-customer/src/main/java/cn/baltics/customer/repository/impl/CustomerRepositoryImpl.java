package cn.baltics.customer.repository.impl;

import cn.baltics.customer.dao.entity.CustomerDO;
import cn.baltics.customer.dao.mapper.CustomerRepositoryMapper;
import cn.baltics.customer.repository.CustomerRepository;
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
    public boolean registerCheckDuplication(String username, String mail) {
        List<CustomerDO> result = customerRepositoryMapper.checkUsernameAndMail(username, mail);
        return result != null && !result.isEmpty();
    }
}
