package com.tenghu.financial.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenghu.financial.mapper.AccountMapper;
import com.tenghu.financial.mapper.AccountTypeMapper;
import com.tenghu.financial.model.AccountType;
import com.tenghu.financial.model.page.PageBean;
import com.tenghu.financial.service.IAccountTypeService;
import com.tenghu.financial.utils.JsonMessageUtil;

/**
 * 账目类型服务实现类
 * @author Arvin_Li
 *
 */
@Service
public class AccountTypeServiceImpl implements IAccountTypeService{
	
	@Autowired
	private AccountTypeMapper accountTypeMapper;
	
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public AccountType queryAccountTypeById(int typeId) {
		return accountTypeMapper.queryAccountTypeById(typeId);
	}

	@Override
	public String addAccountType(AccountType accountType) {
		try {
			//判断类型是否存在
			if(null==accountTypeMapper.queryAccountTypeByName(accountType.getTypeName())){
				accountType.setCreateTime(new Date());
				//添加账目类型
				int result=accountTypeMapper.addAccountType(accountType);
				return result>0?JsonMessageUtil.getSuccessJSON("添加成功！"):JsonMessageUtil.getErrorJSON("添加失败！");
			}
			return JsonMessageUtil.getErrorJSON("账目类型已存在！");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonMessageUtil.getErrorJSON("系统异常，请稍候再试！");
		}
	}

	@Override
	public AccountType queryAccountTypeByName(String typeName) {
		return accountTypeMapper.queryAccountTypeByName(typeName);
	}

	@Override
	public String updateAccountTypeById(AccountType accountType) {
		try {
			//判断账目类型是否存在
			if(null!=accountTypeMapper.queryAccountTypeById(accountType.getAtId())){
				//修改
				int result=accountTypeMapper.updateAccountTypeById(accountType);
				return result>0?JsonMessageUtil.getSuccessJSON("修改成功！"):JsonMessageUtil.getErrorJSON("修改失败！");
			}
			return JsonMessageUtil.getErrorJSON("账目类型不存在！");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonMessageUtil.getErrorJSON("系统异常，请稍候再试！");
		}
	}

	@Override
	public String deleteAccountTypeById(int atId) {
		try {
			//判断账目类型是否存在
			if(null!=accountTypeMapper.queryAccountTypeById(atId)){
				//查询该类型id是否存在账目
				int accountNum=accountMapper.queryAccountByTypeIdNum(atId);
				if(accountNum<=0){
					//删除
					int result=accountTypeMapper.deleteAccountTypeById(atId);
					return result>0?JsonMessageUtil.getSuccessJSON("删除成功！"):JsonMessageUtil.getErrorJSON("删除失败！");
				}else{
					return JsonMessageUtil.getErrorJSON("该类型有账目记录，删除失败！");
				}
			}
			return JsonMessageUtil.getErrorJSON("账目类型不存在！");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonMessageUtil.getErrorJSON("系统异常，请稍候再试！");
		}
	}

	@Override
	public PageBean<AccountType> queryPageAcountType(
			PageBean<AccountType> pageBean) {
		//获取总数
		int totalNum=accountTypeMapper.queryAccountTypeNum();
		//查询
		List<AccountType> accountTypeList=accountTypeMapper.queryPageAccountType(pageBean);
		//添加到分页实体
		pageBean.setTotalCount(totalNum);
		pageBean.setShowRecords(accountTypeList);
		return pageBean;
	}

	@Override
	public List<AccountType> queryAccountTypeList() {
		return accountTypeMapper.queryAccountTypeList();
	}

}
