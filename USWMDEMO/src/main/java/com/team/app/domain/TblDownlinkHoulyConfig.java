package com.team.app.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_downlink_houly_config database table.
 * 
 */
@Entity
@Table(name="tbl_downlink_houly_config")
@NamedQuery(name="TblDownlinkHoulyConfig.findAll", query="SELECT t FROM TblDownlinkHoulyConfig t")
public class TblDownlinkHoulyConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_dt")
	private Date createdDt;

	private String perday;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_dt")
	private Date updatedDt;

	//bi-directional many-to-one association to TblDownlinkPacketConfig
	@OneToMany(mappedBy="tblDownlinkHoulyConfig")
	private List<TblDownlinkPacketConfig> tblDownlinkPacketConfigs;

	public TblDownlinkHoulyConfig() {
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

	public String getPerday() {
		return this.perday;
	}

	public void setPerday(String perday) {
		this.perday = perday;
	}

	public Date getUpdatedDt() {
		return this.updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	public List<TblDownlinkPacketConfig> getTblDownlinkPacketConfigs() {
		return this.tblDownlinkPacketConfigs;
	}

	public void setTblDownlinkPacketConfigs(List<TblDownlinkPacketConfig> tblDownlinkPacketConfigs) {
		this.tblDownlinkPacketConfigs = tblDownlinkPacketConfigs;
	}

	public TblDownlinkPacketConfig addTblDownlinkPacketConfig(TblDownlinkPacketConfig tblDownlinkPacketConfig) {
		getTblDownlinkPacketConfigs().add(tblDownlinkPacketConfig);
		tblDownlinkPacketConfig.setTblDownlinkHoulyConfig(this);

		return tblDownlinkPacketConfig;
	}

	public TblDownlinkPacketConfig removeTblDownlinkPacketConfig(TblDownlinkPacketConfig tblDownlinkPacketConfig) {
		getTblDownlinkPacketConfigs().remove(tblDownlinkPacketConfig);
		tblDownlinkPacketConfig.setTblDownlinkHoulyConfig(null);

		return tblDownlinkPacketConfig;
	}

}