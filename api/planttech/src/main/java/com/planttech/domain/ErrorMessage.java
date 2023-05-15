package com.planttech.domain;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ErrorMessage {
	
	private String code;
    private String message;
    private Map<String, Object> map;

    public ErrorMessage(String code, String message) {
        this.message = message;
        this.code = code;

        update();
    }

    private void update() {
        map = new HashMap<String, Object>();
        
        map.put("error", 
        		new HashMap<String, Object>() {{
		            put("message", message);
		            put("code", code);
		        }}
        );
    }

    public Map<String, Object> getMap() {
        return map;
    }
    
}
