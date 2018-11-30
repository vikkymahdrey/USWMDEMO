<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
 <title>Dash board</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet"
	href="dashboard/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="dashboard/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->

<!-- Theme style -->
<link rel="stylesheet" href="dashboard/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="dashboard/dist/css/skins/_all-skins.min.css">
<!-- Morris chart -->
<link rel="stylesheet"
	href="dashboard/bower_components/morris.js/morris.css">

<!-- Date Picker -->
<link rel="stylesheet"
	href="dashboard/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="dashboard/bower_components/bootstrap-daterangepicker/daterangepicker.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet"
	href="dashboard/customcolors/unizencolors.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<!-- Google Font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue skin-black" onload="loadData()">

<div class="modal" id="modal-info" style="display: none;">
              
              <div class="modal-body">
              <div class="row">
            <div class="col-sm-4 col-md-2">
              
            </div>
            <!-- /.col -->
            <div class="col-sm-4 col-md-2">
              
            </div> <div class="col-sm-4 col-md-2">
              
            </div>
            <!-- /.col -->
           
            <!-- /.col -->
            <div class="col-sm-4 col-md-2">
             
             <i class="fa fa-spinner fa-spin" style="font-size:100px;color:#235E93"></i>
            </div>
            <!-- /.col -->
            <div class="col-sm-4 col-md-2">
              
            </div>
            <!-- /.col -->
            <div class="col-sm-4 col-md-2">
             
              
            </div>
            
            <!-- /.col -->
          </div>
              </div>
            
            <!-- /.modal-content -->
         
          <!-- /.modal-dialog -->
        </div>

	<div class="wrapper">


		<!-- Left side column. contains the logo and sidebar -->

		<%@include file="Header_v2.jsp"%>


		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Total Water Consumption <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-refresh fa-spin" style="font-size:24px;" onclick="loadData()"></i> </a></li>
				
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Donot delete this tab -->
				
				<div id="refTotal" style="display: none" class="col-lg-4 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-green">
							<div class="inner">
								<h3>
									53<sup style="font-size: 20px">%</sup>
								</h3>

								<p></p>
							</div>
							<div class="icon">
								<i class="ion ion-stats-bars"></i>
							</div>
							<a href="#" class="small-box-footer"><!--  More info --><i
								class="fa fa-arrow-circle-right"></i></a>
						</div>
					</div>
				
				<div id="totalConsumption" class="row">

				
				</div>
				<!-- /.row -->

				<!-- Main row -->
				<div class="row">
					<!-- Left col -->
					<section class="col-lg-12 connectedSortable">
						<!-- Custom tabs (Charts with tabs)-->
						<div class="nav-tabs-custom">
							<!-- Tabs within a box -->
							<div class="box-header with-border">
								<h3 class="box-title">Daily history</h3>

							
							</div>
							<div class="tab-content no-padding">
								<!-- Morris chart - Sales -->
								<div class="chart tab-pane active" id="daily-chart"
									style="position: relative; height: 300px;"></div>

							</div>
						</div>
					


					</section>
					
				</div>
			
			</section>
			<section class="content">
				
				<div class="row">

				
					<div class="col-md-12">
						
						
						<div class="box box-success">
							<div class="box-header with-border">
								<h3 class="box-title">Monthly history </h3>

								
							</div>
							<div class="box-body chart-responsive">
								<div class="chart" id="bar-chart"
									style="height: 300px; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
								
									<div class="morris-hover morris-default-style"
										style="left: 981.852px; top: 112px; display: none;">
										<div class="morris-hover-row-label">2011</div>
										<div class="morris-hover-point" style="color: #00a65a">
											CPU: 75</div>
										<div class="morris-hover-point" style="color: #f56954">
											DISK: 65</div>
									</div>
								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->

					</div>
					<!-- /.col (RIGHT) -->
				</div>
				<!-- /.row -->



			</section>


			<section class="content">

				<div class="row">
					<div class="col-md-4">
						<!-- AREA CHART -->

						<!-- /.box -->

						<!-- DONUT CHART -->
						<div class="box box-danger">
							<div class="box-header with-border">
								<h3 class="box-title">Donut Chart</h3>

								
							</div>
							<div class="box-body chart-responsive">
								<div class="chart" id="sales-chart1"
									style="height: 300px; position: relative;">
									
								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->

					</div>
					
					<div class="col-md-4">
						<!-- AREA CHART -->

						<!-- /.box -->

						<!-- DONUT CHART -->
						<div class="box box-danger">
							<div class="box-header with-border">
								<h3 class="box-title">Donut Chart</h3>

								
							</div>
							<div class="box-body chart-responsive">
								<div class="chart" id="sales-chart2"
									style="height: 300px; position: relative;">
									
								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->

					</div>
					<div class="col-md-4">
						<!-- AREA CHART -->

						<!-- /.box -->

						<!-- DONUT CHART -->
						<div class="box box-danger">
							<div class="box-header with-border">
								<h3 class="box-title">Donut Chart</h3>

								
							</div>
							<div class="box-body chart-responsive">
								<div class="chart" id="sales-chart3"
									style="height: 300px; position: relative;">
																	</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->

					</div>
				</div>
				<!-- /.row -->

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
			<%@include file="footer_v2.jsp"%>


	
	
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

	<!-- jQuery 3 -->
	<script src="dashboard/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- jQuery UI 1.11.4 -->
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="dashboard/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Morris.js charts -->
	<script src="dashboard/bower_components/raphael/raphael.min.js"></script>
	<script src="dashboard/bower_components/morris.js/morris.min.js"></script>
	<!-- Sparkline -->
		<!-- jvectormap -->
	
	<!-- jQuery Knob Chart -->
	<script
		src="dashboard/bower_components/jquery-knob/dist/jquery.knob.min.js"></script>
	<!-- daterangepicker -->
		<script
		src="dashboard/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
	<!-- datepicker -->
	<script
		src="dashboard/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
	<!-- Bootstrap WYSIHTML5 -->
	
	<!-- Slimscroll -->
	<script
		src="dashboard/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	
	<!-- AdminLTE App -->
	<script src="dashboard/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script src="dashboard/dist/js/pages/dashboard.js"></script>
	<!-- AdminLTE for demo purposes -->

	<script>
	
	var loader = document.getElementById("modal-info");
	
	
	setInterval(loadData, 60000);
	
	
	var load = false;
	function loaderz(){
		
		if(load == false){
			loader.setAttribute("style","display:block");
			load = true;	
		}else{
			loader.setAttribute("style","display:none");
			load = false;
		}
		
	}
	
	function loadData(){
	
		getcurrebtDaysReport();
		getMonthDates();
		createdonotjson();
		lastFirstmonthdonut();
		lastSecondmonthdonut();
		lastThirdmonthdonut();
		getWeeksDates();
		drawbarchart();		
		
	}
	
	

	
	
		var dayreportjson = {}
		var totalConsumption = {}
		var dateJSON={"FirstMonth":{from:" ",to:" "},"SecondMonth":{from:" ",to:" "},"ThiredMonth":{from:" ",to:" "}};

		var monthconsumtion = {"FirstMonth":'',"SecondMonth":'',"ThiredMonth":''}
		
		var weekJSON={"FirstWeek":{from:" ",to:" "},"SecondWeek":{from:" ",to:" "},"ThirdWeek":{from:" ",to:" "},"FourthWeek":{from:" ",to:" "}};
		const monthNames = ["January", "February", "March", "April", "May", "June",
			  "July", "August", "September", "October", "November", "December"
			];
		
		function drawbarchart(){
			
			
var tempdata = []
var yykeys = []



for(var devices in totalConsumption){

yykeys.push(devices.split("->")[0])
}

			
			for(var week in weekJSON){
				
				
				
				
				var consuptionref = {y:''+weekJSON[week].from+'-'+weekJSON[week].to};
				for(var device in totalConsumption){
					
				
					var responsejson = JSON.parse(getConsumtion(device.split("->")[1],weekJSON[week].from,weekJSON[week].to,null))
					
					
					var result = responsejson.result;
					
					var tempconsumtion = 0;
					
					for(var obj in result){
						
						tempconsumtion = tempconsumtion + result[obj].units;
						
					}
					
					consuptionref[''+device.split("->")[0]+''] = tempconsumtion
					

				}
				tempdata.push(consuptionref)
			}
			
			
			var bar = new Morris.Bar({
				element : 'bar-chart',
				resize : true,
				data : tempdata,
				barColors :  [ '#F58C1F', '#64B246', '#91191C','#235E93','#235E93', '#64B246', '#91191C','#F58C1F'],
				xkey : 'y',
				ykeys : yykeys,
				labels : yykeys,
				hideHover : 'auto'
			});
			
			
		}
		
		
		function getWeeksDates()
		{
		
			
		   var date = new Date();
		   var year = date.getFullYear(), month = date.getMonth();

		  
		   weekJSON.FirstWeek.from = new Date(year, month, 1).toLocaleDateString();
		   weekJSON.FirstWeek.to=new Date(year, month, 8).toLocaleDateString();
		   
		   weekJSON.SecondWeek.from = (month+1)+"/08/"+year
		   weekJSON.SecondWeek.to=new Date(year, month, 15).toLocaleDateString();
		       
		   weekJSON.ThirdWeek.from= new Date(year, month, 15).toLocaleDateString();
		   weekJSON.ThirdWeek.to=new Date(year, month, 22).toLocaleDateString();
		       
		   weekJSON.FourthWeek.from = new Date(year, month, 22).toLocaleDateString();
		   
		   weekJSON.FourthWeek.to=new Date(year, month + 1, 1).toLocaleDateString();      
		
		   weekJSON.FourthWeek.to =   weekJSON.FourthWeek.to.split("/")[0]+"/0"+weekJSON.FourthWeek.to.split("/")[1]+"/"+weekJSON.FourthWeek.to.split("/")[2]
			
		   
		if(weekJSON.FourthWeek.from.split("/")[0].length == 1){
			weekJSON.FourthWeek.from = "0"+weekJSON.FourthWeek.from
			
		}
		if(weekJSON.FourthWeek.to.split("/")[0].length == 1){
			weekJSON.FourthWeek.to = "0"+weekJSON.FourthWeek.to
			
		}
		   
	
		}
		
		function lastFirstmonthdonut(){
			var tempmonth = JSON.stringify(dateJSON.FirstMonth.from).split('"')[1].split("/")[0]
			document.getElementById('sales-chart1').parentElement.parentElement.getElementsByTagName('h3')[0].innerHTML =monthNames[parseInt(tempmonth)-1]//(dateJSON.FirstMonth.from +' to '+ dateJSON.FirstMonth.to)
			
			var donut = new Morris.Donut({
				element : 'sales-chart1',
				resize : true,
				colors : [ '#235E93', '#64B246', '#91191C','#F58C1F','#235E93', '#64B246', '#91191C','#F58C1F'],
				data :monthconsumtion.FirstMonth,
				hideHover : 'auto'
			});
			
		}

		function lastSecondmonthdonut(){
			var tempmonth = JSON.stringify(dateJSON.SecondMonth.from).split('"')[1].split("/")[0]
			document.getElementById('sales-chart2').parentElement.parentElement.getElementsByTagName('h3')[0].innerHTML = monthNames[parseInt(tempmonth)-1]
			
			var donut = new Morris.Donut({
				element : 'sales-chart2',
				resize : true,
				colors : [ '#235E93', '#64B246', '#91191C','#F58C1F','#235E93', '#64B246', '#91191C','#F58C1F'],
				data :monthconsumtion.SecondMonth,
				hideHover : 'auto'
			});
			
		}

		function lastThirdmonthdonut(){
			var tempmonth = JSON.stringify(dateJSON.ThiredMonth.from).split('"')[1].split("/")[0]
			
			document.getElementById('sales-chart3').parentElement.parentElement.getElementsByTagName('h3')[0].innerHTML = monthNames[parseInt(tempmonth)-1]
			
			
			var donut = new Morris.Donut({
				element : 'sales-chart3',
				resize : true,
				colors : [ '#F58C1F', '#64B246', '#91191C', '#F58C1F',
					'#64B246', '#91191C', '#F58C1F', '#64B246', '#91191C' ],
				data : monthconsumtion.ThiredMonth,
				hideHover : 'auto'
			});
			
		}

function createdonotjson(){
	
	for(var i in dateJSON){
		
		var consuptionref = [];
		
		for(var device in totalConsumption){
			
			var responsejson = JSON.parse(getConsumtion(device.split("->")[1],dateJSON[i].from,dateJSON[i].to,null))
		
			var result = responsejson.result;
			
			var tempconsumtion = 0;
			
			for(var obj in result){
				
				tempconsumtion = tempconsumtion + result[obj].units;
				
			}
			var devicedataref = {label:device.split("->")[0],value:tempconsumtion}
			consuptionref.push(devicedataref);
			
		}
		
		var consumptiontest = 0;
		for(var index in consuptionref){
			
			consumptiontest = consumptiontest + consuptionref[index].value
		}
		
		if(consumptiontest > 0){
			monthconsumtion[i] = consuptionref;
		}else {
			
			monthconsumtion[i] = [{label:'Water Consumption ',value:0}]
			
		}
		
	}
	
	
}


function getConsumtion(devid,fromDate,toDate,filter){
	
	var jsonVal;
	
	   $.ajax({
           url: 'getGraphOnDemand',
           async: false,
           type: 'POST',
           data: jQuery.param({ devId : devid,fromDate : fromDate, toDate:toDate,type: 'days'}) ,
           success: function (data) {
        	   jsonVal=data; 
        	  
        	   },
		 		error: function(e){
	     			        alert('Error: ' + e);
	     		 }

              
           }); 


	   return jsonVal;

}

var collop = 0;
function getLineColors(){
			
	if(collop == 4){
		collop = 0;
	}
	var staticlineColors = [ '#F58C1F', '#64B246', '#91191C','#235E93']
			
			return staticlineColors[collop++]
		
		}
		
		
		var colorloop = 0;
		
		function getBoxColor(){
			if(colorloop == 4){
				colorloop = 0;
			}
			var totalBoxClasses = ['unizen-orange','unizen-green','unizen-red','unizen-blue']
			return totalBoxClasses[colorloop++]
			
		}
		
		function getcurrebtDaysReport() {
			
			
			$.ajax({
				url : 'getGraphOnBodyLoad',
				async: false,
				type : 'GET',
				success : function(data) {
					dayreportjson = JSON.parse(data);

					plotdayChart();
				},
				error : function(e) {
					alert('Error: ' + e);
				}

			});

			$.ajax({
				url : 'totalWaterConsumedByUser',
				async: false,
				type : 'GET',
				success : function(data) {
					totalConsumption = data;
					
				
					plotTotalConsuption()

				},
				error : function(e) {
					alert(' hError: ' + e);
				}

			});

		}

		function plotTotalConsuption() {
			
			colorloop = 0;
			var refdiv = document.getElementById('refTotal')	

			var devicesCount = Object.keys(totalConsumption).length;
			
			document.getElementById('totalConsumption').innerHTML = "";
			
			for ( var i in totalConsumption) {

				var innerval = refdiv.cloneNode(true);

				innerval.setAttribute("style", "");
				innerval.setAttribute("id", "");
				
				var boxwidthclass = 'col-lg-4 col-xs-4';
				if(devicesCount == 1){
					
					boxwidthclass = 'col-lg-12 col-xs-12'
				}else if(devicesCount == 2){
					
					boxwidthclass = 'col-lg-6 col-xs-6'
				}else{
					
				}
				
				innerval.setAttribute("class",boxwidthclass)
 
				innerval.getElementsByTagName("p")[0].innerHTML ="<span style='color:white'>"+ i.split('->')[0]+"</span>";
				
				innerval.getElementsByTagName("div")[0].setAttribute("class","small-box");
				innerval.getElementsByTagName("div")[0].classList.add(getBoxColor())
				

				innerval.getElementsByTagName("h3")[0].innerHTML = "<span style='color:white'>"+totalConsumption[i] + "<small style='color:white'>Ltrs</small>"+"</span>";
				
				document.getElementById('totalConsumption').appendChild(
						innerval);

			}

		}

		function plotdayChart() {
			collop =0;
			var ykeys = []
			var labels = []
			var lineColors = []

			var eachValObj = {}

			var dataVal = []

			try {
				var config = dayreportjson.result.units;
				var timeval = dayreportjson.result.xAxis.categories;

				for ( var k in config) {

					ykeys.push(config[k].name);
					labels.push(config[k].name.split('->')[0]);
					lineColors.push(getLineColors())

				}

				for ( var i in timeval) {

					var newobj = {}
					newobj['y'] = timeval[i]

					for ( var j in config) {

						var waterval = config[j].data[i]
						var name = config[j].name

						newobj['' + name + ''] = waterval;

					}

					dataVal.push(newobj)

				}

			} catch (e) {

				var line = new Morris.Line({
					element : 'daily-chart',
					resize : true,
					data : dataVal,
					xkey : 'y',
					ykeys : ykeys,
					labels : labels,
					lineColors : lineColors,
					hideHover : 'auto'
				});

			}

			var line = new Morris.Line({
				element : 'daily-chart',
				resize : true,
				data : dataVal,
				xkey : 'y',
				ykeys : ykeys,
				labels : labels,
				lineColors : lineColors,
				hideHover : 'auto'
			});

		}
		
		
		
		
		 function monthformat(value){
			 
				if(value.split("/")[0].length == 1){
					   
					   return "0"+value;
					   
				   } else{
					   return value;
					   
				   }
				 }
		
		
			function getMonthDates()
		{

		   var date = new Date();
		   var year = date.getFullYear(), month = date.getMonth();

		   dateJSON.FirstMonth.from = monthformat(new Date(year, month, 1).toLocaleDateString());
		   
		   
		
		   
		   dateJSON.FirstMonth.to = monthformat(new Date(year, month + 1, 1).toLocaleDateString());
		   
		   dateJSON.SecondMonth.from = monthformat(new Date(year, month-1, 1).toLocaleDateString());
		   dateJSON.SecondMonth.to = monthformat(new Date(year, (month-1)+1, 1).toLocaleDateString());
		 
		   
		   dateJSON.ThiredMonth.from =monthformat( new Date(year, month-2, 1).toLocaleDateString());
		   dateJSON.ThiredMonth.to= monthformat(new Date(year, (month-2) + 1, 1).toLocaleDateString());
		
		 
		   
		
		   
		}
		
		
</script>

</body>
</html>
