package cn.test.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.test.sys.constast.SYS_Constast;
import cn.test.sys.domain.LeaveBill;
import cn.test.sys.domain.User;
import cn.test.sys.service.LeaveBillService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.utils.SessionUtils;
import cn.test.sys.vo.LeaveBillVo;

@Controller
@RequestMapping("leaveBill")
public class LeaveBillController {

	@Autowired
	private LeaveBillService leaveBillService;
    //跳转请假单管理
	@RequestMapping("toLeaveBillManager")
	public String toLeaveBillManager() {
		return "system/leaveBill/leaveBillManager";
	}
    //跳转添加页面
	@RequestMapping("toAddLeaveBill")
	public String toAddLeaveBill() {
		return "system/leaveBill/addLeaveBill";
	}
    //跳转修改页面
	@RequestMapping("toUpdateLeaveBill")
	public String toUpdateLeaveBill(LeaveBillVo leaveBillVo,Model model) {
		LeaveBill leaveBill=this.leaveBillService.queryLeaveBillById(leaveBillVo.getId());
		model.addAttribute("leaveBill", leaveBill);
		return "system/leaveBill/updateLeaveBill";
	}

	// 查询所有请假单 分页 模糊
	@RequestMapping("loadAllLeaveBill")
	@ResponseBody
	public DataGridView loadAllLeaveBill(LeaveBillVo leaveBillVo) {
		//如果用户是超级管理员 则查询全部请假单 否则根据当前登入用户ID查询请假单
		if(SessionUtils.getUserInSession("user").getType()==SYS_Constast.USER_TYPE_SUPER) {
			return this.leaveBillService.queryAllLeaveBills(leaveBillVo);
		}else {
			 User user = SessionUtils.getUserInSession("user");
			 leaveBillVo.setUserid(user.getId());
		     return this.leaveBillService.queryAllLeaveBills(leaveBillVo);
		}
	}
	
	// 删除单个请假单
	@RequestMapping("delLeaveBill")
	@ResponseBody
	public Map<String, Object> delLeaveBill(LeaveBillVo leaveBillVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.leaveBillService.delLeaveBill(leaveBillVo);
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

 

	// 添加请假单
	@RequestMapping("addLeaveBill")
	@ResponseBody
	public Map<String, Object> addLeaveBill(LeaveBillVo leaveBillVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.leaveBillService.addLeaveBill(leaveBillVo);
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

	// 修改请假单
	@RequestMapping("updateLeaveBill")
	@ResponseBody
	public Map<String, Object> updateLeaveBill(LeaveBillVo leaveBillVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.leaveBillService.updateLeaveBill(leaveBillVo);
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
}
