package cn.baltics.customer.repository;
/**
 *@name CustomerRepository
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
public interface CustomerRepository {
    /**
     * 检查用户名和邮箱是否重复
     * @param username 用户名
     * @param mail 邮箱
     * @return 重复结果
     */
    boolean registerCheckDuplication(String username, String mail);
}
