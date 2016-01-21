package com.xulf.util.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Json序列化工具
 * Created by wangwenyao on 2015/5/7.
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static final ObjectMapper objectMapper = new ObjectMapper() {{
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//未知的字段忽略
        setSerializationInclusion(JsonInclude.Include.NON_NULL);//null值的字段不序列化
    }};


    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error(String.format("反序列化JSON出现异常，class=%d,json=%d", clazz, json), e);
        }
        return null;
    }

    public static <T> T fromJson(String json, TypeReference clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error(String.format("反序列化JSON出现异常，class=%d,json=%d", clazz, json), e);
        }
        return null;
    }

    public static <T> String toJson(T value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (IOException e) {
            logger.error(String.format("序列化JSON出现异常，value=%d", value), e);
        }
        return null;
    }
}
