package com.web.advise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.web.exception.EmployeeAlreadyExit;
import com.web.exception.EmployeeNotFoundException;
import com.web.exception.EmployeesNotFoundException;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String,List<String>>> handleValidationErrors(ConstraintViolationException ex)
	{
		Map<String,List<String>> errorMap=new HashMap<>();
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		
		for(ConstraintViolation<?> violation : violations) {
			String fieldName = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			
			List<String> errorList = errorMap.getOrDefault(fieldName, new ArrayList<>());
			errorList.add(message);
			errorMap.put(fieldName, errorList);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(errorMap);
	}
	

    @ExceptionHandler(EmployeeNotFoundException.class)
   	public Map<String,String> handleEmployeeNotFoundException(EmployeeNotFoundException ex)
   	{
   		Map<String,String> errorMap=new HashMap<>();
   		errorMap.put("errorMessage", ex.getMessage());
   		return errorMap;
   	} 
    
    
    @ExceptionHandler(EmployeesNotFoundException.class)
	public Map<String,String> handleEmployeesNotFoundException(EmployeesNotFoundException ex)
	{
		Map<String,String> errorMap=new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
	}


    @ExceptionHandler(EmployeeAlreadyExit.class)
   	public Map<String,String> handleEmployeeAlreadyExit(EmployeeAlreadyExit ex)
   	{
   		Map<String,String> errorMap=new HashMap<>();
   		errorMap.put("errorMessage", ex.getMessage());
   		return errorMap;
   	}

}
