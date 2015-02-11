package com.tenghu.financial.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenghu.financial.context.ThreadContextHolder;
import com.tenghu.financial.mapper.UsersMapper;
import com.tenghu.financial.model.Users;
import com.tenghu.financial.model.page.PageBean;
import com.tenghu.financial.service.IUsersService;
import com.tenghu.financial.utils.JsonMessageUtil;
import com.tenghu.financial.utils.SecurityPwdUtil;

/**
 * 用户服务实现类
 * @author Arvin_Li
 *
 */
@Service
public class UsersServiceImpl implements IUsersService{
	
	@Autowired
	private UsersMapper usersMapper;

	@Override
	public Users queryUsersByUsername(String userName) {
		return usersMapper.queryUsersByUsernameNum(userName);
	}

	@Override
	public Users queryUsersById(int id) {
		return usersMapper.queryUsersById(id);
	}

	@Override
	public String addUsers(Users users) {
		//根据用户名查询是否有用户
		Users oldUsers=queryUsersByUsername(users.getUserName());
		if(null==oldUsers){
			//添加用户
			int result=usersMapper.addUsers(users);
			return result>0?"恭喜，添加成功!":"抱歉，添加失败！";
		}else{
			return "该用户已存在，换个用户名试试？";
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String userLogin(String userName, String password) {
		//获取HttpServletRequest
		HttpServletRequest httpRequest=ThreadContextHolder.getHttpRequest();
		//根据用户名获取用户
		Users users=usersMapper.queryUsersByUsernameNum(userName);
		if(null!=users){
			//获取用户名和密码盐
			String oldPwd=users.getPassword();
			String salt=users.getSalt();
			//判断密码是否正确
			boolean isTrue=SecurityPwdUtil.authenticate(password, oldPwd, salt);
			if(isTrue){
				//修改登录时间
				users.setLoginTime(new Date());
				//登录IP
				users.setIp(httpRequest.getServerName());
				//修改用户
				usersMapper.updateUsers(users);
				//将用户存入Session中
				ThreadContextHolder.getSessionContext().setAttribute("user", users);
				return JsonMessageUtil.getSuccessJSON("登录成功！");
			}
		}
		return JsonMessageUtil.getErrorJSON("用户名或密码错误！");
	}

	@Override
	public String updateUsers(Users users) {
		//根据用户名查询是否有用户
		Users oldUsers=queryUsersById(users.getuId());
		if(null!=oldUsers){
			int result=usersMapper.updateUsers(users);
			return result>0?JsonMessageUtil.getSuccessJSON("修改成功！"):JsonMessageUtil.getErrorJSON("修改失败！");
		}
		return JsonMessageUtil.getErrorJSON("用户不存在，修改失败！");
	}

	@Override
	public Users getCurrentUsers() {
		//获取用户
		Users users=(Users) ThreadContextHolder.getSessionContext().getAttribute("user");
		return null!=users?users:null;
	}

	@Override
	public PageBean<Users> queryUserPage(PageBean<Users> pageBean) {
		//获取用户总数
		int totalCount=usersMapper.queryUserNum();
		//获取用户
		List<Users> usersList=usersMapper.queryUserPage(pageBean);
		//设置分页参数
		pageBean.setShowRecords(usersList);
		pageBean.setTotalCount(totalCount);
		return pageBean;
	}
}
