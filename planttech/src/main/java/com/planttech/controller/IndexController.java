package com.planttech.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {
	
//	@Autowired IndexService IndexService;

	
	@GetMapping
	public String getIndexPage(HttpSession session) {
		System.out.println("::: get - planttech home :::");
		
//		System.out.println(session.getAttribute("loginUser"));
		
//		if(session.getAttribute("loginUser") != null) {
//			if(session.getAttribute("loginManager")!= null) {
//				return "planttech/user/home";
//			}
//			return "/planttech/today";
//		}
		
		return "index";
	}
	
	
}

