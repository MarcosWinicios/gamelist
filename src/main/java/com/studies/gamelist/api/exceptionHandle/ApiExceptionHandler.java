package com.studies.gamelist.api.exceptionHandle;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.studies.gamelist.domain.exception.BusinessException;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<Field> fields =  new ArrayList<Field>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name =  ((FieldError) error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			fields.add(new Field(name, message));
			
		}
		
		Problem problem = new Problem();
		problem.setStatus(status.value());
		problem.setDateHour(OffsetDateTime.now());
		problem.setTitle("One or more invalid fields.");
		problem.setFields(fields);
		
		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusiness (BusinessException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problem problem = new Problem();
		
		problem.setStatus(status.value());
		problem.setDateHour(OffsetDateTime.now());
		problem.setTitle(ex.getMessage());
		
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntityNotFoundException ex, WebRequest request) {
		var status =  HttpStatus.NOT_FOUND;
		var problem = new Problem();
		
		problem.setStatus(status.value());
		problem.setDateHour(OffsetDateTime.now());
		problem.setTitle(ex.getMessage());
	
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

}
