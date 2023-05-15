package com.planttech.service;

import java.util.List;


import com.planttech.domain.Product;
import com.planttech.domain.Page;

public interface ProductService {

	public List<Product> getArticleList(Page page);
	public int addArticle(Product product);
	public int modifyArticle(Product product);
	public int removeArticle(Product product);
	
	
	


}
