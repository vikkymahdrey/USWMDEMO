package com.team.app.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_device_feedback_type database table.
 * 
 */
@Entity
@Table(name="tbl_device_feedback_type")
@NamedQuery(name="TblDeviceFeedbackType.findAll", query="SELECT t FROM TblDeviceFeedbackType t")
public class TblDeviceFeedbackType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String name;

	private String type;

	public TblDeviceFeedbackType() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}