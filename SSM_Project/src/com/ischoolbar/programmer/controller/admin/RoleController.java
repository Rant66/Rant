package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.util.StringUtil;
import com.ischoolbar.programmer.entity.admin.Menu;
import com.ischoolbar.programmer.entity.admin.Role;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.RoleService;


@RequestMapping("/role")
@Controller
public class RoleController {
	@Autowired
     private RoleService roleServiceImpl; 
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model)
	{
		model.setViewName("/role/list");
		return model;
	}
	
	@RequestMapping(value = "/show",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>getRoleList(Page page,@RequestParam(name ="name",required = true,defaultValue = "")String name)
	{
		Map<String, Object>ret=new HashMap<String, Object>();
		Map<String, Object>qureyMap=new HashMap<>();
		qureyMap.put("name",name);
		qureyMap.put("offset",page.getOffset());
		qureyMap.put("pageSize", page.getRows());
		List<Role> findList = roleServiceImpl.findList(qureyMap);
		ret.put("rows", findList);
		ret.put("total",roleServiceImpl.findTotal(qureyMap));
		return ret;
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>add(Role role)
	{
		Map<String, Object>ret=new HashMap<>();
		if(role==null)
		{
			ret.put("type","error");
			ret.put("msg","请填写正确的角色信息");
			return ret;
		}
		if(StringUtil.isEmpty(role.getName()))
		{
			ret.put("type", "error");
			ret.put("msg","请填写角色名称");
			return ret;
		}
		if(roleServiceImpl.add(role)<=0)
		{
			ret.put("type","error");
			ret.put("msg","添加失败");
			return ret;
		}
		ret.put("type","success");
		ret.put("msg", "添加成功");
		return ret;
	}
	
	@RequestMapping(value ="/edit",method = RequestMethod.POST)
	public Map<String, Object>edit(Role role)
	{
		Map<String, Object>ret=new HashMap<>();
		if(role==null)
		{
			ret.put("type", "error");
			ret.put("msg", "请选择正确的角色信息");
			return ret;
		}
		if(StringUtil.isEmpty(role.getName()))
		{
			ret.put("type", "error");
			ret.put("msg", "请选择角色名称");
			return ret;
		}
		if(roleServiceImpl.edit(role)<=0)
		{
			ret.put("type","error");
			ret.put("msg", "修改失败");
			return ret;
		}
		ret.put("type","error");
		ret.put("msg","修改成功");
		return ret;
	}
	
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String>delete(@RequestParam(name = "id",required = true)Long id)
	{
		Map<String, String>ret=new HashMap<String, String>();
		if(id==null)
		{
		    ret.put("type", "error");
		    ret.put("msg", "请选择要删除的菜单信息");
		    return ret;
		}
		try {
			if(roleServiceImpl.delete(id)<=0)
			{
				ret.put("type", "error");
				ret.put("msg", "删除失败");
				return ret;	
			}
		} 
		catch (Exception e) {
			// TODO: handle exception
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功");
		return ret;
	}

}
