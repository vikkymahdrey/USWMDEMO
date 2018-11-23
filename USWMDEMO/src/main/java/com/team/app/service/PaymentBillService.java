package com.team.app.service;

import com.team.app.domain.TblPaymentInfo;

public interface PaymentBillService {

	TblPaymentInfo updatePaymentInfo(TblPaymentInfo payment) throws Exception;
	String hashCal(String type,String str);
	boolean empty(String s);
	

}
