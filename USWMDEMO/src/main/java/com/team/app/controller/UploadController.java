package com.team.app.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team.app.constant.AppConstants;
import com.team.app.domain.TblKeywordType;
import com.team.app.logger.AtLogger;
import com.team.app.service.KeywordService;
import com.team.app.service.OrganisationService;

@Controller
public class UploadController {
	
	private static final AtLogger logger = AtLogger.getLogger(UploadController.class);
	
	@Autowired
	private OrganisationService organisationService;
	
	@Autowired
	private KeywordService keywordService;

	@RequestMapping(value= {"/uploadUserSubscription"}, method=RequestMethod.GET)
	public String uploadUserSubscriptionHandler(Map<String,Object> map, HttpServletRequest request){
		logger.debug("Inside /uploadUserSubscription");
		try{
			Map<String,Object> orgMapped=organisationService.getLoraServerOrganisation();	   
			map.put("organisations", orgMapped);
			
			List<TblKeywordType> keyTypes= keywordService.getKeywordTypes(); 
			map.put("keyTypes",keyTypes);	
		  	 
			 return "userSubscriptionUpload";
		}catch(Exception e){
			e.printStackTrace();
			HttpSession s=request.getSession();
		    s.setAttribute("statusLog",AppConstants.statusLog);
			s.setAttribute("url", request.getRequestURL());
			s.setAttribute("exception", e.toString());				
			s.setAttribute("className",Thread.currentThread().getStackTrace()[1].getClassName());
			s.setAttribute("methodName",Thread.currentThread().getStackTrace()[1].getMethodName());
			s.setAttribute("lineNumber",Thread.currentThread().getStackTrace()[1].getLineNumber());		       
		    return "redirect:/exception";
	    }		
	}
}
