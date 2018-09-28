package cn.test.bus.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.test.bus.domain.Customer;
import cn.test.bus.mapper.CustomerMapper;
import cn.test.bus.service.CustomerService;
import cn.test.bus.vo.CustomerVo;
import cn.test.sys.utils.DataGridView;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public DataGridView queryAllCustomer(CustomerVo customerVo) {
		Page<Object> page = PageHelper.startPage(customerVo.getPage(), customerVo.getLimit());
		List<Customer> data = this.customerMapper.queryAllCustomer(customerVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public int delCustomer(CustomerVo customerVo) {
		return this.customerMapper.deleteByPrimaryKey(customerVo.getId());
	}

	@Override
	public List<Customer> queryAllCustomerForList(CustomerVo customerVo) {
		return this.customerMapper.queryAllCustomer(customerVo);
	}

	@Override
	public int addCustomer(CustomerVo customerVo) {
		return this.customerMapper.insert(customerVo);
	}

	@Override
	public int updateCustomer(CustomerVo customerVo) {
		return this.customerMapper.updateByPrimaryKey(customerVo);
	}

	@Override
	public Customer queryCustomerById(Integer id) {
		return this.customerMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delCustomers(CustomerVo customerVo) {
		return this.customerMapper.delCustomers(customerVo.getIds());
	}
}
