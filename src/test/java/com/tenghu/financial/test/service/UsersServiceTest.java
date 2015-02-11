package com.tenghu.financial.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tenghu.financial.model.Users;
import com.tenghu.financial.service.IUsersService;
import com.tenghu.financial.test.BasicTest;

/**
 * 用户服务类测试
 * @author Arvin_Li
 *
 */
public class UsersServiceTest extends BasicTest{
	@Autowired
	private IUsersService usersService;
	
	@Test
	public void testUpdateUsersService(){
		Users users=usersService.queryUsersByUsername("admin");
		users.setPhone("13289878765");
		System.out.println(usersService.updateUsers(users));
	}
}
