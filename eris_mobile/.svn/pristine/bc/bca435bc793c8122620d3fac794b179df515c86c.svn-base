package egovframework.eris.dcr.web;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.mbl.com.cmm.annotation.IncludedMblInfo;
import egovframework.mbl.com.oas.service.EgovOpenApiService;
import egovframework.mbl.com.oas.service.OpenApi;
import egovframework.mbl.com.oas.service.OpenApiVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import kma.websky.client.stub.forecast.CurrentWeatherModel;
import kma.websky.client.stub.forecast.SurfaceServiceImplService_Impl;
import kma.websky.client.stub.forecast.SurfaceServiceImpl_Stub;
import noNamespace.SearchDocument;
import noNamespace.SearchDocument.Search;
import noNamespace.TotalResultsDocument.TotalResults;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * 개요
 * - Sound에 대한 Controller를 정의한다.
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
 *   2020.08.11  고은아          최초 생성
 *
 * </pre>
 */
@Controller
public class ErisSoundController {

	/** EgovPropertyService */
	@Resource(name="propertiesService")
	protected EgovPropertyService propertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ErisSoundController.class);
	
	@IncludedMblInfo(name="사운드 서비스", order = 1000, gid = 10)
	@RequestMapping("/eris/dcr/erisSoundInfo.mdo")
	public String selectOpenApiInquiryHistoryList() throws Exception {
		// 권한 체크
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		/*if (!isAuthenticated) {
			return "egovframework/mbl/com/uat/uia/EgovLoginUsr";
		}*/
		
		return "egovframework/eris/dcr/ErisSoundInfo";
	}

}