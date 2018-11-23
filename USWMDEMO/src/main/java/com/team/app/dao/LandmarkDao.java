package com.team.app.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team.app.domain.Landmark;

public interface LandmarkDao extends JpaRepository<Landmark, Serializable> {

	@Query("from Landmark l where l.place.area.id =:areaId")
	List<Landmark> getSpecificLandmarksByAreaId(@Param("areaId") String areaId);

	@Query("from Landmark l where l.place.id =:placeId")
	List<Landmark> getSpecificLandmarksByPlaceId(@Param("placeId") String placeId);

	@Query("from Landmark l where l.place.area.orgId =:orgId")
	List<Landmark> getSpecificLandmarksByOrgId(@Param("orgId") String orgId);

	@Query("from Landmark l where l.landmarkname=:landmarkname and l.place.id =:placeId")
	Landmark getLandmarkByNameAndPlaceId(@Param("landmarkname") String landmarkname,@Param("placeId") String placeId);

	@Query("from Landmark l where l.id=:landmarkId and l.place.id =:placeId")
	Landmark getLandMarkByIdAndPlaceId(@Param("landmarkId") String landmarkId, @Param("placeId") String placeId);

	@Query("from Landmark l where l.id =:landmarkId")
	Landmark getLandMarkById(@Param("landmarkId") String landmarkId);

	@Query(value="select l.id, l.landmarkname, p.placename, a.areaname from landmark l join place p on l.placeId=p.id join area a on p.areaId=a.id  where a.orgId=?1 and (l.landmarkname like %?2% or p.placename like %?2% or a.areaname like %?2%) order by a.areaname,p.placename,l.landmarkname",nativeQuery = true)
	List<Object[]> getLandMarkByAPL(@Param("orgId") String orgId,@Param("landMarkText") String landMarkText);

	@Query("from Landmark l where l.id =:landmarkId")
	List<Landmark> getLandMarkByUserLandmarkId(@Param("landmarkId") String landmarkId);
	
	

}
