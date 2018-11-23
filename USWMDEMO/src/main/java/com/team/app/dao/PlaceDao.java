package com.team.app.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team.app.domain.Place;

public interface PlaceDao extends JpaRepository<Place, Serializable> {

	@Query("Select p from Place p where p.id=:placeId")
	Place getPlaceById(@Param("placeId") String placeId);

	
	@Query("Select p from Place p where p.placename=:placename and p.area.id=:areaId")
	Place getPlaceByAreaAndName(@Param("placename") String placename,@Param("areaId") String areaId);

	@Query("Select p from Place p where p.id=:placeId and p.area.id=:areaId")
	Place getPlaceByPlaceAndAreaId(@Param("placeId") String placeId, @Param("areaId") String areaId);

	

}
