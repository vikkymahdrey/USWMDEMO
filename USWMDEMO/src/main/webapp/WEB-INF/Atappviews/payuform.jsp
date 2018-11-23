<%@page import="com.team.app.exception.AtAppException"%>
<%@ page import="java.util.*"%>
<%@ page import="java.security.*"%>
<%@ page import="com.team.app.constant.*"%>
<%@ page import="com.team.app.*"%>
<%@ page errorPage="error.jsp" %> 


<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Pay Bills</title>

<script type="text/javascript" src="js/jquery-latest.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="js/bootstrap.min.js"></script>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/custom_siemens.css" rel="stylesheet">
<!-- <link href="css/styling.css" rel="stylesheet"> -->
<link href="css/marquees.css" rel="stylesheet">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="css/AdminLTE.min.css">
<link rel="stylesheet" href="css/AdminLTE.css">
<link rel="stylesheet" href="css/skins/_all-skins.min.css">
<link rel="stylesheet" href="css/modal1.css" type="text/css" />
<link rel="stylesheet" href="css/modal.css" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />


<script src="js/app.min.js"></script>
<script src="js/demo.js"></script>

</head>


<%!
public boolean empty(String s)
	{
		if(s== null || s.trim().equals(""))
			return true;
		else
			return false;
	}
%>
<%!
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
			
		}catch(NoSuchAlgorithmException nsae){ }
		
		return hexString.toString();


	}
%>
<% 
	
	String merchant_key=AppConstants.merchant_key;
	String salt=AppConstants.salt;	
	String base_url=AppConstants.base_url;
	String surl=AppConstants.surl;
	String furl=AppConstants.furl;
	String action1 ="";
	int error=0;
	String hashString="";	
 

	
	Enumeration paramNames = request.getParameterNames();
	Map<String,String> params= new HashMap<String,String>();
    	while(paramNames.hasMoreElements()) 
	{
      		String paramName = (String)paramNames.nextElement();
      
      		String paramValue = request.getParameter(paramName);

		params.put(paramName,paramValue);
	}
	String txnid ="";
	String udf2="";
	if(empty(params.get("txnid"))){
		Random rand = new Random();
		String rndm = Integer.toString(rand.nextInt())+(System.currentTimeMillis() / 1000L);
		txnid=hashCal("SHA-256",rndm).substring(0,20);
	}
	else
		txnid=params.get("txnid");
	udf2=txnid;
	String txn="SWC";
	String hash="";
	String hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
	if(empty(params.get("hash")) && params.size()>0)
	{
		if( empty(params.get("key"))
			|| empty(params.get("txnid"))
			|| empty(params.get("amount"))
			|| empty(params.get("firstname"))
			|| empty(params.get("email"))
			|| empty(params.get("phone"))
			|| empty(params.get("productinfo")) )
			//|| empty(params.get("surl"))
			//|| empty(params.get("furl"))	)
			
			error=1;
		else{
			String[] hashVarSeq=hashSequence.split("\\|");
			
			for(String part : hashVarSeq)
			{
				hashString= (empty(params.get(part)))?hashString.concat(""):hashString.concat(params.get(part));
				hashString=hashString.concat("|");
			}
			hashString=hashString.concat(salt);
			

			 hash=hashCal("SHA-512",hashString);
			action1=base_url.concat("/_payment");
		}
	}
	else if(!empty(params.get("hash")))
	{
		hash=params.get("hash");
		action1=base_url.concat("/_payment");
	}
		

%>

<script>
$(document).ready(function(){
   
        $("#myModal").modal();
   
});
</script>

<script type="text/javascript">
var hash='<%= hash %>';
function submitPayubillForm() {	
	
	if (hash == '')
		return;
	
      var payubillForm = document.forms.payubillForm;
      payubillForm.submit(); 
    }
    


function confirmValidate(){
	   
	    var productinfo=document.getElementById("productinfo").value;
	    var amount=document.getElementById("amount").value;
	  	var email=document.getElementById("email").value;
	  	var phone=document.getElementById("phone").value;  	
	  	
	  	var emailRegx = /^((([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/;
	   	var phoneRegx = /^\+?\d?\d?\d{10}$/;
	   	var amountRegx=/^\d*[1-9]\d*$/;
		
	   	   
	   if(productinfo=="NA"){
		   alert("Please select a water meter !");
		   document.getElementById("productinfo").focus();
		   return false;
	   }else if(amount==""){
		   alert("Please enter the amount!");
		   document.getElementById("amount").focus();
		   return false;
	   } else if(amountRegx.test(amount)==false){
		   alert("Invalid amount entered!");
		   document.getElementById("amount").focus();
		   return false;
	   }else if(emailRegx.test(email)==false){
		   alert("Invalid email format!");
		   document.getElementById("email").focus();
		   return false;
	   }else if(phoneRegx.test(phone)==false){
		   alert("Invalid mobile number!");
		   document.getElementById("phone").focus();
		   return false;
	   }
}	   
</script>

<body class="hold-transition skin-blue sidebar-mini"
	onload="submitPayubillForm();">
	<% List<UserDeviceMapping> deviceList=(List<UserDeviceMapping>)request.getAttribute("deviceList");	
		List<TblPaymentInfo> paymentHistory=(List<TblPaymentInfo>)request.getAttribute("paymentHistory");%>
	<div class="wrapper">
		<%@include file="Header.jsp"%>

		<div class="content-wrapper">

			<section class="content">
				<div class="content-wrap box box-primary">


					<div class="row">
						<div class="col-sm-12 text-right">
							<img src="images/user_iocn_header.png" />&nbsp;<b>Welcome <%=userSession.getUname()%></b>
						</div>

					</div>

					<div class="box-header with-border">
						<h5 class="text-blue text-left ">
							<span class="fa fa-paypal"></span><b> Payment History</b>
						</h5>

					</div>
					<!-- /.box-header -->

					<div class="row">
						<div class="col-sm-12 text-center">
							<%String message="";
						
										try{
											message=request.getParameter("message");
											if(message!=null&&!message.equals("")){
												
											}else{						
													message = "";
													message = request.getAttribute("status").toString();
													session.setAttribute("status", "");
											}
				
										}catch(Exception e)
										{
											;
										}	%>
							<span style="color: red;"><%=message %></span>

						</div>
					</div>
					
					
					<%if(paymentHistory!=null && !paymentHistory.isEmpty()){
				for(TblPaymentInfo payInfo : paymentHistory) {
				 %>
					<div class="row">
						<div class="col-sm-9 mar-top-20" style="border:1px solid black;">

							<table class="table table-condensed table-striped " >

								<thead >
									<tr>
										<th>Transaction ID: <%=payInfo.getTxnid()%></th>
										<th>Date:  <%=payInfo.getCreatedOn()%></th>										
									</tr>
								<thead>
								<tbody>

									<tr>
										<td>PaymentID:</td>
										<td><%=payInfo.getPaymentId()%></td>
										<!-- <td rowspan="10" style="vertical-align:middle">
							              <button class="btn btn-primary" type="button">Leave Feedback	</button>
							            </td> -->
									</tr>
									<tr>
										<td>Mode:</td>
										<td><%=payInfo.getMode()%></td>
									</tr>
									<tr>
										<td>FirstName:</td>
										<td><%=payInfo.getFirstname()%></td>
									</tr>
									<tr>
										<td>Amount:</td>
										<td><%=payInfo.getAmount()%></td>
									</tr>
									<!-- <tr>
									<td align="right"><button class="form-control text-bold" type="button">Leave Feedback	</button></td>
									</tr> -->
									<tr>
										<td>Cardnum:</td>
										<td><%=payInfo.getCardnum()%></td>
									</tr>
									<tr>
										<td>EmailId:</td>
										<td><%=payInfo.getEmailId()%></td>
									</tr>
									<tr>
										<td>WaterMeter:</td>
										<td><%=payInfo.getDevEUI()%></td>
									</tr>
									<tr>
										<td>Productinfo:</td>
										<td><%=payInfo.getProductInfo()%></td>
									</tr>
									<tr>
										<td>Phone:</td>
										<td><%=payInfo.getPhoneno()%></td>
									</tr>
									<tr>
										<td>Status:</td>
										<td><%=payInfo.getStatus()%></td>
									</tr>
									
								</tbody>
								
							</table>
						</div>
					</div>

					<%
						}
					}
					%>
					
					

					<div class="container">

						<!-- Modal -->
						<div class="modal fade" id="myModal" role="dialog">
							<div class="modal-dialog">
								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header" style="padding: 15px 30px;">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4>
											<span class="fa fa-paypal"></span> PayU
										</h4>
									</div>

									<div class="modal-body" style="padding: 40px 50px;">

										<form action="<%= action1 %>" method="post"
											name="payubillForm" onsubmit="return confirmValidate();">

											<input type="hidden" name="key" value="<%= merchant_key %>" />
											<input type="hidden" name="hash" value="<%= hash %>" /> <input
												type="hidden" name="txnid" value="<%= txnid %>" /> <input
												type="hidden" name="surl" value="<%= surl %>" /> <input
												type="hidden" name="firstname"
												value="<%= userSession.getUname() %>" /> <input
												type="hidden" name="furl" value="<%= furl %>" /> <input
												type="hidden" name="udf1"	value="<%= userSession.getId() %>" />


											<table>
												<tr>
													<td><fieldset class="fieldset">
															<legend class="legend"> Water Meter</legend>
															<select style="border: none" name="productinfo"
																id="productinfo">
																<% if(deviceList!=null && !deviceList.isEmpty()){
	              		   											for(UserDeviceMapping udm : deviceList){ %>
																		<option value="<%=udm.getDevEUI()%>"><%=udm.getDevNode()+"-"+udm.getDevEUI()%></option>
																<%}
		               											 }else{%>
																<option value="NA">Please Choose</option>
																<%}%>

															</select>
														</fieldset></td>
												</tr>


												<tr>
													<td><fieldset class="fieldset">
															<legend class="legend"> Amount</legend>
															<input style="border: none" id="amount" name="amount"
																value="<%= (empty(params.get("amount"))) ? "" : params.get("amount")%>" />
														</fieldset></td>
												</tr>

												<tr>
													<td><fieldset class="fieldset">
															<legend class="legend">Email</legend>
															<input style="border: none" id="email" name="email" readonly
																value="<%=userSession.getEmailId()%>" />
														</fieldset></td>
												</tr>


												<tr>
													<td><fieldset class="fieldset">
															<legend class="legend">Phone</legend>
															<input style="border: none" id="phone" name="phone" readonly
																value="<%= userSession.getContactnumber() %>" />
														</fieldset></td>
												</tr>


												<tr>
													<% if(empty(hash)){ %>
													<td align="center">
														<button type="submit" class="btn btn-success">
															Proceed</button>
													</td>
													<% } %>
												</tr>

											</table>
										</form>

									</div>
						<div class="modal-footer">
						
						
						
										<%@include file="Footer.jsp"%>

						</div>
								</div>

							</div>
						</div>
						<!-- End Modal here -->
						
		

					</div>
				</div>
			</section>
			<%@include file="Footer.jsp"%>
		</div>
	</div>



</body>
</html>
