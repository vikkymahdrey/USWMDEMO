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
		   	   
	   if ($("input[name=uname]").val() == "") {
			alert("Please specify Username");
			return false;
	   }else if ($("input[name=email]").val() == "") {
			alert("Please specify Email ID");
			return false;
	   }else if (isNaN($("input[name=contact]").val())
				|| ($("input[name=contact]").val()).length != 10) {
			alert("Please specify 10 digit mobile number");
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
    				    	
    				    	<form name="form1" action="userUpdateInfo" onsubmit="return confirmValidate();" method="post">
										
								  <table class="table">
								  	
																	
									
									<tr>	
									   <td align="right"><b>Username:</b></td>
									   
										 <td>
										 	<input type="text" name="uname" id="uname" value="<%=user.getUname()%>" readonly>
										 	<input type="hidden" name="uId" id="uId" value="<%=user.getId()%>">
										</td>
									</tr>
									
									<tr>	
									   <td align="right"><b>Email ID:</b></td>
									   
										 <td>
										 	<input type="text" name="email" id="email" value="<%=user.getEmailId()%>">
										</td>
									</tr>
									
																									
									
									<tr>	
									   <td align="right"><b>Mobile Number:</b></td>
									   
										 <td>
										 	<input type="text"  name="contact" id="contact" value="<%=user.getContactnumber()%>">
										</td>
									</tr>
									
									
									
									<tr>	
									   <td align="right"><b>Area:</b></td>
									   
										 <td>
										 	<input type="text" name="area" id="area" value="<%=user.getLandmark().getPlace().getArea().getAreaname()%>" readonly/>
										 	
										</td>
									</tr>
									<tr>	
									   <td align="right"><b>Place:</b></td>
									   
										 <td>
										 	<input type="text" name="place" id="place" value="<%=user.getLandmark().getPlace().getPlacename()%>" readonly/>
										</td>
									</tr>
									<tr>	
									   <td align="right"><b>Landmark:</b></td>
									   
										 <td>
										 	<input type="text" name="landmark" id="landmark" value="<%=user.getLandmark().getLandmarkname()%>" readonly/>
										</td>
									</tr>
									
									<tr>	
										<td align="right"></td>
											<td> <input type="submit"  class="formbutton" style="background-color:#3c8dbc;" value="Update Info"/></td>
										 
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