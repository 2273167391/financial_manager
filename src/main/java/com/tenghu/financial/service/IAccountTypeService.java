package com.tenghu.financial.service;

import java.util.List;

import com.tenghu.financial.model.AccountType;
import com.tenghu.financial.model.page.PageBean;

/**
 * 账目类型操作接口
 * @author Arvin_Li
 *
 */
public interface IAccountTypeService {
	/**
	 * 根据id查询账目类型
	 * @param typeId 类型id
	 * @return 账目类型
	 */
	public AccountType queryAccountTypeById(int typeId);
	
	/**
	 * 添加账目类型
	 * @param accountType 账目类型实体
	 * @return 添加结果
	 */
	public String addAccountType(AccountType accountType);
	
	/**
	 * 根据类型名称查询账目类型
	 * @param typeName 类型名称
	 * @return 账目类型
	 */
	public AccountType queryAccountTypeByName(String typeName);
	
	/**
	 * 根据类型id修改账目类型
	 * @param accountType 账目类型
	 * @return 修改结果
	 */
	public String updateAccountTypeById(AccountType accountType);
	
	/**
	 * 根据类型id删除账目类型
	 * @param atId 类型id
	 * @return 删除结果
	 */
	public String deleteAccountTypeById(int atId);
	
	/**
	 * 分页查询账目类型
	 * @param pageBean
	 * @return
	 */
	public PageBean<AccountType> queryPageAcountType(PageBean<AccountType> pageBean);
	
	/**
	 * 获取所有账目类型
	 * @return
	 */
	public List<AccountType> queryAccountTypeList();
}
