package com.tenghu.financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenghu.financial.model.Account;
import com.tenghu.financial.model.AccountType;
import com.tenghu.financial.model.page.PageBean;
import com.tenghu.financial.service.IAccountServer;
import com.tenghu.financial.service.IAccountTypeService;
import com.tenghu.financial.service.IUsersService;
/**
 * 账目控制器
 * @author Arvin_Li
 *
 */
@Controller
@RequestMapping(value="/account")
public class AccountController {
	
	@Autowired
	private IAccountServer accountServer;
	@Autowired
	private IAccountTypeService accountTypeService;
	@Autowired
	private IUsersService usersService;
	
	/**
	 * 获取收入管理
	 * @return
	 */
	@RequestMapping(value="/{status}/list",method=RequestMethod.POST)
	@ResponseBody
	public PageBean<Account> getAccountList(@ModelAttribute("page") PageBean<Account> pageBean,@PathVariable("status") String status){
		if("income".equals(status)){
			pageBean.setParamters("status", 1);//收入标识
		}else if("expenditure".equalsIgnoreCase(status)){
			pageBean.setParamters("status", 0);//支出入标识
		}
		pageBean.setParamters("user", usersService.getCurrentUsers().getuId());
		pageBean=accountServer.queryPageAccount(pageBean);
		return pageBean;
	}
	
	/**
	 * 删除账目
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	@ResponseBody
	public String deleteAccount(@PathVariable("id") int aId){
		return accountServer.deleteAccount(aId);
	}
	
	/**
	 * 根据账目id获取账目信息
	 * @param aId
	 * @return
	 */
	@RequestMapping(value="/info/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Account getAccountInfo(@PathVariable("id") int aId){
		return accountServer.queryAccountById(aId);
	}
	
	/**
	 * 分页查询账目类型
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/type/list",method=RequestMethod.POST)
	@ResponseBody
	public PageBean<AccountType> getAccountTypeList(@ModelAttribute("page") PageBean<AccountType> pageBean){
		return pageBean=accountTypeService.queryPageAcountType(pageBean);
	}
	
	/**
	 * 根据账目类型id查询
	 * @param atId
	 * @return
	 */
	@RequestMapping(value="/type/{id}",method=RequestMethod.GET)
	@ResponseBody
	public AccountType getAccountTypeInfo(@PathVariable("id") int atId){
		return accountTypeService.queryAccountTypeById(atId);
	}
	
	/**
	 * 添加账目类型
	 * @param accountType
	 * @return
	 */
	@RequestMapping(value="/type/add",method=RequestMethod.POST)
	@ResponseBody
	public String addAccountType(AccountType accountType){
		return accountTypeService.addAccountType(accountType);
	}
	
	/**
	 * 修改账目类型
	 * @param accountType
	 * @return
	 */
	
	@RequestMapping(value="/type/edit",method=RequestMethod.POST)
	@ResponseBody
	public String updateAccountType(AccountType accountType){
		return accountTypeService.updateAccountTypeById(accountType);
	}
	
	/**
	 * 删除账目类型
	 * @param atId
	 * @return
	 */
	@RequestMapping(value="/type/delete/{id}",method=RequestMethod.GET)
	@ResponseBody
	public String deleteAccountType(@PathVariable("id") int atId){
		return accountTypeService.deleteAccountTypeById(atId);
	}
	
	/**
	 * 获取所有账目类型
	 * @return
	 */
	@RequestMapping(value="/type/all/list",method=RequestMethod.GET)
	@ResponseBody
	public List<AccountType> getAccountTypeList(){
		return accountTypeService.queryAccountTypeList();
	}
	
	/**
	 * 添加账目
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String addAccount(@ModelAttribute("account") Account account,int typeId){
		
		//获取账目类型
		AccountType accountType=accountTypeService.queryAccountTypeById(typeId);
		account.setAccountType(accountType);
		//设置用户
		account.setUsers(usersService.getCurrentUsers());
		//添加
		return accountServer.addAccount(account);
	}
}
