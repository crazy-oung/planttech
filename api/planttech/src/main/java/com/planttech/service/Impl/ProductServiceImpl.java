package com.planttech.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.search.Page;
import com.planttech.domain.shop.Product;
import com.planttech.mapper.ProductMapper;
import com.planttech.service.ProductService;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired private ProductMapper productMapper;

	@Override
	public List<Product> getProductList(Page page) {
		return productMapper.selectProductList(page);
	}

	@Override
	public Product getProduct(int plantNo) {
		return productMapper.selectProduct(plantNo);
	}
	
	@Override
	public int addProduct(Product product) {
		return productMapper.insertProduct(product);
	}

	@Override
	public int modifyProduct(Product product) {
		return productMapper.updateProduct(product);
	}

	@Override
	public int removeProduct(Product product) {
		return productMapper.deleteProduct(product);
	}
}
