package cn.test.bus.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.test.bus.domain.Goods;
import cn.test.bus.domain.Provider;
import cn.test.bus.service.GoodsService;
import cn.test.bus.service.ProviderService;
import cn.test.bus.vo.GoodsVo;
import cn.test.bus.vo.ProviderVo;
import cn.test.sys.constast.SYS_Constast;
import cn.test.sys.utils.DataGridView;

@Controller
@RequestMapping("goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ProviderService providerService;

	// 跳转商品管理
	@RequestMapping("toGoodsManager")
	public String toGoodsManager() {
		return "business/goods/goodsManager";
	}

	// 跳转添加页面
	@RequestMapping("toAddGoods")
	public String toAddGoods(Model model) {
		List<Provider> providers = this.providerService.queryAllProviderForList(new ProviderVo());
	    model.addAttribute("providers", providers);
		return "business/goods/addGoods";
	}

	// 跳转LEFT
	@RequestMapping("toGoodsLeft")
	public String toGoodsLeft() {
		return "business/goods/goodsLeft";
	}

	// 跳转RIGHT
	@RequestMapping("toGoodsRight")
	public String toGoodsRight() {
		return "business/goods/goodsRight";
	}

	// 跳转修改页面
	@RequestMapping("toUpdateGoods")
	public String toUpdateGoods(GoodsVo goodsVo, Model model) {
		Goods goods = this.goodsService.queryGoodsById(goodsVo.getId());
		model.addAttribute("goods", goods);
		return "business/goods/updateGoods";
	}
    
	// 查询所有商品 分页 模糊
	@RequestMapping("loadAllGoods")
	@ResponseBody
	public DataGridView loadAllGoods(GoodsVo goodsVo) {
		return this.goodsService.queryAllGoods(goodsVo);
	}
	
	// 根据供应商providerid查询商品
	@RequestMapping("queryGoodsByProviderid")
	@ResponseBody
	public List<Goods> queryGoodsByProviderid(GoodsVo goodsVo) {
		if(goodsVo.getProviderid()==SYS_Constast.TYPE_PUBLIC_ZERO) {
			goodsVo.setProviderid(null);
		}
		return this.goodsService.queryGoodsByProviderid(goodsVo);
	}
	//全查询
	@RequestMapping("loadTreeForGoods")
	@ResponseBody
	public DataGridView loadTreeForGoods(GoodsVo goodsVo) {
		return this.goodsService.queryAllGoods(goodsVo);
	}

	// 删除单个商品
	@RequestMapping("delGoods")
	@ResponseBody
	public Map<String, Object> delGoods(GoodsVo goodsVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.goodsService.delGoods(goodsVo);
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

	// 添加商品
	@RequestMapping("addGoods")
	@ResponseBody
	public Map<String, Object> addGoods(GoodsVo goodsVo,HttpSession session) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.goodsService.addGoods(goodsVo);
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

	// 修改商品
	@RequestMapping("updateGoods")
	@ResponseBody
	public Map<String, Object> updateGoods(GoodsVo goodsVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.goodsService.updateGoods(goodsVo);
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
	@RequestMapping("delGoodses")
	@ResponseBody
	public Map<String, Object> delGoodses(GoodsVo goodsVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.goodsService.delGoodses(goodsVo);
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
