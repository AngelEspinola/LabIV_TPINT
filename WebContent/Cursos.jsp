<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Cursos</title>
	<link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="Bootstrap/css/CascadingStyleSheet.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<form action="ServletCurso" method="get">
		<!-- Declaración -->
		<%!String nombre = "Tamara";%>

		<!-- Expresion para mostrar variables -->
		<!-- <%=nombre%> -->
		<!-- Scriptlet Cualquier codigo java-->
		<%
		%>
	<div>
		<label class="form-title">Docente:</label>
		<label class="form-title"><%= nombre %></label>
	</div>
	
	<h2>Mis cursos:</h2>

	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th scope="col">#</th>
	      <!--  <th scope="col" colspan="2">Materia</th> -->
	      <th scope="col">Materia</th>
	      <th scope="col">Cuatrimestre</th>
	      <th scope="col">Año</th>
	      <th scope="col">Ir</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <th scope="row">1</th>
	      <td>Programación IV</td>
	      <td>Segundo</td>
	      <td>2020</td>
	      <td> <input type="submit" name="btnIr">	</td>
	    </tr>
	    <tr>
	      <th scope="row">2</th>
	      <td>Programación III</td>
	      <td>Primero</td>
	      <td>2020</td>
	    </tr>
	    <tr>
	      <th scope="row">3</th>
	      <td>Programación IV</td>
	      <td>Primero</td>
	      <td>2019</td>
	    </tr>
	  </tbody>
	</table>
	</form>
</body>
</html>