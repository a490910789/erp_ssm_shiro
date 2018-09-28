package cn.test.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.test.sys.constast.SYS_Constast;
import cn.test.sys.domain.Permission;
import cn.test.sys.domain.User;
import cn.test.sys.service.LogInfoService;
import cn.test.sys.service.PermissionService;
import cn.test.sys.utils.ActiverUser;
import cn.test.sys.utils.TreeNode;
import cn.test.sys.utils.TreeNodeBuilder;
import cn.test.sys.vo.LogInfoVo;
import cn.test.sys.vo.PermissionVo;
import cn.test.sys.vo.UserVo;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private LogInfoService logInfoService;
	 
	/**
	 * 跳转到用户登陆的页面
	 */
	@RequestMapping("toLogin")
	public String toLogin() {
		return "system/main/login";
	}
	
	/**
	 * 跳转到首页
	 * @return
	 */
	@RequestMapping("toDesk")
	public String toDesk() {
		return "system/main/desk";
	}
	/**
	 * 登陆
	 */
	@RequestMapping("login")
	public String login(UserVo userVo,HttpServletRequest request,Model model) {
		//1,得到shiro的主体
		Subject subject=SecurityUtils.getSubject();
		// 2.创建token用于认证 客户端传递过来的用户名和密码
		AuthenticationToken token = new UsernamePasswordToken(userVo.getLoginname(), userVo.getPwd());
		// 做认证
		try {
			subject.login(token);
			System.out.println("---------------------认证成功");
			HttpSession session = request.getSession();
			//取出ActiverUser
			ActiverUser activerUser =(ActiverUser) subject.getPrincipal();
			session.setAttribute("user", activerUser.getCurrentUser());
			//记录登陆日志
			User user = activerUser.getCurrentUser();
			String loginip = request.getRemoteAddr();
			this.logInfoService.addLogInfo(new LogInfoVo(user.getName()+user.getLoginname(),loginip, new Date()));
			return "system/main/index";
		} catch (UnknownAccountException e1) {
			model.addAttribute("error", "用户名不存在");
		} catch (IncorrectCredentialsException e2) {
			model.addAttribute("error", "密码不正确");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return "system/main/login";
	}
	
	@RequestMapping("loadLeftMenu")
	@ResponseBody
	public List<TreeNode> loadLeftMenu(PermissionVo permissionVo,HttpSession session){
		User user=(User) session.getAttribute("user");
		List<TreeNode> treeNodes=new ArrayList<>();
		List<Permission> permissions=null;
		if(user.getType()==SYS_Constast.USER_TYPE_SUPER) {
			permissionVo.setType(SYS_Constast.PERMISSION_TYPE_MENU);
			permissionVo.setAvailable(SYS_Constast.TYPE_PUBLIC_ONE);
			permissions = this.permissionService.queryAllMenuForList(permissionVo);
		}else {
			permissionVo.setAvailable(SYS_Constast.TYPE_PUBLIC_ONE);
			permissions = this.permissionService.queryPermissionsByUid(SYS_Constast.PERMISSION_TYPE_MENU,user.getId());
		}
		for (Permission permission : permissions) {
			Boolean spread=permission.getSpread()==SYS_Constast.TYPE_PUBLIC_ONE?true:false;
			treeNodes.add(new TreeNode(permission.getId(), permission.getPid(), permission.getName(), permission.getHref(), permission.getIcon(), spread, permission.getTarget()));
		}
		List<TreeNode> nodes = TreeNodeBuilder.builder(treeNodes, 1);
		return nodes;
	}
}
