package com.team.app.service;

import com.team.app.domain.TblUnizenKeyConfig;
import com.team.app.dto.UserLoginDTO;
import com.team.app.exception.AtAppException;

public interface UnizenCommonService {

	public TblUnizenKeyConfig getKeyConfigByKey(String keyName);
	
	public void validateXToken(String servicInvoker, String jwtToken) throws AtAppException;

	public UserLoginDTO getRefreshTokenOnBaseToken()throws AtAppException;
}
