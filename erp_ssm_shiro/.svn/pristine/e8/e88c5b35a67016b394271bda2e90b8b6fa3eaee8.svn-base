package cn.test.sys.mapper;

import java.util.List;

import cn.test.sys.domain.Permission;
import cn.test.sys.vo.PermissionVo;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

	List<Permission> queryAllPermission(PermissionVo permissionVo);

	int delPermissions(Integer[] ids);
	
	List<Permission> queryPermissionsByRid(Integer id,Integer available);

	List<Permission> queryPermissionsByUid(String type, Integer id);
}