package com.springmvc.niklaus.utils;

import java.util.ResourceBundle;

/**
 * Created by nicholas.chi on 2016/9/27.
 */
public class ResourceTools {
    public static String getBundleValue(String key) {
        ResourceBundle resource = ResourceBundle.getBundle("dev/config");
        return resource.getString(key);
    }

    public static String getBundleValue(String filename, String key) {
        ResourceBundle resource = ResourceBundle.getBundle(filename);
        return resource.getString(key);
    }

    public static void main(String[] args){
        String port = getBundleValue("redis.port");
        System.out.println("Port : "+port);
    }
}
