package com.tenghu.financial.model.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

/**
 * 分页实体
 * @author Arvin_Li
 *
 */
@Alias("pageBean")
@JsonIgnoreProperties(value={"DEFAULT_PAGESIZE","startNum"})
@JsonSerialize(include=Inclusion.NON_NULL)
public class PageBean<T> {
	private final int DEFAULT_PAGESIZE=10;//默认每页的行数
	
	private int currentPage;//当前页
	private int pageSize;//每页显示的记录数
	private int totalPage;//总页数
	private int totalCount;//总记录数
	private boolean isOnPage;//是否有上页
	private boolean isFirstPage;//是否是首页
	private boolean isNextPage;//是否有下页
	private boolean isLastPage;//是否是末页
	
	private int startNum;//分页开始行
	
	private List<T> showRecords=new ArrayList<T>();
	
	private Map<String, Object> paramters=new HashMap<String, Object>();

	public int getCurrentPage() {
		if(currentPage<=0)
			this.currentPage=1;
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		if(pageSize==0)
			pageSize=DEFAULT_PAGESIZE;
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		if(totalCount%pageSize==0)
			return totalCount/pageSize;
		else
			return totalCount/pageSize+1;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getShowRecords() {
		return showRecords;
	}

	public void setShowRecords(List<T> showRecords) {
		this.showRecords = showRecords;
	}

	public boolean isOnPage() {
		return getCurrentPage()>1;
	}

	public boolean isFirstPage() {
		return getCurrentPage()==1;
	}


	public boolean isNextPage() {
		return getCurrentPage()<getTotalPage();
	}


	public boolean isLastPage() {
		return getCurrentPage()==getTotalPage();
	}

	public Map<String, Object> getParamters() {
		return paramters;
	}

	public void setParamters(String name,Object value) {
		this.paramters .put(name, value);
	}

	public int getStartNum() {
		return (getCurrentPage()-1)*getPageSize();
	}
	
}
