package com.tenghu.financial.context.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.tenghu.financial.context.WebSessionContext;

public class WebSessionContextImpl<T> implements WebSessionContext<T>{
	private HttpSession httpSession;
	private Map<String, T> attribute;

	@SuppressWarnings("unchecked")
	@Override
	public void setHttpSession(HttpSession httpSession) {
		this.httpSession=httpSession;
		//从会话中获取attribute
		this.attribute=(Map<String, T>) this.httpSession.getAttribute(sessionAttributeKey);
		if(null==this.attribute){
			this.attribute=new HashMap<String, T>();
			this.onSaveSessionAttribute();
		}
	}
	
	/**
	 * 保存属性
	 */
	public void onSaveSessionAttribute(){
		//将属性集合保存到Session
		this.httpSession.setAttribute(sessionAttributeKey, attribute);
	}

	@Override
	public HttpSession getHttpSession() {
		return this.httpSession;
	}

	@Override
	public void invalidateSession() {
		this.httpSession.invalidate();
	}

	@Override
	public void setAttribute(String name, T value) {
		if(null!=this.attribute){
			this.attribute.put(name, value);		
			this.onSaveSessionAttribute();
		}
	}

	@Override
	public T getAttribute(String name) {
		if(null!=this.attribute)
			return this.attribute.get(name);
		return null;
	}

	@Override
	public void removeAttribute(String name) {
		if(null!=this.attribute){
			this.attribute.remove(name);
			this.onSaveSessionAttribute();
		}
	}

	@Override
	public void destory() {
		this.attribute=null;
		this.httpSession=null;
	}

	@Override
	public Set<String> getAttributeNames() {
		return this.attribute.keySet();
	}

}
