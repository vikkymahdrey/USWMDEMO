package com.team.app.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.app.dao.PaymentBillDao;
import com.team.app.domain.TblPaymentInfo;
import com.team.app.logger.AtLogger;
import com.team.app.service.PaymentBillService;

@Service
public class PaymentBillServiceImpl implements PaymentBillService{
	private static final AtLogger logger = AtLogger.getLogger(PaymentBillServiceImpl.class);
	
	@Autowired
	private PaymentBillDao paymentBillDao;
	
	private Integer error;
	
    

    public String hashCal(String type,String str){
		byte[] hashseq=str.getBytes();
		StringBuffer hexString = new StringBuffer();
		try{
			MessageDigest algorithm = MessageDigest.getInstance(type);
			algorithm.reset();
			algorithm.update(hashseq);
			byte messageDigest[] = algorithm.digest();         
			
	
			for (int i=0;i<messageDigest.length;i++) {
				String hex=Integer.toHexString(0xFF & messageDigest[i]);
				if(hex.length()==1) hexString.append("0");
				hexString.append(hex);
			}				
		}catch(NoSuchAlgorithmException nsae){ 
			;
		}
		
		return hexString.toString();
	}

    

	
	public TblPaymentInfo updatePaymentInfo(TblPaymentInfo payment) throws Exception {
			return paymentBillDao.save(payment);
	}
	
	
	public boolean empty(String s)
	{
		if(s== null || s.trim().equals(""))
			return true;
		else
			return false;
	}




	

	
}
