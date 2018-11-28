package com.team.app.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team.app.domain.LoraFrame;

public interface FrameDao extends JpaRepository<LoraFrame, Serializable> {

	@Query("Select f from LoraFrame f order by id desc")
	List<LoraFrame> getFrames();
		
	@Modifying
	@Query(value="UPDATE lora_frames f SET f.nodeName=?1 WHERE f.devEUI=?2", nativeQuery = true)
	@Transactional
	void setUpdateNodeName(@Param("nodeName") String nodeName, @Param("devEUI") String devEUI);
	
	
	@Modifying
	@Query(value="delete from lora_frames  where applicationID=?1 and devEUI=?2",nativeQuery = true)
	@Transactional
	void deleteDevEUI(@Param("applicationID") String applicationID, @Param("devEUI") String devEUI);

	@Query("Select f from LoraFrame f where f.applicationID=:applicationID group by f.devEUI")
	List<LoraFrame> getDevEUIByAppId(@Param("applicationID") String applicationID);

	@Query("Select f from LoraFrame f where f.devEUI=:devEUI")
	List<LoraFrame> getDeviceIdByDevEUI(@Param("devEUI") String devEUI);

	/*@Query(value="Select f.* from lora_frames  f where f.devEUI=?1 and f.created_at between ?2 and ?3",nativeQuery=true)
	List<Object[]> getFramesByFrmToDateAndDevEUI(@Param("devEUI") String devEUI,@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	*/
	
	@Query("Select f from LoraFrame f where f.devEUI=:devEUI and f.createdAt between :fromDate and :toDate")
	List<LoraFrame> getFramesByFrmToDateAndDevEUI(@Param("devEUI") String devEUI,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);

	/*@Query(value="SELECT sum(f.waterltr) as row1 FROM lora_frames f where f.created_at like '%2018-07-27%' and ?1<=?2 and f.applicationId=?3 and f.devEUI=?4",nativeQuery=true)
	Object[] getLoraFrameByDevEUIandAppIdandDates(@Param("startDt") Date startDt, @Param("endDt") Date endDt, @Param("appId") String appId, @Param("devEUI") String devEUI);
	*/
	
	
	@Query(value = "CALL frames(?1,?2,?3,?4,?5)", nativeQuery = true)
	Object[] getLoraFrameByDevEUIandAppIdandDates(@Param("startDt") Date startDt, @Param("endDt") Date endDt, @Param("appId") String appId, @Param("devEUI") String devEUI,@Param("interval") String interval);

	@Query(value = "CALL loraframes(?1,?2,?3,?4)", nativeQuery = true)
	Object[] getLoraFrameByUserIdAndDates(@Param("startDt") Date startDt, @Param("endDt") Date endDt,@Param("userId") String userId,@Param("interval") String interval);

	@Query(value = "CALL graphunits(?1,?2,?3)", nativeQuery = true)
	Object[] getLoraFrameByDateAndDevEUI(@Param("reqDt") Date reqDt,@Param("appId") String appId,@Param("devEUI") String devEUI);

	/*@Query("Select f from LoraFrame f where f.applicationID=:appId and f.devEUI=:devEUI and f.createdAt between :fromDate and :toDate")
	List<LoraFrame> getFramesByFrmToDateAndDevEUIAndAppId(@Param("appId")  String appId, @Param("devEUI") String devEUI,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	*/
	
	@Query(value = "CALL dashboard(?1,?2,?3,?4,?5)", nativeQuery = true)
	Object[] getFramesByFrmToDateAndDevEUIAndAppId(@Param("appId")  String appId, @Param("devEUI") String devEUI,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate, @Param("type") String type);

	@Query(value="Select f.* from lora_frames f where  f.applicationID=?1 and f.devEUI=?2 and f.devMapId=?3 order by f.id desc limit 1",nativeQuery=true)
	LoraFrame getLoraFrameByDevEUIAndDeviceId(@Param("applicationID") String applicationID, @Param("devEUI") String devEUI, @Param("devMapId") String devMapId);

	@Query(value="Select f.* from lora_frames f where f.devEUI=?1 and  f.applicationID=?2  order by f.id desc limit 1",nativeQuery=true)
	LoraFrame getLoraFrameByDevEUIAndAppID(@Param("devEUI") String devEUI, @Param("appId") String appId);

	@Query(value="Select f.* from lora_frames f where f.applicationID=?1 and f.devEUI=?2 and f.devMapId=?3 order by f.id desc limit 1,1",nativeQuery=true)
	LoraFrame getSecLastLoraFrameByDevEUIAndDeviceId(@Param("applicationID") String applicationID, @Param("devEUI") String devEUI, @Param("devMapId") String devMapId);

	@Query(value="SELECT sum(f.waterltr) as total_waterltr FROM lora_frames f where date_format(f.created_at, '%Y-%m')=date_format(now(), '%Y-%m')",nativeQuery=true)
	Long getWaterConsumptionUnitsByCurMonth();

	@Query("Select sum(f.waterltr) as total_waterunits FROM LoraFrame f where f.applicationID=:appId and f.devEUI=:devEUI and f.createdAt between :fromDate and :toDate")
	Long getTotalWaterUnitsFrmToDate(@Param("appId") String appId,@Param("devEUI") String devEUI,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);

	@Query(value = "CALL user_dashboard(?1,?2,?3)", nativeQuery = true)
	Object[] getUserDashboardGraphsOnLoad(@Param("appId") String appId, @Param("devEUI") String devEUI,@Param("currDate") Date currDate);

	@Query(value = "CALL user_dashboard_date_filter(?1,?2,?3,?4)", nativeQuery = true)
	Object[] getUserDashboardGraphOnSubmit(@Param("devEUI") String devEUI,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate,@Param("type") String type);

	@Query(value="SELECT sum(f.waterltr) as total_waterltr FROM lora_frames f where f.applicationID=?1 and f.devEUI=?2",nativeQuery=true)
	Long getWaterConsumptionsUnitForEndUser(@Param("appId") String appId,@Param("devEUI") String devEUI);

	@Query(value="SELECT sum(f.waterltr) as total_waterltr FROM lora_frames f where f.applicationID=?1 and f.devEUI=?2 and f.id>?3",nativeQuery=true)
	Long getWaterConsumptionsUnitForEndUserOnConsoilID(@Param("appId") String appId,@Param("devEUI") String devEUI,@Param("id") long fId);

	@Query(value="SELECT sum(f.waterltr) as total_waterltr FROM lora_frames f where f.devEUI=?1 and f.created_at between ?2 and ?3",nativeQuery=true)
	Long getWaterConsumptionsUnitFromDates(@Param("devEUI") String devEUI,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
	
	

}
