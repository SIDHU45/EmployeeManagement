package com.sid.CRUDOPERATIONS.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sid.CRUDOPERATIONS.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, String> {

}
