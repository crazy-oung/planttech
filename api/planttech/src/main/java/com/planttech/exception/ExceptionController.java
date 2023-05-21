package com.planttech.exception;

import lombok.extern.slf4j.Slf4j;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.planttech.domain.response.ErrorMessage;
import com.planttech.util.ErrorCodeEnum;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Pattern;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ResponseEntity<ErrorMessage> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
    	log.warn("MethodArgumentNotValidException - url:{}", request.getRequestURI());
    	e.printStackTrace();
        ErrorMessage errMsg = buildErrorResponse(e.getBindingResult());
        return new ResponseEntity<ErrorMessage>(errMsg, HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(LoginException.class)
    public @ResponseBody ResponseEntity<ErrorMessage> loginException(LoginException e, HttpServletRequest request){
    	log.warn("LoginException - url:{}", request.getRequestURI());
    	e.printStackTrace();
        ErrorMessage errMsg = new ErrorMessage("LOGIN", "로그인이 필요합니다.");
        return new ResponseEntity<ErrorMessage>(errMsg, HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody ResponseEntity<ErrorMessage> runtimeException(RuntimeException e, HttpServletRequest request){
    	log.warn("RuntimeException - url:{}", request.getRequestURI());
    	e.printStackTrace();
    	ErrorMessage errMsg = new ErrorMessage("Server Error", e.getClass().getSimpleName());
    	return new ResponseEntity<ErrorMessage>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    // error build 
    private ErrorMessage buildErrorResponse(BindingResult bindingResult){
        String code = "";
        String message = "";

        if(bindingResult.hasErrors()) {
            message = bindingResult.getFieldError().getDefaultMessage();
            String bindResultCode = bindingResult.getFieldError().getCode();
            System.out.println(bindResultCode);
            
            switch (bindResultCode){
	            case "NotBlank":
	            	code = ErrorCodeEnum.NOT_BLANK.getCode();
	            	break;
                case "NotNull":
                    code = ErrorCodeEnum.NOT_NULL.getCode();
                    break;
                case "NotEmpty":
                	code = ErrorCodeEnum.NOT_EMPTY.getCode();
                	break;
                case "Pattern":
                	code = ErrorCodeEnum.PATTERN.getCode();
                	break;
                case "Min":
                    code = ErrorCodeEnum.MIN_VALUE.getCode();
                    break;
                case "Max":
                	code = ErrorCodeEnum.MAX_VALUE.getCode();
                	break;
            		
            }
        }

        return new ErrorMessage(code, message);
    }
}
