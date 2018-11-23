package com.team.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team.app.constant.AppConstants;
import com.team.app.domain.Area;
import com.team.app.domain.Landmark;
import com.team.app.domain.Place;
import com.team.app.domain.TblUserInfo;
import com.team.app.dto.APLDto;
import com.team.app.dto.OrganisationDto;
import com.team.app.logger.AtLogger;
import com.team.app.service.APLService;
import com.team.app.service.UserLoginService;

@Controller
public class APLConfigController {

	private static final AtLogger logger = AtLogger.getLogger(APLConfigController.class);
	
	@Autowired
	private APLService aplService;
	
	@Autowired
	private UserLoginService userLoginService;
	
	@RequestMapping(value= {"/aplConfig"}, method=RequestMethod.GET)
	public String getAPLHandler(Map<String,Object> map, HttpServletRequest request){
		logger.debug("Inside /aplCoNFiG");
		try{
		  	 List<OrganisationDto> orgDtos=aplService.getOrganisation();
			 map.put("organisations", orgDtos); 
			 String orgId=request.getParameter("orgid");
			 logger.debug("printing orgId ",orgId);
			 if(orgId != null && !(orgId.isEmpty())){
				 List<Area> areaList=aplService.getAreasByOrgId(orgId);
				 map.put("areaList",areaList);
			 }
			 return "area";
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
	
		
	@RequestMapping(value= {"/LandMarkSearch"}, method=RequestMethod.GET)
	public String LandMarkSearchHandler(HttpServletRequest request,Map<String,Object> map){		
		logger.debug("Inside /LandMarkSearch");	
		try{
			String orgs=request.getParameter("orgId");
			String[] orgIdName=orgs.split(":");
			String orgId=orgIdName[0];
				logger.debug("printing orgId as: ",orgId);
			map.put("orgId", orgId);
				 return "LandMarkSearch1";
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
	
	
	
	@RequestMapping(value= {"/marklandmark"}, method=RequestMethod.GET)
	public String getAPLFromGoogleMap(HttpServletRequest request,Map<String,Object> map){
		try{
			String orgId=request.getParameter("orgId");
			String areaId=request.getParameter("area");
			String placeId=request.getParameter("place");
			List<Area> areaList=null;
			if(orgId!=null && !(orgId.isEmpty())){
				 areaList=aplService.getAreasByOrgId(orgId);	
			}else if(areaId!=null && !(areaId.isEmpty())){
				areaList=aplService.getAreasListByAreaId(areaId);
			}else if(placeId!=null && !(placeId.isEmpty())){
				Place p=aplService.getPlaceById(placeId);
				areaList=aplService.getAreasListByAreaId(p.getArea().getId());
			}
			
			map.put("areas",areaList);
				
			 return "marklandmark";
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
	
	
	@RequestMapping(value= {"/marklandmarkdash"}, method=RequestMethod.GET)
	public String getAPLForDashFromGoogleMap(HttpServletRequest request,Map<String,Object> map) {
		try{
			String orgId=request.getParameter("orgId");
			String areaId=request.getParameter("area");
			String placeId=request.getParameter("place");
			List<Area> areaList=null;
			if(orgId!=null && !(orgId.isEmpty())){
				 areaList=aplService.getAreasByOrgId(orgId);	
			}else if(areaId!=null && !(areaId.isEmpty())){
				areaList=aplService.getAreasListByAreaId(areaId);
			}else if(placeId!=null && !(placeId.isEmpty())){
				Place p=aplService.getPlaceById(placeId);
				areaList=aplService.getAreasListByAreaId(p.getArea().getId());
			}
			
			map.put("areas",areaList);
				
			 return "marklandmarkdash";
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
	
	
	@RequestMapping(value= {"/marklandmarkUserDash"}, method=RequestMethod.GET)
	public String marklandmarkUserDashHanlder(HttpSession session,HttpServletRequest request,Map<String,Object> map){
		logger.debug("/marklandmarkUserDash");
		
		try{
			TblUserInfo u=(TblUserInfo)session.getAttribute("user");	
			TblUserInfo user=userLoginService.getUserByUserId(u.getId());
			logger.debug("/landmarkId",user.getLandmark().getId());
			String orgId=request.getParameter("orgId");
			
			List<Area> areaList=null;
				areaList=new ArrayList<Area>();
			Landmark landmark=aplService.getLandMarkById(user.getLandmark().getId());
			if(orgId!=null && !(orgId.isEmpty())){			
				Area area=landmark.getPlace().getArea();
				if(area.getOrgId().equals(orgId)){
					areaList.add(area);
				}
			}
			
			map.put("areas",areaList);
			map.put("landmarkId", user.getLandmark().getId());
			return "marklandmarkUserDash";
			
		}catch(Exception e){
			e.printStackTrace();
			HttpSession s=request.getSession();
		    s.setAttribute("statusLog",AppConstants.statusLog);
			s.setAttribute("url", request.getRequestURL());
			s.setAttribute("exception",e.toString());				
			s.setAttribute("className",Thread.currentThread().getStackTrace()[1].getClassName());
			s.setAttribute("methodName",Thread.currentThread().getStackTrace()[1].getMethodName());
			s.setAttribute("lineNumber",Thread.currentThread().getStackTrace()[1].getLineNumber());		       
		    return "redirect:/exception";
	    }	
		 
	}
	
	
			// An ajax call comes from marklandmark.jsp
			@RequestMapping(value = {"/getLandmarks"},  method=RequestMethod.POST)
			public  @ResponseBody String getLandmarks(@RequestParam(required = false, value = "place") String place,
					@RequestParam(required = false, value = "area") String area, @RequestParam(required = false, value = "orgId") String orgId,
					@RequestParam(required = false, value = "placeforLandmark") String placeforLandmark)
			{
				String response = "";
			
				if (placeforLandmark != null) {
					try {
						  
						Place p=aplService.getPlaceById(placeforLandmark);
						List<Landmark> landmarks=p.getLandmarks();
						
						response = "<select name='landmarkId' id='landmarkId'>";
					
						for (Landmark eachlandmark : landmarks) {
							response += "<option value='" + eachlandmark.getId()
									+ "' >" + eachlandmark.getLandmarkname() + "</option>";
						}
						response += "</select>";
					}catch(Exception ex){
						logger.error("Error in fetching landmarks for place ", ex);
					} 
				}else{
					try {
					
						List<Landmark> landmarks = aplService.getSpecificLandmarks(area,place,orgId);
						
						for (Landmark eachlandmark : landmarks) {
							response +=  "$" + eachlandmark.getPlace().getArea().getAreaname() + ":"
									+ eachlandmark.getPlace().getPlacename() + ":"
									+ eachlandmark.getId() + ":"
									+ eachlandmark.getLandmarkname() + ":"
									+ eachlandmark.getLat() + ":"
									+ eachlandmark.getLon();
						}
					
					}catch(Exception ex){
						logger.error("Error in fetching the specific landmarks ",ex);												
					}
				}
				
				
				return response;
			}
			
			
			
						// An ajax call comes from marklandmark.jsp
						@RequestMapping(value = {"/getLandmarksByLandmarkId"},  method=RequestMethod.POST)
						public  @ResponseBody String getLandmarksByLandmarkIdHanlder(@RequestParam(required = false, value = "place") String place,
								@RequestParam(required = false, value = "area") String area, @RequestParam(required = false, value = "orgId") String orgId,
								@RequestParam(required = false, value = "placeforLandmark") String placeforLandmark,
								@RequestParam(required = false, value = "landmarkId") String landmarkId)
						{
							String response = "";
						
							if (placeforLandmark != null) {
								try {
									  
									Place p=aplService.getPlaceById(placeforLandmark);
									List<Landmark> landmarks=p.getLandmarks();
									
									response = "<select name='landmarkId' id='landmarkId'>";
								
									for (Landmark eachlandmark : landmarks) {
										response += "<option value='" + eachlandmark.getId()
												+ "' >" + eachlandmark.getLandmarkname() + "</option>";
									}
									response += "</select>";
								}catch(Exception ex){
									logger.error("Error in fetching landmarks for place ", ex);
								} 
							}else{
								try {
								
									List<Landmark> landmarks = aplService.getLandMarkByUserLandmarkId(landmarkId);
									
									for (Landmark eachlandmark : landmarks) {
										response +=  "$" + eachlandmark.getPlace().getArea().getAreaname() + ":"
												+ eachlandmark.getPlace().getPlacename() + ":"
												+ eachlandmark.getId() + ":"
												+ eachlandmark.getLandmarkname() + ":"
												+ eachlandmark.getLat() + ":"
												+ eachlandmark.getLon();
									}
								
								}catch(Exception ex){
									logger.error("Error in fetching the specific landmarks ",ex);												
								}
							}
							
							
							return response;
						}
						
			
			@RequestMapping(value= {"/addArea"}, method=RequestMethod.GET)
			public String getArea(HttpSession session,HttpServletRequest request,Map<String,Object> map,RedirectAttributes redirectAttributes){
			 
				String areaName = request.getParameter("area");
				String orgId = request.getParameter("orgId");
				
				logger.debug("printing areaName /addArea",areaName);
				logger.debug("printing orgId /addArea",orgId);
				/*TblUserInfo user = (TblUserInfo)session.getAttribute("user");
				String doneBy=user.getId();*/
				Area areaObj=null;
				try{
					areaObj=new Area();
				    areaObj.setAreaname(areaName);
				  areaObj.setOrgId(orgId);
				  Area a=aplService.insertArea(areaObj);
				
				if (a!=null) {
					
					redirectAttributes.addFlashAttribute("status",
							"<div class=\"success\" > New area added successfully</div>");
				}else{
					
					
					redirectAttributes.addFlashAttribute("status",
							"<div class=\"failure\" > Area Already Exist !</div>");
					
				}
			}catch(Exception e){
				logger.error("Error in adding area ",e);
				redirectAttributes.addFlashAttribute("status",
						"<div class=\"failure\" > Area adding failed</div>");
					}
					
				return "redirect:/aplConfig?orgId="+orgId;
			}
			
			
			
			@RequestMapping(value= {"/showPlace"}, method=RequestMethod.GET)
			public String getPlaceHandler(Map<String,Object> map, HttpServletRequest request){
				try{		
					String areaId=request.getParameter("areaId");
						Area a=aplService.getAreasByAreaId(areaId);
						List<Place> placeList=a.getPlaces();
						map.put("placeList",placeList);
						map.put("areas", a);
						return "place";	
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
			
			@RequestMapping(value= {"/showLandmark"}, method=RequestMethod.GET)
			public String getLandmarkHandler(Map<String,Object> map, HttpServletRequest request) {
				try{
				    String placeId=request.getParameter("placeId");								
					Place place=aplService.getPlaceById(placeId);
					List<Landmark> landmarkList=place.getLandmarks();									
					map.put("landmarkList",landmarkList);
					map.put("place", place);
					return "landmark";
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
			
			
			@RequestMapping(value= {"/updateArea"}, method=RequestMethod.GET)
			public String getUpdatedArea(HttpServletRequest request,Map<String,Object> map,RedirectAttributes redirectAttributes){

				String areaId = request.getParameter("areaId");
				String areaName = request.getParameter("area");
				String orgId = request.getParameter("orgId");
				
				logger.debug("printing areaId /updateArea ",areaId);
				logger.debug("printing areaName /updateArea ",areaName);
				logger.debug("printing orgId /updateArea ",orgId);
				
				try{
						Area area=aplService.getAreaByOrgAndAreaId(orgId,areaId);
						if(area!=null){
							area.setAreaname(areaName);
							Area a=aplService.updateArea(area);	
							 if(a!=null){
								 redirectAttributes.addFlashAttribute("status",
											"<div class=\"success\" > Area updated successfully</div>");
							 }else{
								 redirectAttributes.addFlashAttribute("status",
											"<div class=\"failure\" > Area Already Exist !</div>");
							 }
						}else{
							redirectAttributes.addFlashAttribute("status",
									"<div class=\"success\" > Area not exist!</div>");
						}
										
						
			}catch(Exception e){
				logger.error("Error in updating area ",e);
				redirectAttributes.addFlashAttribute("status",
						"<div class=\"success\" > Adding area failed!</div>");
				
			}
				return "redirect:/aplConfig?orgId="+orgId;
	}
			
			

			@RequestMapping(value= {"/addPlace"}, method=RequestMethod.GET)
			public String addPlace(HttpSession session,HttpServletRequest request,Map<String,Object> map,RedirectAttributes redirectAttributes){
				
				String placeName = request.getParameter("place");
				String areaId = request.getParameter("areaId");
				
				try{
					Area areaBean = aplService.getAreasByAreaId(areaId);
					Place place = new Place();
					place.setPlacename(placeName);
					place.setArea(areaBean);	
			
				Place p = aplService.insertPlace(place,areaId);
				
				 if(p!=null){
					 redirectAttributes.addFlashAttribute("status",
								"<div class=\"success\" > New place added successfully!</div>");
				 }else{
					 redirectAttributes.addFlashAttribute("status",
								"<div class=\"failure\" > Place Already Exist in the area!</div>");
				 }
			
				
				
			}catch(Exception e){
					logger.error("Error in adding place ",e);
					 redirectAttributes.addFlashAttribute("status",
							"<div class=\"failure\" > Place adding failed</div>");
			}
						
					return "redirect:/showPlace?areaId="+areaId;
				}
			
			
			@RequestMapping(value= {"/updatePlace"}, method=RequestMethod.GET)
			public String getUpdatedPlace(HttpServletRequest request,Map<String,Object> map,RedirectAttributes redirectAttributes){
				
				String placeId = request.getParameter("placeId");
				String placeName = request.getParameter("place");
				String areaId = request.getParameter("areaId");
				
				try{
					Place place=aplService.getPlaceByPlaceAndAreaId(placeId,areaId);
					if(place!=null){
						if(place.getPlacename().equalsIgnoreCase(placeName)){
							 redirectAttributes.addFlashAttribute("status",
										"<div class=\"failure\" > Place Already Exist in the area!</div>");
						}else{
							place.setPlacename(placeName);
							Place p=aplService.updatePlace(place);
							redirectAttributes.addFlashAttribute("status",
									"<div class=\"success\" > Place updated successfully!</div>");
						}
					}
				
									
			}catch(Exception e){
				logger.error("Error in updating place ",e);
					redirectAttributes.addFlashAttribute("status",
							"<div class=\"failure\" > Place updating failed</div>");
						}
							return "redirect:/showPlace?areaId="+areaId;
				}
			
			
			@RequestMapping(value= {"/addLandmark"}, method=RequestMethod.POST)
			public String addLandmark(HttpServletRequest request,Map<String,Object> map,RedirectAttributes redirectAttributes) {
				
				String landmarkName = request.getParameter("landmark");
				String placeId = request.getParameter("placeId");
				
				try{	
					
					Place place=aplService.getPlaceById(placeId);
					Landmark landmark=null;
				     		landmark=new Landmark();				
							landmark.setLandmarkname(landmarkName);
							landmark.setPlace(place);		
					Landmark l=aplService.insertLandmark(landmark,placeId);
					if(l!=null){
						 redirectAttributes.addFlashAttribute("status",
									"<div class=\"success\" > New Landmark added successfully!</div>");
					 }else{
						 redirectAttributes.addFlashAttribute("status",
									"<div class=\"failure\" > Landmark Already Exist in the place!</div>");
					 }
				
			   }catch(Exception e){
					logger.error("Error in adding landmark ",e);
					map.put("status",
							"<div class=\"failure\" > Landmark adding failed</div>");
						}
						
					return "redirect:/showLandmark?placeId=" + placeId;
				}
					
			@RequestMapping(value= {"/updateLandmark"}, method=RequestMethod.POST)
			public String getUpdatedLandmark(HttpServletRequest request,Map<String,Object> map,RedirectAttributes redirectAttributes) throws Exception {
				logger.debug("In /updateLandmark");
				String retVal = "";
				String placeId = request.getParameter("placeId");
				String landmarkId = request.getParameter("landmarkId");
				String landmark = request.getParameter("landmark");
				String latLng= request.getParameter("latlng");
				String orgId= request.getParameter("orgId");
				String areaId= request.getParameter("area");
				
				logger.debug("printing placeId /updateLandmark",placeId);
				logger.debug("printing landmarkId /updateLandmark",landmarkId);
				logger.debug("printing landmark /updateLandmark",landmark);
				logger.debug("latitude longitude = "+latLng);
				
			
					
					Landmark lm = aplService.getLandMarkByIdAndPlaceId(landmarkId,placeId);
					if(lm != null){
						if(lm.getLandmarkname().equalsIgnoreCase(landmark)){
							logger.debug("inside matching landmark");
							 redirectAttributes.addFlashAttribute("status",
										"<div class=\"failure\" > Landmark Already Exist in the place!</div>");
							 retVal =  "redirect:/showLandmark?placeId=" + placeId;
							 return retVal; 
						}else{
							logger.debug("printing /else = ");
							if(landmark!=null && !landmark.isEmpty()){
								lm.setLandmarkname(landmark);
							}
						}
						
					}
				
				
				if(latLng!=null && latLng.trim().equals("")==false)  {	
					logger.debug("Inside Latlng");
					String latitude=latLng.split(",")[0].substring(1,  latLng.split(",")[0].length()-1);
					String longitude = latLng.split(",")[1].substring(1, latLng.split(",")[1].length()-1);
						 lm.setLat(latitude);
						 lm.setLon(longitude);
						 lm = aplService.updateLandmark(lm);
						 if(orgId != null){
							 retVal =  "redirect:/marklandmark?orgId="+orgId;
						 }else if(areaId != null){
							 retVal =  "redirect:/marklandmark?area="+areaId;
						 }else if(placeId != null){
							 retVal =  "redirect:/marklandmark?place="+placeId;
						 }
						 
						 			
				}else{
					logger.debug("Else Inside Latlng");
					Landmark lnd = aplService.updateLandmark(lm);
					if(lnd!=null){
						redirectAttributes.addFlashAttribute("status",
								"<div class=\"success\" > Landmark updated successfully !</div>");
						
					}else{
						redirectAttributes.addFlashAttribute("status",
								"<div class=\"failure\" > Landmark updation failed!</div>");
						
					}
					retVal =  "redirect:/showLandmark?placeId=" + placeId;
					
				}	
				
				return retVal;
						
			}	
			
			
			
			@RequestMapping(value= {"/updateMapLandmark"}, method=RequestMethod.POST)
			public String updateMapLandmarkHandler(HttpServletRequest request,Map<String,Object> map,RedirectAttributes redirectAttributes) throws Exception{
				logger.debug("In Ajax /updateMapLandmark");
				String retVal = "";
				String placeId = request.getParameter("placeId");
				String landmarkId = request.getParameter("landmarkId");
				String landmark = request.getParameter("landmark");
				String latLng= request.getParameter("latlng");
				String orgId= request.getParameter("orgId");
				String areaId= request.getParameter("area");
				
				logger.debug("printing placeId /updateLandmark",placeId);
				logger.debug("printing landmarkId /updateLandmark",landmarkId);
				logger.debug("printing landmark /updateLandmark",landmark);
				logger.debug("latitude longitude = ",latLng);
				logger.debug("areaId areaId = ",areaId);
				logger.debug("placeId placeId = ",placeId);
				logger.debug("orgId orgId = ",orgId);
				
				
					
					Landmark lm = aplService.getLandMarkByIdAndPlaceId(landmarkId,placeId);
					if(lm != null){				
				
							if(latLng!=null && latLng.trim().equals("")==false)  {	
								logger.debug("Inside Latlng");
								String latitude=latLng.split(",")[0].substring(1,  latLng.split(",")[0].length()-1);
								String longitude = latLng.split(",")[1].substring(1, latLng.split(",")[1].length()-1);
									 lm.setLat(latitude);
									 lm.setLon(longitude);
									 lm = aplService.updateLandmark(lm);
									 if(orgId != null){
										 retVal =  "redirect:/marklandmark?orgId="+orgId;
									 }else if(placeId != null){
										 retVal =  "redirect:/marklandmark?place="+placeId;
										 
									 }else if(areaId != null){
										 retVal =  "redirect:/marklandmark?area="+areaId;
									 }
							}
					}		
					
					return retVal;
				
				
			}
			
			/* AJAX calling from marklandmark.jsp */		
			@RequestMapping(value= {"/getPlace"}, method=RequestMethod.GET)
			public @ResponseBody String getPlaceHandler(@RequestParam(required = true, value = "area") String area) throws Exception{
				  String response="";
						try {
							Area a=aplService.getAreasByAreaId(area);
								List<Place> placesUnderArea=a.getPlaces();
						
							response = "<select name='placeId' id='place' onchange='showLandmark(this.value)'>";
							response += "<option value='0' >Select</option>";
							for (Place place : placesUnderArea) {
								response += "<option value='" + place.getId() + "' >"
										+ place.getPlacename() + "</option>";
							}
							response += "</select>";
						}catch(Exception ex){
							logger.debug("Error during AJAX calling for getPlace",ex);
						}
				return response;
						}
			
			
			
			@RequestMapping(value= {"/GetLandMark"}, method=RequestMethod.GET)
			public @ResponseBody String GetLandMarkHandler(HttpServletRequest request,Map<String,Object> map) throws Exception{
				logger.debug("Inside /GetLandMark");		
				String landMarkText=request.getParameter("landMarkText").trim();
				String orgId=request.getParameter("orgId").trim();
				
					logger.debug("printing landMarkText as: ",landMarkText);
					logger.debug("printing orgId as: ",orgId);
					
					String response="";
					try {
						List<Object[]> dtos=null;
							dtos=aplService.getLandMarkByAPL(orgId,landMarkText);
							
						List<APLDto> aplDto=null;
									aplDto=new ArrayList<APLDto>();		
									
									APLDto dto=null;
							
								for(Object[] obj: dtos){
									dto=new APLDto();
									dto.setLandMarkID(String.valueOf(obj[0]));
									dto.setLandMark(String.valueOf(obj[3]) + " ->"
											+ String.valueOf(obj[2]) + " ->"
											+ String.valueOf(obj[1]));
																	
									aplDto.add(dto);
								}
								
							
							
							if(aplDto!=null && !aplDto.isEmpty()){
								for(APLDto d : aplDto ){
									response += d.getLandMarkID() + ":" + d.getLandMark()
									+ "|";
								}
							}
					}catch(Exception e){
						logger.debug("Error during AJAX calling for GetLandMark",e);	
					}
					logger.debug("Response",response);
					return response;
			}
				
}
