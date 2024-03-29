package egovframework.eris.dcr.web;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.mbl.com.cmm.annotation.IncludedMblInfo;
import egovframework.mbl.com.oas.service.OpenApiVO;
import egovframework.rte.fdl.property.EgovPropertyService;


/**
 * 개요
 * - Video에 대한 Controller를 정의한다.
 *
 * 상세내용
 * - 
 * @author 안태경
 * @since 2020.08.03
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2020.08.03  안태경          최초 생성
 *
 * </pre>
 */
@Controller
public class ErisPhotoController {

	/** EgovPropertyService */
	@Resource(name="propertiesService")
	protected EgovPropertyService propertyService;
	
	@Resource(name="messageSource")
	MessageSource messageSource ;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ErisPhotoController.class);
	
	@IncludedMblInfo(name="사진 촬영 서비스", order = 1001, gid = 10)
	@RequestMapping("/eris/dcr/erisPhotoInfo.mdo")
	public String selectOpenApiInquiryHistoryList(@ModelAttribute("searchVO") OpenApiVO openApiVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 권한 체크
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		/*if (!isAuthenticated) {
			return "egovframework/mbl/com/uat/uia/EgovLoginUsr";
		}*/
		
		
		
		
		//Locale locale = new Locale(request.getParameter("language"));
		Locale locale = Locale.getDefault();
		
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		
		localeResolver.setLocale(request, response, locale);
		
		LOGGER.debug("dcr.test ----->" + messageSource.getMessage("dcr.test", null, locale));
		
		return "egovframework/eris/dcr/ErisPhotoInfo";
	}

}