package cn.zglong.mylog.system.user.service;


import cn.zglong.mylog.system.user.entity.User;

import java.util.List;

public interface UserService {
	/**查询所有用户信息*/
	List<User> selectUserAll();
	/**添加用户信息即注册*/
	int insertUser(User user);
	/**查询用户  name,pwd,salt*/
	User selectLogin(String name);

}