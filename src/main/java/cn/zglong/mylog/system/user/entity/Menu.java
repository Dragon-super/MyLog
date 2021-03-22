package cn.zglong.mylog.system.user.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Menu extends Entity{
	/**首页不需要id暂时注释*/
	@TableId(type = IdType.AUTO)
	private int menuid;
	private String menuname;

}
