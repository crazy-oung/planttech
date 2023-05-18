package com.planttech.service;

import java.util.List;

import com.planttech.domain.search.Page;
import com.planttech.domain.shop.Product;

public interface ProductService {

	public List<Product> getArticleList(Page page);
	public int addArticle(Product product);
	public int modifyArticle(Product product);
	public int removeArticle(Product product);
	
	
	


}
