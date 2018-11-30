package com.team.app.audit;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team.app.dao.DbAuditDao;
import com.team.app.dao.UserAuditDao;
import com.team.app.domain.ActionsAudit;
import com.team.app.domain.Area;
import com.team.app.domain.DatabaseAudit;
import com.team.app.domain.DownlinkQueue;
import com.team.app.domain.Landmark;
import com.team.app.domain.LoraFrame;
import com.team.app.domain.Place;
import com.team.app.domain.Role;
import com.team.app.domain.TblDeviceFeedback;
import com.team.app.domain.TblDeviceFeedbackType;
import com.team.app.domain.TblDownlinkHoulyConfig;
import com.team.app.domain.TblDownlinkPacketConfig;
import com.team.app.domain.TblKeyword;
import com.team.app.domain.TblKeywordType;
import com.team.app.domain.TblPaymentInfo;
import com.team.app.domain.TblSchedularTask;
import com.team.app.domain.TblUnizenKeyConfig;
import com.team.app.domain.TblUserInfo;
import com.team.app.domain.UserDeviceMapping;
import com.team.app.logger.AtLogger;
import com.team.app.utils.DateUtil;

@Aspect
@Component
public class UserActionAudit {
	
	private static final AtLogger logger = AtLogger.getLogger(UserActionAudit.class);

	@Autowired
	private UserAuditDao userAuditDao;

	@Autowired
	private DbAuditDao dbauditdao;

	public static int auditid = 0;

	@SuppressWarnings("unused")
	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object urlAccessed(ProceedingJoinPoint jp) {
		logger.debug("In Aspect");
	
		String user = null, url = "", ipaddress = "";
		Object returnobj = null;
		Object returnType = null;
		Date responsetime;
		Date requesttime = DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss");
		ActionsAudit actionsAudit = new ActionsAudit();
		HttpServletRequest servletRequest=null;
		
			RequestMapping requestMapping = ((MethodSignature) jp.getSignature()).getMethod()
					.getAnnotation(RequestMapping.class);
			
			for (Object o : jp.getArgs()) {
				if (o instanceof HttpServletRequest) {
					 servletRequest = (HttpServletRequest) o;
					 ipaddress = (servletRequest.getRemoteAddr());
					 HttpSession session =null;
						session=servletRequest.getSession();		
										
					try{
						/*Session checker*/
						if (session != null ) {
							user = (((TblUserInfo) session.getAttribute("user")).getId());						
						}else {
							user = "NO SESSION FOUND";
						}
					
					}catch(Exception e){
							;
					}
					
				}
			}
			
			for (String annotationvalue : requestMapping.value()) {

				url = annotationvalue;

			}

			try {
				logger.debug("Printing Signature : ",jp.getSignature().toString());
				
				if(jp.getSignature().toString().contains("ModelAndView")){
					returnType=new ModelAndView("redirect:/");
				}else{
					returnType="redirect:/";
				}
				
				returnobj = jp.proceed();				
				
				responsetime = DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss");				
				actionsAudit.setRequestTime(requesttime);
				actionsAudit.setResponseTime(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
					if (user != null) {
						actionsAudit.setUserid(Integer.parseInt(user));
					}
				actionsAudit.setUrl(url);
				actionsAudit.setIpaddress(ipaddress);			
				auditid = ((ActionsAudit) userAuditDao.save(actionsAudit)).getId();				
				
			} catch (Throwable e){
				logger.debug("In catch Audit log");
				actionsAudit = new ActionsAudit();
				actionsAudit.setRequestTime(requesttime);
				actionsAudit.setUrl(url);
				actionsAudit.setIpaddress(ipaddress);
				actionsAudit.setException(e.toString()+" Line "+Thread.currentThread().getStackTrace()[1].getLineNumber());
				ActionsAudit auditAction=userAuditDao.save(actionsAudit);
				auditid = auditAction.getId();								
					        	
				//returnobj=returnType;		
			}			

		return returnobj;

	}

	
	@AfterThrowing(pointcut="within(com.team.app..*)",throwing="ex")
	public void exceptionsAudit(Exception ex){		
		logger.debug("Exception in AfterThrowing",ex);		
	}
	
	
	@Around("execution(* com.team.app.dao.*.save(..))")
	public Object dabaseCrudAuditSave(ProceedingJoinPoint jp) {

		DatabaseAudit dataaudit = new DatabaseAudit();
		Object returnObject = null;

		try {
			returnObject = jp.proceed();
			
						if (returnObject instanceof Area) {
				String rowid = ((Area)returnObject).getId();
							dataaudit = new DatabaseAudit();
				
				dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
				dataaudit.setAction_type("SAVE");
				dataaudit.setAuditid(auditid);
				dataaudit.setTablename("Area");
				dataaudit.setUpdatedrow(rowid);
				dbauditdao.save(dataaudit);
				
			} else if (returnObject instanceof TblDeviceFeedback) {
				String rowid = ((TblDeviceFeedback)returnObject).getId();
				dataaudit = new DatabaseAudit();
	            
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("TblDeviceFeedback");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

				
			} else if (returnObject instanceof TblDeviceFeedbackType) {
				String rowid = ((TblDeviceFeedbackType)returnObject).getId();
				dataaudit = new DatabaseAudit();
	           
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("TblDeviceFeedbackType");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

			} else if (returnObject instanceof TblDownlinkHoulyConfig) {
				
				String rowid = ((TblDownlinkHoulyConfig)returnObject).getId();
				dataaudit = new DatabaseAudit();
	            
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("TblDownlinkHoulyConfig");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

				
			} else if (returnObject instanceof TblDownlinkPacketConfig) {
				
				String rowid = ((TblDownlinkPacketConfig)returnObject).getId();
				dataaudit = new DatabaseAudit();
	          
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("TblDownlinkPacketConfig");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

				
			} else if (returnObject instanceof TblKeyword) {
				
				String rowid = ((TblKeyword)returnObject).getId();
				dataaudit = new DatabaseAudit();
	          
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("TblKeyword");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

				
			} else if (returnObject instanceof TblKeywordType) {
			

				String rowid = ((TblKeywordType)returnObject).getId();
				dataaudit = new DatabaseAudit();
	           
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("TblKeywordType");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

				
			} else if (returnObject instanceof TblUnizenKeyConfig) {
				
				String rowid = ((TblUnizenKeyConfig)returnObject).getId();
				dataaudit = new DatabaseAudit();
	           
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("TblUnizenKeyConfig");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

				
			} else if (returnObject instanceof TblPaymentInfo) {
			
				String rowid = ((TblPaymentInfo)returnObject).getId();
				dataaudit = new DatabaseAudit();
	            
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("TblPaymentInfo");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

				
			} else if (returnObject instanceof TblSchedularTask) {
				String rowid = ((TblSchedularTask)returnObject).getId();
				dataaudit = new DatabaseAudit();
	           
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("TblSchedularTask");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

			} else if (returnObject instanceof TblUnizenKeyConfig) {
				String rowid = ((TblUnizenKeyConfig)returnObject).getId();
				dataaudit = new DatabaseAudit();
	        
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("TblUnizenKeyConfig");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

			} else if (returnObject instanceof TblUserInfo) {
				String rowid = ((TblUserInfo)returnObject).getId();
				dataaudit = new DatabaseAudit();
	        
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("TblUserInfo");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

			} else if (returnObject instanceof UserDeviceMapping) {
				String rowid = ((UserDeviceMapping)returnObject).getId();
				dataaudit = new DatabaseAudit();
	          
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("UserDeviceMapping");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

			} else if (returnObject instanceof DownlinkQueue) {
				String rowid = ((DownlinkQueue)returnObject).getId();
				dataaudit = new DatabaseAudit();
	           
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("DownlinkQueue");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

			} else if (returnObject instanceof Landmark) {
				String rowid = ((Landmark)returnObject).getId();
				dataaudit = new DatabaseAudit();
	          
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("Landmark");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

			} else if (returnObject instanceof LoraFrame) {
				String rowid = ((LoraFrame)returnObject).getId();
				dataaudit = new DatabaseAudit();
	           
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("LoraFrame");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

			} else if (returnObject instanceof Place) {
				String rowid = ((Place)returnObject).getId();
				dataaudit = new DatabaseAudit();
	        
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("Place");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);

			} else if (returnObject instanceof Role) {
				String rowid = ((Role)returnObject).getId();
				dataaudit = new DatabaseAudit();
	          
	            dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
             	dataaudit.setAction_type("SAVE");
             	dataaudit.setAuditid(auditid);
             	dataaudit.setTablename("Role");
              	dataaudit.setUpdatedrow(rowid);
	            dbauditdao.save(dataaudit);
			
			}

		} catch (Throwable e) {

			// e.printStackTrace();
		}
		return returnObject;
	}

	@Before("execution(* com.team.app.dao.*.*pdate*(..))")
	public void dabaseCrudAuditUpdate(JoinPoint jp) {

		StringBuilder updateargs = new StringBuilder();
		String updateNativeQuery = null;
			
		Query queryannotation = null;
		try {

			try {

				queryannotation = ((MethodSignature) jp.getSignature()).getMethod().getAnnotation(Query.class);

			} catch (Exception excep) {
				DatabaseAudit dataaudit = new DatabaseAudit();
				dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
				dataaudit.setExceptionifany(excep.toString());
				dataaudit.setAuditid(auditid);
				dbauditdao.save(dataaudit);

			}

			updateNativeQuery = queryannotation.value();

			for (Object eachargval : jp.getArgs()) {

				if (eachargval instanceof String) {

					updateargs.append(" & " + ((String) eachargval));

				} else if (eachargval instanceof Integer) {

					updateargs.append(" & " + ((Integer) eachargval));

				} else if (eachargval instanceof Date) {

					updateargs.append(" & " + ((Date) eachargval));

				}

			}

		
			DatabaseAudit dataaudit = new DatabaseAudit();
			dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
			dataaudit.setUpdateNativeQuery(updateNativeQuery);
			dataaudit.setUpdateparams(updateargs.toString());
			dataaudit.setAction_type("UPDATE");
			dataaudit.setAuditid(auditid);
			dbauditdao.save(dataaudit);
		} catch (Exception excep) {

			DatabaseAudit dataaudit = new DatabaseAudit();
			dataaudit.setTimeofaction(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
			dataaudit.setExceptionifany(excep.toString());
			dataaudit.setAuditid(auditid);
			dbauditdao.save(dataaudit);
		}
	}

	@Before("execution(* com.team.app.dao.*.delete(..))")
	public void dabaseCrudAuditDelete(JoinPoint jp) {


		DatabaseAudit dataaudit = new DatabaseAudit();
		dataaudit.setTablename("sample");
		dbauditdao.save(dataaudit);
	}

	@Before("execution(* com.team.app.dao.*.get*(..))")
	public void dabaseCrudAuditGet(JoinPoint jp) {

		// jp.get
		// DatabaseAudit dataaudit = new DatabaseAudit();
		// dataaudit.setTablename("sample");
		// dbauditdao.save(dataaudit);
	}

}
