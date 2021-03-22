package cn.zglong.mylog.system.user.util.task;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.mail.MailUtil;
import cn.zglong.mylog.common.util.weather.WeatherUtil;
import cn.zglong.mylog.system.user.entity.User;
import cn.zglong.mylog.system.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class TimeTask {
    private Logger LOGGER  = LoggerFactory.getLogger(TimeTask.class);
    @Autowired
    private UserService userService;
//    @Scheduled(cron = "* 0/1 * * * ?")
    private void cancelTimeOutOrder() {
        User user = new User();
//        System.out.println(user);
//		user.setPwdid(3);
        //	用户名不能重复
        String code = Convert.toStr((int) (Math.random() * 1000000));
//        System.out.println(code);
        user.setName(code);
        user.setPwd(code);
        user.setEmail(code+"@qq.com");
        user.setIphone("18215"+code);
//        System.out.println(user.getIphone());
//		user.setSalt("34234fdfg344r");
//        System.out.println(user.toString());
        int insertUser = userService.insertUser(user);
        LOGGER.info("(添加用户)定时任务执行完成");
    }
//    @Scheduled(cron = "0/10 * * * * ?")
    public void WeatherEmail(){
        //雁塔
        String cityId = "285270";
        String weatherJson = WeatherUtil.WeatherUtilCityID(cityId);
        LOGGER.info(weatherJson);
        MailUtil.send("helong255@yeah.net", "[雁塔]天气信息", weatherJson, false);
    }
}
