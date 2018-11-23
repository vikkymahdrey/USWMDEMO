package com.team.app.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team.app.domain.TblDownlinkPacketConfig;

public interface DownlinkPacketConfigDao extends JpaRepository<TblDownlinkPacketConfig, Serializable> {

	@Query("From TblDownlinkPacketConfig p where p.tblDownlinkHoulyConfig.id=:hourly")
	List<TblDownlinkPacketConfig> getDownlinkPacketByHourlyID(@Param("hourly") String hourly);
	
}
