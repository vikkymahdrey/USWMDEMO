package com.team.app.service;

import java.util.List;

import com.team.app.domain.Role;
import com.team.app.domain.TblUserInfo;
import com.team.app.domain.UserDeviceMapping;
import com.team.app.dto.UserLoginDTO;
import com.team.app.exception.AtAppException;

public interface UserLoginService {

	TblUserInfo getUserByUserAndPwd(String username, String password) throws Exception;

	List<TblUserInfo> getUserInfosCount() throws Exception;

	List<TblUserInfo> getUserInfos()throws Exception;

	TblUserInfo getUserByUnameAndEmail(String uname, String email)throws Exception;

	TblUserInfo getUserByEmailId(String email)throws Exception;

	Role getRoleByRoleId(String roleId)throws Exception;

	String saveUser(TblUserInfo user, UserDeviceMapping udm)throws Exception;

	void updateUserInfo(TblUserInfo user)throws Exception;

	TblUserInfo getUserByUId(String uId)throws Exception;

	List<TblUserInfo> getUserListByEmail(String email)throws Exception;

	UserDeviceMapping saveUDM(UserDeviceMapping udm)throws Exception;

	UserDeviceMapping saveNewUDMToUser(UserDeviceMapping udm)throws Exception;

	List<Role> getRoles()throws Exception;

	List<UserDeviceMapping> getUserDeviceByOrgId(String orgId)throws Exception;

	List<TblUserInfo> getUserByRoleId(String usertype) throws Exception;

	UserLoginDTO userLogin(String username, String password, String usertype) throws AtAppException;

	TblUserInfo getUserByUserNameAndEmailId(String uname, String email)throws Exception;

	TblUserInfo getUserByUsername(String uname)throws Exception;

	List<TblUserInfo> getUserListByEmailAndType(String email)throws Exception;

	UserLoginDTO setPassword(String username, String curpwd, String newpwd, String usertype) throws AtAppException;

	TblUserInfo getUserByUserId(String userId)throws AtAppException;

	UserDeviceMapping getUserDeviceByDevEUI(String devEUI)throws AtAppException;

	void deleteDevLoraNode(String appId, String devEUI, String userId) throws Exception;

	

}
