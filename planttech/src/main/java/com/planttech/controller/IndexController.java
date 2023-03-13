package com.planttech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

//	@Autowired IndexService IndexService;

	@Operation(summary = "INDEX controller", description = "non REST API, For visiting Index page")
	@GetMapping
	public String getIndexPage(HttpSession session) {
		System.out.println("::: GET(NON REST) - planttech home :::");

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
