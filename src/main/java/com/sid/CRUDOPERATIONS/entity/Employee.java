package com.sid.CRUDOPERATIONS.entity;

import java.util.Collection;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee")
@Data
@NoArgsConstructor
public class Employee {
	
		@Id
		@Column(name = "phone_number")
		private String  phone_number;
		
		@NotBlank(message = "*")
		@Size(min = 1 , message = "*")
		@Column(name = "firstName")
		private String firstName;
		
		@NotBlank(message = "*")
		@Size(min = 1 , message = "*")
		@Column(name = "lastName")
		private String lastName;
		
		@NotBlank(message = "*")
		@Size(min = 1 , message = "*")
		@Email(message = "*")
		@Column(name = "email")
		private String email;
		
		@NotBlank(message = "*")
		@Size(min = 1 , message = "*")
		@Column(name = "password")
		private String password;
		
		@Column(name = "active")
		private int active;
		
		@ManyToMany(fetch = FetchType.EAGER)
		@Cascade(CascadeType.ALL)
		@JoinTable(name = "employeerole",
					joinColumns = @JoinColumn(name = "phone_number"),
					inverseJoinColumns = @JoinColumn(name = "role"))
		private Collection<Role> role;
}

