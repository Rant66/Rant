package com.ischoolbar.programmer.controller.admin;

import com.github.pagehelper.util.StringUtil;
import com.ischoolbar.programmer.entity.admin.Menu;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private MenuService menuServiceImpl;
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public ModelAndView menu(ModelAndView model)
	{
		model.addObject("topList", menuServiceImpl.findTopList());
		model.setViewName("/menu/list");
		return model;
	}
	@RequestMapping(value = "/get_icons",method =RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>get_icons(HttpServletRequest res)
	{
		Map<String, Object> ret=new HashMap<String, Object>();
		String realPath = res.getServletContext().getRealPath("/");
		File file=new File(realPath+"\\resources\\admin\\easyui\\css\\icons");
		List<String> icons=new ArrayList<>();
		if(!file.exists())
		{
			ret.put("type","error");
			ret.put("msg","该文件不存在");
			return ret;
		}
	  File listfiles[]=file.listFiles();
	  for(File f:listfiles)
	  {
	  if(f!=null&&f.getName().contains(".png"))
	   {
		  icons.add("icon-"+f.getName().substring(0,f.getName().indexOf(".")).replace("_","-"));
	   }
	  }
		ret.put("type","success");
		ret.put("content",icons);
		return ret;
	}
	
	@RequestMapping(value = "/show",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object>getMenList(Page page,@RequestParam(name = "name",required = true,defaultValue = "")String name)
	{
		Map<String, Object>ret=new HashMap<>();
		Map<String, Object>qureyMap=new HashMap<>();
		qureyMap.put("offset",page.getOffset());
		qureyMap.put("pageSize",page.getRows());
		qureyMap.put("name",name);
		List<Menu> findList = menuServiceImpl.FindList(qureyMap);
		ret.put("rows",findList);
		ret.put("total",menuServiceImpl.findTotal(qureyMap));
		return ret;
	}
	/**
	 * 添加菜单信息
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String>add(Menu menu)
	{
		Map<String, String>ret=new HashMap<String, String>();
		if(menu==null)
		{
		    ret.put("type", "error");
		    ret.put("msg", "请填写正确的菜单信息");
		    return ret;
		}
		if(StringUtil.isEmpty(menu.getName()))
		{
			ret.put("type", "error");
			ret.put("msg", "请填写菜单名称");
			return ret;	
		}
		if(StringUtil.isEmpty(menu.getIcon()))
		{
			ret.put("type", "error");
			ret.put("msg", "请填写菜单图标类");
			return ret;	
		}
        if(menu.getParentId()==null)
        {
        	menu.setParentId(0l);
        }
		if(menuServiceImpl.add(menu)<0)
		{
			ret.put("type", "error");
			ret.put("msg", "添加失败");
			return ret;	
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功");
		return ret;
	}
	/**
	  * 修改菜单信息
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String>edit(Menu menu)
	{
		Map<String, String>ret=new HashMap<String, String>();
		if(menu==null)
		{
		    ret.put("type", "error");
		    ret.put("msg", "请选择正确的菜单信息");
		    return ret;
		}
		if(StringUtil.isEmpty(menu.getName()))
		{
			ret.put("type", "error");
			ret.put("msg", "请填写菜单名称");
			return ret;	
		}
		if(StringUtil.isEmpty(menu.getIcon()))
		{
			ret.put("type", "error");
			ret.put("msg", "请填写菜单图标类");
			return ret;	
		}
		if(menu.getParentId()==null)
	    {
	        	menu.setParentId(0l);
	     }
		if(menuServiceImpl.edit(menu)<0)
		{
			ret.put("type", "error");
			ret.put("msg", "修改失败");
			return ret;	
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功");
		return ret;
	}
	/**
	 * 删除菜单信息
	 * @param menu
	 * @return
	 */
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
		List<Menu> findChildList = menuServiceImpl.findChildList(id);
		if(findChildList!=null&&findChildList.size()>0)
		{
			ret.put("type", "error");
			ret.put("msg", "该分类下存在子分类，不能删除");
			return ret;	
		}
		if(menuServiceImpl.delete(id)<=0)
		{
			ret.put("type", "error");
			ret.put("msg", "删除失败");
			return ret;	
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功");
		return ret;
	}
	

}
