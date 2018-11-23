<%--
    Author     : Vikky
--%>


<%@page import="com.team.app.domain.*"%>
<%@page import="com.team.app.dto.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<%@ page errorPage="error.jsp" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<script type="text/javascript">
	function selectRow(id) {
		var name;
		var places;
		var place;
		try {
			name = document.getElementById("landMarkValue-" + id).innerHTML;
			places = name.split("-&gt;");
			place = places[0] + "\n" + places[1] + "\n" + places[2];
			opener.document.getElementById("landMarkID").value = id;
			opener.document.getElementById("area").value = places[0];
			opener.document.getElementById("place").value = places[1];
			opener.document.getElementById("landmark").value = places[2];

		} catch (e) {
			alert(e);
		}
		self.close();
	}

	//  get Land Mark via Ajax
	function getLandMarks() {

		var place = document.getElementById("place").value;
		var orgId = document.getElementById("orgId").value;
		var url="GetLandMark?landMarkText=" + place + "&orgId=" + orgId;
			
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				setLandMarks(xmlhttp.responseText);
			}
		}

		xmlhttp.open("GET", url, true);

		xmlhttp.send();
		return false;
	}

	function setLandMarks(data) {

		try {
			var emp = data.toString().split("|");
			var innerHtml = "";
			if (emp.length > 1) {
				var innerHtml = "<table width=500 border=1 >"
						+ " <thead> <th> Area -> Place -> Landmark </th> <th> </th> </thead> <tbody> ";

				for (i = 0; i < emp.length - 1; i++) {
					//   alert(emp[i] + " length : " );
					var attr = emp[i].split(":");
					//inner= inner + "<option value='" + attr[0] + "'>" + attr[1]+ "</option> ";
					// document.getElementsByName("supervisor1").add(new Option(attr[0],attr[1],true,false));

					innerHtml = innerHtml
							+ " <tr> <td id=landMarkValue-" + attr[0] +" >"
							+ attr[1]
							+ "</td> <td> <a href='#' onClick='selectRow("
							+ attr[0] + ")' >Select </a> </tr>";

				}
				innerHtml + "</tbody> </table>";
			} else {
				innerHtml = "No Matching APL found";

			}
			document.getElementById("UserDetails").innerHTML = innerHtml;

		} catch (e) {
			alert(e);
		}

	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%
		String orgId = (String)request.getAttribute("orgId");
		
	%>
	<form name="SearchForm" action="" onsubmit="return getLandMarks();">


		<table border="0">

			<tr>
				<td colspan="3">
					<input type="hidden" id="orgId"	value="<%=orgId%>"></td>
			</tr>
			<tr>
				<td>APL</td>
				<td>
					<input type="text" name="place" id="place" />
				</td>
				<td align="left">
					<input type="submit" name="Search" class="formbutton" value="Search" />
				</td>
			</tr>



		</table>
	</form>
	<div id="UserDetails"></div>

</body>
</html>




