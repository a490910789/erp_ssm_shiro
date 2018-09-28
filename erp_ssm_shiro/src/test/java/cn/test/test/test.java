/*package cn.test.test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.test.sys.constast.SYS_Constast;
import cn.test.sys.domain.User;
import cn.test.sys.mapper.UserMapper;
import cn.test.sys.utils.Md5Uitls;
import cn.test.sys.utils.RandomUtils;
import cn.test.sys.vo.UserVo;

public class test {
	public static void main(String[] args2) throws IllegalAccessException, InvocationTargetException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		 UserMapper mapper = context.getBean(UserMapper.class);
		 List<User> list = mapper.queryAllUserForList(new UserVo());
		 User user=new User();
		 user.setId(1); 
		 String uuid=RandomUtils.createRandomUUID();
		 user.setPwd(Md5Uitls.encodePwdUseMd5(SYS_Constast.USER_DEFAULT_PWD, uuid, 2));
		 user.setUuid(uuid);
		 mapper.updateByPrimaryKeySelective(user);
		 
		 for (User user : list) {
			 String uuid=RandomUtils.createRandomUUID();
			 user.setPwd(Md5Uitls.encodePwdUseMd5(SYS_Constast.USER_DEFAULT_PWD, uuid, 2));
			 user.setUuid(uuid);
			 mapper.updateByPrimaryKey(user);
		}
	}
}
*/