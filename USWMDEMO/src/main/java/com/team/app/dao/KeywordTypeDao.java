package com.team.app.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team.app.domain.TblKeywordType;

public interface KeywordTypeDao extends JpaRepository<TblKeywordType, Serializable> {

	@Query("From TblKeywordType")
	List<TblKeywordType> getKeywordTypes();

	@Query("From TblKeywordType t where t.id=:typeId")
	TblKeywordType getKeyTypeById(@Param("typeId") String typeId);


}
