<%@page import="java.io.InputStream"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.w3c.dom.NodeList"%>
<%@page import="org.w3c.dom.Node"%>
<%@page import="org.w3c.dom.Element"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="java.net.URL"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>건조에 강한 실내식물</title>
</head>
<body>
<h4><strong> * 샘플화면은 디자인을 적용하지 않았으니, 개별 사이트의 스타일에 맞게 코딩하시기 바랍니다.</strong></h4>
<h3><strong>건조에 강한 실내식물</strong></h3>
<hr>
<%
//인테러뱅 상세조회

	//apiKey - 농사로 Open API에서 신청 후 승인되면 확인 가능
	String apiKey="nongsaroSampleKey";
	//서비스 명
	String serviceName="dryGarden";
	//오퍼레이션 명
	String operationName="dryGardenDtl";

	//XML 받을 URL 생성
	String parameter = "/"+serviceName+"/"+operationName;
	parameter += "?apiKey="+ apiKey;
	parameter += "&cntntsNo="+ request.getParameter("cntntsNo");

	//서버와 통신
	URL apiUrl = new URL("http://api.nongsaro.go.kr/service"+parameter);
	InputStream apiStream = apiUrl.openStream();

	Document doc = null;
	try{
		//xml document
		doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apiStream);
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		apiStream.close();
	}
	String resultCode="";
	String resultMsg="";
	try{resultCode = doc.getElementsByTagName("resultCode").item(0).getFirstChild().getNodeValue();}catch(Exception e){resultCode = "";}
	try{resultMsg = doc.getElementsByTagName("resultMsg").item(0).getFirstChild().getNodeValue();}catch(Exception e){resultMsg = "";}

	//컨텐츠 번호
	String cntntsNo = "";
	//분류_명
	String clNm = "";
	//분류_코드설명
	String clCodeDc = "";
	//학명
	String scnm = "";
	//유통명
	String distbNm = "";
	//원산지
	String orgplce = "";
	//형태분류_명
	String stleSeNm = "";
	//꽃
	String flwrInfo = "";
	//엽색변화
	String lfclChngeInfo = "";
	//뿌리형태_명
	String rdxStleNm = "";
	//생장형
	String grwtInfo = "";
	//생장속도_명
	String grwtseVeNm = "";
	//생육온도
	String grwhTpInfo = "";
	//월동온도
	String pswntrTpInfo = "";
	//특성
	String chartrInfo = "";
	//광
	String lighttInfo = "";
	//물주기
	String waterCycleInfo = "";
	//번식
	String prpgtInfo = "";
	//고온다습
	String hgtmMhmrInfo = "";
	//병충해
	String dlthtsInfo = "";
	//관리수준_명
	String manageLevelNm = "";
	//관리요구도_명
	String manageDemandNm = "";
	//비료
	String frtlzrInfo = "";
	//배치장소
	String batchPlaceInfo = "";
	//팁
	String tipInfo = "";
	//식물명
	String cntntsSj = "";
	//대표이미지1
	String mainImgUrl1 = "";
	//대표이미지2
	String mainImgUrl2 = "";
	//광 적응성 이미지1
	String lightImgUrl1 = "";
	//광 적응성 이미지2
	String lightImgUrl2 = "";
	//광 적응성 이미지3
	String lightImgUrl3 = "";

	try{ cntntsNo 		= doc.getElementsByTagName("cntntsNo").item(0).getFirstChild().getNodeValue();}catch(Exception e){ cntntsNo = "";}
	try{ clNm 			= doc.getElementsByTagName("clNm").item(0).getFirstChild().getNodeValue();}catch(Exception e){ clNm = "";}
	try{ clCodeDc 		= doc.getElementsByTagName("clCodeDc").item(0).getFirstChild().getNodeValue();}catch(Exception e){ clCodeDc = "";}
	try{ scnm 			= doc.getElementsByTagName("scnm").item(0).getFirstChild().getNodeValue();}catch(Exception e){ scnm = "";}
	try{ distbNm 		= doc.getElementsByTagName("distbNm").item(0).getFirstChild().getNodeValue();}catch(Exception e){ distbNm = "";}
	try{ orgplce 		= doc.getElementsByTagName("orgplce").item(0).getFirstChild().getNodeValue();}catch(Exception e){ orgplce = "";}
	try{ stleSeNm 		= doc.getElementsByTagName("stleSeNm").item(0).getFirstChild().getNodeValue();}catch(Exception e){ stleSeNm = "";}
	try{ flwrInfo 		= doc.getElementsByTagName("flwrInfo").item(0).getFirstChild().getNodeValue();}catch(Exception e){ flwrInfo = "";}
	try{ lfclChngeInfo 	= doc.getElementsByTagName("lfclChngeInfo").item(0).getFirstChild().getNodeValue();}catch(Exception e){ lfclChngeInfo = "";}
	try{ rdxStleNm 		= doc.getElementsByTagName("rdxStleNm").item(0).getFirstChild().getNodeValue();}catch(Exception e){ rdxStleNm = "";}
	try{ grwtInfo 		= doc.getElementsByTagName("grwtInfo").item(0).getFirstChild().getNodeValue();}catch(Exception e){ grwtInfo = "";}
	try{ grwtseVeNm 	= doc.getElementsByTagName("grwtseVeNm").item(0).getFirstChild().getNodeValue();}catch(Exception e){ grwtseVeNm = "";}
	try{ grwhTpInfo 	= doc.getElementsByTagName("grwhTpInfo").item(0).getFirstChild().getNodeValue();}catch(Exception e){ grwhTpInfo = "";}
	try{ pswntrTpInfo 	= doc.getElementsByTagName("pswntrTpInfo").item(0).getFirstChild().getNodeValue();}catch(Exception e){ pswntrTpInfo = "";}
	try{ chartrInfo 	= doc.getElementsByTagName("chartrInfo").item(0).getFirstChild().getNodeValue();}catch(Exception e){ chartrInfo = "";}
	try{ lighttInfo 	= doc.getElementsByTagName("lighttInfo").item(0).getFirstChild().getNodeValue();}catch(Exception e){ lighttInfo = "";}
	try{ waterCycleInfo = doc.getElementsByTagName("waterCycleInfo").item(0).getFirstChild().getNodeValue();}catch(Exception e){ waterCycleInfo = "";}
	try{ prpgtInfo 		= doc.getElementsByTagName("prpgtInfo").item(0).getFirstChild().getNodeValue();}catch(Exception e){ prpgtInfo = "";}
	try{ hgtmMhmrInfo 	= doc.getElementsByTagName("hgtmMhmrInfo").item(0).getFirstChild().getNodeValue();}catch(Exception e){ hgtmMhmrInfo = "";}
	try{ dlthtsInfo 	= doc.getElementsByTagName("dlthtsInfo").item(0).getFirstChild().getNodeValue();}catch(Exception e){ dlthtsInfo = "";}
	try{ manageLevelNm 	= doc.getElementsByTagName("manageLevelNm").item(0).getFirstChild().getNodeValue();}catch(Exception e){ manageLevelNm = "";}
	try{ manageDemandNm = doc.getElementsByTagName("manageDemandNm").item(0).getFirstChild().getNodeValue();}catch(Exception e){ manageDemandNm = "";}
	try{ frtlzrInfo 	= doc.getElementsByTagName("frtlzrInfo").item(0).getFirstChild().getNodeValue();}catch(Exception e){ frtlzrInfo = "";}
	try{ batchPlaceInfo = doc.getElementsByTagName("batchPlaceInfo").item(0).getFirstChild().getNodeValue();}catch(Exception e){ batchPlaceInfo = "";}
	try{ tipInfo 		= doc.getElementsByTagName("tipInfo").item(0).getFirstChild().getNodeValue();}catch(Exception e){ tipInfo = "";}
	try{ cntntsSj 		= doc.getElementsByTagName("cntntsSj").item(0).getFirstChild().getNodeValue();}catch(Exception e){ cntntsSj = "";}
	try{ mainImgUrl1 	= doc.getElementsByTagName("mainImgUrl1").item(0).getFirstChild().getNodeValue();}catch(Exception e){ mainImgUrl1 = "";}
	try{ mainImgUrl2 	= doc.getElementsByTagName("mainImgUrl2").item(0).getFirstChild().getNodeValue();}catch(Exception e){ mainImgUrl2 = "";}
	try{ lightImgUrl1 	= doc.getElementsByTagName("lightImgUrl1").item(0).getFirstChild().getNodeValue();}catch(Exception e){ lightImgUrl1 = "";}
	try{ lightImgUrl2 	= doc.getElementsByTagName("lightImgUrl2").item(0).getFirstChild().getNodeValue();}catch(Exception e){ lightImgUrl2 = "";}
	try{ lightImgUrl3 	= doc.getElementsByTagName("lightImgUrl3").item(0).getFirstChild().getNodeValue();}catch(Exception e){ lightImgUrl3 = "";}


	if(resultCode.equals("00")){
%>
	<table  border="1" cellspacing="0" cellpadding="0">
		<colgroup>
			<col width="15%">
			<col width="*">
		</colgroup>
		<tr>
            <th>식물이미지</th>
            <td>
            	<%if(!"".equals(mainImgUrl1)){ %>
            		<img src="<%=mainImgUrl1%>" />
            	<%}%>
            	<%if(!"".equals(mainImgUrl2)){ %>
            		<img src="<%=mainImgUrl2%>" />
            	<%}%>
            </td>
        </tr>
		<tr>
            <th>식물명</th>
            <td><%=cntntsSj%></td>
        </tr>
		<tr>
            <th>과(분류)명</th>
            <td><%=clNm%></td>
        </tr>
		<tr>
            <th>과(분류) 설명</th>
            <td><%=clCodeDc%></td>
        </tr>
		<tr>
            <th>학명</th>
            <td><%=scnm%></td>
        </tr>
		<tr>
            <th>유통명</th>
            <td><%=distbNm%></td>
        </tr>
		<tr>
            <th>원산지</th>
            <td><%=orgplce%></td>
        </tr>
		<tr>
            <th>형태분류</th>
            <td><%=stleSeNm%></td>
        </tr>
		<tr>
            <th>꽃</th>
            <td><%=flwrInfo%></td>
        </tr>
		<tr>
            <th>엽색변화</th>
            <td><%=lfclChngeInfo%></td>
        </tr>
		<tr>
            <th>뿌리형태</th>
            <td><%=rdxStleNm%></td>
        </tr>
		<tr>
            <th>생장형</th>
            <td><%=grwtInfo%></td>
        </tr>
		<tr>
            <th>생장속도</th>
            <td><%=grwtseVeNm%></td>
        </tr>
		<tr>
            <th>생육온도</th>
            <td><%=grwhTpInfo%></td>
        </tr>
		<tr>
            <th>월동온도</th>
            <td><%=pswntrTpInfo%></td>
        </tr>
		<tr>
            <th>특성</th>
            <td><%=chartrInfo%></td>
        </tr>
		<tr>
            <th>광</th>
            <td><%=lighttInfo%></td>
        </tr>
		<tr>
            <th>물주기</th>
            <td><%=waterCycleInfo%></td>
        </tr>
		<tr>
            <th>번식</th>
            <td><%=prpgtInfo%></td>
        </tr>
		<tr>
            <th>고온다습</th>
            <td><%=hgtmMhmrInfo%></td>
        </tr>
		<tr>
            <th>병충해</th>
            <td><%=dlthtsInfo%></td>
        </tr>
		<tr>
            <th>관리수준</th>
            <td><%=manageLevelNm%></td>
        </tr>
		<tr>
            <th>관리요구도</th>
            <td><%=manageDemandNm%></td>
        </tr>
		<tr>
            <th>비료</th>
            <td><%=frtlzrInfo%></td>
        </tr>
		<tr>
            <th>배치장소</th>
            <td><%=batchPlaceInfo%></td>
        </tr>
		<tr>
            <th>Tip</th>
            <td><%=tipInfo%></td>
        </tr>
		<tr>
            <th>실내 광 적응성(배치 전)</th>
            <td>
            	<%if(!"".equals(lightImgUrl1)){ %>
            		<img src="<%=lightImgUrl1%>" />
            	<%}%>

            </td>
        </tr>
		<tr>
            <th>실내 광 적응성(6개월 후 발코니 창측)</th>
            <td>
            	<%if(!"".equals(lightImgUrl2)){ %>
            		<img src="<%=lightImgUrl2%>" />
            	<%}%>
            </td>
        </tr>
		<tr>
            <th>실내 광 적응성(6개월 후 발코니 내측)</th>
            <td>
            	<%if(!"".equals(lightImgUrl3)){ %>
            		<img src="<%=lightImgUrl3%>" />
            	<%}%>
            </td>
        </tr>
	</table>
<%
	}else{
		out.println(resultMsg);
	}
%>
<input type="button" onclick="javascript:fncList();" value="목록"/>
<form name="searchApiForm">
<%
String[] searchNmArr = {"sType", "sText", "wordType", "word", "sClCode", "stleSeCodeVal", "rdxStleCodeVal", "grwtseVeCodeVal", "manageLevelCodeVal", "manageDemandCodeVal"};
for(int i=0; i<searchNmArr.length; i++){
	out.println("<input type='hidden' name='"+searchNmArr[i]+"' value='"+request.getParameter(searchNmArr[i])+"' />");
}
%>
</form>
<script type="text/javascript">
//목록이동
function fncList(){
	with(document.searchApiForm){
		method="get";
		action = "dryGardenList.jsp";
		target = "_self";
		submit();
	}
}
</script>
</body>
</html>