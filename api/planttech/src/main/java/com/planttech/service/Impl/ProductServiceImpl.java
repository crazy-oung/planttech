package com.planttech.service.Impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<Product> getBidListByType(String productActive, int productType, int plantNo) {
		Map map = new HashMap<>() {{
			put("productActive", productActive);
			put("productType", productType);
			put("plantNo", plantNo);
		}};
		return productMapper.selectBidListByType(map);
	}

	@Override
	public Map<String, Object> getProduct(int plantNo) {
		return productMapper.selectProduct(plantNo);
	}
	
	@Override
	public List<Map<String, Object>>  getProductPriceListByGrade(Product product) {
		return productMapper.selectProductPriceListByGrade(product);
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
	
	
	// 입찰 체결 내역 조회 
	@Override
	public List<Product> getProductBidList(int plantNo, int searchDay, int plantScoreNo) {
		Map map = new HashMap<>() {{
			put("plantNo", plantNo);
			put("searchDay", searchDay);
			put("plantScoreNo", plantScoreNo);
		}};
		
		return productMapper.selectProductBidList(map);
	}
}
