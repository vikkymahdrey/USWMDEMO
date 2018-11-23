<%--
    Author     : Vikky
--%>

<%@ page errorPage="error.jsp" %> 
<%@page import="com.team.app.constant.AppConstants"%>
<%@page import="com.team.app.domain.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>MPDU Setting</title>
    
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
    
   <script type="text/javascript">
   
   function confirmValidate(){
	   
	   var orgid=document.getElementById("orgid").value;
 	  	var appid=document.getElementById("appid").value;
 	  	var devid=document.getElementById("devid").value;
 	  	var orgDesc=document.getElementById("orgN").value;
	  	var appDesc=document.getElementById("appN").value;
	  	var devDesc=document.getElementById("devN").value;
	  	var hourly=document.getElementById("hourly").value;
	  	var packet=document.getElementById("packet").value;
 		
	   	   
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
	   }else if(hourly=="0"){
		   alert("Choose Packets per day!");
		   document.getElementById("hourly").focus();
		   return false;
	   }else if(packet=="0"){
		   alert("Choose Packet length");
		   document.getElementById("packet").focus();
		   return false;
	   }else{		   
			   $.ajax({
	               url: 'downlinkSetting',
	               type: 'POST',
	               //data: 'orgId='+orgid+'&appId='+appid+'&devId='+devid,
	               data: jQuery.param({ orgId: orgid, appId : appid, devId : devid,hourly:hourly,packet:packet}) ,
	               success: function (data) {
	            	   alert(data);
	            	   if(data==="success"){
	            		   window.location.reload();
	            	   }
	            	  
	              
	                //window.location.reload();
	            	   //$(".success").html(data);
	                   	
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
	        			    	
	        				     document.getElementById("oId").innerHTML="<b>"+resultant[i].organisation+"</b>";  
	        				     document.getElementById("orgN").value=resultant[i].desc; 
	        				}else if(resultant[i].application!==undefined){
								 document.getElementById("aId").innerHTML="<b>"+resultant[i].application+"</b>"; 
								 document.getElementById("appN").value=resultant[i].desc; 
							}else if(resultant[i].device!==undefined){
								 document.getElementById("dId").innerHTML="<b>"+resultant[i].device+"</b>";
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
   
   
   function getPacketByHourly(){
	   
	   var hourly=document.getElementById("hourly").value;
	  
	   if(hourly=="0")
   		{    
	   		var packet=document.getElementById("packet");
	   			packet.innerHTML='<select name="packet" class="form-control" id="packet"> <option value="0" >Please Choose</option></select>';
	   			return;
   		}else{
		   $.ajax({
	           url: 'getPacketByHourly',
	           type: 'POST',
	           data: jQuery.param({ hourly: hourly }) ,
	           success: function (data) {
	        	   
		        	   if(data.length==0){
		        		   alert("Packet size not associated on this hour config!");            		
		        	   }else{
		        		   var packet=document.getElementById("packet"); 
		        		   packet.innerHTML='<select  name="packet" class="form-control" id="packet" >'+data+'</select>';
		        	   }
	        	
	               },
			 		error: function(e){
		     			        alert('Error: ' + e);
		     		 }
	
	              
	           }); 
   		} 
   }
   
function getAppByOrgID()
{    
            	var orgid=document.getElementById("orgid").value;
            	
            	if(orgid=="0")
                	{      
            	
                	var appid=document.getElementById("appid");
                	var devid=document.getElementById("devid");
                		appid.innerHTML='<select name="appname" id="appid" onchange="getDevEUIByAppID()"> <option value="0" >Please Choose</option></select>';
                		devid.innerHTML='<select name="devname" id="devid"> <option value="0" >Please Choose</option></select>';
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
    		devid.innerHTML='<select name="devname" id="devid"> <option value="0" >Please Choose</option></select>';
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
                    var devid=document.getElementById("devid");
                    appid.innerHTML='<select  name="appname" id="appid" onchange="getDevEUIByAppID()"><Option value="0">Please Choose</Option>'+returnText+'</select>';  
                    devid.innerHTML='<select  name="devname" id="devid"><Option value="0">Please Choose</Option></select>';
                }
            }
            
            function setDevEUI() 
            {                      
                if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
                { 
                    var returnText=xmlHttp.responseText;
                    var devid=document.getElementById("devid");
                    devid.innerHTML='<select  name="devname" id="devid"><Option value="0">Please Choose</Option>'+returnText+'</select>';                                             
                }
            }
     </script>       
     
  </head>
  
  <body class="hold-transition skin-blue sidebar-mini">
  
  			<% 
  				Map<String,Object> organisations=(Map<String,Object>)request.getAttribute("organisations");
  				List<TblKeywordType> keyTypes=(List<TblKeywordType>)request.getAttribute("keyTypes");
  				List<TblDownlinkHoulyConfig> configs=(List<TblDownlinkHoulyConfig>)request.getAttribute("configs");
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
  					  <h5 class="text-blue text-left "><span class="fa fa-gear"></span>&nbsp;&nbsp;<b>Uplink MPDU Setting </b></h5>
       
   				</div><!-- /.box-header -->
		 							
   						
   						  <div class="row" >
    				    	<div class="col-sm-6">	
    				    	
    				    	<form name="form1" action="#" onsubmit="return confirmValidate();" method="post">
										
								  <table class="table">
								  <tr>								  		
										<td align="right"><b>Housing Type</b></td>
										<td>	
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
			 																	    
										</td>
																	  		
								    </tr>
								  	<tr>
								  		<td id="oId" align="right"><b><%=(String)request.getAttribute("orgN")%></b></td>
								  			<%-- <td><input type="text" value="<%=orgName%>"  class="formbutton" id="<%=orgId%>" name="orgName" /></td>--%>
										
										<td>
										 <select name="orgname" class="form-control" id="orgid" onchange="getAppByOrgID()">
										    <option value="0">Please Choose</option>	
										    <%if(userSession.getRoleBean().getType().equalsIgnoreCase(AppConstants.superAdmin)){										    
											    if(organisations!=null && !organisations.isEmpty()){
											    	for(Map.Entry<String,Object> map :organisations.entrySet()){%>
											    	    <option value="<%=map.getKey()+":"+map.getValue()%>"><%=map.getValue()%></option>
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
										</td>
									</tr>
									<tr>	
									   <td id="aId" align="right"><b><%=(String)request.getAttribute("appN")%></b></td>
									   
										 <td>
										 	<select name="appname" class="form-control" id="appid" onchange="getDevEUIByAppID()">
										    	<option value="0">Please Choose</option>	
										    </select> 
										</td>
									</tr>
									
									<tr>	
									   <td id="dId" align="right"><b><%=(String)request.getAttribute("devN")%></b></td>
									   
										 <td>
										 	<select name="devname" class="form-control" id="devid" >
										    	<option value="0">Please Choose</option>	
										    </select> 
										</td>
									</tr>
									
									
									<tr>	
									   <td align="right"><b>Hourly Packets</b></td>
									   
										 <td>
										 	<select name="hourly" class="form-control" id="hourly" onchange="getPacketByHourly()" >
										    	<option value="0">Please Choose</option>
										    	<% if(configs!=null && !configs.isEmpty()){
													for(TblDownlinkHoulyConfig d : configs){ %>
										    			<option value="<%=d.getId()%>"><%=d.getPerday()%></option>	
										    		<%}
												}%>
										
										    </select> 
										</td>
									</tr>
									
									<tr>	
									   <td align="right"><b>Packet length</b></td>
									   
										 <td>
										 	<select name="packet" class="form-control" id="packet" >
										    	<option value="0">Please Choose</option>										    	
										    </select> 
										</td>
									</tr>
										
										
									<tr>	
										<td align="right"></td>
											<td> <input type="submit"  class="form-control text-bold " style="background-color:#3c8dbc;color:white; " value="Done"/></td>
										 
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