package com.tenghu.financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenghu.financial.model.Authority;
import com.tenghu.financial.model.Role;
import com.tenghu.financial.model.page.PageBean;
import com.tenghu.financial.service.IAuthorityService;
import com.tenghu.financial.service.IRoleService;
import com.tenghu.financial.utils.JsonMenuUtil;

/**
 * 角色控制器
 * @author Arvin_Li
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IAuthorityService authorityService;
	
	/**
	 * 角色列表
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public PageBean<Role> roleList(@ModelAttribute("page") PageBean<Role> pageBean){
		return roleService.queryPageRole(pageBean);
	}
	
	/**
	 * 角色权限
	 * @return
	 */
	@RequestMapping(value="/auth/{rId}")
	@ResponseBody
	public String roleAuth(@PathVariable("rId") int rId){
		//根据角色id查询角色
		Role role=roleService.queryRoleById(rId);
		//获取权限
		String[] auths=role.getAuthIds().split(",");
		//获取权限列表
		List<Authority> authList=authorityService.queryAuthorityByIds(auths);
		//转为JSON数据
		return JsonMenuUtil.getJsonMenu(authList);
	}
	
	/**
	 * 获取角色
	 * @param rId 角色id
	 * @return
	 */
	@RequestMapping(value="/info/{rId}")
	@ResponseBody
	public Role getRole(@PathVariable("rId") int rId){
		//根据角色id查询角色
		return roleService.queryRoleById(rId);
	}
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String addRole(@ModelAttribute("role") Role role){
		return roleService.addRole(role);
	}
	
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public String updateRole(@ModelAttribute("role") Role role){
		return roleService.updateRoleById(role);
	}
	
	/**
	 * 删除角色
	 * @param rId
	 * @return
	 */
	@RequestMapping(value="/delete/{id}")
	@ResponseBody
	public String deleteRole(@PathVariable("id") int rId){
		return roleService.deleteRole(rId);
	}
	
	/**
	 * 获取所有角色
	 * @return
	 */
	@RequestMapping(value="/all")
	@ResponseBody
	public List<Role> queryRoleList(){
		return roleService.queryRoleList();
	}
}
