package com.team.app.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the downlink_queue database table.
 * 
 */
@Entity
@Table(name="downlink_queue")
@NamedQuery(name="DownlinkQueue.findAll", query="SELECT d FROM DownlinkQueue d")
public class DownlinkQueue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private String data;

	@Column(name="dev_eui")
	private String devEui;
	
	@Column(name="deviceId")
	private String deviceId;
	
	@Column(name="flag")
	private String flag;
	
	@Column(name="downlinkID")
	private String downlinkID;
	
	
	
	public String getDownlinkID() {
		return downlinkID;
	}

	public void setDownlinkID(String downlinkID) {
		this.downlinkID = downlinkID;
	}

	@Column(name="applicationID")
	private String applicationID;

	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	private String fport;

	private String mpdu;

	private String pdu;

	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	public DownlinkQueue() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDevEui() {
		return this.devEui;
	}

	public void setDevEui(String devEui) {
		this.devEui = devEui;
	}

	public String getFport() {
		return this.fport;
	}

	public void setFport(String fport) {
		this.fport = fport;
	}


	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getMpdu() {
		return mpdu;
	}

	public void setMpdu(String mpdu) {
		this.mpdu = mpdu;
	}

	public String getPdu() {
		return pdu;
	}

	public void setPdu(String pdu) {
		this.pdu = pdu;
	}

	

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}