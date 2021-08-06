package com.app.siteminder.emailUtilDemo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmailDetails {

	@NotEmpty(message = "Email cannot be empty.")
	@NotNull(message = "Email cannot be empty.")
	@Email(message = "Email is not valid", regexp=".+@.+\\..+")
	private String email;
	
	private String name;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "EmailDetails [email=" + email + ", name=" + name + "]";
	}
	  
	
	  
	  
	  
}
