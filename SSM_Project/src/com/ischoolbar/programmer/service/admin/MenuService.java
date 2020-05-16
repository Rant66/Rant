package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Menu;


@Service
public interface MenuService {
	
	public int add(Menu menu);
	
	public List<Menu> FindList(Map<String, Object> qureyMap);
	
	public List<Menu> findTopList();
	
	public int findTotal(Map<String, Object> qureyMap);
	
	public int edit(Menu menu);
	
	public int delete(long id);
	
	public List<Menu>findChildList(Long id);

}
