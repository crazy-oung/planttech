<% @CODEPAGE="65001" language="VBScript" %>
<html>
<head>
<meta http-dquiv="Content-Type" content="text/html" charset="utf-8">
<title>건조에 강한 실내식물</title>
</head>
<body>
<h4><strong> * 샘플화면은 디자인을 적용하지 않았으니, 개별 사이트의 스타일에 맞게 코딩하시기 바랍니다.</strong></h4>
<h3><strong>건조에 강한 실내식물</strong></h3>
<hr>

<%
	'apiKey - 농사로 Open API에서 신청 후 승인되면 확인가능
	apiKey = "nongsaroSampleKey"
	'서비스 명
	serviceName = "dryGarden"
	'오퍼레이션 명
	operationName = "dryGardenDtl"

	'XML 받을 URL 생성
	parameter = "/" & serviceName & "/" & operationName
	parameter = parameter & "?apiKey="&apiKey
	parameter = parameter & "&cntntsNo=" & Request("cntntsNo")

	targetURL = "http://api.nongsaro.go.kr/service" & parameter

	'농사로 Open API 통신 시작
	Set xmlHttp = Server.CreateObject("Microsoft.XMLHTTP")
	xmlHttp.Open "GET", targetURL, False
	xmlHttp.Send

	Set oStream = CreateObject("ADODB.Stream")
	oStream.Open
	oStream.Position = 0
	oStream.Type = 1
	oStream.Write xmlHttp.ResponseBody
	oStream.Position = 0
	oStream.Type = 2
	oStream.Charset = "utf-8"
	sText = oStream.ReadText
	oStream.Close

	Set xmlDOM = server.CreateObject("MSXML.DOMDOCUMENT")
	xmlDOM.async = False
	xmlDOM.LoadXML sText
	'농사 Open API 통신 끝

	Set item = xmlDOM.SelectNodes("//body")
	cnt = item(0).childNodes.length

	If cnt = 0 Then
		Response.Write("<h3>조회한 정보가 없습니다.</h3>")
	Else
		'컨텐츠 번호
		Set cntntsNo = xmlDOM.SelectNodes("//cntntsNo")
		If Not cntntsNo(0) Is Nothing Then cntntsNoText= cntntsNo(0).Text Else cntntsNoText = "" End If
		'분류_명
		Set clNm = xmlDOM.SelectNodes("//clNm")
		If Not clNm(0) Is Nothing Then clNmText= clNm(0).Text Else clNmText = "" End If
		'분류_코드설명
		Set clCodeDc = xmlDOM.SelectNodes("//clCodeDc")
		If Not clCodeDc(0) Is Nothing Then clCodeDcText= clCodeDc(0).Text Else clCodeDcText = "" End If
		'학명
		Set scnm = xmlDOM.SelectNodes("//scnm")
		If Not scnm(0) Is Nothing Then scnmText= scnm(0).Text Else scnmText = "" End If
		'유통명
		Set distbNm = xmlDOM.SelectNodes("//distbNm")
		If Not distbNm(0) Is Nothing Then distbNmText= distbNm(0).Text Else distbNmText = "" End If
		'원산지
		Set orgplce = xmlDOM.SelectNodes("//orgplce")
		If Not orgplce(0) Is Nothing Then orgplceText= orgplce(0).Text Else orgplceText = "" End If
		'형태분류_명
		Set stleSeNm = xmlDOM.SelectNodes("//stleSeNm")
		If Not stleSeNm(0) Is Nothing Then stleSeNmText= stleSeNm(0).Text Else stleSeNmText = "" End If
		'꽃
		Set flwrInfo = xmlDOM.SelectNodes("//flwrInfo")
		If Not flwrInfo(0) Is Nothing Then flwrInfoText= flwrInfo(0).Text Else flwrInfoText = "" End If
		'엽색변화
		Set lfclChngeInfo = xmlDOM.SelectNodes("//lfclChngeInfo")
		If Not lfclChngeInfo(0) Is Nothing Then lfclChngeInfoText= lfclChngeInfo(0).Text Else lfclChngeInfoText = "" End If
		'뿌리형태_명
		Set rdxStleNm = xmlDOM.SelectNodes("//rdxStleNm")
		If Not rdxStleNm(0) Is Nothing Then rdxStleNmText= rdxStleNm(0).Text Else rdxStleNmText = "" End If
		'생장형
		Set grwtInfo = xmlDOM.SelectNodes("//grwtInfo")
		If Not grwtInfo(0) Is Nothing Then grwtInfoText= grwtInfo(0).Text Else grwtInfoText = "" End If
		'생장속도_명
		Set grwtseVeNm = xmlDOM.SelectNodes("//grwtseVeNm")
		If Not grwtseVeNm(0) Is Nothing Then grwtseVeNmText= grwtseVeNm(0).Text Else grwtseVeNmText = "" End If
		'생육온도
		Set grwhTpInfo = xmlDOM.SelectNodes("//grwhTpInfo")
		If Not grwhTpInfo(0) Is Nothing Then grwhTpInfoText= grwhTpInfo(0).Text Else grwhTpInfoText = "" End If
		'월동온도
		Set pswntrTpInfo = xmlDOM.SelectNodes("//pswntrTpInfo")
		If Not pswntrTpInfo(0) Is Nothing Then pswntrTpInfoText= pswntrTpInfo(0).Text Else pswntrTpInfoText = "" End If
		'특성
		Set chartrInfo = xmlDOM.SelectNodes("//chartrInfo")
		If Not chartrInfo(0) Is Nothing Then chartrInfoText= chartrInfo(0).Text Else chartrInfoText = "" End If
		'광
		Set lighttInfo = xmlDOM.SelectNodes("//lighttInfo")
		If Not lighttInfo(0) Is Nothing Then lighttInfoText= lighttInfo(0).Text Else lighttInfoText = "" End If
		'물주기
		Set waterCycleInfo = xmlDOM.SelectNodes("//waterCycleInfo")
		If Not waterCycleInfo(0) Is Nothing Then waterCycleInfoText= waterCycleInfo(0).Text Else waterCycleInfoText = "" End If
		'번식
		Set prpgtInfo = xmlDOM.SelectNodes("//prpgtInfo")
		If Not prpgtInfo(0) Is Nothing Then prpgtInfoText= prpgtInfo(0).Text Else prpgtInfoText = "" End If
		'고온다습
		Set hgtmMhmrInfo = xmlDOM.SelectNodes("//hgtmMhmrInfo")
		If Not hgtmMhmrInfo(0) Is Nothing Then hgtmMhmrInfoText= hgtmMhmrInfo(0).Text Else hgtmMhmrInfoText = "" End If
		'병충해
		Set dlthtsInfo = xmlDOM.SelectNodes("//dlthtsInfo")
		If Not dlthtsInfo(0) Is Nothing Then dlthtsInfoText= dlthtsInfo(0).Text Else dlthtsInfoText = "" End If
		'병충해
		Set dlthtsInfo = xmlDOM.SelectNodes("//dlthtsInfo")
		If Not dlthtsInfo(0) Is Nothing Then dlthtsInfoText= dlthtsInfo(0).Text Else dlthtsInfoText = "" End If
		'관리수준_명
		Set manageLevelNm = xmlDOM.SelectNodes("//manageLevelNm")
		If Not manageLevelNm(0) Is Nothing Then manageLevelNmText= manageLevelNm(0).Text Else manageLevelNmText = "" End If
		'관리요구도_명
		Set manageDemandNm = xmlDOM.SelectNodes("//manageDemandNm")
		If Not manageDemandNm(0) Is Nothing Then manageDemandNmText= manageDemandNm(0).Text Else manageDemandNmText = "" End If
		'비료
		Set frtlzrInfo = xmlDOM.SelectNodes("//frtlzrInfo")
		If Not frtlzrInfo(0) Is Nothing Then frtlzrInfoText= frtlzrInfo(0).Text Else frtlzrInfoText = "" End If
		'배치장소
		Set batchPlaceInfo = xmlDOM.SelectNodes("//batchPlaceInfo")
		If Not batchPlaceInfo(0) Is Nothing Then batchPlaceInfoText= batchPlaceInfo(0).Text Else batchPlaceInfoText = "" End If
		'팁
		Set tipInfo = xmlDOM.SelectNodes("//tipInfo")
		If Not tipInfo(0) Is Nothing Then tipInfoText= tipInfo(0).Text Else tipInfoText = "" End If
		'식물명
		Set cntntsSj = xmlDOM.SelectNodes("//cntntsSj")
		If Not cntntsSj(0) Is Nothing Then cntntsSjText= cntntsSj(0).Text Else cntntsSjText = "" End If
		'대표이미지1
		Set mainImgUrl1 = xmlDOM.SelectNodes("//mainImgUrl1")
		If Not mainImgUrl1(0) Is Nothing Then mainImgUrl1Text= mainImgUrl1(0).Text Else mainImgUrl1Text = "" End If
		'대표이미지2
		Set mainImgUrl2 = xmlDOM.SelectNodes("//mainImgUrl2")
		If Not mainImgUrl2(0) Is Nothing Then mainImgUrl2Text= mainImgUrl2(0).Text Else mainImgUrl2Text = "" End If
		'광 적응성 이미지1
		Set lightImgUrl1 = xmlDOM.SelectNodes("//lightImgUrl1")
		If Not lightImgUrl1(0) Is Nothing Then lightImgUrl1Text= lightImgUrl1(0).Text Else lightImgUrl1Text = "" End If
		'광 적응성 이미지2
		Set lightImgUrl2 = xmlDOM.SelectNodes("//lightImgUrl2")
		If Not lightImgUrl2(0) Is Nothing Then lightImgUrl2Text= lightImgUrl2(0).Text Else lightImgUrl2Text = "" End If
		'광 적응성 이미지3
		Set lightImgUrl3 = xmlDOM.SelectNodes("//lightImgUrl3")
		If Not lightImgUrl3(0) Is Nothing Then lightImgUrl3Text= lightImgUrl3(0).Text Else lightImgUrl3Text = "" End If

%>
		<table  border="1" cellspacing="0" cellpadding="0">
		<colgroup>
			<col width="15%">
			<col width="*">
		</colgroup>
		<tr>
            <th>식물이미지</th>
            <td>
				<%
					If mainImgUrl1Text <> "" Then
				%>
						<img src="<%=mainImgUrl1Text%>" />
				<%
					End If
				%>
				<%
					If mainImgUrl2Text <> "" Then
				%>
						<img src="<%=mainImgUrl2Text%>" />
				<%
					End If
				%>

            </td>
        </tr>
		<tr>
            <th>식물명</th>
            <td><%=cntntsSjText%></td>
        </tr>
		<tr>
            <th>과(분류)명</th>
            <td><%=clNmText%></td>
        </tr>
		<tr>
            <th>과(분류) 설명</th>
            <td><%=clCodeDcText%></td>
        </tr>
		<tr>
            <th>학명</th>
            <td><%=scnmText%></td>
        </tr>
		<tr>
            <th>유통명</th>
            <td><%=distbNmText%></td>
        </tr>
		<tr>
            <th>원산지</th>
            <td><%=orgplceText%></td>
        </tr>
		<tr>
            <th>형태분류</th>
            <td><%=stleSeNmText%></td>
        </tr>
		<tr>
            <th>꽃</th>
            <td><%=flwrInfoText%></td>
        </tr>
		<tr>
            <th>엽색변화</th>
            <td><%=lfclChngeInfoText%></td>
        </tr>
		<tr>
            <th>뿌리형태</th>
            <td><%=rdxStleNmText%></td>
        </tr>
		<tr>
            <th>생장형</th>
            <td><%=grwtInfoText%></td>
        </tr>
		<tr>
            <th>생장속도</th>
            <td><%=grwtseVeNmText%></td>
        </tr>
		<tr>
            <th>생육온도</th>
            <td><%=grwhTpInfoText%></td>
        </tr>
		<tr>
            <th>월동온도</th>
            <td><%=pswntrTpInfoText%></td>
        </tr>
		<tr>
            <th>특성</th>
            <td><%=chartrInfoText%></td>
        </tr>
		<tr>
            <th>광</th>
            <td><%=lighttInfoText%></td>
        </tr>
		<tr>
            <th>물주기</th>
            <td><%=waterCycleInfoText%></td>
        </tr>
		<tr>
            <th>번식</th>
            <td><%=prpgtInfoText%></td>
        </tr>
		<tr>
            <th>고온다습</th>
            <td><%=hgtmMhmrInfoText%></td>
        </tr>
		<tr>
            <th>병충해</th>
            <td><%=dlthtsInfoText%></td>
        </tr>
		<tr>
            <th>관리수준</th>
            <td><%=manageLevelNmText%></td>
        </tr>
		<tr>
            <th>관리요구도</th>
            <td><%=manageDemandNmText%></td>
        </tr>
		<tr>
            <th>비료</th>
            <td><%=frtlzrInfoText%></td>
        </tr>
		<tr>
            <th>배치장소</th>
            <td><%=batchPlaceInfoText%></td>
        </tr>
		<tr>
            <th>Tip</th>
            <td><%=tipInfoText%></td>
        </tr>
		<tr>
            <th>실내 광 적응성(배치 전)</th>
            <td>
				<%
					If lightImgUrl1Text <> "" Then
				%>
						<img src="<%=lightImgUrl1Text%>" />
				<%
					End If
				%>
            </td>
        </tr>
		<tr>
            <th>실내 광 적응성(6개월 후 발코니 창측)</th>
            <td>
				<%
					If lightImgUrl2Text <> "" Then
				%>
						<img src="<%=lightImgUrl2Text%>" />
				<%
					End If
				%>
            </td>
        </tr>
		<tr>
            <th>실내 광 적응성(6개월 후 발코니 내측)</th>
            <td>
		            	<%
					If lightImgUrl3Text <> "" Then
				%>
						<img src="<%=lightImgUrl3Text%>" />
				<%
					End If
				%>
            </td>
        </tr>
	</table>
<%
	End If
%>
<input type="button" onclick="javascript:fncList();" value="목록"/>
<form name="searchApiForm">
<%
	searchNmArr = Array("pageNo","sType", "sText", "wordType", "word", "sClCode", "stleSeCodeVal", "rdxStleCodeVal", "grwtseVeCodeVal", "manageLevelCodeVal", "manageDemandCodeVal")
	For i=0 To UBound(searchNmArr)
	Response.Write("<input type='hidden' name='"&searchNmArr(i)&"' value='"&Request(searchNmArr(i))&"' />")
	Next
%>
</from>
<script type="text/javascript">
//목록이동
function fncList(){
	with(document.searchApiForm){
		method="get";
		action = "dryGardenList.asp";
		target = "_self";
		submit();
	}
}
</script>
</body>
</html>
