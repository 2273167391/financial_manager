package com.tenghu.financial.mapper;

import java.util.List;
import java.util.Map;

import com.tenghu.financial.model.Account;
import com.tenghu.financial.model.page.PageBean;

/**
 * 账目操作接口
 * @author Arvin_Li
 *
 */
public interface AccountMapper {
	/**
	 * 根据类型id查询账目
	 * @param typeId 类型id
	 * @return 账目集合
	 */
	public List<Account> queryAccountByTypeId(int typeId);
	/**
	 * 根据类型id查询账目记录数
	 * @param typeId
	 * @return
	 */
	public int queryAccountByTypeIdNum(int typeId);
	
	/**
	 * 根据用户id查询账目
	 * @param uId 用户id
	 * @return 账目集合
	 */
	public List<Account> queryAccountByUsersId(int uId);
	
	/**
	 * 添加账目
	 * @param account 账目实体
	 * @return 添加结果
	 */
	public int addAccount(Account account);
	
	/**
	 * 分页查询
	 * @param pageBean
	 * @return
	 */
	public List<Account> queryPageAccount(PageBean<Account> pageBean);
	
	/**
	 * 根据账目id查询账目
	 * @param aId
	 * @return
	 */
	public Account queryAccountById(int aId);
	
	/**
	 * 查询账目状态总记录数
	 * @param status
	 * @return
	 */
	public int queryAccountStatus(Map<String, Object> paramters);
	
	/**
	 * 删除账目
	 * @param aId
	 * @return
	 */
	public int deleteAccount(int aId);
	
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
