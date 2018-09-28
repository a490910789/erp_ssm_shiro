package cn.test.bus.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.test.bus.domain.Customer;
import cn.test.bus.service.CustomerService;
import cn.test.bus.vo.CustomerVo;
import cn.test.sys.utils.DataGridView;

@Controller
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
    //跳转客户管理
	@RequestMapping("toCustomerManager")
	public String toCustomerManager() {
		return "business/customer/customerManager";
	}
    //跳转添加页面
	@RequestMapping("toAddCustomer")
	public String toAddCustomer() {
		return "business/customer/addCustomer";
	}
    //跳转修改页面
	@RequestMapping("toUpdateCustomer")
	public String toUpdateCustomer(CustomerVo customerVo,Model model) {
		Customer customer=this.customerService.queryCustomerById(customerVo.getId());
		model.addAttribute("customer", customer);
		return "business/customer/updateCustomer";
	}

	// 查询所有客户 分页 模糊
	@RequestMapping("loadAllCustomer")
	@ResponseBody
	public DataGridView loadAllCustomer(CustomerVo customerVo) {
		return this.customerService.queryAllCustomer(customerVo);
	}
	
	// 删除单个客户
	@RequestMapping("delCustomer")
	@ResponseBody
	public Map<String, Object> delCustomer(CustomerVo customerVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.customerService.delCustomer(customerVo);
			if (i > 0) {
				msg = "删除成功";
			} else {
				msg = "删除失败";
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
		}
		map.put("msg", msg);
		return map;
	}

 

	// 添加客户
	@RequestMapping("addCustomer")
	@ResponseBody
	public Map<String, Object> addCustomer(CustomerVo customerVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.customerService.addCustomer(customerVo);
			if (i > 0) {
				msg = "添加成功";
			} else {
				msg = "添加失败";
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		map.put("msg", msg);
		return map;
	}

	// 修改客户
	@RequestMapping("updateCustomer")
	@ResponseBody
	public Map<String, Object> updateCustomer(CustomerVo customerVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.customerService.updateCustomer(customerVo);
			if (i > 0) {
				msg = "修改成功";
			} else {
				msg = "修改失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败";
		}
		map.put("msg", msg);
		return map;
	}
	// 批量删除
		@RequestMapping("delCustomers")
		@ResponseBody
		public Map<String, Object> delCustomers(CustomerVo customerVo) {
			String msg = "";
			Map<String, Object> map = new HashMap<>();
			try {
				int i = this.customerService.delCustomers(customerVo);
				if (i > 0) {
					msg = "删除成功";
				} else {
					msg = "删除失败";
				}

			} catch (Exception e) {
				e.printStackTrace();
				msg = "删除失败";
			}
			map.put("msg", msg);
			return map;
		}
}
