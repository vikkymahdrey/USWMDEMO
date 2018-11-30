package com.team.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team.app.constant.AppConstants;
import com.team.app.domain.TblKeywordType;
import com.team.app.domain.TblUserInfo;
import com.team.app.domain.UserDeviceMapping;
import com.team.app.logger.AtLogger;
import com.team.app.service.ConsumerInstrumentService;
import com.team.app.service.KeywordService;
import com.team.app.service.OrganisationService;
import com.team.app.service.UserLoginService;


@Controller
public class LoginController {
	private static final AtLogger logger = AtLogger.getLogger(LoginController.class);
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private OrganisationService organisationService;
	
	@Autowired
	private KeywordService keywordService;
	
	@Autowired
	private ConsumerInstrumentService consumerInstrumentServiceImpl;
		
	
	@RequestMapping(value= {"/"})
	public String defaultURL(HttpServletRequest request,HttpSession session,Map<String, Object> map) {
		logger.debug("In / URL");
		String statusLog=(String)request.getAttribute("statusLog");		
		if(statusLog!=null){
				map.put("statusLog",statusLog);
				map.put("url",String.valueOf(session.getAttribute("url")));
			 	map.put("exception",String.valueOf(session.getAttribute("exception")));
			 	map.put("className", String.valueOf(session.getAttribute("className")));
			 	map.put("methodName",String.valueOf(session.getAttribute("methodName")));
			 	map.put("lineNumber", String.valueOf(session.getAttribute("lineNumber")));
			 	session.invalidate();
		}
			return "index1";		
	}
	
	
	/*@RequestMapping(value= {"/exception"}, method=RequestMethod.GET)
	public String exceptionHandler(HttpServletRequest request,HttpSession session,Map<String, Object> map){
		logger.debug("In / Exception Handler");
		String statusLog=(String)session.getAttribute("statusLog");		
		if(statusLog!=null){
				map.put("statusLog",statusLog);
				map.put("url",String.valueOf(session.getAttribute("url")));
			 	map.put("exception",String.valueOf(session.getAttribute("exception")));
			 	map.put("className", String.valueOf(session.getAttribute("className")));
			 	map.put("methodName",String.valueOf(session.getAttribute("methodName")));
			 	map.put("lineNumber", String.valueOf(session.getAttribute("lineNumber")));			 	 			 	
		}
			
			 return "exception";		
	}*/
	
	
	@RequestMapping(value= {"/exception"}, method=RequestMethod.GET)
	public String exceptionHandler(HttpServletRequest request,HttpSession session,Map<String, Object> map){
		logger.debug("In / Exception Handler");
		String statusLog=(String)session.getAttribute("statusLog");		
		if(statusLog!=null){
				request.setAttribute("statusLog",statusLog);
				request.setAttribute("url",String.valueOf(session.getAttribute("url")));
				request.setAttribute("exception",String.valueOf(session.getAttribute("exception")));
				request.setAttribute("className", String.valueOf(session.getAttribute("className")));
				request.setAttribute("methodName",String.valueOf(session.getAttribute("methodName")));
				request.setAttribute("lineNumber", String.valueOf(session.getAttribute("lineNumber")));			 	 			 	
		}
			return "forward:/";
				
	}
	
		
	
	@RequestMapping(value= {"/onSubmitlogin"}, method=RequestMethod.POST)
	public ModelAndView loginUser(HttpServletRequest request, HttpServletResponse response,HttpSession session,RedirectAttributes redirectAttributes) {
		logger.debug("in /onSubmitlogin");
		try{
						
			String username = request.getParameter("uname") == null ? "" : request
					.getParameter("uname");
			String password = request.getParameter("pass") == null ? "" : request.getParameter("pass");
	
	        TblUserInfo userInfo=null;
	        boolean needToChangePwd=false;
			
	        if (username.equals("") || password.equals("")) {
	        	redirectAttributes.addFlashAttribute("status",
						"<div class='failure'>Enter Username/Password!</div");
				return new ModelAndView("redirect:/");
			} else {			
				userInfo=userLoginService.getUserByUserAndPwd(username.trim(),password);
				if(userInfo!=null) {
					if(!username.trim().equals(userInfo.getUname())){
						redirectAttributes.addFlashAttribute("status","<div class='failure'>Invalid Username !</div");
			        		return new ModelAndView("redirect:/");
					}
					if(!password.equals(userInfo.getPassword())){
						redirectAttributes.addFlashAttribute("status","<div class='failure'>Invalid Password !</div");
			        		return new ModelAndView("redirect:/");
					}
				}
				
			}
	        
	        if (userInfo!=null) {
	        	logger.debug("Inside NeedToPwd Change");
				if (userInfo.getPwdChangeDt()== null || userInfo.getPwdChangeDt().equals("")) {
					logger.debug("Inside NeedToPwd is null");
					needToChangePwd = true;
				} 
			}
	        
	        if(userInfo!=null){	          	
	        		session.setAttribute("user", userInfo);
		        	session.setAttribute("userId", userInfo.getId());        	        	         	
	        }
	        
	        
	        if (userInfo!=null) {
				if (userInfo.getPwdChangeDt()== null || userInfo.getPwdChangeDt().equals("")) {
					needToChangePwd = true;
				}
			}
	        
	        if (needToChangePwd) {
	        	
				return new ModelAndView("redirect:/changePasswordReq");
				
			} 
	        
	        if(userInfo!=null){
	        	if(userInfo.getRoleBean().getType().equalsIgnoreCase(AppConstants.admin) || userInfo.getRoleBean().getType().equalsIgnoreCase(AppConstants.superAdmin)) {
	        		logger.debug("In end /onSubmitlogin");
	        		return new ModelAndView("redirect:/home");
	        	}else if(userInfo.getRoleBean().getType().equalsIgnoreCase("usr")) {
	        		return new ModelAndView("redirect:/userDashBoard");
	        	}else {
	        		redirectAttributes.addFlashAttribute("status",
	    					"<div class='failure'>Incorrect role identified!</div");
	        		return new ModelAndView("redirect:/");
	        	}
	        }else{	        	
	        	redirectAttributes.addFlashAttribute("status","<div class='failure'>Invalid Username/Password !</div");
	        	return new ModelAndView("redirect:/");
	        }
	        
		}catch(Exception e){
			e.printStackTrace();
			HttpSession s=request.getSession();
		    s.setAttribute("statusLog",AppConstants.statusLog);
			s.setAttribute("url", request.getRequestURL());
			s.setAttribute("exception", e.toString());				
			s.setAttribute("className",Thread.currentThread().getStackTrace()[1].getClassName());
			s.setAttribute("methodName",Thread.currentThread().getStackTrace()[1].getMethodName());
			s.setAttribute("lineNumber",Thread.currentThread().getStackTrace()[1].getLineNumber());		       
		    return new ModelAndView("redirect:/");
	    }
		
	}
	
	
	 @RequestMapping(value= {"/home"}, method=RequestMethod.GET) 
	 public String home(Map<String,Object> map,HttpServletRequest request){
		try{  
			List<TblUserInfo> userInfoList = userLoginService.getUserInfos();
			 map.put("userInfoList",userInfoList);
			 map.put("appUserCount",String.valueOf(userInfoList.size()));
			 
			List<TblKeywordType> keyTypes= keywordService.getKeywordTypes(); 
				map.put("keyTypes",keyTypes);
			  
				
			Map<String,Object> orgMapped=organisationService.getLoraServerOrganisation();	   
				map.put("organisations", orgMapped);
				map.put("orgCount",String.valueOf(orgMapped.size()));
				
				logger.debug("orgCount as: ",String.valueOf(orgMapped.size()));
				
			/*Long curMonthWaterUnits=consumerInstrumentServiceImpl.getWaterConsumptionUnitsByCurMonth();	
				map.put("curMonthWaterUnits",curMonthWaterUnits);*/
				
			/*long userCount=organisationService.getLoraServerUsers();	
				map.put("userCount", String.valueOf(userCount));
					logger.debug("userCount as: ",userCount);*/
				
			return "adminDashboard";
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
	    	
	
	
	@RequestMapping(value= {"/inValid"})
	public String inValidCredentials(HttpServletRequest request){
		return "index";
	}
	
	
	
	@RequestMapping(value= {"/forgotPassword"})
	public String forgetPasswordHandler(HttpServletRequest request){
		return "forgotPassword";
	}
	
	@RequestMapping(value= {"/userHome"},method=RequestMethod.GET)
	public String userHome(Map<String,Object> map,HttpServletRequest request,HttpSession session){
		String userId=(String) session.getAttribute("userId");
		logger.debug("UserId as: ",userId);
		try{
			Map<String,Object> frms=null;
				frms=new HashMap<String,Object>();
			TblUserInfo user=userLoginService.getUserByUId(userId);
			if(user!=null){
				List<UserDeviceMapping> udmList=user.getUserDeviceMappings();
				map.put("udmList",udmList);
				if(udmList!=null && !udmList.isEmpty()) {
					for(UserDeviceMapping udm : udmList) {
						Long units=consumerInstrumentServiceImpl.getWaterConsumptionsUnitForEndUser(udm.getAppId(),udm.getDevEUI());
						if(units!=null) {
							frms.put(udm.getDevNode()+"->"+udm.getDevEUI(), units);
						}else {
							frms.put(udm.getDevNode()+"->"+udm.getDevEUI(), 0);
						}
						
						
					}
				}
			}
			map.put("waterConsumptionDetails", frms);
			return "userHome";
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
	
	
	
		 
		
		 
			@RequestMapping(value= {"/logout"})
			public String goToLogout(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map){
				logger.debug("In gotoLogout Page......");
					HttpSession session = request.getSession(true);
						session.invalidate();
							response.setHeader("Cache-Control",	"no-cache, no-store, must-revalidate");
								response.setHeader("Pragma", "co-cache");
									response.setDateHeader("Expires", 0);
										return "redirect:/";
			}
		 	
		 	
		 	
			@RequestMapping(value= {"/changePasswordReq"})
			public String changePwdReqHandler(){
				logger.debug("IN ChangePassword");
				return "changePassword";
			}	
			
			
			@RequestMapping(value= {"/changePasswordSubmit"},method=RequestMethod.POST)
			public String changePwdSubmitHandler(HttpSession session,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
				logger.debug("IN ChangePassword Controller....");
			
					TblUserInfo user=(TblUserInfo)session.getAttribute("user");
						String password=request.getParameter("pwd");
						String oldpwd=request.getParameter("oldpwd");
						if(user.getPassword().equals(oldpwd)){
							user.setPassword(password);
							user.setPwdChangeDt(new Date(System.currentTimeMillis()));
							userLoginService.updateUserInfo(user);
							redirectAttributes.addFlashAttribute("status",
									"<div class='success'>New Password set, Please Sign-In!</div");
							
						}else{
							redirectAttributes.addFlashAttribute("status",
									"<div class='failure'>Old Password didn't match!</div");
						}				
				        
						return "redirect:/";
						
				
			}
			
			@GetMapping("userDashBoard")
			public String uploadExcel() {
				logger.debug("In userDashBoard");
					return "userDashBoard";
			}
			
			
			
}
