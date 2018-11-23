<%@page import="java.util.*"%>
<%@page import="com.team.app.constant.*"%>
<%@page import="com.team.app.domain.*"%>
<%@page import="com.team.app.service.impl.UserLoginServiceImpl"%>
<%@ page errorPage="error.jsp" %> 
<header class="main-header" >

   
	    <a href="#" class="logo affix">
			    <svg width="135px" height="50px" >
						  <defs>
						    <filter id="MyFilter" filterUnits="userSpaceOnUse" x="50" y="50" width="200" height="120">
						        <feGaussianBlur in="SourceGraphic" stdDeviation="15" />
						      <feOffset result="offOut" in="SourceAlpha" dx="60" dy="60" />
						    
						    </filter>
						  </defs>
						  <text stroke-width="3.5"  stroke="white" font-size="26" font-family="Verdana" x="0" y="40">USWM</text>
						  
						  Sorry, your browser does not support inline SVG.
				</svg>
		</a> 
	    

	    <!-- Header Navbar: style can be found in header.less -->
	    <nav class="navbar navbar-static-top affix" >
	      <!-- Sidebar toggle button-->
	      <a href="#" class="sidebar-toggle" style="width:2.5em;" data-toggle="offcanvas" role="button">
	      </a> 
	    </nav>
	    
   </header>
  
  
  <aside class="main-sidebar" style="position:fixed;">
  
   <% TblUserInfo userSession = (TblUserInfo) request.getSession().getAttribute("user");
   		            	if (userSession == null){
			                response.sendRedirect("/");
			            }else if(userSession.getRoleBean().getType().equals(AppConstants.admin) || userSession.getRoleBean().getType().equalsIgnoreCase(AppConstants.superAdmin)){
			            
			            
  %>
    <section class="sidebar">
        
      <!-- search form -->
      <!-- <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form> -->
     
      
      <ul class="sidebar-menu">
        <li class="header"><b>MAIN NAVIGATION</b></li>
        <li class="active treeview">
          <a href="home">
            <i class="fa fa-dashboard"></i> <span><b>Dashboard</b></span>
          </a>
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-wrench"></i>
            <span><b>Settings</b></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          
          
          <ul class="treeview-menu">
          	<li><a href="downlinkConfig"><i class="fa fa-circle-o"></i><b>Uplink MPDU Config</b></a></li>
          	<li><a href="retryConfig"><i class="fa fa-circle-o"></i><b>Uplink Retry Config</b></a></li> 
          <!-- 	<li><a href="dateConfig"><i class="fa fa-circle-o"></i><b>Date Configuration</b></a></li> -->
            <li><a href="aplConfig"><i class="fa fa-circle-o"></i><b>APL Config</b></a></li>
            <li><a href="#"><i class="fa fa-paypal"></i><b>Payment-CutOff</b></a></li>           
          </ul>           
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i>
            <span><b>User Mgmt</b></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          
          
          <ul class="treeview-menu">
            <li><a href="userMgmt"><i class="fa fa-circle-o"></i><b>User Subscription</b></a></li>
           <!--  <li><a href="orgUserMgmt"><i class="fa fa-circle-o"></i><b>Organisation-User</b></a></li> -->
           
          </ul>
          
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-cube"></i>
            <span><b>Device Mgmt</b></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          
          
          
         <!--   <ul class="treeview-menu">
            <li><a href="deleteNode"><i class="fa fa-recycle"></i><b>Delete Node</b></a></li>
           
          </ul> -->
           <ul class="treeview-menu">
            <li><a href="delDevEUI"><i class="fa fa-recycle"></i><b>Remove Water Meter Data</b></a></li>
             <li><a href="addDevice"><i class="fa fa-user-plus"></i><b>Add Water Meter</b></a></li>
              <li><a href="deleteNode"><i class="fa fa-recycle"></i><b>Delete Node</b></a></li>
           
          </ul>
          
        </li>
        
         <!-- <li class="treeview"> 
          <a href="#">
            <i class="fa fa-cc-visa"></i>
            <span><b>Pay Bills</b></span>
            <span class="pull-right-container">
              <span class="label label-primary pull-right">1</span>
            </span>
          </a>
          <ul class="treeview-menu">
              <li><a href="payUBills"><i class="fa fa-circle-o"></i><b>Make a payment</b></a></li>                       
          </ul>
          
             
        </li> -->
        
         <li class="treeview">
          <a href="#">
            <i class="fa fa-arrow-right"></i>
            <span><b>Reports</b></span>
            <span class="pull-right-container">
              <span class="label label-primary pull-right">2</span>
            </span>
          </a>
          
          
          <ul class="treeview-menu">
            <li><a href="waterConsumptionCal"><i class="fa fa-circle-o"></i><b>Water Consumption</b></a></li>
             <li><a href="userReport"><i class="fa fa-circle-o"></i><b>User</b></a></li>
             <!-- <li><a href="#"><i class="fa fa-circle-o"></i><b>User-Device-Mapped</b></a></li>
             <li><a href="#"><i class="fa fa-circle-o"></i><b>Payment-Info</b></a></li> -->
           
          </ul>
          
        </li>
        
         <li class="treeview"> 
          <a href="#">
            <i class="fa fa-upload"></i>
            <span><b>Upload</b></span>
            <span class="pull-right-container">
              <span class="label label-primary pull-right">1</span>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="uploadUserSubscription"><i class="fa fa-circle-o"></i><b>User Upload</b></a></li>
                       
          </ul>
          
             
        </li>
        
        
        <li class="treeview"> 
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span><b>Logs</b></span>
            <span class="pull-right-container">
              <span class="label label-primary pull-right">1</span>
            </span>
          </a>
          <ul class="treeview-menu">
            <!-- <li><a href="userInfoHistory"><i class="fa fa-circle-o"></i><b>Admin User</b></a></li> -->
            <li><a href="frameInfos"><i class="fa fa-circle-o"></i> <b>Uplink Log</b></a></li>
            <li><a href="downlinkQueue"><i class="fa fa-circle-o"></i> <b>Downlink Log</b></a></li> 
          </ul>
          
             
        </li>
        
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-bolt"></i>
            <span><b>Sync</b></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="sync"><i class="fa fa-circle-o"></i><b>Sync-Water Meter </b></a></li>
           
          </ul>
        </li>
         <li class="treeview">
          <a href="logout">
            <i class="fa fa-sign-out"></i> <span><b>Sign-Out</b></span>
           
          </a>
        </li>
        
      
      </ul>
    </section>
  <%}else if(userSession.getRoleBean().getType().equals("usr")){ %>
  
    <section class="sidebar">
          
      <ul class="sidebar-menu">
        <li class="header"><b>MAIN NAVIGATION</b></li>
        <li class="active treeview">
          <a href="userHome">
            <i class="fa fa-dashboard"></i> <span><b>Dashboard</b></span>
          </a>
        </li>
        
        
       <!-- <li class="treeview">
          <a href="personalInfo">
            <i class="fa fa-edit"></i>
            <span><b>Edit Information</b></span>
            <span class="pull-right-container">
                      </span>
          </a>
              
          
        </li> -->
        
        <!-- <li class="treeview">
          <a href="#">
            <i class="fa fa-cog"></i>
            <span><b>Device Information</b></span>
            <span class="pull-right-container">
             
            </span>
          </a>
          
                   
        </li> -->
        
        
        
         <!-- <li class="treeview">
          <a href="#">
            <i class="fa fa-arrow-right"></i>
            <span><b>Reports</b></span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          
          
          <ul class="treeview-menu">
            <li><a href="endUserWaterConsumption"><i class="fa fa-circle-o"></i><b>Water Consumption</b></a></li>
            <li><a href="userDeviceMapped"><i class="fa fa-circle-o"></i><b>User-Device Mapping</b></a></li>
                       
          </ul>
          
        </li> -->
        
        
        <!-- <li class="treeview"> 
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span><b>Logs</b></span>
            <span class="pull-right-container">
              <span class="label label-primary pull-right">1</span>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="userInfoHistory"><i class="fa fa-circle-o"></i><b>Admin User</b></a></li>
            <li><a href="userFrameInfos"><i class="fa fa-circle-o"></i> <b>Uplink Log</b></a></li>
                   
          </ul>
          
             
        </li> -->
        
        <!-- <li class="treeview"> 
          <a href="#">
            <i class="fa fa-cc-visa"></i>
            <span><b>Payment</b></span>
            <span class="pull-right-container">
              <span class="label label-primary pull-right">1</span>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="payUBills"><i class="fa fa-circle-o"></i><b>Make a payment</b></a></li>
                       
          </ul>
          
             
        </li> -->
        
       
        
        
        
         <li class="treeview">
          <a href="logout">
            <i class="fa fa-sign-out"></i> <span><b>Sign-Out</b></span>
           
          </a>
        </li>
        
      
      </ul>
    </section>
  <%} %>
  </aside>