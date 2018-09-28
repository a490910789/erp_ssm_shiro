package cn.test.sys.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.test.sys.constast.SYS_Constast;
import cn.test.sys.domain.Permission;
import cn.test.sys.domain.Role;
import cn.test.sys.domain.User;
import cn.test.sys.service.PermissionService;
import cn.test.sys.service.RoleService;
import cn.test.sys.service.UserService;
import cn.test.sys.utils.ActiverUser;

/**
 * shrio的自定义realm
 * 
 * @author LJH
 *
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
 
	/**
	 * 认证
	 */
	@SuppressWarnings("unused")
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1,得到身份
		String loginname = token.getPrincipal().toString();
		// 2,根据身份查询用户信息
		User user = this.userService.queryUserByLoginname(loginname);
		if (null != user) {
			String dbPassword = user.getPwd();// 数据库加密的密码
			ActiverUser activerUser = new ActiverUser();
			activerUser.setCurrentUser(user);
			//根据用户ID查询对应菜单和权限
			List<Role> myRoles = this.roleService.queryRolesByUserId(user.getId());
			List<String> roles = activerUser.getRoles();
			for (Role r : myRoles) {
				roles.add(r.getName());
			}
			List<String> permissions = activerUser.getPermissions();
			List<Permission> perimissions=this.permissionService.queryPermissionsByUid(SYS_Constast.PERMISSION_TYPE_PERMISSION,user.getId());
			for (Permission p : perimissions) {
				permissions.add(p.getPercode());
			}
			activerUser.setRoles(roles);
			activerUser.setPermissions(permissions);
			// 盐
			ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUuid());
			AuthenticationInfo info = new SimpleAuthenticationInfo(activerUser, dbPassword, credentialsSalt, getName());
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
		 ActiverUser activerUser = (ActiverUser) collection.getPrimaryPrincipal();
		 SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		 if(null!=activerUser.getRoles()&&activerUser.getRoles().size()>0) {
			 info.addRoles(activerUser.getRoles());
		 }
		 if(null!=activerUser.getPermissions() && activerUser.getPermissions().size()>0) {
			 info.addStringPermissions(activerUser.getPermissions());
		 }
		return info; 
	}
}
