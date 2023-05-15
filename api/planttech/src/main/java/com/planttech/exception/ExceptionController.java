package com.planttech.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.planttech.domain.ErrorMessage;
import com.planttech.util.ErrorCodeEnum;

import jakarta.servlet.http.HttpServletRequest;



@Slf4j
@ControllerAdvice
public class ExceptionController {

    /**
     * @valid  유효성체크에 통과하지 못하면  MethodArgumentNotValidException 이 발생한다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
    	
        log.warn("::: !!! MethodArgumentNotValidException !!! ::: \n" + 
        		"url:{}, trace:{}", 
        		request.getRequestURI(), e.getStackTrace()
		);
        
        ErrorMessage errMsg = buildErrorResponse(e.getBindingResult());
        
        return new ResponseEntity<ErrorMessage>(errMsg, HttpStatus.BAD_REQUEST);
    }
    
    // error build 
    private ErrorMessage buildErrorResponse(BindingResult bindingResult){
        String code = "";
        String message = "";

        if(bindingResult.hasErrors()) {
            message = bindingResult.getFieldError().getDefaultMessage();
            String bindResultCode = bindingResult.getFieldError().getCode();

            switch (bindResultCode){
                case "NotNull":
                    code = ErrorCodeEnum.NOT_NULL.getCode();
                    break;
                case "NotEmpty":
                	code = ErrorCodeEnum.NOT_EMPTY.getCode();
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
