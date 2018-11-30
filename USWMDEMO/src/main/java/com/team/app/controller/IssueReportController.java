package com.team.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team.app.logger.AtLogger;

@Controller
public class IssueReportController 
{
	
	private static final AtLogger logger = AtLogger.getLogger(InvoiceController.class);
	
	@RequestMapping(value= {"/issue"}, method=RequestMethod.GET)
	public String IssueReport(HttpServletRequest request, HttpServletResponse response)
	{
		logger.debug("In issue handler");
			return "IssueReport";
	}
	

}