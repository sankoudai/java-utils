package com.xulf.util.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by sankoudai on 2016/10/11.
 */
public class URIUtil {
    /**
     * 将字符串转化成Map形式.
     * api约定如下:
     * 1. 如果queryString格式错误, 将可以分辨的参数返回, 不可分辨的舍弃.
     * 例子: a=1&b&c=1  将被解析为 {"a": ["1"], "c":["1"]}
     * 2. 如果queryString两个分隔符之间, 有多于一个=, 则键值分割以第一个等号为准
     * 例子: a=1&b==1&c=2 将被解析为{"a":["1"],  "b":["=1"],  "c":["1"]}
     * 3. 如果queryString为null或空字符串, 返回空的Map
     * 4. 如果queryString中有两个key相同， 则两个值会被放到同一个key下
     *
     * @param queryString 待解析字符串 key=val&key=val&key3=val
     * @return 解析后的结果
     */
    public static Map<String, String[]> resolveParametersStrictly(String queryString) {
        //参数检查
        if (queryString == null || queryString.trim().equals("")) {
            return new HashMap<String, String[]>();
        }


        //解析
        Map<String, ArrayList<String>> listParams = new HashMap<String, ArrayList<String>>();
        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            //一对key-val
            String[] items = pair.split("=", 2);
            if (items.length == 2) {
                String key = items[0];
                String val = items[1];

                ArrayList<String> vals = listParams.get(key);
                if (vals == null) {
                    vals = new ArrayList<String>(1);
                }

                vals.add(val);
                listParams.put(key, vals);
            }
        }

        //格式转换
        Map<String, String[]> arrayParams = new HashMap<String, String[]>();
        Set<Map.Entry<String, ArrayList<String>>> entries = listParams.entrySet();
        for (Map.Entry<String, ArrayList<String>> entry : entries) {
            String key = entry.getKey();
            String[] val = entry.getValue().toArray(new String[0]);
            arrayParams.put(key, val);
        }
        return arrayParams;
    }


    /**
     * 将字符串转化成Map形式
     * 如果多个key相同, 保留第一个key对应得 值
     *
     * @param queryString 待解析字符串 key=val&key=val&key3=val
     */
    public static Map<String, String> resolveParameters(String queryString) {
        //参数检查
        if (queryString == null || queryString.trim().equals("")) {
            return new HashMap<String, String>();
        }

        //解析
        Map<String, String> params = new HashMap<String, String>();
        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            //一对key-val
            String[] items = pair.split("=", 2);
            if (items.length == 2 && params.get(items[0]) == null) {
                try {
                    params.put(items[0], URLDecoder.decode(items[1], "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        return params;
    }

    /**
     * @param url
     * @param key
     * @param value
     * @return
     */
    public static String appendParam(String url, String key, String value) {
        if (url == null || key == null || value == null) {
            return url;
        }

        String newUrl = url;
        try {
            String pair = key + "=" + URLEncoder.encode(value, "utf8");
            if (!url.contains("?")) {
                newUrl = newUrl + "?" + pair;
            } else {
                if (url.endsWith("&") || url.endsWith("?")) {
                    newUrl = newUrl + pair;
                } else {
                    newUrl = newUrl + "&" + pair;
                }
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("HttpUtil[appendParam]出错: UnsupportedEncodingException！");
        }

        return newUrl;
    }
}
