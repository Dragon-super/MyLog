package cn.zglong.mylog.test.redis;

import cn.zglong.mylog.MylogApplicationTests;
import cn.zglong.mylog.common.config.RedisConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class redisContentTest extends MylogApplicationTests {
    @Autowired
    RedisConfig rdisConfig;
    @Test
    public void redisTest(){

        User user = new User();
        user.setId(11);
        user.setUsername("test");
        user.setPassword("hello redis");
        rdisConfig.set("key1",user);

        User us = rdisConfig.get("key1",User.class);
        System.out.println(us.getUsername()+":  "+us.getPassword());
    }
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Test
    public void test()  {

        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");

    }
}
