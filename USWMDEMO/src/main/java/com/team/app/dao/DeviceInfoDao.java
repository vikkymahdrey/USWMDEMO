package com.team.app.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team.app.domain.TblDeviceDetail;

public interface DeviceInfoDao extends JpaRepository<TblDeviceDetail, Serializable> {

	@Query("Select b from TblDeviceDetail b where b.devEUI=:devEUI")
	TblDeviceDetail getDeviceInfoByDevEUI(@Param("devEUI") String devEUI);

}
