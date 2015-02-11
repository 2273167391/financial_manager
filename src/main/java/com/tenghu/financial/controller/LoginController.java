package com.tenghu.financial.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenghu.financial.common.Constant;
import com.tenghu.financial.context.ThreadContextHolder;
import com.tenghu.financial.service.IUsersService;
import com.tenghu.financial.utils.JsonMessageUtil;
import com.tenghu.financial.utils.cookie.CookieUtil;

/**
 * 登录控制器
 * @author Arvin_Li
 *
 */
@Controller
@RequestMapping(value="/index")
public class LoginController {
	
	@Autowired
	private IUsersService usersService;
	
	/**
	 * 获取Cookie的值
	 * @param httpRequest
	 * @return
	 */
	@RequestMapping(value="/get_cookie")
	@ResponseBody
	public String getCookie(HttpServletRequest httpRequest){
		//获取Cookie中的值
		String userName=CookieUtil.getCookieValue("username");
		return "{\"username\":\""+userName+"\"}";
	}
	
	/**
	 * 用户登录
	 * @param userName 用户名
	 * @param password 密码
	 * @param code 验证码
	 * @param rem 是否记住我
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam(value="username") String userName,String password,String code,String rem){
		try {
			//如果为记住我
			if("1".equals(rem)){
				//将用户名和密码存入到Cookie中
				CookieUtil.setCookie("username", userName);
			}else{
				//将Cookie中的用户名密码删除
				CookieUtil.removeCookie("username");
			}
			//获取Session中的验证码
			String oldCode=(String) ThreadContextHolder.getSessionContext().getAttribute(Constant.RANDOM_STR);
			if(null!=oldCode&&oldCode.trim().equals(code.trim())){
				//登录用户
				return usersService.userLogin(userName, password);
			}
			return JsonMessageUtil.getErrorJSON("验证码错误");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonMessageUtil.getErrorJSON("系统异常，请稍候再试...");
		}
	}
}
