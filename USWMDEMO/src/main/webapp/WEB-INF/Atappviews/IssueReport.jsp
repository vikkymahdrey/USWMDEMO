<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Feedback</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="dashboard/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="dashboard/bower_components/font-awesome/css/font-awesome.min.css">
	
  <link rel="stylesheet" href="dashboard/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="dashboard/dist/css/skins/_all-skins.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue skin-black sidebar-mini">
<div class="wrapper">

  <%@include file="Header_v2.jsp"%>
  
  <!-- Left side column. contains the logo and sidebar -->
 

  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Issue Report
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Issue Report</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">      
        <div class="col-md-12">
          <div class="box">
            <div class="box-body">
            
            <div class="row">      
              <div class="col-md-2 pull-right">
                    <button type="button" class="btn btn-block  btn-success" data-toggle="modal" data-target="#modal-info" style="background-color:#64B246; ">
                    <b>New Issue</b></button>
              </div>
            </div>
            
              <br>
           <div class="table-responsive">
              <table id="example2" class="table table-hover " style="width:100%">
                <thead class="text-right">
                <tr class="active ">
                  <th>Issue Id</th>
                  <th>Device Name</th>
                  <th>Raised Date</th>
                  <th>Query</th>
                  
                  <th>Status</th>
                  <th>Actions</th>
                </tr>
                </thead>
                 <tbody style="width:100%">
                 <tr>
                  <td>1</td>
                  <td>WM_Demo_P1->1200000000002345 </td>
                  <td>28/11/2018</td>
                   <td>Meter Drained </td>
                 
                  <td>Success</td>
                  <td >
                 
                    <button type="button" class="btn btn-box-tool btn-success " data-toggle="collapse" data-target="#collapse-info"style="color:white; background-color:#64B246;width:100px"><b >Details</b>
                    </button>
                      
                   </td>
                   </tr>
                   <tr  id="collapse-info" class="collapse">
                   <td colspan="6">
                   
                   <div class="box-body">
                   <div class=" direct-chat direct-chat-warning">
                
              
                  <!-- Conversations are loaded here -->
                  <div class="direct-chat-messages">
                    <!-- Message. Default to the left -->
                    <div class="direct-chat-msg">
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-left">Alexander Pierce</span>
                        
                      </div>
                      <!-- /.direct-chat-info -->
                      <div class="direct-chat-text pull-left">
                        Is this template really for free? That's unbelievable! 
                      <br><span class="direct-chat-timestamp pull-right">23 Jan 2:00 pm</span>
                      </div>
                      <!-- /.direct-chat-text -->
                    </div>
                    <!-- /.direct-chat-msg -->

                    <!-- Message to the right -->
                    <div class="direct-chat-msg right">
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-right">Sarah Bullock</span>
                        <span class="direct-chat-timestamp pull-left">23 Jan 2:05 pm</span>
                      </div>
                      <!-- /.direct-chat-info -->
                      <div class="direct-chat-text pull-right">
                        You better believe it!
                        <br>
                        <span class="direct-chat-timestamp pull-left">23 Jan 2:05 pm</span>
                      </div>
                      <!-- /.direct-chat-text -->
                    </div>
                    <!-- /.direct-chat-msg -->

                    <!-- Message. Default to the left -->
                    <div class="direct-chat-msg">
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-left">Alexander Pierce</span>
                      </div>
                      <!-- /.direct-chat-info -->
                      <div class="direct-chat-text pull-left">
                        Working with AdminLTE on a great new app! Wanna join?
                        <br> <span class="direct-chat-timestamp pull-right">23 Jan 5:37 pm</span>
                      </div>
                      <!-- /.direct-chat-text -->
                    </div>
                    <!-- /.direct-chat-msg -->

                    <!-- Message to the right -->
                    <div class="direct-chat-msg right">
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-right">Sarah Bullock</span>
                      </div>
                      <!-- /.direct-chat-info -->
                      <div class="direct-chat-text pull-right">
                        I would love to.
                       <br> <span class="direct-chat-timestamp pull-left">23 Jan 6:10 pm</span>
                      </div>
                      <!-- /.direct-chat-text -->
                    </div>
                    <!-- /.direct-chat-msg -->

                  </div>
                  <!--/.direct-chat-messages-->

                  <!-- Contacts are loaded here -->
                                    <!-- /.direct-chat-pane -->
                </div>   <!-- /.box-body -->
                
                  </div>
                   
                   <div class="box-footer col-xs-12">
                  <form action="#" method="post">
                    <div class="input-group">
                      <input type="text" name="message" placeholder="Type Message ..." class="form-control">
                      <span class="input-group-btn">
                            <button type="button" class="btn btn-success btn-flat">Send</button>
                          </span>
                    </div>
                  </form>
                </div>
                   
                   </td>
                   </tr>
                   <tr >
                  <td >2</td>
                  <td>WM_Demo_P2->0000013450006603
                  </td>
                  <td>30/11/2018</td>
                  <td>Water Leak
                  </td>
                  <td>Pending</td>
                  <td>
                 
                   <button type="button" class="btn btn-box-tool btn-success  text-center " data-toggle="modal" data-target="#modal-info3" style="color:white;background-color:#64B246;width:100Px"><b >Details</b>
                    </button>
                      
                   </td>
                   
                   </tr>
                
              </table>
              </div>
              
            </div>
            <!-- /.box-body -->
          </div>
    </div>
    </div>
    </section>
     
  </div>
<%@include file="footer_v2.jsp"%>

  <!-- Control Sidebar -->
  
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>



<div class="modal  fade in" id="modal-info" style="display:none;">
          <div class="modal-dialog">
            <div class="modal-content" style="background-color:#eee ">
              <div class="modal-header" style=" border: 0">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">×</span></button>
               <h4 class="modal-title"><b>New Issue</b></h4>
              </div>
              <div class="modal-body">
              
              <from>
              <div class="row"> 
              
              <div class="col-xs-12">
              
              
              <div class="col-xs-6">
              
              <div class="form-group">
              
                  <select class="form-control" style=" border: 0;border-radius:6px;" required>
                  <option>Select Device</option>
                    <option>option 1</option>
                    <option>option 2</option>
                    <option>option 3</option>
                    <option>option 4</option>
                    <option>option 5</option>
                  </select>
              </div>
              
              </div>
              <div class="col-xs-6">
              <div class="form-group">
             
                  <select class="form-control" style=" border: 0; border-radius:6px;" required>
                    <option>Select Query</option>
                    <option>option 1</option>
                    <option>option 2</option>
                    <option>option 3</option>
                    <option>option 4</option>
                    <option>option 5</option>
                  </select>
              </div>
              
              </div>
              
              <div class="col-xs-12">
              <div class="form-group">
                  
                  <textarea class="form-control" rows="15" placeholder="Enter your comment here" style=" border:0;border-radius:6px;" required></textarea>
                </div>
              </div>
              <div class="col-xs-12">
              <div class="form-group">
                  
                 <button type="button" class="btn btn-block btn-success" style="background-color:#64B246;width:100px">Submit</button>
                </div>
              </div>
              </div>
              
              </div>
              
              </from>
 
              </div>
             </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>


<!-- View issue details model -->
    <div class="modal fade in" id="modal-info3" style="display:none;">
          <div class="modal-dialog">
            <div class="modal-content" >
              <div class="modal-header" style="background-color:#eee ">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">×</span></button>
               <h4 class="modal-title"><b>Issue Details</b></h4>
              </div>
              <div class="modal-body">
              
               <div class=" direct-chat direct-chat-warning">
                
              
                  <!-- Conversations are loaded here -->
                  <div class="direct-chat-messages">
                    <!-- Message. Default to the left -->
                    <div class="direct-chat-msg">
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-left">Alexander Pierce</span>
                        
                      </div>
                      <!-- /.direct-chat-info -->
                      <div class="direct-chat-text pull-left">
                        Is this template really for free? That's unbelievable! 
                      <br><span class="direct-chat-timestamp pull-right">23 Jan 2:00 pm</span>
                      </div>
                      <!-- /.direct-chat-text -->
                    </div>
                    <!-- /.direct-chat-msg -->

                    <!-- Message to the right -->
                    <div class="direct-chat-msg right">
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-right">Sarah Bullock</span>
                        <span class="direct-chat-timestamp pull-left">23 Jan 2:05 pm</span>
                      </div>
                      <!-- /.direct-chat-info -->
                      <div class="direct-chat-text pull-right">
                        You better believe it!
                        <br>
                        <span class="direct-chat-timestamp pull-left">23 Jan 2:05 pm</span>
                      </div>
                      <!-- /.direct-chat-text -->
                    </div>
                    <!-- /.direct-chat-msg -->

                    <!-- Message. Default to the left -->
                    <div class="direct-chat-msg">
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-left">Alexander Pierce</span>
                      </div>
                      <!-- /.direct-chat-info -->
                      <div class="direct-chat-text pull-left">
                        Working with AdminLTE on a great new app! Wanna join?
                        <br> <span class="direct-chat-timestamp pull-right">23 Jan 5:37 pm</span>
                      </div>
                      <!-- /.direct-chat-text -->
                    </div>
                    <!-- /.direct-chat-msg -->

                    <!-- Message to the right -->
                    <div class="direct-chat-msg right">
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-right">Sarah Bullock</span>
                      </div>
                      <!-- /.direct-chat-info -->
                      <div class="direct-chat-text pull-right">
                        I would love to.
                       <br> <span class="direct-chat-timestamp pull-left">23 Jan 6:10 pm</span>
                      </div>
                      <!-- /.direct-chat-text -->
                    </div>
                    <!-- /.direct-chat-msg -->

                  </div>
                  <!--/.direct-chat-messages-->

                  <!-- Contacts are loaded here -->
                                    <!-- /.direct-chat-pane -->
                </div>   <!-- /.box-body -->
                
                 
                  </div>
                   
                   <div class="box-footer col-xs-12">
                  <form action="#" method="post">
                    <div class="input-group">
                      <input type="text" name="message" placeholder="Type Message ..." class="form-control">
                      <span class="input-group-btn">
                            <button type="button" class="btn btn-success "style="background-color:#64B246;width:100px">Send</button>
                          </span>
                    </div>
                    <br>
                    <div class="input-group">
                      <button type="button" class="btn btn-success" style="background-color:#64B246;width:100px">Re-Open</button>
                          
                    </div>
                  </form>
                </div>
                  

                   </td>
                   </tr>
                
              </table>
            </div>
            <!-- /.box-body -->
          </div>
    </div>
    </div>
    </section>
     
  </div>
  
	

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
      <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Home tab content -->
      <div class="tab-pane" id="control-sidebar-home-tab">
        <h3 class="control-sidebar-heading">Recent Activity</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-birthday-cake bg-red"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                <p>Will be 23 on April 24th</p>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-user bg-yellow"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Frodo Updated His Profile</h4>

                <p>New phone +1(800)555-1234</p>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-envelope-o bg-light-blue"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Nora Joined Mailing List</h4>

                <p>nora@example.com</p>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-file-code-o bg-green"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Cron Job 254 Executed</h4>

                <p>Execution time 5 seconds</p>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

        <h3 class="control-sidebar-heading">Tasks Progress</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Custom Template Design
                <span class="label label-danger pull-right">70%</span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Update Resume
                <span class="label label-success pull-right">95%</span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-success" style="width: 95%"></div>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Laravel Integration
                <span class="label label-warning pull-right">50%</span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-warning" style="width: 50%"></div>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Back End Framework
                <span class="label label-primary pull-right">68%</span>
              </h4>
                <div class="progress-bar progress-bar-primary" style="width: 68%"></div>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

      </div>
      <!-- /.tab-pane -->
      <!-- Stats tab content -->
      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
      <!-- /.tab-pane -->
      <!-- Settings tab content -->
      <div class="tab-pane" id="control-sidebar-settings-tab">
        <form method="post">
          <h3 class="control-sidebar-heading">General Settings</h3>

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Report panel usage
              <input type="checkbox" class="pull-right" checked>
            </label>

            <p>
              Some information about this general settings option
            </p>
          </div>
          <!-- /.form-group -->

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Allow mail redirect
              <input type="checkbox" class="pull-right" checked>
            </label>

            <p>
              Other sets of options are available
            </p>
          </div>
          <!-- /.form-group -->

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Expose author name in posts
              <input type="checkbox" class="pull-right" checked>
            </label>

            <p>
              Allow the user to show his name in blog posts
            </p>
          </div>
          <!-- /.form-group -->

          <h3 class="control-sidebar-heading">Chat Settings</h3>

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Show me as online
              <input type="checkbox" class="pull-right" checked>
            </label>
          </div>
          <!-- /.form-group -->

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Turn off notifications
              <input type="checkbox" class="pull-right">
            </label>
          </div>
          <!-- /.form-group -->

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Delete chat history
              <a href="javascript:void(0)" class="text-red pull-right"><i class="fa fa-trash-o"></i></a>
            </label>
          </div>
          <!-- /.form-group -->
        </form>
      </div>
      <!-- /.tab-pane -->
    </div>
  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>


<div class="modal modal-info fade in" id="modal-info" style="display:none;">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">×</span></button>
                <h4 class="modal-title">New Issue</h4>
              </div>
              <div class="modal-body">
                
                <form>
                <div class="form-group">
                  <label>Device</label>
                  <select class="form-control" required>
                    <option>option 1</option>
                    <option>option 2</option>
                    <option>option 3</option>
                    <option>option 4</option>
                    <option>option 5</option>
                  </select>
                </div>
                
                <div class="form-group">
                  <label>Issue Query</label>
                  <select class="form-control" required>
                    <option>option 1</option>
                    <option>option 2</option>
                    <option>option 3</option>
                    <option>option 4</option>
                    <option>option 5</option>
                  </select>
                </div>
                
                <div class="form-group">
                  <label>Issue</label>
                  <textarea class="form-control" rows="3" placeholder="Enter detailed issue..." required></textarea>
                </div>
                
              </div>
              <div class="modal-footer">
                <input type="submit" class="btn btn-outline" value="Submit">
>>>>>>> f9f940aa0316e3fc1a1a222eb4f5156b972c8974
              </div>
             </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>


<!-- jQuery 3 -->
<script src="dashboard/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="dashboard/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<script src="dashboard/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dashboard/dist/js/demo.js"></script>
</body>
</html>