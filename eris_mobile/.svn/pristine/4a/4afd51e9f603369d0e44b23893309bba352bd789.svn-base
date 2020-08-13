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
	<title>Text To Speech</title>
	
	<!-- eGovFrame Common import -->
	<link rel="stylesheet" href="<c:url value='/css/egovframework/mbl/cmm/jquery.mobile-1.4.5.css'/>"/>
	<link rel="stylesheet" href="<c:url value='/css/egovframework/mbl/cmm/EgovMobile-1.4.5.css'/>"/>
	<script type="text/javascript" src="<c:url value='/js/egovframework/mbl/cmm/jquery-1.11.2.js'/>"></script>
	
		
	<script type="text/javascript" src="<c:url value='/js/egovframework/mbl/cmm/jquery.mobile-1.4.5.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/egovframework/mbl/cmm/EgovMobile-1.4.5.js'/>"></script>
  
	<!-- 신규공통컴포넌트 import -->
	<link rel="stylesheet" href="<c:url value='/css/egovframework/mbl/mcomd/egovMcomd.css'/>"/>
	
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
			<input id="ment" type="text" value="삼겹살" autocomplete="off">
			<input type="button" value="말하기" onClick="doSpeech();" />
			
			<button onclick="setLang('en-US'); ttsTest();">Click</button>
			<button onclick="doMuted();">음소거</button>
		</div>
		
		<select id="select-lang">
			<option value="ko-KR">한국어</option>
			<option value="ja-JP" selected>일본어</option>
			<option value="en-US">영어</option>
		</select>
		<textarea id="text" rows="5" cols="20"></textarea>
		<button id="btn-read">읽기</button>
		
		<div data-role="footer" data-theme="z" data-position="fixed" class="egovBar">
			<h4>Copyright(c)2011 Ministry of Government Administration and Home Affairs.</h4>
		</div>
	</div>
	
	<script type="text/javascript">
		var voices = [];
		function setVoiceList() {
			voices = window.speechSynthesis.getVoices();
		}
		
		setVoiceList();
		
		if (window.speechSynthesis.onvoiceschanged !== undefined) {
			window.speechSynthesis.onvoiceschanged = setVoiceList;
		}
		
		function speech(txt) {
			if (!window.speechSynthesis) {
				alert("음성 재생을 지원하지 않는 브라우저입니다. 크롬, 파이어폭스 등의 최신 브라우저를 이용하세요");
				return;
			}
			
			var lang = 'ko-KR';
			var utterThis = new SpeechSynthesisUtterance(txt);
			
			utterThis.onend = function (event) {
				console.log('end');
			};
			
			utterThis.onerror = function(event) {
				console.log('error', event);
			};
			
			var voiceFound = false;
			for (var i = 0; i < voices.length ; i++) {
				if (voices[i].lang.indexOf(lang) >= 0 || voices[i].lang.indexOf(lang.replace('-', '_')) >= 0) {
					utterThis.voice = voices[i];
					voiceFound = true;
				}
			}
			
			if (!voiceFound) {
				alert('voice not found');
				return;
			}
			
			utterThis.lang = lang;
			utterThis.pitch = 1;
			utterThis.rate = 1; //속도
			window.speechSynthesis.speak(utterThis);
		}
		
		function doSpeech() {
			speech($("#ment").val());
		};
		
		var msg = new SpeechSynthesisUtterance();
		msg.lang = 'en-US';
		function ttsTest() {
			//msg.rate = 0.4; // 0.1 ~ 10
			//msg.pitch = 1.5; // 0 ~ 2
			msg.rate = 0.4; // 0.1 ~ 10
			msg.pitch = 1.5; // 0 ~ 2
			
			msg.text = $("#ment").val();//"Hi my name is Hong Gil Dong.";
			speechSynthesis.speak(msg);
		}
		
		function setLang(langTxt){
			msg.lang = langTxt;
		}
		
		function doMuted() {
			document.getElementById("myAudio").muted = true;
		}
		
		function speak(text, opt_prop) {
			if (typeof SpeechSynthesisUtterance === "undefined" || typeof window.speechSynthesis === "undefined") {
				alert("이 브라우저는 음성 합성을 지원하지 않습니다.")
				return
			}
			
			window.speechSynthesis.cancel() // 현재 읽고있다면 초기화
			
			const prop = opt_prop || {}
			
			const speechMsg = new SpeechSynthesisUtterance()
			speechMsg.rate = prop.rate || 1 // 속도: 0.1 ~ 10
			speechMsg.pitch = prop.pitch || 1 // 음높이: 0 ~ 2
			speechMsg.lang = prop.lang || "ko-KR"
			speechMsg.text = text
			
			// SpeechSynthesisUtterance에 저장된 내용을 바탕으로 음성합성 실행
			window.speechSynthesis.speak(speechMsg)
		}
		
		// 이벤트 영역
		const selectLang = document.getElementById("select-lang")
		const text = document.getElementById("text")
		const btnRead = document.getElementById("btn-read")
		
		btnRead.addEventListener("click", e => {
			speak(text.value, {
				rate: 1,
				pitch: 1.2,
				lang: selectLang.options[selectLang.selectedIndex].value
			})
		})
	</script>

</body>
</html>