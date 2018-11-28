<%--
    Author     : Vikky
--%>


<%@page import="com.team.app.domain.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<%@ page errorPage="error.jsp" %> 
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Edit Info</title>
    
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

    
   <script type="text/javascript">   
   
   function confirmValidate(){
	   
	  	var uId=document.getElementById("uId").value;
		var email=document.getElementById("email").value;
	  	var contact=document.getElementById("contact").value;
		var uname=document.getElementById("uname").value;
			   
	   	var emailRegx = /^((([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/;
	  	var nameRegx = /^[A-Za-z][A-Za-z0-9 ]*$/;
	  	var phoneRegx = /^[0][1-9]\d{9}$|^[1-9]\d{9}$/g;
	  	//var phoneRegx=^([0|\+[0-9]{1,5})?([7-9][0-9]{9})$;
		   	   
	  	if(nameRegx.test(uname)==false) {
			alert("Invalid Username Format!! Spaces and Special characters are not allowed");
			document.getElementById("uname").focus();
			return false;
	   }else if(emailRegx.test(email)==false) {
			alert("Invalid Email ID Format!");
			document.getElementById("email").focus();
			return false;			
	   }else if (contact==""){
		   alert("Please Specify Phone Number");
			document.getElementById("contact").focus();
			return false;
	   }else if (phoneRegx.test(contact)==false){
		   alert("Invalid Mobile Number !");
			document.getElementById("contact").focus();
			return false;
	   }else if ($("input[name=area]").val() == "") {
			alert("Please select area");
			return false;
	   }else if ($("input[name=place]").val() == "") {
			alert("Please select place");
			return false;
	   }else if ($("input[name=landmark]").val() == "") {
			alert("Please select landmark");
			return false;
	   }else{
		   
		   $.ajax({
	           url: 'userUpdateInfo',
	           type: 'POST',
	           data: jQuery.param({uId: uId,email:email,contact:contact}) ,
	           success: function (data) {	
	        	  
		        	   if(data=='success'){
		        		   alert("User details updated successfully!");		        		   	        		
		        	   }else if(data=='failed') {
		        		   alert("System Error! User details updation failed!"); 
		        	   }    		   
		        	   location.reload();
	               },
			 		error: function(e){
		     			        alert('Error: ' + e);
		     		 }

	              
	           }); 
		   
		   return false;
	   }				
   }
   

</script> 
     
  </head>
  
  <body class="hold-transition skin-blue sidebar-mini">
  		<% TblUserInfo user=(TblUserInfo)request.getAttribute("user");%>
							 
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
  					  <h5 class="text-blue text-left "><span class="fa fa-edit"></span><b> Edit Info</b></h5>
       
   				</div><!-- /.box-header -->
		 							
		 					<div class="row">
									<div class="col-sm-12 text-center">
									<%String message="";
						
										try{
											//message=request.getParameter("message");
											message=(String)request.getAttribute("status");
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
    				    	<div class="col-sm-6">	
    				    	
    				    	<form name="form1" action="#" onsubmit="return confirmValidate();" method="post">
										
								  <table class="table">					  	
																	
									
									<tr>	
									   <td align="right"><b>Username:</b></td>
									   
										 <td>
										 	<input type="text" class="form-control" name="uname" id="uname" value="<%=user.getUname()%>" readonly>
										 	<input type="hidden" name="uId" id="uId" value="<%=user.getId()%>">
										</td>
									</tr>
									
									<tr>	
									   <td align="right"><b>Email ID:</b></td>
									   
										 <td>
										 	<input type="text" class="form-control" name="email" id="email" value="<%=user.getEmailId()%>">
										</td>
									</tr>
									
																									
									
									<tr>	
									   <td align="right"><b>Mobile Number:</b></td>
									   
										 <td>
										 	<input type="text" class="form-control" name="contact" id="contact" value="<%=user.getContactnumber()%>">
										</td>
									</tr>
									
									
									
									<tr>	
									   <td align="right"><b>Area:</b></td>
									   
										 <td>
										 	<input type="text" class="form-control" name="area" id="area" value="<%=user.getLandmark().getPlace().getArea().getAreaname()%>" readonly/>
										 	
										</td>
									</tr>
									<tr>	
									   <td align="right"><b>Place:</b></td>
									   
										 <td>
										 	<input type="text" class="form-control" name="place" id="place" value="<%=user.getLandmark().getPlace().getPlacename()%>" readonly/>
										</td>
									</tr>
									<tr>	
									   <td align="right"><b>Landmark:</b></td>
									   
										 <td>
										 	<input type="text" class="form-control" name="landmark" id="landmark" value="<%=user.getLandmark().getLandmarkname()%>" readonly/>
										</td>
									</tr>
									
									<tr>	
										<td align="right"></td>
											<td> <input type="submit"  class="form-control"  style="background-color:#3c8dbc;color:white;" value="Update Info"/></td>
										 
									</tr>	
								</table>	
							 </form> 
							</div>	
					   </div>
										
					</div>	
			</section>	
			<%@include file="Footer.jsp"%>  		
	</div>	
	</div>
			
  </body>
</html>