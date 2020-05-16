package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Menu;

@Repository
public interface MenuDao {

	public int add(Menu menu);
	
	public List<Menu> FindList(Map<String, Object> queryMap);
	
	public List<Menu>findTopList();
	
	public int findTotal(Map<String, Object> queryMap);
	
	public int edit(Menu menu);
	
	public int delete(Long id);
	
	public List<Menu>findChildList(Long id);
}
