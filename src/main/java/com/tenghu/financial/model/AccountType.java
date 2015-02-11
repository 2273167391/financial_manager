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
 * 账目类型
 * @author Arvin_Li
 *
 */
@Alias("accountType")
@JsonIgnoreProperties(value={"accountList"})
@JsonSerialize(include=Inclusion.NON_NULL)
public class AccountType implements Serializable{
	private int atId;//类型id
	private String typeName;//类型名称
	private List<Account> accountList=new ArrayList<Account>();
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;//创建时间
	public int getAtId() {
		return atId;
	}
	public void setAtId(int atId) {
		this.atId = atId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public List<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
