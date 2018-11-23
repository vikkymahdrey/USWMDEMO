<%--
    Author     : Vikky
--%>


<%@page import="com.team.app.domain.*"%>
<%@page import="com.team.app.dto.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<%@ page errorPage="error.jsp" %> 
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>APL config</title>
    <link rel="stylesheet" href="css/map.css">
    

  <%
	List<Area> areas=(List<Area>)request.getAttribute("areas");
	%>  
  <script type="text/javascript">
	 
     var geocoder;
	 var map;
	 var markers=[];
	 var marker;
	 var cityLat;
	 var cityLon;
	  
	 
	function loadScript() {
			cityLat = document.getElementById("cityLat").value;
			cityLon = document.getElementById("cityLon").value;
			try {
				var script = document.createElement("script");
				
				script.type = "text/javascript";
				script.src = "http://maps.googleapis.com/maps/api/js?key=AIzaSyC9Qem9w4qe_9EqmMXJql00Qvkv1yB9wcU&libraries=places&callback=initialize";
				//script.src = "http://maps.googleapis.com/maps/api/js?sensor=true&callback=initialize";
				document.body.appendChild(script);	
			} catch (e) {
				alert("ERRO" + e);
			}
		}
 
	function initialize()
	{
		var placeId=document.getElementById("place").value;
			
		try{
	    	geocoder = new google.maps.Geocoder();
	  		var myLatlng = new google.maps.LatLng(cityLat, cityLon);
	  		var myOptions = {
			    zoom: 12,
			    center: myLatlng,
			    mapTypeId: google.maps.MapTypeId.ROADMAP
			  };
	  		map = new google.maps.Map(document.getElementById("map"), myOptions);
	  		//searchPlace();
		  showExistingLandmarks();
		  if(placeId!='null'){
			  google.maps.event.addListener(map, 'click', function(event) {
			     placeMarker(event.latLng);
			    displayWindowForSetAPL(marker,event);		    
			    
			  });
		  }	  
		  
		}catch(e){
			aler(e);	
		}
	}

function placeMarker(location) {
	marker = new google.maps.Marker({
		position : location,
		map : map
	});
}

function searchPlace(){
	    
    // Create the search box and link it to the UI element.
      var input=document.getElementById('pac-input');     
      map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
      var searchBox = new google.maps.places.SearchBox(input);
      
       // Bias the SearchBox results towards current map's viewport.
      google.maps.event.addListener(map, 'bounds_changed', function() {
           var bounds = map.getBounds();
           searchBox.setBounds(bounds);
        });
        
      // [START region_getplaces]
      // Listen for the event fired when the user selects an item from the
      // pick list. Retrieve the matching places for that item.
      google.maps.event.addListener(searchBox, 'places_changed', function() {
        var places = searchBox.getPlaces();
        
        if (places.length == 0) {
          return;
        }
        for (var i = 0, marker; marker = markers[i]; i++) {
          marker.setMap(null);
        }

        // For each place, get the icon, place name, and location.
        markers = [];
        var bounds = new google.maps.LatLngBounds();
        
        for (var i = 0, place; place = places[i]; i++) {
            // Create a marker for each place.
                     var marker = new google.maps.Marker({
                     map: map,
                     title:  place.name + "   ,  " + place.formatted_address + "  " +  place.geometry.location,
                     position: place.geometry.location
                     });

                     markers.push(marker);

          bounds.extend(place.geometry.location);
        }

        map.fitBounds(bounds);
      });
      // [END region_getplaces]
   
  }


function showExistingLandmarks(){
	
	xmlHttp=GetXmlHttpObject();
	var orgId=document.getElementById("orgid").value;
	
	var area=document.getElementById("area").value;
	var place=document.getElementById("place").value;	
		
	var url="";
	if(place!="null")
		{
		url="getLandmarks?place="+place;
		}
	else if(area!="null")
		{
		
		url="getLandmarks?area="+area;
		}
	else if (orgId!="null")		
		{
		url="getLandmarks?orgId="+orgId;
		}
	else
		{
		url="getLandmarks";
		}
		xmlHttp.onreadystatechange=displayExistingLandmarks;
		xmlHttp.open("POST",url,true);
		xmlHttp.send()
	}
	
	
function displayExistingLandmarks()
{
		if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
			{
			var fullnodes = xmlHttp.responseText;
			var nodes=fullnodes.split("$");
			var noofnodes=nodes.length;
			var ltln;
			for(var i=1;i<noofnodes;i++)
			{
			ltln=nodes[i].split(":");
			var latlng = new google.maps.LatLng(ltln[4],ltln[5]);
						placeMarker(latlng);
						var message=ltln[0]+"<br>"+ltln[1]+"<br>"+ltln[3]+"<br>"+'';
						displayExistingLandmarkMarkers(marker,latlng,message);
			}
			}
}

function validateMapForm(){
	alert("hiii");
	
	/* var area1=document.getElementsByClassName("a").value;
	alert("area"+area1);
	return true; */
	/* var place=document.getElementByName("place");
	alert("place"+place);
	var landmark=document.getElementByName("landmarkId");
	alert("landmark"+landmark);
	if(area==null){
		return false;
		alert("Please select area!");
	}else if(place=="undefined"){
		return false;
		alert("Please select place!");
	}else if(landmark=="undefined"){
		return false;
		alert("Please select landmark!");
	} */
}

function displayExistingLandmarkMarkers(marker,latlng,message) {	  
	  var infowindow = new google.maps.InfoWindow(
	      { content: message,
	        size: new google.maps.Size(50,50)
	      });
	     google.maps.event.addListener(marker, 'click', function() {
	  infowindow.open(map,marker);
	  });

	}
		


function displayWindowForSetAPL(marker,event) {
	
	   try{
		   var orgId=document.getElementById("orgid").value;
			var area=document.getElementById("area").value;
			var place=document.getElementById("place").value;	
			
			var action="";
			
			 if (orgId!="null")		
				{
				action="updateMapLandmark?orgId="+orgId;
				}
			 else if(area!="null")
				{
				
				action="updateMapLandmark?area="+area;
				}
			 else if(place!="null")
				{
				action="updateMapLandmark?placeId="+place;
				}
			
			
			
	var latln=String(event.latLng);
	 var latlng=latln.split(" ");
	latlng=latlng[0]+"$"+latlng[1];	
	  var message = 'Update APL<form name="form1" action="'+action+'" method="post" ><table>';
	  message=message+'<tr><td>Area</td><td>';
	  message=message+'<select name="area" id="area" onchange="showPlace(this.value)">';
	  message=message+'<option value="">Select</option><%for(Area area:areas){%>';
	  message=message+'<option value=<%=area.getId()%>><%=area.getAreaname()%>';
	  message=message+'</option><%}%></select></td></tr>';
	  message=message+'<tr><td>Place</td><td id="placeTd"><Select name="place"><option value="">Select</option></select></td></tr><tr><td>Land Mark</td><td id="landmark"><select name="landmarkId" ></select></td></tr><tr><td><input type="hidden" value=';
	  message=message+latlng;
	  message=message+' name="latlng"></td><td><input type="submit" value="Update"></td></tr></form>';
			var infowindow = new google.maps.InfoWindow({
				content : message,
				size : new google.maps.Size(50, 50)
			});
			google.maps.event.addListener(marker, 'click', function() {
				infowindow.open(map, marker);
			});
			google.maps.event.addListener(marker, 'dblclick', function() {
				marker.setMap(null);
			});
		} catch (e) {
			alert("ERORQ" + e);
		}
	}
function showPlace(area)
{
    if(document.getElementById("area").value!="")
    {
        xmlHttp=GetXmlHttpObject()
        if (xmlHttp==null)
		{
            alert ("Browser does not support HTTP Request")
            return
        }
		var url="getPlace";
		url=url+"?area="+area;
		xmlHttp.onreadystatechange=showPlace_select;

		xmlHttp.open("GET",url,true);
		//alert('sent');
		xmlHttp.send(null);
    }
    else
    {
    	 alert("Please Select area-- ");
    }
}
function showLandmark(place)
{
    if(place!="0")
    {
        xmlHttp=GetXmlHttpObject()
        if (xmlHttp==null)
		{
            alert ("Browser does not support HTTP Request")
            return
        }
		var url="getLandmarks";
		url=url+"?placeforLandmark="+place;
		xmlHttp.onreadystatechange=showLandmark_select;

		xmlHttp.open("POST",url,true);
		xmlHttp.send(null);
    }
    else
    {
    	 alert("Please Select Place-- ");
    }
}
function showLandmark_select()
{
    if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
    {
        document.getElementById("landmark").innerHTML=xmlHttp.responseText
    }
}

function showPlace_select()
{
	//alert("status :"+ xmlHttp.readyState);
    if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
    { 
    
        document.getElementById("placeTd").innerHTML=xmlHttp.responseText;
   	  
    }
}
function GetXmlHttpObject()
{
	var xmlHttp=null;
if (window.XMLHttpRequest) {
		xmlHttp=new XMLHttpRequest();
	}
	//catch (e)
else if (window.ActiveXObject) {
	 		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
 		}

return xmlHttp;
}
	
</script>     
</head>
  
  			<%           
	            String orgId= request.getParameter("orgId");
	            String areaId= request.getParameter("area");
	            String placeId= request.getParameter("place");
	            String[] city=new String[2];
	            city[0]  = ""+"12.9173526";
	            city[1] = ""+"77.6257187";
	            
	            System.out.println("marklandmark .jsp orgId:"+orgId);
	            System.out.println("marklandmark .jsp areaId:"+areaId);
	            System.out.println("marklandmark .jsp placeId:"+placeId);
          
  			
  			%>
<input type="hidden" value="<%=city[0]%>" id="cityLat">
<input type="hidden" value="<%=city[1]%>" id="cityLon">
<input type="hidden" value="<%=areaId%>" id="area">
<input type="hidden" value="<%=placeId%>" id="place">
<input type="hidden" value="<%=orgId%>" id="orgid">

  <body onload="loadScript()"> 
  		
  		<div id="map" style="width: 100%; height: 720px; float: right;"></div> 
  		<div></div>
  		<div></div>
  		
  		<input id="pac-input" class="controls" type="text"  placeholder="Search Box"> 
  </body>
</html>