package cn.test.bus.controller;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.test.bus.domain.Goods;
import cn.test.bus.domain.Inport;
import cn.test.bus.service.GoodsService;
import cn.test.bus.service.InportService;
import cn.test.bus.vo.InportVo;
import cn.test.sys.domain.User;
import cn.test.sys.utils.DataGridView;

@Controller
@RequestMapping("inport")
public class InportController {

	@Autowired
	private InportService inportService;
	@Autowired
	private GoodsService goodsService;

	// 跳转进货管理
	@RequestMapping("toInportManager")
	public String toInportManager() {
		return "business/inport/inportManager";
	}

	// 跳转添加页面
	@RequestMapping("toAddInport")
	public String toAddInport() {
		return "business/inport/addInport";
	}

	// 跳转LEFT
	@RequestMapping("toInportLeft")
	public String toInportLeft() {
		return "business/inport/inportLeft";
	}

	// 跳转RIGHT
	@RequestMapping("toInportRight")
	public String toInportRight() {
		return "business/inport/inportRight";
	}

	// 跳转修改页面
	@RequestMapping("toUpdateInport")
	public String toUpdateInport(InportVo inportVo, Model model,HttpSession session) {
		Inport inport = this.inportService.queryInportById(inportVo.getId());
		session.setAttribute("inport", inport);
		model.addAttribute("inport", inport);
		return "business/inport/updateInport";
	}

	// 查询所有进货 分页 模糊
	@RequestMapping("loadAllInport")
	@ResponseBody
	public DataGridView loadAllInport(InportVo inportVo) {
		return this.inportService.queryAllInport(inportVo);
	}

	@RequestMapping("loadTreeForInport")
	@ResponseBody
	public DataGridView loadTreeForInport(InportVo inportVo) {
		return this.inportService.queryAllInport(inportVo);
	}

	// 删除单个进货
	@RequestMapping("delInport")
	@ResponseBody
	public Map<String, Object> delInport(InportVo inportVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.inportService.delInport(inportVo);
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

	// 添加进货
	@RequestMapping("addInport")
	@ResponseBody
	public Map<String, Object> addInport(InportVo inportVo,HttpSession session) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			inportVo.setInporttime(sdf.format(new Date()));
			User user=(User)session.getAttribute("user");
			inportVo.setOperateperson(user.getName());
			int i = this.inportService.addInport(inportVo);
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

	// 修改进货
	@RequestMapping("updateInport")
	@ResponseBody
	public Map<String, Object> updateInport(InportVo inportVo,HttpSession session) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			Inport inport= (Inport)session.getAttribute("inport");
			Goods goods = this.goodsService.queryGoodsById(inportVo.getGoodsid());
			goods.setNumber(goods.getNumber()+(inportVo.getNumber()-inport.getNumber()));
			int i = this.inportService.updateInport(inportVo,goods);
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
	@RequestMapping("delInports")
	@ResponseBody
	public Map<String, Object> delInports(InportVo inportVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.inportService.delInports(inportVo);
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
