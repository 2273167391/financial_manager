package com.tenghu.financial.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tenghu.financial.model.AccountType;
import com.tenghu.financial.model.page.PageBean;
import com.tenghu.financial.service.IAccountTypeService;
import com.tenghu.financial.test.BasicTest;


public class AcccountTypeServiceTest extends BasicTest{
	
	@Autowired
	private IAccountTypeService accountTypeService;
	
	@Test
	public void testQueryAccountType(){
		PageBean<AccountType> pageBean=new PageBean<AccountType>();
		pageBean.setCurrentPage(1);
		pageBean.setPageSize(2);
		pageBean=accountTypeService.queryPageAcountType(pageBean);
		System.out.println(pageBean.getShowRecords());
	}
}
