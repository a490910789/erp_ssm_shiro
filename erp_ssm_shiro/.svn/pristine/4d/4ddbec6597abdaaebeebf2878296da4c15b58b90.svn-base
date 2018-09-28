package cn.test.sys.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.test.sys.constast.SYS_Constast;
import cn.test.sys.domain.User;
import cn.test.sys.mapper.UserMapper;
import cn.test.sys.service.UserService;
import cn.test.sys.utils.DataGridView;
import cn.test.sys.utils.Md5Uitls;
import cn.test.sys.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User queryUserByLoginname(String loginname) {
		return this.userMapper.queryUserByLoginname(loginname);
	}

	@Override
	public int delUsers(UserVo userVo) {
		return this.userMapper.delUsers(userVo.getIds());
	}

	@Override
	public int addUser(UserVo userVo) {
		return this.userMapper.insert(userVo);
	}

	@Override
	public User queryUserById(Integer id) {
		return this.userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delUser(UserVo userVo) {
		return this.userMapper.deleteByPrimaryKey(userVo.getId());
	}

	@Override
	public int updateUser(UserVo userVo) {
		return this.userMapper.updateByPrimaryKeySelective(userVo);
	}

	@Override
	public DataGridView queryAllUser(UserVo userVo) {
		Page<Object> page=PageHelper.startPage(userVo.getPage(), userVo.getLimit());
		List<User> data=this.userMapper.queryAllUserForList(userVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public List<User> queryUsersByDeptId(UserVo userVo) {
		return this.userMapper.queryUsersByDeptId(userVo);
	}

	@Override
	public int resetPwd(UserVo userVo) {
		User user = this.userMapper.selectByPrimaryKey(userVo.getId());
		user.setPwd(Md5Uitls.encodePwdUseMd5(SYS_Constast.USER_DEFAULT_PWD, user.getUuid(), 2));
		return this.userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int changePwd(UserVo userVo) {
		return this.userMapper.updateByPrimaryKeySelective(userVo);
	}
}
