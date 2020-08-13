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
	
	<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=eb9a72baas"></script>
	<script type="text/javascript" src="<c:url value='/js/egovframework/mbl/com/mpa/mpa.js'/>"></script>
</head>
	
<body>

	<!--리스트 페이지 -->
		<div data-role="header" data-position="inline" data-theme="z">
			<a href="<c:url value='/index.jsp'/>" data-ajax="false" data-icon="home" class="ui-btn-left">메인</a>
			<h1><img src="<c:url value='/images/egovframework/mbl/mcomd/h1_logo.png'/>"/></h1>
			<div class="ui-body-a mcomd-title"><h3>네이버맵</h3></div>
		</div>

		<div id="map" style="width:100%;height:400px;"></div>
		<div data-role="fieldcontain" class="com-logId">
			<input type="button" value="병원검색" onClick="setHospital()" />
		</div>
		<div data-role="footer" data-theme="z" data-position="fixed" class="egovBar">
			<h4>Copyright(c)2011 Ministry of Government Administration and Home Affairs.</h4>
		</div>
	

	<script type="text/javascript">
	
	// map 셋팅
	var map = new naver.maps.Map('map', {
	    center: new naver.maps.LatLng(33.450701, 126.570667),
	    zoom: 15,
	    mapTypeId: naver.maps.MapTypeId.NORMAL
	});

	// 길찾기 요청 정보
	var start = null;		// 출발지 
	var goal = null;		// 도착지 
	var polyline = null;
	// 현재 위치 표시
	function onSuccessGeolocation(position) {
	    var location = new naver.maps.LatLng(position.coords.latitude,
	                                         position.coords.longitude);

	    map.setCenter(location); // 얻은 좌표를 지도의 중심으로 설정합니다.
	    start = position.coords.longitude + ',' + position.coords.latitude;
	    
        var markerOptions = 
           {
				map:map,
				position: location,
				icon: 
               { 
              		url: 'http://static.naver.com/maps2/icons/pin_spot2.png',
       				size: new naver.maps.Size(24, 37),
         			origin: new naver.maps.Point(0, 0),
               		anchor: new naver.maps.Point(11, 35)
               }
           };
           
           var marker = new naver.maps.Marker(markerOptions);
           marker.setMap(map);
	}

	function onErrorGeolocation() {
	    var center = map.getCenter();

	    infowindow.setContent('<div>' +
	        '<h5 style="margin-bottom:5px;color:#f00;">Geolocation failed!</h5>'+ "latitude: "+ center.lat() +"<br />longitude: "+ center.lng() +'</div>');

	    infowindow.open(map, center);
	}

	$(window).on("load", function() {
	    if (navigator.geolocation) {
	        /**
	         * navigator.geolocation 은 Chrome 50 버젼 이후로 HTTP 환경에서 사용이 Deprecate 되어 HTTPS 환경에서만 사용 가능 합니다.
	         * http://localhost 에서는 사용이 가능하며, 테스트 목적으로, Chrome 의 바로가기를 만들어서 아래와 같이 설정하면 접속은 가능합니다.
	         * chrome.exe --unsafely-treat-insecure-origin-as-secure="http://example.com"
	         */
	         
	        navigator.geolocation.getCurrentPosition(onSuccessGeolocation, onErrorGeolocation);
	    } else {
	    	// Geolocation 지원 안하는 기기일 경우
	        var center = map.getCenter();
	        infowindow.setContent('<div style="padding:20px;"><h5 style="margin-bottom:5px;color:#f00;">Geolocation not supported</h5></div>');
	        infowindow.open(map, center);
	    }

	});
	
	/* 마커 추가 */
	function addMarker(hsptlData) 
	{
	         var position = new naver.maps.LatLng(hsptlData.latitude, hsptlData.longitude);
	         var markerOptions = 
	            {
	            	map:map,
	                position: position,
	                icon: 
	                {
	                    content: '<img src="http://static.naver.net/maps/mantle/2x/marker-default.png" alt="" ' +
	                    'style="margin: 0px; padding: 0px; border: 0px solid transparent; display: block; max-width: none; max-height: none; ' +
	                    '-webkit-user-select: none; position: absolute; width: 22px; height: 35px; left: 0px; top: 0px;">'
	                }
	            };
	            
	            var marker = new naver.maps.Marker(markerOptions);
	            marker.setMap(map);
	            
	            // marker 클릭시 이벤트
	            // 해당하는 maker 정보 뜨게 해야됨
	            // 마커 정보 셋팅
	            var contentString = [
						        '<div class="iw_inner">',
						        '   <h3>',hsptlData.dutyName,'</h3>',
						        '   <p>',hsptlData.dutyAddr,'<br />',
						        '       <br />',
								'		<center><button id="driving" onClick="setDriving()">도착</button></center>',
						        '   </p>',
						        '</div>'
						    ].join('');
	            
	            // 정보창 생성 및 옵션
				var infowindow = new naver.maps.InfoWindow({
					content: contentString,
					maxWidth: 140,
				    backgroundColor: "#eee",
				    borderColor: "#2db400",
				    borderWidth: 5,
				    anchorSize: new naver.maps.Size(30, 30),
				    anchorSkew: true,
				    anchorColor: "#eee",
				    pixelOffset: new naver.maps.Point(20, -20)
				});
	         
	    		naver.maps.Event.addListener(marker, "click", function(e) {
	    			goal = hsptlData.longitude + ',' + hsptlData.latitude;
	    			
	     		    if (infowindow.getMap()) {
	     		        infowindow.close();
	     		    } else {
	     		        infowindow.open(map, marker);
	     		    }
	    		});
	}

		/* 근처 병원 검색 */
		// 추후 현재 현재위치 파라미터로 던져서 내 근처 병원 받아와야됨
		// 병원 영업시간 끝나면 안나오는듯... 나중에 문의해보기
		function setHospital() {
		 	$.ajax({
	 			url:"<c:url value='/eris/dcr/srchHsptl.mdo'/>",
	            type:"POST",
	            dataType   : "json",
	            success: function(data){
		              
	               var hsptlList = eval(data.hsptlList);
		               
	               for(var i=0; i<hsptlList.length; i++) {
	            	   addMarker(hsptlList[i]);
	               } // end for
	    	    		
				}	// end callback
			});
		}
		
		
		/* 길찾기 */
		function setDriving() {
			$.ajax({
	 			url:"<c:url value='/eris/dcr/setDriving.mdo'/>",
	            type:"POST",
	            data: {"start" : start, "goal" : goal},
	            dataType   : "json",
	            success: function(data){
	            	
	            	var longitudeList = data.longitudeList;
	            	var latitudeList = data.latitudeList;
	            	var pol=[];
	            	
	            	for(var i=0; i<longitudeList.length; i++) {
	            		pol.push(new naver.maps.LatLng(latitudeList[i], longitudeList[i]));
	            	}
		
	            	// 기존 polyline 초기화
	            	if(polyline != null) {
	            		polyline.setMap(null);
	            	}
	            	
	            	// polyline 그리기 (옵션 추가가능)
            		polyline = new naver.maps.Polyline({
	        		    map: map,
	        		    path: pol
	        		});
            		
				}	// end callback
			});
		}
	
		
	</script>
	
</body>
</html>