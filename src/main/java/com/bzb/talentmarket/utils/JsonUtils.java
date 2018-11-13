package com.bzb.talentmarket.utils;

import java.io.IOException;
import java.util.Collection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author bzb
 * @Description: json转换工具类
 * @date 2018/11/13 10:01
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(Include.NON_NULL);
        //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 将对象转化为json字符串
     * @author bzb
     * @param obj 待转化的对象
     * @return 当转化发生异常时返回null
     */
    public static String toJson(Object obj){
        if(obj == null){
            return null;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json转化为对象
     * @author bzb
     * @time 2017年3月16日 下午2:56:26
     * @param json json对象
     * @param clazz 待转化的对象类型
     * @return 当转化发生异常时返回null
     */
    public static <T> T fromJson(String json, Class<T> clazz){
        if(json == null){
            return null;
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json对象转化为集合类型
     * @author bzb
     * @time 2017年3月16日 下午2:57:15
     * @param json json对象
     * @param collectionClazz 具体的集合类的class，如：ArrayList.class
     * @param clazz 集合内存放的对象的class
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <T> Collection<T> fromJson(String json, Class<? extends Collection> collectionClazz, Class<T> clazz){
        if(json == null){
            return null;
        }
        try {
            Collection<T> collection =  mapper.readValue(json, getCollectionType(collectionClazz,clazz));
            return collection;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * json转换为对象
     * @param json json字符串
     * @param tr 对象类型，如new TypeReference() {}
     * @param <T> 转换后的对象
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T json2Object(String json, TypeReference<T> tr) {
        if (StringUtils.hasLength(json) == false)
            return null;

        T t = null;
        try {
            t = (T) mapper.readValue(json, tr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) t;
    }
}
