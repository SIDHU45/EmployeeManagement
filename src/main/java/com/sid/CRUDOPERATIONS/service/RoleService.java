package com.sid.CRUDOPERATIONS.service;

import com.sid.CRUDOPERATIONS.entity.Role;

public interface RoleService {
	
	public void createRole();
	
	public Role findByRole(String Role);
	
	public Role finById(int id);
}
