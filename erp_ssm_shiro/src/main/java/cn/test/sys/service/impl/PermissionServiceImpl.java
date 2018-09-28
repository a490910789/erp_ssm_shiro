package cn.test.sys.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.test.sys.domain.Permission;
import cn.test.sys.mapper.PermissionMapper;
import cn.test.sys.service.PermissionService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.vo.PermissionVo;
@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public DataGridView queryAllPermission(PermissionVo permissionVo) {
		Page<Object> page = PageHelper.startPage(permissionVo.getPage(), permissionVo.getLimit());
		List<Permission> data = this.permissionMapper.queryAllPermission(permissionVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public int delPermission(PermissionVo permissionVo) {
		return this.permissionMapper.deleteByPrimaryKey(permissionVo.getId());
	}

	@Override
	public List<Permission> queryAllPermissionForList(PermissionVo permissionVo) {
		return this.permissionMapper.queryAllPermission(permissionVo);
	}

	@Override
	public int addPermission(PermissionVo permissionVo) {
		return this.permissionMapper.insert(permissionVo);
	}

	@Override
	public int updatePermission(PermissionVo permissionVo) {
		return this.permissionMapper.updateByPrimaryKeySelective(permissionVo);
	}

	@Override
	public Permission queryPermissionById(Integer id) {
		return this.permissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delPermissions(PermissionVo permissionVo) {
		return this.permissionMapper.delPermissions(permissionVo.getIds());
	}

	@Override
	public List<Permission> queryAllMenuForList(PermissionVo permissionVo) {
		return this.permissionMapper.queryAllPermission(permissionVo);
	}

	@Override
	public List<Permission> queryPermissionsByUid(String type,Integer id) {
		return this.permissionMapper.queryPermissionsByUid(type,id);
	}
}
