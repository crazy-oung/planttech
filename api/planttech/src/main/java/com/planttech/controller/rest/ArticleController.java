package com.planttech.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.domain.Article;
import com.planttech.domain.Page;
import com.planttech.service.ArticleService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Article", description = "식물 거래")
@RestController
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired ArticleService articleService;
	
	// 거래 글 가져오기
	@GetMapping()
	public List<Article> getProductList(Page page) {
		System.out.println("::: getArticleList :::");
		System.out.println(page.toString());
		
		return articleService.getArticleList(page);
	}
	
	
	
	// 거래 글 올림
	@PostMapping()
	public int addArticle(Article article) {
		System.out.println("::: addUser :::"); 
		System.out.println(article.toString()); 
		
		return articleService.addArticle(article);
	}
	
	
	
}
