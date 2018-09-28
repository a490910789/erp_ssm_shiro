package cn.test.bus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.test.bus.domain.Provider;
import cn.test.bus.service.ProviderService;
import cn.test.bus.vo.ProviderVo;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.utils.TreeNode;


@Controller
@RequestMapping("provider")
public class ProviderController {

	@Autowired
	private ProviderService providerService;
    //跳转供应商管理
	@RequestMapping("toProviderManager")
	public String toProviderManager() {
		return "business/provider/providerManager";
	}
    //跳转添加页面
	@RequestMapping("toAddProvider")
	public String toAddProvider() {
		return "business/provider/addProvider";
	}
    //跳转修改页面
	@RequestMapping("toUpdateProvider")
	public String toUpdateProvider(ProviderVo providerVo,Model model) {
		Provider provider=this.providerService.queryProviderById(providerVo.getId());
		model.addAttribute("provider", provider);
		return "business/provider/updateProvider";
	}
	
	@RequestMapping("loadTreeForProvider")
	@ResponseBody
	public List<TreeNode> loadTreeForProvider(ProviderVo providerVo) {
		List<Provider> providers = this.providerService.queryAllProviderForList(providerVo);
		List<TreeNode> treeNodes=new ArrayList<>();
	    treeNodes.add(new TreeNode(0, 0, "所有供应商", true, true));
		for (Provider p : providers) {
			treeNodes.add(new TreeNode(p.getId(), 0, p.getProvidername(), false, false));
		}
		return treeNodes;
	}
	
	// 查询所有供应商 分页 模糊
	@RequestMapping("loadAllProvider")
	@ResponseBody
	public DataGridView loadAllProvider(ProviderVo providerVo) {
		return this.providerService.queryAllProvider(providerVo);
	}
	
	// 删除单个供应商
	@RequestMapping("delProvider")
	@ResponseBody
	public Map<String, Object> delProvider(ProviderVo providerVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.providerService.delProvider(providerVo);
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

 

	// 添加供应商
	@RequestMapping("addProvider")
	@ResponseBody
	public Map<String, Object> addProvider(ProviderVo providerVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.providerService.addProvider(providerVo);
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

	// 修改供应商
	@RequestMapping("updateProvider")
	@ResponseBody
	public Map<String, Object> updateProvider(ProviderVo providerVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.providerService.updateProvider(providerVo);
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
		@RequestMapping("delProviders")
		@ResponseBody
		public Map<String, Object> delProviders(ProviderVo providerVo) {
			String msg = "";
			Map<String, Object> map = new HashMap<>();
			try {
				int i = this.providerService.delProviders(providerVo);
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
