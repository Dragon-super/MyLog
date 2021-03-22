/**
 * Copyright 2019 bejson.com
 */
package cn.zglong.mylog.common.util.weather.dao;
import java.util.Date;

/**
 * Auto-generated: 2019-06-14 19:42:23
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ConditionDao {

    private String condition;
    private String humidity;
    private String icon;
    private String temp;
    private Date updatetime;
    private String vis;
    private String windDegrees;
    private String windDir;
    private String windLevel;
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getCondition() {
        return condition;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    public String getHumidity() {
        return humidity;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
    public String getTemp() {
        return temp;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }
    public String getVis() {
        return vis;
    }

    public void setWindDegrees(String windDegrees) {
        this.windDegrees = windDegrees;
    }
    public String getWindDegrees() {
        return windDegrees;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }
    public String getWindDir() {
        return windDir;
    }

    public void setWindLevel(String windLevel) {
        this.windLevel = windLevel;
    }
    public String getWindLevel() {
        return windLevel;
    }

}