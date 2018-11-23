<%--
    Document   : home page
    Author     : Vikky
--%>

<%@ page errorPage="error.jsp" %> 
<%@page import="com.team.app.domain.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
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
  <link rel="stylesheet" href="css/modal.css">
  
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
 	
<script type="text/javascript">
/* $(document).ready(function(){ 
	 $('.userCountModal').click(function(){
	        $("#userModal").modal();
	    });  
}); */
  
  $(function () {
	  
	   /*   $('#datetimepicker1').datetimepicker({maxDate: moment()});
		  $('#datetimepicker2').datetimepicker({maxDate: moment()}); */
	  $('#datetimepicker1').datetimepicker();
	  $('#datetimepicker2').datetimepicker();
	  
	   $('.userCountModal').click(function(){
	        $("#userModal").modal();
	    });  
	  
	  $('.orgCountModal').click(function(){
	        $("#orgModal").modal();     	  
	    });
	  
	  
	  $('.usrModal').click(function(){
  		var orgid=this.getAttribute("ajaxid");
  		
			  $.ajax({
              url: 'getUserByOrgIDModal',
              type: 'POST',
              data: jQuery.param({ orgId: orgid}),
              success: function (data) {
            	 document.getElementById("users").innerHTML=data; 	           	  		
              },
              error: function(e){
 	     			        alert('Error: ' + e);
 	     	  }                 
              }); 
		  
		  $("#uModal").modal(); 
  		}); 
	  
	  $('.appModal').click(function(){
		   var orgid=this.getAttribute("ajaxid");
		   $.ajax({
              url: 'getAppByOrgIDModal',
              type: 'POST',
              data: jQuery.param({ orgId: orgid}) ,
              success: function (data) {
            	 document.getElementById("applications").innerHTML=data;
            	 
            	  $('.devModal').click(function(){
            		var appid=this.getAttribute("ajaxid");
          			  $.ajax({
          	              url: 'getDevByAppID',
          	              type: 'POST',
          	              data: jQuery.param({ appId: appid}) ,
          	              success: function (data) {
          	            	 document.getElementById("devices").innerHTML=data; 	           	  		
          	              },
          	              error: function(e){
          	 	     			        alert('Error: ' + e);
          	 	     	  }                 
          	              }); 
          			  
          			  $("#devModalId").modal(); 
          	  		}); 
            	  
            	          	  
              },
              error: function(e){
 	     			        alert('Error: ' + e);
 	     	  }                 
              }); 
		  
		  $("#appModalId").modal();
  		});
	  
	  
	 
	  
	     	   
  });
  
  var jsonVal;
  
  function confirmValidate(){
	   
	   var orgid=document.getElementById("orgid").value;
	  	var appid=document.getElementById("appid").value;
	  	var devid=document.getElementById("devid").value;
	  	var fromDate=document.getElementById("fromDate").value;
	  	var toDate=document.getElementById("toDate").value;
	  	var filter=document.getElementById("filter").value;
	    var orgDesc=document.getElementById("orgN").value;
	  	var appDesc=document.getElementById("appN").value;
	  	var devDesc=document.getElementById("devN").value;
	  		   	   
	   if(orgid=="0"){
		   alert(orgDesc);
		   document.getElementById("orgid").focus();
		   return false;
	   }else if(appid=="0"){
		   alert(appDesc);
		   document.getElementById("appid").focus();
		   return false;
	   }else if(devid=="0"){
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
               url: 'getGraphVal',
               type: 'POST',
               data: jQuery.param({ orgId: orgid, appId : appid, devId : devid,fromDate : fromDate, toDate:toDate,type:filter }) ,
               success: function (data) {
            	   var obj=eval("(function(){return " + data + ";})()");
          		   var resultant=obj.result;
          		 
            	   if(resultant.length==0){
            		   alert("FromDate is after ToDate! ' Incorrect dates '");            		
            	   }
            	   jsonVal=data;
            		  
            	
              
                   //$(".success").html(data);
               //window.location.reload();
               loadScript();
                   },
  		 		error: function(e){
  	     			        alert('Error: ' + e);
  	     		 }

                  
               }); 
		  
		   return false;
		
   		} 
  }
  
  
  function getKeywords(){	  
	   var typeId=document.getElementById("typeId").value;
	     
	   $.ajax({
           url: 'getKeywordByTypeId',
           type: 'POST',
           data: jQuery.param({ typeId: typeId }) ,
           success: function (data) {
        	       var obj=eval("(function(){return " + data + ";})()");
      		       var resultant=obj.result;        		
	        	   if(resultant.length==0){
	        		   alert("Data not found!");            		
	        	   }else{
	        		   for (var i = 0; i <resultant.length; i++) {
	        			    if(resultant[i].organisation!==undefined){
	        			    	
	        				     document.getElementById("oId").innerHTML=resultant[i].organisation;  
	        				     document.getElementById("orgN").value=resultant[i].desc; 
	        				}else if(resultant[i].application!==undefined){
								 document.getElementById("aId").innerHTML=resultant[i].application; 
								 document.getElementById("appN").value=resultant[i].desc; 
							}else if(resultant[i].device!==undefined){
								 document.getElementById("dId").innerHTML=resultant[i].device;
								 document.getElementById("devN").value=resultant[i].desc; 
							}       			    
	        			}	  
	        	   }
        	
               },
		 		error: function(e){
	     			        alert('Error: ' + e);
	     		 }

              
           }); 
	   
  }  
  /*Calling for graph*/
  
 
  function loadScript() {
	 var resultant;
	 var JSON;
	 var dateVal;
	 var unitsVal;
	 
	 if( typeof this.jsonVal !== 'undefined' ) {
		 //var obj=JSON.parse(jsonVal);
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
		 //alert('DateArr'+dateVal);
		 //alert('UnitArr'+unitsVal);
	 }else{
		 dateVal=['FromDate..','ToDate']; 
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
                              text: 'Water Consumption Units Graph'
                            },
                     xAxis: {
                              categories: dateVal
                              //type: 'datetime'
                            },
                     yAxis: {
                          	  min: 0,
                          	  max:500,
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
  
 
  
  
function getAppByOrgID()
{    
           	var orgid=document.getElementById("orgid").value;
           	
             	if(orgid=="0")
               	{      
           	
               	var appid=document.getElementById("appid");               	
               		appid.innerHTML='<select name="appid" id="appid" onchange="getDevEUIByAppID()"> <option value="0" >Please Choose</option></select>';
               	
               	var devid=document.getElementById("devid");
               		devid.innerHTML='<select name="devid" id="devid"> <option value="0" >Please Choose</option></select>';
               		return;
               	}
               else
               	{
               	                
               var url="getApplications?orgId="+orgid;                                    
               xmlHttp=GetXmlHttpObject()
               if (xmlHttp==null)
               {
                   alert ("Browser does not support HTTP Request");
                   return
               }                    
               xmlHttp.onreadystatechange=setApplication;	
               xmlHttp.open("GET",url,true);                
               xmlHttp.send(null);
               
               	}
}
           
           
function getDevEUIByAppID()
{     
	var appid=document.getElementById("appid").value;
	
	if(appid=="0")
   	{                	
   	var devid=document.getElementById("devid");
   		devid.innerHTML='<select name="devid" id="devid"> <option value="0" >Please Choose</option></select>';
   	return;
   	}
   else
   	{
   var url="getDevEUISync?appId="+appid;                                    
   xmlHttp=GetXmlHttpObj()
   if (xmlHttp==null)
   {
       alert ("Browser does not support HTTP Request");
       return
   }                    
   xmlHttp.onreadystatechange=setDevEUI;	
   xmlHttp.open("GET",url,true);                
   xmlHttp.send(null);
   
   	}
}
               
           function GetXmlHttpObject()
           {
               var xmlHttp=null;
               if (window.XMLHttpRequest) 
               {
                   xmlHttp=new XMLHttpRequest();
               }                
               else if (window.ActiveXObject) 
               { 
                   xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
               }

               return xmlHttp;
           }
           
           
           function GetXmlHttpObj()
           {
               var xmlHttp=null;
               if (window.XMLHttpRequest) 
               {
                   xmlHttp=new XMLHttpRequest();
               }                
               else if (window.ActiveXObject) 
               { 
                   xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
               }

               return xmlHttp;
           }
       
           function setApplication() 
           {                      
               if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
               { 
                   var returnText=xmlHttp.responseText;
                   var appid=document.getElementById("appid");
                   	appid.innerHTML='<select  name="appid" id="appid" onchange="getDevEUIByAppID()"><Option value="0">Please Choose</Option>'+returnText+'</select>';
                   var devid=document.getElementById("devid");
              		devid.innerHTML='<select name="devid" id="devid"> <option value="0" >Please Choose</option></select>';
               }
           }
           
           function setDevEUI() 
           {                      
               if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
               { 
                   var returnText=xmlHttp.responseText;
                   var devid=document.getElementById("devid");
                   devid.innerHTML='<select  name="devid" id="devid"><Option value="0">Please Choose</Option>'+returnText+'</select>';                                             
               }
           }
	   
  
  
/*   function loadScript() {
      var JSON = [
                   { name: 'Pay Off',
                     data: [500.0, 9.5, 14.5, 180.4, 21.5, 25.2, 206.5, 23.3, 180.3, 13.9, 900.6]
                   },
                   {
                     name: 'Profit',
                     data: [800.0, 402.2, 510.7, 820.5, 1100.9, 1520.2, 173.0, 164.6, 145.2, 10.3, 6.6, 4.8]
                   }
                 ];
      var options = {
                     chart: {
                              renderTo: 'container',
                              type: 'line'
                            },
                     title: {
                              text: 'Water Consumption Units Graph'
                            },
                     xAxis: {
                              categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
                              //type: 'datetime'
                            },
                     yAxis: {
                          	  min: 0,
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
      } */
  
 	 
 </script> 
   
  
     
  </head>
  
  <body class="hold-transition skin-blue sidebar-mini" onload="loadScript()">
  		   <% 
  			Map<String,Object> organisations=(Map<String,Object>)request.getAttribute("organisations");
  		  	List<TblKeywordType> keyTypes=(List<TblKeywordType>)request.getAttribute("keyTypes");
  		 	List<TblKeywordType> userInfoList=(List<TblKeywordType>)request.getAttribute("userInfoList");
  		 	String appUser=(String)request.getAttribute("appUserCount");
  		 	//String loraServerUser=(String)request.getAttribute("userCount");
  		 	String orgCount=(String)request.getAttribute("orgCount"); 
  		 	/* Long curMonthWaterUnits=(Long)request.getAttribute("curMonthWaterUnits"); 
  		 	if(curMonthWaterUnits==null){
  		 		curMonthWaterUnits=0L;	
  		 	} */
  			%>
							 
  <div class="wrapper">  
  	
 <%@include file="Header.jsp"%>  
 
	<div class="content-wrapper">
		
			<section class="content">
		 		<div class="content-wrap box box-primary">
		 		
		 				<div class="row">
							<div class="col-sm-12 text-right ">	
							   <img src="images/user_iocn_header.png" />&nbsp;<b> Welcome <%=userSession.getUname()%> , Date: <%=new Date() %></b> 
							</div>
													
						</div><br/>
						
			 		
						
					<div class="row">
					  <div class="col-md-12">					       
					    <div class="box box-solid">
					        <div class="box-header">
					            <i class="fa fa-user"></i>
								<h3 class="box-title">
									<b>User Details</b>
								</h3>				           
					
					            <div class="box-tools pull-right">
					                <button type="button" class="btn btn-default btn-sm" data-widget="collapse"><i class="fa fa-minus"></i>
					                </button>
					                <button type="button" class="btn btn-default btn-sm"><i class="fa fa-refresh"></i></button>
					            </div>
					        </div>
					           
					            <div class="box-body">					             
					              	    							
									  <div class="info-box col-sm-6 mar-top-25" >											
										  	<span class="info-box-icon bg-green"><i class="fa fa-user"></i></span>
										  	<div class="info-box-content">
											    <span class="info-box-text">Enterprise User Subscription</span>
											    <%-- <span class="info-box-number"><b><%=userInfo.get(0)%></b></span> --%>
											    <a href="#" class="userCountModal"><span class="info-box-number"><b><%=appUser%></b></span></a>
										 	</div>
									  </div> 
									  
									  									  
									   <div class="info-box col-sm-6 mar-top-25" >											
										  	<span class="info-box-icon bg-yellow"><i class="fa fa-user"></i></span>
										  	<div class="info-box-content">
											    <span class="info-box-text">Lora Server Information</span>
											    <a href="#" class="orgCountModal"><span class="info-box-number"><b><%=orgCount%></b></span></a>
											    <%-- <span class="info-box-number"><b><a href="getOrgansiation"><%=orgCount%></a></b></span> --%>
										 	</div>
									   </div>
									   
									   
									<%-- <div class="info-box col-sm-6 mar-top-25" >											
										  	<span class="info-box-icon bg-blue"><i class="fa fa-user"></i></span>
										  	<div class="info-box-content">
											    <span class="info-box-text">Current Month Water Consumption Units</span>
											     <span class="info-box-number"><b><%=curMonthWaterUnits%></b></span> 											    
										 	</div>
								   </div>  --%>
									  
									  <!-- Modal -->
                                      <div class="modal fade" id="userModal" role="dialog">
                                      	<div class="modal-dialog modal-lg">    
                                      		<!-- Modal content-->
                                      		<div class="modal-content">
                                      			<div class="modal-header" style="padding:15px 30px;">
                                      				<button type="button" class="close" data-dismiss="modal">&times;</button>
                                      				<h4><span class="fa fa-user"></span> Enterprise User Subscription </h4>
                                      			</div>
                                      			
                                      			<div class="modal-body" style="padding:40px 50px;">
        		                                     <form name="userList" >
		                                      			<div class="row" style="overflow-y: auto;">
		                                      			
															<div class="col-sm-12 ">
									
														     <display:table  class="table table-hover  text-center"  name="<%=userInfoList%>" id="row"
																		 requestURI="" defaultsort="1" defaultorder="descending" pagesize="50">
																<display:column  property="id" title="ID" sortable="true" headerClass="sortable" />
																<display:column  property="uname" title="UserName" sortable="true"  />
																<display:column  property="emailId" title="EmailID" sortable="true"  />
																<display:column  property="contactnumber" title="MobileNo." sortable="true"  />
																<display:column  property="status" title="Status" sortable="true"  />
																<display:column  property="createddt" title="CreatedDate" format="{0,date,dd-MM-yyyy HH:mm:ss}" sortable="true"  />
																		 
															</display:table> 
															
														   </div>
														</div>
					  								 </form>	
							                   </div>
						
									  
					     					  <div class="modal-footer">
                         						<button type="submit" class="btn-default pull-right" data-dismiss="modal"> Cancel</button>
                         					  </div>
                         					  	  
				      					  </div><!-- content close here -->
			                           </div><!-- modal dialog close here -->					             			              
					               </div><!-- modal close here -->	
					               
					               
					               
					                <!-- Modal -->
                                      <div class="modal fade" id="orgModal" role="dialog">
                                      	<div class="modal-dialog ">    
                                      		<!-- Modal content-->
                                      		<div class="modal-content">
                                      			<div class="modal-header" style="padding:15px 30px;">
                                      				<button type="button" class="close" data-dismiss="modal">&times;</button>
                                      				<h4><span class="fa fa-building"></span> Lora Server Organisation </h4>
                                      			</div>
                                      			
                                      			<div class="modal-body" style="padding:40px 50px;">
        		                                      <div class="row" style="overflow-y: auto;">		                                      			
															<div class="col-sm-12 ">
																	<table class="table">
																	    <thead>
																	      <tr>
																	        <th>Id</th>
																	        <th>Organisation_Name</th>
																	        <th>User_Info</th>
																	        
																	      </tr>
																	    </thead>
																	    <tbody>
															<% int i=1;
															if(organisations!=null && !organisations.isEmpty()){
																for(Map.Entry<String,Object> map :organisations.entrySet()){%>					
																		  <tr>
																	        <td><a ajaxid="<%=map.getKey()%>" href="#" class="appModal"><span class="info-box-number"><b><%=map.getKey()%></b></span></a>
																	        </td>
																	        <td><%=map.getValue()%></td>
																	        <td><a ajaxid="<%=map.getKey()%>" href="#" class="usrModal"><span class="info-box-number">view user</span></a></td>
																	        																	     
																	      </tr>
																	     
																	<% i++;
																	}
																}%>  
																
																 
																	    </tbody>
																	</table>
															
														   </div>
														</div>
					  								
							                   </div>
						
									  
					     					  <div class="modal-footer">
                         						<button type="submit" class="btn-default pull-right" data-dismiss="modal"> Cancel</button>
                         					  </div>
                         					  	  
				      					  </div><!-- content close here -->
			                           </div><!-- modal dialog close here -->					             			              
					               </div><!-- modal close here -->		
					               
					               
					                <!-- Modal -->
                                      <div class="modal fade" id="appModalId" role="dialog">
                                      	<div class="modal-dialog ">    
                                      		<!-- Modal content-->
                                      		<div class="modal-content">
                                      			<div class="modal-header" style="padding:15px 30px;">
                                      				<button type="button" class="close" data-dismiss="modal">&times;</button>
                                      				<h4><span class="fa fa-building-o"></span> Organisation-Applications </h4>
                                      			</div>
                                      			
                                      			<div class="modal-body" style="padding:40px 50px;">
        		                                      <div class="row" style="overflow-y: auto;">		                                      			
															<div class="col-sm-12 ">
																	<table class="table">
																	    <thead>
																	      <tr>
																	        <th>Id</th>
																	        <th>Application_Name</th>
																	        <th>OrganisationID</th>																	        
																	      </tr>
																	    </thead>
																	    <tbody id="applications">																																														 
																	    </tbody>
																	</table>
															
														   </div>
														</div>
					  								
							                   </div>
						
									  
					     					  <div class="modal-footer">
                         						<button type="submit" class="btn-default pull-right" data-dismiss="modal"> Cancel</button>
                         					  </div>
                         					  	  
				      					  </div><!-- content close here -->
			                           </div><!-- modal dialog close here -->					             			              
					               </div><!-- modal close here -->				
					               
					               <!-- Modal -->
                                      <div class="modal fade" id="devModalId" role="dialog">
                                      	<div class="modal-dialog ">    
                                      		<!-- Modal content-->
                                      		<div class="modal-content">
                                      			<div class="modal-header" style="padding:15px 30px;">
                                      				<button type="button" class="close" data-dismiss="modal">&times;</button>
                                      				<h4><span class="fa fa-building-o"></span> Application-Nodes </h4>
                                      			</div>
                                      			
                                      			<div class="modal-body" style="padding:40px 50px;">
        		                                      <div class="row" style="overflow-y: auto;">		                                      			
															<div class="col-sm-12 ">
																	<table class="table">
																	    <thead>
																	      <tr>
																	        <th>DeviceEUI</th>
																	        <th>DeviceName</th>
																	        <th>ApplicationID</th>																	        
																	      </tr>
																	    </thead>
																	    <tbody id="devices">																																														 
																	    </tbody>
																	</table>
															
														   </div>
														</div>
					  								
							                   </div>
						
									  
					     					  <div class="modal-footer">
                         						<button type="submit" class="btn-default pull-right" data-dismiss="modal"> Cancel</button>
                         					  </div>
                         					  	  
				      					  </div><!-- content close here -->
			                           </div><!-- modal dialog close here -->					             			              
					               </div><!-- modal close here -->		
					               
					               
					               <!-- Modal -->
                                      <div class="modal fade" id="uModal" role="dialog">
                                      	<div class="modal-dialog ">    
                                      		<!-- Modal content-->
                                      		<div class="modal-content">
                                      			<div class="modal-header" style="padding:15px 30px;">
                                      				<button type="button" class="close" data-dismiss="modal">&times;</button>
                                      				<h4><span class="fa fa-building-o"></span> Organisation-Users </h4>
                                      			</div>
                                      			
                                      			<div class="modal-body" style="padding:40px 50px;">
        		                                      <div class="row" style="overflow-y: auto;">		                                      			
															<div class="col-sm-12 ">
																	<table class="table">
																	    <thead>
																	      <tr>
																	        <th>Id</th>
																	        <th>UserName</th>
																	        <th>isAdmin</th>
																	        <th>organisationID</th>
																	        																	        																        
																	      </tr>
																	    </thead>
																	    <tbody id="users">																																														 
																	    </tbody>
																	</table>
															
														   </div>
														</div>
					  								
							                   </div>
						
									  
					     					  <div class="modal-footer">
                         						<button type="submit" class="btn-default pull-right" data-dismiss="modal"> Cancel</button>
                         					  </div>
                         					  	  
				      					  </div><!-- content close here -->
			                           </div><!-- modal dialog close here -->					             			              
					               </div><!-- modal close here -->												              
					             			              
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
										<b>Water Consumption in Litres</b>
									</h3>
									
									<form name="form1" action="#" onsubmit="return confirmValidate();" method="post">
										
								  <table class="table">
								  <tr>
								  
								  		<td> 
									   		<label>Housing Type</label>
									   		<div>
											 	<select name="typeId" class="form-control" id="typeId" onchange="getKeywords()">
											    	<!-- <option value="0">Select Housing Type</option> -->
											    	<%if(keyTypes!=null && !keyTypes.isEmpty()){
											    		for(TblKeywordType type :keyTypes){%>	
											    			<option value="<%=type.getId()%>"><%=type.getType()%></option>
											    		<%} 
											    	}%>
											    </select> 
											    <%
											    for(TblKeyword key : keyTypes.get(0).getTblKeywords()){
											    	if(key.getKey().equalsIgnoreCase(AppConstants.orgName)){
											    	   	request.setAttribute("orgN", key.getValue());%>											    	
											    	     <input type="hidden" name="orgN" id="orgN" value="<%=key.getDesc()%>"/> 
											    	     
				 									<%}else if(key.getKey().equalsIgnoreCase(AppConstants.appName)){ 
				 										request.setAttribute("appN", key.getValue());%>
				 										<input type="hidden" name="appN" id="appN" value="<%=key.getDesc()%>"/>
				 										
				 									<%}else if(key.getKey().equalsIgnoreCase(AppConstants.devName)){
				 										request.setAttribute("devN", key.getValue());%>
				 										<input type="hidden" name="devN" id="devN" value="<%=key.getDesc()%>"/>
				 										
				 									<%}%>	
				 								<%} %>	
			 									
										    </div>
										</td>
										
								  		<td>
								  			 <label id="oId"><%=(String)request.getAttribute("orgN")%></label> 
								  			 <div>										          												  
												  <select name="orgid" class="form-control" id="orgid" onchange="getAppByOrgID()">
										    			<option value="0">Please choose</option>	
													    <%if(userSession.getRoleBean().getType().equalsIgnoreCase(AppConstants.superAdmin)){										    
														    if(organisations!=null && !organisations.isEmpty()){
														    	for(Map.Entry<String,Object> map :organisations.entrySet()){%>
														    	    <option value="<%=map.getKey()+":"+map.getValue()%>"><%=map.getValue()%></option>
														    	   <%--  <input type="hidden" id="orgName" name="orgName" value=<%=map.getValue()%>>	 --%>
														    	<%}
														    }
													    }else if(userSession.getRoleBean().getType().equalsIgnoreCase(AppConstants.admin)){
													    	if(organisations!=null && !organisations.isEmpty()){
														    	for(Map.Entry<String,Object> map :organisations.entrySet()){
														    		for(UserDeviceMapping udm : userSession.getUserDeviceMappings()){
														    		   if(udm.getOrgId().equals(map.getKey())){%>
														    	    	 <option value="<%=map.getKey()+":"+map.getValue()%>"><%=map.getValue()%></option>
														    	    	 
														    		   <%}
														    		} 
														    	}
														    }	    
													    }%>
										 		</select>
												   									            
									        </div>										
										</td>
										
									    <td> 
									   		<label id="aId"><%=(String)request.getAttribute("appN")%></label>
									   		<div>
											 	<select name="appid" class="form-control" id="appid" onchange="getDevEUIByAppID()">
											    	<option value="0">Please choose</option>	
											    </select> 
										    </div>
										</td>
									
									
									
									    <td>
									   		<label id="dId"><%=(String)request.getAttribute("devN")%></label>
										  	<div>
									          <select name="devid" id="devid" class="form-control">
										    	<option value="0">Please choose</option>	
										      </select> 
									            
									        </div>
										</td>
									</tr>
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
							       		 <label>Filter By</label>
									        <div>
									          <select name="filter" id="filter" class="form-control">
									            <!-- <option value="0">Select Filter</option> -->
										    	<option value="days">Per Day</option>
										    	<option value="hourly">Per Hour</option>
										    	<!-- <option value="Hours">Hourly</option>	
										    	<option value="Minutes">Minutes</option> -->	
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
										<button type="button" class="btn btn-default btn-sm">
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
			                   
			                  <iframe  style="float: right; width: 100%" height="300px" src="marklandmarkdash?orgId=6" ></iframe>
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