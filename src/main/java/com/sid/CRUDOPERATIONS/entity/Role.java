package com.sid.CRUDOPERATIONS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Id
	private String role;
	
}
