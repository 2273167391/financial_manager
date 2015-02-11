package com.tenghu.financial.test.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tenghu.financial.mapper.AuthorityMapper;
import com.tenghu.financial.mapper.RoleMapper;
import com.tenghu.financial.model.Authority;
import com.tenghu.financial.model.Role;
import com.tenghu.financial.model.Users;
import com.tenghu.financial.model.page.PageBean;
import com.tenghu.financial.test.BasicTest;

public class RoleMapperTest extends BasicTest{
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private AuthorityMapper authorityMapper;

	/**
	 * 测试角色下用户个数
	 */
	@Test
	public void testRoleCount(){
		Role role=roleMapper.queryRoleById(1);
		List<Users> userList=role.getUsersList();
		for (Users users : userList) {
			System.out.println(role.getRoleName()+"\t"+users.getUserName());
		}
	}
	
	/**
	 * 测试根据角色id获取角色
	 */
	@Test
	public void testQueryRoleById(){
		Role role=roleMapper.queryRoleById(1);
		System.out.println(role.getrId()+"\t"+role.getRoleName()+"\t"+role.getAuthIds());
	}
	
	/**
	 * 测试查询角色权限
	 */
	@Test
	public void testQueryRoleAuthority(){
		//获取角色
		Role role=roleMapper.queryRoleById(2);
		//将权限id拆分到数组
		String[] ids=role.getAuthIds().split(",");
		//查询权限集合
		List<Authority> authorityList=authorityMapper.queryAuthorityByIds(ids);
		System.out.println(authorityList.size());
	}
	
	@Test
	public void testQueryPageRole(){
		PageBean<Role> pageBean=new PageBean<Role>();
		pageBean.setCurrentPage(1);
		List<Role> roleList=roleMapper.queryPageRole(pageBean);
		//获取总数
		int totleNum=roleMapper.queryRoleNum();
		System.out.println(totleNum);
	}
}
