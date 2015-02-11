package com.tenghu.financial.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tenghu.financial.common.Constant;
import com.tenghu.financial.context.ThreadContextHolder;
import com.tenghu.financial.utils.RandomImageGenerator;

@SuppressWarnings("serial")
public class CodeServlet extends HttpServlet {
	
	private int width=0;//验证码宽度
	private int height=0;//验证码高度
	private int num=0;//验证码个数

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置编码
		request.setCharacterEncoding("utf-8");
		//获取Session
		HttpSession httpSession=request.getSession();
		//获取验证码字符串
		String randomStr=RandomImageGenerator.random(num);
		if(null!=httpSession){
			//将验证码字符串设置到session中
			ThreadContextHolder.getSessionContext().setAttribute(Constant.RANDOM_STR, randomStr);
			 //设置响应类型,输出图片客户端不缓存  
            response.setDateHeader("Expires", 1L);    
            response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");  
            response.addHeader("Pragma", "no-cache");  
            response.setContentType("image/jpeg");  
            //输出到页面  
            RandomImageGenerator.render(randomStr, response.getOutputStream(), width, height);  
		}
	}
	
	@Override
	public void init() throws ServletException {
		width=Integer.valueOf(this.getInitParameter("width"));
		height=Integer.valueOf(this.getInitParameter("height"));
		num=Integer.valueOf(this.getInitParameter("num"));
	}

}
