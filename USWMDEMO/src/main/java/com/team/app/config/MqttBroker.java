package com.team.app.config;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.app.constant.AppConstants;
import com.team.app.dao.DeviceInfoDao;
import com.team.app.dao.DownlinkQueueDao;
import com.team.app.dao.FrameDao;
import com.team.app.dao.LoraConsoildatedPktDao;
import com.team.app.domain.DownlinkQueue;
import com.team.app.domain.LoraFrame;
import com.team.app.domain.TblDeviceDetail;
import com.team.app.domain.TblLoraConsoildatedPkt;
import com.team.app.logger.AtLogger;
import com.team.app.service.ConsumerInstrumentService;
import com.team.app.service.DownlinkService;

@Service
public class MqttBroker implements MqttCallback,MqttIntrf {
	
	private static final AtLogger logger = AtLogger.getLogger(MqttBroker.class);
	
		
	@Autowired
	private FrameDao frameDao;	
	
	@Autowired
	private DeviceInfoDao deviceInfoDao;
	
	@Autowired
	private DownlinkService downlinkService;
	
	@Autowired
	private DownlinkQueueDao downlinkQueueDao;
	
	@Autowired
	private ConsumerInstrumentService  consumerInstrumentService;
	
	@Autowired
	private LoraConsoildatedPktDao  loraConsoildatedPktDao;
		
	MqttClient client;
	
	MqttMessage message;
	
	 public void doDemo(String appId, String devId)  {
	    try {
	    	logger.debug("/ INside MQTT Broker"+devId);	
	    	MqttConnectOptions connOpts = new MqttConnectOptions();
	        connOpts.setUserName("loragw");
	        connOpts.setPassword("loragw".toCharArray());
	        connOpts.setCleanSession(true);
	        client = new MqttClient("tcp://139.59.14.31:1883", MqttClient.generateClientId());
	        
	        client.connect(connOpts);
	        client.setCallback(this);
	        client.subscribe("application/"+appId+"/node/"+devId+"/rx");
	        MqttMessage message = new MqttMessage();
	        message.setPayload("sending......."
	                .getBytes());
	        client.publish("application/"+appId+"/node/"+devId+"/tx", message);
	        System.out.println("Message printing here "+message);
	        //System.exit(0);
	    } catch (MqttException e) {
	        e.printStackTrace();
	    }
	}    
	
	@Transactional
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		logger.debug("Inside messageArrived");
		try{
			LoraFrame frame=null;
			if(!message.toString().isEmpty()){
				 
				  JSONObject json=null;
				  		json=new JSONObject();
				  		json=(JSONObject)new JSONParser().parse(message.toString());
				  		logger.debug("REsultant json",json);
				  		
				  		 frame=new LoraFrame();				  		
				  		 frame.setApplicationID(json.get("applicationID").toString());
				  		 frame.setApplicationName(json.get("applicationName").toString());
				  		 frame.setNodeName(json.get("nodeName").toString());
				  		 frame.setDevEUI(json.get("devEUI").toString());
				  		logger.debug("applicationID",json.get("applicationID").toString());
							logger.debug("applicationName",json.get("applicationName").toString());
								logger.debug("nodeName",json.get("nodeName").toString());
									logger.debug("devEUI",json.get("devEUI").toString());
				  		 
				  		 JSONArray arr=(JSONArray) json.get("rxInfo");
				  		 
				  		 if(arr!=null && arr.size()>0){
	   						 for (int i = 0; i < arr.size(); i++) {
	   							 JSONObject jsonObj = (JSONObject) arr.get(i);
	   							 frame.setGatewayMac(jsonObj.get("mac").toString());
	   							 frame.setGatewayName(jsonObj.get("name").toString());
	   							 
		   							logger.debug("mac",jsonObj.get("mac").toString());
		   								logger.debug("name",jsonObj.get("name").toString());
	   						 }
				  		 }
				  		 
				  		
				  		logger.debug("fport",json.get("fPort").toString());
				  	  		logger.debug("Data As:",json.get("data").toString());
				  	  		
				  	  	frame.setfPort(json.get("fPort").toString().trim());
				  	  	TimeZone.setDefault(TimeZone.getTimeZone("IST"));
				  	  	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				  	  	formatter.setTimeZone(TimeZone.getTimeZone("IST")); // Or whatever IST is supposed to be
				  	  	formatter.parse(formatter.format(new Date(System.currentTimeMillis())));
				  	  	frame.setCreatedAt(formatter.parse(formatter.format(new Date(System.currentTimeMillis()))));
				  	  	frame.setUpdatedAt(formatter.parse(formatter.format(new Date(System.currentTimeMillis()))));
				  	  	
				  	  	
				  	  	
				  	  	if(json.get("data")!=null){	
				  	  		 logger.debug("Data not empty");
				     		 byte[] decoded=Base64.decodeBase64(json.get("data").toString());
				     		 
				     		 	if(decoded!=null && decoded.length>0){
				     		 		LoraFrame previousFrm=frameDao.getLoraFrameByDevEUIAndAppID(frame.getDevEUI(),frame.getApplicationID());
				     		 		LoraFrame f=null;
				     		 		int i=0;				     		 	
				     		 		String packetType="";
				     		 		int temp=0;
				     		 		String devMapId="";
				     		 		String waterLtr="";	
				     		 		String battrey_info="";
				     		 		int hourly=-1;
				     		 		int n=0;
				     		 		int consoildatedUnits=0;
				     		 		int mpdu=0;
				     		 		int packetLength=0;
				     		 		long millseconds=System.currentTimeMillis();
				     		 		LoraFrame frm=null;
				     		 			frm=new LoraFrame();
				     		 			for(Byte b : decoded){
				     		 				if(n>0){
				     		 					frm=new LoraFrame();
				     		 					n=0;	
			     		 		   				temp=0;			     		 		   				
			     		 		   				waterLtr="";
			     		 		   				consoildatedUnits=0;
			     		 		   				
			     		 		   			}
				     		 			  if(i==0){
					     		 			String decodeBinary =String.format("%x", b);
					     		 			 logger.debug("decodeBinary i=0 :",decodeBinary);
						     		 			//if(!decodeBinary.equalsIgnoreCase("11111111111111111111111111111111")){
						     		 				if(decodeBinary.equals("0")){
						     		 					packetType="0";
						     		 					devMapId="0";
						     		 				}else{
						     		 					if(decodeBinary.length()>1){
						     		 						//packetType=String.format("%02X ",decodeBinary.substring(0,decodeBinary.length()-4));
						     		 						packetType=String.valueOf(Integer.parseInt(decodeBinary.substring(0,decodeBinary.length()-1),16));
						     		 						devMapId=decodeBinary.substring(1);
						     		 					}else{
						     		 						packetType="0";
						     		 						devMapId=decodeBinary;
						     		 					}
						     		 					
						     		 				}						     		 				
						     		 			 	logger.debug("packetType :",packetType);
						     		 			 	logger.debug("devMapId :",devMapId);
						     		 			//} 	
					     		 			   i++;					     		 			 
						     		 		  }else if(i==1){
						     		 			String decodeBinary =String.format("%x", b);
						     		 			 logger.debug("decodeBinary i=1 :",decodeBinary);
							     		 			//if(!decodeBinary.equalsIgnoreCase("11111111111111111111111111111111")){
						     		 			 		if(packetType.equals("1")){
						     		 			 			logger.debug("Packet Type 1 in i=1");
						     		 			 			int devMapCombination=0;
						     		 			 			if(decodeBinary.length()>1){
						     		 			 				devMapCombination=Integer.parseInt(devMapId+decodeBinary,16);
						     		 			 			}else{
						     		 			 				devMapCombination=Integer.parseInt(devMapId+"0"+decodeBinary,16);
						     		 			 			}
						     		 			 			devMapId=String.valueOf(devMapCombination);
						     		 			 		}else if(packetType.equals("2")){
						     		 			 		 logger.debug("Packet Type 2 as in i=1",packetType);
							     		 			 		 
							     		 			 		int devMapCombination=0;
						     		 			 			if(decodeBinary.length()>1){
						     		 			 				devMapCombination=Integer.parseInt(devMapId+decodeBinary,16);
						     		 			 			}else{
						     		 			 				devMapCombination=Integer.parseInt(devMapId+"0"+decodeBinary,16);
						     		 			 			}
						     		 			 			devMapId=String.valueOf(devMapCombination);
						     		 			 			
						     		 			 			LoraFrame retryFrm=frameDao.getLoraFrameByDevEUIAndDeviceId(frame.getApplicationID(),frame.getDevEUI(),devMapId);
						     		 			 			if(retryFrm!=null){	
						     		 			 				//Date curdate=OtherFunctions.getCurrentDateFrmt();
						     		 			 				//logger.debug("cur date",curdate);
						     		 			 				//Date existDate=OtherFunctions.getDateWithoutTimestamp(retryFrm.getUpdatedAt());
						     		 			 				//logger.debug("exist Date",existDate);
						     		 			 				
						     		 			 				//long datediff=OtherFunctions.getDifferenceDays(curdate,existDate);
						     		 			 				//logger.debug("Date datediff",datediff);
						     		 			 				//if(datediff<30){
						     		 			 				//	logger.debug("Inside datediff");
						     		 			 				//	break;
						     		 			 				//}    
						     		 			 				
						     		 			 				break;
						     		 			 				
						     		 			 			}
						     		 			 		}else if(packetType.equals("3")){
						     		 			 			logger.debug("Packet Type as 3" ,packetType);
						     		 			 			break;
						     		 			 		}else if(packetType.equals("4")){
						     		 			 				logger.debug("Packet Type as 4" ,packetType);
						     		 			 				TblDeviceDetail existBattreyInfo=deviceInfoDao.getDeviceInfoByDevEUI(frame.getDevEUI());
						     		 			 				if(existBattreyInfo!=null){
						     		 			 					existBattreyInfo.setBattreyPerc(String.valueOf(Integer.parseInt(decodeBinary,16)));
						     		 			 					existBattreyInfo.setUpdatedAt(new Date(System.currentTimeMillis()));
						     		 			 					deviceInfoDao.save(existBattreyInfo);
						     		 			 				}else{
						     		 			 					TblDeviceDetail newBattreyInfo=null;
						     		 			 						newBattreyInfo=new TblDeviceDetail();
						     		 			 						newBattreyInfo.setApplicationID(frame.getApplicationID());
						     		 			 						newBattreyInfo.setApplicationName(frame.getApplicationName());
						     		 			 						newBattreyInfo.setNodeName(frame.getNodeName());
						     		 			 						newBattreyInfo.setDevEUI(frame.getDevEUI());
						     		 			 						newBattreyInfo.setBattreyPerc(String.valueOf(Integer.parseInt(decodeBinary,16)));
						     		 			 						newBattreyInfo.setCreatedAt(new Date(System.currentTimeMillis()));
						     		 			 						newBattreyInfo.setUpdatedAt(new Date(System.currentTimeMillis()));
						     		 			 						deviceInfoDao.save(newBattreyInfo);						     		 			 						
						     		 			 						
						     		 			 						
						     		 			 				}
						     		 			 			
						     		 			 		}else if(packetType.equals("5")){
						     		 			 			logger.debug("Packet Type as 5" ,packetType);
						     		 			 			
						     		 			 			TblDeviceDetail existTempInfo=deviceInfoDao.getDeviceInfoByDevEUI(frame.getDevEUI());
					     		 			 				if(existTempInfo!=null){
					     		 			 					existTempInfo.setTempInfo(String.valueOf(Integer.parseInt(decodeBinary,16)));
					     		 			 					existTempInfo.setUpdatedAt(new Date(System.currentTimeMillis()));
					     		 			 					deviceInfoDao.save(existTempInfo);
					     		 			 				}else{
					     		 			 					TblDeviceDetail newTempInfo=null;
					     		 			 					newTempInfo=new TblDeviceDetail();
					     		 			 					newTempInfo.setApplicationID(frame.getApplicationID());
					     		 			 					newTempInfo.setApplicationName(frame.getApplicationName());
					     		 			 					newTempInfo.setNodeName(frame.getNodeName());
					     		 			 					newTempInfo.setDevEUI(frame.getDevEUI());
					     		 			 					newTempInfo.setTempInfo(String.valueOf(Integer.parseInt(decodeBinary,16)));
					     		 			 					newTempInfo.setCreatedAt(new Date(System.currentTimeMillis()));
					     		 			 					newTempInfo.setUpdatedAt(new Date(System.currentTimeMillis()));
					     		 			 					deviceInfoDao.save(newTempInfo);						     		 			 						
					     		 			 						
					     		 			 						
					     		 			 				}
						     		 			 			
						     		 			 		}else if(packetType.equals("6")){
						     		 			 			logger.debug("Packet Type as 6" ,packetType);						     		 			 			
						     		 			 			break;
						     		 			 		}else if(packetType.equals("7")){
						     		 			 			logger.debug("Packet Type as 7" ,packetType);
						     		 			 			logger.debug("consoildated packet ");
						     		 			 			int devMapCombination=0;
						     		 			 			if(decodeBinary.length()>1){
						     		 			 				devMapCombination=Integer.parseInt(devMapId+decodeBinary,16);
						     		 			 			}else{
						     		 			 				devMapCombination=Integer.parseInt(devMapId+"0"+decodeBinary,16);
						     		 			 			}
						     		 			 			devMapId=String.valueOf(devMapCombination);
						     		 			 		
						     		 			 		}else if(packetType.equals("8")){
						     		 			 			logger.debug("Packet Type as 8" ,packetType);
						     		 			 			battrey_info=String.valueOf(Integer.parseInt(decodeBinary,16));
						     		 			 		}
							     		 			//}
							     		 		i++;	
						     		 		  }else if(i==2){
						     		 			//String decodeBinary = Integer.toBinaryString(b);
						     		 			String decodeBinary =String.format("%x", b);
						     		 			 logger.debug("decodeBinary i=2 :",decodeBinary);
						     		 				if(packetType.equals("1") || packetType.equals("2")){
				     		 			 			   logger.debug("Packet Type 1 in i==2");
				     		 			 			   //Calculating hourly
				     		 			 			    mpdu=Integer.parseInt(decodeBinary.substring(0,decodeBinary.length()-1),16);
					     		 			 			hourly=hourly+Integer.parseInt(decodeBinary.substring(0,decodeBinary.length()-1),16);
						     		 					logger.debug("Total hourly :",hourly);
						     		 					logger.debug("Actual millseconds :",millseconds);
						     		 					millseconds=millseconds-TimeUnit.HOURS.toMillis(hourly);
						     		 					logger.debug("Sub millseconds :",millseconds);
						     		 					logger.debug("Date as :",formatter.parse(formatter.format(new Date(millseconds))));
						     		 				   
						     		 					//Calculating Packet Length						     		 					
						     		 					packetLength=Integer.parseInt(decodeBinary.substring(1),16);
						     		 				}else if(packetType.equals("7")) {
						     		 					logger.debug("printing i==2,consoildated");
						     		 					consoildatedUnits=Integer.parseInt(decodeBinary,16);
						     		 				}else if(packetType.equals("8")){
					     		 			 			logger.debug("Packet Type as " ,packetType);
					     		 			 			TblDeviceDetail existBattreySwInfo=deviceInfoDao.getDeviceInfoByDevEUI(frame.getDevEUI());
				     		 			 				if(existBattreySwInfo!=null){
				     		 			 					existBattreySwInfo.setBattreyPerc(battrey_info);
				     		 			 					existBattreySwInfo.setSwInfo(String.valueOf(Integer.parseInt(decodeBinary,16)));
				     		 			 					existBattreySwInfo.setUpdatedAt(new Date(System.currentTimeMillis()));
				     		 			 					deviceInfoDao.save(existBattreySwInfo);
				     		 			 					
				     		 			 				}else{
				     		 			 					TblDeviceDetail newBattreySWInfo=null;
					     		 			 					newBattreySWInfo=new TblDeviceDetail();
					     		 			 					newBattreySWInfo.setApplicationID(frame.getApplicationID());
					     		 			 					newBattreySWInfo.setApplicationName(frame.getApplicationName());
					     		 			 					newBattreySWInfo.setNodeName(frame.getNodeName());
					     		 			 					newBattreySWInfo.setDevEUI(frame.getDevEUI());
					     		 			 					newBattreySWInfo.setBattreyPerc(battrey_info);
					     		 			 					newBattreySWInfo.setSwInfo(String.valueOf(Integer.parseInt(decodeBinary,16)));
					     		 			 					newBattreySWInfo.setCreatedAt(new Date(System.currentTimeMillis()));
					     		 			 					newBattreySWInfo.setUpdatedAt(new Date(System.currentTimeMillis()));
					     		 			 					deviceInfoDao.save(newBattreySWInfo);						     		 			 						
				     		 			 						
				     		 			 						
				     		 			 				}
				     		 			 				
				     		 			 				downlinkService.doDownlinkForDate(frame.getDevEUI(),frame.getApplicationID(),frame.getfPort());
				     		 			 				
				     		 			 				battrey_info="";
					     		 			 		}		     		 			 			
				     		 			 			
							     		 			
							     		 		i++;	
						     		 		  }else if(i>2){
						     		 			 String decodeBinary =String.format("%x", b);
						     		 			logger.debug("decodeBinary i>2 :",decodeBinary);						     		 			
							     		 			//if(!decodeBinary.equalsIgnoreCase("11111111111111111111111111111111")){
						     		 			
						     		 			
					     		 			 			
							     		 				if(packetType.equals("1") || packetType.equals("2")){
							     		 					//Bussiness logic here	
							     		 					logger.debug("Packet Type 0 in i>2");
							     		 					logger.debug("i Value : ",i);
							     		 					logger.debug("Packet Type ===0: ");
							     		 					logger.debug("Packet Length : ",packetLength);
							     		 					logger.debug("Temp Length : ",temp);
							     		 					logger.debug("waterLtr Length : ",waterLtr);
							     		 												     		 					
							     		 					if(temp<packetLength){
							     		 						if(String.valueOf(decodeBinary).length()==1){
							     		 							waterLtr=waterLtr+"0"+decodeBinary;
							     		 						}else{
							     		 							waterLtr=waterLtr+decodeBinary;
							     		 						}
							     		 						
							     		 						temp++;
							     		 						if(temp!=packetLength){
							     		 							i++;
								     		 						continue;
							     		 						}
							     		 						 
							     		 					}
							     		 					
							     		 					logger.debug("waterLtr Length final ===0",waterLtr);							     		 				
							     		 					logger.debug("resultant: packetType : ",packetType);
							     		 					logger.debug("devMapId: AS : ",devMapId);
							     		 						     		 		 	
									     		 		 	
									     		 		 	frm.setCreatedAt(formatter.parse(formatter.format(new Date(millseconds))));
									     		 		 	frm.setUpdatedAt(formatter.parse(formatter.format(new Date(millseconds))));						     		 					
									     		 		 	frm.setDevMapId(devMapId);
									     		 		 	//frm.setWaterltr(String.format("%02X ", Integer.toBinaryString(Integer.parseInt(waterLtr))));
									     		 		 	frm.setWaterltr(String.valueOf(Integer.parseInt(waterLtr,16)*10));
									     		 		 	frm.setApplicationID(frame.getApplicationID());
									     		 		 	frm.setApplicationName(frame.getApplicationName());
									     		 		 	frm.setNodeName(frame.getNodeName());
									     		 		 	frm.setDevEUI(frame.getDevEUI());
									     		 		 	frm.setGatewayMac(frame.getGatewayMac());
									     		 		 	frm.setGatewayName(frame.getGatewayName());
									     		 		 	frm.setMpdu(String.valueOf(mpdu));
									     		 		 	frm.setPdu(String.valueOf(packetLength));
									     		 		 	frm.setfPort(frame.getfPort());									     		 		 	
									     		 		 	
									     		 		    f=frameDao.save(frm);
							     		 					
									     		 		    millseconds=millseconds+TimeUnit.HOURS.toMillis(1);									     		 		    
							     		 					n++;							     		 					
									     		 		    
							     		 				}else if(packetType.equals("7")) {
							     		 					logger.debug("printing i>2,consoildated");
							     		 					consoildatedUnits=consoildatedUnits+Integer.parseInt(decodeBinary,16);
							     		 					if(i<5) {
							     		 						i++;
							     		 						continue;
							     		 					}
							     		 					
							     		 					TblLoraConsoildatedPkt pkt=null;
							     		 						pkt=new TblLoraConsoildatedPkt();
							     		 						
							     		 						pkt.setCreatedDt(formatter.parse(formatter.format(new Date(millseconds))));
							     		 						pkt.setUpdatedDt(formatter.parse(formatter.format(new Date(millseconds))));						     		 					
							     		 						pkt.setWaterltr(String.valueOf(consoildatedUnits));
							     		 						pkt.setApplicationId(frame.getApplicationID());									     		 		 
							     		 						pkt.setNodeName(frame.getNodeName());
							     		 						pkt.setDevEUI(frame.getDevEUI());
							     		 						pkt.setStatus(AppConstants.IND_A);
							     		 						
							     		 						TblLoraConsoildatedPkt consPkt=loraConsoildatedPktDao.save(pkt);
							     		 						if(consPkt!=null) {
							     		 							Long l=frameDao.getWaterConsumptionsUnitForEndUser(consPkt.getApplicationId(),consPkt.getDevEUI());
							     		 							if(l!=null) {
							     		 									logger.debug("IN Long ,",l);
							     		 									logger.debug("IN Long1 ,",Long.parseLong(consPkt.getWaterltr()));
							     		 								if(l!=Long.parseLong(consPkt.getWaterltr()) && Long.parseLong(consPkt.getWaterltr())>=l) {
							     		 									logger.debug("IN if condition");
							     		 									LoraFrame fm=frameDao.getLoraFrameByDevEUIAndAppID(consPkt.getDevEUI(),consPkt.getApplicationId());
							     		 									if(fm!=null) {
							     		 										logger.debug("IN if condition fm");
							     		 										fm.setWaterltr(String.valueOf(Long.parseLong(consPkt.getWaterltr())*10-l));
							     		 										fm.setUpdatedAt(formatter.parse(formatter.format(new Date(millseconds))));
							     		 										frameDao.save(fm);
							     		 										
							     		 										consPkt.setStatus(AppConstants.IND_D);
							     		 										loraConsoildatedPktDao.save(consPkt);
							     		 									}
							     		 								}
							     		 							}
							     		 						}
							     		 						
									     		 		 		
									     		 		    n++;
							     		 				}
						     		 				//}
							     		 		i++;							     		 			  
						     		 		  }
				     		 			
				     		 		}
				     		 		
				     		 		if(previousFrm!=null){	
				     		 			logger.debug("In previousFrm");
				     		 			if(f!=null){
				     		 				logger.debug("In f");	
				     		 								     		 				
				     		 					int latest=Integer.parseInt(f.getDevMapId());
			     		 						int previous=Integer.parseInt(previousFrm.getDevMapId());
			     		 						
			     		 						logger.debug("the latest ",latest);
			     		 						logger.debug("the previous ",previous);
			     		 						
			     		 						if(f.getMpdu().equals(previousFrm.getMpdu())){
			     		 							logger.debug("latest and previous mpdu equals ");
			     		 							
					     		 					if(f.getMpdu().equals("12")){	
					     		 						logger.debug("In MPDU ==12");
					     		 						if((latest-previous)>=24){
					     		 						//Do Downlink for missing packet by mpdu 12
					     		 							int b=latest-12;					     		 							    		 								
					     		 							for(int k=0;previous<b;k++){
						     		 							DownlinkQueue q=downlinkQueueDao.getDownlinkQueueByAppIdDevEUIAndDeviceId(f.getApplicationID(),f.getDevEUI(),String.valueOf(b));
										     		 		 	if(q==null){
										     		 		 	   //Downlink Required
										     		 		 		downlinkService.doDownlinkForMissingPkt(f.getDevEUI(),f.getApplicationID(),f.getfPort(),String.valueOf(b),f.getMpdu(),f.getPdu());
										     		 		 	}
										     		 		 	b=b-1;										     		 		 	
					     		 							}
					     		 							
					     		 						}
					     		 						
					     		 					}else if(f.getMpdu().equals("6")){
					     		 						logger.debug("In MPDU ==6");
					     		 						if((latest-previous)>=12){
					     		 							//Do Downlink for missing packet by mpdu 6
					     		 							int b=latest-6;					     		 							    		 								
					     		 							for(int k=0;previous<b;k++){
						     		 							DownlinkQueue q=downlinkQueueDao.getDownlinkQueueByAppIdDevEUIAndDeviceId(f.getApplicationID(),f.getDevEUI(),String.valueOf(b));
										     		 		 	if(q==null){
										     		 		 	   //Downlink Required
										     		 		 		downlinkService.doDownlinkForMissingPkt(f.getDevEUI(),f.getApplicationID(),f.getfPort(),String.valueOf(b),f.getMpdu(),f.getPdu());
										     		 		 	}
										     		 		 	b=b-1;										     		 		 	
					     		 							}
					     		 						}
					     		 						
					     		 					}else if(f.getMpdu().equals("1")){	
					     		 						logger.debug("In MPDU ==1");
					     		 						if((latest-previous)>=2){
					     		 							//Do Downlink for missing packet by mpdu 1
					     		 							int b=latest-1;					     		 							    		 								
					     		 							for(int k=0;previous<b;k++){
						     		 							DownlinkQueue q=downlinkQueueDao.getDownlinkQueueByAppIdDevEUIAndDeviceId(f.getApplicationID(),f.getDevEUI(),String.valueOf(b));
										     		 		 	if(q==null){
										     		 		 	   //Downlink Required
										     		 		 		downlinkService.doDownlinkForMissingPkt(f.getDevEUI(),f.getApplicationID(),f.getfPort(),String.valueOf(b),f.getMpdu(),f.getPdu());
										     		 		 	}
										     		 		 	b=b-1;										     		 		 	
					     		 							}
					     		 						}
					     		 					}					     		 					
			     		 						}else{
			     		 							logger.debug("latest and previous mpdu not equals ");
			     		 						}
				     		 					
				     		 					
				     		 					
				     		 				}
				     		 				
				     		 			
				     		 		} 	
				     		 		 	
				     		 		
				     		 	}
					
				  	  	}				  		
				  		
		}
		
		}catch(Exception e){
			logger.error("Error",e);
			e.printStackTrace();
		}
	}
	
	
	/*@Transactional
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		logger.debug("Inside messageArrived");
		try{
			LoraFrame frame=null;
			if(!message.toString().isEmpty()){
				 
				  JSONObject json=null;
				  		json=new JSONObject();
				  		json=(JSONObject)new JSONParser().parse(message.toString());
				  		logger.debug("REsultant json",json);
				  		
				  		 frame=new LoraFrame();
				  		
				  		 frame.setApplicationID(json.get("applicationID").toString());
				  		 frame.setApplicationName(json.get("applicationName").toString());
				  		 frame.setNodeName(json.get("nodeName").toString());
				  		 frame.setDevEUI(json.get("devEUI").toString());
				  		logger.debug("applicationID",json.get("applicationID").toString());
							logger.debug("applicationName",json.get("applicationName").toString());
								logger.debug("nodeName",json.get("nodeName").toString());
									logger.debug("devEUI",json.get("devEUI").toString());
				  		 
				  		 JSONArray arr=(JSONArray) json.get("rxInfo");
				  		 
				  		 if(arr!=null && arr.size()>0){
	   						 for (int i = 0; i < arr.size(); i++) {
	   							 JSONObject jsonObj = (JSONObject) arr.get(i);
	   							 frame.setGatewayMac(jsonObj.get("mac").toString());
	   							 frame.setGatewayName(jsonObj.get("name").toString());
	   							 
		   							logger.debug("mac",jsonObj.get("mac").toString());
		   								logger.debug("name",jsonObj.get("name").toString());
	   						 }
				  		 }
				  		 
				  		
				  		logger.debug("fport",json.get("fPort").toString());
				  	  		logger.debug("Data As:",json.get("data").toString());
				  	  		
				  	  	frame.setfPort(json.get("fPort").toString().trim());
				  	  	TimeZone.setDefault(TimeZone.getTimeZone("IST"));
				  	  	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				  	  	formatter.setTimeZone(TimeZone.getTimeZone("IST")); // Or whatever IST is supposed to be
				  	  	formatter.parse(formatter.format(new Date(System.currentTimeMillis())));
				  	  	frame.setCreatedAt(formatter.parse(formatter.format(new Date(System.currentTimeMillis()))));
				  	  	frame.setUpdatedAt(formatter.parse(formatter.format(new Date(System.currentTimeMillis()))));
				  	  	
				  	  	
				  	  	
				  	  	if(json.get("data")!=null){	
				  	  		 logger.debug("Data not empty");
				     		 byte[] decoded=Base64.decodeBase64(json.get("data").toString());
				     		 
				     		 	if(decoded!=null && decoded.length>0){
				     		 		
				     		 		 String devIdBinary = Integer.toBinaryString(decoded[0]);
				     		 		 String waterValBinary = Integer.toBinaryString(decoded[1]);
				     		 		 	logger.debug("devIdBinary[0] AS : ",devIdBinary);
				     		 		 		logger.debug("waterValBinary[1] AS : ",waterValBinary);
				     		 		 				     		 		  	
				     		 		 			     		 		  	
				     		 		  int dId=Integer.parseInt(devIdBinary,2);
				     		 		  int waterVal=Integer.parseInt(waterValBinary,2);
				     		 		  	logger.debug("dId : ",dId);
				     		 		  		logger.debug("waterVal : ",waterVal);
				     		 			
				     		 		  				frame.setWaterltr(String.valueOf(waterVal));
				     		 		   				frameDao.save(frame);
				     		 		
				     		 	}
					
				  	  	}				  		
				  		
		}
		
		}catch(Exception e){
			logger.error("Error",e);
			e.printStackTrace();
		}
	}*/
	


	public void deliveryComplete(IMqttDeliveryToken token) {
	}
	
	public void doDemo() {
 	}


	public void connectionLost(Throwable cause) {
   	}

}
