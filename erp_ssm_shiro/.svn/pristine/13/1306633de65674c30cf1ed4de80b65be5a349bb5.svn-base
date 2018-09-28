package cn.test.sys.listener;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.test.sys.domain.User;
import cn.test.sys.service.UserService;

public class LeaveBillTaskListener implements TaskListener {
     
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("null")
	@Override
	public void notify(DelegateTask delegateTask) {
	// 1,当前这个对象没有被spring管理 如何得到 在这里面得到request
	ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	HttpServletRequest request = servletRequestAttributes.getRequest();
	//2. 通过request 得到serssion
	HttpSession session = request.getSession();
	//3.得到当前user
	User user=(User) session.getAttribute("user");
	//4.得到UserService
	WebApplicationContext webApplicationContext=WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
	UserService userService = webApplicationContext.getBean(UserService.class);
	//5.根据user的MGR属性查询对应领导信息
	User leaderUser = userService.queryUserById(user.getMgr());
	//6.设置上级领导办理人
	delegateTask.setAssignee(leaderUser.getName());
	}
}
