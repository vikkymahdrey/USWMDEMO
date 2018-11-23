<%--
    Author     : Vikky
--%>

<%@ page errorPage="error.jsp" %> 
<%@page import="com.team.app.domain.*"%>
<%@page import="com.team.app.dto.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
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
   
   $(document).ready(function(){
  	 $("form[name=area]").hide();
  	 $("form[name=editArea]").hide();
  	$("#showAddAreaDiv_a").click(showAddArea);
  	$("#closeAddArea_img").click(closeAddArea);
  	 $(".editButton").click(showEditArea);
  	 $("form[name=area]").draggable();
  	 
  	//$("#submitbtn").click(addAreaAjax);
  	
    });
   
   function confirmValidate(){
	   
	   var orgid=document.getElementById("orgid").value;
 	     	   
	   if(orgid=="0"){
		   alert("Please select organisation!");
		   return false;
	   }/* else{		   
			   $.ajax({
	               url: 'syncDev',
	               type: 'POST',
	               //data: 'orgId='+orgid+'&appId='+appid+'&devId='+devid,
	               data: jQuery.param({ orgId: orgid, appId : appid, devId : devid}) ,
	               success: function (data) {
	               alert(data);
	               window.location.reload();
	            	   //$(".success").html(data);
	                   	
	                   },
	  		 		error: function(e){
	  	     			        alert('Error: ' + e);
	  	     		 }
	
	                  
	               });
			   
			  return false;
	   		}  */
	   		
   }
   
   
   function addAreaAjax(){
 	  var area = $("input[name=area]").val().trim();
 	  var location = $("input[name=location]").val().trim();
   		 $.ajax({
                url: 'addArea',
                type: "POST",
                data: 'area='+area+'&location='+location,
                success: function (data) {
                alert(data);
             	   //$(".success").html(data);
                    	
                    },
   		 error: function(e){
   	     			        alert('Error: ' + e);
   	      }

                   
                });
   	
   }
   
   function aplUpload() {
 	  if( isNaN($("select[name=branchId]").val() )) {
 		  alert('Please select location');
 	  } else { 
 		window.location.href="aplUpload?branchId=" +$("select[name=branchId]").val(); 
 	  }
   }
   
   function fixAPL() {
 	  window.open("marklandmark?location="+$("select[name=branchId]").val());
   }
   
   function showAddArea()
   {
 	  $("#area").val("");
 	  $("form[name=editArea]").attr("action","addArea");
 	  $("#windowTitle").text("Add Area");
 	  $("#showAddAreaDiv").hide();
 	  $("form[name=area]").show();
 	  $("#submitbtn").val("Add");
   }
   function closeAddArea()
   {
 	  $("#showAddAreaDiv").show();
 	  $("form[name=area]").hide();
 	  $("form[name=area]").attr("action","#");
   }

   function showEditArea()
   {
 	 try{ 
 	  $("form[name=area]").show();
 	  $("form[name=area]").attr("action","updateArea");
 	  $("#windowTitle").text("Edit Area");
 	  $("#submitbtn").val("Update");
 	  
 	  $("input[name=areaId]").val($(this).parent().parent().children().children(".areaId").val());
 	  $("#area").val($(this).parent().parent().children().children(".area").val());
 	 
 	 }catch(e)
 	 {
 		 alert(e);
 	 }
   }
   
   //-----validation
   
   function validateArea()
   {
 	  var flag=true;
 	  var area = $("input[name=area]").val().trim();
 	  var orgId = $("input[name=orgId]").val().trim();
 		  try{
 	if(area=="")
 	{
 		alert("Area should not be blank")
 		flag=false;
 	} else if (/^[A-Za-z0-9]+[A-Za-z0-9&\s\.\,\/\-\_:\(\)\[\]]*$/.test(area)==false) {
 		alert("Area includes invalid charectors");
 		flag=false;
 	}
 	}catch (e) {
			
			alert(e.message);
			flag=false;
		}
 	
 	return flag;   
   }
   
   
      
   function submitForm()
   {
 	 document.getElementById("branchForm").submit(); 
   }

     </script>       
     
  </head>
  
  <body class="hold-transition skin-blue sidebar-mini">
  
  			<% 
  			List<OrganisationDto> list=(List<OrganisationDto>)request.getAttribute("organisations");
  		  			
  			String orgId = request.getParameter("orgid");
			 if(orgId==null){
				 orgId="";
			}  
			 
			  			
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
  					  <h5 class="text-blue text-left "><span class="fa fa-map-marker"></span>&nbsp;&nbsp;<b>APL Configuration</b></h5>
       
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
    				    	   	<form action="aplConfig" name="branchForm" id="branchForm">			    								
								  <table class="table">
								  	<tr>
								  		<td align="right"><b>Organization</b></td>	
								  								  											
										<td>										
										 <select name="orgid" id="orgid" onchange="submitForm()">
										  <option value="0">--Choose Organisation--</option>
										 <%if(list!=null && !list.isEmpty()){
								  			for (OrganisationDto dto : list) {
								  				if (orgId.equals(""+dto.getOrgId())) {%>	
										   		    <option value="<%=dto.getOrgId()%>" selected="selected"><%=dto.getOrgName()%></option>
										 		<%}else{ %>
										 		    <option value="<%=dto.getOrgId()%>"><%=dto.getOrgName()%></option>
										 		<%} %>
										 </select> 
										</td>
											<%}
								  		}%>
										
										
									</tr>
								   </table>	
							    </form>
							</div>	
					   </div>
					   
					   
					       <%
							if (orgId != null  && !(orgId.isEmpty())) {
								@SuppressWarnings("unchecked")
								List<Area> areaList=(List<Area>)request.getAttribute("areaList");
								//if(areaList!=null && !areaList.isEmpty()){								
								
						   %>
					   
					   <div class="row" >
    				    	<div class="col-sm-12">	
    				    	   																		
								<table style="width: 50%;float: left" class="table table-bordered">
									<tr>
										<td style="width: 70%; vertical-align: top;">
											<table>
												<thead>
													<tr>
														<th>Id</th>
														<th>Area_Name</th>
																												
													</tr>
												</thead>
												<% 
													for (Area area : areaList) {
														
												%>
												<tr>
													<td ><%=area.getId()%></td>
													<td ><a href="showPlace?areaId=<%=area.getId()%>"><%=area.getAreaname()%></a>
														<input type="hidden" value="<%=area.getId()%>" class="areaId" /> 
														<input type="hidden" value="<%=area.getAreaname()%>" class="area" />
														
													</td>
													<td><img src="images/edit.png" class="editButton" title="Edit" /></td>
																																							
												</tr>
												<%
													}
												%>
											</table>
										</td>			
										<td style="width: 30%; vertical-align: top;">
											<div id="showAddAreaDiv" style="padding-top: 3px;">
												<input type="button" class="formbutton" id="showAddAreaDiv_a" 
													value="Add Area"  style="background-color:#3c8dbc";/>
					
											</div>
											<form name="area" id="addAreaForm" action="addArea"	onsubmit="return validateArea();">
												<table style="border-style: outset; width: 20%;">
													<thead>
														<tr>
															<th colspan="2"><label id="windowTitle">Add Area</label>
																<div style="float: right;" id="closeAddArea">
																	<img id="closeAddArea_img" style="float: right;"
																		id="closeAddArea" src="images/close.png" title="Close" />
																</div>
															</th>
														</tr>
													</thead>
													<tr>
														<td align="center">Area</td>
														<td align="center"><input type="text" name="area" id="area" /> 
														<input type="hidden" name="areaId" /></td>
													</tr>
													<tr>
														<td align="center">&nbsp;
															<input type="hidden" name="orgId" value="<%=orgId%>" />
														</td>
														<td align="center"><input type="submit" class="formbutton" style="background-color:#3c8dbc";
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
			
								<iframe  style="float: right; width: 48%" height="500px" src="marklandmark?orgId=<%=orgId%>" ></iframe>
											<% //}
												}%>
							</div>	
					   </div>
					   
										
					</div>	
			</section>	
			<%@include file="Footer.jsp"%>  		
	</div>	
	</div>
			
  </body>
</html>