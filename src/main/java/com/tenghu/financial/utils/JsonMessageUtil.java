package com.tenghu.financial.utils;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * Json消息工具类
 * @author Arvin_Li
 *
 */
public class JsonMessageUtil {
	private JsonMessageUtil(){}
	
	/**
	 * 成功JSON消息
	 * @param message
	 * @return
	 */
	public static String getSuccessJSON(String message){
		return "{\"result\":1,\"message\":\""+message+"\"}";
	}
	
	/**
	 * 失败JSON消息
	 * @param message
	 * @return
	 */
	public static String getErrorJSON(String message){
		return "{\"result\":0,\"message\":\""+message+"\"}";
	}
	
	/**
	 * 返回集合JSON消息
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "null" })
	public static String getListJSON(List list){
		if(null==list&&list.size()<=0)
			return getErrorJSON("List is null");
		String listJSON=JSONObject.toJSONString(list).toString();
		return "{\"result\":1,\"data\":"+listJSON+"}";
	}
	
	/**
	 * 返回对象JSON消息
	 * @param obj
	 * @return
	 */
	public static String getObjectJSON(Object obj){
		if(null==obj)
			return getErrorJSON("Object is null");
		String objJson=JSONObject.toJSONString(obj).toString();
		return "{\"result\":1,\"data\":"+objJson+"}";
	}
}
