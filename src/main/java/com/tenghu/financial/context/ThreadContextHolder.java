package com.tenghu.financial.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tenghu.financial.context.impl.WebSessionContextImpl;

/**
 * 本地线程类
 * @author Arvin_Li
 *
 */
public class ThreadContextHolder {
	private ThreadContextHolder(){}
	
	@SuppressWarnings("rawtypes")
	private static ThreadLocal<WebSessionContext> sessionContextThreadContextHolder=new ThreadLocal<WebSessionContext>();
	private static ThreadLocal<HttpServletRequest> httpRequestThreadContextHolder=new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> httpResponseThreadContextHolder=new ThreadLocal<HttpServletResponse>();
	
	/**
	 * 将WebSessionContext设置到当前线程中
	 * @param context
	 */
	@SuppressWarnings("rawtypes")
	public static void setSessionContext(WebSessionContext context){
		sessionContextThreadContextHolder.set(context);
	}
	
	/**
	 * 从当前线程中销毁WebSessionContext
	 */
	@SuppressWarnings("rawtypes")
	public static void destorySessionContext(){
		WebSessionContext context=sessionContextThreadContextHolder.get();
		if(null!=context){
			context.destory();
		}
	}
	
	/**
	 * 从当前线程中获取WebSessionContext
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static WebSessionContext getSessionContext(){
		if(null==sessionContextThreadContextHolder.get()){
			sessionContextThreadContextHolder.set(new WebSessionContextImpl());
		}
		return sessionContextThreadContextHolder.get();
	}
	
	/**
	 * 将HttpServletRequest设置到本地线程中
	 * @param httpRequest
	 */
	public static void setHttpRequest(HttpServletRequest httpRequest){
		httpRequestThreadContextHolder.set(httpRequest);
	}
	
	/**
	 * 从本地线程中获取HttpServletRequest
	 * @return
	 */
	public static HttpServletRequest getHttpRequest(){
		return httpRequestThreadContextHolder.get();
	}
	
	/**
	 * 将HttpServletResponse设置到本地线程中
	 * @param httpResponse
	 */
	public static void setHttpResponse(HttpServletResponse httpResponse){
		httpResponseThreadContextHolder.set(httpResponse);
	}
	
	/**
	 * 从本地线程中获取HttpServletResponse
	 * @return
	 */
	public static HttpServletResponse getHttpResponse(){
		return httpResponseThreadContextHolder.get();
	}
}
