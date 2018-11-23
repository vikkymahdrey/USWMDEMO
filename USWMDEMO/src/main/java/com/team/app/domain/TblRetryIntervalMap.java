package com.team.app.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_retry_interval_map database table.
 * 
 */
@Entity
@Table(name="tbl_retry_interval_map")
@NamedQuery(name="TblRetryIntervalMap.findAll", query="SELECT t FROM TblRetryIntervalMap t")
public class TblRetryIntervalMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String interval;

	//bi-directional many-to-one association to TblRetryConfig
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="retryId")
	private TblRetryConfig tblRetryConfig;

	public TblRetryIntervalMap() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInterval() {
		return this.interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public TblRetryConfig getTblRetryConfig() {
		return this.tblRetryConfig;
	}

	public void setTblRetryConfig(TblRetryConfig tblRetryConfig) {
		this.tblRetryConfig = tblRetryConfig;
	}

}