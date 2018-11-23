package com.team.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team.app.constant.AppConstants;
import com.team.app.domain.LoraFrame;
import com.team.app.domain.TblKeywordType;
import com.team.app.domain.TblUserInfo;
import com.team.app.domain.UserDeviceMapping;
import com.team.app.logger.AtLogger;
import com.team.app.service.ConsumerInstrumentService;
import com.team.app.service.KeywordService;
import com.team.app.service.OrganisationService;
import com.team.app.service.UserLoginService;
import com.team.app.utils.JsonUtil;

@Controller
@SessionAttributes({"status"})
public class WaterConsumptionController {

	private static final AtLogger logger = AtLogger.getLogger(WaterConsumptionController.class);	
		
	@Autowired
	private ConsumerInstrumentService consumerInstrumentServiceImpl;
	
	@Autowired
	private OrganisationService organisationService;
	
	@Autowired
	private KeywordService keywordService;
	
	@Autowired
	private UserLoginService userLoginService;
	
	
	@RequestMapping(value= {"/waterConsumption"}, method=RequestMethod.GET)
    public String waterConsumptionHanlder(Map<String,Object> map) {		
			return "WaterConsumption";
		 
	 }
	
	@RequestMapping(value= {"/waterConsumptionCal"}, method={ RequestMethod.GET, RequestMethod.POST })
    public String waterConsumptionCalHandler(HttpSession session,HttpServletRequest request,Map<String,Object> map) {
		try{	
			 Map<String,Object> orgMapped=organisationService.getLoraServerOrganisation();	   
				map.put("organisations", orgMapped);
			 List<TblKeywordType> keyTypes= keywordService.getKeywordTypes(); 
				map.put("keyTypes",keyTypes);
					return "AdminWaterConsumptionCal";
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
	
	
	@RequestMapping(value= {"/endUserWaterConsumption"}, method={ RequestMethod.GET, RequestMethod.POST })
    public String endUserWaterConsumptionHanlder(HttpSession session,HttpServletRequest request,Map<String,Object> map) {
		  try{
		     TblUserInfo user=(TblUserInfo) session.getAttribute("user");
		     TblUserInfo u=userLoginService.getUserByUserId(user.getId());
			 List<UserDeviceMapping> udm=u.getUserDeviceMappings();
			 
			 if(udm!=null && !udm.isEmpty()){
				 map.put("udmList", udm);
			 }
			 
		     List<LoraFrame> frm=(List<LoraFrame>) request.getAttribute("frames");
		     
		     if(frm!=null && !frm.isEmpty()){
			   map.put("frames", frm);
		     }	
		     return "EndUserWaterConsumption";
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
	
	
	@RequestMapping(value= {"/waterConsumptionUnits"}, method=RequestMethod.POST)
    public String waterConsumptionUnitsHanlder(HttpServletRequest request, Map<String,Object> map,RedirectAttributes redirectAttributes) {
	  try{
		String orgs=request.getParameter("orgid");
		//String name=request.getParameter("orgName");
		String apps=request.getParameter("appid");
		String dev=request.getParameter("devid");
		String fDate=request.getParameter("fromDate");		
		String tDate=request.getParameter("toDate");
		
		String[] orgArr=orgs.split(":");
		String orgid=orgArr[0];
		String name=orgArr[1];
		String[] devArr=dev.split(":");
		String devNode=devArr[0];
		String[] appArr=apps.split(":");
		String appId=appArr[0];
				
		
		
		logger.debug("orgid",orgid);
		logger.debug("appId",appId);
		logger.debug("devNode",devNode);
		logger.debug("fromDate",fDate);
		logger.debug("toDate",fDate);
				
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         Date fromDate=DATE_FORMAT.parse(DATE_FORMAT.format(new Date(fDate)));
	        Date toDate=DATE_FORMAT.parse(DATE_FORMAT.format(new Date(tDate)));
	        
	        Map<String,Object> orgMapped=organisationService.getLoraServerOrganisation();	   
			map.put("organisations", orgMapped);
			
			List<TblKeywordType> keyTypes= keywordService.getKeywordTypes(); 
				map.put("keyTypes",keyTypes);
				
			Long totalwaterunitsfrmtodate = (Long) consumerInstrumentServiceImpl.getTotalWaterUnitsFrmToDate(appId,devNode, fromDate, toDate);
	            map.put("totalwaterunitsfrmtodate", totalwaterunitsfrmtodate);
	            
	        List<LoraFrame> frames=consumerInstrumentServiceImpl.getFramesByFrmToDateAndDevEUI(devNode,fromDate,toDate);
	       
	        if(frames!=null && !frames.isEmpty()){
	        	logger.debug("list size",frames.size());
	        	map.put("frames", frames);
	        	
	        	//redirectAttributes.addAttribute("frames",frames);
	        	//redirectAttributes.addAllAttributes(request.getParameterMap());
	        }
	        return "WaterConsumptionVal";
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
	
	
	/* Ajax calling for /getGraphVal */	
	@SuppressWarnings("unchecked")
	@RequestMapping(value= {"/getGraphVal"}, method=RequestMethod.POST)
    public @ResponseBody String getGraphValHandler(HttpServletRequest request) {
		String orgs=request.getParameter("orgId");
		String[] orgArr=orgs.split(":");
		String orgid=orgArr[0];
		String apps=request.getParameter("appId");
		String[] appArr=apps.split(":");
		String appId=appArr[0];
		String devNode=request.getParameter("devId");
		String fDate=request.getParameter("fromDate");		
		String tDate=request.getParameter("toDate");
		String type=request.getParameter("type");
		logger.debug("orgid",orgid);
		logger.debug("appId",appId);
		logger.debug("devNode",devNode);
		logger.debug("fromDate",fDate);
		logger.debug("toDate",fDate);
		logger.debug("type",type);
		
		String returnVal="";
		try{
			
			JSONArray dateUnitArr=null;
				dateUnitArr=new JSONArray();
				
			JSONObject json=null;	
				json=new JSONObject();	
			
			
			
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date fromDate=DATE_FORMAT.parse(DATE_FORMAT.format(new Date(fDate)));
	        Date toDate=DATE_FORMAT.parse(DATE_FORMAT.format(new Date(tDate)));
	        if (fDate.compareTo(tDate) > 0) {
	        	logger.debug("Greater");
	        	json.put("result",dateUnitArr);
				returnVal=JsonUtil.objToJson(json);
				   return returnVal;
	        }
	        
	        
	        
	        Object[] frames=consumerInstrumentServiceImpl.getFramesByFrmToDateAndDevEUIAndAppId(appId,devNode,fromDate,toDate,type);
	        logger.debug("resultant",frames[0]);
	      
	       if(String.valueOf(frames[0])!=null && !String.valueOf(frames[0]).isEmpty()){
				String[] result=String.valueOf(frames[0]).split(",");
				logger.debug("result length",result.length);				
				
							
					for(int i=0;i<result.length;i++){							
						String[] jsonVal=result[i].split("&");
						JSONObject js=null;
							js=new JSONObject();
							js.put("xaxis", jsonVal[0]);
							js.put("units", Integer.parseInt(jsonVal[1]));
							dateUnitArr.add(js);
																		
					}
												
					json.put("result",dateUnitArr);
					returnVal=JsonUtil.objToJson(json);
				
				    logger.debug("Resultant JSON String ",returnVal);
	       }else{
	    	   json.put("result","No Content");
	    	   returnVal=JsonUtil.objToJson(json);
	       }
		}catch(Exception e){
			e.printStackTrace();
		}
			return returnVal;
		 
	 }
	
	
	/* Ajax calling for /getGraphOnDemand */	
	@SuppressWarnings("unchecked")
	@RequestMapping(value= {"/getGraphOnDemand"}, method=RequestMethod.POST)
    public @ResponseBody String getGraphOnDemandHandler(HttpServletRequest request,HttpSession session) {
		String userId=(String)session.getAttribute("userId");	
		String devId=request.getParameter("devId");
		String fDate=request.getParameter("fromDate");		
		String tDate=request.getParameter("toDate");
		String type=request.getParameter("type");
			
		logger.debug("devNode",devId);
		logger.debug("fromDate",fDate);
		logger.debug("toDate",fDate);
	
		
		String returnVal="";
		try{
			TblUserInfo user=userLoginService.getUserByUserId(userId);
			if(user!=null){
				JSONArray dateUnitArr=null;
					dateUnitArr=new JSONArray();
					
				JSONObject json=null;	
					json=new JSONObject();	
				
			
			
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        Date fromDate=DATE_FORMAT.parse(DATE_FORMAT.format(new Date(fDate)));
		        Date toDate=DATE_FORMAT.parse(DATE_FORMAT.format(new Date(tDate)));
		        if (fDate.compareTo(tDate) > 0) {
		        	logger.debug("Greater");
		        	json.put("result",dateUnitArr);
					returnVal=JsonUtil.objToJson(json);
					   return returnVal;
		        }
		        
		        
		        
		        Object[] frames=consumerInstrumentServiceImpl.getUserDashboardGraphOnSubmit(devId,fromDate,toDate,type);
		        logger.debug("resultant",frames[0]);
		      
		       if(String.valueOf(frames[0])!=null && !String.valueOf(frames[0]).isEmpty()){
					String[] result=String.valueOf(frames[0]).split(",");
					logger.debug("result length",result.length);				
					
								
						for(int i=0;i<result.length;i++){							
							String[] jsonVal=result[i].split("&");
							JSONObject js=null;
								js=new JSONObject();
								js.put("xaxis", jsonVal[0]);
								js.put("units", Integer.parseInt(jsonVal[1]));
								dateUnitArr.add(js);
																			
						}
													
						json.put("result",dateUnitArr);
						returnVal=JsonUtil.objToJson(json);
					
					    logger.debug("Resultant JSON String ",returnVal);
		       }else{
		    	   json.put("result","No Content");
		    	   returnVal=JsonUtil.objToJson(json);
		       }
			}   
		}catch(Exception e){
			e.printStackTrace();
		}
			return returnVal;
		 
	 }
	
	
	
	/* Ajax calling for /getGraphVal */	
	@SuppressWarnings("unchecked")
	@RequestMapping(value= {"/getGraphOnBodyLoad"}, method=RequestMethod.GET)
    public @ResponseBody String getGraphOnBodyLoadHandler(HttpServletRequest request, HttpSession session) {
		logger.debug("In /getGraphOnBodyLoad");
		String userId=(String)session.getAttribute("userId");		
		String returnVal="";
		try{
			TblUserInfo user=userLoginService.getUserByUserId(userId);
			if(user!=null){
				JSONArray dateUnitArr=null;
					dateUnitArr=new JSONArray();
					
				JSONObject json=null;	
					json=new JSONObject();	
					
				JSONObject js=null;
   						
			
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		        Date currDate=DATE_FORMAT.parse(DATE_FORMAT.format(new Date())); 
		        	logger.debug("Date as:",currDate);
		        List<UserDeviceMapping> udmList=user.getUserDeviceMappings();
		       if(udmList!=null){
		    	   int m=0;
		    	   js=new JSONObject();
		    	   for(UserDeviceMapping udm: udmList){
		    		   Object[] frames=consumerInstrumentServiceImpl.getUserDashboardGraphsOnLoad(udm.getAppId(),udm.getDevEUI(),currDate);
		    		   
		    		   logger.debug("resultant",frames[0]);
		    		   				JSONObject jsn=null;
		    		   					jsn=new JSONObject();	    		   					
		    		   				
		    		   					
		    		   				JSONArray jsnArr=null;
		    		   					jsnArr=new JSONArray();   
		    		   					
		    		   				JSONArray jsArr=null;
		    		   					jsArr=new JSONArray();	
		    		   					
		    		   					
			    		   if(String.valueOf(frames[0])!=null && !String.valueOf(frames[0]).isEmpty()){
			   				String[] result=String.valueOf(frames[0]).split(",");
			   				logger.debug("result length",result.length);  
			   								   				
				   				for(int i=0;i<result.length;i++){							
			   						String[] jsonVal=result[i].split("&");
			   							jsArr.add(jsonVal[0]);
			   								jsnArr.add(Integer.parseInt(jsonVal[1]));			   																		
			   					}
				   				
				   				if(m==0){
				   					js.put("categories", jsArr);
				   					m++;
				   				}
				   								   								   				
				   				jsn.put("name",udm.getDevNode()+"->"+udm.getDevEUI());
				   				jsn.put("data", jsnArr);  				
				   				   				
				   				dateUnitArr.add(jsn);
				   				
			   						   				
			   				
			    	       }					
		             }
		    	   
		    	   	json.put("xAxis", js);
		    	   	json.put("units",dateUnitArr);
		    	   	JSONObject res=null;
		    	   		res=new JSONObject();
		    	   			res.put("result", json);
		    	   	returnVal=JsonUtil.objToJson(res);
  				    logger.debug("Resultant JSON String ",returnVal);
		      }else{
	   	    	   json.put("result","No Content");
	   	    	   returnVal=JsonUtil.objToJson(json);
	   	      }
	       }
		}catch(Exception e){
			logger.error(e);
		}
			return returnVal;
		 
	 }
	
	
	@RequestMapping(value= {"/endUserWaterConsumptionUnits"}, method=RequestMethod.POST)
    public String endUserWaterConsumptionUnitsHandler(HttpServletRequest request, Map<String,Object> map,RedirectAttributes redirectAttributes) {
	  try{
		String devNode=request.getParameter("devid");
		String fDate=request.getParameter("fromDate");		
		String tDate=request.getParameter("toDate");
		
		logger.debug("devNode",devNode);
		logger.debug("fromDate",fDate);
		logger.debug("toDate",fDate);
		
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         Date fromDate=DATE_FORMAT.parse(DATE_FORMAT.format(new Date(fDate)));
	        Date toDate=DATE_FORMAT.parse(DATE_FORMAT.format(new Date(tDate)));
	        
	        	            
	        List<LoraFrame> frames=consumerInstrumentServiceImpl.getFramesByFrmToDateAndDevEUI(devNode,fromDate,toDate);
	       
	        if(frames!=null && !frames.isEmpty()){
	        	logger.debug("list size",frames.size());
	            	request.setAttribute("frames",frames);
	        	      	//redirectAttributes.addAllAttributes(request.getParameterMap());
	        }
	        return "forward:/endUserWaterConsumption";
		
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
