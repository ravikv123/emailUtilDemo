package com.app.siteminder.emailUtilDemo.dto;

import java.util.List;

public class EmailResponse {

	List<String> errorMessage;
	String statusMessage;

	public List<String> getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	

	
}
