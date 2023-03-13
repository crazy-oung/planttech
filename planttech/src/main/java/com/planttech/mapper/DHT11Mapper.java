package com.planttech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.Dht11;
import com.planttech.domain.Page;


@Mapper
public interface DHT11Mapper {
	// Test Data 로 plant 
	public List<Dht11> selectDHT11List(Page page);
	
	public int insertDHT11(Dht11 dht11);
	
}
