package com.tenghu.financial.service;

import java.util.List;
import java.util.Map;

import com.tenghu.financial.model.Account;
import com.tenghu.financial.model.page.PageBean;

/**
 * 账目服务接口
 * @author Arvin_Li
 *
 */
public interface IAccountServer {
	/**
	 * 根据用户id查询账目
	 * @param uId 用户id
	 * @return 账目集合
	 */
	public List<Account> queryAccountByUsersId(int uId);
	
	/**
	 * 根据账目id查询账目
	 * @param aId
	 * @return
	 */
	public Account queryAccountById(int aId);
	
	/**
	 * 添加账目
	 * @param account 账目实体
	 * @return 添加结果
	 */
	public String addAccount(Account account);
	
	/**
	 * 分页查询
	 * @param pageBean
	 * @return
	 */
	public PageBean<Account> queryPageAccount(PageBean<Account> pageBean);
	
	/**
	 * 删除账目
	 * @param aId
	 * @return
	 */
	public String deleteAccount(int aId);
	
	/**
	 * 年度统计
	 * @param paramters
	 * @return
	 */
	public List<Map<String, Object>> queryYearStatistics(Map<String, Object> paramters);
	/**
	 * 按月统计
	 * @param paramters
	 * @return
	 */
	public List<Map<String, Object>> queryMonthStatistics(Map<String, Object> paramters);
	/**
	 * 分类统计
	 * @param paramters
	 * @return
	 */
	public List<Map<String, Object>> queryTypeStatistics(Map<String, Object> paramters);
}
