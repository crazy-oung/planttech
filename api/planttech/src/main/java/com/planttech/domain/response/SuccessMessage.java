package com.planttech.domain.response;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.Getter;

@Data
public class SuccessMessage {
	
    private Map<String, Object> success;

    public SuccessMessage(Object data) {
    	success = new HashMap<String, Object>() {{
            put("message", data);
        }};
    }
    
    public SuccessMessage(String msg, Object data) {
    	success = new HashMap<String, Object>() {{
            put(msg, data);
        }};
    }
    
    
}
