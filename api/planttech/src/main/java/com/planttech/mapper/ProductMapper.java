package com.planttech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.search.Page;
import com.planttech.domain.shop.Product;


@Mapper
public interface ProductMapper {

	public List<Product> selectProductList(Page page);
	public Product selectProduct(int plantNo);
	
	public int insertProduct(Product product);
	public int updateProduct(Product product);
	public int deleteProduct(Product product);

}

