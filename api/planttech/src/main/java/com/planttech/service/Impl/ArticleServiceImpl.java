package com.planttech.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.Article;
import com.planttech.domain.Page;
import com.planttech.mapper.ArticleMapper;
import com.planttech.service.ArticleService;


@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
	@Autowired private ArticleMapper articleMapper;


	@Override
	public List<Article> getArticleList(Page page) {
		System.out.println("::: - getArticleList :::");
		
		return articleMapper.selectArticleList(page);
	}

	@Override
	public int addArticle(Article article) {
		System.out.println("::: - addArticle :::");
		
		try {
			return articleMapper.insertArticle(article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	

}
