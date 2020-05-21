package com.joe.gmail.manage.util;

import java.io.FileInputStream;
import java.util.Properties;

public class ProperiesUtil {

    public static String getProperiesValue(String name,String key){
        String value = null;
        String file = ProperiesUtil.class.getResource("/"+name).getFile();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
            value = properties.getProperty(key);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }


}
