package com.tenghu.financial.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenghu.financial.mapper.RoleMapper;
import com.tenghu.financial.mapper.UsersMapper;
import com.tenghu.financial.model.Role;
import com.tenghu.financial.model.page.PageBean;
import com.tenghu.financial.service.IRoleService;
import com.tenghu.financial.utils.JsonMessageUtil;

/**
 * 角色服务实现类
 * @author Arvin_Li
 *
 */

@Service
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UsersMapper usersMapper;

	@Override
	public Role queryRoleById(int id) {
		return roleMapper.queryRoleById(id);
	}

	@Override
	public PageBean<Role> queryPageRole(PageBean<Role> pageBean) {
		//获取角色
		List<Role> roleList=roleMapper.queryPageRole(pageBean);
		//获取总数
		int totalNum=roleMapper.queryRoleNum();
		//设置值
		pageBean.setShowRecords(roleList);
		pageBean.setTotalCount(totalNum);
		return pageBean;
	}

	@Override
	public String updateRoleById(Role role) {
		try {
			//修改
			int reuslt=roleMapper.updateRoleById(role);
			return reuslt>0?JsonMessageUtil.getSuccessJSON("修改成功"):JsonMessageUtil.getErrorJSON("修改失败");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonMessageUtil.getErrorJSON("系统异常，稍后再试!");
		}
	}

	@Override
	public String addRole(Role role) {
		//设置时间
		role.setCreateTime(new Date());
		try {
			//添加
			int result=roleMapper.addRole(role);
			return result>0?JsonMessageUtil.getSuccessJSON("添加成功"):JsonMessageUtil.getErrorJSON("添加失败");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonMessageUtil.getErrorJSON("系统异常，稍后再试!");
		}
	}

	@Override
	public String deleteRole(int rId) {
		try {
			//根据角色id查询用户
			int userNum=usersMapper.queryUsersByRoleId(rId).size();
			if(userNum>0){
				return JsonMessageUtil.getErrorJSON("该角色存在用户，删除失败！");
			}
			int result=roleMapper.deleteRole(rId);
			return result>0?JsonMessageUtil.getSuccessJSON("删除成功"):JsonMessageUtil.getErrorJSON("删除失败");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonMessageUtil.getErrorJSON("系统异常，稍后再试!");
		}
		
	}

	@Override
	public List<Role> queryRoleList() {
		return roleMapper.queryRoleList();
	}

}
