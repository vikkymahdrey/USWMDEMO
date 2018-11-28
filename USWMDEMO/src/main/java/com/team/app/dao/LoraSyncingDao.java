package com.team.app.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team.app.domain.TblSyncingChecker;

public interface LoraSyncingDao extends JpaRepository<TblSyncingChecker, Serializable> {

	@Query("From TblSyncingChecker c where c.appId=:appId and c.devEUI=:devEUI and c.status='A' ")
	TblSyncingChecker getLoraSyncingInfo(@Param("appId") String appId, @Param("devEUI") String devEUI);

	@Query("From TblSyncingChecker c ")
	List<TblSyncingChecker> getLoraSyncedDevices();

}
