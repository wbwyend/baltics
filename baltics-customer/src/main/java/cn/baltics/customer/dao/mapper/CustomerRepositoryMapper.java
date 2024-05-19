package cn.baltics.customer.dao.mapper;

import cn.baltics.customer.dao.entity.CustomerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *@name CustomerRepositoryMapper
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
@Mapper
public interface CustomerRepositoryMapper {
    @Select("select * from customer where username = #{username} union select * from customer where mail = #{mail}")
    List<CustomerDO> checkUsernameAndMail(String username, String mail);
}
