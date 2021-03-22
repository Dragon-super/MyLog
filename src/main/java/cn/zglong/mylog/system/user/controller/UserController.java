package cn.zglong.mylog.system.user.controller;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.zglong.mylog.common.exception.ServiceException;
import cn.zglong.mylog.common.util.excel.ExcelUtil;
import cn.zglong.mylog.common.util.tool.RedisTool;
import cn.zglong.mylog.common.vo.JsonResult;
import cn.zglong.mylog.system.user.dao.UserDao;
import cn.zglong.mylog.system.user.entity.User;
import cn.zglong.mylog.system.user.service.UserService;
import cn.zglong.mylog.system.user.service.impl.UserServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 返回用户相关信息
 *
 * @author dragon-super
 */
@Controller
@RequestMapping("/user/")
public class UserController {
    private Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTool redisTool;
    @Resource
    private UserDao userDao;

    /**
     * 查询所有用户信息
     */
    @RequestMapping(value = "selectUserAll")
    @ResponseBody
    public JsonResult selectUserAll() {
        JsonResult jsonResult = new JsonResult();
        try {
            //		String json = ""; //获取的Json数据
            jsonResult = redisTool.get("selectUserAll");
            if (jsonResult!=null){
                LOGGER.info("从【缓存】查询所有用户信息");
                return  jsonResult;
            }
        }catch (Exception e){
           throw  new ServiceException("缓存查询失败");
        }

        List<User> selectUserAll = userService.selectUserAll();
        LOGGER.info("从【数据库】查询所有用户信息");
        jsonResult = new JsonResult(selectUserAll);
        redisTool.set("selectUserAll",jsonResult);
        LOGGER.info("将用户数据存入【缓存】");
        return jsonResult;
//		return selectUserAll;
    }

    /**
     * 添加用户信息.即注册
     */
    @RequestMapping("insertUser")
    @ResponseBody
    public JsonResult insertUser(String name, String password, String email, String iphone) {
        String string1 = stringRedisTemplate.opsForValue().get("selectUserAll");
        if (string1 != null) {
            LOGGER.info("从【缓存】中删除所有用户信息");
            stringRedisTemplate.delete("selectUserAll");
        }
        User user = new User(name, password, email, iphone);
        int count = userServiceImpl.insertUser(user);
        if (count != 0) {
            return new JsonResult("你已经成功注册");
        }

        return new JsonResult("注册失败,系统异常");
    }

    /**
     * 用户登录查询验证
     */
    @SuppressWarnings("all")
    @RequestMapping(value = "selectLogin",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult selectLogin(String name, String pwd) {
        if (name == null || name == "") {
            throw new ServiceException("用户名不能为空");
        }
        if (pwd == null || pwd == "") {
            throw new ServiceException("密码不能为空");
        }
        User user = userServiceImpl.selectLogin(name);
        // 获得key
        byte[] key2 = user.getSalt();
        // 获得16进制密码
        String encryptHex2 = user.getPwd();
        // 解密获得明文密码
        SymmetricCrypto aes2 = new SymmetricCrypto(SymmetricAlgorithm.AES, key2);
        String decryptStr2 = aes2.decryptStr(encryptHex2, CharsetUtil.CHARSET_UTF_8);
        System.out.println(decryptStr2);
        if (pwd.equals(decryptStr2)) {
//			throw new ServiceException("登录成功点击确定跳转首页");
            return new JsonResult();
        } else {
            return new JsonResult(0,"密码错误,请重新登录");
        }
    }

    /**
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "export")
    @ResponseBody
    public void exportUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //Excel文件名
        String fileName = "用户信息表" + System.currentTimeMillis() + ".xls";
        //sheet名
        String sheetName = "用户信息表";
        //Excel表头
        String[] title = {"ID", "名称", "邮箱", "手机号", "注册时间"};
        //数据
        List<User> selectUserAll = userServiceImpl.selectUserAll();
        //填充数据
        String[][] content = new String[selectUserAll.size()][];
        for (int i = 0; i < selectUserAll.size(); i++) {
            content[i] = new String[title.length];
            User user = selectUserAll.get(i);
            content[i][0] = String.valueOf(user.getPwdid());
            content[i][1] = user.getName();
            content[i][2] = user.getEmail();
            content[i][3] = user.getIphone();
            content[i][4] = String.valueOf(user.getCreatedTime());
        }
        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        //响应至客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
