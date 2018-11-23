package com.team.app.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.app.constant.AppConstants;
import com.team.app.domain.TblKeyword;
import com.team.app.domain.TblKeywordType;
import com.team.app.logger.AtLogger;
import com.team.app.service.KeywordService;
import com.team.app.utils.JsonUtil;
@Controller
public class KeywordController {
	private static final AtLogger logger = AtLogger.getLogger(KeywordController.class);
	
	@Autowired
	private KeywordService keywordService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value= {"/getKeywordByTypeId"}, method=RequestMethod.POST)
	public @ResponseBody String getKeywordByTypeIdHandler(HttpServletRequest request,Map<String,Object> map) throws Exception  {
		logger.debug("/*Ajax getting getKeywordByTypeId */");		
		String typeId = request.getParameter("typeId").trim();		
		logger.debug("typeId as ",typeId);
		String returnVal="";
		
		
		try{
				TblKeywordType Keytype=	keywordService.getKeyTypeById(typeId);
				if(Keytype!=null){
					List<TblKeyword> keys=Keytype.getTblKeywords();
					if(keys!=null && !keys.isEmpty()){
						JSONArray jsonArr=null;
							jsonArr=new JSONArray();
						for(TblKeyword key : keys){
							JSONObject json=null;
								json=new JSONObject();
								if(key.getKey().equalsIgnoreCase(AppConstants.orgName)){
									json.put(AppConstants.orgName, key.getValue());
									json.put("desc", key.getDesc());
								}else if(key.getKey().equalsIgnoreCase(AppConstants.appName)){
									json.put(AppConstants.appName, key.getValue());
									json.put("desc", key.getDesc());
								}else if(key.getKey().equalsIgnoreCase(AppConstants.devName)){
									json.put(AppConstants.devName, key.getValue());
									json.put("desc", key.getDesc());
								}
								jsonArr.add(json);							
						}
						
						JSONObject js=null;
							js=new JSONObject();
							js.put("result", jsonArr);
						returnVal=JsonUtil.objToJson(js);
						
					}
				}
				
				logger.debug("Resultant",returnVal);
			
			
		}catch(Exception e){
			logger.error("Error in Ajax/getKeywordByTypeId",e);
			return returnVal;
		}
			
			
		return returnVal;
	}
	
	
}
