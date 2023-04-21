package com.planttech.domain;

import lombok.Data;

@Data
public class Article{
	
	private int articleNo;
	private int userNo;
	private int plantNo;
	
	private String articleTitle;
	private String articleSubject;
	private String articleContent;
	private String articleProductPrice;
	
	// Timestamp
	private String articleCreatetime;
	private String articleModifytime;
	
}
