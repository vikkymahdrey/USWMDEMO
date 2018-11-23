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
		$("form[name=editLandmark]").hide();
		$("form[name=landmark]").hide();
		$("#showAddLandmarkDiv_a").click(showAddLandmark);
		$("#closeAddLandmark_img").click(closeAddLandmark);
		$(".editButton").click(showEditLandmark);
		$("form[name=landmark]").draggable();
	});

      
 //--------------
 	function showAddLandmark() {
 		$("#landmark").val("");
 		$("form[name=editLandmark]").attr("action", "addLandmark");
 		$("#windowTitle").text("Add Landmark");
 		$("#showAddLandmarkDiv").hide();
 		$("form[name=landmark]").show();
 		$("#submitbtn").val("Add");
 	}
 	function closeAddLandmark() {

 		$("#showAddLandmarkDiv").show();
 		$("form[name=landmark]").hide();
 		$("form[name=landmark]").attr("action", "#");
 	}

 	function showEditLandmark() {
 		try {
 			$("form[name=landmark]").show();
 			$("form[name=landmark]").attr("action", "updateLandmark");
 			$("#windowTitle").text("Edit Landmark");
 			$("#submitbtn").val("Update");

 			$("input[name=landmarkId]").val(
 					$(this).parent().parent().children().children(".landmarkId")
 							.val());
 			$("#landmark").val(
 					$(this).parent().parent().children().children(".landmark")
 							.val());

 		} catch (e) {
 			alert(e);
 		}
 	}

 	//-----validation

 	//--------------

 	function validateLandmark() {
	
 		var flag = true;

 		try {
 			if ($("input[name=landmark]").val().trim() == "") {
 				alert("Landmark should not be blank");
 				flag = false;
 			}else if(/^[A-Za-z0-9]+[A-Za-z0-9&\s\.\,\/\-\_:\(\)\[\]]*$/.test($("input[name=landmark]").val().trim())==false) {
 				alert("Landmark includes invalid data");
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
	  						
				 List<Landmark> landmarkList = (List<Landmark>)request.getAttribute("landmarkList");
            		Place place = (Place)request.getAttribute("place");
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
  					  <h5 class="text-blue text-left "><span class="fa fa-map-marker"></span>&nbsp;&nbsp;<b>Landmark under <%=place.getPlacename()%></b></h5>
       
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
		   				
   						 
					   
					   
					       <%-- <%
							if (landmarkList != null  && !(landmarkList.isEmpty())) {
															
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
														<th></th>
														<th>Landmark_Name</th>
																											
													</tr>
												</thead>
												<% 
													for (Landmark l : landmarkList) {
														
												%>
												<tr>
													<td ><%=l.getId()%></td>
													<td >
														<input type="hidden" class="landmarkId"	value="<%=l.getId()%>" /> 
														<input type="hidden" class="landmark" value="<%=l.getLandmarkname()%>" />
													</td>
													<td align="center"><%=l.getLandmarkname()%></td>
													<td id="modifyLandmarkLabel<%=l.getId()%>">
														<img src="images/edit.png" class="editButton" title="Edit" />
													</td>
													<TD id="modifyLandmark<%=l.getId()%>" style="display: none"></TD>
													
																																							
												</tr>
												<%
													}
												%>
											</table>
										</td>			
										<td width="30%"
											style="border-bottom: 0px solid #000; vertical-align: top;">
					
											<div id="showAddLandmarkDiv" style="padding-top: 3px;">
												<input type="button" class="formbutton" id="showAddLandmarkDiv_a"
													 value="Add Landmark"  style="background-color:#3c8dbc";/>
					
											</div>
					
					
											<form name="landmark" action="addLandmark"
												onsubmit="return validateLandmark();" method="post">
					
					
												<table style="border-style: outset; width: 20%;">
													<thead>
														<tr>
															<th colspan="2"><label id="windowTitle">Add
																	Landmark</label>
																<div style="float: right;" id="closeAddLandmark">
																	<img id="closeAddLandmark_img" style="float: right;"
																		id="closeAddLandmark" src="images/close.png" title="Close" />
																</div></th>
														</tr>
													</thead>
													<tr>
														<td align="center">Landmark</td>
														<td align="center">
														<input type="hidden" name="landmarkId" value="" />
														<input type="hidden" value="<%=place.getId()%>" name="placeId" />
														<input type="text" name="landmark" id="landmark" /></td>
													</tr>
													<tr>
														<td align="center">&nbsp;</td>
														<td align="center"><input type="submit" class="formbutton" style="background-color:#3c8dbc"
															value="Add" name="submitbtn" id="submitbtn" /></td>
													</tr>
													<tr>
														<td align="center"></td>
													</tr>
												</table>
											</form>
										</td>		
									</tr>
								</table>
			<iframe  style="float: right; width: 48%" height="500px" src="marklandmark?place=<%=place.getId()%>" ></iframe>
									<%-- <% }%> --%>
							</div>	
					   </div>
					   
										
					</div>	
			</section>	
			<%@include file="Footer.jsp"%>  		
	</div>	
	</div>
			
  </body>
</html>