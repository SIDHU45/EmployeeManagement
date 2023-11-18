package com.sid.CRUDOPERATIONS.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jayway.jsonpath.Option;
import com.sid.CRUDOPERATIONS.entity.Employee;
import com.sid.CRUDOPERATIONS.entity.Role;
import com.sid.CRUDOPERATIONS.service.EmployeeServiceImpl;
import com.sid.CRUDOPERATIONS.service.RoleServiceImpl;


import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class EmployeeController {
	
	private EmployeeServiceImpl employeeService;
	private RoleServiceImpl roleServiceImpl;
	
	
	@Autowired
	public EmployeeController(EmployeeServiceImpl theEmployeeService,
											RoleServiceImpl roleService) {	
		employeeService = theEmployeeService;
		roleServiceImpl = roleService;
	}
	
	@PostConstruct
	public void createDefault() {
		roleServiceImpl.createRole();
		employeeService.defaultUser();
		
	}
	
	@GetMapping("/login_page")
	public String showLogin() {
		return "login-form";
	}
	@GetMapping("/access_denied_page")
	public String showAccessDenied() {
		return "access-denied";
	}	
	@GetMapping("/signup_page")
	public String showSignUpPage(Model theModel) {
		theModel.addAttribute("user" , new Employee());
		return "signup-form";
	}
	@PostMapping("/register")
	public String registerEmp(@Valid @ModelAttribute ("employee") Employee theEmployee , BindingResult result) {
		if(result.hasErrors()) {
			return "signup-form";
		}else {
			employeeService.createEmployee(theEmployee);
			return "redirect:/login_page";
		}
	}
	@GetMapping("/list")
	public String fetch(Model theModel) {
		List<Employee> listEmp = employeeService.findAll();
		theModel.addAttribute("employee" , listEmp );
		
		return "employees/listEmployee";
	}
	
	@GetMapping("/showform")
	public String addemp(Model theModel, Employee theEmployee) {
		theModel.addAttribute("employee", theEmployee);
		return "employees/showform";
	}	
	@PostMapping("/save")
	public String saveEmployee(@Valid @ModelAttribute ("employee") Employee theEmployee , BindingResult result,
										HttpSession session) {
		
		session.setAttribute("employee" , theEmployee);
		if(result.hasErrors()) {
			return "employees/showform";
		}
		else {
			employeeService.createEmployee(theEmployee);
			return "redirect:/list";
		}
	}
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute ("employee") Employee theEmployee , BindingResult result,
										HttpSession session) {
		
		session.setAttribute("employee" , theEmployee);
		if(result.hasErrors()) {
			return "employees/update-form";
		}
		else {
			
			employeeService.save(theEmployee);
			return "redirect:/list";
		}
	}
	@GetMapping("/showupdate")
	public String updateEmployee(@RequestParam ("employeeId") String phone_number , Model theModel) {
		Employee theEmployee = employeeService.findById(phone_number);
		theModel.addAttribute("employee", theEmployee);
		return "employees/update-form";
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam ("employeeId") String phone_number) {		
		Employee employee = employeeService.findById(phone_number);
		employee.setRole(null);
		employeeService.deleteById(phone_number);
		return "redirect:/list";
	}

}
