package cn.test.bus.controller;


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
import cn.test.bus.domain.Outport;
import cn.test.bus.service.GoodsService;
import cn.test.bus.service.OutportService;
import cn.test.bus.vo.OutportVo;
import cn.test.sys.domain.User;
import cn.test.sys.utils.DataGridView;

@Controller
@RequestMapping("outport")
public class OutportController {

	@Autowired
	private OutportService outportService;
	@Autowired
	private GoodsService goodsService;

	// 跳转进货管理
	@RequestMapping("toOutportManager")
	public String toOutportManager() {
		return "business/outport/outportManager";
	}

	// 跳转添加页面
	@RequestMapping("toAddOutport")
	public String toAddOutport() {
		return "business/outport/addOutport";
	}

	// 跳转LEFT
	@RequestMapping("toOutportLeft")
	public String toOutportLeft() {
		return "business/outport/outportLeft";
	}

	// 跳转RIGHT
	@RequestMapping("toOutportRight")
	public String toOutportRight() {
		return "business/outport/outportRight";
	}

	// 跳转修改页面
	@RequestMapping("toUpdateOutport")
	public String toUpdateOutport(OutportVo outportVo, Model model,HttpSession session) {
		Outport outport = this.outportService.queryOutportById(outportVo.getId());
		session.setAttribute("outport", outport);
		model.addAttribute("outport", outport);
		return "business/outport/updateOutport";
	}

	// 查询所有进货 分页 模糊
	@RequestMapping("loadAllOutport")
	@ResponseBody
	public DataGridView loadAllOutport(OutportVo outportVo) {
		return this.outportService.queryAllOutport(outportVo);
	}

	@RequestMapping("loadTreeForOutport")
	@ResponseBody
	public DataGridView loadTreeForOutport(OutportVo outportVo) {
		return this.outportService.queryAllOutport(outportVo);
	}

	// 删除单个进货
	@RequestMapping("delOutport")
	@ResponseBody
	public Map<String, Object> delOutport(OutportVo outportVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.outportService.delOutport(outportVo);
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
	@RequestMapping("addOutport")
	@ResponseBody
	public Map<String, Object> addOutport(OutportVo outportVo,HttpSession session) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			outportVo.setOutputtime(new Date());
			User user=(User)session.getAttribute("user");
			outportVo.setOperateperson(user.getName());
			int i = this.outportService.addOutport(outportVo);
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
	@RequestMapping("updateOutport")
	@ResponseBody
	public Map<String, Object> updateOutport(OutportVo outportVo,HttpSession session) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			Outport outport= (Outport)session.getAttribute("outport");
			Goods goods = this.goodsService.queryGoodsById(outportVo.getGoodsid());
			goods.setNumber(goods.getNumber()-(outportVo.getNumber()-outport.getNumber()));
			int i = this.outportService.updateOutport(outportVo,goods);
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
	@RequestMapping("delOutports")
	@ResponseBody
	public Map<String, Object> delOutports(OutportVo outportVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.outportService.delOutports(outportVo);
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
