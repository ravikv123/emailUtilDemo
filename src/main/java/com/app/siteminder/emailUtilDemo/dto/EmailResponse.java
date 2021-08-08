package com.app.siteminder.emailUtilDemo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class EmailResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
	List<String> errorMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
