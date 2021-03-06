package com.ischoolbar.programmer.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.util.StringUtil;
import com.ischoolbar.programmer.entity.admin.User;
import com.ischoolbar.programmer.service.admin.UserService;
import com.ischoolbar.programmer.util.CpachaUtil;

@Controller
@RequestMapping("/system")
public class SystemController {
//	@RequestMapping("system/index")
//	public String index()
//	{
//		return "system/index";
//	}
//	@RequestMapping("system/index")
//	public ModelAndView index(ModelAndView model)
//	{
//		model.setViewName("system/index");
//		model.addObject("name", "小明");
//		return model;
//	}
	@Autowired
	private UserService userService;

	
	@RequestMapping("/login")
	public ModelAndView login(ModelAndView model)
	{
		model.setViewName("system/login");
		return model;
	}
	
	@RequestMapping("/main")
	public ModelAndView main(ModelAndView model)
	{
		model.setViewName("system/main");
		return model;
	}
	@RequestMapping("/welcome")
	public ModelAndView welcome(ModelAndView model)
	{
		model.setViewName("system/welcome");
		return model;
	}
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginAct(User user, String cpacha,HttpServletRequest request)
	{
		Map<String, String> ret=new HashMap<String, String>();
		if(user==null)
		{
			ret.put("type","error");
			ret.put("msg", "请填写用户 信息");
			return ret;
		}
		if(StringUtil.isEmpty(cpacha))
		{
			ret.put("type","error");
			ret.put("msg", "请填写验证码");
			return ret;
		}
		if(StringUtil.isEmpty(user.getUsername()))
		{
			ret.put("type","error");
			ret.put("msg", "请填写用户名");
			return ret;
		}
		if(StringUtil.isEmpty(user.getPassword()))
		{
			ret.put("type","error");
			ret.put("msg", "请填写密码");
			return ret;
		}
	    Object loginCpacha = request.getSession().getAttribute("loginCpacha");
		if(loginCpacha==null)
		{
			ret.put("type","error");
			ret.put("msg", "会话超时，请刷新页面！");
			return ret;
		}
		if(!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase()))
		{
			ret.put("type","error");
			ret.put("msg", "验证码错误！");
			return ret;
		}
		User selUsername = userService.show(user.getUsername());
		if(selUsername==null)
		{
			ret.put("type","error");
			ret.put("msg", "用户名不存在！");
			return ret;
		}
		if(!user.getPassword().equals(selUsername.getPassword()))
		{
			ret.put("type","error");
			ret.put("msg", "密码错误！");
			return ret;
		}
	    request.getSession().setAttribute("admin",selUsername);
	        ret.put("type","success");
			ret.put("msg", "登陆成功");
			return ret;

			
	}
	
	@RequestMapping("/get_cpacha")
	public  void generateCpacha(
			@RequestParam(name = "vl",required = false,defaultValue = "4") Integer vcodeLen,
			@RequestParam(name = "w",required = false,defaultValue = "100") Integer width,
			@RequestParam(name = "h",required = false,defaultValue = "30") Integer height,
			@RequestParam(name = "type",required = true,defaultValue = "loginCpacha") String cpachaType,
			HttpServletRequest request,
			HttpServletResponse response
			){
	    CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen, width, height);
	    String generatorVCode = cpachaUtil.generatorVCode();
	    request.getSession().setAttribute(cpachaType,generatorVCode);
	    BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
	    try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
}
