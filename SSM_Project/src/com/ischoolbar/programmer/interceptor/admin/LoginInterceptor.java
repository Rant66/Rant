package com.ischoolbar.programmer.interceptor.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest res, HttpServletResponse resp, Object arg2) throws Exception {
		
		String requestURI = res.getRequestURI();
		Object admin = res.getSession().getAttribute("admin");
		if(admin==null)
		{
			String header = res.getHeader("X-Requested-With");
			//判断是否时ajax请求
			if("XMLHttpRequest".equals(header))
			{
				Map<String, String> ret=new HashMap<String, String>();
				ret.put("type","error");
				ret.put("msg", "请求超时或还未登陆，请重新登陆");
				resp.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			resp.sendRedirect(res.getServletContext().getContextPath()+"/system/login");
			return false;
		}
		return true;
	}

}
