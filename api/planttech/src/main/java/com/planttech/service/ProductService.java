package com.planttech.service;

import java.util.List;

import com.planttech.domain.search.Page;
import com.planttech.domain.shop.Product;

public interface ProductService {

	public List<Product> getProductList(Page page);
	public Product getProduct(int plantNo);
	public int addProduct(Product product);
	public int modifyProduct(Product product);
	public int removeProduct(Product product);
	
}
