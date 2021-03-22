package cn.zglong.mylog.test.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.mail.MailUtil;
import cn.zglong.mylog.common.vo.JsonResult;
import cn.zglong.mylog.system.user.entity.User;
import cn.zglong.mylog.system.user.service.UserService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserTest  {
	@Autowired
    private UserService userService;
	/**注册加密测试*/
	@Test
	public void TestInsert() {
		User user = new User();
		System.out.println(user);
//		user.setPwdid(3);
		//	用户名不能重复
		String code = Convert.toStr((int) (Math.random() * 1000000));
		user.setName(code);
		user.setPwd(code);
		user.setEmail("25513563356579@qq.com");
		user.setIphone("18212391778");
//		user.setSalt("34234fdfg344r");
		System.out.println(user.toString());
		int insertUser = userService.insertUser(user);
		System.out.println("添加成功"+insertUser);

	}
	@Test
	public void test02() {
		List<User> selectUserAll = userService.selectUserAll();
		JsonResult jsonResult1 = new JsonResult(selectUserAll);

		String jsonResultStr = JSON.toJSONString(jsonResult1.toString());
		JsonResult jsonResult2 = JSON.parseObject(jsonResultStr, JsonResult.class);
		System.out.println(jsonResult2.toString());
	}
	@Test
	public void test03(){
		MailUtil.send("helong255@yeah.net", "[吾志]注册邮箱验证码", "123456780-", false);
	}
}
