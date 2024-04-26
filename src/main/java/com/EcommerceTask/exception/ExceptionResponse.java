package com.EcommerceTask.exception;

import org.springframework.stereotype.Component;

@Component
public class ExceptionResponse {
 private String errorMessage;
 private String requestedURL;
 private String timeStamp;
 
 
 
public ExceptionResponse(String errorMessage, String requestedURL, String timeStamp) {
	super();
	this.errorMessage = errorMessage;
	this.requestedURL = requestedURL;
	this.timeStamp = timeStamp;
}
public ExceptionResponse() {
	// TODO Auto-generated constructor stub
}
public String getErrorMessage() {
	return errorMessage;
}
public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}
public String getRequestedURL() {
	return requestedURL;
}
public void setRequestedURL(String requestedURL) {
	this.requestedURL = requestedURL;
}
public String getTimeStamp() {
	return timeStamp;
}
public void setTimeStamp(String timeStamp) {
	this.timeStamp = timeStamp;
}

 
}
