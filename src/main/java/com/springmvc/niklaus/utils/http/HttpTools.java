package com.springmvc.niklaus.utils.http;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by nicholas.chi on 2016/9/27.
 */
public class HttpTools {
    private static final Logger log = LoggerFactory.getLogger(HttpTools.class);

    public static String sendGet(String url, String param){
        String result = "";
        BufferedReader in = null;
        try{
            String urlName = url;
            if (StringUtils.isNotBlank(param)) {
                urlName = url + "?" + param;
            }
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Content-Type","text/html,application/xhtml+xml,application/xml");
            conn.setRequestProperty("Charset", "utf-8");
            // 建立实际的连接
            conn.connect();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null){
                result += "" + line;
            }
        }catch (Exception e){
            log.error("发送GET请求出现异常！ {}",e);
            result = e.getMessage();
            e.printStackTrace();
        }finally{// 使用finally块来关闭输入流
            try{
                if (in != null){
                    in.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String sendPost(String link, String param){
        BufferedWriter out = null;
        BufferedReader in = null;
        String body = "";
        try {
            URL url = new URL(link);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            out.write(param);
            out.flush();
            // 读取响应
            InputStream jnn = connection.getInputStream();
            in = new BufferedReader(new InputStreamReader(jnn, "UTF-8"));
            String line = "";
            while ((line = in.readLine()) != null) {
                body += "" + line;
            }
            if (connection.getResponseCode() == 200) {
                return body;
            }
        } catch (IOException e) {
            log.error("send to request is failed: {}",e);
            e.printStackTrace();
        } finally { // 关闭流
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                log.error("close in or out io failed");
                ex.printStackTrace();
            }
        }
        return body;
    }

    public static void main(String[] args){
        String url  = "http://charts.kitco.com/KitcoCharts/RequestHandler";
        String param = "requestName=getSymbolSnapshot&Symbol=GOLD";
        String result = sendGet(url,param);
        log.info("通过请求得到的返回结果 ： {}" , result);
    }
}
