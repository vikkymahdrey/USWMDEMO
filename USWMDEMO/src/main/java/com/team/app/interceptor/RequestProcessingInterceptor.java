package com.team.app.interceptor;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.team.app.domain.TblUserInfo;
import com.team.app.logger.AtLogger;
import com.team.app.utils.OtherFunctions;

public class RequestProcessingInterceptor extends HandlerInterceptorAdapter {

	private static final AtLogger logger = AtLogger.getLogger(RequestProcessingInterceptor.class);

	@Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
				
		logger.debug(">> FILTER <<");
		String indexPage="/";
		String forwardPage="/";
		String queryString="";
		HttpSession session = request.getSession();
			
		try{
					String role="";
						String preUrl = request.getServletPath();
							String actualPrePage=getURI(request);	
								String page=preUrl;
									queryString= getQueryString(request)==null?"": getQueryString(request);
										logger.debug("preUrl as "+page);
										logger.debug("queryString as "+queryString);
										logger.debug("actualPrePage as "+actualPrePage);
				page=page==null?"":page;
				if(page.equals("")==false )
				{		
					logger.debug("Inside page false "+page);
					if(page.equals("/")){
						page=page.substring(0);
					}
					else{
						page=page.substring(1);
					}
					
					if(page.contains("?"))
					{ 
							logger.debug("page in ???"+page);
						request.getSession().invalidate();
						page=page.substring(0,page.indexOf("?"));				
					}
							logger.debug("queryString .."+queryString);
					queryString=queryString==""?"":"?"+queryString;
							logger.debug("queryString ..."+queryString);
					forwardPage=page+queryString;
							logger.debug("forword page"+forwardPage);
				}else
				{
					page=indexPage;
					forwardPage=page;
				}
		
			
				if(page.equals("/") || page.equals("forgotPassword") || page.equals("resetPassword"))
				{	
					return true;
				}else if(page.contains("rest/consumer")) { 
					return true;
				}else if(page.equals("onSubmitlogin")) { 
					return true;
				}										
				else if(OtherFunctions.isEitherJspOrServlet(actualPrePage))
				{
					logger.debug("Inside in OtherFunctions");
					TblUserInfo user=(TblUserInfo) session.getAttribute("user");
					
					if (user == null || user.equals("null")) 
					{
						logger.debug("No user Id page : "+ page);
						String oPage="/";
						if(actualPrePage.equals("logout")){
							response.sendRedirect(oPage);
						}else{
							logger.debug("Actual Prepage = "+ actualPrePage);
							if(actualPrePage.substring(actualPrePage.length()-3).equals(".do")==false)  
								{
									String param = actualPrePage + "___"+ request.getQueryString();
									oPage="/USWMDEMO/?prePage="+param;									
									response.sendRedirect(oPage);
																	
									return false;
								}
						 }
				    }
				}else {		
					logger.debug("In session active: ");
					request.getRequestDispatcher("/"+page).forward(request, response);
				}
				
		
		}catch(Exception e)
		{				
				logger.error("Error in Filters",e);
				request.getRequestDispatcher("/").forward(request, response);
						
		}
		
		return true;
	}

    

private String getURI(HttpServletRequest request ) {
	
	String returnString="";
	try {
		returnString = request.getAttribute("javax.servlet.forward.request_uri").toString();
		if(returnString.trim().equals("")) {
			returnString = request.getServletPath().substring(1);
		} else {
			returnString.substring(returnString.lastIndexOf("/")+1);
		}
	}catch(Exception ex) {
		returnString = request.getServletPath().substring(1); 
	}
	
	return returnString;
}

public String getQueryString( HttpServletRequest servletRequest  )
{
	
	StringBuilder queryString = new StringBuilder();
	  Map<String, String[]> params= servletRequest.getParameterMap();
	  Set<String> pset= params.keySet();
	  boolean isFirst=true;
	  if(pset!=null&&pset.size()>0) {
	  	  for(String name:pset) {
	  		  for(String value:params.get(name) ) {
				 StringBuilder tocken=new StringBuilder();
        		   tocken.append(name).append("=").append(value);
        		   				if(isFirst) {
        		   						queryString.append(tocken);
        		   						isFirst=false;
        		   				} else {
        		   						queryString.append("&").append(tocken);
        		   }
        		    
			  }
      		 
		  }
	  }
	  return queryString.toString();
}
}
