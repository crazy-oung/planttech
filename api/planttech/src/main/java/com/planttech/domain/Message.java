package com.planttech.domain;

import com.planttech.util.StatusEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Message {
	
	@Schema(description = "HTTP 상태 코드", example = "OK", deprecated = false)
    private StatusEnum status;
	@Schema(description = "응답 메시지", example = "응답 메세지", deprecated = false)
    private String message;
	@Schema(description = "반환 데이터", example = "", deprecated = false)
    private Object data;

    public Message() {
        this.status = StatusEnum.OK;
        this.data = null;
        this.message = null;
    }
    
	public void setFailMessage() {
		this.setStatus(StatusEnum.BAD_REQUEST);
		this.setData("fail");
		this.setMessage("fail");
	}
	
	public void setSuccessMessage() {
		this.setStatus(StatusEnum.OK);
		this.setData("success");
		this.setMessage("success");
	}
	
}