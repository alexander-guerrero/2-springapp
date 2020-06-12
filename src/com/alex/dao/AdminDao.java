package com.alex.dao;

import java.util.List;

import com.alex.pojo.Admin;

public interface AdminDao {
	
	public boolean save(Admin admin);
	public List<Admin> findAll();

}
