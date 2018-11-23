package com.team.app.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team.app.domain.TblUnizenKeyConfig;

public interface UnizenKeyConfigDAO extends JpaRepository<TblUnizenKeyConfig, Serializable> {

	@Query("SELECT key FROM TblUnizenKeyConfig key WHERE key.unizenKeyName =:keyUnizenMobile")
	TblUnizenKeyConfig getKeyConfigValue(@Param("keyUnizenMobile") String keyUnizenMobile);

}
