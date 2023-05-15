package com.planttech.domain;


import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserMileage {
	
	@Schema(description = "유저 마일리지 고유번호", example = "0", deprecated = false)
	private int userMileageNo;
	@Schema(description = "유저 고유번호", example = "0", deprecated = false)
	private int userNo;
	@Schema(description = "누적/지출 마일리지 값", example = "1000", deprecated = false)
	private int userMileageValue;
	@Schema(description = "마일리지 누적/지출 내용", example = "웰컴 기프트", deprecated = false)
	private String userMileageContent;
	@Schema(description = "마일리지 지출처", example = "", deprecated = false)
	private String userMileagePayment;
	@Schema(description = "마일리지 가용 여부", example = "(0: 불능, 1: 사용 가능)", deprecated = false)
	private String userMileageAble;
	
	@Schema(description = "마일리지 누적 일시", deprecated = true)
	private Timestamp userMileageCreatetime;
    
}
