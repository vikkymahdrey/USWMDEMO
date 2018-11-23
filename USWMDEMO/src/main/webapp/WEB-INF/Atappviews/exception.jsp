<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Exception</title>
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
	
	 function loadScript(){	
	
		 var statusLog='<%=statusLog%>';
		 if(statusLog!='null'){			 
			$("#loginAudit").modal(); 
		 }
		 
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
						 <div>
						 
							<p><b>Please contact to support team at vikky@unizentechnoglogies.com</b></p> 
						 </div>
							

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
  
		
</body>
</html>
