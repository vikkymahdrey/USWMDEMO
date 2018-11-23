package com.team.app.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_retry_config database table.
 * 
 */
@Entity
@Table(name="tbl_retry_config")
@NamedQuery(name="TblRetryConfig.findAll", query="SELECT t FROM TblRetryConfig t")
public class TblRetryConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;
	

	@Column(name="retry_vol")
	private String retryVol;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to TblRetryIntervalMap
	@OneToMany(mappedBy="tblRetryConfig")
	private List<TblRetryIntervalMap> tblRetryIntervalMaps;

	public TblRetryConfig() {
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

	

	public String getRetryVol() {
		return this.retryVol;
	}

	public void setRetryVol(String retryVol) {
		this.retryVol = retryVol;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<TblRetryIntervalMap> getTblRetryIntervalMaps() {
		return this.tblRetryIntervalMaps;
	}

	public void setTblRetryIntervalMaps(List<TblRetryIntervalMap> tblRetryIntervalMaps) {
		this.tblRetryIntervalMaps = tblRetryIntervalMaps;
	}

	public TblRetryIntervalMap addTblRetryIntervalMap(TblRetryIntervalMap tblRetryIntervalMap) {
		getTblRetryIntervalMaps().add(tblRetryIntervalMap);
		tblRetryIntervalMap.setTblRetryConfig(this);

		return tblRetryIntervalMap;
	}

	public TblRetryIntervalMap removeTblRetryIntervalMap(TblRetryIntervalMap tblRetryIntervalMap) {
		getTblRetryIntervalMaps().remove(tblRetryIntervalMap);
		tblRetryIntervalMap.setTblRetryConfig(null);

		return tblRetryIntervalMap;
	}

}