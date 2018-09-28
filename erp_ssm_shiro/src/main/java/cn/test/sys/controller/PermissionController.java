package cn.test.sys.controller;



import java.util.HashMap;
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
import cn.test.sys.vo.PermissionVo;

@Controller
@RequestMapping("permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@RequestMapping("toPermissionManager")
	public String toPermissionManager() {
		return "system/permission/permissionManager";
	}

	@RequestMapping("toPermissionLeft")
	public String toPermissionLeft() {
		return "system/permission/permissionLeft";
	}

	@RequestMapping("toPermissionRight")
	public String toPermissionRight() {
		return "system/permission/permissionRight";
	}

	@RequestMapping("toAddPermission")
	public String toAddPermission() {
		return "system/permission/addPermission";
	}

	@RequestMapping("toUpdatePermission")
	public String toUpdatePermission(PermissionVo permissionVo,Model model) {
		Permission permission=this.permissionService.queryPermissionById(permissionVo.getId());
		model.addAttribute("permission", permission);
		return "system/permission/updatePermission";
	}

	// 查询所有菜单 分页 模糊
	@RequestMapping("loadAllPermission")
	@ResponseBody
	public DataGridView loadAllPermission(PermissionVo permissionVo) {
		permissionVo.setType(SYS_Constast.PERMISSION_TYPE_PERMISSION);
		return this.permissionService.queryAllPermission(permissionVo);
	}

	// 删除单个菜单
	@RequestMapping("delPermission")
	@ResponseBody
	public Map<String, Object> delPermission(PermissionVo permissionVo) {
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

	// 添加菜单
	@RequestMapping("addPermission")
	@ResponseBody
	public Map<String, Object> addPermission(PermissionVo permissionVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			permissionVo.setType(SYS_Constast.PERMISSION_TYPE_PERMISSION);
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
	@RequestMapping("updatePermission")
	@ResponseBody
	public Map<String, Object> updatePermission(PermissionVo permissionVo) {
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
		@RequestMapping("delPermissions")
		@ResponseBody
		public Map<String, Object> delPermissions(PermissionVo permissionVo) {
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
