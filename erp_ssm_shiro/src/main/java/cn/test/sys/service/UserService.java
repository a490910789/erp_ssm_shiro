package cn.test.sys.service;

import java.util.List;

import cn.test.sys.domain.User;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.vo.UserVo;

public interface UserService {
    User queryUserByLoginname(String loginname);

	int delUsers(UserVo userVo);

	int addUser(UserVo userVo);

	User queryUserById(Integer id);

	int delUser(UserVo userVo);

	int updateUser(UserVo userVo);

	DataGridView queryAllUser(UserVo userVo);

	List<User> queryUsersByDeptId(UserVo userVo);

	int resetPwd(UserVo userVo);

	int changePwd(UserVo userVo);
	
}
