package com.planttech.util;



import lombok.Getter;

public enum ErrorCodeEnum {

     NOT_NULL("ERR_0012",	"필수값이 누락되었습니다."), 
     NOT_EMPTY("ERR_0011",	"필수값이 누락되었습니다."), 
     MIN_VALUE("ERR_0001",	"값이 너무 작습니다."), 
     MAX_VALUE("ERR_0002", 	"값이 너무 큽니다.");

    @Getter
    private String code;

    @Getter
    private String description;

    ErrorCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}