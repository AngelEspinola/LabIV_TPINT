<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

	String texto = "Texto generico de exito";
	if(request.getAttribute("Exito") != null)
	{
		texto = request.getAttribute("Exito").toString();
	}
%>
<div style="text-align:center">
	<h1 class="card-title"><%=texto%></h1>
</div>
<div style="text-align:center;margin: 150px">
	<img alt="" src="https://image.flaticon.com/icons/png/512/66/66374.png">
</div>
</body>
</html>