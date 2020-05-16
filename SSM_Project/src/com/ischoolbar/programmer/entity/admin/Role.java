package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 角色列表
 * @author Administrator
 *
 */
@Component
public class Role {

	
	private long id;
	
	private String name;
	
	private String remark;//角色备注

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
