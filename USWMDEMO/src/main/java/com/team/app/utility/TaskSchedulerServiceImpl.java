package com.team.app.utility;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Service;

import com.team.app.dao.TaskSchedulerDao;
import com.team.app.domain.TblSchedularTask;
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
	 
	 
	 @Bean
	 public TaskScheduler taskScheduler() {
	     //org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
	     return new ThreadPoolTaskScheduler();
	 }
	 
	
	    
	    public static final String CRON_EXPRESSION = "0 */60 * * * ?";

	    TblSchedularTask task=null;
	    
	    public Date init() {	        
	    	 logger.debug("Executing init() method");
	    	 task=taskSchedulerDao.getTaskSchedular();
	    	 logger.debug("taskttl :",task.getTaskTtl());
				 
			 CronSequenceGenerator cronTrigger = new CronSequenceGenerator(CRON_EXPRESSION);           
			 Date next = cronTrigger.next(task.getTaskTtl());			
	    
			 return next;
         }

	    @Scheduled(cron = CRON_EXPRESSION)
	    public void run() throws RuntimeException{
	      
	    	try {
	    		
	    		 logger.debug("In run() method");
	    		 
	    		 Date nextdate=init();	
	    		 task.setTaskTtl(nextdate);
	    		 task.setUpdatedDt(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
	    		 taskSchedulerDao.save(task); 		 
	    	
	    	
	    	}catch(Exception e) {
	    		  e.printStackTrace();	    		
	    	}
	  }
	    
	    
	    /*Start of executing logic at once*/
		 /*@Bean
	     public TaskScheduler taskExecutor () {
	         return new ConcurrentTaskScheduler(
	                   Executors.newScheduledThreadPool(2));
	     }
		
		 
		 @Bean
		 public String getCronValue(){
			 logger.debug("In Schedular ");			 
			 TblSchedularTask task=taskSchedulerDao.getTaskSchedular();
			 
				 String df="";
				 try{
					 if(task!=null){
						 DateFormat formatter = new SimpleDateFormat("ss mm hh d M ?");
							formatter.setTimeZone(TimeZone.getTimeZone("IST"));
						 df = formatter.format(task.getTaskTtl());
						 logger.debug("In Schedular df",df);
						 task.setTaskTtl(DateUtils.addDays(task.getTaskTtl(), 1));
						 task.setUpdatedDt(new Date(System.currentTimeMillis()));
						 taskSchedulerDao.save(task); 				
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
		
		*//*end of executing logic at once*/
		 
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

	
	 
	 
	 
	 
	 
	 
	



}
