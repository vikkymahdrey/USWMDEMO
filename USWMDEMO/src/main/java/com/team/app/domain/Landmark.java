package com.team.app.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the landmark database table.
 * 
 */
@Entity
@Table(name="landmark")
@NamedQuery(name="Landmark.findAll", query="SELECT l FROM Landmark l")
public class Landmark implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String landmarkname;

	private String lat;

	private String lon;

	//bi-directional many-to-one association to Place
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="placeId")
	private Place place;

	//bi-directional many-to-one association to TblUserInfo
	@OneToMany(mappedBy="landmark")
	private List<TblUserInfo> tblUserInfos;

	public Landmark() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLandmarkname() {
		return this.landmarkname;
	}

	public void setLandmarkname(String landmarkname) {
		this.landmarkname = landmarkname;
	}

	public String getLat() {
		return this.lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return this.lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public Place getPlace() {
		return this.place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public List<TblUserInfo> getTblUserInfos() {
		return this.tblUserInfos;
	}

	public void setTblUserInfos(List<TblUserInfo> tblUserInfos) {
		this.tblUserInfos = tblUserInfos;
	}

	public TblUserInfo addTblUserInfo(TblUserInfo tblUserInfo) {
		getTblUserInfos().add(tblUserInfo);
		tblUserInfo.setLandmark(this);

		return tblUserInfo;
	}

	public TblUserInfo removeTblUserInfo(TblUserInfo tblUserInfo) {
		getTblUserInfos().remove(tblUserInfo);
		tblUserInfo.setLandmark(null);

		return tblUserInfo;
	}

}