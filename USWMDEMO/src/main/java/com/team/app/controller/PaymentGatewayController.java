package com.team.app.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team.app.constant.AppConstants;
import com.team.app.domain.TblPaymentInfo;
import com.team.app.domain.TblUserInfo;
import com.team.app.domain.UserDeviceMapping;
import com.team.app.logger.AtLogger;
import com.team.app.service.PaymentBillService;
import com.team.app.service.UserLoginService;
import com.team.app.utils.DateUtil;

@Controller
@SessionAttributes({"status"})
public class PaymentGatewayController {
	
	private static final AtLogger logger = AtLogger.getLogger(PaymentGatewayController.class);
	
	@Autowired
	private PaymentBillService paymentBillService;
	
	@Autowired
	private UserLoginService userLoginService;
	
		
	@RequestMapping(value= {"/payUBills"}, method={RequestMethod.GET,RequestMethod.POST})
	public String payUBillsHandler(HttpServletRequest request, HttpSession session, Map<String, Object> map){
		logger.debug("In /payUBill");	
		try{
				TblUserInfo user=(TblUserInfo) session.getAttribute("user");
				
				TblUserInfo u=userLoginService.getUserByUserId(user.getId());
				
				List<UserDeviceMapping> deviceList=u.getUserDeviceMappings();				
				   map.put("deviceList", deviceList);	
					
				List<TblPaymentInfo> paymentHistory=u.getTblPaymentInfos();
					logger.debug("In /paymentHistory",paymentHistory.size());	
				
					map.put("paymentHistory", paymentHistory);					
					
						return "payuform";
		}catch(Exception e){	
			e.printStackTrace();
			HttpSession s=request.getSession();
		    s.setAttribute("statusLog",AppConstants.statusLog);
			s.setAttribute("url", request.getRequestURL());
			s.setAttribute("exception", e.toString());				
			s.setAttribute("className",Thread.currentThread().getStackTrace()[1].getClassName());
			s.setAttribute("methodName",Thread.currentThread().getStackTrace()[1].getMethodName());
			s.setAttribute("lineNumber",Thread.currentThread().getStackTrace()[1].getLineNumber());		       
		    return "redirect:/exception";
	    }
		
	}
	
	/*@RequestMapping(value= {"/payBillForm"}, method=RequestMethod.POST)
	public String payBillsFormHandler(HttpServletRequest request, HttpSession session, HttpServletResponse response,Map<String, Object> map,RedirectAttributes redirectAttributes) throws ServletException, IOException{
		logger.debug("In /payBillForm");
		Map<String, String> values = paymentBillServiceImpl.hashCalMethod(request, response);
		String action=values.get("action");
		String key=values.get("key").trim();
		String hash=values.get("hash").trim();
		String txnid=values.get("txnid").trim();
		String amount=values.get("amount").trim();
		String firstname=values.get("firstname").trim();
		String email=values.get("email").trim();
		String phone=values.get("phone").trim();
		String productinfo=values.get("productinfo").trim();
		String surl=values.get("surl").trim();
		String furl=values.get("furl").trim();
		String lastname=values.get("lastname").trim();
		String curl=values.get("curl").trim();
		String address1=values.get("address1").trim();
		String address2=values.get("address2").trim();
		String city=values.get("city").trim();
		String state=values.get("state").trim();
		String country=values.get("country").trim();
		String zipcode=values.get("zipcode").trim();
		String udf1=values.get("udf1").trim();
		String udf2=values.get("udf2").trim();
		String udf3=values.get("udf3").trim();
		String udf4=values.get("udf4").trim();
		String udf5=values.get("udf5").trim();
		String pg=values.get("pg").trim();
		
		logger.debug("action : ",action);
		logger.debug("key : ",key);
		logger.debug("hash : ",hash);
		logger.debug("txnid : ",txnid);
		logger.debug("amount : ",amount);
		logger.debug("firstname : ",firstname);
		logger.debug("email : ",email);
		logger.debug("phone : ",phone);
		logger.debug("productinfo : ",productinfo);
		logger.debug("surl : ",surl);
		logger.debug("furl : ",furl);
		logger.debug("lastname : ",lastname);
		logger.debug("curl : ",curl);
		logger.debug("address1 : ",address1);
		logger.debug("address2 : ",address2);
		logger.debug("city : ",city);
		logger.debug("state : ",state);
		logger.debug("country : ",country);
		logger.debug("zipcode : ",zipcode);
		logger.debug("udf1 : ",udf1);
		logger.debug("udf2 : ",udf2);
		logger.debug("udf3 : ",udf3);
		logger.debug("udf4 : ",udf4);
		logger.debug("udf5 : ",udf5);
		logger.debug("pg : ",pg);
		
		map.put("action",action);
		map.put("key",key);
		map.put("hash",hash);
		map.put("txnid",txnid);
		map.put("amount",amount);
		map.put("firstname",firstname);
		map.put("email",email);
		map.put("phone",phone);
		map.put("productinfo",productinfo);
		map.put("surl",surl);
		map.put("furl",furl);
		map.put("lastname",lastname);
		map.put("curl",curl);
		map.put("address1",address1);
		map.put("address2",address2);
		map.put("city",city);
		map.put("state",state);
		map.put("country",country);
		map.put("zipcode",zipcode);
		map.put("udf1",udf1);
		map.put("udf2",udf2);
		map.put("udf3",udf3);
		map.put("udf4",udf4);
		map.put("udf5",udf5);
		map.put("pg",pg);
		
		
			return "PayUForm";
	}*/
	
	/*@RequestMapping(value= {"/paymentSuccess"}, method={RequestMethod.GET,RequestMethod.POST})
	public String paymentSuccessHandler(HttpServletRequest request, HttpSession session, HttpServletResponse response,Map<String, Object> map,RedirectAttributes redirectAttributes){
		logger.debug("In /paymentSuccess");	
		try{
			redirectAttributes.addFlashAttribute("status",
					"<div class=\"success\" > Redirecting url getting success result !</div>");
		}catch(Exception e){
			logger.error(e);
			redirectAttributes.addFlashAttribute("status",
					"<div class=\"failure\" >Exception in  success redirecting URL /paymentSuccess !</div>");
		}
			//return "redirect:/payUBills";
		//return "paymentsuccess";
		return "redirect:/paymentStatus";
	}*/
	
	
	@RequestMapping(value= {"/paymentStatus"}, method={RequestMethod.GET,RequestMethod.POST})
	public String paymentStatusHandler(HttpServletRequest request, HttpSession session, HttpServletResponse response,Map<String, Object> map,RedirectAttributes redirectAttributes) {
		logger.debug("In /paymentStatus");	
			try{
				 	String amount = request.getParameter("amount");
					String productinfo= request.getParameter("productinfo");
					String txnid = request.getParameter("txnid");
					String phone = request.getParameter("phone");
					String firstname = request.getParameter("firstname");
					String userId = request.getParameter("udf1");
					String key = AppConstants.merchant_key;
					String salt=AppConstants.salt;
					String status = request.getParameter("status");
					String r_h =request.getParameter("hash");
			        String hashStr="";
			      	String udfStr1 =request.getParameter("udf1");
			        String udfStr2 =request.getParameter("udf2");
			        String udfStr3 =request.getParameter("udf3");
			        String udfStr4 =request.getParameter("udf4");
			        String udfStr5 =request.getParameter("udf5"); 
			        String p_Id =request.getParameter("mihpayid");
			        
			        String cardnum =request.getParameter("cardnum");
			        String discount =request.getParameter("discount");
			        String errorCode =request.getParameter("error");
			        String errorMessage =request.getParameter("error_Message");
			        String mode =request.getParameter("mode"); 
			        String netAmountDebit =request.getParameter("net_amount_debit");
			        String nameOnCard =request.getParameter("name_on_card");
			        String retryCount =request.getParameter("retry_count");
			        String payuMoneyId =request.getParameter("payuMoneyId");
			        /*String city =request.getParameter("city");
			        String state =request.getParameter("state");
			        String country =request.getParameter("country");
			        String zipcode =request.getParameter("zipcode");
			        String address1 =request.getParameter("address1");
			        String address2 =request.getParameter("address2");
			        */			 
			        
			        String additionalCharges = request.getParameter("additionalCharges");
			        
			        logger.debug("Printing amount",amount);
			        logger.debug("Printing productinfo",productinfo);
			        logger.debug("Printing txnid",txnid);
			        logger.debug("Printing firstname",firstname);
			        logger.debug("Printing phone",phone);
			        logger.debug("Printing userId",userId);
			        logger.debug("Printing udfStr1",udfStr1);
			        logger.debug("Printing status",status);
			        logger.debug("Printing p_Id",p_Id);
			        logger.debug("Printing cardnum",cardnum);
			        logger.debug("Printing discount",discount);
			        logger.debug("Printing error_code",errorCode);
			        logger.debug("Printing error_message",errorMessage);
			        logger.debug("Printing mode",mode);
			        logger.debug("Printing netAmountDebit",netAmountDebit);
			        logger.debug("Printing name_on_card",nameOnCard);
			        logger.debug("Printing retry_count",retryCount);
			        logger.debug("Printing mode",payuMoneyId);
			        
			        
			        
			        
			        
			        //out.println("Your payment with <b>Payment ID</b> is :    " + p_Id + "    is    "+"<br>");
			        
			        TblPaymentInfo p=null;		        	
			        	
			        String hash1;
			        String email = request.getParameter("email");
			        if(status=="success"){
			        	logger.debug("In Success");
			        			        	
			                if(additionalCharges!=null){
			                	String hashSeq = additionalCharges+"|"+salt+"|"+status+"||||||"+udfStr5+"|"+udfStr4+"|"+udfStr3+"|"+udfStr2+"|"+udfStr1+"|"+email+"|"+firstname+"|"+productinfo+"|"+amount+"|"+txnid+"|";		
					
							hashStr=hashSeq.concat(key);
					                        	logger.debug("Printing hashStr",hashStr);
					                        hash1=paymentBillService.hashCal("SHA-512",hashStr);
					                        	logger.debug("Printing hash1",hash1);					                       
								if(r_h.equals(hash1)){
									logger.debug("Successfull with additional charges" + "<br>");
									logger.debug("Transaction details</b>:  " + "<br>");
									logger.debug("<b>Transaction Id</b>:    " + txnid+"<br>");
									logger.debug("<b>Product Info</b>:    " + productinfo+"<br>");
									logger.debug("<b>Amount</b>:    " + amount+"<br>");
									logger.debug("<b>additionalCharges</b>:    " + additionalCharges+"<br>");
									logger.debug("<b>Status of Transaction</b>:    " + status+"<br>");
									logger.debug("<b>Email</b>:    " + email+"<br>");
									logger.debug("<b>Phone</b>:    " + phone+"<br>");
					           }else{
					        	   logger.debug("<b>Transaction details</b>:  "+"<br>");
					        	   logger.debug("<b>Transaction Id</b>:    " + txnid+"<br>");
					        	   logger.debug("<b>Product Info</b>:    " + productinfo+"<br>");
					        	   logger.debug("<b>Amount</b>:    " + amount+"<br>");
					        	   logger.debug("<b>additionalCharges</b>:    " + additionalCharges+"<br>");
					        	   logger.debug("<b>Status of Transaction</b>:    " + status+"<br>");
					        	   logger.debug("<b>Email</b>:    " + email+"<br>");
					        	   logger.debug("<b>Phone</b>:    " + phone+"<br>");
			                       }
								
			                }else{
			                	String hashSeq = salt+"|"+status+"||||||"+udfStr5+"|"+udfStr4+"|"+udfStr3+"|"+udfStr2+"|"+udfStr1+"|"+email+"|"+firstname+"|"+productinfo+"|"+amount+"|"+txnid+"|";
					
										hashStr=hashSeq.concat(key);
										logger.debug("hashStr",hashStr);
											hash1=paymentBillService.hashCal("SHA-512",hashStr);				                       
										logger.debug("hash1",hash1);
									if(r_h.equals(hash1)){
										logger.debug("Successfull"+"<br>");
										logger.debug("<b>Transaction details</b>:  "+"<br>");
										logger.debug("<b>Transaction Id</b>:    " + txnid+"<br>");
										logger.debug("<b>Product Info</b>:    " + productinfo+"<br>");
										logger.debug("<b>Amount</b>:    " + amount+"<br>");
										logger.debug("<b>additionalCharges</b>:    " + additionalCharges+"<br>");
										logger.debug("<b>Status of Transaction</b>:    " + status+"<br>");
										logger.debug("<b>Email</b>:    " + email+"<br>");
										logger.debug("<b>Phone</b>:    " + phone+"<br>");
			                            
			                            }else{                             	
			                            	logger.debug("failure"+"<br>");
			                            	logger.debug("<b>Transaction details</b>:  "+"<br>");
			                            	logger.debug("<b>Transaction Id</b>:    " + txnid+"<br>");
			                            	logger.debug("<b>Product Info</b>:    " + productinfo+"<br>");
			                            	logger.debug("<b>Amount</b>:    " + amount+"<br>");
			                            	logger.debug("<b>additionalCharges</b>:    " + additionalCharges+"<br>");
			                            	logger.debug("<b>Status of Transaction</b>:    " + status+"<br>");
			                            	logger.debug("<b>Email</b>:    " + email+"<br>");
			                            	logger.debug("<b>Phone</b>:    " + phone+"<br>");
			                            }
			                }	
			                
			                	        			
			        			
			             //Success ended here   
			        }else{
				        	logger.debug("<b>Transaction details</b>:  "+"<br>");
				            logger.debug("<b>Transaction Id</b>:    " + txnid+"<br>");
				        	logger.debug("<b>Product Info</b>:    " + productinfo+"<br>");
				        	logger.debug("<b>Amount</b>:    " + amount+"<br>");
				        	logger.debug("<b>additionalCharges</b>:    " + additionalCharges+"<br>");
				        	logger.debug("<b>Status of Transaction</b>:    " + status+"<br>");
				        	logger.debug("<b>Email</b>:    " + email+"<br>");
				        	logger.debug("<b>Phone</b>:    " + phone+"<br>");
				        	
				        	redirectAttributes.addFlashAttribute("status",
		        					"<div class=\"success\" > System has failed !</div>");
			        }
			        
			        
			        TblUserInfo user=userLoginService.getUserByUId(udfStr1);
	                p= new TblPaymentInfo();
	        			p.setPaymentId(p_Id);
	        			p.setTxnid(txnid);
	        			p.setProductInfo(AppConstants.txnMode);
	        			p.setAmount(amount);
	        			p.setAdditionalCharges(additionalCharges);
	        			p.setStatus(status);
	        			p.setDevEUI(productinfo);
	        			p.setEmailId(email);
	        			p.setPhoneno(phone);
	        			p.setTblUserInfo(user);
	        			p.setFirstname(firstname);
	        			p.setMode(mode);
	        			p.setDiscount(discount);
	        			p.setNameOnCard(nameOnCard);
	        			p.setErrorCode(errorCode);
	        			p.setErrorMessage(errorMessage);
	        			p.setPayuMoneyId(payuMoneyId);
	        			p.setRetryCount(retryCount);
	        			p.setNetAmountDebit(netAmountDebit);
	        			p.setCardnum(cardnum);
	        			p.setCreatedOn(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
	        			p.setUpdateddt(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
	        			
	        			TblPaymentInfo pay=paymentBillService.updatePaymentInfo(p);
	        			if(pay!=null){
	        				map.put("paymentInfo", pay);
	        				redirectAttributes.addFlashAttribute("status",
		        					"<div class=\"success\" > Payment information saved successfully !</div>");
	        			}else{
	        				redirectAttributes.addFlashAttribute("status",
		        					"<div class=\"success\" > System not persisted payment information !</div>");
	        			}
			
	        			return "paymentsuccess";
		
		}catch(Exception e){
			e.printStackTrace();
			HttpSession s=request.getSession();
		    s.setAttribute("statusLog",AppConstants.statusLog);
			s.setAttribute("url", request.getRequestURL());
			s.setAttribute("exception", e.toString());				
			s.setAttribute("className",Thread.currentThread().getStackTrace()[1].getClassName());
			s.setAttribute("methodName",Thread.currentThread().getStackTrace()[1].getMethodName());
			s.setAttribute("lineNumber",Thread.currentThread().getStackTrace()[1].getLineNumber());		       
		    return "redirect:/exception";
	    }
		
		
	}
	
	
	
	@RequestMapping(value= {"/paymentFailure"}, method={RequestMethod.GET,RequestMethod.POST})
	public String paymentFailureHandler(HttpServletRequest request, HttpSession session, HttpServletResponse response,Map<String, Object> map,RedirectAttributes redirectAttributes){
		logger.debug("In /paymentFailure");	
			try{
		     	 	String amount = request.getParameter("amount");
					String productinfo= request.getParameter("productinfo");
					String txnid = request.getParameter("txnid");
					String phone = request.getParameter("phone");
					String firstname = request.getParameter("firstname");
					String userId = request.getParameter("udf1");
					String key = AppConstants.merchant_key;
					String salt=AppConstants.salt;
					String status = request.getParameter("status");
					String r_h =request.getParameter("hash");
			        String hashStr="";
			      	String udfStr1 =request.getParameter("udf1");
			        String udfStr2 =request.getParameter("udf2");
			        String udfStr3 =request.getParameter("udf3");
			        String udfStr4 =request.getParameter("udf4");
			        String udfStr5 =request.getParameter("udf5"); 
			        String p_Id =request.getParameter("mihpayid");
			        
			        String cardnum =request.getParameter("cardnum");
			        String discount =request.getParameter("discount");
			        String errorCode =request.getParameter("errorCode");
			        String errorMessage =request.getParameter("errorMessage");
			        String mode =request.getParameter("mode"); 
			        String netAmountDebit =request.getParameter("netAmountDebit");
			        String nameOnCard =request.getParameter("nameOnCard");
			        String retryCount =request.getParameter("retryCount");
			        String payuMoneyId =request.getParameter("payuMoneyId");
			 
			        
			        String additionalCharges = request.getParameter("additionalCharges");
			        
			        logger.debug("Printing amount",amount);
			        logger.debug("Printing productinfo",productinfo);
			        logger.debug("Printing txnid",txnid);
			        logger.debug("Printing firstname",firstname);
			        logger.debug("Printing phone",phone);
			        logger.debug("Printing userId",userId);
			        logger.debug("Printing udfStr1",udfStr1);
			        logger.debug("Printing status",status);
			        logger.debug("Printing p_Id",p_Id);
			        logger.debug("Printing cardnum",cardnum);
			        logger.debug("Printing discount",discount);
			        logger.debug("Printing error_code",errorCode);
			        logger.debug("Printing error_message",errorMessage);
			        logger.debug("Printing mode",mode);
			        logger.debug("Printing netAmountDebit",netAmountDebit);
			        logger.debug("Printing name_on_card",nameOnCard);
			        logger.debug("Printing retry_count",retryCount);
			        logger.debug("Printing mode",payuMoneyId);
			        
			        
			        
			        
			        
			        //out.println("Your payment with <b>Payment ID</b> is :    " + p_Id + "    is    "+"<br>");
			        
			        TblPaymentInfo p=null;		        	
			        	
			        String hash1;
			        String email = request.getParameter("email");
			        if(status=="failure"){
			        	logger.debug("In failure");
			        			        	
			                if(additionalCharges!=null){
			                	String hashSeq = additionalCharges+"|"+salt+"|"+status+"||||||"+udfStr5+"|"+udfStr4+"|"+udfStr3+"|"+udfStr2+"|"+udfStr1+"|"+email+"|"+firstname+"|"+productinfo+"|"+amount+"|"+txnid+"|";		
					
							hashStr=hashSeq.concat(key);
					                        	logger.debug("Printing hashStr",hashStr);
					                        hash1=paymentBillService.hashCal("SHA-512",hashStr);
					                        	logger.debug("Printing hash1",hash1);					                       
								if(r_h.equals(hash1)){
									logger.debug("Successfull with additional charges" + "<br>");
									logger.debug("Transaction details</b>:  " + "<br>");
									logger.debug("<b>Transaction Id</b>:    " + txnid+"<br>");
									logger.debug("<b>Product Info</b>:    " + productinfo+"<br>");
									logger.debug("<b>Amount</b>:    " + amount+"<br>");
									logger.debug("<b>additionalCharges</b>:    " + additionalCharges+"<br>");
									logger.debug("<b>Status of Transaction</b>:    " + status+"<br>");
									logger.debug("<b>Email</b>:    " + email+"<br>");
									logger.debug("<b>Phone</b>:    " + phone+"<br>");
					           }else{
					        	   logger.debug("<b>Transaction details</b>:  "+"<br>");
					        	   logger.debug("<b>Transaction Id</b>:    " + txnid+"<br>");
					        	   logger.debug("<b>Product Info</b>:    " + productinfo+"<br>");
					        	   logger.debug("<b>Amount</b>:    " + amount+"<br>");
					        	   logger.debug("<b>additionalCharges</b>:    " + additionalCharges+"<br>");
					        	   logger.debug("<b>Status of Transaction</b>:    " + status+"<br>");
					        	   logger.debug("<b>Email</b>:    " + email+"<br>");
					        	   logger.debug("<b>Phone</b>:    " + phone+"<br>");
			                       }
								
			                }else{
			                	String hashSeq = salt+"|"+status+"||||||"+udfStr5+"|"+udfStr4+"|"+udfStr3+"|"+udfStr2+"|"+udfStr1+"|"+email+"|"+firstname+"|"+productinfo+"|"+amount+"|"+txnid+"|";
					
										hashStr=hashSeq.concat(key);
										logger.debug("hashStr",hashStr);
											hash1=paymentBillService.hashCal("SHA-512",hashStr);				                       
										logger.debug("hash1",hash1);
									if(r_h.equals(hash1)){
										logger.debug("Successfull"+"<br>");
										logger.debug("<b>Transaction details</b>:  "+"<br>");
										logger.debug("<b>Transaction Id</b>:    " + txnid+"<br>");
										logger.debug("<b>Product Info</b>:    " + productinfo+"<br>");
										logger.debug("<b>Amount</b>:    " + amount+"<br>");
										logger.debug("<b>additionalCharges</b>:    " + additionalCharges+"<br>");
										logger.debug("<b>Status of Transaction</b>:    " + status+"<br>");
										logger.debug("<b>Email</b>:    " + email+"<br>");
										logger.debug("<b>Phone</b>:    " + phone+"<br>");
			                            
			                            }else{                             	
			                            	logger.debug("failure"+"<br>");
			                            	logger.debug("<b>Transaction details</b>:  "+"<br>");
			                            	logger.debug("<b>Transaction Id</b>:    " + txnid+"<br>");
			                            	logger.debug("<b>Product Info</b>:    " + productinfo+"<br>");
			                            	logger.debug("<b>Amount</b>:    " + amount+"<br>");
			                            	logger.debug("<b>additionalCharges</b>:    " + additionalCharges+"<br>");
			                            	logger.debug("<b>Status of Transaction</b>:    " + status+"<br>");
			                            	logger.debug("<b>Email</b>:    " + email+"<br>");
			                            	logger.debug("<b>Phone</b>:    " + phone+"<br>");
			                            }
			                }	
			                
			                	        			
			        			
			             //Success ended here   
			        }else{
				        	logger.debug("<b>Transaction details</b>:  "+"<br>");
				            logger.debug("<b>Transaction Id</b>:    " + txnid+"<br>");
				        	logger.debug("<b>Product Info</b>:    " + productinfo+"<br>");
				        	logger.debug("<b>Amount</b>:    " + amount+"<br>");
				        	logger.debug("<b>additionalCharges</b>:    " + additionalCharges+"<br>");
				        	logger.debug("<b>Status of Transaction</b>:    " + status+"<br>");
				        	logger.debug("<b>Email</b>:    " + email+"<br>");
				        	logger.debug("<b>Phone</b>:    " + phone+"<br>");
				        	
				        	redirectAttributes.addFlashAttribute("status",
		        					"<div class=\"success\" > System has failed !</div>");
			        }
			        
			        
			        TblUserInfo user=userLoginService.getUserByUId(userId);
	                p= new TblPaymentInfo();
	        			p.setPaymentId(p_Id);
	        			p.setTxnid(txnid);
	        			p.setProductInfo(AppConstants.txnMode);
	        			p.setAmount(amount);
	        			p.setAdditionalCharges(additionalCharges);
	        			p.setStatus(status);
	        			p.setDevEUI(productinfo);
	        			p.setEmailId(email);
	        			p.setPhoneno(phone);
	        			p.setTblUserInfo(user);
	        			p.setFirstname(firstname);
	        			p.setMode(mode);
	        			p.setDiscount(discount);
	        			p.setCardnum(cardnum);
	        			p.setCreatedOn(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
	        			p.setUpdateddt(DateUtil.getCurrentDateTimeIST("yyyy-MM-dd HH:mm:ss"));
	        			
	        			TblPaymentInfo pay=paymentBillService.updatePaymentInfo(p);
	        			if(pay!=null){
	        				map.put("paymentInfo", pay);
	        				redirectAttributes.addFlashAttribute("status",
		        					"<div class=\"success\" > Payment information saved successfully !</div>");
	        			}else{
	        				redirectAttributes.addFlashAttribute("status",
		        					"<div class=\"success\" > System not persisted payment information !</div>");
	        			}
			
	        			return "paymentsuccess";
			}catch(Exception e){
				e.printStackTrace();
				HttpSession s=request.getSession();
			    s.setAttribute("statusLog",AppConstants.statusLog);
				s.setAttribute("url", request.getRequestURL());
				s.setAttribute("exception", e.toString());				
				s.setAttribute("className",Thread.currentThread().getStackTrace()[1].getClassName());
				s.setAttribute("methodName",Thread.currentThread().getStackTrace()[1].getMethodName());
				s.setAttribute("lineNumber",Thread.currentThread().getStackTrace()[1].getLineNumber());		       
			    return "redirect:/exception";
		    }
		
		
	}
	
	
	

}
