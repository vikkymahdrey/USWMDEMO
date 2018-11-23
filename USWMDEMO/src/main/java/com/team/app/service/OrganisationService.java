package com.team.app.service;

import java.util.Map;

public interface OrganisationService {

	Map<String, Object> getLoraServerOrganisation() throws Exception;

	long getLoraServerUsers()throws Exception;

	String getLoraServerApplicationByOrgId(String orgId)throws Exception;

	String getLoraServerDevEUIByAppId(String appId)throws Exception;

	String getLoraServerDevEUIByAppIdForSync(String appId)throws Exception;

	String getLoraServerDevEUIByAppIdCall(String appId) throws Exception;

	String getLoraServerDevEUIByAppIdModal(String appId)throws Exception;

	String getLoraServerApplicationByOrgIdModal(String orgId)throws Exception;

	String getLoraServerUsersByOrgIdModal(String orgId)throws Exception;

}
