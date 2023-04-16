package com.planttech.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.Plant;
import com.planttech.mapper.TestMapper;
import com.planttech.service.TestService;


@Service
@Transactional
public class TestServiceImpl implements TestService {
	@Autowired private TestMapper testMapper;

	
	//Report 카테고리 가져오기
	@Override
	public List<Plant> getTestDataList() {
		System.out.println(":::TestServiceImpl - getTestDataList:::");
		return testMapper.selectPlant();
	}
	

}
