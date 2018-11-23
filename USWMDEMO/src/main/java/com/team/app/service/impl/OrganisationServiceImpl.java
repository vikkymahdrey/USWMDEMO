package com.team.app.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.app.constant.AppConstants;
import com.team.app.domain.LoraFrame;
import com.team.app.dto.ApplicationDto;
import com.team.app.logger.AtLogger;
import com.team.app.service.ConsumerInstrumentService;
import com.team.app.service.OrganisationService;

@Service
public class OrganisationServiceImpl implements OrganisationService {
	
	private static final AtLogger logger = AtLogger.getLogger(OrganisationServiceImpl.class);
	
	@Autowired
	private ConsumerInstrumentService  consumerInstrumentServiceImpl;	

	
	public Map<String, Object> getLoraServerOrganisation() throws Exception {
		
		Map<String,Object> organisations=null;
						
			 try {
				   organisations=new HashMap<String,Object>();	
				   String url=AppConstants.org_url;
					logger.debug("URLConn",url);
					URL obj1 = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj1.openConnection();
					con.setDoOutput(true);
					con.setRequestMethod("GET");
					con.setRequestProperty("accept", "application/json");
					con.setRequestProperty("Content-Type", "application/json");
					con.setRequestProperty("Grpc-Metadata-Authorization",AppConstants.jwtToken);
					
					    
					int responseCode = con.getResponseCode();
						logger.debug("POST Response Code :: " + responseCode);
							    				
					if(responseCode == HttpURLConnection.HTTP_OK) {
						logger.debug("Token valid,POST Response with 200");
						
						BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
						String inputLine;
						StringBuffer response = new StringBuffer();

						while ((inputLine = in.readLine()) != null) {
							response.append(inputLine);
						}
						
						in.close();
						
						JSONObject json=null;
							json=new JSONObject();
						json=(JSONObject)new JSONParser().parse(response.toString());
					
						JSONArray arr=(JSONArray) json.get("result"); 
						//long orgCount=(long) json.get("totalCount");
						
						if(arr!=null && arr.size()>0){
							logger.debug("Inside Array not null");
							 for (int i = 0; i < arr.size(); i++) {
								 JSONObject jsonObj = (JSONObject) arr.get(i);													
									logger.debug("Name matching ..");
									logger.debug("Organisation name ..",jsonObj.get("name").toString());
									logger.debug("Organisation id ..",jsonObj.get("id").toString());
									organisations.put(jsonObj.get("id").toString(), jsonObj.get("name"));
									
							 }
				        }
						
						//organisations.put("orgCount", orgCount);
					}
					
			   }catch(Exception e){
					e.printStackTrace();
			   }
	   
			 return organisations;
	}


	
	public long getLoraServerUsers() throws Exception {
		long userCount=0;
		
		 try {
			 	
			   String url=AppConstants.user_url;
				logger.debug("URLConn",url);
				URL obj1 = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj1.openConnection();
				con.setDoOutput(true);
				con.setRequestMethod("GET");
				con.setRequestProperty("accept", "application/json");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Grpc-Metadata-Authorization",AppConstants.jwtToken);
				
				    
				int responseCode = con.getResponseCode();
					logger.debug("POST Response Code :: " + responseCode);
						    				
				if(responseCode == HttpURLConnection.HTTP_OK) {
					logger.debug("Token valid,POST Response with 200");
					
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					
					in.close();
					
					JSONObject json=null;
						json=new JSONObject();
					json=(JSONObject)new JSONParser().parse(response.toString());
				
					userCount=(long) json.get("totalCount");   
					//JSONArray arr=(JSONArray) json.get("result");
					
			  }
				
				
		   }catch(Exception e){
				e.printStackTrace();
		   }
  
		 return userCount;
	}



	
	public String getLoraServerApplicationByOrgId(String orgId) throws Exception {
		
		String retVal="";
		try{
			  String url=AppConstants.app_url;
				logger.debug("URLConn",url);
				URL obj1 = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj1.openConnection();
				con.setDoOutput(true);
				con.setRequestMethod("GET");
				con.setRequestProperty("accept", "application/json");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Grpc-Metadata-Authorization",AppConstants.jwtToken);
				
				    
				int responseCode = con.getResponseCode();
					logger.debug("POST Response Code :: " + responseCode);
					
				
						    				
				if(responseCode == HttpURLConnection.HTTP_OK) {
					logger.debug("Token valid,POST Response with 200");
					
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					
					in.close();
					
					JSONObject json=null;
						json=new JSONObject();
					json=(JSONObject)new JSONParser().parse(response.toString());
				
					JSONArray arr=(JSONArray) json.get("result");
					
					List<ApplicationDto> dtos=null;	
					
					if(arr!=null && arr.size()>0){
						logger.debug("Inside Array not null");
							dtos=new ArrayList<ApplicationDto>();
						 for (int i = 0; i < arr.size(); i++) {
							 JSONObject jsonObj = (JSONObject) arr.get(i);
							 ApplicationDto dto=null;
							 		dto=new ApplicationDto();
							
							if(jsonObj.get("organizationID").toString().equalsIgnoreCase(orgId)){
								logger.debug("organizationID matching ..");
								logger.debug("Application name ..",jsonObj.get("name").toString());
								logger.debug("Application id ..",jsonObj.get("id").toString());
								
								dto.setAppId(jsonObj.get("id").toString());
								dto.setAppName(jsonObj.get("name").toString());
								dtos.add(dto);							
							}
						 }
			        }
					
					if(dtos!=null && !dtos.isEmpty()){
						for(ApplicationDto a : dtos){
							retVal+="<option value="+a.getAppId()+":"+a.getAppName()+">"+ a.getAppName() + "</option>";
						}
					}
				}
			
				
			
		}catch(Exception e){
			logger.error("Error in Ajax/getApplications",e);
			e.printStackTrace();
		}
		
		return retVal;
	}



	
	public String getLoraServerDevEUIByAppId(String appId) throws Exception {
		String returnVal="";		
		try{		  
			   String url=AppConstants.domain+"/api/applications/"+appId+"/nodes?limit=100";
				logger.debug("URLConn",url);
				URL obj1 = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj1.openConnection();
				con.setDoOutput(true);
				con.setRequestMethod("GET");
				con.setRequestProperty("accept", "application/json");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Grpc-Metadata-Authorization",AppConstants.jwtToken);
				
				    
				int responseCode = con.getResponseCode();
					logger.debug("POST Response Code :: " + responseCode);
					
				
						    				
				if(responseCode == HttpURLConnection.HTTP_OK) {
					logger.debug("Token valid,POST Response with 200");
					
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					
					in.close();
					
					JSONObject json=null;
						json=new JSONObject();
					json=(JSONObject)new JSONParser().parse(response.toString());
				
					JSONArray arr=(JSONArray) json.get("result");
					
					List<ApplicationDto> dtos=null;	
					
					if(arr!=null && arr.size()>0){
						logger.debug("Inside Array not null");
							dtos=new ArrayList<ApplicationDto>();
						 for (int i = 0; i < arr.size(); i++) {
							 JSONObject jsonObj = (JSONObject) arr.get(i);
							 ApplicationDto dto=null;
							 		dto=new ApplicationDto();						
							
								logger.debug("DevEUI name ..",jsonObj.get("devEUI").toString());
													
								dto.setDevEUI(jsonObj.get("devEUI").toString());
								dto.setDevName(jsonObj.get("name").toString());
								dtos.add(dto);							
							
						  }
			         }
					
					if(dtos!=null && !dtos.isEmpty()){
						for(ApplicationDto a : dtos){
							logger.debug("DevEUI DevEUI..",a.getDevEUI());
									returnVal+="<option value="+a.getDevEUI()+">"+a.getDevName()+"-"+ a.getDevEUI() + "</option>";
								
						}
					}
				}
			
				
			
		}catch(Exception e){
			logger.error("Error in Ajax/getDevEUI",e);
			e.printStackTrace();
		}
			
			
		return returnVal;
	}



	
	public String getLoraServerDevEUIByAppIdForSync(String appId) throws Exception {
		String returnVal="";	
		
		try{
						
			   String url=AppConstants.domain+"/api/applications/"+appId+"/nodes?limit=100";
				logger.debug("URLConn",url);
				URL obj1 = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj1.openConnection();
				con.setDoOutput(true);
				con.setRequestMethod("GET");
				con.setRequestProperty("accept", "application/json");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Grpc-Metadata-Authorization",AppConstants.jwtToken);
				
				    
				int responseCode = con.getResponseCode();
					logger.debug("POST Response Code :: " + responseCode);
					
				
						    				
				if(responseCode == HttpURLConnection.HTTP_OK) {
					logger.debug("Token valid,POST Response with 200");
					
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					
					in.close();
					
					JSONObject json=null;
						json=new JSONObject();
					json=(JSONObject)new JSONParser().parse(response.toString());
				
					JSONArray arr=(JSONArray) json.get("result");
					
					List<ApplicationDto> dtos=null;	
					
					if(arr!=null && arr.size()>0){
						logger.debug("Inside Array not null");
							dtos=new ArrayList<ApplicationDto>();
						 for (int i = 0; i < arr.size(); i++) {
							 JSONObject jsonObj = (JSONObject) arr.get(i);
							 ApplicationDto dto=null;
							 		dto=new ApplicationDto();						
							
								logger.debug("DevEUI name ..",jsonObj.get("devEUI").toString());
													
								dto.setDevEUI(jsonObj.get("devEUI").toString());
								dto.setDevName(jsonObj.get("name").toString());
								dtos.add(dto);							
							
						  }
			         }
					
					if(dtos!=null && !dtos.isEmpty()){
						for(ApplicationDto a : dtos){
							 returnVal+="<option value="+a.getDevEUI()+">"+a.getDevName()+"-"+ a.getDevEUI() + "</option>";
								
						}
					}
				}
			
				
			
		}catch(Exception e){
			logger.error("Error in Ajax/getApplications",e);
			e.printStackTrace();
		}
			
			
		return returnVal;
	}



	public String getLoraServerDevEUIByAppIdCall(String appId) throws Exception {
		String returnVal="";
		
		try{		
			   String url=AppConstants.domain+"/api/applications/"+appId+"/nodes?limit=100";
				logger.debug("URLConn",url);
				URL obj1 = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj1.openConnection();
				con.setDoOutput(true);
				con.setRequestMethod("GET");
				con.setRequestProperty("accept", "application/json");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Grpc-Metadata-Authorization",AppConstants.jwtToken);
				
				    
				int responseCode = con.getResponseCode();
					logger.debug("POST Response Code :: " + responseCode);
					
				
						    				
				if(responseCode == HttpURLConnection.HTTP_OK) {
					logger.debug("Token valid,POST Response with 200");
					
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					
					in.close();
					
					JSONObject json=null;
						json=new JSONObject();
					json=(JSONObject)new JSONParser().parse(response.toString());
				
					JSONArray arr=(JSONArray) json.get("result");
					
					List<ApplicationDto> dtos=null;	
					
					if(arr!=null && arr.size()>0){
						logger.debug("Inside Array not null");
							dtos=new ArrayList<ApplicationDto>();
						 for (int i = 0; i < arr.size(); i++) {
							 JSONObject jsonObj = (JSONObject) arr.get(i);
							 ApplicationDto dto=null;
							 		dto=new ApplicationDto();						
							
								logger.debug("DevEUI name ..",jsonObj.get("devEUI").toString());
													
								dto.setDevEUI(jsonObj.get("devEUI").toString());
								dto.setDevName(jsonObj.get("name").toString());
								dtos.add(dto);							
							
						  }
			         }
					
					if(dtos!=null && !dtos.isEmpty()){
						for(ApplicationDto a : dtos){
							 returnVal+="<option value="+a.getDevEUI()+":"+a.getDevName()+">"+a.getDevName()+"-"+ a.getDevEUI() + "</option>";
								
						}
					}
				}
			
				
			
		}catch(Exception e){
			logger.error("Error in Ajax/getDevEUIByAppId",e);
			e.printStackTrace();
		}
			
			
		return returnVal;

	}



	public String getLoraServerDevEUIByAppIdModal(String appId) throws Exception {
		String returnVal="";	
		
		try{
		
			   String url=AppConstants.domain+"/api/applications/"+appId+"/nodes?limit=100";
				logger.debug("URLConn",url);
				URL obj1 = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj1.openConnection();
				con.setDoOutput(true);
				con.setRequestMethod("GET");
				con.setRequestProperty("accept", "application/json");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Grpc-Metadata-Authorization",AppConstants.jwtToken);
				
				    
				int responseCode = con.getResponseCode();
					logger.debug("POST Response Code :: " + responseCode);
					
				
						    				
				if(responseCode == HttpURLConnection.HTTP_OK) {
					logger.debug("Token valid,POST Response with 200");
					
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					
					in.close();
					
					JSONObject json=null;
						json=new JSONObject();
					json=(JSONObject)new JSONParser().parse(response.toString());
				
					JSONArray arr=(JSONArray) json.get("result");
					
					List<ApplicationDto> dtos=null;	
					
					if(arr!=null && arr.size()>0){
						logger.debug("Inside Array not null");
							dtos=new ArrayList<ApplicationDto>();
						 for (int i = 0; i < arr.size(); i++) {
							 JSONObject jsonObj = (JSONObject) arr.get(i);
							 ApplicationDto dto=null;
							 		dto=new ApplicationDto();						
							
								logger.debug("DevEUI name ..",jsonObj.get("devEUI").toString());
													
								dto.setDevEUI(jsonObj.get("devEUI").toString());
								dto.setDevName(jsonObj.get("name").toString());
								dto.setAppId(jsonObj.get("applicationID").toString());
								dtos.add(dto);							
							
						  }
			         }
					
					if(dtos!=null && !dtos.isEmpty()){
						for(ApplicationDto a : dtos){
							returnVal+="<tr><td><a href=\"#\" class=\"usrModal\"><span class=\"info-box-number\"><b>"+a.getDevEUI()+"</b></span></a></td><td>"+a.getDevName()+"</td><td>"+a.getAppId()+"</td></tr>";
								
						}
					}
				}
			
				
			
		}catch(Exception e){
			logger.error("Error in Ajax/getDevByAppIDModal",e);
			e.printStackTrace();
		}
			
			
		return returnVal;
	}



	public String getLoraServerApplicationByOrgIdModal(String orgId) throws Exception {
		String returnVal="";		
		
		try{
								
			   String url=AppConstants.app_url;
				logger.debug("URLConn",url);
				URL obj1 = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj1.openConnection();
				con.setDoOutput(true);
				con.setRequestMethod("GET");
				con.setRequestProperty("accept", "application/json");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Grpc-Metadata-Authorization",AppConstants.jwtToken);
				
				    
				int responseCode = con.getResponseCode();
					logger.debug("POST Response Code :: " + responseCode);
					
				
						    				
				if(responseCode == HttpURLConnection.HTTP_OK) {
					logger.debug("Token valid,POST Response with 200");
					
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					
					in.close();
					
					JSONObject json=null;
						json=new JSONObject();
					json=(JSONObject)new JSONParser().parse(response.toString());
				
					JSONArray arr=(JSONArray) json.get("result");
					
					List<ApplicationDto> dtos=null;	
					
					if(arr!=null && arr.size()>0){
						logger.debug("Inside Array not null");
							dtos=new ArrayList<ApplicationDto>();
						 for (int i = 0; i < arr.size(); i++) {
							 JSONObject jsonObj = (JSONObject) arr.get(i);
							 ApplicationDto dto=null;
							 		dto=new ApplicationDto();
							
							if(jsonObj.get("organizationID").toString().equalsIgnoreCase(orgId)){
								logger.debug("organizationID matching ..");
								logger.debug("Application name ..",jsonObj.get("name").toString());
								logger.debug("Application id ..",jsonObj.get("id").toString());
								
								dto.setAppId(jsonObj.get("id").toString());
								dto.setAppName(jsonObj.get("name").toString());
								dto.setOrgId(jsonObj.get("organizationID").toString());
								dtos.add(dto);							
							}
						 }
			        }
					
					if(dtos!=null && !dtos.isEmpty()){
						for(ApplicationDto a : dtos){							
							returnVal+="<tr><td><a href=\"#\" ajaxid="+a.getAppId()+" class=\"devModal\"><span class=\"info-box-number\"><b>"+a.getAppId()+"</b></span></a></td><td>"+a.getAppName()+"</td><td>"+a.getOrgId()+"</td></tr>";
						}
					}
				}
			
				
			
		}catch(Exception e){
			logger.error("Error in Ajax/getAppByOrgIDModal",e);
			e.printStackTrace();
		}
			
			
		return returnVal;
	}




	public String getLoraServerUsersByOrgIdModal(String orgId) throws Exception {
		String retVal="";
		try{
			  String url=AppConstants.org+orgId+"/users?limit=500";
				logger.debug("URLConn",url);
				URL obj1 = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj1.openConnection();
				con.setDoOutput(true);
				con.setRequestMethod("GET");
				con.setRequestProperty("accept", "application/json");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Grpc-Metadata-Authorization",AppConstants.jwtToken);
				
				    
				int responseCode = con.getResponseCode();
					logger.debug("POST Response Code :: " + responseCode);
					
				
						    				
				if(responseCode == HttpURLConnection.HTTP_OK) {
					logger.debug("Token valid,POST Response with 200");
					
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					
					in.close();
					
					JSONObject json=null;
						json=new JSONObject();
					json=(JSONObject)new JSONParser().parse(response.toString());
				
					JSONArray arr=(JSONArray) json.get("result");
					
					List<ApplicationDto> dtos=null;	
					
					
					if(arr!=null && arr.size()>0){
						logger.debug("Inside Array not null");
							dtos=new ArrayList<ApplicationDto>();
						 for (int i = 0; i < arr.size(); i++) {
							 JSONObject jsonObj = (JSONObject) arr.get(i);
							 ApplicationDto dto=null;
							 		dto=new ApplicationDto();
												
								logger.debug("userId..",jsonObj.get("id").toString());
								logger.debug("username..",jsonObj.get("username").toString());
								logger.debug("isAdmin..",String.valueOf(jsonObj.get("isAdmin")));
								
								dto.setUserId(jsonObj.get("id").toString());
								dto.setUsername(jsonObj.get("username").toString());
								dto.setIsAdmin(String.valueOf(jsonObj.get("isAdmin")));
								dtos.add(dto);							
							}
						 }
			        
					
					if(dtos!=null && !dtos.isEmpty()){
						for(ApplicationDto a : dtos){
							retVal+="<tr><td>"+a.getUserId()+"</td><td>"+a.getUsername()+"</td><td>"+a.getIsAdmin()+"</td><td>"+orgId+"</td></tr>";
						}
					}
				}
			
				
			
		}catch(Exception e){
			logger.error("Error in Ajax/getApplications",e);
			e.printStackTrace();
		}
		
		return retVal;
	}

}
