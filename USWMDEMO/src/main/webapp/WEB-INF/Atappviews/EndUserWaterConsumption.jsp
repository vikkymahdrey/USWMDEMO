 <%--
    Document   : Water Consumption Unit
    Author     : Vikky
--%>
<%@ page errorPage="error.jsp" %> 
<%@page import="java.util.*"%>
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
  <title>WaterConsumption |  Litre</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
 <!--  Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
 <!--  Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
 <!--  Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="css/AdminLTE.min.css">
 <!--  AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="css/skins/_all-skins.min.css">
  
  <script src="https://code.highcharts.com/highcharts.js"></script>
  <script src="https://code.highcharts.com/modules/exporting.js"></script>
  <script src="https://code.highcharts.com/modules/export-data.js"></script>
  
  
  <script type="text/javascript" src="js/scroller.js"></script>


<!-- ChartJS 1.0.1 -->
<script src="plugins/chartjs/Chart.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="js/pages/dashboard2.js"></script>
  
<!--  jQuery 2.2.3 -->
<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- SlimScroll 1.3.0 -->
<!-- <script src="plugins/slimScroll/jquery.slimscroll.js"></script> -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick-->
<script src="plugins/fastclick/fastclick.js"></script>
<!-- <script src="plugins/fastclick/fastclick.min.js"></script> -->
<!-- AdminLTE App -->
<script src="js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="js/demo.js"></script>
<!-- jQuery Knob -->
<script src="plugins/knob/jquery.knob.js"></script>
<!-- Sparkline -->
<!-- <script src="plugins/sparkline/jquery.sparkline.js"></script> -->
<script src="plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- Bootstrap Date-Picker Plugin -->

<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">

</head>

<script type="text/javascript">

	$(function() {
	  $('#datetimepicker1').datetimepicker();
	  $('#datetimepicker2').datetimepicker();
	});
   
   function confirmValidate(){
	  
 	  var devid=document.getElementById("devid").value;
 	  		   	   
	   if ($("input[name=fromDate]").val() == "") {
			alert("Please specify FromDate");
			return false;
	   }else if ($("input[name=toDate]").val() == "") {
			alert("Please specify ToDate");
			return false;
	   }else if(devid=="0"){
		   alert("Please select Water Meter!");
		   return false;
	   }
   }
   

</script> 
     

<body class="hold-transition skin-blue sidebar-mini">
			<% 
			String fname1=("WaterConsumptionUnits :").concat(new Date().toString()).concat(".csv");
			String fname2=("WaterConsumptionUnits :").concat(new Date().toString()).concat(".xls");
			String fname3=("WaterConsumptionUnits :").concat(new Date().toString()).concat(".xml");
			  			
  			List<UserDeviceMapping> udmList=(List<UserDeviceMapping>)request.getAttribute("udmList");
  			List<LoraFrame> frames=(List<LoraFrame>)request.getAttribute("frames");
  			
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
						</div>	
					
													
						
						<div class="box-header with-border">
  					 		 <h5 class="text-blue text-left "><span class="fa fa-tint"></span><b> Water Consumption In Litre</b></h5>
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
    				    	
    				    	<form name="form1" action="endUserWaterConsumptionUnits" onsubmit="return confirmValidate();" method="post">
										
								  <table class="table">
								 							
								  <tr>      
								      <td>
								        <label>From Date</label>
								        <div class='input-group date' id='datetimepicker1'>
								          <input type='text' name="fromDate" class="form-control" placeholder="Select FromDate"/>
								            <span class="input-group-addon">
								            	<span class="glyphicon glyphicon-calendar"></span>    
								            </span>
								         </div>
								      </td>
								      
							           <td>
							       		 <label>To Date</label>
									        <div class='input-group date' id='datetimepicker2'>
									          <input type='text' name="toDate" class="form-control" placeholder="Select ToDate" />
									            <span class="input-group-addon">
									            	<span class="glyphicon glyphicon-calendar"></span>
									            </span>
									        </div>
							            </td>
							            
							            <td>
									   		<label>Water Meters</label>
										  	<div>
									          <select name="devid" id="devid" class="form-control">
										    	<option value="0">Select Water Meter</option>	
										    	<%if(udmList!=null && !udmList.isEmpty()){
											    	for(UserDeviceMapping udm: udmList) {%>
											    	<option value="<%=udm.getDevEUI()%>"><%=udm.getDevNode()+"-"+udm.getDevEUI()%></option>
											    	<%}
										    	}%>
										      </select> 
									            
									        </div>
										</td>
							           							           
							            
							            <td>
							       		 <label>Action</label>
									        <div>
									         <input type="submit"  class="form-control" style="background-color:#3c8dbc;" value="Submit"/>
									            
									        </div>
							            </td>
							            
							            
        							</tr>
									
									
								</table>	
							 </form> 
							</div>	
											 
					   </div>
					   
					    	<% if(frames!=null && !frames.isEmpty()){
    				    	%>
    				    	 
    				    	 <div class="row" style="overflow-y: auto;">
							<div class="col-sm-12 ">	
								<display:table  class="table table-hover  text-center"  name="<%=frames%>" id="row"
										export="true" requestURI="" defaultsort="1" defaultorder="descending" pagesize="100">
									<display:column  property="id" title="ID" sortable="true" headerClass="sortable" />
									<display:column  property="applicationID" title="BlockID" sortable="true"  />
									<display:column  property="applicationName" title="BlockName" sortable="true"  />
									<display:column  property="devEUI" title="WaterMeter" sortable="true"  />
									<%-- <display:column  property="fPort" title="fPort" sortable="true"  /> --%>
									<display:column  property="gatewayMac" title="GatewayMac" sortable="true"  />
									<display:column  property="gatewayName" title="GatewayName" sortable="true"  />
									<display:column  property="waterltr" title="Water_Val_In_Ltr"  sortable="true"  />
									<display:column  property="createdAt" title="CreatedDt"  sortable="true"  />
															     		   
								 	<display:setProperty name="export.csv.filename" value="<%=fname1%>" />
									<display:setProperty name="export.excel.filename" value="<%=fname2%>" />
									<display:setProperty name="export.xml.filename" value="<%=fname3%>" /> 
						         </display:table> 
							</div>
						</div>
    				    	 <%
    				    	 }else{%>
    				    	 <p>No data found!</p>
    				    	 <% }%>
					      				    		   				
				 </div>
			</section>	
			<%@include file="Footer.jsp"%>  		
	</div>	
</div>
		 
		
</body>
</html> 