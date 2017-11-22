package cn.javaxxw.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/** 
 * @author  tuyong: 
 * @date 创建时间：2016年12月14日 上午11:46:54 
 * @version 1.0  
 * desc json工具类
 */
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 将json结果集转化为对象
     * 
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }

    /**
	 * json字符串转实体对象
	 * @param jsonString
	 * @return
	 */
	public static <T>T jsonStringToEntity(String jsonString, Class<T> clazz){
		if(jsonString!=null && !"".equals(jsonString)){
			JSONObject jsonObject= JSONObject.fromObject(jsonString);
			Map<String, Class> map=new HashMap<String, Class>();
			map.put("ls", clazz);
			return (T) JSONObject.toBean(jsonObject, clazz,map);
		}
		return null;
	}
	
	public static HashMap<String, String> toHashMap(Object object)  
	   {  
	       HashMap<String, String> data = new HashMap<String, String>();  
	       // 将json字符串转换成jsonObject  
	       JSONObject jsonObject = JSONObject.fromObject(object);
	       Iterator it = jsonObject.keys();  
	       // 遍历jsonObject数据，添加到Map对象  
	       while (it.hasNext())  
	       {  
	           String key = String.valueOf(it.next());  
	           String value = (String) jsonObject.get(key);  
	           data.put(key, value);  
	       }  
	       return data;  
	   }  
	
	public static String toUrlParm(Object object)  
	   {  
		   StringBuilder sb=new StringBuilder(); 
	       // 将json字符串转换成jsonObject  
	       JSONObject jsonObject = JSONObject.fromObject(object);
	       Iterator it = jsonObject.keys();  
	       // 遍历jsonObject数据，添加到Map对象  
	       while (it.hasNext())  
	       {  
	           String key = String.valueOf(it.next());  
	           Object value = (Object) jsonObject.get(key);  
	           sb.append(key+"="+value+"&");
	       } 
	       
	       return sb.toString().substring(0,sb.toString().length()-1);  
	   }  
	
	/**
     * json字符串转换为List
     *@descript
     *@param jsonStr
     *@return
     *@author tuyong
     *@date 2015年6月15日
     *@version 1.0v
     */
    public static List<Map<String, Object>> parseJSONList(String jsonStr){  
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();  
        Iterator<JSONObject> it = jsonArr.iterator();
        while(it.hasNext()){  
            JSONObject JSON = it.next();
            list.add(parseJSONMap(JSON.toString()));  
        }  
        return list;  
    }  
    
    /**
     * json字符串转换为map
     *@descript
     *@param jsonStr
     *@return
     *@author tuyong
     *@date 2015年6月15日
     *@version 1.0v
     */
   public static Map<String, Object> parseJSONMap(String jsonStr){  
       Map<String, Object> map = new HashMap<String, Object>();  
       try {
			//最外层解析  
			JSONObject json = JSONObject.fromObject(jsonStr);
			for (Object k : json.keySet()) {
				Object v = json.get(k);
				//如果内层还是数组的话，继续解析  
				if (v instanceof JSONArray) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Iterator<JSONObject> it = ((JSONArray) v).iterator();
					while (it.hasNext()) {
						JSONObject JSON = it.next();
						list.add(parseJSONMap(JSON.toString()));
					}
					map.put(k.toString(), list);
				} else {
					map.put(k.toString(), v);
				}
			} 
		} catch (Exception e) {
			map.put("exception", jsonStr);
		}
		return map;  
   }  
	
}
