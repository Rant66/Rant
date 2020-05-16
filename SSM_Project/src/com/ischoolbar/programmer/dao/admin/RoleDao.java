package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Role;
@Repository
public interface RoleDao {
	
	public int add(Role role);
	
	public int edit(Role role);
	
	public int delete(long id);
	
	public int findTotal(Map<String, Object> queryMap);
	
	public List<Role>findList(Map<String, Object> queryMap);

}
