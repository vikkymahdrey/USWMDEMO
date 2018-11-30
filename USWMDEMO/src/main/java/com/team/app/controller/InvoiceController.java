package com.team.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team.app.logger.AtLogger;

@Controller
public class InvoiceController 
{
	private static final AtLogger logger = AtLogger.getLogger(InvoiceController.class);
	
	@RequestMapping(value= {"/invoice"}, method=RequestMethod.GET)
	public String InvoceResponceHandler(HttpServletRequest request, HttpServletResponse response)
	{	
		logger.debug("In invoice handler");
			return "Invoice";
	}
	

	@RequestMapping(value= {"/transactions"}, method=RequestMethod.GET)
	public String transactionsHandler(HttpServletRequest request, HttpServletResponse response)
	{		
		logger.debug("In transactions handler");
			return "transactions";
	}
}
