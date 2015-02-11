package com.tenghu.financial.mapper;

import java.util.List;

import com.tenghu.financial.model.Role;
import com.tenghu.financial.model.page.PageBean;

/**
 * 角色Mapper
 * @author Arvin_Li
 *
 */
public interface RoleMapper {
	
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> queryPageRole(PageBean<Role> pageBean);
	
	/**
	 * 根据角色id查询角色
	 * @param id
	 * @return
	 */
	public Role queryRoleById(int id);
	
	/**
	 * 统计角色个数
	 * @return
	 */
	public int queryRoleNum();
	
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	public int updateRoleById(Role role);
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	public int addRole(Role role);
	
	/**
	 * 删除角色
	 * @param rId
	 * @return
	 */
	public int deleteRole(int rId);
	
	/**
	 * 获取所有角色
	 * @return
	 */
	public List<Role> queryRoleList();
}
