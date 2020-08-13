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
			var recorder = document.getElementById('recorder');
			var player = document.getElementById('player');
			
			recorder.addEventListener('change', function(e) {
				var file = e.target.files[0];
				// Do something with the audio file.
				player.src =  URL.createObjectURL(file);
			});
			
			//var player = document.getElementById('player');
			/* var handleSuccess = function(stream) {
				if (window.URL) {
					player.src = window.URL.createObjectURL(stream);
				} else {
					player.src = stream;
				}
			};
			
			navigator.mediaDevices.getUserMedia({ audio: true, video: false })
				.then(handleSuccess)
			
			var handleSuccess = function(stream) {
				var context = new AudioContext();
				var input = context.createMediaStreamSource(stream)
				var processor = context.createScriptProcessor(1024,1,1);
				
				source.connect(processor);
				processor.connect(context.destination);
				
				processor.onaudioprocess = function(e) {
					// Do something with the data, i.e Convert this to WAV
					console.log(e.inputBuffer);
				};
			};
			
			navigator.mediaDevices.getUserMedia({ audio: true, video: false })
				.then(handleSuccess) */
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
			<div class="ui-body-a mcomd-title"><h3>모바일사진앨범</h3></div>
		</div>
		
		<div data-role="content">
			<form name="PhotoListForm" method="post">
				<ul data-role="listview" class="photoList">
				</ul>
			</form>
		</div>
		
		<div data-role="fieldcontain" class="com-logId">
			<!-- <input type="file" accept="audio/*" capture="microphone"> -->
			<input type="file" accept="audio/*" capture="microphone" id="recorder">
			<br />
			<audio id="player" controls></audio>
			
			<a href="tel:010-3578-7462">010-3578-7462</a>
		</div>
		
		<div data-role="footer" data-theme="z" data-position="fixed" class="egovBar">
			<h4>Copyright(c)2011 Ministry of Government Administration and Home Affairs.</h4>
		</div>
	</div>
	

</body>
</html>