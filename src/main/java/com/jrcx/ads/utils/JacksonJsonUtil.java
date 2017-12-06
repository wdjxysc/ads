package com.jrcx.ads.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用jackson解析json
 *
 * @author
 */
public class JacksonJsonUtil {
    public static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * 把字符串解析成对象
     *
     * @param content
     * @param valueType
     * @return
     * @throws Exception
     */
    public static <T> T readValue(String content, Class<T> valueType)
        throws Exception {
        return mapper.readValue(content, valueType);
    }

    public static String writeValueAsString(Object value)
        throws Exception {
        return mapper.writeValueAsString(value);
    }

    /**
     * 获取泛型的Collection Type
     * @param jsonStr         json字符串
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类型
     */
    public static <T> T readJson(String jsonStr, Class<?> collectionClass, Class<?> elementClasses)
        throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        return mapper.readValue(jsonStr, javaType);
    }
    
    public static void main(String[] args) throws Exception {
    	
    	 Map<String,String> jobConfig = new HashMap<String,String>();
	        jobConfig.put("OutputObject", "11");
	        jobConfig.put("TemplateId","222");
	      
	        List<Map<String,String>> jobConfigArray = new ArrayList<Map<String,String>>();
	        jobConfigArray.add(jobConfig);
		System.out.println(JacksonJsonUtil.writeValueAsString(jobConfigArray));
		
		String str = "[{\"OutputObject\":\"11\",\"TemplateId\":\"222\"}]";
		List<Map<String,String>> maps = JacksonJsonUtil.readValue(str,  List.class);
		System.out.println(maps);
	}
}
