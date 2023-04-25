package com.planttech.domain;


import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Plant {
	
	@Schema(description = "식물 종 고유번호", example = "0", deprecated = false)
	private int plantNo;
	@Schema(description = "식물 종 이름", example = "난초", deprecated = false)
	private String plantName;
	
	@Schema(description = "식물 종 등록 날짜", deprecated = true)
	private Timestamp plantCreatetime;

}