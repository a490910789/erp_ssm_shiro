package cn.test.sys.constast;

public interface SYS_Constast {

	/**
	 * 用户类型
	 */
	public static final Integer USER_TYPE_SUPER = 0;
	public static final Integer USER_TYPE_NORMAL = 1;

	/**
	 * 可用类型
	 */
	public static final Integer TYPE_AVAILABLE_YES = 1;
	public static final Integer TYPE_AVAILABLE_NO = 0;

	/**
	 * 公用类型
	 */
	public static final Integer TYPE_PUBLIC_ZERO = 0;
	public static final Integer TYPE_PUBLIC_ONE = 1;
	public static final Integer TYPE_PUBLIC_TOW = 2;
	
	/**
	 * 请假单状态
	 */
	public static final Integer TYPE_LEAVEBILL_STATE_ZERO = 0;
	public static final Integer TYPE_LEAVEBILL_STATE_ONE = 1;
	public static final Integer TYPE_LEAVEBILL_STATE_TOW = 2;
	public static final Integer TYPE_LEAVEBILL_STATE_THREE = 3;

	
	
	/**
	 * 用户默认密码
	 */
	public static final String USER_DEFAULT_PWD = "123456";
	
	/**
	 * 用户默认头像
	 */
	public static final String USER_DEFAULT_TITLE="../resources/images/defaulttitle.jpg";
   
	/**
	 * 权限类型
	 */
	public static final String PERMISSION_TYPE_MENU = "menu";
	public static final String PERMISSION_TYPE_PERMISSION = "permission";

}
