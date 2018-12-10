package com.lms.api.constant;

/**
 * 消息状态码集
 * @author Ming
 * @date 2018年11月5日
 */
public class Code {
	
	/**
	 * 正常码
	 */
	public static final Integer SUCCESS=Integer.valueOf(1);
	/**
	 * 正常码
	 */
	public static final Integer OK=Integer.valueOf(1);
	
	/**
	 * 错误码
	 */
	public static final Integer ERROR=Integer.valueOf(0);
	
	/**
	 * 错误详细码
	 * @author Ming
	 * @date 2018年11月1日
	 */
	public static class ERROR{
		/**
		 * 密码错误
		 */
		public static final Integer ERROR_CODE_101=Integer.valueOf(101);
		/**
		 * 用户不存在
		 */
		public static final Integer ERROR_CODE_102=Integer.valueOf(102);
		/**
		 * 空数据
		 */
		public static final Integer ERROR_CODE_103=Integer.valueOf(103);
		/**
		 * 未登录
		 */
		public static final Integer	ERROR_CODE_104=Integer.valueOf(104);
		/**
		 * 无权限
		 */
		public static final Integer ERROR_CODE_105=Integer.valueOf(105);
		/**
		 * token过期
		 */
		public static final Integer ERROR_CODE_106=Integer.valueOf(106);
		/**
		 * 无效token
		 */
		public static final Integer ERROR_CODE_107=Integer.valueOf(107);
		/**
		 * ip地址异常
		 */
		public static final Integer ERROR_CODE_108 = Integer.valueOf(108);
	}

}
