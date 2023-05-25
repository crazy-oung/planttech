package com.planttech.domain.user;


import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserBookmark {
	
	@Schema(description = "북마크 고유번호", example = "0", deprecated = false)
	private int bookrmarkNo;
	@Schema(description = "유저 고유번호", example = "0", deprecated = false)
	private int userNo;
	@Schema(description = "식물 고유번호", example = "0", deprecated = false)
	private int plantNo;
	@Schema(description = "식물 등급 번호", example = "0", deprecated = false)
	private int plantScoreNo;
	@Schema(description = "북마크 타입(0: 입찰 항목에 대한 북마크, 1: 항목의 등급에 대한 북마크)", example = "0", deprecated = false)
	private int bookmarkType;
	
	
	@Schema(description = "북마크 생성 일자", deprecated = true)
	private Timestamp bookmarkCreatetime;
	
}
