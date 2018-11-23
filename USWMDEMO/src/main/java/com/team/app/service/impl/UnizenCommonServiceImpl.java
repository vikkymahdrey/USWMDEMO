package com.team.app.service.impl;

import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.team.app.constant.AppConstants;
import com.team.app.dao.UnizenKeyConfigDAO;
import com.team.app.domain.TblUnizenKeyConfig;
import com.team.app.dto.UserLoginDTO;
import com.team.app.exception.AtAppException;
import com.team.app.logger.AtLogger;
import com.team.app.service.UnizenCommonService;
import com.team.app.utils.JWTKeyGenerator;

@Service
public class UnizenCommonServiceImpl implements UnizenCommonService {
	
	private final AtLogger logger = AtLogger.getLogger(UnizenCommonServiceImpl.class);

	@Autowired
	private UnizenKeyConfigDAO unizenKeyConfigDAO;

	@Override
	public TblUnizenKeyConfig getKeyConfigByKey(String keyName) {
		return unizenKeyConfigDAO.getKeyConfigValue(keyName);
	}

	@Override
	public void validateXToken(String servicInvoker, String jwtToken) throws AtAppException {
		TblUnizenKeyConfig unizenConfig = getKeyConfigByKey(servicInvoker);
		
		if(unizenConfig == null || unizenConfig.getUnizenKeyValue() == null) {
			throw new AtAppException("Invalid Service Invoker Value", HttpStatus.UNAUTHORIZED);
		}
		
		if(unizenConfig.getStatus().equalsIgnoreCase(AppConstants.IND_D)) {
			throw new AtAppException("Service Invoker Config is invalid", HttpStatus.NOT_IMPLEMENTED);
		}
		
		JWTKeyGenerator.validateJWTToken(unizenConfig.getUnizenKeyValue(), jwtToken);
		
	}

	@Transactional
	public UserLoginDTO getRefreshTokenOnBaseToken() throws AtAppException {
		TblUnizenKeyConfig unizenKeyConfig = unizenKeyConfigDAO.getKeyConfigValue(AppConstants.KEY_UNIZEN_MOBILE);
		UserLoginDTO userLoginDTO=null;
		if(null != unizenKeyConfig && (unizenKeyConfig.getIsEnabled() != null && 
				unizenKeyConfig.getIsEnabled().equalsIgnoreCase(AppConstants.IND_Y))) {
			
			//long ttlMillis = Long.parseLong(SpringPropertiesUtil.getProperty(MightyAppConstants.TTL_LOGIN_KEY));
			
			/*long ttlMillis=TimeUnit.HOURS.toMillis(2);
			long ttlBaseMillis=TimeUnit.DAYS.toMillis(60);*/
			long ttlMillis=TimeUnit.DAYS.toMillis(1);
			long ttlBaseMillis=TimeUnit.DAYS.toMillis(1);
			
			//long ttlMillis=TimeUnit.MINUTES.toMillis(1);
			//long ttlBaseMillis=TimeUnit.MINUTES.toMillis(5);
			
			logger.debug("ttlMillisVal",ttlMillis);
			logger.debug("ttlBaseMillisVal",ttlBaseMillis);
			
			UserLoginDTO accessToken = JWTKeyGenerator.createJWTAccessToken(unizenKeyConfig.getUnizenKeyValue(), AppConstants.TOKEN_LOGN_ID,
					AppConstants.SUBJECT_SECURE, ttlMillis);
			
			UserLoginDTO baseToken = JWTKeyGenerator.createJWTBaseToken(unizenKeyConfig.getUnizenKeyValue(), AppConstants.TOKEN_LOGN_ID,
					AppConstants.SUBJECT_SECURE, ttlBaseMillis);
			
				userLoginDTO=new UserLoginDTO();	
			userLoginDTO.setApiToken(accessToken.getApiToken());
			userLoginDTO.setAccessTokenExpDate(accessToken.getAccessTokenExpDate());
			
			userLoginDTO.setBaseToken(baseToken.getBaseToken());
			userLoginDTO.setBaseTokenExpDate(baseToken.getBaseTokenExpDate());
			
		}
		if(userLoginDTO==null){
			throw new AtAppException("Token authentication not activated", HttpStatus.LOCKED);
		}
		
		return userLoginDTO;
	}

}
