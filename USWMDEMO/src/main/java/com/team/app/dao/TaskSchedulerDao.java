package com.team.app.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team.app.domain.TblSchedularTask;

public interface TaskSchedulerDao extends JpaRepository<TblSchedularTask, Serializable> {

	@Query("SELECT t FROM TblSchedularTask t ")
	List<TblSchedularTask> getTaskdetails();		
	
	@Modifying
	@Query(value="UPDATE tbl_schedular_task t SET t.updated_dt=?1 WHERE t.id=?2", nativeQuery = true)
	@Transactional
	void setUpdateDt(@Param("updated_dt") Date updatedDt, @Param("id") int id);
	
	
	@Query("Select t.taskTtl from TblSchedularTask t where t.id=1")
    Date getTaskttl();
	
	@Query("FROM TblSchedularTask t ")
	TblSchedularTask getTaskSchedular();
}
