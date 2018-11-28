<%--
    Author     : Vikky
--%>

<%@ page errorPage="error.jsp" %> 
<%@page import="com.team.app.domain.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Create User</title>
    
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
   
   function confirmValidate(){
	   
	   var orgid=document.getElementById("orgid").value;
 	  	var appid=document.getElementById("appid").value;
 	  	var devid=document.getElementById("devid").value;
 	  	var usertype=document.getElementById("usertype").value;
 	    var orgDesc=document.getElementById("orgN").value;
	  	var appDesc=document.getElementById("appN").value;
	  	var devDesc=document.getElementById("devN").value;
	  	
	  	var email=document.getElementById("email").value;
	  	var contact=document.getElementById("contact").value;
		var uname=document.getElementById("uname").value;
		var landMarkID=document.getElementById("landMarkID").value;
	  	
	  	var emailRegx = /^((([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/;
	  	var nameRegx = /^[A-Za-z][A-Za-z0-9 ]*$/;
	  	var phoneRegx = /^\+?\d?\d?\d{10}$/;
 		
	   	   
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
	   }else if(usertype == "0") {
			alert("Choose User Type");
			document.getElementById("usertype").focus();
			return false;
	   }else if(nameRegx.test(uname)==false) {
			alert("Invalid Username Format!! Spaces and Special characters are not allowed");
			document.getElementById("uname").focus();
			return false;
	   }
 	   /* else if ($("input[name=uname]").val() == "") {
			alert("Choose Username");
			document.getElementById("uname").focus();
			return false;
	   } */else if(emailRegx.test(email)==false) {
			alert("Invalid Email ID Format!");
			document.getElementById("email").focus();
			return false;
	   }else if (contact==""){
		   alert("Please Specify Phone Number");
			document.getElementById("contact").focus();
			return false;
	   }else if (phoneRegx.test(contact)==false){
		   alert("Invalid Mobile Number !");
			document.getElementById("contact").focus();
			return false;
	   }
 	   /* else if (isNaN($("input[name=contact]").val())
				|| ($("input[name=contact]").val()).length != 10) {
			alert("Choose 10 digit Mobile Number");
			document.getElementById("contact").focus();
			return false;
	   } */else if ($("input[name=area]").val() == "") {
			alert("Please Add Location");
			document.getElementById("area").focus();
			return false;
	   }else if ($("input[name=place]").val() == "") {
			alert("Choose Add Location");
			document.getElementById("place").focus();
			return false;
	   }else if ($("input[name=landmark]").val() == "") {
			alert("Choose Add Location");
			document.getElementById("landmark").focus();
			return false;
	   }else{
		   $.ajax({
	           url: 'subscription',
	           type: 'POST',
	           data: jQuery.param({orgid: orgid,appid:appid,devid:devid,uname:uname,email:email,contact:contact,usertype:usertype,landMarkID:landMarkID }) ,
	           success: function (data) {	        	          		
		        	   if(data=='success'){
		        		   alert("User registered successfully.Please check your email notification for activation!");
		        		   window.location="http://139.59.14.31:8081/USWMDEMO/userReport";
		        		
		        	   }else if(data=='exist') {
		        		   alert("Selected Water Meter is already mapped to an existing user!");  
		        		   document.getElementById("devid").focus();
		        	   }else if(data=='failed') {
		        		   alert("System Error! User registration failed!"); 
		        		   location.reload();
		        	   }    		   
	        	
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
   
   
   function checkUsername(){
		var uname=document.getElementById("uname").value;
	   if(uname!=''){
	   $.ajax({
           url: 'validateUserName',
           type: 'POST',
           //data: 'orgId='+orgid+'&appId='+appid+'&devId='+devid,
           data: jQuery.param({uname: uname}) ,
           success: function (data) {
        	   if(data!=''){
        	  		alert(data); 
        	  		document.getElementById("uname").value = "";
        	  		document.getElementById("uname").focus();
        	  		
        	   }
               },
		 		error: function(e){
	     			        alert('Error: ' + e);
	     		 }

              
           });
	   }  
	  //return false;
   }
   
   function checkEmail(){
	   var email=document.getElementById("email").value;
	   if(email!=''){
		   $.ajax({
	           url: 'validateEmail',
	           type: 'POST',
	           data: jQuery.param({email: email}) ,
	           success: function (data) {
	        	   if(data!=''){
	        	  		alert(data);
	        	  		document.getElementById("email").value = ""; 
	        	  		document.getElementById("email").focus();
	        	  		
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
    var url="getDevEUIByAppId?appId="+appid;                                    
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
     </script>      
     
     
     <script type="text/javascript">
			function showPopup(url) {
				var params = "toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
				size = "height=450,width=520,top=200,left=300," + params;
				if (url == "LandMarkSearch") {
					size = "height=450,width=600,top=200,left=300," + params;
				}
				
				var orgId=document.getElementById("orgid").value;
				var orgDesc=document.getElementById("orgN").value;
				if (url == "LandMarkSearch") {
					
					if(orgId=="0")
					{
					alert(orgDesc);
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
  		  		List<TblKeywordType> keyTypes=(List<TblKeywordType>)request.getAttribute("keyTypes");
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
  					  <h5 class="text-blue text-left "><span class="fa fa-user"></span><b> Create User</b></h5>
       
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
    				    	<div class="col-sm-6">	
    				    	
    				    	<form name="form1" action="#" onsubmit="return confirmValidate();" >
										
								  <table class="table" >
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
										<td>
										 <select name="orgid" class="form-control" id="orgid" onchange="getAppByOrgID()">
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
										 	<select name="appid" class="form-control" id="appid" onchange="getDevEUIByAppID()">
										    	<option value="0">Please Choose</option>	
										    </select> 
										</td>
									</tr>
									
									<tr>	
									   <td id="dId" align="right"><b><%=(String)request.getAttribute("devN")%></b></td>
									   
										 <td>
										 	<select name="devid" id="devid" class="form-control">
										    	<option value="0">Please Choose</option>	
										    </select> 
										</td>
									</tr>
									
									<tr>
								  		<td align="right"><b>User Type</b></td>
								  													
										<td>
										 <select name="usertype" id="usertype" class="form-control">
										    <option value="0">Please Choose</option>	
										    <%if(roles!=null && !roles.isEmpty()){
										    	for(Role r: roles){
											    	if(r.getType().equalsIgnoreCase(AppConstants.user)){%>
											    		<option value="<%=r.getId()%>" ><%=r.getName()%></option> 
											   		<%}
										    	}	
										     }%> 
										 </select> 
										</td>
									</tr>
									
									<tr>	
									   <td align="right"><b>Username</b></td>
									   
										 <td>
										 	<input type="text" name="uname" class="form-control" id="uname" onchange="checkUsername()" placeholder="Enter Username">
										</td>
									</tr>
									
									<tr>	
									   <td align="right"><b>Email ID</b></td>
									   
										 <td>
										 	<input type="text" name="email" class="form-control" id="email" onchange="checkEmail()" placeholder="Enter Email ID">
										</td>
									</tr>
									
									<tr align="right">	
									   <td><b>Mobile Number</b></td>
									   
										 <td>
										 	<input type="text" class="form-control" name="contact" id="contact" placeholder="Enter Mobile Number" >
										</td>
									</tr>
									
									
									
									<tr >
										<td align="right">
											 <input style="margin-left: 220%; background-color:#3c8dbc;color:white;" type="button" class="form-control"  value="Add Location" 
													onclick="showPopup('LandMarkSearch')" />											
										</td>
				
									</tr>
									
									<tr>	
									   <td align="right"><b>Area</b></td>
									   
										 <td>
										 	<input type="text" class="form-control" name="area" id="area" readonly/>
										 	<input type="hidden" id="landMarkID" name="landMarkID" />
										</td>
									</tr>
									<tr>	
									   <td align="right"><b>Place</b></td>
									   
										 <td>
										 	<input type="text" class="form-control" name="place" id="place" readonly/>
										</td>
									</tr>
									<tr>	
									   <td align="right"><b>Landmark</b></td>
									   
										 <td>
										 	<input type="text" class="form-control" name="landmark" id="landmark" readonly/>
										</td>
									</tr>
									
									<tr>	
										<td align="right"></td>
											<td> <input type="submit"  class="form-control"  style="background-color:#3c8dbc;color:white;" value="Create user"/></td>
										 
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