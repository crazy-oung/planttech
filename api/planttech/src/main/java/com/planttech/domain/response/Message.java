package com.planttech.domain.response;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Message {
	
	@Schema(description = "HTTP 상태 코드", example = "OK", deprecated = false)
    private HttpStatus status;
	@Schema(description = "응답 메시지", example = "응답 메세지", deprecated = false)
    private String message;
	@Schema(description = "반환 데이터", example = "", deprecated = false)
    private Object data;

    public Message() {
        this.status = HttpStatus.OK;
        this.data = null;
        this.message = null;
    }
   
    public Message(HttpStatus status, String message, Object data) {
    	this.status = status;
    	this.message = message;
    	this.data = data;
    }
    
    
	public void setFailMessage() {
		this.setStatus(HttpStatus.BAD_REQUEST);
		this.setData("fail");
		this.setMessage("fail");
	}
	
	public void setSuccessMessage() {
		this.setStatus(HttpStatus.OK);
		this.setData("success");
		this.setMessage("success");
	}
	
}