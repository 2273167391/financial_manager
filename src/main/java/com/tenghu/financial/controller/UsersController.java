package com.tenghu.financial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenghu.financial.model.Role;
import com.tenghu.financial.model.Users;
import com.tenghu.financial.model.page.PageBean;
import com.tenghu.financial.service.IRoleService;
import com.tenghu.financial.service.IUsersService;
import com.tenghu.financial.utils.JsonMessageUtil;
import com.tenghu.financial.utils.SecurityPwdUtil;

/**
 * 用户控制器
 * @author Arvin_Li
 *
 */
@Controller
@RequestMapping(value="/user")
public class UsersController {
	
	@Autowired
	private IUsersService usersService;
	@Autowired
	private IRoleService roleService;
	/**
	 * 获取用户信息
	 * @return
	 */
	@RequestMapping(value="/get_user_info/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Users getUserInfo(@PathVariable("id") int userId){
		return usersService.queryUsersById(userId);
	}
	
	@RequestMapping(value="/update_pwd",method=RequestMethod.POST)
	@ResponseBody
	public String updatePwd(String oldPwd,String newPwd){
		//获取当前用户
		Users currentUser=usersService.getCurrentUsers();
		if(SecurityPwdUtil.authenticate(oldPwd, currentUser.getPassword(), currentUser.getSalt())){
			//获取密码盐
			String salt=SecurityPwdUtil.generateSale();
			currentUser.setPassword(SecurityPwdUtil.getSecurityPassword(newPwd, salt));//设置密码
			currentUser.setSalt(salt);//设置密码盐
			return usersService.updateUsers(currentUser);
		}else{
			return JsonMessageUtil.getErrorJSON("原密码错误，修改失败！");
		}
	}
	
	/**
	 * 查询用户
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public PageBean<Users> queryUserPage(@ModelAttribute("page") PageBean<Users> pageBean){
		return usersService.queryUserPage(pageBean);
	}
	
	/**
	 * 更新用户
	 * @param user
	 * @param rId
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public String updateUser(Users user,int rId){
		//根据角色id查询角色
		Role role=roleService.queryRoleById(rId);
		//设置用户角色
		user.setRole(role);
		//更新用户
		return usersService.updateUsers(user);
	}
	
	@RequestMapping(value="/delete/{id}")
	@ResponseBody
	public String deleteUser(@PathVariable("id") int uId){
		return usersService.deleteUser(uId);
	}
}
