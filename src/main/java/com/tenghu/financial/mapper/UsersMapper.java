package com.tenghu.financial.mapper;

import java.util.List;

import com.tenghu.financial.model.Users;
import com.tenghu.financial.model.page.PageBean;

/**
 * 用户操作映射接口
 * @author Arvin_Li
 *
 */
public interface UsersMapper {
	/**
	 * 根据用户名查询用户个数
	 * @param userName 用户名
	 * @return 用户个数
	 */
	public Users queryUsersByUsernameNum(String userName);
	
	/**
	 * 添加用户
	 * @param users 用户对象
	 * @return 添加结果
	 */
	public int addUsers(Users users);
	
	/**
	 * 根据用户id查询用户
	 * @param id 用户id
	 * @return 用户对象
	 */
	public Users queryUsersById(int id);
	
	/**
	 * 根据角色id查询用户
	 * @param rId 角色id
	 * @return 用户集合
	 */
	public List<Users> queryUsersByRoleId(int rId);
	
	/**
	 * 修改用户
	 * @param users 用户对象
	 * @return
	 */
	public int updateUsers(Users users);
	
	/**
	 * 分页查询用户
	 * @param pageBean
	 * @return
	 */
	public List<Users> queryUserPage(PageBean<Users> pageBean);
	
	/**
	 * 获取用户人数
	 * @return
	 */
	public int queryUserNum();
}
