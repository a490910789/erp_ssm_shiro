package cn.test.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.test.sys.constast.SYS_Constast;
import cn.test.sys.domain.Permission;
import cn.test.sys.domain.Role;
import cn.test.sys.mapper.PermissionMapper;
import cn.test.sys.mapper.RoleMapper;
import cn.test.sys.service.RoleService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.vo.RoleVo;
import cn.test.sys.vo.UserVo;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public DataGridView queryAllRole(RoleVo roleVo) {
		Page<Object> page = PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());
		List<Role> data = this.roleMapper.queryAllRole(roleVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public int delRole(RoleVo roleVo) {
		return this.roleMapper.deleteByPrimaryKey(roleVo.getId());
	}

	@Override
	public List<Role> queryAllRoleForList(RoleVo roleVo) {
		return this.roleMapper.queryAllRole(roleVo);
	}

	@Override
	public int addRole(RoleVo roleVo) {
		return this.roleMapper.insert(roleVo);
	}

	@Override
	public int updateRole(RoleVo roleVo) {
		return this.roleMapper.updateByPrimaryKeySelective(roleVo);
	}

	@Override
	public Role queryRoleById(Integer id) {
		return this.roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delRoles(RoleVo roleVo) {
		return this.roleMapper.delRoles(roleVo.getIds());
	}

	@Override
	public List<Role> queryAllMenuForList(RoleVo roleVo) {
		return this.roleMapper.queryAllRole(roleVo);
	}

	@Override
	public List<Permission> queryPermissionsByRid(Integer available,Integer id) {
		return this.permissionMapper.queryPermissionsByRid(available,id);
	}

	@Override
	public int saveSetPermissions(RoleVo roleVo) {
		Integer roleid=roleVo.getId();
		Integer[] ids=roleVo.getIds();
		if(null!=ids && ids.length>0) {
			this.roleMapper.deleteRolePermissionKeyByRid(roleid);
			for (Integer pid : ids) {
				this.roleMapper.saveSetPermissions(roleid,pid);
			}
		}else {
		    this.roleMapper.deleteRolePermissionKeyByRid(roleid);
		}
		return SYS_Constast.TYPE_PUBLIC_ONE;
	}

	@Override
	public List<Role> queryRolesByUserId(Integer id) {
		return this.roleMapper.queryRolesByUserId(id);
	}

	@Override
	public DataGridView loadAllRoles(UserVo userVo) {
		//查询用户拥有的角色
		List<Role> Myroles=this.roleMapper.queryRolesByUserId(userVo.getId());
		//查询所有角色
		List<Role> allRoles = this.queryAllRoleForList(new RoleVo());
		for (Role r1 : allRoles) {
			Boolean LAY_CHECKED=false;
			for (Role r2 : Myroles) {
				if(r1.getId()==r2.getId()) {
					LAY_CHECKED=true;
				}
			}
			r1.setLAY_CHECKED(LAY_CHECKED);
		}
		Page<Object> page=PageHelper.startPage(userVo.getPage(), userVo.getLimit());
		return new DataGridView(page.getTotal(), allRoles);
	}

	@Override
	public int saveSetRoles(UserVo userVo) {
		    Integer uid = userVo.getId();
			this.deleteUserRoleKeyByUid(userVo.getId());
			Integer[] ids = userVo.getIds();
			for (Integer rid : ids) {
			this.roleMapper.saveSetRoles(rid, uid);
			}
		  return SYS_Constast.TYPE_PUBLIC_ONE;
	  }

	@Override
	public void deleteUserRoleKeyByUid(Integer id) {
		this.roleMapper.deleteUserRoleKeyByUid(id);
	}
}
