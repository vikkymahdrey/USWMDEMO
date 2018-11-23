package com.team.app.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the place database table.
 * 
 */
@Entity
@Table(name="place")
@NamedQuery(name="Place.findAll", query="SELECT p FROM Place p")
public class Place implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String placename;

	//bi-directional many-to-one association to Landmark
	@OneToMany(mappedBy="place")
	private List<Landmark> landmarks;

	//bi-directional many-to-one association to Area
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="areaId")
	private Area area;

	public Place() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlacename() {
		return this.placename;
	}

	public void setPlacename(String placename) {
		this.placename = placename;
	}

	public List<Landmark> getLandmarks() {
		return this.landmarks;
	}

	public void setLandmarks(List<Landmark> landmarks) {
		this.landmarks = landmarks;
	}

	public Landmark addLandmark(Landmark landmark) {
		getLandmarks().add(landmark);
		landmark.setPlace(this);

		return landmark;
	}

	public Landmark removeLandmark(Landmark landmark) {
		getLandmarks().remove(landmark);
		landmark.setPlace(null);

		return landmark;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}