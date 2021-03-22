package cn.zglong.mylog.system.user.dao;

import cn.zglong.mylog.system.user.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author long
 * @since 2019-06-29
 */
public interface UserDao extends BaseMapper<User> {
	/**查询所有用户消息*/
	List<User> selectUserAll();
	/**添加用户信息即注册*/
	int insertUser(User user);
	/**用户登录*/
	User selectLogin(String name);
	/**用户注册name重复性验证*/
	List<User> selectName(String name);
	/**用户注册email重复性验证*/
	List<User> selectEmail(String email);
	/**用户注册iphone重复性验证*/
	List<User> selectIphone(String siphone);
}