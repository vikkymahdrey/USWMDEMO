<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page errorPage="error.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<input type="text" id="inputTest" name="inputTest">

<%=request.getParameter("inputTest")%>

<tr> 
      <td height="30" width="25%">&nbsp;&nbsp;&nbsp;Password:</td> 
        <td valign="top" width="70%"> 
          <input type="password" name="tfPassword" size="8" maxlength="8" value=""> 
        </td> 
      <td valign="top" width="5%"> 
        <input type="checkbox" name="chkPassword" value=""> 
      </td> 
    </tr> 
    </table></CENTER><center><HR> 
<%!String strD;%> 
<%strD=request.getParameter("tfPassword");
out.print(strD);%> 
(Here is what I changed according to your suggestion)	
<input type="text" name="tfDisclose" value="<%=strD%>"> 

&nbsp;&nbsp<input type="submit" value="submit" name="submit" > 
</body>
</html>