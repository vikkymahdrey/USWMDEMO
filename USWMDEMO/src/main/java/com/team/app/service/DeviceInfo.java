package com.team.app.service;

import java.util.List;

import com.team.app.domain.TblDeviceFeedback;
import com.team.app.domain.TblDeviceFeedbackType;
import com.team.app.exception.AtAppException;

public interface DeviceInfo {

	List<TblDeviceFeedbackType> getDeviceFeedbackType() throws AtAppException;

	TblDeviceFeedback setDeviceFeedback(TblDeviceFeedback f)throws AtAppException;

}
