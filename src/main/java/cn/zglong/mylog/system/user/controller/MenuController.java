package cn.zglong.mylog.system.user.controller;

import cn.zglong.mylog.common.exception.ServiceException;
import cn.zglong.mylog.common.util.tool.RedisTool;
import cn.zglong.mylog.common.vo.JsonResult;
import cn.zglong.mylog.system.user.entity.Menu;
import cn.zglong.mylog.system.user.service.MenuService;
import cn.zglong.mylog.system.user.service.impl.MenuServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 	菜单Controller
 * 	返回菜单相关的CRUD
 * @author 20181128
 *
 */
@Controller
@RequestMapping("menu/")
public class MenuController {
	private Logger LOGGER  = LoggerFactory.getLogger(MenuController.class);;
	@Autowired 
	private MenuService menuService;
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	@Autowired
	RedisTool redisTool;
	@RequestMapping("selectMenuAll")
	@ResponseBody
	public JsonResult selectMenuAll() {
		JsonResult jsonResult = new JsonResult();
		try {
			//		String json = ""; //获取的Json数据
			jsonResult = redisTool.get("selectMenuAll");
			if (jsonResult!=null){
				LOGGER.info("从【缓存】查询所有菜单信息");
				return  jsonResult;
			}
		}catch (Exception e){
			throw  new ServiceException("缓存查询失败");
		}
		List<Menu> selectMenuAll = menuService.selectMenuAll();
		LOGGER.info("从【数据库】查询所有菜单信息");
		jsonResult = new JsonResult(selectMenuAll);
		redisTool.set("selectMenuAll",jsonResult);
		LOGGER.info("将菜单数据存入【缓存】");
		return jsonResult;
	}
}
