package cn.test.bus.mapper;

import java.util.List;

import cn.test.bus.domain.Customer;
import cn.test.bus.vo.CustomerVo;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

	List<Customer> queryAllCustomer(CustomerVo customerVo);

	int delCustomers(Integer[] ids);
}