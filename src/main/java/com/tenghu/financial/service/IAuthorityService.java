package com.tenghu.financial.service;

import java.util.List;

import com.tenghu.financial.model.Authority;
import com.tenghu.financial.model.page.PageBean;

/**
 * 权限服务接口
 * @author Arvin_Li
 *
 */
public interface IAuthorityService {
	/**
	 * 获取所有权限
	 * @return 权限集合
	 */
	public List<Authority> queryAllAuthority();
	
	/**
	 * 根据权限id获取权限
	 * @param id 权限id
	 * @return 权限对象
	 */
	public Authority queryAuthorityById(int id);
	
	/**
	 * 根据权限id数组获取权限
	 * @param ids id数组
	 * @return 权限集合
	 */
	public List<Authority> queryAuthorityByIds(String[] ids);
	
	/**
	 * 分页查询权限
	 * @param pageBean
	 * @return
	 */
	public PageBean<Authority> queryAuthorityPage(PageBean<Authority> pageBean);
	
	/**
	 * 添加权限
	 * @param auth
	 * @param parentId
	 * @return
	 */
	public String addAuth(Authority auth,int parentId);
	
	/**
	 * 删除权限
	 * @param authId
	 * @return
	 */
	public String deleteAuth(int authId);
	
	/**
	 * 修改权限
	 * @param auth
	 * @return
	 */
	public String updateAuth(Authority auth);
}
