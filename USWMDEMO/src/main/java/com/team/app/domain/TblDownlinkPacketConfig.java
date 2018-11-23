package com.team.app.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_downlink_packet_config database table.
 * 
 */
@Entity
@Table(name="tbl_downlink_packet_config")
@NamedQuery(name="TblDownlinkPacketConfig.findAll", query="SELECT t FROM TblDownlinkPacketConfig t")
public class TblDownlinkPacketConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_dt")
	private Date createdDt;

	private String packet;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_dt")
	private Date updatedDt;

	//bi-directional many-to-one association to TblDownlinkHoulyConfig
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="packetConfigID")
	private TblDownlinkHoulyConfig tblDownlinkHoulyConfig;

	public TblDownlinkPacketConfig() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedDt() {
		return this.createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	public String getPacket() {
		return this.packet;
	}

	public void setPacket(String packet) {
		this.packet = packet;
	}

	public Date getUpdatedDt() {
		return this.updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	public TblDownlinkHoulyConfig getTblDownlinkHoulyConfig() {
		return this.tblDownlinkHoulyConfig;
	}

	public void setTblDownlinkHoulyConfig(TblDownlinkHoulyConfig tblDownlinkHoulyConfig) {
		this.tblDownlinkHoulyConfig = tblDownlinkHoulyConfig;
	}

}