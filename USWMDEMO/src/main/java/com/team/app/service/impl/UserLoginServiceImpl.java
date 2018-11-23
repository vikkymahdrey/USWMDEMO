package com.team.app.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.team.app.constant.AppConstants;
import com.team.app.dao.RoleDao;
import com.team.app.dao.UnizenKeyConfigDAO;
import com.team.app.dao.UserDeviceMappingDao;
import com.team.app.dao.UserInfoDao;
import com.team.app.domain.Role;
import com.team.app.domain.TblUnizenKeyConfig;
import com.team.app.domain.TblUserInfo;
import com.team.app.domain.UserDeviceMapping;
import com.team.app.dto.UserLoginDTO;
import com.team.app.exception.AtAppException;
import com.team.app.logger.AtLogger;
import com.team.app.service.ConsumerInstrumentService;
import com.team.app.service.UserLoginService;
import com.team.app.utils.JWTKeyGenerator;
import com.team.mighty.notification.SendMail;
import com.team.mighty.notification.SendMailFactory;

@Service
public class UserLoginServiceImpl implements UserLoginService {
	
	private static final AtLogger logger = AtLogger.getLogger(UserLoginServiceImpl.class);

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserDeviceMappingDao userDeviceMappingDao;
	
	@Autowired
	private ConsumerInstrumentService consumerInstrumentServiceImpl;
	
	@Autowired
	private UnizenKeyConfigDAO unizenKeyConfigDAO;
	
	
	public TblUserInfo getUserByUserAndPwd(String username, String password) throws Exception {
		return userInfoDao.getUserByUserAndPwd(username,password);
	}


	
	public List<TblUserInfo> getUserInfosCount() throws Exception {
		return userInfoDao.getUserInfosCount();
	}



	public List<TblUserInfo> getUserInfos()throws Exception {
		return userInfoDao.getUserInfos();
	}



	public TblUserInfo getUserByUnameAndEmail(String uname, String email) throws Exception {
		return userInfoDao.getUserByUnameAndEmail(uname,email);
	}



	public TblUserInfo getUserByEmailId(String email) throws Exception {
		return userInfoDao.getUserByEmailId(email);
	}



	public Role getRoleByRoleId(String roleId) throws Exception {
		return roleDao.getRoleByRoleId(roleId);
	}


	@Transactional
	public String saveUser(TblUserInfo user, UserDeviceMapping udm) throws Exception  {
		//validateDeviceEUI(udm.getDevEUI(),udm.getOrgId());
		UserDeviceMapping dEUI=userDeviceMappingDao.getDeviceByEUIAndOrgId(udm.getDevEUI(),udm.getOrgId());
		if(dEUI!=null){
			return "exist";
		}
		
		TblUserInfo u=userInfoDao.save(user);
		if(u!=null){						
			udm.setTblUserInfo(u);
			UserDeviceMapping udmSave=userDeviceMappingDao.save(udm);
			if(udmSave!=null){
				try{
					logger.debug("in try block");
					String subject = "Your brand new USWM account";
					String message = consumerInstrumentServiceImpl.getUserAccountMessage(u);
							
					SendMail mail =SendMailFactory.getMailInstance();
					mail.send(u.getEmailId().trim(), subject, message);
					
				
				}catch(Exception e){
					logger.error("Exception in mail ",e);
					
				}
				
				return "success";
			}else{
				return "failed";
			}
		}else{
			return "failed";
		}
		
		
	}



	



	void validateDeviceEUI(String devEUI,String orgId) throws Exception{
		UserDeviceMapping dEUI=userDeviceMappingDao.getDeviceByEUIAndOrgId(devEUI,orgId);
		if(dEUI!=null){
			throw new Exception("Device already exist");
		}
		
	}



	public UserDeviceMapping saveUDM(UserDeviceMapping udm) throws Exception {
		return userDeviceMappingDao.save(udm);
	}



	
	public void updateUserInfo(TblUserInfo user) throws Exception {
		userInfoDao.save(user);
		
	}


	public TblUserInfo getUserByUId(String uId) throws Exception {
		
		return userInfoDao.getUserByUId(uId);
	}



	public List<TblUserInfo> getUserListByEmail(String email) throws Exception {
		return userInfoDao.getUserListByEmail(email);
	}



	
	public UserDeviceMapping saveNewUDMToUser(UserDeviceMapping udm) throws Exception {
		validateDeviceEUI(udm.getDevEUI(),udm.getOrgId());	
		return userDeviceMappingDao.save(udm);
	}



	
	public List<Role> getRoles() throws Exception {
		return roleDao.getRoles();
	}



	public List<UserDeviceMapping> getUserDeviceByOrgId(String orgId) throws Exception {
		return userDeviceMappingDao.getUserDeviceByOrgId(orgId);
	}



	public List<TblUserInfo> getUserByRoleId(String usertype) throws Exception {
		return userInfoDao.getUserByRoleId(usertype);
	}

	@Transactional
	public UserLoginDTO userLogin(String username, String password, String usertype) throws AtAppException {
		TblUserInfo userInfo= userInfoDao.userLogin(username,password,usertype);
	
		if(userInfo==null){
			throw new AtAppException("UserDetails not found", HttpStatus.NOT_FOUND);
		}
		
		if(userInfo.getPwdChangeDt()==null || userInfo.getPwdChangeDt().equals("") ){
			throw new AtAppException("Account not activated", HttpStatus.NOT_ACCEPTABLE);
		}		

		
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
			userLoginDTO.setUserId(userInfo.getId());
		}
		if(userLoginDTO==null){
			throw new AtAppException("Token authentication not activated", HttpStatus.LOCKED);
		}
		
		return userLoginDTO;
	}



	
	public TblUserInfo getUserByUserNameAndEmailId(String uname, String email) throws Exception {
			return userInfoDao.getUserByUserNameAndEmailId(uname,email);
	}



	public TblUserInfo getUserByUsername(String uname) throws Exception {
		return userInfoDao.getUserByUsername(uname);
	}



	
	public List<TblUserInfo> getUserListByEmailAndType(String email) throws Exception {
		return userInfoDao.getUserListByEmailAndType(email);
	}



	@Transactional
	public UserLoginDTO setPassword(String username, String curpwd, String newpwd, String usertype) throws AtAppException {
		TblUserInfo userInfo= userInfoDao.userLogin(username,curpwd,usertype);
		
		if(userInfo==null){
			throw new AtAppException("UserDetails not found", HttpStatus.NOT_FOUND);
		}
		
			
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
			userLoginDTO.setUserId(userInfo.getId());
		}
		if(userLoginDTO==null){
			throw new AtAppException("Token authentication not activated", HttpStatus.LOCKED);
		}
		
		userInfo.setPwdChangeDt(new Date(System.currentTimeMillis()));
		userInfo.setPassword(newpwd);
		userInfoDao.save(userInfo);
		
		return userLoginDTO;
	}



	public TblUserInfo getUserByUserId(String userId) throws AtAppException {
			return userInfoDao.getUserByUserId(userId);
	}


	public UserDeviceMapping getUserDeviceByDevEUI(String devEUI) throws AtAppException {
		return userDeviceMappingDao.getDeviceByEUI(devEUI);
	}



	
	public void deleteDevLoraNode(String appId, String devEUI,String userId) throws Exception {
		userDeviceMappingDao.deleteDevLoraNode(appId,devEUI,userId);
		
	}

}
