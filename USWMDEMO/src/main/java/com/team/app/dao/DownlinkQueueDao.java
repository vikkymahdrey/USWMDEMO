package com.team.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team.app.domain.DownlinkQueue;

public interface DownlinkQueueDao  extends JpaRepository<DownlinkQueue, Serializable> {

	@Query("From DownlinkQueue r where r.applicationID=:applicationID and r.devEui=:devEUI and r.deviceId=:devMapId and r.flag='0'")
	DownlinkQueue getDownlinkQueueByAppIdDevEUIAndDeviceId(@Param("applicationID") String applicationID, @Param("devEUI") String devEUI, @Param("devMapId") String devMapId);

	@Query("From DownlinkQueue r where r.downlinkID=:downlinkID and r.flag='0'")
	DownlinkQueue getDownlinkQueueById(@Param("downlinkID") String downlinkID);
	
	
	@Modifying
	@Query(value="UPDATE downlink_queue f SET f.flag='1' WHERE f.applicationID=?1 and f.dev_eui=?2 and f.deviceId=?3 and f.flag='0' ", nativeQuery = true)
	@Transactional
	void updateDownlinkQueueFlag(@Param("applicationID") String applicationID, @Param("devEUI") String devEUI,@Param("deviceId") String deviceId);

	@Query("From DownlinkQueue d ")
	List<DownlinkQueue> getDownlinkQueue();

	@Query("From DownlinkQueue d where d.applicationID=:applicationID and d.devEui=:devEUI")
	List<DownlinkQueue> getDownlinkByDevEUIAndAppId(@Param("applicationID") String applicationID, @Param("devEUI") String devEUI);

}
