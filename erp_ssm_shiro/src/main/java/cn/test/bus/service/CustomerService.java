package cn.test.bus.service;

import java.util.List;

import cn.test.bus.domain.Customer;
import cn.test.bus.vo.CustomerVo;
import cn.test.sys.utils.DataGridView;

public interface CustomerService {
	public DataGridView queryAllCustomer(CustomerVo customerVo);

	public int delCustomer(CustomerVo customerVo);

	public List<Customer> queryAllCustomerForList(CustomerVo customerVo);

	public int addCustomer(CustomerVo customerVo);

	public int updateCustomer(CustomerVo customerVo);

	public Customer queryCustomerById(Integer id);

	public int delCustomers(CustomerVo customerVo);
}
