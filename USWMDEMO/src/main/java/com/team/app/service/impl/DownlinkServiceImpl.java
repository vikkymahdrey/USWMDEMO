package com.team.app.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.app.constant.AppConstants;
import com.team.app.dao.DownlinkHourlyConfigDao;
import com.team.app.dao.DownlinkPacketConfigDao;
import com.team.app.dao.DownlinkQueueDao;
import com.team.app.dao.DownlinkRetryConfigDao;
import com.team.app.domain.DownlinkQueue;
import com.team.app.domain.TblDownlinkHoulyConfig;
import com.team.app.domain.TblDownlinkPacketConfig;
import com.team.app.domain.TblRetryConfig;
import com.team.app.logger.AtLogger;
import com.team.app.service.ConsumerInstrumentService;
import com.team.app.service.DownlinkService;
import com.team.app.utils.DateUtil;

@Service
public class DownlinkServiceImpl implements DownlinkService {
	
	private static final AtLogger logger = AtLogger.getLogger(DownlinkServiceImpl.class);
	
	@Autowired
	private DownlinkPacketConfigDao downlinkPacketConfigDao;
	
	@Autowired
	private DownlinkHourlyConfigDao downlinkHourlyConfigDao;	
	
	@Autowired
	private ConsumerInstrumentService  consumerInstrumentService;
	
	@Autowired
	private DownlinkRetryConfigDao downlinkRetryConfigDao;
	
	@Autowired
	private DownlinkQueueDao downlinkQueueDao;
	
	

	public List<TblDownlinkHoulyConfig> getDownlinkHourlyConfig() throws Exception {
		return downlinkHourlyConfigDao.getDownlinkHourlyConfig();
	}

	
	public List<TblDownlinkPacketConfig> getDownlinkPacketByHourlyID(String hourly) throws Exception {
		
		return downlinkPacketConfigDao.getDownlinkPacketByHourlyID(hourly);
	}


	
	public TblDownlinkHoulyConfig getDownlinkPktPerDayByID(String perDayPkt) throws Exception {
		return downlinkHourlyConfigDao.getDownlinkPktPerDayByID(perDayPkt);
	}


	
	@SuppressWarnings("unchecked")
	public void doDownlinkForDate(String devEUI, String applicationID, String fport) throws Exception {
		logger.debug("In doDownlinkForDate");
		try{
			//LoraFrame frm=consumerInstrumentService.getLoraFrameByDevEUIAndAppID(devEUI,applicationID);
			//if(frm!=null){
				logger.debug("Inside IF doDownlinkForDate");
				byte[] byteArr = new byte[7];
				
				 SimpleDateFormat formatDay = new SimpleDateFormat("dd");
				 SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
				 SimpleDateFormat formatYear = new SimpleDateFormat("YY");
				 SimpleDateFormat datetimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 datetimestamp.setTimeZone(TimeZone.getTimeZone("IST"));
				 
				   String currentDay = formatDay.format(new Date(System.currentTimeMillis()));
				    String currentMonth = formatMonth.format(new Date(System.currentTimeMillis()));
				    String currentYear = formatYear.format(new Date(System.currentTimeMillis()));
				    Date dateTmStmp=datetimestamp.parse(datetimestamp.format(new Date(System.currentTimeMillis())));
				    
				    /*Calendar calendar = Calendar.getInstance();

				    logger.debug("Year: " , calendar.get(Calendar.YEAR));
				    logger.debug("Month: " , calendar.get(Calendar.MONTH));
				    logger.debug("Date: " , calendar.get(Calendar.DATE));

				    logger.debug("Hour: " , calendar.get(Calendar.HOUR));
				    logger.debug("Minute: " , calendar.get(Calendar.MINUTE));
				    logger.debug("Second: " , calendar.get(Calendar.SECOND));*/
				
				
			
				byteArr[0]=Byte.valueOf(AppConstants.date);
				byteArr[1]=Byte.valueOf(currentYear);
				byteArr[2]=Byte.valueOf(currentMonth);
				byteArr[3]=Byte.valueOf(currentDay);
				byteArr[4]=Byte.valueOf(String.valueOf(dateTmStmp.getHours()));
				byteArr[5]=Byte.valueOf(String.valueOf(dateTmStmp.getMinutes()));
				byteArr[6]=Byte.valueOf(String.valueOf(dateTmStmp.getSeconds()));
								
				
				logger.debug("/byteArr[0] ",byteArr[0]);				
				logger.debug("/byteArr[1] ",byteArr[1]);
				logger.debug("/byteArr[2] ",byteArr[2]);
				logger.debug("/byteArr[3] ",byteArr[3]);
				logger.debug("/byteArr[4] ",byteArr[4]);
				logger.debug("/byteArr[5] ",byteArr[5]);
				logger.debug("/byteArr[6] ",byteArr[6]);
				
				
				
				int fPort=0;
				try{
					fPort=Integer.parseInt(fport);
				}catch(Exception e){
					;
				}
				
				 logger.debug("/fPort printing int ",fPort);			 
				 logger.debug("Base64 resultant",Base64.encodeBase64String(byteArr));
				 
				 JSONObject jsonObj=null;
					jsonObj=new JSONObject();
					jsonObj.put("confirmed",true);
					jsonObj.put("data",Base64.encodeBase64String(byteArr));
					jsonObj.put("devEUI",devEUI);
					jsonObj.put("fPort",fPort);
					jsonObj.put("reference","DateSetting");
	
				
				String jsonData=jsonObj.toString(); 			
						
				String url=AppConstants.domain+"/api/nodes/"+devEUI+"/queue";
	   				logger.debug("URLConn",url);	    				
	   				URL obj1 = new URL(url);
	   				HttpURLConnection con = (HttpURLConnection) obj1.openConnection();
	   				con.setDoOutput(true);
	   				con.setRequestMethod("POST");
	   				con.setRequestProperty("accept", "application/json");
	   				con.setRequestProperty("Content-Type", "application/json");
	   				con.setRequestProperty("Grpc-Metadata-Authorization",AppConstants.jwtToken);
	   				
	   				OutputStream os = con.getOutputStream();
	   		        os.write(jsonData.getBytes());
	   		        os.flush();
	   		        os.close();
	   		        
	   				int responseCode = con.getResponseCode();
	   					logger.debug("POST Response Code :: " + responseCode);
	   						logger.debug("POST Response message :: " + con.getResponseMessage());
	   				
	   				
			//}				
			
		}catch(Exception e){
			logger.error("doDownlinkforDate",e);
			
		}
		
	}


	
	@SuppressWarnings("unchecked")
	public String doDownlinkForMPDU(String devEUI, String applicationID, String fport,String pktId, String packetlength) throws Exception {
		String returnVal="";		
		try{
			TblDownlinkHoulyConfig pkts=getDownlinkPktPerDayByID(pktId);
			if(pkts!=null){					
										
					byte[] byteArr = new byte[2];
					byteArr[0]=Byte.valueOf(AppConstants.MPDU);
						int packet=Integer.parseInt(pkts.getPerday());
						int pktlng=Integer.parseInt(packetlength);
						logger.debug("pkt...",packet);
						logger.debug("pktlng...",pktlng);
						//int lng=256+(packet*16)+pktlng;
						  //int lng=256+(packet*16)+pktlng;
						int lng=Byte.toUnsignedInt((byte) ((packet*16)+pktlng));
						//logger.debug("lng...",lng);
						  byteArr[1]=(byte)lng;	
						  
					
					
					logger.debug("/byteArr[0] ",byteArr[0]);				
					logger.debug("/byteArr[1] ",byteArr[1]);
					
					int fPrt=0;
					try{
						fPrt=Integer.parseInt(fport);
					}catch(Exception e){
						;
					}
					
					 logger.debug("/fPort printing int ",fPrt);			 
					 logger.debug("Base64 resultant",Base64.encodeBase64String(byteArr));
					 
					 JSONObject jsonObj=null;
		 				jsonObj=new JSONObject();
		 				jsonObj.put("confirmed",true);
		 				jsonObj.put("data",Base64.encodeBase64String(byteArr));
		 				jsonObj.put("devEUI",devEUI);
		 				jsonObj.put("fPort",fPrt);
		 				jsonObj.put("reference","MPDU");
			
					
						String jsonData=jsonObj.toString(); 			
							
						String url=AppConstants.domain+"/api/nodes/"+devEUI+"/queue";
		    				logger.debug("URLConn",url);	    				
		    				URL obj1 = new URL(url);
		    				HttpURLConnection con = (HttpURLConnection) obj1.openConnection();
		    				con.setDoOutput(true);
		    				con.setRequestMethod("POST");
		    				con.setRequestProperty("accept", "application/json");
		    				con.setRequestProperty("Content-Type", "application/json");
		    				con.setRequestProperty("Grpc-Metadata-Authorization",AppConstants.jwtToken);
		    				
		    				OutputStream os = con.getOutputStream();
		    		        os.write(jsonData.getBytes());
		    		        os.flush();
		    		        os.close();
		    		        
		    				int responseCode = con.getResponseCode();
		    					logger.debug("POST Response Code :: " + responseCode);
		    						logger.debug("POST Response message :: " + con.getResponseMessage());
		    				
		    				if(responseCode == HttpURLConnection.HTTP_OK) {
		    					returnVal="success";	
		    				}else{
		    					returnVal="failed";
		    				}
		    				
		    				
							
			}else{
				returnVal="Packet Per Day Not Found!";
			}		
		
		}catch (Exception e) {
			returnVal="Exception!";
		}	
		
		return returnVal;
	
	}


	public List<TblRetryConfig> getDownlinkForRetryConfig() throws Exception {
		
		return downlinkRetryConfigDao.getDownlinkForRetryConfig();
	}


	
	public TblRetryConfig getRetryConfigById(String retryId) throws Exception {
		return downlinkRetryConfigDao.getRetryConfigById(retryId);
	}


	
	public String doDownlinkForRetry(String devEUI, String applicationID, String fport, String retry,
			String interval) throws Exception {
		String returnVal="";
		logger.debug("In doDownlinkForRetry");
		try{			
				byte[] byteArr = new byte[3];
				
				logger.debug("/byteArr[0] ",byteArr[0]);				
				logger.debug("/byteArr[1] ",byteArr[1]);
						
				byteArr[0]=Byte.valueOf(AppConstants.retry);
				byteArr[1]=Byte.valueOf(retry);
				byteArr[2]=Byte.valueOf(interval);
				
								
				
				logger.debug("/byteArr[0] ",byteArr[0]);				
				logger.debug("/byteArr[1] ",byteArr[1]);
				logger.debug("/byteArr[2] ",byteArr[2]);
				
				
				int fPort=0;
				try{
					fPort=Integer.parseInt(fport);
				}catch(Exception e){
					;
				}
				
				 logger.debug("/fPort printing int ",fPort);			 
				 logger.debug("Base64 resultant",Base64.encodeBase64String(byteArr));
				 
				 JSONObject jsonObj=null;
					jsonObj=new JSONObject();
					jsonObj.put("confirmed",true);
					jsonObj.put("data",Base64.encodeBase64String(byteArr));
					jsonObj.put("devEUI",devEUI);
					jsonObj.put("fPort",fPort);
					jsonObj.put("reference","DateSetting");
	
				
				String jsonData=jsonObj.toString(); 			
						
				String url=AppConstants.domain+"/api/nodes/"+devEUI+"/queue";
	   				logger.debug("URLConn",url);	    				
	   				URL obj1 = new URL(url);
	   				HttpURLConnection con = (HttpURLConnection) obj1.openConnection();
	   				con.setDoOutput(true);
	   				con.setRequestMethod("POST");
	   				con.setRequestProperty("accept", "application/json");
	   				con.setRequestProperty("Content-Type", "application/json");
	   				con.setRequestProperty("Grpc-Metadata-Authorization",AppConstants.jwtToken);
	   				
	   				OutputStream os = con.getOutputStream();
	   		        os.write(jsonData.getBytes());
	   		        os.flush();
	   		        os.close();
	   		        
	   				int responseCode = con.getResponseCode();
	   					logger.debug("POST Response Code :: " + responseCode);
	   						logger.debug("POST Response message :: " + con.getResponseMessage());
	   				
	   						if(responseCode == HttpURLConnection.HTTP_OK) {
		    					returnVal="success";	
		    				}else{
		    					returnVal="failed";
		    				}
				
			
		}catch(Exception e){
			logger.error("doDownlinkForRetry",e);
			
		}
		
		return returnVal;
	}


	
	
	
	public void doDownlinkForMissingPkt(String devEUI, String applicationID, String fport, String deviceId,String mpdu, String pdu)	throws Exception {
		logger.debug("In doDownlinkForMissingPKT");
		try{			
			
				byte[] byteArr = new byte[4];
				int cmdIdVal=0;
				String idVal="";
				if(deviceId.length()==1){
					cmdIdVal=Byte.toUnsignedInt((byte)(AppConstants.missing*16));
					idVal="0"+deviceId;
				}else if(deviceId.length()==2){
					cmdIdVal=Byte.toUnsignedInt((byte)(AppConstants.missing*16));
					idVal=deviceId;
				}else if(deviceId.length()==3){
					cmdIdVal=Byte.toUnsignedInt((byte)((AppConstants.missing*16)+Integer.parseInt(deviceId.substring(0, 1))));
					idVal=deviceId.substring(1, deviceId.length());
				}
					
				
					  byteArr[0]=(byte)cmdIdVal;
					  byteArr[1]=Byte.valueOf(idVal);
					  byteArr[2]=Byte.valueOf(mpdu);
					  byteArr[3]=Byte.valueOf(pdu);
					  
				
				
				logger.debug("/byteArr[0] ",byteArr[0]);				
				logger.debug("/byteArr[1] ",byteArr[1]);
				logger.debug("/byteArr[2] ",byteArr[2]);
				logger.debug("/byteArr[3] ",byteArr[3]);
								
				
				
				
				
				int fPort=0;
				try{
					fPort=Integer.parseInt(fport);
				}catch(Exception e){
					;
				}
				
				 logger.debug("/fPort printing int ",fPort);			 
				 logger.debug("Base64 resultant",Base64.encodeBase64String(byteArr));
				 
				 JSONObject jsonObj=null;
					jsonObj=new JSONObject();
					jsonObj.put("confirmed",true);
					jsonObj.put("data",Base64.encodeBase64String(byteArr));
					jsonObj.put("devEUI",devEUI);
					jsonObj.put("fPort",fPort);
					jsonObj.put("reference","MissingPkt");
	
				
				String jsonData=jsonObj.toString(); 			
						
				String url=AppConstants.domain+"/api/nodes/"+devEUI+"/queue";
	   				logger.debug("URLConn",url);	    				
	   				URL obj1 = new URL(url);
	   				HttpURLConnection con = (HttpURLConnection) obj1.openConnection();
	   				con.setDoOutput(true);
	   				con.setRequestMethod("POST");
	   				con.setRequestProperty("accept", "application/json");
	   				con.setRequestProperty("Content-Type", "application/json");
	   				con.setRequestProperty("Grpc-Metadata-Authorization",AppConstants.jwtToken);
	   				
	   				OutputStream os = con.getOutputStream();
	   		        os.write(jsonData.getBytes());
	   		        os.flush();
	   		        os.close();
	   		        
	   				int responseCode = con.getResponseCode();
	   					logger.debug("POST Response Code :: " + responseCode);
	   						logger.debug("POST Response message :: " + con.getResponseMessage());
	   						if(responseCode==HttpURLConnection.HTTP_OK){

	   							/*For Downlink queue checkout*/																	
								 try {										   
									   String urlQueue=AppConstants.domain+"/api/nodes/"+devEUI+"/queue";
										logger.debug("urlQueue ",urlQueue);
										URL urlQ = new URL(urlQueue);
										HttpURLConnection c = (HttpURLConnection) urlQ.openConnection();
										c.setDoOutput(true);
										c.setRequestMethod("GET");
										c.setRequestProperty("accept", "application/json");
										c.setRequestProperty("Content-Type", "application/json");
										c.setRequestProperty("Grpc-Metadata-Authorization",AppConstants.jwtToken);
										
										    
										int respQueue = c.getResponseCode();
											logger.debug("GET Response Code :: " + respQueue);
												    				
										if(respQueue == HttpURLConnection.HTTP_OK) {
											logger.debug("Token valid,GET Response with 200");
											
											BufferedReader inQ = new BufferedReader(new InputStreamReader(c.getInputStream()));
											String inputLine;
											StringBuffer respQ = new StringBuffer();

											while ((inputLine = inQ.readLine()) != null) {
												respQ.append(inputLine);
											}
											
											inQ.close();
											
											JSONObject j=null;
												j=new JSONObject();
											j=(JSONObject)new JSONParser().parse(respQ.toString());
										
											JSONArray arrJ=(JSONArray) j.get("items");    					
											
											if(arrJ!=null && arrJ.size()>0){
												logger.debug("Inside downlink arrJ not null");
												 logger.debug("Size Downink GET",arrJ.size());
												 for (int k = 0; k < arrJ.size(); k++) {
													 JSONObject jsObj = (JSONObject) arrJ.get(k);
													 logger.debug("jsonObj Downink GET",jsObj);
													 
													 if(jsObj.get("reference").toString().equalsIgnoreCase("MissingPkt")){
													 
													// persist downlink queue 
													//DownlinkQueue d=downlinkQueueDao.getDownlinkQueueByAppIdDevEUIAndDeviceId(applicationID, devEUI, deviceId);
														 DownlinkQueue d=downlinkQueueDao.getDownlinkQueueById(jsObj.get("id").toString());
													 if(d!=null){
														 d.setUpdatedAt(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
														 	downlinkQueueDao.save(d);
													 }else{
														 DownlinkQueue downlinkQ=null;
														 	downlinkQ=new DownlinkQueue();
														 	downlinkQ.setDownlinkID(jsObj.get("id").toString());
														 	downlinkQ.setApplicationID(applicationID);
														 	downlinkQ.setDevEui(devEUI);
														 	downlinkQ.setDeviceId(deviceId);
														 	downlinkQ.setFport(fport);
														 	downlinkQ.setFlag("0");
														 	downlinkQ.setMpdu(mpdu);
														 	downlinkQ.setPdu(pdu);
														 	downlinkQ.setData(jsObj.get("data").toString());
														 	downlinkQ.setCreatedAt(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
														 	downlinkQ.setUpdatedAt(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
														 	downlinkQueueDao.save(downlinkQ);
														 
													 }
													// ending downlink queue ended 
													
												 }else{
													 logger.debug("MissingPKT is empty");
													 DownlinkQueue d=downlinkQueueDao.getDownlinkQueueByAppIdDevEUIAndDeviceId(applicationID, devEUI, deviceId);
													 if(d!=null){
														 logger.debug("updating downlink information");														 
														 d.setFlag("1");
														 	d.setUpdatedAt(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
														 		downlinkQueueDao.save(d);
													 }
													 
													// downlinkQueueDao.updateDownlinkQueueFlag(applicationID, devEUI,deviceId);
												 }
													 
											   }		 
									        }
										}
										
								   }catch(Exception e){
										e.printStackTrace();
								   }
							/*End downlink queue checkout*/
	   							
	   							
	   						}
	   				
	   				
			
			
		}catch(Exception e){
			logger.error("doDownlinkForMissingPKT",e);
			
		}
		
	}


	public List<DownlinkQueue> getDownlinkQueue() throws Exception {
		return downlinkQueueDao.getDownlinkQueue();
	}


	
	public List<DownlinkQueue> getDownlinkByDevEUIAndAppId(String applicationID, String devEUI) throws Exception {
		return downlinkQueueDao.getDownlinkByDevEUIAndAppId(applicationID,devEUI);
	}
	
}
