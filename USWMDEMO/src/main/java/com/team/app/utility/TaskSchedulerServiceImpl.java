package com.team.app.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;

import com.team.app.config.MqttIntrf;
import com.team.app.dao.LoraSyncingDao;
import com.team.app.dao.TaskSchedulerDao;
import com.team.app.domain.TblSchedularTask;
import com.team.app.domain.TblSyncingChecker;
import com.team.app.logger.AtLogger;
import com.team.app.utils.DateUtil;



//@EnableScheduling
//@ComponentScan(basePackages="com.team.app.utility.*")
@Service
@Configuration
public class TaskSchedulerServiceImpl {
	
	private static final AtLogger logger = AtLogger.getLogger(TaskSchedulerServiceImpl.class);

	
	 @Autowired
	 TaskSchedulerDao taskSchedulerDao;	
	 
	 @Autowired
	 LoraSyncingDao loraSyncingDao;	
	 
	 @Autowired
	 private MqttIntrf mqttIntrf;
	 
	 
	    
	    
	    /*Start of executing logic at once*/
		 @Bean
	     public TaskScheduler taskExecutor () {
	         return new ConcurrentTaskScheduler(
	                   Executors.newScheduledThreadPool(2));
	     }
		
		 
		 @Bean
		 public String getCronValue(){
			 logger.debug("In Schedular Syncing Process");			 
			 		 
				 String df="";
				 try{						 									
					 		List<TblSyncingChecker> reqSync=loraSyncingDao.getLoraSyncedDevices();
					 			if(reqSync!=null && !reqSync.isEmpty()){
									 
									 for(TblSyncingChecker sync : reqSync) {
										 mqttIntrf.doDemo(sync.getAppId(), sync.getDevEUI());
									 }					 
								 }	
					 			
					 											 
								 	DateFormat formatter = new SimpleDateFormat("ss mm hh d M ?");
								 		formatter.setTimeZone(TimeZone.getTimeZone("IST"));
								 			TblSchedularTask task=taskSchedulerDao.getTaskSchedular();
								if(task!=null) {
									 df = formatter.format(task.getTaskTtl());
									 //task.setTaskTtl(DateUtils.addDays(task.getTaskTtl(), 1));
									 task.setUpdatedDt(DateUtil.getCurrentDateTimeIST());
									 taskSchedulerDao.save(task);
								}else {
									 df = formatter.format(DateUtil.getCurrentDateTimeIST());
									 
								}
										
				 }catch(Exception e){
					 e.printStackTrace();
				 }
				 
				 return df;
			 }
			
			  @Scheduled(cron="#{@getCronValue}")
			  public void schedulingTaskImpl() throws InterruptedException
			    {    
				  logger.debug("Execution completed.. ");		
			    }
		
		
		 
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

	
	 
	 
	 
	 
	 
	 
	



}
