package com.planttech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.Product;
import com.planttech.domain.Page;


@Mapper
public interface ProductMapper {

	public List<Product> selectArticleList(Page page);
	public List<Product> selectProductList(Page page);
	
	public int insertArticle(Product product);
	public int updateArticle(Product product);
	public int deleteArticle(Product product);

}

