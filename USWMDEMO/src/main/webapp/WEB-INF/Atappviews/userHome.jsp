<%--
    Document   : home page
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
    
    <title>Chart</title>
     
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="css/marquees.css" rel="stylesheet">   
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="stylesheet" href="css/jvectormap/jquery-jvectormap-1.2.2.css">
  <link rel="stylesheet" href="css/AdminLTE.min.css">
  <link rel="stylesheet" href="css/AdminLTE.css">
  <link rel="stylesheet" href="css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="css/skins/skin-blue.min.css">
  <link rel="stylesheet" href="css/map.css">
  
  
	

	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script type="text/javascript" src="js/jquery-latest.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>       
	<script type="text/javascript" src="js/scroller.js"></script>  	 
	<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
 	<script src="bootstrap/js/bootstrap.min.js"></script>
 	<script src="plugins/fastclick/fastclick.js"></script>
 	<script src="js/app.min.js"></script>
 	<script src="plugins/sparkline/jquery.sparkline.min.js"></script>
 	<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
 	<script src="plugins/jvectormap/jquery-jvectormap-in-mill.js"></script>
 	<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
 	<script src="plugins/chartjs/Chart.min.js"></script>
 	<script src="js/demo.js"></script>
 	<script src="plugins/knob/jquery.knob.js"></script>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyC9Qem9w4qe_9EqmMXJql00Qvkv1yB9wcU&sensor=false" type="text/javascript"></script>
 	
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">
 	
  <script type="text/javascript">
  function reload(){
	  location.reload();
  }

  $(function () {
	  
	   /*   $('#datetimepicker1').datetimepicker({maxDate: moment()});
		  $('#datetimepicker2').datetimepicker({maxDate: moment()}); */
	  $('#datetimepicker1').datetimepicker();
	  $('#datetimepicker2').datetimepicker();
	  
  });	 
  
  var jsonVal;
  
  function confirmValidate(){   
	  
	  	var devid=document.getElementById("devid").value;
	  	var fromDate=document.getElementById("fromDate").value;
	  	var toDate=document.getElementById("toDate").value;
	  	var filter=document.getElementById("filter").value;
	  	
	  		   	   
	   if(devid=="0"){
		   alert(devDesc);
		   document.getElementById("devid").focus();
		   return false;
	   }else if ($("input[name=fromDate]").val() == "") {
			alert("Choose FromDate");
			document.getElementById("fromDate").focus();
			return false;
	   }else if ($("input[name=toDate]").val() == "") {
			alert("Choose ToDate");
			document.getElementById("toDate").focus();
			return false;
	   }else if ($("input[name=filter]").val() == "") {
			alert("Choose filter");
			document.getElementById("filter").focus();
			return false;
	   }else{
		   
		   $.ajax({
               url: 'getGraphOnDemand',
               type: 'POST',
               data: jQuery.param({ devId : devid,fromDate : fromDate, toDate:toDate,type: filter}) ,
               success: function (data) {
            	   var obj=eval("(function(){return " + data + ";})()");
          		   var resultant=obj.result;
          		 
            	   if(resultant.length==0){
            		   alert("FromDate is after ToDate! ' Incorrect dates '");            		
            	   }
            	   jsonVal=data;            	
               			graphOnDemand();
                   },
  		 		error: function(e){
  	     			        alert('Error: ' + e);
  	     		 }

                  
               }); 
		  
		   return false;
		
   		} 
  }
  
  /*Calling to get graph onDemand*/  
  function graphOnDemand() {
	 var resultant;
	 var JSON;
	 var dateVal;
	 var unitsVal;
	 	 
	 if( typeof this.jsonVal !== 'undefined' ) {
	    var obj=eval("(function(){return " + jsonVal + ";})()");
		 resultant=obj.result;
		if(resultant==='No Content'){
			alert('No Data Found!'); 
			return;
		 };
		 
		 dateVal=new Array(resultant.length);
		 unitsVal=new Array(resultant.length);
		 
		 for (var i = 0; i <resultant.length; i++) {
			dateVal[i]=resultant[i].xaxis;
		    unitsVal[i]=resultant[i].units;
		    
		}	
		 
		 
	 }
	 
	 
			 
	 if( typeof this.jsonVal !== 'undefined' ) {
      	JSON = [                   
                   {
                     name: 'WaterConsumedUnits',
                     data: unitsVal
                   }
                 ];
	 }else{
		 JSON = [                   
             {
               name: 'WaterConsumedUnits',
               data: [0, 0]
             }
           ];
	 } 
      var options = {
                     chart: {
                              renderTo: 'container',
                              type: 'line'
                            },
                     title: {
                              text: 'Water Consumption-Hourly'
                            },
                     xAxis: {
                              categories: dateVal
                              //type: 'datetime'
                            },
                     yAxis: {
                          	  min: 0,
                          	  max:1000,
                              title: {
                                text: 'Values'
                              }                
                            },
                    legend: {
                              layout: 'vertical',
                              align: 'right',
                              verticalAlign: 'middle'
                            },
               plotOptions: {
                    	      line: {
                     	      dataLabels: {
                     	        enabled: true
                     	      }
                     	    }
                     	  },
                    series: []
                   };

                     options.series = JSON;

                     // Create the chart
                     var chart = new Highcharts.Chart(options);
      }
  
 
  /*Calling to get graph on body onload*/  
  
  	var jsonOnLoad;
   function loadScript() {
	   $.ajax({
	         url: 'getGraphOnBodyLoad',
	         type: 'GET',
	         success: function (data) {
	        	jsonOnLoad=data;
	      	 	getGraphAsPerBodyLoad();
	           },
		 		error: function(e){
	     			        alert('Error: ' + e);
	     		 }

	            
	         }); 
  	}
  
  function getGraphAsPerBodyLoad() {
	 var resultant;
	 var JSON;
	 var dateVal;
	 var unitsVal;  
	 
	 if( typeof this.jsonOnLoad !== 'undefined' ) {
		 var obj=eval("(function(){return " + jsonOnLoad + ";})()");
		 resultant=obj.result;
		if(resultant==='No Content'){
			alert('No Data Found!'); 
			return;
		}else{
			unitsVal=resultant.units;
			dateVal=resultant.xAxis;
		};
		 
		
	 }
	 
			 
	 if( typeof this.jsonOnLoad !== 'undefined' ) {
      	JSON = unitsVal;
	 }else{
		 JSON = [                   
             {
               name: 'WaterConsumedUnits',
               data: [0, 0]
             }
           ];
	 }
	 
	 
      var options = {
                     chart: {
                              renderTo: 'container',
                              type: 'line'
                            },
                     title: {
                              text: 'Water Consumption-Current Day (Hourly)'
                            },
                     xAxis: dateVal,
                     yAxis: {
                          	  min: 0,
                          	  max:1000,
                              title: {
                                text: 'Values'
                              }                
                            },
                    legend: {
                              layout: 'vertical',
                              align: 'right',
                              verticalAlign: 'middle'
                            },
               plotOptions: {
                    	      line: {
                     	      dataLabels: {
                     	        enabled: true
                     	      }
                     	    }
                     	  },
                    series: []
                   };

                     options.series = JSON;

                     // Create the chart
                     var chart = new Highcharts.Chart(options);
      }
  
 	 
 </script> 
   
  
     
  </head>
  
  <body class="hold-transition skin-blue sidebar-mini" onload="loadScript()">
    	<%	List<UserDeviceMapping> udmList=(List<UserDeviceMapping>)request.getAttribute("udmList"); 
    		Map<String,Object> waterConsumptionUnits=(Map<String,Object>)request.getAttribute("waterConsumptionDetails");%>
							 
  <div class="wrapper">  
  	
 <%@include file="Header.jsp"%>  
 
	<div class="content-wrapper">
		
			<section class="content">
		 		<div class="content-wrap box box-primary">
		 		
		 			<div class="row">
							<div class="col-sm-12 text-right ">	
							   <img src="images/user_iocn_header.png" />&nbsp;<b>Welcome <%=userSession.getUname()%> , Date: <%=new Date() %></b> 
							</div>
													
						</div><br/>
						
						
					<div class="row">
					  <div class="col-md-12">					       
					    <div class="box box-solid">
					        <div class="box-header">
					            <i class="fa fa-user"></i>
								<h3 class="box-title">
									<b>User-Device Details</b>
								</h3>				           
					
					            <div class="box-tools pull-right">
					                <button type="button" class="btn btn-default btn-sm" data-widget="collapse"><i class="fa fa-minus"></i>
					                </button>
					                <button type="button" class="btn btn-default btn-sm"></i></button>
					            </div>
					        </div>
					           
					            <div class="box-body">	       
					              	    							
									   
									   <div class="info-box col-sm-4 mar-top-25" >											
										  	<span class="info-box-icon bg-yellow"><i class="fa fa-edit"></i></span>
										  	<div class="info-box-content">
											    <span class="info-box-text">EDIT USER INFO</span><br/>
											    <a href="personalInfo"><span class="fa fa-edit"> <b>EDIT</b></span></a>											   
										 	</div>
									   </div>
									   
									    <div class="info-box col-sm-4 mar-top-25" >											
										  	<span class="info-box-icon bg-green"><i class="fa fa-tint"></i></span>
										  	<div class="info-box-content">
											    <span class="info-box-text">Water-Consumption</span><br/>
											     <a href="endUserWaterConsumption"><span class="fa fa-tint"> <b>Report</b></span></a>	
											    
											    
										 	</div>
									    </div> 
									    
									    <div class="info-box col-sm-4 mar-top-25" >											
										  	<span class="info-box-icon bg-orange"><i class="fa fa-file"></i></span>
										  	<div class="info-box-content">
											    <span class="info-box-text">User-Device</span><br/>
											     <a href="userDeviceMapped"><span class="fa fa-file"> <b>Mapping</b></span></a>	
											    
											    
										 	</div>
									    </div>
									   
									   
							   </div>
						   </div>
						 </div>
					  </div>
					  
					  <div class="row">
						<div class="col-md-12">
							<div class="box box-success">
								<div class="box-header with-border">
								<i class="fa fa-area-chart"></i>
									<h3 class="box-title">
										<b>Total Water Consumptions</b>
									</h3>
									
									<%-- <form name="form1" action="#" onsubmit="return confirmValidate();" method="post">
										
								  <table class="table">
															
								  		
								  <tr>      
								       <td>
								        <label>From Date</label>
								        <div class='input-group date' id='datetimepicker1'>
								          <input type='text' name="fromDate" id="fromDate" class="form-control" placeholder="Please choose"/>
								            <span class="input-group-addon">
								            	<span class="glyphicon glyphicon-calendar"></span>    
								            </span>
								         </div>
								      </td>
								      
							           <td>
							       		 <label>To Date</label>
									        <div class='input-group date' id='datetimepicker2'>
									          <input type='text' name="toDate" id="toDate" class="form-control" placeholder="Please choose" />
									            <span class="input-group-addon">
									            	<span class="glyphicon glyphicon-calendar"></span>
									            </span>
									        </div>
							           </td>	           							           
							            		
							          	<td>
							       		 <label>Water Meter</label>
									        <div>
									        	 <select name="devid" class="form-control" id="devid" >
											    	<option value="0">Please Select Water Meter</option> 
											    	<%if(udmList!=null && !udmList.isEmpty()){
											    		for(UserDeviceMapping udm : udmList){%>	
											    			<option value="<%=udm.getDevEUI()%>"><%=udm.getDevNode()+"->"+udm.getDevEUI()%></option>
											    		<%} 
											    	}%>
											    </select> 
									        </div>
							           </td>
							           
							           				            
							          
							            
							            <td>
							       		 <label>Action</label>
									        <div>
									         <input type="submit"  class="form-control" style="background-color:#C0C0C0;" value="Submit"/>
									            
									        </div>
							            </td>
							            
							            
        							</tr>
									
									
								</table>	
							 </form> --%>

									<div class="box-tools pull-right">
										<button type="button" class="btn btn-box-tool"
											data-widget="collapse">
											<i class="fa fa-minus"></i>
										</button>
										<button type="button" class="btn btn-default btn-sm" onclick="reload()">
											<i class="fa fa-refresh"></i>
										</button>
									</div>
								</div>
								<!-- /.box-header -->
								<%//if(!waterConsumptionUnits.isEmpty()){ 
								for(Map.Entry<String,Object> map : waterConsumptionUnits.entrySet()){%>
								<div class="box-body">
									<div class="info-box col-sm-6 mar-top-25">
										<span class="info-box-icon bg-blue"><i
											class="fa fa-tint"></i></span>
										<div class="info-box-content">
											<span class="info-box-text"><%=map.getKey()%></span>
											<span class="info-box-number"><b><%=map.getValue()%></b></span>
										</div>
									</div>
									
								</div>
								<%//} 
									}%>

								<!-- /.box-footer -->
							</div>
							<!-- /.box -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
					  
					  
					  <div class="row">
					  <div class="col-md-12">					       
					    <div class="box box-solid">
					        <div class="box-header">
					            <i class="fa fa-cc-visa"></i>
								<h3 class="box-title">
									<b>Make A Payment</b>
								</h3>				           
					
					            <div class="box-tools pull-right">
					                <button type="button" class="btn btn-default btn-sm" data-widget="collapse"><i class="fa fa-minus"></i>
					                </button>
					                <button type="button" class="btn btn-default btn-sm"></i></button>
					            </div>
					        </div>
					           
					            <div class="box-body">	       
					              	    							
									 
									   
									    <div class="info-box col-sm-6 mar-top-25" >											
										  	<span class="info-box-icon bg-red"><i class="fa fa-user"></i></span>
										  	<div class="info-box-content">
											    <span class="info-box-text">Make a payment</span><br/>
											    <!-- <span class="info-box-number"><b>2</b></span>  -->
											    <a href="payUBills"><span class="fa fa-cc-visa"> <b>Pay Bills</b></span></a>
											    
										 	</div>
									  </div> 
									   
									   
							   </div>
						   </div>
						 </div>
					  </div>
									   
		 		
						 
		 		
		 			<div class="row">
						<div class="col-md-12">
							<div class="box box-success">
								<div class="box-header with-border">
								<i class="fa fa-area-chart"></i>
									<h3 class="box-title">
										<b>Water Consumption in Litres-Hourly</b>
									</h3>
									
									<form name="form1" action="#" onsubmit="return confirmValidate();" method="post">
										
								  <table class="table">
															
								  		
								  <tr>      
								       <td>
								        <label>From Date</label>
								        <div class='input-group date' id='datetimepicker1'>
								          <input type='text' name="fromDate" id="fromDate" class="form-control" placeholder="Please choose"/>
								            <span class="input-group-addon">
								            	<span class="glyphicon glyphicon-calendar"></span>    
								            </span>
								         </div>
								      </td>
								      
							           <td>
							       		 <label>To Date</label>
									        <div class='input-group date' id='datetimepicker2'>
									          <input type='text' name="toDate" id="toDate" class="form-control" placeholder="Please choose" />
									            <span class="input-group-addon">
									            	<span class="glyphicon glyphicon-calendar"></span>
									            </span>
									        </div>
							           </td>	           							           
							            		
							          	<td>
							       		 <label>Water Meter</label>
									        <div>
									        	 <select name="devid" class="form-control" id="devid" >
											    	<option value="0">Please Select Water Meter</option> 
											    	<%if(udmList!=null && !udmList.isEmpty()){
											    		for(UserDeviceMapping udm : udmList){%>	
											    			<option value="<%=udm.getDevEUI()%>"><%=udm.getDevNode()+"->"+udm.getDevEUI()%></option>
											    		<%} 
											    	}%>
											    </select> 
									        </div>
							           </td>
							           
							           <td>
							       		 <label>Filter By</label>
									        <div>
									          <select name="filter" id="filter" class="form-control">
									            <!-- <option value="0">Select Filter</option> -->
										    	<option value="hourly">Hourly</option>
										    	<option value="days">Days</option>
										    	
										      </select> 
									            
									        </div>
							            </td>					            
							          
							            
							            <td>
							       		 <label>Action</label>
									        <div>
									         <input type="submit"  class="form-control" style="background-color:#C0C0C0;" value="Submit"/>
									            
									        </div>
							            </td>
							            
							            
        							</tr>
									
									
								</table>	
							 </form>

									<div class="box-tools pull-right">
										<button type="button" class="btn btn-box-tool"
											data-widget="collapse">
											<i class="fa fa-minus"></i>
										</button>
										<button type="button" class="btn btn-default btn-sm" onclick="reload()">
											<i class="fa fa-refresh"></i>
										</button>
									</div>
								</div>
								<!-- /.box-header -->
								<div class="box-body">
									<div class="row">
										<div class="col-md-12">
											<div class="chart">
											
												<!-- Sales Chart Canvas -->
												<!-- <canvas id="salesChart" style="height: 180px;"></canvas> -->
												<div id="container"  style="height: 400px;"></div>
																						
   												<p class="text-right">
													<strong>X-axis Date</strong><br /> <strong>Y-axis
														Water Units</strong><br />
												</p>
											</div>
											<!-- /.chart-responsive -->
										</div>
										<!-- /.col -->

										<!-- /.col -->
									</div>
									<!-- /.row -->
								</div>
								<!-- ./box-body -->

								<!-- /.box-footer -->
							</div>
							<!-- /.box -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
					
					
					
					
					
					
					
			      <!-- Main row -->
			      <div class="row">
			        <!-- Left col -->
			        <div class="col-sm-12">
			          <!-- MAP & BOX PANE -->
			          <div class="box box-success">
			            <div class="box-header with-border">
			            <i class="fa fa-map-marker"></i>
			              <h3 class="box-title"><b>Water Reservoirs</b></h3>
			
			              <div class="box-tools pull-right">
			                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
			                </button>
			                <button type="button" class="btn btn-default btn-sm"><i class="fa fa-refresh"></i></button>
			              </div>
			            </div>
			            <!-- /.box-header -->
			            <div class="box-body no-padding">
			              <div class="row">
			                <div class="col-md-12 col-sm-12">
			                  <div class="pad">
			                    <!-- Map will be created here -->
			                   
			                  <iframe  style="float: right; width: 100%" height="300px" src="marklandmarkUserDash?orgId=6" ></iframe>
			                  </div>
			                </div>
			                <!-- /.col -->
			         
			                <!-- /.col -->
			              </div>
			              <!-- /.row -->
			            </div>
			            <!-- /.box-body -->
			          </div>
			          <!-- /.box -->
			         
			        </div>
			        <!-- /.col -->
			
			      
			      </div>
			      <!-- /.row -->
					
		 	</div>
		</section>	
	  <%@include file="Footer.jsp"%>  		
	</div>	
	</div>
			
  </body>
</html>