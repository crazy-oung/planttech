<?php
error_reporting(E_ALL);
ini_set("display_errors", 1);
?>

<html>
<head>
<meta http-dquiv="Content-Type" content="text/html" charset="utf-8">
<title>건조에 강한 실내식물</title>
</head>
<body>
<h4><strong> * 샘플화면은 디자인을 적용하지 않았으니, 개별 사이트의 스타일에 맞게 코딩하시기 바랍니다.</strong></h4>
<h3><strong>건조에 강한 실내식물</strong></h3>
<hr>
<?PHP
	//apiKey - 농사로 Open API에서 신청 후 승인되면 확인 가능
	$apiKey = "nongsaroSampleKey";
	//서비스 명
	$serviceName = "dryGarden";
	//오퍼레이션 명
	$operationName = "dryGardenDtl";

	//XML 받을 URL 생성
	$parameter = "/".$serviceName."/".$operationName;
	$parameter .= "?apiKey=".$apiKey;

	if(isset($_REQUEST["cntntsNo"])){
		$parameter .= "&cntntsNo=";
		$parameter .= $_REQUEST["cntntsNo"];
	}

	$url = "http://api.nongsaro.go.kr/service" . $parameter;

	//XML Parsing
	$xml = file_get_contents($url);
	//PHP5.x 이상이 설치되어 있어야 하며, php.ini에 allow_url_fopen을 on으로 해주시기 바랍니다.
	$object = simplexml_load_string($xml);

  //콘텐츠번호
  $cntntsNo        = $object->body[0]->item[0]->cntntsNo;
  //분류_명
  $clNm            = $object->body[0]->item[0]->clNm;
  //분류_코드설명
  $clCodeDc        = $object->body[0]->item[0]->clCodeDc;
  //학명
  $scnm            = $object->body[0]->item[0]->scnm;
  //유통명
  $distbNm         = $object->body[0]->item[0]->distbNm;
  //원산지
  $orgplce         = $object->body[0]->item[0]->orgplce;
  //형태분류_명
  $stleSeNm        = $object->body[0]->item[0]->stleSeNm;
  //꽃
  $flwrInfo        = $object->body[0]->item[0]->flwrInfo;
  //엽색변화
  $lfclChngeInfo   = $object->body[0]->item[0]->lfclChngeInfo;
  //뿌리형태_명
  $rdxStleNm       = $object->body[0]->item[0]->rdxStleNm;
  //생장형
  $grwtInfo        = $object->body[0]->item[0]->grwtInfo;
  //생장속도_명
  $grwtseVeNm      = $object->body[0]->item[0]->grwtseVeNm;
  //생육온도
  $grwhTpInfo      = $object->body[0]->item[0]->grwhTpInfo;
  //월동온도
  $pswntrTpInfo    = $object->body[0]->item[0]->pswntrTpInfo;
  //특성
  $chartrInfo      = $object->body[0]->item[0]->chartrInfo;
  //광
  $lighttInfo      = $object->body[0]->item[0]->lighttInfo;
  //물주기
  $waterCycleInfo  = $object->body[0]->item[0]->waterCycleInfo;
  //번식
  $prpgtInfo       = $object->body[0]->item[0]->prpgtInfo;
  //고온다습
  $hgtmMhmrInfo    = $object->body[0]->item[0]->hgtmMhmrInfo;
  //병충해
  $dlthtsInfo      = $object->body[0]->item[0]->dlthtsInfo;
  //관리수준_명
  $manageLevelNm   = $object->body[0]->item[0]->manageLevelNm;
  //관리요구도_명
  $manageDemandNm  = $object->body[0]->item[0]->manageDemandNm;
  //비료
  $frtlzrInfo      = $object->body[0]->item[0]->frtlzrInfo;
  //배치장소
  $batchPlaceInfo  = $object->body[0]->item[0]->batchPlaceInfo;
  //팁
  $tipInfo         = $object->body[0]->item[0]->tipInfo;
  //식물명
  $cntntsSj        = $object->body[0]->item[0]->cntntsSj;
  //대표이미지1
  $mainImgUrl1     = $object->body[0]->item[0]->mainImgUrl1;
  //대표이미지2
  $mainImgUrl2     = $object->body[0]->item[0]->mainImgUrl2;
  //광 적응성 이미지1
  $lightImgUrl1    = $object->body[0]->item[0]->lightImgUrl1;
  //광 적응성 이미지2
  $lightImgUrl2    = $object->body[0]->item[0]->lightImgUrl2;
  //광 적응성 이미지3
  $lightImgUrl3    = $object->body[0]->item[0]->lightImgUrl3;
?>
<table  border="1" cellspacing="0" cellpadding="0">
  <colgroup>
    <col width="15%">
    <col width="*">
  </colgroup>
	<tr>
	    <th>식물이미지</th>
	    <td>
			<?PHP
				if ($mainImgUrl1 != ""){
			?>
	    		<img src="<?=$mainImgUrl1?>" />
	    	<?PHP
				}
			?>
			<?PHP
				if ($mainImgUrl2 != ""){
			?>
	    		<img src="<?=$mainImgUrl2?>" />
	    	<?PHP
				}
			?>
	    </td>
	</tr>
	<tr>
	    <th>식물명</th>
	    <td><?=$cntntsSj?></td>
	</tr>
	<tr>
	    <th>과(분류)명</th>
	    <td><?=$clNm?></td>
	</tr>
	<tr>
	    <th>과(분류) 설명</th>
	    <td><?=$clCodeDc?></td>
	</tr>
	<tr>
	    <th>학명</th>
	    <td><?=$scnm?></td>
	</tr>
	<tr>
	    <th>유통명</th>
	    <td><?=$distbNm?></td>
	</tr>
	<tr>
	    <th>원산지</th>
	    <td><?=$orgplce?></td>
	</tr>
	<tr>
	    <th>형태분류</th>
	    <td><?=$stleSeNm?></td>
	</tr>
	<tr>
	    <th>꽃</th>
	    <td><?=$flwrInfo?></td>
	</tr>
	<tr>
	    <th>엽색변화</th>
	    <td><?=$lfclChngeInfo?></td>
	</tr>
	<tr>
	    <th>뿌리형태</th>
	    <td><?=$rdxStleNm?></td>
	</tr>
	<tr>
	    <th>생장형</th>
	    <td><?=$grwtInfo?></td>
	</tr>
	<tr>
	    <th>생장속도</th>
	    <td><?=$grwtseVeNm?></td>
	</tr>
	<tr>
	    <th>생육온도</th>
	    <td><?=$grwhTpInfo?></td>
	</tr>
	<tr>
	    <th>월동온도</th>
	    <td><?=$pswntrTpInfo?></td>
	</tr>
	<tr>
	    <th>특성</th>
	    <td><?=$chartrInfo?></td>
	</tr>
	<tr>
	    <th>광</th>
	    <td><?=$lighttInfo?></td>
	</tr>
	<tr>
	    <th>물주기</th>
	    <td><?=$waterCycleInfo?></td>
	</tr>
	<tr>
	    <th>번식</th>
	    <td><?=$prpgtInfo?></td>
	</tr>
	<tr>
	    <th>고온다습</th>
	    <td><?=$hgtmMhmrInfo?></td>
	</tr>
	<tr>
	    <th>병충해</th>
	    <td><?=$dlthtsInfo?></td>
	</tr>
	<tr>
	    <th>관리수준</th>
	    <td><?=$manageLevelNm?></td>
	</tr>
	<tr>
	    <th>관리요구도</th>
	    <td><?=$manageDemandNm?></td>
	</tr>
	<tr>
	    <th>비료</th>
	    <td><?=$frtlzrInfo?></td>
	</tr>
	<tr>
	    <th>배치장소</th>
	    <td><?=$batchPlaceInfo?></td>
	</tr>
	<tr>
	    <th>Tip</th>
	    <td><?=$tipInfo?></td>
	</tr>
	<tr>
	    <th>실내 광 적응성(배치 전)</th>
	    <td>
			<?PHP
				if ($lightImgUrl1 != ""){
			?>
	    		<img src="<?=$lightImgUrl1?>" />
	    	<?PHP
				}
			?>
	    </td>
	</tr>
	<tr>
	    <th>실내 광 적응성(6개월 후 발코니 창측)</th>
	    <td>
	    	<?PHP
				if ($lightImgUrl2 != ""){
			?>
	    		<img src="<?=$lightImgUrl2?>" />
	    	<?PHP
				}
			?>
	    </td>
	</tr>
	<tr>
	    <th>실내 광 적응성(6개월 후 발코니 내측)</th>
	    <td>
	    	<?PHP
				if ($lightImgUrl3 != ""){
			?>
	    		<img src="<?=$lightImgUrl3?>" />
	    	<?PHP
				}
			?>
	    </td>
	</tr>
</table>
<br>
<input type="button" onclick="javascript:fncList();" value="목록"/>
<form name="searchApiForm">
<?PHP

$srchNmArr = array("pageNo","sType", "sText", "wordType", "word", "sClCode", "stleSeCodeVal", "rdxStleCodeVal", "grwtseVeCodeVal", "manageLevelCodeVal", "manageDemandCodeVal");

for($i=0; $i<sizeof($srchNmArr); $i++){
  $reqVal = isset($_REQUEST[$srchNmArr[$i]]) ? $_REQUEST[$srchNmArr[$i]] : "";
  echo "<input type='hidden' name='" . $srchNmArr[$i] . "' value='" . $reqVal . "' />";
}

?>
</form>
<script type="text/javascript">
//목록이동
function fncList(){
	with(document.searchApiForm){
		method="get";
		action = "dryGardenList.php";
		target = "_self";
		submit();
	}
}
</script>
</body>
</html>
