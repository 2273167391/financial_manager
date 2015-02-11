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
import com.tenghu.financial.model.page.PageBean;
import com.tenghu.financial.service.IAuthorityService;
import com.tenghu.financial.utils.JsonMenuUtil;
import com.tenghu.financial.utils.JsonMessageUtil;

/**
 * 权限控制器
 * @author Arvin_Li
 *
 */
@Controller
@RequestMapping(value="/auth")
public class AuthorityController {
	@Autowired
	private IAuthorityService authorityService;
	
	/**
	 * 获取所有权限
	 * @return
	 */
	@RequestMapping(value="/all")
	@ResponseBody
	public String getAuthorutyAll(){
		//获取所有权限
		List<Authority> authList=authorityService.queryAllAuthority();
		return JsonMenuUtil.getJsonMenu(authList);
	}
	
	/**
	 * 获取权限
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public PageBean<Authority> getAuthList(@ModelAttribute("page") PageBean<Authority> pageBean){
		return authorityService.queryAuthorityPage(pageBean);
	}
	
	/**
	 * 添加权限
	 * @param auth
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value="/add/{parentId}",method=RequestMethod.POST)
	@ResponseBody
	public String addAuth(Authority auth,@PathVariable("parentId") int parentId){
		return authorityService.addAuth(auth, parentId);
	}
	
	/**
	 * 删除权限
	 * @param authId
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	@ResponseBody
	public String deleteAuth(@PathVariable("id") int authId){
		return authorityService.deleteAuth(authId);
	}
	
	/**
	 * 修改权限
	 * @param auth
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public String updateAuth(Authority auth){
		return authorityService.updateAuth(auth);
	}
}
