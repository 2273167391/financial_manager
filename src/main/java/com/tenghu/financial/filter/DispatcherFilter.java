package com.tenghu.financial.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tenghu.financial.context.ThreadContextHolder;
import com.tenghu.financial.model.Users;

/**
 * 中央过滤器
 * @author Arvin_Li
 *
 */
public class DispatcherFilter implements Filter{

	@Override
	public void destroy() {
		
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//获取HttpServetRequest
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		//获取HttpServletResponse
		HttpServletResponse httpResponse=(HttpServletResponse) response;
		
		//将HttpServletRequest,HttpServletResponse,HttpSession设置到本地线程中
		ThreadContextHolder.setHttpRequest(httpRequest);
		ThreadContextHolder.setHttpResponse(httpResponse);
		ThreadContextHolder.getSessionContext().setHttpSession(httpRequest.getSession());
		//获取请求部分URL
		String uri=httpRequest.getServletPath();
		if(uri.indexOf("/index")<0&&uri.indexOf("/resources")<0&&uri.indexOf("register")<0){
			//获取HttpSession中的用户
			Users users=(Users) ThreadContextHolder.getSessionContext().getAttribute("user");
			if(null==users){
				httpResponse.setHeader("Content-Type", "text/html;charset=UTF-8");
				httpResponse.getWriter().print("<script>alert('抱歉，您未登录或登录已超时，请重新登录!');location.href='index.html'</script>");
				return;
			}
		}
		//交给下一个过滤器
		chain.doFilter(httpRequest, httpResponse);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
