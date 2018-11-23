package com.team.app.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team.app.domain.TblRetryConfig;

public interface DownlinkRetryConfigDao extends JpaRepository<TblRetryConfig, Serializable> {

	@Query("From TblRetryConfig")
	List<TblRetryConfig> getDownlinkForRetryConfig();

	@Query("From TblRetryConfig r where r.id=:retryId")
	TblRetryConfig getRetryConfigById(@Param("retryId") String retryId);

}
