package com.tenghu.financial.test.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.tenghu.financial.mapper.AccountMapper;
import com.tenghu.financial.mapper.AccountTypeMapper;
import com.tenghu.financial.mapper.UsersMapper;
import com.tenghu.financial.model.Account;
import com.tenghu.financial.model.AccountType;
import com.tenghu.financial.model.Users;
import com.tenghu.financial.model.page.PageBean;
import com.tenghu.financial.test.BasicTest;

/**
 * 账目测试类
 * @author Arvin_Li
 *
 */
public class AccountMapperTest extends BasicTest{
	
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private AccountTypeMapper accountTypeMapper;
	
	/**
	 * 根据类型id查询账目
	 */
	@Test
	public void testQueryAccountByTypeId(){
		List<Account> accountList=accountMapper.queryAccountByTypeId(2);
		System.out.println(accountList.size());
	}
	
	/**
	 * 根据用户id查询账目
	 */
	@Test
	public void testQueryAccountByUserId(){
		List<Account> accountList=accountMapper.queryAccountByUsersId(3);
		System.out.println(accountList.size());
	}
	
	/**
	 * 测试添加账目
	 */
	@Test
	public void testAddAccount(){
		//查询用户
		Users users=usersMapper.queryUsersById(3);
		//查询类型
		AccountType accountType=accountTypeMapper.queryAccountTypeById(2);
		//创建账目对象
		Account account=new Account();
		account.setUsers(users);
		account.setAccountType(accountType);
		account.setAccountName("中午吃饭");
		account.setMoney(13);
		account.setStatus(0);
		account.setCreateTime(new Date());
		//添加
		int result=accountMapper.addAccount(account);
		System.out.println(result>0?"添加成功":"添加失败");
	}
	
	@Test
	public void testQueryPageAccount(){
		PageBean<Account> pageBean=new PageBean<Account>();
		pageBean.setParamters("status", 0);
		pageBean.setParamters("user", 4);
		List<Account> accounts=accountMapper.queryPageAccount(pageBean);
		//获取总记录数
		int totalCount=accountMapper.queryAccountStatus(pageBean.getParamters());
		pageBean.setTotalCount(totalCount);
		pageBean.setShowRecords(accounts);
		System.out.println(pageBean.getCurrentPage()+"\t"+pageBean.getTotalPage());
		System.out.println(pageBean.getTotalCount());
	}
	
	@Test
	public void testQueryAccountStatus(){
		PageBean<Account> pageBean=new PageBean<Account>();
		pageBean.setParamters("status", 0);
		pageBean.setParamters("user", 3);
		//获取总记录数
		int totalCount=accountMapper.queryAccountStatus(pageBean.getParamters());
		System.out.println(totalCount);
	}
	
	@Test
	public void testDeleteAccount(){
		System.out.println(accountMapper.deleteAccount(1)>0?"删除成功":"删除失败");
	}
	
	@Test
	public void testQueryAccountById(){
		Account account=accountMapper.queryAccountById(1);
		System.out.println(null==account?"不存在":"存在");
	}
	
	@Test
	public void testQueryAccountByTypeIdNum(){
		int result=accountMapper.queryAccountByTypeIdNum(3);
		System.out.println(result);
	}
	
	@Test
	public void testQueryYearStatistics(){
		Map<String, Object> paramters=new HashMap<String, Object>();
		paramters.put("year", "2015");
		paramters.put("status", 0);
		
		List<Map<String, Object>> map=accountMapper.queryYearStatistics(paramters);
		for (Map<String, Object> map2 : map) {
			System.out.println(map2.get("month")+"\t"+map2.get("money"));
		}
	}
	
	@Test
	public void testQueryMonthStatistics(){
		Map<String, Object> paramters=new HashMap<String, Object>();
		paramters.put("month", "2015-01");
		paramters.put("status", 0);
		
		List<Map<String, Object>> map=accountMapper.queryMonthStatistics(paramters);
		System.out.println(map.size());
		for (Map<String, Object> map2 : map) {
			System.out.println(map2.get("day")+"\t"+map2.get("money"));
		}
	}
	
	@Test
	public void testQueryTypeStatistics(){
		Map<String, Object> paramters=new HashMap<String, Object>();
		paramters.put("month", "2015-01");
		paramters.put("status", 0);
		
		List<Map<String, Object>> map=accountMapper.queryTypeStatistics(paramters);
		System.out.println(map.size());
		for (Map<String, Object> map2 : map) {
			System.out.println(map2.get("typeName")+"\t"+map2.get("money"));
		}
	}
}
