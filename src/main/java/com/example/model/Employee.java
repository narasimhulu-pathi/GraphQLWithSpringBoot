package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Employee {
	
	@Id
	private String id;
	private String name;
	private String salary;
	private String dateOfBirth;
	private String address;
	
	public Employee() {}
	
	public Employee(String id, String name, String salary, String dateOfBirth, String address) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
