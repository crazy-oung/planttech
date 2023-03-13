package com.planttech.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.Dht11;
import com.planttech.domain.Page;
import com.planttech.mapper.DHT11Mapper;
import com.planttech.service.DHT11Service;


@Service
@Transactional
public class DHT11ServiceImpl implements DHT11Service {
	@Autowired private DHT11Mapper dht11Mapper;


	@Override
	public List<Dht11> getDHT11List(Page page) {
		System.out.println("::: DHT11ServiceImpl - getDHT11List :::");
		
		return dht11Mapper.selectDHT11List(page);
	}

	@Override
	public int addDHT11(Dht11 dht11) {
		int success= 0;
		
		
		try {
			dht11Mapper.insertDHT11(dht11);
			success++;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return success;
	}
	

}
