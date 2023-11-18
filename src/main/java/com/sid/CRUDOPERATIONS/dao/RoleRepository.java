package com.sid.CRUDOPERATIONS.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sid.CRUDOPERATIONS.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	public Role findByRole(String role);
}
