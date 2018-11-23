package com.team.app.service;

import java.util.List;

import com.team.app.domain.TblKeywordType;

public interface KeywordService {

	List<TblKeywordType> getKeywordTypes() throws Exception;

	TblKeywordType getKeyTypeById(String typeId)throws Exception;

}
