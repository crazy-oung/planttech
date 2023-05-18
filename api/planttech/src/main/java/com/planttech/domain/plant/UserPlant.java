package com.planttech.domain.plant;


import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserPlant extends Plant{
	
	@Schema(description = "유저 식물 고유번호", example = "0", deprecated = false)
	private int userPlantNo;
	@Schema(description = "유저 고유번호", example = "0", deprecated = false)
	private int userNo;
	@Schema(description = "식물 창고위치 고유번호", example = "0", deprecated = false)
	private int warehousePlantNo;
	@Schema(description = "유저 식물 별명", example = "0", deprecated = false)
	private String userPlantName;
	
	@Schema(description = "유저 식물 지정일시", deprecated = true)
	private Timestamp userPlantCreatetime;
	@Schema(description = "유저 식물 수정일시", deprecated = true)
	private Timestamp userPlantModifytime;
	
	
}