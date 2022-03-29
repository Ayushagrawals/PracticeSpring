package com.PracticeProject.Practice.exception;

import org.springframework.stereotype.Component;

@Component
public class ExceptionResponse {

	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	public ExceptionResponse()
	{
		
	}
	public ExceptionResponse(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "CustomException [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}
	
}
