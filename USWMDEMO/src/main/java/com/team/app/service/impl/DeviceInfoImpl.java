package com.team.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.app.dao.DeviceFeedbackDao;
import com.team.app.dao.DeviceFeedbackTypeDao;
import com.team.app.domain.TblDeviceFeedback;
import com.team.app.domain.TblDeviceFeedbackType;
import com.team.app.exception.AtAppException;
import com.team.app.logger.AtLogger;
import com.team.app.service.DeviceInfo;

@Service
public class DeviceInfoImpl implements DeviceInfo {
	
	private static final AtLogger logger = AtLogger.getLogger(DeviceInfoImpl.class);
	
	@Autowired
	private DeviceFeedbackTypeDao deviceFeedbackTypeDao;	
	
	@Autowired
	private DeviceFeedbackDao deviceFeedbackDao;
	

	
	public List<TblDeviceFeedbackType> getDeviceFeedbackType() throws AtAppException {
		return deviceFeedbackTypeDao.getDeviceFeedbackType();
	}


	
	public TblDeviceFeedback setDeviceFeedback(TblDeviceFeedback f) throws AtAppException {
		return deviceFeedbackDao.save(f);
	}

}
