package com.tenghu.financial.service;

import java.util.List;

import com.tenghu.financial.model.Role;
import com.tenghu.financial.model.page.PageBean;

/**
 * 角色服务接口
 * @author Arvin_Li
 *
 */
public interface IRoleService {
	
	/**
	 * 根据角色id查询角色对象
	 * @return
	 */
	public Role queryRoleById(int id);
	
	
	/**
	 * 分页查询角色
	 * @param pageBean
	 * @return
	 */
	public PageBean<Role> queryPageRole(PageBean<Role> pageBean);
	
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	public String updateRoleById(Role role);
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	public String addRole(Role role);
	
	/**
	 * 删除角色
	 * @param rId
	 * @return
	 */
	public String deleteRole(int rId);
	
	/**
	 * 获取所有角色
	 * @return
	 */
	public List<Role> queryRoleList();
}
