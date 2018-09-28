package cn.test.sys.service;

import java.util.List;

import cn.test.sys.domain.Permission;
import cn.test.sys.domain.Role;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.vo.RoleVo;
import cn.test.sys.vo.UserVo;

public interface RoleService {
	public DataGridView queryAllRole(RoleVo roleVo);

	public int delRole(RoleVo roleVo);

	public List<Role> queryAllRoleForList(RoleVo roleVo);

	public int addRole(RoleVo roleVo);

	public int updateRole(RoleVo roleVo);

	public Role queryRoleById(Integer id);

	public int delRoles(RoleVo roleVo);

	public List<Role> queryAllMenuForList(RoleVo roleVo);

	public List<Permission> queryPermissionsByRid(Integer available, Integer id);

	public int saveSetPermissions(RoleVo roleVo);

	public List<Role> queryRolesByUserId(Integer id);

	public DataGridView loadAllRoles(UserVo userVo);

	public int saveSetRoles(UserVo userVo);

	public void deleteUserRoleKeyByUid(Integer id);

}
