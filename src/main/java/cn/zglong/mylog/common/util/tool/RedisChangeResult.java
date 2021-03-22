package cn.zglong.mylog.common.util.tool;

import cn.zglong.mylog.common.vo.JsonResult;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisChangeResult {
    private static Logger LOGGER = LoggerFactory.getLogger(RedisChangeResult.class);

    public static String pojoFromStr(JsonResult jsonResult) {
        String jsonResultStr = null;
        try {
            XStream xStream = new XStream(new JettisonMappedXmlDriver());
            xStream.setMode(XStream.NO_REFERENCES);
            jsonResultStr = xStream.toXML(jsonResult);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("数据转换【JsonResult-->String】异常");
        }
        return jsonResultStr;
    }

    public static JsonResult strFromPojo(String jsonResultStr) {
        JsonResult jsonResult = null;
        try {
            XStream xStream = new XStream(new JettisonMappedXmlDriver());
            xStream.setMode(XStream.NO_REFERENCES);
            jsonResult = (JsonResult) xStream.fromXML(jsonResultStr);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("数据转换【String-->JsonResult】异常");
        }
        return jsonResult;
    }

}
