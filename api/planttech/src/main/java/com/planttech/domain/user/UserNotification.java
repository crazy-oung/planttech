package com.planttech.domain.user;


import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserNotification {
	
	@Schema(description = "유저 알림 고유번호", example = "0", deprecated = false)
	private int userNotificationNo;
	
	@Schema(description = "유저 고유번호", example = "0", deprecated = false)
	private int userNo;
	@Schema(description = "화분 고유번호", example = "0", deprecated = false)
	private Object warehousePlantNo;
	@Schema(description = "입찰 고유번호", example = "0", deprecated = false)
	private int productNo;
	@Schema(description = "유저 식물 고유번호", example = "0", deprecated = false)
	private int userPlantNo;
	@Schema(description = "유저 마일리지 고유번호", example = "0", deprecated = false)
	private int userMileageNo;
	
	@Schema(description = "알림 내용", example = "웰컴 기프트", deprecated = false)
	private String userNotificationContent;
	
	@Schema(description = "알림 종류(0: 식물 알림, 1: 판매 알림, 2: 구매 알림, 3: 마일리지 알림, 4:기타)", example = "0", deprecated = false)
	private int userNotificationType;
	@Schema(description = "알림 확인 여부(0: 확인안함, 1: 확인함, 2: 삭제됨)", example = "", deprecated = false)
	private int userNotificationActive;
	
	@Schema(description = "유저 알림 수정 일시", deprecated = true)
	private Timestamp userNotificatinReadtime;
	@Schema(description = "유저 알림 생성 일시", deprecated = true)
	private Timestamp userNotificatinCreatetime;
    
}
