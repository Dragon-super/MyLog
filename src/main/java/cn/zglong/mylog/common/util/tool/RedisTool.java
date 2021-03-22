package cn.zglong.mylog.common.util.tool;

import cn.zglong.mylog.common.vo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTool {
    private Logger LOGGER = LoggerFactory.getLogger(RedisTool.class);
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @SuppressWarnings("all")
    public boolean set(String key, JsonResult value) {
        try {
            //任意类型转换成String
            String val = RedisChangeResult.pojoFromStr(value);
            if (val == null || val.length() <= 0) {
                return false;
            }
            stringRedisTemplate.opsForValue().set(key, val);
            return true;
        } catch (Exception e) {
            LOGGER.info("出错了");
            return false;
        }
    }

    public JsonResult get(String key) {
        JsonResult jsonResult = null;
        try {
            String value = stringRedisTemplate.opsForValue().get(key);
            if (value != null) {
                jsonResult = RedisChangeResult.strFromPojo(value);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return jsonResult;
    }


}
