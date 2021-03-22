package cn.zglong.mylog.system.user.dao;

import cn.zglong.mylog.system.user.entity.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;


public interface MenuDao extends BaseMapper<Menu> {
	/**查询所有菜单*/
	public List<Menu> selectMenuAll();
	
}