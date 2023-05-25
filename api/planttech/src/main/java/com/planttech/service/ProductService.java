package com.planttech.service;

import java.util.List;
import java.util.Map;

import com.planttech.domain.search.Page;
import com.planttech.domain.shop.Product;

public interface ProductService {

	public List<Product> 		getProductList(Page page);
	public List<Product> 		getBidListByType(String productActive, int productType, int plantNo);
	public Map<String, Object> 	getProduct(int plantNo);
	
	public List<Product> 				getProductBidList(int plantNo, int searchDay, int plantScoreNo);
	public List<Map<String, Object>>  	getProductPriceListByGrade(Product product);
	
	public int addProduct(Product product);
	public int modifyProduct(Product product);
	public int removeProduct(Product product);
	
}
