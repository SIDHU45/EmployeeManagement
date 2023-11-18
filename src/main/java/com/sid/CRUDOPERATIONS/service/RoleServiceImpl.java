package com.sid.CRUDOPERATIONS.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sid.CRUDOPERATIONS.dao.RoleRepository;
import com.sid.CRUDOPERATIONS.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {
	
	private RoleRepository roleRepository;
	private Optional<Role> findById;
	
	public RoleServiceImpl(RoleRepository repository) {
		roleRepository=repository;
	}

	@Override
	public void createRole() {
		Role role1 = new Role();
		role1.setRole("ROLE_EMPLOYEE");
		Role role2 = new Role();
		role2.setRole("ROLE_MANAGER");
		Role role3 = new Role();
		role3.setRole("ROLE_ADMIN");
				
		List<Role> roles = Arrays.asList(role1 , role2 ,role3);
		Collection<Role> list = roles;		
		roleRepository.saveAll(roles);
	}

	@Override
	public Role findByRole(String role) {
		
		return roleRepository.findByRole(role); 
	}

	@Override
	public Role finById(int id) {		
		return roleRepository.findById(id).get();
	}

	
}
