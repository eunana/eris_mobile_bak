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
	
	<script type="text/javascript" src="<c:url value='/js/egovframework/mbl/com/mpa/mpa.js'/>"></script>
</head>
	
<body>

	<!--리스트 페이지 -->
		<div data-role="header" data-position="inline" data-theme="z">
			<a href="<c:url value='/index.jsp'/>" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
			<h1><img src="<c:url value='/images/egovframework/mbl/mcomd/h1_logo.png'/>"/></h1>
			<div class="ui-body-a mcomd-title"><h3>카카오맵</h3></div>
		</div>
		
		<div id="map" style="width:100%;height:400px;">
	    	<iframe id="daumMapFrame" src='https://m.map.kakao.com' width="100%" height="400px"></iframe>
	    </div>
		
		<div data-role="footer" data-theme="z" data-position="fixed" class="egovBar">
			<h4>Copyright(c)2011 Ministry of Government Administration and Home Affairs.</h4>
		</div>
	
	<script type="text/javascript">
	
// 		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
// 	    mapOption = { 
// 	        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
// 	        level: 6 // 지도의 확대 레벨 
// 	    }; 
	
// 		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
// 		// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
// 		if (navigator.geolocation) {
		    
// 		    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
// 		    navigator.geolocation.getCurrentPosition(function(position) {
		        
// 		        var lat = position.coords.latitude, // 위도
// 		            lon = position.coords.longitude; // 경도
		        
// 		        var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
// 		            message = '<div>내위치</div>'; // 인포윈도우에 표시될 내용입니다
		        
// 		        // 마커와 인포윈도우를 표시합니다
// 		        displayMarker(locPosition, message);
		            
// 	      });
	    
// 		} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
	    
// 		    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
// 		        message = 'geolocation을 사용할수 없어요..'
		        
// 		    displayMarker(locPosition, message);
// 		}
		
// 		// 지도에 마커와 인포윈도우를 표시하는 함수입니다
// 		function displayMarker(locPosition, message) {
		
// 		    // 마커를 생성합니다
// 		    var marker = new kakao.maps.Marker({  
// 		        map: map, 
// 		        position: locPosition
// 		    }); 
		    
// 		    var iwContent = message, // 인포윈도우에 표시할 내용
// 		        iwRemoveable = true;
		
// 		    // 인포윈도우를 생성합니다
// 		    var infowindow = new kakao.maps.InfoWindow({
// 		        content : iwContent,
// 		        removable : iwRemoveable
// 		    });
		    
// 		    // 인포윈도우를 마커위에 표시합니다 
// 		    infowindow.open(map, marker);
		    
// 		    // 지도 중심좌표를 접속위치로 변경합니다
// 		    map.setCenter(locPosition);      
// 		}    
		
// 		// 마커를 표시할 위치와 title 객체 배열입니다 
// 		var positions = [
// 		    {
// 		        title: '테스트', 
// 		        latlng: new kakao.maps.LatLng(35.83658979968666, 128.54419119874078)
// 		    },
// 		    {
// 		        title: '송현역', 
// 		        latlng: new kakao.maps.LatLng(35.830781563589404, 128.55155929245285)
// 		    }
// 		];

// 		// 마커 이미지의 이미지 주소입니다
// 		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
		    
// 		for (var i = 0; i < positions.length; i ++) {
		    
// 		    // 마커 이미지의 이미지 크기 입니다
// 		    var imageSize = new kakao.maps.Size(24, 35); 
		    
// 		    // 마커 이미지를 생성합니다    
// 		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
		    
// 		    // 마커를 생성합니다
// 		    var marker = new kakao.maps.Marker({
// 		        map: map, // 마커를 표시할 지도
// 		        position: positions[i].latlng, // 마커를 표시할 위치
// 		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
// 		        image : markerImage // 마커 이미지 
// 		    });
// 		}
	</script>
	

</body>
</html>