package com.team.app.service;

import java.util.List;

import com.team.app.domain.DownlinkQueue;
import com.team.app.domain.TblDownlinkHoulyConfig;
import com.team.app.domain.TblDownlinkPacketConfig;
import com.team.app.domain.TblRetryConfig;

public interface DownlinkService {
	
	List<TblDownlinkHoulyConfig> getDownlinkHourlyConfig() throws Exception;

	List<TblDownlinkPacketConfig> getDownlinkPacketByHourlyID(String hourly)throws Exception;

	TblDownlinkHoulyConfig getDownlinkPktPerDayByID(String perDayPkt)throws Exception;

	void doDownlinkForDate(String devEUI, String applicationID, String fPort)throws Exception;

	String doDownlinkForMPDU(String devEUI, String applicationID, String fPort, String pktId, String packetlength)throws Exception;

	List<TblRetryConfig> getDownlinkForRetryConfig()throws Exception;

	TblRetryConfig getRetryConfigById(String retryId)throws Exception;

	String doDownlinkForRetry(String devEUI, String applicationID, String fPort, String retry, String interval)throws Exception;

	void doDownlinkForMissingPkt(String devEUI, String applicationID, String fport, String deviceId, String mpdu, String pdu)throws Exception;

	List<DownlinkQueue> getDownlinkQueue()throws Exception;

	List<DownlinkQueue> getDownlinkByDevEUIAndAppId(String applicationID, String devEUI)throws Exception;


}
