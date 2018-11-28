<%@ page errorPage="error.jsp" %> 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Log in</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="css/blue.css">
  <link rel="stylesheet" href="css/style.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
    
	  <!-- jQuery 2.2.3 -->
	<script src="js/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="plugins/iCheck/icheck.min.js"></script>
	
	<%String statusLog=(String)request.getAttribute("statusLog");%>
	<script>
	  $(function () {
	    $('input').iCheck({
	      checkboxClass: 'icheckbox_square-blue',
	      radioClass: 'iradio_square-blue',
	      increaseArea: '20%' // optional
	    });
	  });
	</script>
	
	<script  type="text/javascript">
	function reload(){		
		var passvalid= document.getElementById("sts").value;		
		if(passvalid=="" || passvalid=="null"){
			return true;
		}else{
			window.location.reload();
		}		
	}
	
	 function loadScript(){	
	
		 var statusLog='<%=statusLog%>';
		
		 if(statusLog!='null'){			 
			$("#loginAudit").modal(); 
		 }
		 
	 }
 
	
function browserIdentity()
{
	if (/MSIE (\d+\.\d+);/.test(navigator.userAgent)){ //test for MSIE x.x;
		 var ieversion=new Number(RegExp.$1) // capture x.x portion and store as a number
		  
		   if (ieversion<=7)
		 	 alert("You are using older version of Internet Explorer. Please upgrade your browser.");
		 
}
}
  $(document).ready(function() {
	  alert("hello");
	$( "#uname" ).click(function() {
		var passvalid= document.getElementById("sts").value;
		
		if(passvalid=="" || passvalid=="null"){
			return true;
		}else{
			window.location.reload();
		}
			
		});
	});  

function validate() {
	
	var uname = document.getElementById("uname").value;
	var password = document.getElementById("pass").value;
	//document.getElementById("namevalid").innerHTML = "";
	//document.getElementById("passvalid").innerHTML = "";
	
	
	
	 if (uname.length < 1) {
		document.getElementById("namevalid").innerHTML = "Please enter Username!";
		document.getElementById("uname").focus(); 
		return false;
	}else if (password.length < 1) {
		document.getElementById("passvalid").innerHTML = "Please enter Password!";
		document.getElementById("pass").focus(); 
		return false;
	} 
		return true;
	
	
}
</script>
   
	
	
</head>
<body class="hold-transition login-page" onload="loadScript()">
		<%String statusLogg=(String)request.getAttribute("statusLog");
			String url=(String)request.getAttribute("url");
				String exception=(String)request.getAttribute("exception");
					String className=(String)request.getAttribute("className");
						String methodName=(String)request.getAttribute("methodName");	
							String lineNumber=(String)request.getAttribute("lineNumber");	
				
					
				%>	
<div class="wrapper">
<%
						response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
						response.setHeader("Pragma", "co-cache");
						response.setDateHeader("Expires", 0);
  
						String message="";
						
						try{
							message=request.getParameter("message");
							if(message!=null&&!message.equals(""))
											{
								System.out.println("Hello message");
							}
							else{						
									message = "";
									message = request.getAttribute("status").toString();
									session.setAttribute("status", "");
								}
				
							}catch(Exception e)
							{
								;
							}
					
					%>	

<div class="login-box">
  <div class="login-logo">
    <h3><b>Unizen</b>SWM Sol.</h3>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg"><b>Please Sign-in</b></p>

    <form action="onSubmitlogin" name="user_validation_form" id="user_validation_form" method="post" onsubmit="return validate()">
    
			      <div class="form-group has-feedback">
			      	<input type="text" name="uname" id="uname" class="form-control" placeholder="Username" onclick="reload()"/>
			      	<span class="fa fa-user form-control-feedback"></span>
			        <span id="namevalid" style="color: red;"></span>
			      </div>
			      
			      <div class="form-group has-feedback">
			        <input type="password" name="pass" id="pass" class="form-control" placeholder="Password" onclick="reload()" />
			        <span class="fa fa-lock form-control-feedback"></span>
			        
			        <span id="passvalid" style="color: red;" ><%=message %></span>
					<input type="hidden" name="sts" id="sts" value="<%=(String)request.getAttribute("status")%>">
			      </div>
			      
			     			        
			      <div class="row" style="text-align:center;">
				   				        
				        <!-- /.col -->
				        <div class="col-xs-4">
				          <button type="submit" style="margin-left: 135%;" class="btn btn-primary btn-block btn-flat text-bold">Sign In</button>
				        </div>
				        <!-- /.col -->
			      </div>
    </form>

   
    <!-- /.social-auth-links -->

    <!-- <a href="#">I forgot my password</a><br> -->
    
    <!-- Calling StatusLog Modal -->
    <!-- Modal -->
                                      <div class="modal fade" id="loginAudit" role="dialog">
                                      	<div class="modal-dialog ">    
                                      		<!-- Modal content-->
                                      		<div class="modal-content">
                                      			<div class="modal-header" style="padding:15px 30px;">
                                      				<button type="button" class="close" data-dismiss="modal">&times;</button>
                                      				<h4><span class="fa fa-history"></span> Exception Audit Log </h4>
                                      			</div>
                                      			
                                      			<div class="modal-body" style="padding:40px 50px;">
        		                                      <div class="row" style="overflow-y: auto;">		                                      			
															<div class="col-sm-12 ">
																	<table class="table table-striped">
																	    <thead>
																	      <tr>
																	        <th>URL</th>
																	        <th>Status</th>
																	        <th>Exception_Message</th>
																	        <th>ClassName</th>
																	        <th>MethodName</th>
																	        <th>LineNumber</th>
																	        																      											        
																	      </tr>
																	    </thead>
																	    <tbody>	
																	    <tr>
																	    <%if(statusLogg!=null){%>																	    	
																	   
																		    <td>
																		    	<%=url %>
																		    </td>																		    
																		    <td>
																		      	<%=statusLogg %>
																		    </td>																		   
																		    <td>
																		      	<%=exception%>
																		    </td>
																		     <td>
																		      	<%=className%>
																		    </td>
																		     <td>
																		      	<%=methodName%>
																		     </td>
																		     <td>
																		      	<%=lineNumber%>
																		     </td>
																		    
																		    
																		  <% }%>  
																	    </tr>												 
																	    </tbody>
																	</table>
															
														   </div>
														</div>
					  								
							                   </div>
						
									  
					     					  <div class="modal-footer">
                         						<button type="button" class="btn-default pull-right" data-dismiss="modal"> Cancel</button>
                         					  </div>
                         					  	  
				      					  </div><!-- content close here -->
			                           </div><!-- modal dialog close here -->					             			              
					               </div><!-- modal close here -->		
    

  </div>
  <!-- /.login-box-body -->
</div>
</div>
<!-- /.login-box -->


</body>
</html>
