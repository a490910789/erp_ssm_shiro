package cn.test.sys.controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.test.sys.constast.SYS_Constast;
import cn.test.sys.domain.Permission;
import cn.test.sys.service.PermissionService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.utils.TreeNode;
import cn.test.sys.vo.PermissionVo;

@Controller
@RequestMapping("menu")
public class MenuController {

	@Autowired
	private PermissionService permissionService;

	@RequestMapping("toMenuManager")
	public String toMenuManager() {
		return "system/menu/menuManager";
	}

	@RequestMapping("toMenuLeft")
	public String toMenuLeft() {
		return "system/menu/menuLeft";
	}

	@RequestMapping("toMenuRight")
	public String toMenuRight() {
		return "system/menu/menuRight";
	}

	@RequestMapping("toAddMenu")
	public String toAddMenu() {
		return "system/menu/addMenu";
	}

	@RequestMapping("toUpdateMenu")
	public String toUpdateMenu(PermissionVo permissionVo,Model model) {
		Permission menu=this.permissionService.queryPermissionById(permissionVo.getId());
		menu.setIcon(menu.getIcon().replace("&", "&amp;"));
		model.addAttribute("menu", menu);
		return "system/menu/updateMenu";
	}

	// 查询所有菜单 分页 模糊
	@RequestMapping("loadAllMenu")
	@ResponseBody
	public DataGridView loadAllMenu(PermissionVo permissionVo) {
		permissionVo.setType(SYS_Constast.PERMISSION_TYPE_MENU);
		return this.permissionService.queryAllPermission(permissionVo);
	}

	// 删除单个菜单
	@RequestMapping("delMenu")
	@ResponseBody
	public Map<String, Object> delMenu(PermissionVo permissionVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.permissionService.delPermission(permissionVo);
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

	// 菜单添加页面树
	@RequestMapping("loadTreeForMenu")
	@ResponseBody
	public List<TreeNode> loadTreeForMenu(PermissionVo permissionVo) {
		permissionVo.setType(SYS_Constast.PERMISSION_TYPE_MENU);
		List<Permission> list = this.permissionService.queryAllPermissionForList(permissionVo);
		List<TreeNode> treeNodes = new ArrayList<>();
		for (Permission permission : list) {
			Boolean open = permission.getSpread() == SYS_Constast.TYPE_PUBLIC_ONE ? true : false;
			Boolean isParent = permission.getParent() == SYS_Constast.TYPE_PUBLIC_ONE ? true : false;
			treeNodes.add(new TreeNode(permission.getId(), permission.getPid(), permission.getName(), open, isParent));
		}
		return treeNodes;
	}

	// 添加菜单
	@RequestMapping("addMenu")
	@ResponseBody
	public Map<String, Object> addMenu(PermissionVo permissionVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			permissionVo.setType(SYS_Constast.PERMISSION_TYPE_MENU);
			int i = this.permissionService.addPermission(permissionVo);
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

	// 修改菜单
	@RequestMapping("updateMenu")
	@ResponseBody
	public Map<String, Object> updateMenu(PermissionVo permissionVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.permissionService.updatePermission(permissionVo);
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
		@RequestMapping("delMenus")
		@ResponseBody
		public Map<String, Object> delMenus(PermissionVo permissionVo) {
			String msg = "";
			Map<String, Object> map = new HashMap<>();
			try {
				int i = this.permissionService.delPermissions(permissionVo);
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
