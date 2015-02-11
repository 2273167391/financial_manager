package com.tenghu.financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenghu.financial.context.ThreadContextHolder;
import com.tenghu.financial.model.Authority;
import com.tenghu.financial.model.Users;
import com.tenghu.financial.service.IAuthorityService;
import com.tenghu.financial.service.IUsersService;
import com.tenghu.financial.utils.JsonMenuUtil;

/**
 * 主页控制器
 * @author Arvin_Li
 *
 */
@Controller
@RequestMapping(value="/main")
public class MainController {
	
	@Autowired
	private IUsersService usersService;
	@Autowired
	private IAuthorityService authorityService;
	
	/**
	 * 获取Session中是否存在用户
	 * @return
	 */
	@RequestMapping(value="/get_current_user")
	@ResponseBody
	public Users getCurrentUsers(){
		return usersService.getCurrentUsers();
	}
	
	/**
	 * 获取菜单
	 * @return
	 */
	@RequestMapping(value="/get_menu")
	@ResponseBody
	public String getMenu(){
		Users users=usersService.getCurrentUsers();
		//获取用户的权限id
		String authid=users.getRole().getAuthIds();
		String[] authids=authid.split(",");
		//获取用户菜单
		List<Authority> authorityList=authorityService.queryAuthorityByIds(authids);
		return JsonMenuUtil.getJsonMenu(authorityList);
	}
	
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping(value="/sign_out")
	public String signOut(){
		ThreadContextHolder.destorySessionContext();
		return "redirect:/index.html";
	}
}
