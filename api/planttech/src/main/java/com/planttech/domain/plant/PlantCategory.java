package com.planttech.domain.plant;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PlantCategory {
	
	@Schema(description = "식물 분류 고유번호", example = "3", deprecated = false)
	private int plantCategoryNo;
	@Schema(description = "식물 분류 이름", example = "관엽식물", deprecated = false)
	private String plantCategoryName;
	
	@Schema(description = "식물 분류 등록/수정 일시", deprecated = true)
	private Timestamp plantCategoryTimestamp;
}
