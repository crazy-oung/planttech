package com.planttech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.Article;
import com.planttech.domain.Page;


@Mapper
public interface ArticleMapper {

	public List<Article> selectArticleList(Page page);
	public int insertArticle(Article article);
	public int updateArticle(Article article);
	public int deleteArticle(Article article);



}

