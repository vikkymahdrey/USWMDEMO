package com.team.app.service;

import java.util.Date;
import java.util.List;

import com.team.app.domain.LoraFrame;
import com.team.app.domain.TblLoraConsoildatedPkt;
import com.team.app.domain.TblUserInfo;
import com.team.app.exception.AtAppException;

/**
 * 
 * @author Vikky
 *
 */
public interface ConsumerInstrumentService {
		
	public void setUpdateNodeName(String nodeName, String devEUI)throws Exception;	
	public void deleteDevEUI(String appId, String devEUI)throws Exception;
	public List<LoraFrame> getDevEUIByAppId(String appId)throws Exception;
	public List<LoraFrame> getDeviceIdByDevEUI(String devEUI)throws Exception;
	public List<LoraFrame> getFrames()throws Exception;
	public String getUserAccountMessage(TblUserInfo u)throws Exception;
	public List<LoraFrame> getFramesByFrmToDateAndDevEUI(String devEUI, Date d1, Date d2);
	public Object[] getLoraFrameByDevEUIandAppIdandDates(Date sDt, Date eDt, String appId, String devEUI,String interval) throws AtAppException;
	public Object[] getLoraFrameByUserIdAndDates(Date sDt, Date eDt, String id, String valueOf)throws AtAppException;
	public Object[] getLoraFrameByDateAndDevEUI(Date rDt,String appId, String devEUI)throws AtAppException;
	public Object[] getFramesByFrmToDateAndDevEUIAndAppId(String appId, String devEUI, Date d1, Date d2, String type);
	public LoraFrame getLoraFrameByDevEUIAndAppID(String devId, String appId)throws Exception;
	public Long getWaterConsumptionUnitsByCurMonth()throws Exception;
	public Long getTotalWaterUnitsFrmToDate(String appId, String devNode, Date fromDate, Date toDate)throws Exception;
	public Object[] getUserDashboardGraphsOnLoad(String appId, String devEUI, Date currDate)throws Exception;
	public Object[] getUserDashboardGraphOnSubmit(String devId, Date fromDate, Date toDate, String type)throws Exception;
	public Long getWaterConsumptionsUnitForEndUser(String appId, String devEUI)throws Exception;
	public List<TblLoraConsoildatedPkt> getConsoildatedFrames()throws Exception;
	public Long getWaterConsumptionsUnitFromDates(String devNode, Date fromDate, Date toDate)throws Exception;
	public Long getWaterConsumptionsForCurrDate(String appId, String devEUI, Date currDate)throws Exception;
	public Long getFrameByDevEUIandAppIdandDates(Date sDt, Date eDt, String appId, String devEUI)throws Exception;
	public Object[] getTotalMontlyIntervalWaterConsumptions(Date sDt, Date eDt, String appId, String devEUI,
			String interval)throws Exception;
	
	
	
}
