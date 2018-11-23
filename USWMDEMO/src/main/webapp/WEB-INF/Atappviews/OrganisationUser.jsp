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
    
    <title>Organisation-User Mapping</title>
    
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
	
<!-- Pie Charts... -->
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
    
   <script type="text/javascript">
   function getOrgUserView(){
	   var orgid=document.getElementById("orgid").value;
	   var usertype=document.getElementById("usertype").value;
	   if(orgid=="0"){
		   alert("Please select organisation!");
		   return false;
	   }else{
		   ajaxOnViewOrgUser(orgid,usertype);
		   return false;
	   }
   }
   
   
   function addOrgUser(){
	   var orgid=document.getElementById("orgid").value;
	   if(orgid=="0"){
		   alert("Please select organisation!");
		   return false;
	   }else if ($("input[name=uname]").val() == "") {
			alert("Please specify LoginId");
			return false;
	   }else if ($("input[name=email]").val() == "") {
			alert("Please specify EmailAddress");
			return false;
	   }else if (isNaN($("input[name=contact]").val())
				|| ($("input[name=contact]").val()).length != 10) {
			alert("Please specify 10 digit contact number");
			return false;
	   }else{
		   //ajaxOnAddOrgUser(orgid);
		   return false;
	   }
   }
   
   function ajaxOnViewOrgUser(orgid,usertype)
   {    
	                      	                
                   var url="getOrgUserView?orgId="+orgid+"&ut="+usertype;                                    
                   xmlHttp=GetXmlHttpObject()
                   if (xmlHttp==null)
                   {
                       alert ("Browser does not support HTTP Request");
                       return
                   }                    
                   xmlHttp.onreadystatechange=setOrgUser;	
                   xmlHttp.open("GET",url,true);                
                   xmlHttp.send(null);
                   
               
    }
   

            
            
function getDevEUIByAppID()
{     
	var appid=document.getElementById("appid").value;
	
	if(appid=="0")
    	{                	
    	var devid=document.getElementById("devid");
    		devid.innerHTML='<select name="devname" id="devid"> <option value="0" >--Choose Device EUI--</option></select>';
    	return;
    	}
    else
    	{
    var url="getDevEUIByAppId?appId="+appid;                                    
    xmlHttp=GetXmlHttpObj()
    if (xmlHttp==null)
    {
        alert ("Browser does not support HTTP Request");
        return
    }                    
    xmlHttp.onreadystatechange=addUpdateOrgUser;	
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
        
            function setOrgUser() 
            {                      
                if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
                { 
                    var returnText=xmlHttp.responseText;
                    var appid=document.getElementById("appid");
                    appid.innerHTML='<select  name="appname" id="appid" onchange="getDevEUIByAppID()"><Option value="0">--Choose Application--</Option>'+returnText+'</select>';                                             
                }
            }
            
            function addUpdateOrgUser() 
            {                      
                if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
                { 
                    var returnText=xmlHttp.responseText;
                    var devid=document.getElementById("devid");
                    devid.innerHTML='<select  name="devname" id="devid"><Option value="0">--Choose Device EUI--</Option>'+returnText+'</select>';                                             
                }
            }
     </script>      
     
     
     <script type="text/javascript">
			function showPopup(url) {
				var params = "toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
				size = "height=450,width=520,top=200,left=300," + params;
				if (url == "LandMarkSearch") {
					size = "height=450,width=600,top=200,left=300," + params;
				}
				
				var orgId=document.getElementById("orgid").value;
				if (url == "LandMarkSearch") {
					
					if(orgId=="0")
					{
					alert("Choose Organisation");
					return false;
					}
					url+="?orgId="+orgId;
				}
				
				newwindow = window.open(url, 'name', size);
		
				if (window.focus) {
					newwindow.focus();
				}
			}
</script> 
     
  </head>
  
  <body class="hold-transition skin-blue sidebar-mini">
  
  			<% 
  				Map<String,Object> organisations=(Map<String,Object>)request.getAttribute("organisations");
  			    List<Role> roles=(List<Role>)request.getAttribute("roles");
  			%>
  			
							 
  <div class="wrapper">  
  	<%@include file="Header.jsp"%>  
 
	<div class="content-wrapper">
		
			<section class="content">
		 		<div class="content-wrap box box-primary">
		 		
					
		 		<div class="row">
							<div class="col-sm-12 text-right">
								<img src="images/user_iocn_header.png" />&nbsp;<b>Welcome <%=userSession.getUname()%></b> <a href="logout"><img src="images/logout_icon_header.png" /><b>Log Out</b></a>
							</div>
					
				</div>
		 		
		 		<div class="box-header with-border">
  					  <h5 class="text-blue text-left "><span class="fa fa-user"></span><b>Add Organisation-User</b></h5>
       
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
    				    	
    				    	<form name="form1" action="userSubscription" onsubmit="return confirmValidate();" method="post">
										
								  <table class="table">
								  	
								  
								  	<tr>
								  		<td align="right"><b>Organization:</b></td>
								  			
										<td>
										 <select name="orgid" id="orgid" onchange="getAppByOrgID()">
										    <option value="0">--Choose Organisation--</option>	
										    <%if(userSession.getRoleBean().getType().equalsIgnoreCase(AppConstants.superAdmin)){										    
											    if(organisations!=null && !organisations.isEmpty()){
											    	for(Map.Entry<String,Object> map :organisations.entrySet()){%>
											    	    <option value="<%=map.getKey()%>"><%=map.getValue()%></option>
											    	<%}
											    }
										    }else if(userSession.getRoleBean().getType().equalsIgnoreCase(AppConstants.admin)){
										    	if(organisations!=null && !organisations.isEmpty()){
											    	for(Map.Entry<String,Object> map :organisations.entrySet()){
											    		for(UserDeviceMapping udm : userSession.getUserDeviceMappings()){
											    		   if(udm.getOrgId().equals(map.getKey())){%>
											    	    	 <option value="<%=map.getKey()%>"><%=map.getValue()%></option>
											    		   <%}
											    		} 
											    	}
											    }	    
										    }%>
										 </select> 
										</td>
										
									</tr>
									
									<tr>
								  		<td align="right"><b>UserType:</b></td>
								  													
										<td>
										 <select name="usertype" id="usertype">
										    <!-- <option value="0">--Choose UserType--</option> -->	
										    <%if(roles!=null && !roles.isEmpty()){
										    	for(Role r: roles){
											    	if(r.getType().equalsIgnoreCase(AppConstants.admin)){%>
											    		<option value="<%=r.getId()%>" ><%=r.getName()%></option> 
											   		<%}
										    	}	
										     }%> 
										    
										  
										 </select> 
										</td>
										
									</tr>
									
									<!-- <tr>
										<td align="right"><b></b></td>	
									   	<td> <input type="button"  class="formbutton" style="background-color:#3c8dbc;" value="View User" onclick="getOrgUserView()"/></td>
									</tr> -->
									
									<tr>
										<td align="right">
											<input style="margin-left: 130%; background-color:#3c8dbc;"
												type="button" value="View User" class="formbutton"
													onclick="getOrgUserView()" />
										</td>
				
									</tr>
									<tr>	
									   <td align="right"><b>LoginID:</b></td>
									   
										 <td>
										 	<input type="text" name="uname" id="uname" >
										</td>
									</tr>
									
									<tr>	
									   <td align="right"><b>EmailId:</b></td>
									   
										 <td>
										 	<input type="text" name="email" id="email" >
										</td>
									</tr>
									
									<tr>	
									   <td align="right"><b>Contact#:</b></td>
									   
										 <td>
										 	<input type="text"  name="contact" id="contact" >
										</td>
									</tr>
									
															
									
									<tr>	
										<td align="right"></td>
											<td> <input type="button"  class="formbutton" style="background-color:#3c8dbc;" value="Add User" onclick="addOrgUser()"/></td>
										 
									</tr>	
								</table>	
							 </form> 
							</div>	
					   </div>
					   
					    <div class="row" >
    				    	<div class="col-sm-12">	
    				    		 <table class="table">
    				    		 	<thead>
								      <tr>
								      	<th>Organisation</th>
								        <th>UserName</th>
								        <th>Type</th>
								        <th>Email</th>
								        <th>ContactNo</th>
								        <th>CreatedDt</th>
								        <th>UpdatedDt</th>
								        <th>Action</th>
								      </tr>
								    </thead>
								    <tbody>
								    <input type="hidden" id="orgUser">
								    </tbody>
    				    		 </table>
    				    	</div>	 
    				   </div>	
					   
										
					</div>	
			</section>	
			<%@include file="Footer.jsp"%>  		
	</div>	
	</div>
			
  </body>
</html>