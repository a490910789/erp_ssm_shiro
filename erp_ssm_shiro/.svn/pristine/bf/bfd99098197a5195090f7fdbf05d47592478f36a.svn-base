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
import cn.test.sys.domain.Role;
import cn.test.sys.service.PermissionService;
import cn.test.sys.service.RoleService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.utils.TreeNode;
import cn.test.sys.vo.PermissionVo;
import cn.test.sys.vo.RoleVo;

@Controller
@RequestMapping("role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	@RequestMapping("toRoleManager")
	public String toRoleManager() {
		return "system/role/roleManager";
	}

	@RequestMapping("toRoleLeft")
	public String toRoleLeft() {
		return "system/role/roleLeft";
	}

	@RequestMapping("toRoleRight")
	public String toRoleRight() {
		return "system/role/roleRight";
	}

	@RequestMapping("toAddRole")
	public String toAddRole() {
		return "system/role/addRole";
	}

	@RequestMapping("toUpdateRole")
	public String toUpdateRole(RoleVo roleVo,Model model) {
		Role role=this.roleService.queryRoleById(roleVo.getId());
		model.addAttribute("role", role);
		return "system/role/updateRole";
	}
	
	@RequestMapping("toSetPermissions")
	public String toSetPermissions(RoleVo roleVo) {
		return "system/role/setPermissions";
	}
	//分配权限 回选权限
	@RequestMapping("setPermissions")
	@ResponseBody
	public List<TreeNode> setPermissions(RoleVo roleVo) {
		PermissionVo permissionVo=new PermissionVo();
		permissionVo.setAvailable(SYS_Constast.TYPE_AVAILABLE_YES);
		List<Permission> myPermissions = this.roleService.queryPermissionsByRid(roleVo.getId(),permissionVo.getAvailable());
		List<Permission> allPermissions = this.permissionService.queryAllPermissionForList(permissionVo);
		List<TreeNode> treeNodes=new ArrayList<>();
		for (Permission p1 : allPermissions) {
			Boolean checked=false;
			for (Permission p2 : myPermissions) {
				if(p1.getId()==p2.getId()) {
					checked=true;
					break;
				}
			}
			Boolean isParent=false;
			if(p1.getType().equals(SYS_Constast.PERMISSION_TYPE_MENU)) {
			  isParent=true;
			}
			Boolean open=p1.getSpread()==SYS_Constast.TYPE_PUBLIC_ONE?true:false;
			treeNodes.add(new TreeNode(p1.getId(), p1.getPid(), p1.getName(), open, isParent, checked));
		}
		return treeNodes;
	}
	
	// 添加菜单
		@RequestMapping("saveSetPermissions")
		@ResponseBody
		public Map<String, Object> saveSetPermissions(RoleVo roleVo) {
			String msg = "";
			Map<String, Object> map = new HashMap<>();
			try {
				int i = this.roleService.saveSetPermissions(roleVo);
				if (i > 0) {
					msg = "分配成功";
				} else {
					msg = "分配失败";
				}

			} catch (Exception e) {
				e.printStackTrace();
				msg = "分配失败";
			}
			map.put("msg", msg);
			return map;
		}
   
	// 查询所有菜单 分页 模糊
	@RequestMapping("loadAllRole")
	@ResponseBody
	public DataGridView loadAllRole(RoleVo roleVo) {
		roleVo.setAvailable(SYS_Constast.TYPE_AVAILABLE_YES);
		return this.roleService.queryAllRole(roleVo);
	}

	// 删除单个菜单
	@RequestMapping("delRole")
	@ResponseBody
	public Map<String, Object> delRole(RoleVo roleVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.roleService.delRole(roleVo);
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
	@RequestMapping("addRole")
	@ResponseBody
	public Map<String, Object> addRole(RoleVo roleVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.roleService.addRole(roleVo);
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
	@RequestMapping("updateRole")
	@ResponseBody
	public Map<String, Object> updateRole(RoleVo roleVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.roleService.updateRole(roleVo);
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
		@RequestMapping("delRoles")
		@ResponseBody
		public Map<String, Object> delRoles(RoleVo roleVo) {
			String msg = "";
			Map<String, Object> map = new HashMap<>();
			try {
				int i = this.roleService.delRoles(roleVo);
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
