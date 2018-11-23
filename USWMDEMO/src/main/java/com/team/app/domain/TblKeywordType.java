package com.team.app.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_keyword_type database table.
 * 
 */
@Entity
@Table(name="tbl_keyword_type")
@NamedQuery(name="TblKeywordType.findAll", query="SELECT t FROM TblKeywordType t")
public class TblKeywordType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String type;

	//bi-directional many-to-one association to TblKeyword
	@OneToMany(mappedBy="tblKeywordType")
	private List<TblKeyword> tblKeywords;

	public TblKeywordType() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<TblKeyword> getTblKeywords() {
		return this.tblKeywords;
	}

	public void setTblKeywords(List<TblKeyword> tblKeywords) {
		this.tblKeywords = tblKeywords;
	}

	public TblKeyword addTblKeyword(TblKeyword tblKeyword) {
		getTblKeywords().add(tblKeyword);
		tblKeyword.setTblKeywordType(this);

		return tblKeyword;
	}

	public TblKeyword removeTblKeyword(TblKeyword tblKeyword) {
		getTblKeywords().remove(tblKeyword);
		tblKeyword.setTblKeywordType(null);

		return tblKeyword;
	}

}