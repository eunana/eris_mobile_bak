package egovframework.eris.dcr.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.mbl.com.oas.service.OpenApiVO;
import egovframework.rte.fdl.property.EgovPropertyService;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 개요
 * - web socket 통신 에 대한 Controller를 정의한다.
 *
 * 상세내용
 * - 
 * @author 고은아
 * @since 2020.08.11
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2020.08.06  고은아          최초 생성
 *
 * </pre>
 */

@ServerEndpoint(value="/websocket")
@Controller
public class ErisWebSocketController {
	
	static List<Session> sessionList = Collections.synchronizedList(new ArrayList<>());

	/** EgovPropertyService */
	@Resource(name="propertiesService")
	protected EgovPropertyService propertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ErisWebSocketController.class);
	
	
	/**
	 * 
	 * 
	 * @param 
	 * @return String
	 * @throws Exception
	 */
	
	@RequestMapping("/eris/dcr/erisWebSocket.mdo")
	public String erisNaverMap() throws Exception {
		// 권한 체크
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		return "egovframework/eris/dcr/ErisWebSocket";
	}
	
	
	 // 웹 소켓 연결시 호출
	  @OnOpen
	  public void handleOpen(Session userSession){
	    sessionList.add(userSession); // 웹 소켓 연결시 세션리스트에 추가
	  }
	  
	  // 웹소켓 메시지 수신시 호출
	  @OnMessage
	  public void handleMessage(String message, Session userSession){
		  
	  	try {
	  		for (Session session : sessionList) {
	  			if (!userSession.getId().equals(session.getId())) {
	  				session.getBasicRemote().sendText("YOU : " + message);
	  			}
	  		}
	  	} catch (IOException e) {
	  		e.printStackTrace();
	  	}
	  	
	  	
	  	try {
	  		
	  		Basic basic = userSession.getBasicRemote();
	  		basic.sendText( "ME : " + message);
	  		
	  	} catch (IOException e) {
	  		e.printStackTrace();
	  	}
	  	
	  }
	
	// 웹 소켓이 닫힐 때 세션 제거
	  @OnClose
	  public void handleClose(Session userSession){
	      sessionList.remove(userSession);
	  }
	  
	  /**
	  * 웹 소켓이 에러가 나면 호출되는 이벤트
	  * @param t
	  */
	  @OnError
	  public void handleError(Throwable t){
	      t.printStackTrace();
	  }
}