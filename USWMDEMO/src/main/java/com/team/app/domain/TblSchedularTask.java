package com.team.app.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_schedular_task database table.
 * 
 */
@Entity
@Table(name="tbl_schedular_task")
@NamedQuery(name="TblSchedularTask.findAll", query="SELECT t FROM TblSchedularTask t")
public class TblSchedularTask implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_dt")
	private Date createdDt;

	private String task;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="task_ttl")
	private Date taskTtl;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_dt")
	private Date updatedDt;

	public TblSchedularTask() {
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

	public String getTask() {
		return this.task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Date getTaskTtl() {
		return this.taskTtl;
	}

	public void setTaskTtl(Date taskTtl) {
		this.taskTtl = taskTtl;
	}

	public Date getUpdatedDt() {
		return this.updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

}