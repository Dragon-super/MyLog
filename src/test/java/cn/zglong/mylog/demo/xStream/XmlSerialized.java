package cn.zglong.mylog.demo.xStream;

import cn.zglong.mylog.MylogApplicationTests;
import cn.zglong.mylog.common.vo.JsonResult;
import cn.zglong.mylog.system.user.entity.User;
import cn.zglong.mylog.system.user.service.UserService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
public class XmlSerialized extends MylogApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    public void toXml() {
        List<User> users = userService.selectUserAll();
        User user = users.get(0);
        XStream xStream = new XStream(new StaxDriver());
        //为根节点重命名
        xStream.alias("MyLogUser",User.class);
        //给pojo的指定属性重命名
        xStream.aliasField("姓名",User.class,"name");
        xStream.aliasField("邮箱",User.class,"email");
        //xml序列化
        String toXML = xStream.toXML(user);
        System.out.println("序列化"+toXML);
        //xml反序列化
        user = (User)xStream.fromXML(toXML);
        System.out.println("反序列化"+user);
    }
    @Test
    public  void toJson(){
        List<User> users = userService.selectUserAll();
       JsonResult  user =new JsonResult(users);

        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        //json序列化
        String toXML = xStream.toXML(user);
        System.out.println(toXML);
        //json反序列化
        user = (JsonResult)xStream.fromXML(toXML);
        System.out.println(user);
    }
}
