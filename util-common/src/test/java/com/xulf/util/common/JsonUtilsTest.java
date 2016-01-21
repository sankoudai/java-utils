package com.xulf.util.common;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;

import java.util.*;

/**
 * Created by wangwenyao on 2015/8/6.
 */
public class JsonUtilsTest {

    @Test
    public void fromJson() {
        Map<String, Object> map = exampleMap();
        String json = JsonUtils.toJson(map);
        map = JsonUtils.fromJson(json, HashMap.class);
        System.out.println(map);
    }

    @Test
    public void genericFromJson() {
        Map<String, Object> map = exampleMap();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list.add(map);
        String json = JsonUtils.toJson(list);
        list = JsonUtils.fromJson(json, new TypeReference<ArrayList>() {
        });
        System.out.println(list);
    }

    Map<String, Object> exampleMap(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "val1");
        map.put("key2", new Date());
        return map;
    }
}
