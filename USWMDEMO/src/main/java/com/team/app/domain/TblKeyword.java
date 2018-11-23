package com.team.app.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_keyword database table.
 * 
 */
@Entity
@Table(name="tbl_keyword")
@NamedQuery(name="TblKeyword.findAll", query="SELECT t FROM TblKeyword t")
public class TblKeyword implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String desc;

	private String key;

	private String value;

	//bi-directional many-to-one association to TblKeywordType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="keytype")
	private TblKeywordType tblKeywordType;

	public TblKeyword() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TblKeywordType getTblKeywordType() {
		return this.tblKeywordType;
	}

	public void setTblKeywordType(TblKeywordType tblKeywordType) {
		this.tblKeywordType = tblKeywordType;
	}

}