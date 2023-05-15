package com.planttech.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.Product;
import com.planttech.domain.Page;
import com.planttech.mapper.ProductMapper;
import com.planttech.service.ProductService;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired private ProductMapper productMapper;


	@Override
	public List<Product> getArticleList(Page page) {
		System.out.println("::: - getArticleList :::");
		
		return productMapper.selectProductList(page);
	}

	@Override
	public int addArticle(Product product) {
		System.out.println("::: - addArticle :::");
		
		try {
			return productMapper.insertArticle(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int modifyArticle(Product product) {
		System.out.println("::: - modifyArticle :::");
		
		try {
			return productMapper.updateArticle(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int removeArticle(Product product) {
		System.out.println("::: - removeArticle :::");
		
		try {
			return productMapper.deleteArticle(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	

}
