package com.rajeshkawali.exception;

public class EmployeeCollectionException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmployeeCollectionException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id) {
		return "Employee id:"+id+" not found!";
	}
	
	public static String EmployeeAlreadyExists() {
		return "Employee with given name already exists";
	}
}