package cn.zglong.mylog.system.user.service.impl;

import cn.zglong.mylog.system.user.dao.MenuDao;
import cn.zglong.mylog.system.user.entity.Menu;
import cn.zglong.mylog.system.user.service.MenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional
public class MenuServiceImpl extends ServiceImpl<MenuDao,Menu> implements MenuService {
	/**查询所有菜单*/
	@Autowired
	private MenuDao menuDao;
	public List<Menu> selectMenuAll(){
		List<Menu> selectMenuAll = menuDao.selectMenuAll();
		return selectMenuAll;
		
	}
}
