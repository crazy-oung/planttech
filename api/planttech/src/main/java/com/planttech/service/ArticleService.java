package com.planttech.service;

import java.util.List;


import com.planttech.domain.Article;
import com.planttech.domain.Page;

public interface ArticleService {

	public List<Article> getArticleList(Page page);
	public int addArticle(Article article);
	public int modifyArticle(Article article);
	public int removeArticle(Article article);
	
	
	


}
