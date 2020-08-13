<%
 /**
  * @Class Name : EgovMobilePhotoList.jsp
  * @Description : EgovMobilePhotoList 화면
  * @Modification Information
  * @
  * @  수정일   	수정자		수정내용
  * @ ----------	------		---------------------------
  * @ 2011.08.11	정홍규		최초 생성
  *
  *  @author 정홍규 
  *  @since 2011.08.11
  *  @version 1.
  *  @see
  *  
  */
%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> 
<head> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>모바일 화면</title>
      
	<!-- eGovFrame Common import -->
	<link rel="stylesheet" href="<c:url value='/css/egovframework/mbl/cmm/jquery.mobile-1.4.5.css'/>"/>
	<link rel="stylesheet" href="<c:url value='/css/egovframework/mbl/cmm/EgovMobile-1.4.5.css'/>"/>
	<script type="text/javascript" src="<c:url value='/js/egovframework/mbl/cmm/jquery-1.11.2.js'/>"></script>
	
		
	<script type="text/javascript" src="<c:url value='/js/egovframework/mbl/cmm/jquery.mobile-1.4.5.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/egovframework/mbl/cmm/EgovMobile-1.4.5.js'/>"></script>
  
	<!-- 신규공통컴포넌트 import -->
	<link rel="stylesheet" href="<c:url value='/css/egovframework/mbl/mcomd/egovMcomd.css'/>"/>
	<script type="text/javascript">
		var contextPath = "${pageContext.request.contextPath}";
		
		$(document).ready(function() {
			if (!('url' in window) && ('webkitURL' in window)) {
				window.URL = window.webkitURL;
			}
			
			$('#camcorder').change(function(e) {
				$('#mov').attr('src', URL.createObjectURL(e.target.files[0]));
			});
		});
	</script>
	
	<script type="text/javascript" src="<c:url value='/js/egovframework/mbl/com/mpa/mpa.js'/>"></script>
</head>
	
<body>

	<!--리스트 페이지 -->
	<div id="photoList" data-role="page" data-theme="d">
		<div data-role="header" data-position="inline" data-theme="z">
			<a href="<c:url value='/index.jsp'/>" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
			<h1><img src="<c:url value='/images/egovframework/mbl/mcomd/h1_logo.png'/>"/></h1>
			<div class="ui-body-a mcomd-title"><h3>번역api</h3></div>
		</div>
		
		<div data-role="content">
			<form name="textForm" method="post">
				<textarea id="beforText"></textarea>
			</form>
			<button id="translate" onClick="googleTranslate()">▼구글</button>
			<button id="translate" onClick="papagoTranslate()">▼파파고</button>
			<textarea id="afterText"></textarea>
		</div>
		
		
		<div data-role="footer" data-theme="z" data-position="fixed" class="egovBar">
			<h4>Copyright(c)2011 Ministry of Government Administration and Home Affairs.</h4>
		</div>
	</div>
	
	<script type="text/javascript">
		function googleTranslate() {
			
			$.ajax({
	 			url:"<c:url value='/eris/dcr/googleTranslate.mdo'/>",
	            type:"POST",
	            data: {"beforeText" : $("#beforText").val()},
	            dataType   : "json",
	            success: function(data){
	            	$("#afterText").val(data.afterText);
				}	// end callback
			});
		}
	
		function papagoTranslate() {
			
			$.ajax({
	 			url:"<c:url value='/eris/dcr/papagoTranslate.mdo'/>",
	            type:"POST",
	            data: {"beforeText" : $("#beforText").val()},
	            dataType   : "json",
	            success: function(data){
	            	$("#afterText").val(data.afterText);
				}	// end callback
			});
			
		}
	</script>

</body>
</html>