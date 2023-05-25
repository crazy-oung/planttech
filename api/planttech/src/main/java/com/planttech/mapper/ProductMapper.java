package com.planttech.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.search.Page;
import com.planttech.domain.shop.Product;
import com.planttech.domain.user.User;


@Mapper
public interface ProductMapper {

	public List<Product> 	selectProductList(Page page);
	public List<Product> 	selectBidListByType(Map<String, Object> map);
	
	
	public List<Product> 	selectProductBidList(Map<String, Object> map);
	public List<Product> 	selectUserProductList(Map<String, Object> map);
	public List<Product> 	selectAllProductList(Page page);
	
	public Map<String, Object> 			selectProduct(int plantNo);
	public List<Map<String, Object>> 	selectProductPriceListByGrade(Product product);
	
	public int insertProduct(Product product);
	public int updateProduct(Product product);
	public int deleteProduct(Product product);

	
	// 인공지능 용
	public int insertBidProduct(Product product);
	
}

