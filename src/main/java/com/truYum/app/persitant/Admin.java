package com.truYum.app.persitant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity 
// used at the class level and marks the class as a persistent entity. 
//It signals to the JPA provider that the class should be treated as a table in the database.

@Table(name="Admin") //Setting the table name as Admin using this annotation
public class Admin {
	@Id //Specifies the primary key of an entity
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="generator") //generating value automatically
	@SequenceGenerator(name = "generator", sequenceName = "contact_seq", allocationSize = 1) //incrementing 1
	private int id;
	private String name;
	private String user_id;
	private String password;
	
	
	//Constructor  with parameter
	public Admin(String name, String user_id, String password) {
		super();
		this.name = name;
		this.user_id = user_id;
		this.password = password;
	}
	
	//Constructor  without parameter
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//Generating getter setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
