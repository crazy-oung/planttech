package com.planttech.domain.response;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.Getter;

@Data
public class ErrorMessage {
	
    private Map<String, Object> error;

    public ErrorMessage(String code, String message) {
    	error = new HashMap<String, Object>() {{
    		put("code", code);
            put("message", message);
        }};
    }
    
    public ErrorMessage(int code, String message) {
    	error = new HashMap<String, Object>() {{
    		put("code", code);
    		put("message", message);
    	}};
    }
    
}
