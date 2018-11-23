package com.team.app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tbl_db_audit")
public class DatabaseAudit {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	public Date getTimeofaction() {
		return timeofaction;
	}

	public void setTimeofaction(Date timeofaction) {
		this.timeofaction = timeofaction;
	}

	private String tablename;
	
	private String action_type;
	 
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="timeofaction")
	private Date timeofaction;
	
	private String updatedrow;
	
	private String exceptionifany;
	
	private String updateNativeQuery;
	
	private String updateparams;
	
	private int auditid;
	
	
public int getAuditid() {
		return auditid;
	}

	public void setAuditid(int auditid) {
		this.auditid = auditid;
	}

	public String getUpdateNativeQuery() {
		return updateNativeQuery;
	}

	public void setUpdateNativeQuery(String updateNativeQuery) {
		this.updateNativeQuery = updateNativeQuery;
	}

	public String getUpdateparams() {
		return updateparams;
	}

	public void setUpdateparams(String updateparams) {
		this.updateparams = updateparams;
	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}

	
	public String getUpdatedrow() {
		return updatedrow;
	}

	public void setUpdatedrow(String updatedrow) {
		this.updatedrow = updatedrow;
	}

	public String getExceptionifany() {
		return exceptionifany;
	}

	public void setExceptionifany(String exceptionifany) {
		this.exceptionifany = exceptionifany;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	
	
	
}