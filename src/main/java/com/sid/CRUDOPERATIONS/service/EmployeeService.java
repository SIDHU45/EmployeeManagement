package com.sid.CRUDOPERATIONS.service;

import java.util.List;

import com.sid.CRUDOPERATIONS.entity.Employee;

public interface EmployeeService {
	
	public Employee createEmployee(Employee theEmployee);
	public Employee save(Employee theEmployee);
	public void deleteById(String phone_number);
	public List<Employee> findAll();
	public Employee findById(String phone_number); 
	public void defaultUser();
}
