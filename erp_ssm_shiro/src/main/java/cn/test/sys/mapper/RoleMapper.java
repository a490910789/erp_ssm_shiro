package cn.test.sys.mapper;

import java.util.List;
import cn.test.sys.domain.Role;
import cn.test.sys.vo.RoleVo;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	List<Role> queryAllRole(RoleVo roleVo);

	int delRoles(Integer[] ids);

	void saveSetPermissions(Integer roleid, Integer pid);

	void deleteRolePermissionKeyByRid(Integer roleid);

	List<Role> queryRolesByUserId(Integer id);

	void deleteUserRoleKeyByUid(Integer id);

	void saveSetRoles(Integer rid, Integer uid);

}