package com.tenghu.financial.service;

import com.tenghu.financial.model.Users;
import com.tenghu.financial.model.page.PageBean;

/**
 * 用户服务接口
 * @author Arvin_Li
 *
 */
public interface IUsersService {
	/**
	 * 根据用户名查询用户对象
	 * @param userName 用户名
	 * @return 用户对象
	 */
	public Users queryUsersByUsername(String userName);
	
	/**
	 * 根据用户id查询用户对象
	 * @param id 用户id
	 * @return 用户对象
	 */
	public Users queryUsersById(int id);
	
	/**
	 * 添加用户
	 * @param users
	 * @return
	 */
	public String addUsers(Users users);
	
	/**
	 * 修改用户
	 * @param users
	 * @return
	 */
	public String updateUsers(Users users);
	
	/**
	 * 用户登录
	 * @param userName 用户名
	 * @param password 密码
	 * @return
	 */
	public String userLogin(String userName,String password);
	/**
	 * 获取当前登录用户
	 * @return 用户
	 */
	public Users getCurrentUsers();
	
	/**
	 * 分页查询
	 * @param pageBean
	 * @return
	 */
	public PageBean<Users> queryUserPage(PageBean<Users> pageBean);
	
	/**
	 * 删除用户
	 * @param uId
	 * @return
	 */
	public String deleteUser(int uId);
	
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	public String registerUser(Users user);
}
