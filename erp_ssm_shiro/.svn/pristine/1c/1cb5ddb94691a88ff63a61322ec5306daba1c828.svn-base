package cn.test.sys.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.test.sys.constast.SYS_Constast;
import cn.test.sys.domain.User;
import cn.test.sys.service.RoleService;
import cn.test.sys.service.UserService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.utils.Md5Uitls;
import cn.test.sys.vo.UserVo;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@RequestMapping("toUserManager")
	public String toUserManager() {
		return "system/user/userManager";
	}

	@RequestMapping("toUserLeft")
	public String toUserLeft() {
		return "system/user/userLeft";
	}

	@RequestMapping("toUserRight")
	public String toUserRight() {
		return "system/user/userRight";
	}

	@RequestMapping("toAddUser")
	public String toAddUser() {
		return "system/user/addUser";
	}

	@RequestMapping("toSetRoles")
	public String toSetRoles(UserVo userVo) {
		return "system/user/setRoles";
	}

	@RequestMapping("toChangePwd")
	public String toChangePwd(UserVo userVo) {
		return "system/user/changePwd";
	}

	@RequestMapping("loadAllRoles")
	@ResponseBody
	public DataGridView loadAllRoles(UserVo userVo) {
		return this.roleService.loadAllRoles(userVo);
	}

	@RequestMapping("toUpdateUser")
	public String toUpdateUser(UserVo userVo, Model model) {
		User user = this.userService.queryUserById(userVo.getId());
		if (null == user.getMgr()) {
			user.setMgr(SYS_Constast.TYPE_PUBLIC_ZERO);
		}
		model.addAttribute("user", user);
		return "system/user/updateUser";
	}

	// 根据用户MRG查询用户领导信息
	@RequestMapping("queryLeaderById")
	@ResponseBody
	public User queryLeaderById(UserVo userVo) {
		return this.userService.queryUserById(userVo.getId());
	}

	// 查询所有用户 分页 模糊
	@RequestMapping("loadAllUser")
	@ResponseBody
	public DataGridView loadAllUser(UserVo userVo) {
		return this.userService.queryAllUser(userVo);
	}

	// 根据部门ID查询该部门下的所有用户
	@RequestMapping("queryUsersByDeptId")
	@ResponseBody
	public List<User> queryUsersByDeptId(UserVo userVo) {
		userVo.setAvailable(SYS_Constast.TYPE_AVAILABLE_YES);
		return this.userService.queryUsersByDeptId(userVo);
	}

	// 删除单个用户
	@RequestMapping("delUser")
	@ResponseBody
	public Map<String, Object> delUser(UserVo userVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.userService.delUser(userVo);
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

	// 分配权限
	@RequestMapping("saveSetRoles")
	@ResponseBody
	public Map<String, Object> saveSetRoles(UserVo userVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		if (null == userVo.getIds()) {
			this.roleService.deleteUserRoleKeyByUid(userVo.getId());
			msg = "分配成功";
			map.put("msg", msg);
			return map;
		}
		try {
			int i = this.roleService.saveSetRoles(userVo);
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

	// 重置密码
	@RequestMapping("resetPwd")
	@ResponseBody
	public Map<String, Object> resetPwd(UserVo userVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.userService.resetPwd(userVo);
			if (i > 0) {
				msg = "重置成功";
			} else {
				msg = "重置失败";
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "分配失败";
		}
		map.put("msg", msg);
		return map;
	}

	// 添加用户
	@RequestMapping("addUser")
	@ResponseBody
	public Map<String, Object> addUser(UserVo userVo) {
		String msg = "";
		userVo.setImgpath(SYS_Constast.USER_DEFAULT_TITLE);
		userVo.setType(SYS_Constast.USER_TYPE_NORMAL);
		userVo.setPwd(
				Md5Uitls.encodePwdUseMd5(SYS_Constast.USER_DEFAULT_PWD, userVo.getName() + userVo.getAddress(), 2));
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.userService.addUser(userVo);
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

	// 修改用户
	@RequestMapping("updateUser")
	@ResponseBody
	public Map<String, Object> updateUser(UserVo userVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.userService.updateUser(userVo);
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
	@RequestMapping("delUsers")
	@ResponseBody
	public Map<String, Object> delUsers(UserVo userVo) {
		String msg = "";
		Map<String, Object> map = new HashMap<>();
		try {
			int i = this.userService.delUsers(userVo);
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
	 
	// 修改密码
	@RequestMapping("changePwd")
	@ResponseBody
	public Boolean changePwd(UserVo userVo, HttpSession session) {
		Boolean flag = false;
		User user = (User) session.getAttribute("user");
		// 加密后的原密码
		String pwd = user.getPwd();
		// 用户从前台输入的密码 没加密
		String inputPwd = userVo.getPwd();
		String encodePwdUseMd5 = Md5Uitls.encodePwdUseMd5(inputPwd, user.getUuid(), 2);
		if (pwd.equals(encodePwdUseMd5)) {
			userVo.setId(user.getId());
			userVo.setPwd(Md5Uitls.encodePwdUseMd5(userVo.getNewPwd(), user.getUuid(), 2));
			int i = this.userService.changePwd(userVo);
			if (i > 0) {
				flag = true;
				return flag;
			}
		} 
			return flag;
	}
}
