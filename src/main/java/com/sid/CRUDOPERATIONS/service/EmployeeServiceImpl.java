package com.sid.CRUDOPERATIONS.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sid.CRUDOPERATIONS.dao.EmployeeRepo;
import com.sid.CRUDOPERATIONS.entity.Employee;
import com.sid.CRUDOPERATIONS.entity.Role;

import jakarta.persistence.EntityExistsException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepo employeeRepo;
	private BCryptPasswordEncoder encoder;
	private RoleServiceImpl roleService;
	
	public EmployeeServiceImpl(EmployeeRepo theEmployeeRepo,
			BCryptPasswordEncoder theEncoder, RoleServiceImpl theRoleService) {
		employeeRepo = theEmployeeRepo;
		encoder = theEncoder;
		roleService = theRoleService;
	}

	@Override
	public void defaultUser() {
		
		// ADMIN
		Employee admin = new Employee();
		admin.setFirstName("Soman");
		admin.setEmail("sidmahe45200@gmail.com");
		admin.setLastName("siddarth");
		admin.setActive(1);
		admin.setPassword(encoder.encode("Sidhu@2000"));
		admin.setPhone_number("9566927550");
		
		Collection<Role> role = new ArrayList<Role>();
		role.add(roleService.findByRole("ROLE_ADMIN"));
		role.add(roleService.findByRole("ROLE_EMPLOYEE"));
		role.add(roleService.findByRole("ROLE_MANAGER"));
		admin.setRole(role);
		
		employeeRepo.save(admin);
		
		// MANAGER	
		Employee manager = new Employee();
		manager.setFirstName("Madhu");
		manager.setEmail("madhu2003@gmail.com");
		manager.setLastName("suthanan");
		manager.setActive(1);
		manager.setPassword(encoder.encode("Madhu@2003"));
		manager.setPhone_number("7608711145");
		
		Collection<Role> role1 = new ArrayList<Role>();
		role1.add(roleService.findByRole("ROLE_EMPLOYEE"));
		role1.add(roleService.findByRole("ROLE_MANAGER"));
		manager.setRole(role1);
		
		employeeRepo.save(manager);
		
		Employee employee = new Employee();
		employee.setFirstName("John");
		employee.setEmail("johndoe@gmail.com");
		employee.setLastName("doe");
		employee.setActive(1);
		employee.setPassword(encoder.encode("John@2000"));
		employee.setPhone_number("7608512211");
		
		Collection<Role> role2 = new ArrayList<Role>();
		role2.add(roleService.findByRole("ROLE_EMPLOYEE"));
		employee.setRole(role2);
		
		employeeRepo.save(employee);
	}
	
	@Override
	public Employee save(Employee theEmployee) {
		return employeeRepo.save(theEmployee);
	}
	@Override
	public void deleteById(String phone_number) {
		employeeRepo.deleteById(phone_number);
	}

	@Override
	public List<Employee> findAll() {		
		return employeeRepo.findAll();
	}

	@Override
	public Employee findById(String  phone_number) {
		Optional<Employee> result = employeeRepo.findById(phone_number);
		if(result.isPresent()) {
			return result.get();
		}else {
			return null;
		}
	}

	@Override
	public Employee createEmployee(Employee theEmployee) {
		
		Optional<Employee> employee = employeeRepo.findById(theEmployee.getPhone_number());
		
		if(!(employee.isPresent())) {
			theEmployee.setActive(1);
			theEmployee.setRole(Arrays.asList(roleService.findByRole("ROLE_EMPLOYEE")));
			theEmployee.setPassword(encoder.encode(theEmployee.getPassword()));		
			return employeeRepo.save(theEmployee);
		}else {
			throw new EntityExistsException("Phone number already exists!");
		}
	}

}
