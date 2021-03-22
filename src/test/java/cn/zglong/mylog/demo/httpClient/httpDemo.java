package cn.zglong.mylog.demo.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class httpDemo {
    @Test
    public void get() throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
       /* //固定url
        String url = "http://www.baidu.com";
        *//*有参数1*//*
        //参数
        StringBuilder params = new StringBuilder();
        //特殊字符在encode下传送避免错误发生
        params.append("name" + URLEncoder.encode("姓&名", "utf-8"));
        params.append("&");
        params.append("age=12");
        url = url + "?" + params;*/
        /*有参数2*/
        //定义请求全路径
        URI urlParams = null;
        //将参数 以键值对放入BasicNameValuePair中再放入List
        List<NameValuePair> params1 = new ArrayList<>();
        params1.add(new BasicNameValuePair("name", "&&&"));
        params1.add(new BasicNameValuePair("age", "3"));
        //设置url
        urlParams = new URIBuilder().setScheme("http").setHost("www.baidu.com").setPath("80").
                setPath("/").setParameters(params1).build();
        HttpGet httpGet = new HttpGet(urlParams);


        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);
        //获取响应实体
        HttpEntity entity = response.getEntity();
        //获取响应状态
        StatusLine statusLine = response.getStatusLine();
        //获取响应长度
        entity.getContentLength();
        //获得响应内容
        EntityUtils.toString(entity);
    }
    public void post(){

    }
}