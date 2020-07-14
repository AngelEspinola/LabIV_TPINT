<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--Referencia en linea-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.6.2/css/buttons.bootstrap4.min.css">
    
    <!--Referencia local-->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <title>TP Final - Inicio</title>

    <style>
        .fondo{ background-color: #E8EAF6; }
        .formulario{ background-color: #ffffff; }
    </style>
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
	
</div>


    <div class="row">
        <br>
    </div>
    <div class="row">
        <div class=" col-1"></div>
        <div class="col-10 card formulario">
            <div class="card-header text-center">
            	<h1 class="card-title"><%=texto%></h1>
					<img alt="" src="https://images.vexels.com/media/users/3/127294/isolated/preview/62f9e10b937aeaaaebd9a0d7e4bbc3a3-empresario-celebrando-silueta-de---xito-by-vexels.png">
				
                <div >
                <form action="Main.jsp">
                    
                </form>
                </div>
            </div>
            <div class=" card-body">
                <div  style="text-align:center">
	                    <a class="navbar-brand" href="Main.jsp">
	                    	<button class="btn btn-success" type="button" id="button-addon2">Volver al menu principal</button>
	                    </a>
                    <div class="col-8"></div>
                </div>
                  <br>
            </div>
            <div class=" card-footer">
            </div>
        </div>
        <div class="col-1"></div>
    </div>


    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    
</body>
</html>