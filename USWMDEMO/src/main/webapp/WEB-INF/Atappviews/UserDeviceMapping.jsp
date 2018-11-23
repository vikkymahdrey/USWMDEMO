 <%--
    Document   : User Report
    Author     : Vikky
--%>

 <%@page import="java.util.*"%>
 <%@ page errorPage="error.jsp" %> 
<%@page import="com.team.app.domain.*"%>
<%@page import="com.team.app.dto.*"%>
<%@page import="org.displaytag.decorator.TotalTableDecorator"%>
<%@page import="org.displaytag.decorator.MultilevelTotalTableDecorator"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
 <%@ page buffer = "900kb" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html >
<html lang="en">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>User Device Mapping</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/custom_siemens.css" rel="stylesheet">
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="css/AdminLTE.min.css">
<link rel="stylesheet" href="css/skins/_all-skins.min.css">
<link rel="stylesheet" href="css/slider.css">
  <link href="css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery-latest.js"></script>
<script  src="https://code.jquery.com/jquery-2.2.0.js"></script>
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="js/app.min.js"></script>
<script src="js/demo.js"></script>
<script src="js/scroller.js"></script>

</head>

<body class="hold-transition skin-blue sidebar-mini">
					<% 
						String fname1=("UserDevieMapping :").concat(new Date().toString()).concat(".csv");
						String fname2=("UserDevieMapping :").concat(new Date().toString()).concat(".xls");
						String fname3=("UserDevieMapping :").concat(new Date().toString()).concat(".xml");
												
						%>
	
<div class="wrapper">  
 <%@include file="Header.jsp"%>  
 
	<div class="content-wrapper">
		
			<section class="content">
		 		<div class="content-wrap box box-primary">
		 			
			 		
						
						<div class="row">
							<div class="col-sm-12 text-right ">	
							   <img src="images/user_iocn_header.png" />&nbsp;<b>Welcome <%=userSession.getUname()%></b> 
							</div>
													
						</div><br/>
					
					
						
						<div class="box-header with-border">
  					 		 <h5 class="text-blue text-left "><span class="fa fa-user"></span><b> User Device Mapping</b></h5>
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
		   				  	
						
						<div class="row" style="overflow-y: auto;">
							<div class="col-sm-12 ">	
							
							<display:table  class="table table-hover  text-center"  name="<%=userSession.getUserDeviceMappings()%>" id="row"
									export="true" requestURI="" defaultsort="1" defaultorder="descending" pagesize="100">
							
							<display:column  property="id" title="ID" sortable="true" headerClass="sortable" />
							<display:column  property="devNode" title="WaterMeterName" sortable="true"  />
							<display:column  property="devEUI" title="WaterMeter" sortable="true"  />
							<display:column  property="createddt" title="Meter_CreatedDt" sortable="true"  />
							<display:column  title="Username" sortable="true">
								<%=userSession.getUname()%>							
							</display:column>
							<display:column  title="EmailID" sortable="true">
								<%=userSession.getEmailId()%>							
							</display:column>
							<display:column   title="UserType" sortable="true">
								<%=userSession.getRoleBean().getType()%>						
							</display:column>
							<display:column  title="MobileNumber" sortable="true">
							<%=userSession.getContactnumber()%>						
							</display:column>
							<display:column title="User_CreatedDt"  sortable="true">
							<%=userSession.getCreateddt()%>						
							</display:column>			
							
										     		   
						 	<display:setProperty name="export.csv.filename" value="<%=fname1%>" />
							<display:setProperty name="export.excel.filename" value="<%=fname2%>" />
							<display:setProperty name="export.xml.filename" value="<%=fname3%>" /> 
							
						</display:table> 
						
						
							</div>
						</div>
						<a  id="goTop"><i class="fa fa-eject"></i></a>	
				 </div>
			</section>	
			<%@include file="Footer.jsp"%>  		
	</div>	
</div>
		 
		
</body>
</html> 