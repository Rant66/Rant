package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Role;

@Service
public interface RoleService {
	
	public int add(Role role); 
	
	public int edit(Role role);
	
	public int delete(long id);
	
	public int findTotal(Map<String, Object> queryMap);
	
	public List<Role>findList(Map<String, Object> queryMap);

}
