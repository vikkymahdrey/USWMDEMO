package com.team.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.app.constant.AppConstants;
import com.team.app.dao.FrameDao;
import com.team.app.dao.LoraConsoildatedPktDao;
import com.team.app.dao.UserInfoDao;
import com.team.app.domain.LoraFrame;
import com.team.app.domain.TblLoraConsoildatedPkt;
import com.team.app.domain.TblUserInfo;
import com.team.app.exception.AtAppException;
import com.team.app.logger.AtLogger;
import com.team.app.service.ConsumerInstrumentService;

/**
 * 
 * @author Vikky
 *
 */
@Service("consumerInstrumentServiceImpl")
public class ConsumerInstrumentServiceImpl implements ConsumerInstrumentService {

	private final AtLogger logger = AtLogger.getLogger(ConsumerInstrumentServiceImpl.class);
	
		
	@Autowired
	private FrameDao frameDao;	
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private  LoraConsoildatedPktDao loraConsoildatedPktDao; 
		
	public void setUpdateNodeName(String nodeName,String devEUI) throws Exception {
		frameDao.setUpdateNodeName(nodeName,devEUI);
	}

	public void deleteDevEUI(String appId, String devEUI) throws Exception {
		frameDao.deleteDevEUI(appId,devEUI);
		
	}
	
	public List<LoraFrame> getDevEUIByAppId(String appId) throws Exception {
		return frameDao.getDevEUIByAppId(appId);
	}

	public List<LoraFrame> getDeviceIdByDevEUI(String devEUI) throws Exception {
		return frameDao.getDeviceIdByDevEUI(devEUI);
	}

	public List<LoraFrame> getFrames() throws Exception {
		return frameDao.getFrames();
	}

	
	public String getUserAccountMessage(TblUserInfo u) throws Exception {
		return "<img src=\""+AppConstants.appURL+"/images/unizen.png\" />"
				+"<br/><br/>Heyo "
				+u.getUname().toLowerCase()	
				+",<br/><br/>I heard that you just created a Unizen SWM account. You're just a few steps away from taking your favorite portal on-the-go. Super exciting."
				+"<br/><br/>You'll remain logged into your USWM account unless you login with a different account. If you happen to get logged out and can't remember your password, click the Forgot Password link on the USWM login screen to reset it."
				+"<br/><br/>Please use the below credential to access your account."
				+"<br/><br/>LoginID : "+u.getUname()
				+"<br/>Password : "+u.getPassword()
				+"<br/>URL : <a href='"+AppConstants.appURL+"'>"+AppConstants.appURL+"</a>"
				+"<br/><br/>If you have any questions about anything related to USWM, please email the USWM team at vikky@unizentechnologies.com"
				+"<br/><br/>Much love,"
				+"<br/>The USWM Robot"
				+"<br/><br/><em>I'm a robot and my owners won't let me receive inbound messages. If you have any questions, please send my owners an email at vikky@unizentechnologies.com</em>"; 
				
		
	}

	
	
	public List<LoraFrame> getFramesByFrmToDateAndDevEUI(String devEUI, Date fromDate, Date toDate) {
		return frameDao.getFramesByFrmToDateAndDevEUI(devEUI,fromDate,toDate);
	}

	
	public Object[] getLoraFrameByDevEUIandAppIdandDates(Date sDt, Date eDt, String appId, String devEUI,String intrvl)
			throws AtAppException {
		return frameDao.getLoraFrameByDevEUIandAppIdandDates(sDt,eDt,appId,devEUI,intrvl);
	}

	
	public Object[] getLoraFrameByUserIdAndDates(Date sDt, Date eDt, String userId, String intrvl) throws AtAppException {
		return frameDao.getLoraFrameByUserIdAndDates(sDt,eDt,userId,intrvl);
	}

	public Object[] getLoraFrameByDateAndDevEUI(Date rDt,String appId, String devEUI) throws AtAppException {
		return frameDao.getLoraFrameByDateAndDevEUI(rDt,appId,devEUI);
	}

	
	public Object[] getFramesByFrmToDateAndDevEUIAndAppId(String appId, String devEUI, Date fromDate, Date toDate, String type) {
		return frameDao.getFramesByFrmToDateAndDevEUIAndAppId(appId,devEUI,fromDate,toDate,type);
	}

	
	public LoraFrame getLoraFrameByDevEUIAndAppID(String devId, String appId) throws Exception {
		
		return frameDao.getLoraFrameByDevEUIAndAppID(devId,appId);
	}

	
	public Long getWaterConsumptionUnitsByCurMonth() throws Exception {
		return frameDao.getWaterConsumptionUnitsByCurMonth();
	}


	public Long getTotalWaterUnitsFrmToDate(String appId, String devNode, Date fromDate, Date toDate) throws Exception {
		return frameDao.getTotalWaterUnitsFrmToDate(appId,devNode,fromDate,toDate);
	}

	
	public Object[] getUserDashboardGraphsOnLoad(String appId, String devEUI, Date currDate) throws Exception {
		return frameDao.getUserDashboardGraphsOnLoad(appId,devEUI,currDate);
	}


	public Object[] getUserDashboardGraphOnSubmit(String devId, Date fromDate, Date toDate,String type) throws Exception {
		
		return frameDao.getUserDashboardGraphOnSubmit(devId,fromDate,toDate,type);
	}

	
	public Long getWaterConsumptionsUnitForEndUser(String appId, String devEUI) throws Exception {
		// TODO Auto-generated method stub
		return frameDao.getWaterConsumptionsUnitForEndUser(appId,devEUI);
	}

	
	public List<TblLoraConsoildatedPkt> getConsoildatedFrames() throws Exception {
		return loraConsoildatedPktDao.getConsoildatedFrames();
	}

	
	public Long getWaterConsumptionsUnitFromDates(String devNode, Date fromDate, Date toDate) throws Exception {
		return frameDao.getWaterConsumptionsUnitFromDates(devNode, fromDate, toDate);
	}

	
	public Long getWaterConsumptionsForCurrDate(String appId, String devEUI, Date currDate) throws Exception {
		return frameDao.getWaterConsumptionsForCurrDate(appId,devEUI,currDate);
	}


	public Long getFrameByDevEUIandAppIdandDates(Date sDt, Date eDt, String appId, String devEUI) throws Exception {
		return frameDao.getFrameByDevEUIandAppIdandDates(sDt, eDt, appId, devEUI);
	}

	
	public Object[] getTotalMontlyIntervalWaterConsumptions(Date sDt, Date eDt, String appId, String devEUI,
			String interval) throws Exception {
		
		return frameDao.getTotalMontlyIntervalWaterConsumptions(sDt, eDt, appId, devEUI,interval);
	}

	
	

	
	
	




	

	
}