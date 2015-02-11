package com.tenghu.financial.mapper;

import java.util.List;

import com.tenghu.financial.model.Authority;
import com.tenghu.financial.model.page.PageBean;

/**
 * 权限映射操作接口
 * @author Arvin_Li
 *
 */
public interface AuthorityMapper {
	/**
	 * 获取所有权限
	 * @return 权限集合
	 */
	public List<Authority> queryAllAuthority();
	
	/**
	 * 根据权限等级获取权限
	 * @param level
	 * @return 权限集合
	 */
	public List<Authority> queryAuthorityByLevel(int level);
	
	/**
	 * 根据权限id查询权限
	 * @param authId 权限id
	 * @return 权限对象
	 */
	public Authority queryAuthorityById(int authId);
	
	/**
	 * 根据父级id查询权限
	 * @param parentId 父级id
	 * @return 权限集合
	 */
	public List<Authority> queryChildAuthorityById(int parentId);
	
	/**
	 * 根据权限id数组查询权限
	 * @param ids id数组
	 * @return 权限集合
	 */
	public List<Authority> queryAuthorityByIds(String[] ids);
	
	/**
	 * 分页查询权限
	 * @param pageBean
	 * @return
	 */
	public List<Authority> queryAuthorityPage(PageBean<Authority> pageBean);
	
	/**
	 * 添加权限
	 * @param auth
	 * @return
	 */
	public int addAuth(Authority auth);
	
	/**
	 * 删除权限
	 * @param authId
	 * @return
	 */
	public int deleteAuth(int authId);
	
	/**
	 * 修改权限
	 * @param auth
	 * @return
	 */
	public int updateAuth(Authority auth);
}
