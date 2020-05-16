package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.MenuDao;
import com.ischoolbar.programmer.entity.admin.Menu;
import com.ischoolbar.programmer.service.admin.MenuService;
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	@Override
	public int add(Menu menu) {
		// TODO Auto-generated method stub
		return menuDao.add(menu);
	}
	@Override
	public List<Menu> FindList(Map<String, Object> qureyMap) {
		// TODO Auto-generated method stub
		return menuDao.FindList(qureyMap);
	}
	@Override
	public List<Menu> findTopList() {
		// TODO Auto-generated method stub
		return menuDao.findTopList();
	}
	@Override
	public int findTotal(Map<String, Object> qureyMap) {
		// TODO Auto-generated method stub
		return menuDao.findTotal(qureyMap);
	}
	@Override
	public int edit(Menu menu) {
		// TODO Auto-generated method stub
		return menuDao.edit(menu);
	}
	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return menuDao.delete(id);
	}
	@Override
	public List<Menu> findChildList(Long id) {
		// TODO Auto-generated method stub
		return menuDao.findChildList(id);
	}

}
