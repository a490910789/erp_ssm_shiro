package cn.test.sys.mapper;

import java.util.List;

import cn.test.sys.domain.User;
import cn.test.sys.vo.UserVo;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User queryUserByLoginname(String loginname);

	int delUsers(Integer[] ids);

	List<User> queryAllUserForList(UserVo userVo);

	List<User> queryUsersByDeptId(User user);

}