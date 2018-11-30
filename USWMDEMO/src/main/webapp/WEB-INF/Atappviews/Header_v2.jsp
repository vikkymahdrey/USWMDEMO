<%@page import="java.util.*"%>
<%@page import="com.team.app.constant.*"%>
<%@page import="com.team.app.domain.*"%>
<%@page import="com.team.app.service.impl.UserLoginServiceImpl"%>
<%@ page errorPage="error.jsp" %> 
<% TblUserInfo userSession = (TblUserInfo) request.getSession().getAttribute("user"); %>
 
  <header class="main-header">
   
  
    <!-- Logo -->
    <a href="#" class="logo">
   
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>A</b>LT</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b> <img src="dashboard/dist/img/logo.png" class="user-image" alt="User Image" height="35" width="35"> <span style="font-family:Gabriola;font-size:25px">Easy Count</span></b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
         
          <!-- Notifications: style can be found in dropdown.less -->
        
          <!-- Tasks: style can be found in dropdown.less -->
         
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="dashboard/dist/img/logo.png" class="user-image" alt="User Image">
              <span class="hidden-xs"><%=userSession.getUname()%></span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="dashboard/dist/img/logo.png" class="img-circle" alt="User Image">

                <p>
                 <%=userSession.getUname()%>
                </p>
              </li>
              <!-- Menu Body -->
             
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat" data-toggle="modal" data-target="#modal-info1">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="#" class="btn btn-default btn-flat" data-toggle="modal" data-target="#modal-info2">Change Password</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
         
        </ul>
      </div>
    </nav>
  </header>
  
  
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
     
      <!-- search form -->
      
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        
        <li>
          <a href="userDashBoard">
            <i class="fa fa-dashboard"></i> <span>Dashboard</span>
            <span class="pull-right-container">
             <!--  <small class="label pull-right bg-green">new</small> -->
            </span>
          </a>
        </li>
        <li>
          <a href="transactions">
           <i class="fa fa-credit-card-alt"></i>  <span>Payments</span>
            <span class="pull-right-container">
             <!--  <small class="label pull-right bg-green">new</small> -->
            </span>
          </a>
        </li>
        <li>
          <a href="issue">
           <i class="fa fa-sign-out"></i>  <span>Issue Report</span>
            <span class="pull-right-container">
             <!--  <small class="label pull-right bg-green">new</small> -->
            </span>
          </a>
        </li>
       <li>
          <a href="logout">
            <i class="fa fa-sign-out"></i> <span>Sign Out</span>
            <span class="pull-right-container">
             <!--  <small class="label pull-right bg-green">new</small> -->
            </span>
          </a>
        </li>
       
      </ul>
    </section>
    <!-- user details edit  model -->
    
    <!-- /.sidebar -->
  </aside>
  <div class="modal  fade in" id="modal-info1" style="display:none;">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color:#235E93">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">×</span></button>
                <h4 class="modal-title" style="color:#ffff">User Details</h4>
              </div>
              <div class="modal-body" style="background-color:#f9f9f9">
                
                <div class="box-body">
              <form role="form">
                
                <div class="form-group">
                  <label>Name</label>
                  <input type="text" class="form-control" placeholder="Enter Name"  readonly="readonly">
                </div>
                
                <div class="form-group">
                  <label for="exampleInputEmail1">Email address</label>
                  <input type="email" class="form-control" id="exampleInputEmail1" name="email" 
                  pattern="[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}"
                  placeholder="Enter email" required>
                </div>
                
                <div class="form-group">
                  <label for="phone">Phone Number</label>
                  <input type="tel" class="form-control" id="phone" name="phone" pattern="[1-9][0-9]{9}" 
                  placeholder="Enter Number" required>
                </div>
                 <div class="form-group text-center">
                  <button  class="btn btn-success" style="background-color:#64B246;width:100px">Edit</button>
                </div>
                </form>
                </div>
                
                 
                 
              </div>
              
             
             
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
        
        <!-- Change Password Model -->
        
        <div class="modal  fade in" id="modal-info2" style="display:none;">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color:#235E93">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">×</span></button>
                <h4 class="modal-title" style="color:#ffff">Change Password</h4>
              </div>
              <div class="modal-body" style="background-color:#f9f9f9">
                
                <div class="box-body">
              <form role="form">
                
                <div class="form-group">
                  <label>Current Password</label>
                  <input type="password" class="form-control" placeholder="Old Password" autocomplete="off" required >
                </div>
                
                <div class="form-group">
                  <label >New Password</label>
                  <input type="password" class="form-control" placeholder="New Password" autocomplete="off" required>
                </div>
                
                <div class="form-group">
                  <label >Confirm NewPassword</label>
                  <input type="password" class="form-control" placeholder="Confirm NewPassword" autocomplete="off" required>
                </div>
                 <div class="form-group text-center">
                  <button  class="btn btn-success" style="background-color:#64B246;width:100px">Save</button>
                </div>
                </form>
                </div>
                
                 
              </div>
              
             
             
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>