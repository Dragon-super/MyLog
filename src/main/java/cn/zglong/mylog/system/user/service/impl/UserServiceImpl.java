package cn.zglong.mylog.system.user.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.zglong.mylog.common.exception.ServiceException;
import cn.zglong.mylog.common.util.tool.RedisTool;
import cn.zglong.mylog.system.user.dao.UserDao;
import cn.zglong.mylog.system.user.entity.User;
import cn.zglong.mylog.system.user.service.UserService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTool redisTool;

    /**
     * 查询所有用户信息
     */
    @Override
    public List<User> selectUserAll() {
        List<User> selectUserAll = userDao.selectUserAll();
        String selectUserAllJson = JSON.toJSONString(selectUserAll);
        return selectUserAll;
    }

    /**
     * 用户注册
     */
    @Override
    public int insertUser(User user) {

        // 注册信息校验
        echo(user);

        // 加密密码,把盐值存进数据库

        String content = user.getPwd();
        // 随机生成密钥
        byte[] key1 = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        // 构建
        SymmetricCrypto aes1 = new SymmetricCrypto(SymmetricAlgorithm.AES, key1);
        // 加密
        byte[] encrypt = aes1.encrypt(content);

        // 加密为16进制表示
        String encryptHex1 = aes1.encryptHex(content);
        // 存入加密后的16进制密码
        user.setPwd(encryptHex1);
        // 存入key
        user.setSalt(key1);
//		MailUtil.send("helong255@yeah.net", "测试", "邮件来自吾志测试", false);
        // 生成随机验证码
        String code = Convert.toStr((int) (Math.random() * 1000000));
        // 发送邮件
//		MailUtil.send(user.getEmail(), "[吾志]注册邮箱验证码", code, false);
//        MailUtil.send("helong255@yeah.net", "[吾志]注册邮箱验证码", code, false);
        LOGGER.info("验证邮件发送完成");

        int insertUser = 0;
        try {
            insertUser = userDao.insertUser(user);
            LOGGER.info("用户注册成功");

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("用户注册失败");
          }
        return insertUser;
    }

    /**
     * 用户登录
     */
    @Override
    public User selectLogin(String name) {
        User user = userDao.selectLogin(name);
        // 获得key
        byte[] key2 = user.getSalt();
        // 获得16进制密码
        String encryptHex2 = user.getPwd();
        // 解密获得明文密码
        SymmetricCrypto aes2 = new SymmetricCrypto(SymmetricAlgorithm.AES, key2);
        LOGGER.info("用户登录");
        return user;
    }

    /**
     * 注册信息校验
     */
    public void echo(User user) {
        List<User> selectName = userDao.selectName(user.getName());
        // 用户名重复判断
//		System.out.println(selectName.size());
        if (selectName.size() != 0) {
            throw new ServiceException("用户名【" + user.getName() + "】已经被注册,请重新输入");
        }
//		System.out.println(user);
        if (user.getName() == null || user.getName() == "") {
            throw new ServiceException("用户名和密码不能为空啦!");
        }
        if (user.getPwd() == null || user.getPwd() == "") {
            throw new ServiceException("都说了密码不能为空了!");
        }
        // 邮箱重复性验证
        List<User> selectEmail = userDao.selectEmail(user.getEmail());
        if (selectEmail.size() != 0) {
            throw new ServiceException("邮箱【" + user.getEmail() + "】已经被注册");
        }
        if (user.getName() == null || user.getName() == "") {
            throw new ServiceException("实在抱歉,邮箱也不能为空!");
        }
        if (user.getIphone() == null || user.getIphone() == "") {
            throw new ServiceException("抱歉手机号不能为空!继续吧,或许邮箱能为空呢!");
        }
        // 手机号合法性验证
        /*if (!(emailValidate(user.getIphone()))) {
            throw new ServiceException("手机号格式错误,请重新输入");
        }*/
        // 手机号重复性验证
        List<User> selectIphone = userDao.selectIphone(user.getIphone());
        if (selectIphone.size() != 0) {
            throw new ServiceException("手机号【" + user.getIphone() + "】已经被注册");
        }
    }

    boolean emailValidate(String s) {
        String regex = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
        return s.matches(regex);
    }
}
