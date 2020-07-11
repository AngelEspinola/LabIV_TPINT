<%@page import="Entidades.Materia"%>
<%@page import="Entidades.Docente"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--Referencia en linea-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <!--Referencia local-->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <title>Agregar curso</title>

        <style>
            .fondo{ background-color: #E8EAF6; }
            .formulario{ background-color: #ffffff; }
        </style>
</head>
<body class="fondo">
<%
	ArrayList<Materia> materias = null;
		if (session.getAttribute("Materias") != null)
	{
		materias = (ArrayList<Materia>)session.getAttribute("Materias");
	}
		ArrayList<Docente> docentes = null;
		if (session.getAttribute("Docentes") != null)
	{
		docentes = (ArrayList<Docente>)session.getAttribute("Docentes");
	}
%>
<jsp:include page="Menu.jsp"></jsp:include>   

    <div class="row">
        <br>
    </div>
    <div class="row">
        <div class=" col-1"></div>
        <div class="col-10 card formulario">
            <div class="card-header text-center">
                <h5 class="card-title">Agregar curso</h5>
            </div>
            <div class=" card-body">
                <form class="needs-validation" action="ServletCurso" method="get" novalidate>
                    <div class="form-row">
                        <div class="col-md-6 mb-3">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="ddlMateria">Materia</label>
                                </div>
                                <select class="custom-select" id="ddlMateria" name="ddlMateria" required>
                                    <option selected disabled value="">Selecciona...</option>
                                    <%
                                    if (materias != null)
                                	{
                                    	for(Materia materia : materias)
                                    	{
                                    %>
                                    		<option value="<%=materia.getId()%>"> <%=materia.getNombre()%> </option>
                                    <%
                                    	}
                                	}
                                    else
                                    {
                                    %>
                                		<option value="1"> materias = null </option>
                                	<%
                                    }
                                    %>
                                </select>  
                                <div class="valid-feedback">Completo!</div>
                                <div class="invalid-feedback"> Por favor completar este dato.</div>
                            </div>                        
                        </div>
                        <div class="col-md-6 mb-3">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="ddlProfesor">Docente</label>
                                </div>
                                <select class="custom-select" id="ddlProfesor" name="ddlProfesor" required>
                                    <option selected disabled value="">Selecciona...</option>
                                    <%
                                    if (docentes != null)
                                	{
                                    	for(Docente d : docentes)
                                    	{
                                    %>
                                    		<option value="<%=d.getID()%>"> <%=d.getID() + " " + d.getApellido()%> </option>
                                    <%
                                    	}
                                	}
                                    else
                                    {
                                    %>
	                                    <option value="1">Tamara Herrera</option>
   	    	                            <option value="2">Claudio Fernandez</option>
    	                                <option value="3">Daniel Kloster</option>
                                	<%
                                    }
                                    %>
                                </select>  
                                <div class="valid-feedback">Completo!</div>
                                <div class="invalid-feedback"> Por favor completar este dato.</div>
                            </div>                                                  
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-6 mb-3">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="txtAnio">Año</label>
                                </div>
                                <input type="number" class="form-control" id="txtAnio" name="txtAnio" required>
                                <div class="valid-feedback">Completo!</div>
                                <div class="invalid-feedback"> Por favor completar este dato.</div>
                            </div>                        
                        </div>
                        <div class="col-md-6 mb-3">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="ddlCuatrimestre">Cuatrimestre</label>
                               </div>
                                <select class="custom-select" name="ddlCuatrimestre" id="ddlCuatrimestre" required>
                                    <option selected disabled value="">Selecciona...</option>
                                    <option value="1">Primer Cuatrimestre</option>
                                    <option value="2">Segundo Cuatrimestre</option>
                                </select>  
                                <div class="valid-feedback">Completo!</div>
                                <div class="invalid-feedback"> Por favor completar este dato.</div>
                            </div>                                                  
                        </div>
                    </div>
                    <button class="btn btn-success" name="btnAltaCurso" type="submit">Agregar</button>
                </form>
            </div>
            <div class=" card-footer">
            </div>
        </div>
        <div class="col-1"></div>
    </div>


    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    
    <script src="js/bootstrap.min.js"></script> 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>      
    <script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
    'use strict';
    window.addEventListener('load', function() {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function(form) {
        form.addEventListener('submit', function(event) {
            if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
        });
    }, false);
    })();
    </script>
</body>
<footer>
    <div class="row divMarco"></div>
</footer>
</html>