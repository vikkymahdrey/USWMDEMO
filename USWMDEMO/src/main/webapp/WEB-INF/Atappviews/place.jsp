<%--
    Author     : Vikky
--%>


<%@page import="com.team.app.domain.*"%>
<%@page import="com.team.app.dto.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<%@ page errorPage="error.jsp" %> 
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>APL Config</title>
    
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
	  <link href="css/style.css" rel="stylesheet" type="text/css" />
	  
	 

	<script src="js/app.min.js"></script>
	<script src="js/demo.js"></script>
	
<!-- Pie Charts... -->
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
    
   <script type="text/javascript">
   
   $(document).ready(function() {

		$("form[name=place]").hide();
		$("form[name=editPlace]").hide();
		$("#showAddPlaceDiv_a").click(showAddPlace);
		$("#closeAddPlace_img").click(closeAddPlace);
		$(".editButton").click(showEditPlace);
		$("form[name=place]").draggable();

	});

      
 //--------------
 	function showAddPlace() {
 		$("#place").val("");
 		$("form[name=editPlace]").attr("action", "addPlace");
 		$("#windowTitle").text("Add Place");
 		$("#showAddPlaceDiv").hide();
 		$("form[name=place]").show();
 		$("#submitbtn").val("Add");
 	}
 	function closeAddPlace() {

 		$("#showAddPlaceDiv").show();
 		$("form[name=place]").hide();
 		$("form[name=place]").attr("action", "#");
 	}

 	function showEditPlace() {
 		try {
 			$("form[name=place]").show();
 			$("form[name=place]").attr("action", "updatePlace");
 			$("#windowTitle").text("Edit Place");
 			$("#submitbtn").val("Update");

 			$("input[name=placeId]").val(
 					$(this).parent().parent().children().children(".placeId")
 							.val());
 			$("#place").val(
 					$(this).parent().parent().children().children(".place")
 							.val());

 		} catch (e) {
 			alert(e);
 		}
 	}

 	//-----validation

 	//--------------

 	function validatePlace() {

 		var flag = true;

 		try {
 			if ($("input[name=place]").val().trim() == "") {
 				alert("Place should not be blank");
 				flag = false;
 			} else if(/^[A-Za-z0-9]+[A-Za-z0-9&\s\.\,\/\-\_:\(\)\[\]]*$/.test($("input[name=place]").val().trim())==false) {
 				alert("Place includes invalid data");
 				flag = false;
 			}
 		} catch (e) {

 			alert(e.message);
 			flag = false;
 		}
 		return flag;

 	}
 	

     </script>       
     
  </head>
  
  <body class="hold-transition skin-blue sidebar-mini">
  
  			<%
				//int areaId = Integer.parseInt("" + request.getParameter("areaId"));

				List<Place> placesUnderArea=(List<Place>)request.getAttribute("placeList");
				Area ar=(Area)request.getAttribute("areas");
			%>


			  			
							 
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
  					  <h5 class="text-blue text-left "><span class="fa fa-map-marker"></span>&nbsp;&nbsp;<b>Places under <%=ar.getAreaname()%></b></h5>
       
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
		   				
   						 
					   
					   
					      <%--  <%
							if (placesUnderArea != null  && !(placesUnderArea.isEmpty())) {
															
						   %> --%>
					   
					   <div class="row" >
    				    	<div class="col-sm-12">	
    				    	   																		
								<table style="width: 50%;float: left" class="table table-bordered">
									<tr>
										<td style="width: 70%; vertical-align: top;">
											<table>
												<thead>
													<tr>
														<th>Id</th>
														<th>Place_Name</th>
																											
													</tr>
												</thead>
												<% 
													for (Place p : placesUnderArea) {
														
												%>
												<tr>
													<td ><%=p.getId()%></td>
													<td ><a href="showLandmark?placeId=<%=p.getId()%>"><%=p.getPlacename()%></a>
														<input type="hidden" value="<%=p.getId()%>" class="placeId" /> 
														<input type="hidden" value="<%=p.getPlacename()%>" class="place" />
														
													</td>
													<td><img src="images/edit.png" class="editButton" title="Edit" /></td>
																																							
												</tr>
												<%
													}
												%>
											</table>
										</td>			
										<td style="width: 30%; vertical-align: top;">
											<div id="showAddPlaceDiv" style="padding-top: 3px;">
												<input type="button" class="formbutton" id="showAddPlaceDiv_a" 
													value="Add Place"  style="background-color:#3c8dbc";/>
					
											</div>
											<form name="place"  action="addPlace"	onsubmit="return validatePlace();">
												<table style="border-style: outset; width: 20%;">
													<thead>
														<tr>
															<th colspan="2"><label id="windowTitle">Add Place</label>
																<div style="float: right;" id="closeAddPlace">
																	<img id="closeAddPlace_img" style="float: right;"
																		id="closeAddPlace" src="images/close.png" title="Close" />
																</div>
															</th>
														</tr>
													</thead>
													<tr>
														<td align="center">Place</td>
														<td align="center">
															<input type="text" name="place" id="place" />
															<input type="hidden" name="placeId" />
															<input type="hidden" name="areaId" value="<%=ar.getId()%>"/>
															
														</td>
													</tr>
													<tr>
														<td align="center">&nbsp;</td>
														<td ><input type="submit" class="formbutton" style="background-color:#3c8dbc";
															value="Add" name="submitbtn" id="submitbtn" />
														</td>
													</tr>
													<!-- <tr>
														<td align="center"></td>
													</tr> -->
												</table>
											</form>
										</td>					
									</tr>
								</table>
			
								<iframe  style="float: right; width: 48%" height="500px" src="marklandmark?area=<%=ar.getId()%>" ></iframe>
											<%-- <% 
												}%> --%>
							</div>	
					   </div>
					   
										
					</div>	
			</section>	
			<%@include file="Footer.jsp"%>  		
	</div>	
	</div>
			
  </body>
</html>