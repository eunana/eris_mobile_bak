package egovframework.eris.dcr.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.mbl.com.oas.service.OpenApiVO;
import egovframework.rte.fdl.property.EgovPropertyService;

import javax.annotation.Resource;
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


/**
 * 개요
 * - NAVER Map 에 대한 Controller를 정의한다.
 *
 * 상세내용
 * - 
 * @author 고은아
 * @since 2020.08.06
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
@Controller
public class ErisNaverMapController {

	/** EgovPropertyService */
	@Resource(name="propertiesService")
	protected EgovPropertyService propertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ErisNaverMapController.class);
	
	
	/**
	 * 네이버맵 api 연동 테스트
	 * 
	 * @param 
	 * @return String
	 * @throws Exception
	 */
	
	@RequestMapping("/eris/dcr/erisNaverMap.mdo")
	public String erisNaverMap() throws Exception {
		// 권한 체크
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		/*if (!isAuthenticated) {
			return "egovframework/mbl/com/uat/uia/EgovLoginUsr";
		}*/
		
		return "egovframework/eris/dcr/ErisNaverMap";
	}
	
	

	/**
	 * 근처 약국 데이터 조회
	 * 
	 * @param model
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	@RequestMapping("/eris/dcr/srchHsptl.mdo")
	public ModelAndView srchHsptl(ModelMap model) throws Exception {
		List hsptlList = new ArrayList();
		
		// 스크립트에서 처리하니깐 크로스도메인 이슈 발생 (https => http)
		// 병원 찾기 open api xml 방식으로만 데이터 교환 가능하기때문에 결과 데이터 파싱
		
		// parsing할 url 지정
		String url = "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncLcinfoInqire?"
				+ "ServiceKey=R72YEyXA%2BqHTJzonrRpv6PjlqBhNLF9I30zDAWMhjtNnjo0y6bK4gxGiYCDFrqIaqywR2Ez%2FAAebepbm6Ghb6w%3D%3D"
				+ "&WGS84_LON=128.543271"		// 병원경도
				+ "&WGS84_LAT=35.835204";		// 병원위도
//				+ "&pageNo=1"							// 페이지 번호
//				+ "&numOfRows=1";					// 목록 건수
		
		
		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(url);

		// root tag 
		doc.getDocumentElement().normalize();
		
		NodeList nList = doc.getElementsByTagName("item");
		System.out.println("파싱할 리스트 수 : "+ nList.getLength());  // 파싱할 리스트 수 
		
		for(int temp = 0; temp < nList.getLength(); temp++){		
			Node nNode = nList.item(temp);
			Map<String, Object> map = new HashMap();
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
								
				Element eElement = (Element) nNode;
				map.put("dutyAddr", getTagValue("dutyAddr", eElement));		// 병원주소
				map.put("dutyName", getTagValue("dutyName", eElement));	// 병원명
				map.put("latitude", getTagValue("latitude", eElement));			// 병원위도
				map.put("longitude", getTagValue("longitude", eElement));		// 병원경도
				
			}	// for end
			
			hsptlList.add(map);
			
		}	// if end
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject("hsptlList", hsptlList);
		
		return modelAndView;
	}
	
	// tag값의 정보를 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}
	
	
	/**
	 * 네이버 길찾기 api
	 * 
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	@RequestMapping("/eris/dcr/setDriving.mdo")
	public ModelAndView setDriving(@RequestParam Map<String, Object> param) throws Exception {
		
		String start = param.get("start").toString();
		String goal = param.get("goal").toString();
		String clientId = "eb9a72baas";
		String clientSecret = "iBQg7dcgys679watM6jRmCYLvPBKI3ZCUcZvuieX";
		
		StringBuffer response = null;
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			
			String apiURL = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving"
					+ "?start=" + start
					+ "&goal=" + goal;
			
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			con.setRequestMethod("GET");
	        con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);			// Client ID
	        con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);		// Client Secret
	        
            int responseCode = con.getResponseCode();
            BufferedReader br;
            
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            
            String inputLine;
            response = new StringBuffer();
            
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            
            br.close();
		
		} catch (Exception e) {
            System.out.println(e);
        }
        
       
		// JSON parsing
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(response.toString());
		JSONObject route = (JSONObject)obj.get("route");
		JSONArray traoptimal = (JSONArray)route.get("traoptimal");
		JSONObject traoptimalObj = (JSONObject)traoptimal.get(0);
		JSONArray path = (JSONArray)traoptimalObj.get("path");
		
		List<String> longitudeList = new ArrayList<String>();	// 경도
		List<String> latitudeList = new ArrayList<String>();	// 위도
		
		for(int i=0;i<path.size();i++){
			JSONArray result = (JSONArray)path.get(i);
			
			longitudeList.add(result.get(0).toString());
			latitudeList.add(result.get(1).toString());
		}
		
		modelAndView.addObject("longitudeList", longitudeList);
		modelAndView.addObject("latitudeList", latitudeList);
		
		return modelAndView;
	}
}