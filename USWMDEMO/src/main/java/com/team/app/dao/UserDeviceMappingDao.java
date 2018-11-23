package com.team.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team.app.domain.UserDeviceMapping;

public interface UserDeviceMappingDao extends JpaRepository<UserDeviceMapping, Serializable> {

	@Query("from UserDeviceMapping udm where udm.devEUI=:devEUI and udm.status='A' ")
	UserDeviceMapping getDeviceByEUI(@Param("devEUI") String devEUI);
	
	@Query("from UserDeviceMapping udm where udm.devEUI=:devEUI and udm.orgId=:orgId and udm.status='A' ")
	UserDeviceMapping getDeviceByEUIAndOrgId(@Param("devEUI") String devEUI, @Param("orgId") String orgId);

	@Query("from UserDeviceMapping udm where udm.orgId=:orgId and udm.status='A' ")
	List<UserDeviceMapping> getUserDeviceByOrgId(@Param("orgId") String orgId);
	
	@Modifying
	@Query(value="delete from user_device_mapping where appId=?1 and devEUI=?2 and userId=?3",nativeQuery = true)
	@Transactional
	void deleteDevLoraNode(@Param("appId") String appId, @Param("devEUI") String devEUI, @Param("userId") String userId);



}
