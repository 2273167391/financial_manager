package com.tenghu.financial.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;


/**
 * 权限实体类
 * 
 * @author Arvin_Li
 * 
 */

@Alias("authority")
@JsonIgnoreProperties(value={"childList"})
@JsonSerialize(include=Inclusion.NON_NULL)
public class Authority implements Serializable {

	public Authority() {
		super();
	}

	private int authId;// 权限id
	private String authName;// 权限名称
	private Authority parentAuth;// 父级权限
	private String url;// url地址
	private int level;// 级别
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	private List<Authority> childList=new ArrayList<Authority>();// 下级角色

	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public Authority getParentAuth() {
		return parentAuth;
	}

	public void setParentAuth(Authority parentAuth) {
		this.parentAuth = parentAuth;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Authority> getChildList() {
		return childList;
	}

	public void setChildList(List<Authority> childList) {
		this.childList = childList;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}
