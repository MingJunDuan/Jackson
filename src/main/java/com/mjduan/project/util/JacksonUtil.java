package com.mjduan.project.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by duan on 2017/4/22.
 */
public final class JacksonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        //filter null property when serialize
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //ignore unknown property when deserialize
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    private JacksonUtil(){}

    //transform Java Object to json-String
    public static String toJsonStr(Object obj) {
        if (null == obj) {
            return null;
        }

        String result=null;
        try {
            result = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    //transform json-String to Java object
    public static <T> T toObject(String jsonStr,Class<T> clazz){
        T t = null;
        try {
            t = objectMapper.readValue(jsonStr,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    //transform json-String to List<T>
    public static <T> List<T> toList(String jsonStr,Class<T> clazz) {
        if (null == jsonStr) {
            return Collections.emptyList();
        }
        JavaType constructParametricType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
        try {
            return objectMapper.readValue(jsonStr, constructParametricType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
