package cn.zglong.mylog.system.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 返回页面
 */
@RequestMapping("/")
@Controller
public class PageController {

    /***************************************加载第一页面************************************************/
    /**返回主页页面*/
    @RequestMapping("index")
    public String doIndex(){
        return "index.html";
    }

    /**返回登录页面*/
    @RequestMapping("login")
    public String doLogin() {
    	return "user/login.html";
    }
    /**返回注册页面*/
    @RequestMapping("signup")
    public String doSignup() {
    	return "user/signup.html";
    }
    /**返回用户列表页面*/
    @RequestMapping("userList")
    public String userList() {
        return "user/userList.html";
    }
    /**返回用户列表页面*/
    @RequestMapping("demo/dataTables")
    public String dataTables() {
        return "demo/dataTables/demo.html";
    }/**返回用户列表Ajax页面*/
    @RequestMapping("demo/dataTablesAjax")
    public String dataTablesAjax() {
        return "demo/dataTables/demoAjax.html";
    }
    /**返回主页页面*/
    @RequestMapping("start")
    public String doStart(){
        return "start.html";
    }
    /**返回请求列表页面*/
    @RequestMapping("url")
    public String url(){
        return "localhost/url.html";
    }
}
