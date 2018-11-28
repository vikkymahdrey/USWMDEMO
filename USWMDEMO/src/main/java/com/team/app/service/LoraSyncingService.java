package com.team.app.service;

import com.team.app.domain.TblSyncingChecker;

public interface LoraSyncingService {

	TblSyncingChecker getLoraSyncingInfo(String appId, String devId)throws Exception;

	TblSyncingChecker saveSyncInfo(TblSyncingChecker sync)throws Exception;

}
