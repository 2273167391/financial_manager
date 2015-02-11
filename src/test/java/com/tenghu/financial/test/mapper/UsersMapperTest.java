package com.tenghu.financial.test.mapper;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tenghu.financial.mapper.RoleMapper;
import com.tenghu.financial.mapper.UsersMapper;
import com.tenghu.financial.model.Role;
import com.tenghu.financial.model.Users;
import com.tenghu.financial.test.BasicTest;
import com.tenghu.financial.utils.SecurityPwdUtil;

public class UsersMapperTest extends BasicTest{
	
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 测试根据用户名获取用户个数
	 */
	@Test
	public void testQueryUsersByUsernameNum(){
		System.out.println(usersMapper.queryUsersByUsernameNum("admin"));
	}
	
	/**
	 * 测试添加用户
	 */
	@Test
	public void testAddUsers(){
		//获取密码盐
		String salt=SecurityPwdUtil.generateSale();
		//创建用户对象
		Users users=new Users();
		users.setUserName("zhangsan");
		users.setPassword(SecurityPwdUtil.getSecurityPassword("admin", salt));
		users.setSalt(salt);
		users.setTrueName("王峰");
		users.setCreateTime(new Date());
		//查询用户是否存在
		if(null==usersMapper.queryUsersByUsernameNum(users.getUserName())){
			//获取角色
			Role role=roleMapper.queryRoleById(2);
			//设置角色
			users.setRole(role);
			//添加用户
			int result=usersMapper.addUsers(users);
			System.out.println(result>0?"添加成功":"添加失败");
		}else{
			System.out.println("该用户已存在，换个用户名试试？");
		}
	}
	
	/**
	 * 测试根据用户id查询用户
	 */
	@Test
	public void testQueryUsersById(){
		Users users=usersMapper.queryUsersById(3);
		System.out.println(users.getAccountList().size());
	}
	
	/**
	 * 修改用户
	 */
	@Test
	public void testUpdateUsers(){
		Users users=usersMapper.queryUsersByUsernameNum("admin");
		if(null!=users){
			users.setProvince("广东省");
			users.setCity("广州市");
			users.setRegion("天河区");
			users.setAddress("棠德一路");
			users.setHeadImg("default_head.png");
			users.setIp("127.0.0.1");
			System.out.println(usersMapper.updateUsers(users)>0?"修改成功！":"修改失败！");
		}else{
			System.out.println("用户不存在，修改失败");
		}
	}
}
