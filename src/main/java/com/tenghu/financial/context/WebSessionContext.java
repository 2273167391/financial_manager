package com.tenghu.financial.context;

import java.util.Set;

import javax.servlet.http.HttpSession;

/**
 * HttpSession上下文接口
 * @author Arvin_Li
 *
 */
public interface WebSessionContext<T> {
	public final static String sessionAttributeKey="financial_session_key";
	
	public void setHttpSession(HttpSession httpSession);
	
	public HttpSession getHttpSession();
	//无效的会话
	public void invalidateSession();
	//设置属性
	public void setAttribute(String name,T value);
	//获取属性
	public T getAttribute(String name);
	
	//获取属性名
	public Set<String> getAttributeNames();
	
	//删除属性
	public void removeAttribute(String name);
	//销毁
	public void destory();
}
