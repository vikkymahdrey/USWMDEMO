package com.team.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.app.dao.KeywordTypeDao;
import com.team.app.domain.TblKeywordType;
import com.team.app.logger.AtLogger;
import com.team.app.service.KeywordService;
@Service
public class KeywordServiceImpl implements KeywordService {
	
	private static final AtLogger logger = AtLogger.getLogger(KeywordServiceImpl.class);

	@Autowired
	private KeywordTypeDao keywordTypeDao;

	
	public List<TblKeywordType> getKeywordTypes() throws Exception {
		return keywordTypeDao.getKeywordTypes();
	}


	public TblKeywordType getKeyTypeById(String typeId) throws Exception {
		return keywordTypeDao.getKeyTypeById(typeId);
	}

}
