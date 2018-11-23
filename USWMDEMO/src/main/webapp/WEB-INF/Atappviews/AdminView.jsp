<%--
    Author     : Vikky
--%>

<%@ page errorPage="error.jsp" %> 
<%@page import="com.team.app.domain.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<%@page import="com.team.app.*"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Dashboard</title>
    
	<script type="text/javascript" src="js/jquery-latest.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
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
	 

	<script src="js/app.min.js"></script>
	<script src="js/demo.js"></script>
	
<!-- Pie Charts... -->
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
    
      
  </head>
  <style>
	#goTop{
		background:#3c8dbc;
		padding:8px;
		position:fixed;
		top:-100px;
		right:10px;
		border-radius: 150%;
	}
</style>
  
  <body class="hold-transition skin-blue sidebar-mini">
  
  			<% 
  			   List<TblUserInfo> userInfos=(List<TblUserInfo>)request.getAttribute("userInfos");
  			%>
  			
		<input type="hidden" id="dashVal" value="0"/>
		<a  id="goTop"><i class="fa fa-eject"></i></a>	
							 
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
	  					  <h5 class="text-blue text-left "><span class="fa fa-dashboard"></span><b>Dashboard</b></h5>
	       
	   				</div><!-- /.box-header -->
		 							
   						
   						
   						<div class="row">
   							<div class="info-box col-sm-4 mar-top-15" >
							  <!-- Apply any bg-* class to to the icon to color it -->
							  	<span class="info-box-icon bg-blue"><i class="fa fa-user"></i></span>
							  	<div class="info-box-content">
								    <span class="info-box-text">Users</span>
								    <%if(userInfos!=null && !userInfos.isEmpty()){ %>
								    	<span class="info-box-number"><b><%=userInfos.get(0)%></b></span>
								    <%}%>
							 	</div><!-- /.info-box-content -->
							 </div><!-- /.info-box -->
						  							
						</div>
										
					</div>	
			</section>	
			<%@include file="Footer.jsp"%>  		
		</div>	
	</div>
			
  </body>
</html>