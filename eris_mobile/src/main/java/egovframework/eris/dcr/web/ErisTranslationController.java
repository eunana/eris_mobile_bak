package egovframework.eris.dcr.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.mbl.com.oas.service.OpenApiVO;
import egovframework.rte.fdl.property.EgovPropertyService;

import javax.annotation.Resource;

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

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;


/**
 * 개요
 * - 구글 텍스트 번역에 대한 Controller를 정의한다.
 *
 * 상세내용
 * - 
 * @author 고은아
 * @since 2020.08.07
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2020.08.07  고은아          최초 생성
 *
 * </pre>
 */
@Controller
public class ErisTranslationController {

	/** EgovPropertyService */
	@Resource(name="propertiesService")
	protected EgovPropertyService propertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ErisTranslationController.class);
	
	@RequestMapping("/eris/dcr/erisTranslation.mdo")
	public String selectOpenApiGoogleTrsln(@ModelAttribute("searchVO") OpenApiVO openApiVO, ModelMap model) throws Exception {
		// 권한 체크
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		
		return "egovframework/eris/dcr/ErisTranslation";
	}
	
	
	
	/**
	 * 구글번역 api
	 * 
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	@RequestMapping("/eris/dcr/googleTranslate.mdo")
	public ModelAndView googleTranslate(@RequestParam Map<String, Object> param) throws Exception {
		// 권한 체크
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		Translate translate = TranslateOptions.getDefaultInstance().getService();
		
		// 언어 자동감지 기능 넣기
		
		Translation translation = translate.translate(param.get("beforeText").toString(), 
//											TranslateOption.sourceLanguage("en"), 
											TranslateOption.sourceLanguage(null), 	//번역할 문장의 언어가 정해져 있지 않을 경우 자동 감지
											TranslateOption.targetLanguage("ko"));
		
		String afterText = translation.getTranslatedText();			// 번역결과
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject("afterText", afterText);
		
		return modelAndView;
	}
	
	
	/**
	 * 파파고 번역 api
	 * 
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	@RequestMapping("/eris/dcr/papagoTranslate.mdo")
	public ModelAndView papagoTranslate(@RequestParam Map<String, Object> param) throws Exception {
		// 권한 체크
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		String clientId = "eb9a72baas";		//애플리케이션 클라이언트 아이디값";
		String clientSecret = "iBQg7dcgys679watM6jRmCYLvPBKI3ZCUcZvuieX";	//애플리케이션 클라이언트 시크릿값";
		
		StringBuffer response = new StringBuffer();
		
		try {
			
            String beforText = URLEncoder.encode(param.get("beforeText").toString(), "UTF-8");
            String apiURL = "https://naveropenapi.apigw.ntruss.com/nmt/v1/translation";
            URL url = new URL(apiURL);
            
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            
            String source = detectLangs(beforText);
            
            // post request
            String postParams = "source=" + source + "&target=ko&text=" + beforText;
            con.setDoOutput(true);
            
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            
            int responseCode = con.getResponseCode();
            BufferedReader br;
            
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 오류 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            
            String inputLine;
            
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            
            br.close();
            
        } catch (Exception e) {
        	
            System.out.println(e);
        } finally {
        	
        	// JSON parsing
    		JSONParser parser = new JSONParser();
    		JSONObject obj = (JSONObject)parser.parse(response.toString());
    		JSONObject message = (JSONObject)obj.get("message");
    		JSONObject result = (JSONObject)message.get("result");
    		
    		String afterText = result.get("translatedText").toString();
    		
    		
    		System.out.println(afterText);
    		modelAndView.addObject("afterText", afterText);
    		
        }
				
		return modelAndView;
	}

	
	/**
	 * 파파고 언어감지 api
	 * 
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	private  String detectLangs(String beforeText) throws Exception {
		
		String clientId = "eb9a72baas";		//애플리케이션 클라이언트 아이디값";
		String clientSecret = "iBQg7dcgys679watM6jRmCYLvPBKI3ZCUcZvuieX";	//애플리케이션 클라이언트 시크릿값";
		
		String langCode = "";
		
		StringBuffer response = new StringBuffer();
		
		 try {
	            String query = beforeText;
	            String apiURL = "https://naveropenapi.apigw.ntruss.com/langs/v1/dect";
	            
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("POST");
	            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
	            
	            // post request
	            String postParams = "query=" + query;
	            con.setDoOutput(true);
	            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	            wr.writeBytes(postParams);
	            wr.flush();
	            wr.close();
	            
	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else {  // 오류 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }
	            
	            String inputLine;
	          
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	            }
	            br.close();
	            
	        } catch (Exception e) {
	            System.out.println(e);
	        } finally {
	        	
	        	// JSON parsing
	    		JSONParser parser = new JSONParser();
	    		JSONObject obj = (JSONObject)parser.parse(response.toString());
	    		
	    		langCode = obj.get("langCode").toString();
	    		
	        }
		 
		 return langCode;
	}
}