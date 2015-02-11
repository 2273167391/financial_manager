package com.tenghu.financial.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenghu.financial.mapper.AccountMapper;
import com.tenghu.financial.model.Account;
import com.tenghu.financial.model.page.PageBean;
import com.tenghu.financial.service.IAccountServer;
import com.tenghu.financial.utils.JsonMessageUtil;
/**
 * 账目服务实现类
 * @author Arvin_Li
 *
 */
@Service
public class AccountServerImpl implements IAccountServer{
	
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public List<Account> queryAccountByUsersId(int uId) {
		return accountMapper.queryAccountByUsersId(uId);
	}

	@Override
	public String addAccount(Account account) {
		try {
			//添加账目
			int result=accountMapper.addAccount(account);
			return result>0?JsonMessageUtil.getSuccessJSON("添加成功！"):JsonMessageUtil.getErrorJSON("添加失败！");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonMessageUtil.getErrorJSON("系统异常，请稍后再试！");
		}
	}
	
	
	/**
	 * 分页查询
	 */
	public PageBean<Account> queryPageAccount(PageBean<Account> pageBean){
		//获取总记录数
		int totalCount=accountMapper.queryAccountStatus(pageBean.getParamters());
		//查询
		List<Account> accountList=accountMapper.queryPageAccount(pageBean);
		//将查询出的数据设置到分页实体中
		pageBean.setShowRecords(accountList);
		pageBean.setTotalCount(totalCount);
		return pageBean;
	}

	@Override
	public String deleteAccount(int aId) {
		//获取账目
		Account account=accountMapper.queryAccountById(aId);
		if(null!=account){
			//执行删除
			int result=accountMapper.deleteAccount(aId);
			return result>0?JsonMessageUtil.getSuccessJSON("删除成功"):JsonMessageUtil.getErrorJSON("删除失败");
		}
		return JsonMessageUtil.getErrorJSON("账目不存在，删除失败！");
	}

	@Override
	public Account queryAccountById(int aId) {
		return accountMapper.queryAccountById(aId);
	}

	@Override
	public List<Map<String, Object>> queryYearStatistics(
			Map<String, Object> paramters) {
		return accountMapper.queryYearStatistics(paramters);
	}

	@Override
	public List<Map<String, Object>> queryMonthStatistics(
			Map<String, Object> paramters) {
		return accountMapper.queryMonthStatistics(paramters);
	}

	@Override
	public List<Map<String, Object>> queryTypeStatistics(
			Map<String, Object> paramters) {
		return accountMapper.queryTypeStatistics(paramters);
	}

}
