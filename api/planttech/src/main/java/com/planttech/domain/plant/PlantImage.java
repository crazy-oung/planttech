package com.planttech.domain.plant;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PlantImage {
	

	@Schema(description = "식물 이미지 고유번호", example = "0", deprecated = true)
	private int plantImageNo; 
	@Schema(description = "식물 고유번호", example = "0", deprecated = false)
	private Object plantNo;
	@Schema(description = "식물 사진 BASE 64 코드", example = "", deprecated = false)
	private String plantImagePic;
	
	@Schema(deprecated = true)
	private String plantImagePic1;
	@Schema(deprecated = true)
	private String plantImagePic2;
	@Schema(deprecated = true)
	private String plantImagePic3;
	
	@Schema(description = "식물 사진 생성일시", deprecated = true)
	private Timestamp plantImageCreatetime;
	@Schema(description = "식물 사진 생성일시", deprecated = true)
	private Timestamp plantImageModifytime;
}
