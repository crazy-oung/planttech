package com.planttech.service;

import java.util.List;

import com.planttech.domain.Dht11;

public interface DHT11Service {

	public List<Dht11> getDHT11List();

	public int addDHT11(Dht11 dht11);
//
//	// Tset 상세정보 가져오기
//	public TsetBoard getTsetOne(int TsetId);
//
//	// Tset 정보 삭제하기
//	public int removeTset(int TsetId);
//
//	// Tset 리스트 삭제
//	public void removeTsetBoardList(List<String> TsetBoardIdList);
//
//	// Tset 수정하기
//	public int modifyTset(TsetBoard TsetBoard);
	
	
}
