package cn.test.sys.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.test.sys.domain.User;

public class SessionUtils {
	// 获取Session
	public static HttpSession getSession() {
		// 1,当前这个对象没有被spring管理 如何得到 在这里面得到request
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		// 2. 通过request 得到serssion
		HttpSession session = request.getSession();
		return session;
	}

	// 从Session里获取当前用户对象
	public static User getUserInSession(String key) {
		return (User)getSession().getAttribute(key);
	}
	
	// 从Session里获取当前用户名称
	public static String getUserNameInSession(String key) {
		return ((User)getUserInSession(key)).getName();
	}
}
