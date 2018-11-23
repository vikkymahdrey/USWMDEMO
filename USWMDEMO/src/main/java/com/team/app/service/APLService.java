package com.team.app.service;

import java.util.List;

import com.team.app.domain.Area;
import com.team.app.domain.Landmark;
import com.team.app.domain.Place;
import com.team.app.dto.OrganisationDto;

public interface APLService {

	List<OrganisationDto> getOrganisation() throws Exception;

	List<Area> getAreasByOrgId(String orgId) throws Exception;

	Place getPlaceById(String placeforLandmark)throws Exception;

	List<Landmark> getSpecificLandmarks(String area, String place, String orgId)throws Exception;

	Area insertArea(Area areaObj)throws Exception;

	Area getAreasByAreaId(String areaId)throws Exception;

	Area getAreaByOrgAndAreaId(String orgId, String areaId)throws Exception;

	Area updateArea(Area area)throws Exception;

	Place insertPlace(Place place, String areaId)throws Exception;

	Place getPlaceByPlaceAndAreaId(String placeId, String areaId)throws Exception;

	Place updatePlace(Place place)throws Exception;

	Landmark insertLandmark(Landmark landmark, String placeId)throws Exception;

	Landmark getLandMarkByIdAndPlaceId(String landmarkId, String placeId)throws Exception;

	Landmark updateLandmark(Landmark lm)throws Exception;

	List<Area> getAreasListByAreaId(String areaId) throws Exception;

	Landmark getLandMarkById(String landmarkId)throws Exception;

	List<Object[]> getLandMarkByAPL(String orgId,String landMarkText )throws Exception;

	List<Landmark> getLandMarkByUserLandmarkId(String landmarkId)throws Exception;

		

}
