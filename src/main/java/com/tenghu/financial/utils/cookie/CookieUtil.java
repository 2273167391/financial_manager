package com.tenghu.financial.utils.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tenghu.financial.context.ThreadContextHolder;

/**
 * Cookie工具类
 * @author Arvin_Li
 *
 */
public class CookieUtil {
	/**
     * 设置cookie有效期，根据需要自定义[本系统设置为30天]
     */
    private final static int COOKIE_MAX_AGE = 1000 * 60 * 60 * 24 * 30;
    
    /**
     * 删除Cookie
     * @param httpRequest
     * @param httpResponse
     * @param name
     */
    public static void removeCookie(String name){
    	HttpServletResponse httpResponse=ThreadContextHolder.getHttpResponse();
    	Cookie cookie=getCookie(name);
    	if(null!=cookie){
	    	cookie.setValue("");
	    	cookie.setPath("/");
	    	httpResponse.addCookie(cookie);
    	}
    }
    
    /**
     * 根据名称获取Cookie值
     * @param name
     * @return
     */
    public static String getCookieValue(String name){
    	Cookie cookie=getCookie(name);
    	if(null!=cookie){
    		return cookie.getValue();
    	}else{
    		return "";
    	}
    }
    
    /**
     * 获取Cookie对象
     * @param name
     * @return
     */
    public static Cookie getCookie(String name){
    	//从本地线程中获取HttpServeltRequest
    	HttpServletRequest httpRequest=ThreadContextHolder.getHttpRequest();
    	//获取Cookie数组
    	Cookie[] cookies=httpRequest.getCookies();
    	if(null==cookies||null==name||"".equals(name)){
    		return null;
    	}
    	Cookie cookie=null;
    	for (int i = 0; i < cookies.length; i++) {
			if(!cookies[i].getName().equals(name))
				continue;
			cookie=cookies[i];
			if(httpRequest.getServerName().equals(cookie.getDomain()))
				break;
		}
    	return cookie;
    }
    
    /**
     * 设置Cookie信息，默认存在时间为一个月
     * @param httpResponse
     * @param name
     * @param value
     */
    public static void setCookie(String name,String value){
    	setCookie( name, value,COOKIE_MAX_AGE);
    }
    
    /**
     * 设置Cookie信息，可以添加最大存在时间
     * @param httpResponse
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(String name,String value,int maxAge){
    	HttpServletResponse httpResponse=ThreadContextHolder.getHttpResponse();
    	if(null==value||"".equals(value)){
    		value="";
    	}
    	//创建Cookie对象
    	Cookie cookie=new Cookie(name, value);
    	if(maxAge!=0){
    		cookie.setMaxAge(maxAge);
    	}else{
    		cookie.setMaxAge(COOKIE_MAX_AGE);
    	}
    	cookie.setPath("/");
    	httpResponse.addCookie(cookie);
    }
}
