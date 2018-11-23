package com.team.app.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_unizen_key_config database table.
 * 
 */
@Entity
@Table(name="tbl_unizen_key_config")
@NamedQuery(name="TblUnizenKeyConfig.findAll", query="SELECT t FROM TblUnizenKeyConfig t")
public class TblUnizenKeyConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DT")
	private Date createdDt;

	@Column(name="IS_ENABLED")
	private String isEnabled;

	private String status;

	@Column(name="UNIZEN_KEY_NAME")
	private String unizenKeyName;

	@Column(name="UNIZEN_KEY_VALUE")
	private String unizenKeyValue;

	public TblUnizenKeyConfig() {
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

	public String getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUnizenKeyName() {
		return this.unizenKeyName;
	}

	public void setUnizenKeyName(String unizenKeyName) {
		this.unizenKeyName = unizenKeyName;
	}

	public String getUnizenKeyValue() {
		return this.unizenKeyValue;
	}

	public void setUnizenKeyValue(String unizenKeyValue) {
		this.unizenKeyValue = unizenKeyValue;
	}

}