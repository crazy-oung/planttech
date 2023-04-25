package com.planttech.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.domain.Article;
import com.planttech.domain.Page;
import com.planttech.service.ArticleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Article", description = "식물 거래")
@RestController
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired ArticleService articleService;
	
	
	@GetMapping()
	@Operation(summary = "식물 거래글 조회", description = "키워드, 카테고리, 페이지 번호, 페이지당 글 수를 이용해 거래글을 조회합니다.")
	public List<Article> getArticleList(Page page) {
		System.out.println("::: getArticleList :::");
		System.out.println(page.toString());
		
		return articleService.getArticleList(page);
	}
	
	@PostMapping()
	@Operation(summary = "식물 거래글 추가", description = "식물 거래글을 올립니다. 객체 값중 생성일자 및 수정일자는 받지 않습니다.")
	public int addArticle(Article article) {
		System.out.println("::: addArticle :::"); 
		System.out.println(article.toString()); 
		
		return articleService.addArticle(article);
	}
	
	@PutMapping()
	@Operation(summary = "식물 거래글 수정", description = "식물 거래글을 수정합니다. 파라미터 값은 거래글 추가와 같으며 마찬가지로 객체 값중 생성일자 및 수정일자는 받지 않습니다.")
	public int modifyArticle(Article article) {
		System.out.println("::: updateArticle :::"); 
		System.out.println(article.toString()); 
		
		return articleService.modifyArticle(article);
	}
	
	
	@DeleteMapping()
	@Operation(summary = "식물 거래글 삭제", description = "식물 거래글을 삭제합니다. 객체 값중 생성일자 및 수정일자는 받지 않습니다.")
	public int removeArticle(Article article) {
		System.out.println("::: removeArticle :::"); 
		System.out.println(article.toString()); 
		
		return articleService.removeArticle(article);
	}
	
	
	
}
