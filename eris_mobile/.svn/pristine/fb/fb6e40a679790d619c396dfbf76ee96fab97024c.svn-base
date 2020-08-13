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
	<meta name="format-detection" content="telephone=no">
	<title>Sound Test</title>
	
	<!-- eGovFrame Common import -->
	<link rel="stylesheet" href="<c:url value='/css/egovframework/mbl/cmm/jquery.mobile-1.4.5.css'/>"/>
	<link rel="stylesheet" href="<c:url value='/css/egovframework/mbl/cmm/EgovMobile-1.4.5.css'/>"/>
	<script type="text/javascript" src="<c:url value='/js/egovframework/mbl/cmm/jquery-1.11.2.js'/>"></script>
	
		
	<script type="text/javascript" src="<c:url value='/js/egovframework/mbl/cmm/jquery.mobile-1.4.5.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/egovframework/mbl/cmm/EgovMobile-1.4.5.js'/>"></script>
  
	<!-- 신규공통컴포넌트 import -->
	<link rel="stylesheet" href="<c:url value='/css/egovframework/mbl/mcomd/egovMcomd.css'/>"/>
</head>
	
<body>

	<!--리스트 페이지 -->
	<div data-role="header" data-position="inline" data-theme="z">
		<a href="<c:url value='/index.jsp'/>" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
		<h1><img src="<c:url value='/images/egovframework/mbl/mcomd/h1_logo.png'/>"/></h1>
		<div class="ui-body-a mcomd-title"><h3>사운드 테스트</h3></div>
	</div>
		
	<div data-role="fieldcontain" class="com-logId">
		<input type="button" onClick="audio.play();" value="PLAY"/>
		<input type="button" onClick="audio.pause();" value="PAUSE"/>
		<label><strong>시간(초)</strong></label>
		<input type="number" onChange="audio.currentTime=this.value"/>
		<label><strong>볼륨(0.0~1.0) 아이폰안됨</strong></label>
		<input type="number" onChange="audio.volume=this.value" />
		<input type="button" onClick="onMute();" value="음소거"/>
		<input type="button" onClick="releaseMute();" value="음소거해제"/>
	</div>
		
		
	<div data-role="footer" data-theme="z" data-position="fixed" class="egovBar">
		<h4>Copyright(c)2011 Ministry of Government Administration and Home Affairs.</h4>
	</div>
	
	<script type="text/javascript">
		
		// Audio 객체생성 
		var audio = new Audio();
		audio.src = "<c:url value='/media/testSound.mp3'/>";
		
		function onMute() {
			audio.muted = true;    // 음소거
		}
		
		function releaseMute() {
			audio.muted = false;    
		}
		
	</script>

</body>
</html>