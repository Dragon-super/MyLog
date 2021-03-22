package cn.zglong.mylog.test.tool;


import cn.zglong.mylog.common.exception.ServiceException;

public class Validate {
	public static void main(String[] args) {
		String s1= "189215399778";
		if (!(emailValidate(s1))) {
			throw new ServiceException("手机号格式错误,请重新输入");
		}
	}
	
	static boolean emailValidate(String s) {
		String regex = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
		return s.matches(regex);
	}
}

