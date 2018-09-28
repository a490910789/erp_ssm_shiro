package cn.test.sys.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
	
/**
 * 加密工具
 * @author LJH
 *
 */
public class Md5Uitls {

	/**
	 * 加密密码
	 * @param pwd 明文密码
	 * @param salt 盐
	 * @param hashIterations 散列次数
	 */
	public static String encodePwdUseMd5(String pwd,String salt,int hashIterations) {
		return new Md5Hash(pwd, salt, hashIterations).toString();
	}
	public static void main(String[] args) {
		String encodePwdUseMd5 = encodePwdUseMd5("123456", "23b31805a3bf4a2ba6515a50c6351b08", 2);
		System.out.println(encodePwdUseMd5);
	}
	
}
