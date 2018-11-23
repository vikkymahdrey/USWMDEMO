<%@page import="java.security.MessageDigest"%>
<%@page import="java.security.NoSuchAlgorithmException"%>
<%@page import="com.team.app.domain.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<%@ page errorPage="error.jsp" %> 
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <title>Payment Details</title>    
	<script type="text/javascript" src="js/jquery-latest.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    
	  <link href="css/bootstrap.min.css" rel="stylesheet">
	  <link href="css/custom_siemens.css" rel="stylesheet">
	  <link href="css/marquees.css" rel="stylesheet">	
	        
    
   
	  <!-- Font Awesome -->
	  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
	  <!-- Ionicons -->
	  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
	  <link rel="stylesheet" href="css/AdminLTE.min.css">
	  <link rel="stylesheet" href="css/AdminLTE.css">
	  <link rel="stylesheet" href="css/skins/_all-skins.min.css"> 
	  <link href="css/style.css" rel="stylesheet" type="text/css" />
	 

	<script src="js/app.min.js"></script>
	<script src="js/demo.js"></script>
	

     
  </head>
    <body class="hold-transition skin-blue sidebar-mini">
    	<% TblPaymentInfo payInfo=(TblPaymentInfo)request.getAttribute("paymentInfo") ;%>
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
  					  <h5 class="text-blue text-left "><span class="fa fa-paypal"></span><b>Payment Status</b></h5>
       
   				</div><!-- /.box-header -->
		 							
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
				 						<span style="color: red;" ><%=message %></span>	
				 									
		   						</div>
		   				  </div>		
 <div class="row" >
  	<div class="col-sm-12">	
  	<%if(payInfo!=null){ %>
   	 <table class="table table-bordered">
   	 	<tr>
   	 		<td>Payment ID : </td>
   	 		<td><%=payInfo.getPaymentId() %></td>
   	 	</tr>
   	 	<tr>
   	 		<td>Mode : </td>
   	 		<td><%=payInfo.getMode() %></td>
   	 	</tr> 
   	 	<tr>
   	 		<td>Txn ID : </td>
   	 		<td><%=payInfo.getTxnid() %></td>
   	 	</tr> 
   	 	<tr>
   	 		<td>User Name : </td>
   	 		<td><%=payInfo.getFirstname() %></td>
   	 	</tr>  	 	
   	 	<tr>
   	 		<td>Amount : </td>
   	 		<td><%=payInfo.getAmount() %></td>
   	 	</tr>   	 	
   	 	<tr>
   	 		<td>Card Number# : </td>
   	 		<td><%=payInfo.getCardnum() %></td>
   	 	</tr>
   	 	<tr>
   	 		<td>Email ID : </td>
   	 		<td><%=payInfo.getEmailId() %></td>
   	 	</tr>
   	 	<tr>
   	 		<td>Water Meter : </td>
   	 		<td><%=payInfo.getDevEUI() %></td>
   	 	</tr>
   	 	<tr>
   	 		<td>Txn Mode : </td>
   	 		<td><%=payInfo.getProductInfo() %></td>
   	 	</tr>  
   	 	<tr>
   	 		<td>Phone Number : </td>
   	 		<td><%=payInfo.getPhoneno() %></td>
   	 	</tr>    	 	
   	 	<tr>
   	 		<td>Transaction Date : </td>
   	 		<td><%=payInfo.getCreatedOn() %></td>
   	 	</tr>  	
   	 	<tr>
   	 		<td>Status : </td>
   	 		<td><%=payInfo.getStatus() %></td>
   	 	</tr>  	 	
   	 	
   	 
   	 </table>
   	 
   	 <% }%>

	</div>	
</div>

</div>	
			</section>	
			<%@include file="Footer.jsp"%>  		
	</div>	
	</div>
    </body>
</html>