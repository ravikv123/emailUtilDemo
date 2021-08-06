package com.app.siteminder.emailUtilDemo.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ravi
 * Custom Exception Implementation
 */
public class CustomException extends Exception
{

	private static final long serialVersionUID = 1L;
	String errorMessage;
	List<String> errorList = new ArrayList<String>();
	public CustomException()
	{
		super();
	}
	public CustomException(String errorMessage){  
		  super(errorMessage);  
		  this.errorMessage=errorMessage;
		 }
	public String getErrorMessage() {
		return errorMessage;
	}
	public List<String> getErrorList() {
		return errorList;
	}
	public void addErrorList(String error) {
		this.errorList.add(error);
	}	
	
	
	  
}