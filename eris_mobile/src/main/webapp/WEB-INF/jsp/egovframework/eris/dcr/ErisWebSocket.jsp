<%
 /**
  * @Class Name : ErisWebSocket.jsp
  * @Description : ErisWebSocket 화면
  * @Modification Information
  * @
  * @  수정일   	수정자		수정내용
  * @ ----------	------		---------------------------
  * @ 2020.08.11	고은아		최초 생성
  *
  *  @author 고은아
  *  @since 2020.08.11
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
	
	<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=eb9a72baas"></script>
	<script type="text/javascript" src="<c:url value='/js/egovframework/mbl/com/mpa/mpa.js'/>"></script>
</head>
	
<body>

	<!--리스트 페이지 -->
	<div data-role="header" data-position="inline" data-theme="z">
		<a href="<c:url value='/index.jsp'/>" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
		<h1><img src="<c:url value='/images/egovframework/mbl/mcomd/h1_logo.png'/>"/></h1>
		<div class="ui-body-a mcomd-title"><h3>채팅</h3></div>
	</div>
	
	
	<div data-role="content" >
		<textarea id="messageTextArea"></textarea>
	
		<input type="text" id="inputText"/>
		<input type="button" id="send" value="보내기" onClick="sendMessage()"/>
	</div>
	
	
	<div data-role="footer" data-theme="z" data-position="fixed" class="egovBar">
		<h4>Copyright(c)2011 Ministry of Government Administration and Home Affairs.</h4>
	</div>
	
	<script type="text/javascript">
	
		//웹소켓 생성
		var webSocket = new WebSocket("ws://localhost:8080/websocket");
		webSocket.onopen = function(event) { socketOpen(event);};
		webSocket.onclose = function(event) { socketClose(event);};
		webSocket.onmessage = function(event) { socketMessage(event);};
		webSocket.onerror = function(event) { socketError(event);};
	
		//웹소켓 연결
		function socketOpen(event){
			console.log("연결 완료");
		}

		  //웹소켓 닫힘
		  function socketClose(event){
		  	 console.log("웹소켓이 닫혔습니다.");
		  	 
		     // 웹소켓이 닫혀있으면 재연결을 시도합니다.
		     // webSocket이 close되면 오브젝트의 속성, 메서드가 사라지기 때문에,
		     // 해당 함수를 호출하여 초기화하여 줍니다.
		     webSocketInit(); 
		  }
		  
		  //메시지를 송신할 때 사용
		  function sendMessage(){
		     // 메시지 포맷
		     var message =  $("#inputText").val();
		     
		    //messageTextArea.value += "Send to Server => "+message.value+"\n";

		     // 세션리스트에 메시지를 송신한다.
		     webSocket.send(message);
		     
		     message.value = "";
		  }
		  
		  
		  //웹소켓 메시지 수신
		  function socketMessage(event){
		  	  var receiveData = event.data; // 수신 data
		      $("#messageTextArea").val( $("#messageTextArea").val() + receiveData + "\n");
		  }
		  
		  //웹소켓 에러
		  function socketError(event){
		  	alert("에러가 발생하였습니다.");
		  }
		  
		  //웹소켓 종료
		  function disconnect(){
		  	webSocket.close();
		  }
		
	</script>
	
</body>
</html>