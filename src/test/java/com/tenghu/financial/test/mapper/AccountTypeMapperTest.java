package com.tenghu.financial.test.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tenghu.financial.mapper.AccountTypeMapper;
import com.tenghu.financial.model.AccountType;
import com.tenghu.financial.model.page.PageBean;
import com.tenghu.financial.test.BasicTest;

/**
 * 账目类型映射测试
 * @author Arvin_Li
 *
 */
public class AccountTypeMapperTest extends BasicTest{
	
	@Autowired
	private AccountTypeMapper accountTypeMapper;
	
	/**
	 * 测试根据类型id查询类型
	 */
	@Test
	public void testQueryAccountById(){
		AccountType accountType=accountTypeMapper.queryAccountTypeById(2);
		System.out.println(accountType.getAccountList());
	}
	
	/**
	 * 测试添加账目类型
	 */
	@Test
	public void testAddAccountType(){
		//创建账目类型
		AccountType accountType=new AccountType();
		accountType.setTypeName("投资理财");
		//判断是否存在类型
		if(null==accountTypeMapper.queryAccountTypeByName(accountType.getTypeName())){
			//添加账目类型
			int result=accountTypeMapper.addAccountType(accountType);
			System.out.println(result>0?"添加成功":"添加失败");
		}else{
			System.out.println("账目类型已经存在，赶快去记上一笔吧！");
		}
	}
	
	/**
	 * 测试修改账目类型
	 */
	@Test
	public void testUpdateAccountTypeById(){
		//创建账目实体
		AccountType accountType=new AccountType();
		accountType.setAtId(1);
		accountType.setTypeName("日常生活");
		//判断是否存在该账目类型
		if(null!=accountTypeMapper.queryAccountTypeById(accountType.getAtId())){
			//修改
			int result=accountTypeMapper.updateAccountTypeById(accountType);
			System.out.println(result>0?"修改成功":"修改失败");
		}else{
			System.out.println("不存在该账目类型，添加一条可否？");
		}
	}
	
	/**
	 * 根据类型id删除账目类型
	 */
	@Test
	public void testDeleteAccountTypeById(){
		//判断是否存在该账目类型
		if(null!=accountTypeMapper.queryAccountTypeById(1)){
			//修改
			int result=accountTypeMapper.deleteAccountTypeById(1);
			System.out.println(result>0?"删除成功":"删除失败");
		}else{
			System.out.println("不存在该账目类型，添加一条可否？");
		}
	}
	
	/**
	 * 分页查询账目类型
	 */
	@Test
	public void testQueryPageAccountType(){
		PageBean<AccountType> pageBean=new PageBean<AccountType>();
		pageBean.setCurrentPage(1);
		pageBean.setPageSize(2);
		
		List<AccountType> accountTypes=accountTypeMapper.queryPageAccountType(pageBean);
		int totalNum=accountTypeMapper.queryAccountTypeNum();
		System.out.println(accountTypes.size());
		System.out.println(totalNum);
	}
}
