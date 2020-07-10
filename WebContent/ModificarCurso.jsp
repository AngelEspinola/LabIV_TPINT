<%@page import="Entidades.Materia"%>
<%@page import="Entidades.Docente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--Referencia en linea-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <!--Referencia local-->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <title>Modificar curso</title>

    <style>
        .fondo{ background-color:#E8EAF6; }
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
  	
  	String cuatrimestre = "";
  	int docente = 0;
  	int materia = 0;
  	int año = 0;
  	int ID = 0;
  	
  	if (request.getAttribute("Result_ID") != null)
	{
    	ID = (int)request.getAttribute("Result_ID");
	}
  	if (request.getAttribute("Result_Cuatrimestre") != null)
	{
    	cuatrimestre = request.getAttribute("Result_Cuatrimestre").toString();
	}
  	if (request.getAttribute("Result_Año") != null)
	{
    	año = (int)request.getAttribute("Result_Año");
	}
  	if (request.getAttribute("Result_Docente") != null)
	{
    	docente = (int)request.getAttribute("Result_Docente");
	}
  	if (request.getAttribute("Result_Materia") != null)
	{
    	materia = (int)request.getAttribute("Result_Materia");
	}
%>
<jsp:include page="Menu.jsp"></jsp:include>
<div class="row">
        <br>
    </div>
        <form class="" method="get" action="ServletCurso" novalidate>
        
    <div class="row">
        <div class=" col-1"></div>
        <div class="col-10 card formulario">
            <div class="card-header text-center">
                <h5 class="card-title">Modificar curso</h5>
                <input type="hidden" class="form-control" id="txtMateria" name="modificarCurso" value="Matematicas" required>    
            </div>
            <div class=" card-body">
                    <div class="form-row">
                        <div class="col-md-3 mb-3">
                            <div class="input-group-prepend">
                                 <label for="ddlMateria">Materia</label>
                            </div>
                            <select class="custom-select" id="txtMateria" name="txtMateria" required>
                                <option selected disabled value="">Selecciona...</option>
                                <%
                                if (materias != null)
                            	{
                                	for(Materia m : materias)
                                	{
                                %>
                                		<option value="<%=m.getId()%>"> <%=m.getNombre()%> </option>
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
                        <div class="col-md-3 mb-3">
                            <div class="input-group-prepend">
                                    <label for="ddlCuatrimestre">Cuatrimestre</label>
                            </div>
                            <select class="custom-select" name="txtCuatrimestre" id="txtCuatrimestre" required>
                                    <option selected disabled value="">Selecciona...</option>
                                    <option value="1">Primer Cuatrimestre</option>
                                    <option value="2">Segundo Cuatrimestre</option>
                            </select>  
                            <div class="valid-feedback">Completo!</div>
                            <div class="invalid-feedback"> Por favor completar este dato.</div>       
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="txtAnio">Año</label>
                            <input type="number" class="form-control" id="txtAnio" name="txtAnio" value="" required>                         
                            <div class="valid-feedback">Completo!</div>
                            <div class="invalid-feedback"> Por favor completar este dato.</div>
                        </div>
                        <div class="col-md-3 mb-3 align-items-end input-group">
                            <button class="btn btn-info align-items-end" name="btnBurscarCurso" type="submit">Buscar</button>
                        </div>
                    </div>
            </div>
            <br>
            <div class=" card-footer">
            		<div class="form-row">
                        <div class="col-md-6 mb-3">
                            <label for="txtID">ID</label>
                            <input class="form-control disabled" type="text" value="<%=ID%>" name="txtID" id="txtID">
                        </div>
                    </div>
					<div class="form-row">
                        <div class="col-md-6 mb-3">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="ddlMateria">Materia</label>
                                </div>
                                <select class="custom-select" id="ddlMateria" name="ddlMateria" required>
                                    <%
                                    if (materias != null)
                                	{
                                    	for(Materia m : materias)
                                    	{
                                    %>
                                    		<option value="<%=m.getId()%>"> <%=m.getNombre()%> </option>
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
                                <select class="custom-select" id="ddlProfesor" value="<%=docente%>" name="ddlProfesor" required>
                                    <%
                                    if (docentes != null)
                                	{
                                    	for(Docente d : docentes)
                                    	{
                                    %>
                                    		<option value="<%=d.getID()%>"> <%=d.getNombre() + " " + d.getApellido()%> </option>
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
                        <div class="col-md-6 mb-3">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="txtAnioNuevo">Año</label>
                                </div>
                                <input type="number" class="form-control" name="txtAnioNuevo" value="<%=año%>" id="txtAnioNuevo" required>
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
                                    <option value="1">Primer Cuatrimestre</option>
                                    <option value="2">Segundo Cuatrimestre</option>
                                </select>  
                                <div class="valid-feedback">Completo!</div>
                                <div class="invalid-feedback"> Por favor completar este dato.</div>
                            </div>                                                  
                        </div>
                    <!--<div class="form-group">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                            <label class="form-check-label" for="invalidCheck">
                                Agree to terms and conditions
                            </label>
                            <div class="invalid-feedback">
                                You must agree before submitting.
                            </div>
                        </div>
                    </div>-->
                    <button class="btn btn-success" name="btnGuardarCurso" type="submit">Guardar</button>
                    <br>
                    <br>
                
            </div>
        </div>
        <div class="col-1"></div>
    </div>
    </form>
    </div>


    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    
    <script src="js/bootstrap.min.js"></script> 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>      
    <script type="text/javascript">
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
    $('#ddlCuatrimestre').val(<%=cuatrimestre%>);
    $('#ddlProfesor').val(<%=docente%>);
    $('#ddlMateria').val(<%=materia%>);

    </script>
</body>
<footer>
    <div class="row divMarco"></div>
</footer>
</html>