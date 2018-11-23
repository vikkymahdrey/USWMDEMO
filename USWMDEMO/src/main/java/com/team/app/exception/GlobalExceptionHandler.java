/*
package com.team.app.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);	
	
	
	@ExceptionHandler(Exception.class)
    public String handleAllException(HttpServletRequest request, Exception ex){
		logger.debug("In @ControllerAdvice");
        logger.debug("Requested URL="+request.getRequestURL());
        logger.debug("Exception as="+ex);             
        
        HttpSession session=request.getSession();
        			session.setAttribute("url Request", request.getRequestURL()); 
        			
        			session.setAttribute("statusLog",String.valueOf(session.getAttribute("statusLog")));
        			session.setAttribute("url",String.valueOf(session.getAttribute("url")));
        			session.setAttribute("exception",String.valueOf(session.getAttribute("exception")));
        			session.setAttribute("className", String.valueOf(session.getAttribute("className")));
        			session.setAttribute("methodName",String.valueOf(session.getAttribute("methodName")));
        			session.setAttribute("lineNumber", String.valueOf(session.getAttribute("lineNumber")));
               
       
        return "exception";
    } 
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
	@ExceptionHandler(IOException.class)
	public void handleIOException(){
		logger.error("IOException handler executed");
		//returning 404 error code
	}
}
*/