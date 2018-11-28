package com.team.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.app.dao.LoraSyncingDao;
import com.team.app.domain.TblSyncingChecker;
import com.team.app.logger.AtLogger;
import com.team.app.service.LoraSyncingService;

@Service
public class LoraSyncingServiceImpl implements LoraSyncingService {
	
	private static final AtLogger logger = AtLogger.getLogger(LoraSyncingServiceImpl.class);

	
	@Autowired
	private LoraSyncingDao loraSyncingDao;	
	
	public TblSyncingChecker getLoraSyncingInfo(String appId, String devId) throws Exception {
			return loraSyncingDao.getLoraSyncingInfo(appId,devId);
	}

	
	public TblSyncingChecker saveSyncInfo(TblSyncingChecker sync) throws Exception {
			return loraSyncingDao.save(sync);
	}

}
