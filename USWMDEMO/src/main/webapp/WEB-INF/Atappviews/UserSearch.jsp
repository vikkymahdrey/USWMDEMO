<%--
    Author     : Vikky
--%>

<%@ page errorPage="error.jsp" %> 
<%@page import="com.team.app.domain.*"%>
<%@page import="com.team.app.dto.*"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<script type="text/javascript">
	function selectRow(id) {
		var name;
		var users;
		var user;
		try {
			name = document.getElementById("uId-" + id).innerHTML;
			users = name.split("-&gt;");
			user = users[0] + "\n" + users[1] + "\n" + users[2];
			opener.document.getElementById("uId").value = id;
			opener.document.getElementById("uname").value = users[0];
			opener.document.getElementById("email").value = users[1];
			opener.document.getElementById("contact").value = users[2];

		} catch (e) {
			alert(e);
		}
		self.close();
	}

	//  get User Info via Ajax
	function getUserInfo() {

		var email = document.getElementById("email").value;
		var orgId = document.getElementById("orgId").value;
		var url="getUserInfoSearch?email=" + email + "&orgId=" + orgId;
			
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				setUserMarks(xmlhttp.responseText);
			}
		}

		xmlhttp.open("GET", url, true);

		xmlhttp.send();
		return false;
	}

	function setUserMarks(data) {

		try {
			var emp = data.toString().split("|");
			var innerHtml = "";
			if (emp.length > 1) {
				var innerHtml = "<table width=500 border=1 >"
						+ " <thead> <th> Username -> Email -> Contact </th> <th> </th> </thead> <tbody> ";

				for (i = 0; i < emp.length - 1; i++) {
					//   alert(emp[i] + " length : " );
					var attr = emp[i].split(":");
					//inner= inner + "<option value='" + attr[0] + "'>" + attr[1]+ "</option> ";
					// document.getElementsByName("supervisor1").add(new Option(attr[0],attr[1],true,false));

					innerHtml = innerHtml
							+ " <tr> <td id=uId-" + attr[0] +" >"
							+ attr[1]
							+ "</td> <td> <a href='#' onClick='selectRow("
							+ attr[0] + ")' >Select </a> </tr>";

				}
				innerHtml + "</tbody> </table>";
			} else {
				innerHtml = "No Matching User Info found";

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
	<form name="SearchForm" action="" onsubmit="return getUserInfo();">


		<table border="0">

			<tr>
				<td colspan="3">
					<input type="hidden" id="orgId"	value="<%=orgId%>"></td>
			</tr>
			<tr>
				<td>User Information</td>
				<td>
					<input type="text" name="email" id="email" placeholder="Search by email" />
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




