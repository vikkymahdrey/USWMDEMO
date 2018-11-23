package com.team.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.app.logger.AtLogger;

@Controller
public class GraphicController {
	
	private static final AtLogger logger = AtLogger.getLogger(GraphicController.class);
	
	@RequestMapping(value= {"/barChart"})
	public String barCharthandler(HttpServletRequest request){
		logger.debug("IN /barChart URL");
		return "chartjs";
	}
	
	@RequestMapping(value= {"/flotChart"})
	public String flotCharthandler(HttpServletRequest request){
		return "flot";
	}
	
	@RequestMapping(value= {"/chartsMap"})
	public String chartsMapHandler(HttpServletRequest request){
		return "chartmap";
	}
	
	
	@RequestMapping(value= {"/inlineChart"})
	public String inLineCharthandler(HttpServletRequest request){
		return "inline";
	}
	
	@RequestMapping(value= {"/reports"})
	public String genReportsHandler(HttpServletRequest request){
		return "simple";
	}

}
