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
    <title>Eliminar curso</title>

    <style>
        .fondo{ background-color: #E8EAF6; }
        .formulario{ background-color: #ffffff; }
    </style>
</head>
<body class="fondo">

<%
	String error = (String)request.getAttribute("Error");

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

	String materia = "";
  	String docente = "";
  	int cuatrimestre = 0;
  	int anio = 0;
  	int ID = 0;
  	
  	if (request.getAttribute("Result_ID") != null)
	{
    	ID = (int)request.getAttribute("Result_ID");
	}
  	if (request.getAttribute("Result_Materia") != null)
	{
    	materia = (String)request.getAttribute("Result_Materia");
	}
  	if (request.getAttribute("Result_Docente") != null)
	{
    	docente = (String)request.getAttribute("Result_Docente");
	}
  	if (request.getAttribute("Result_Cuatrimestre") != null)
	{
    	cuatrimestre = (int)request.getAttribute("Result_Cuatrimestre");
	}
  	if (request.getAttribute("Result_Anio") != null)
	{
    	anio = (int)request.getAttribute("Result_Anio");
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
                <h5 class="card-title">Eliminar curso</h5>
            </div>
    		<form class="needs-validation" method="get" action="ServletCurso" novalidate>
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
                            <label for="txtAnio">A&ntilde;o</label>
                            <input type="number" class="form-control" id="txtAnio" name="txtAnio" value="" required>                         
                            <div class="valid-feedback">Completo!</div>
                            <div class="invalid-feedback"> Por favor completar este dato.</div>
                        </div>
                        <div class="col-md-3 mb-3 align-items-end input-group">
                            <button class="btn btn-info align-items-end" name="btnBurscarCurso_EliminarCurso" type="submit">Buscar</button>
                        </div>
                    </div>
                
            </div>
            </form>
            <form class="needs-validation" method="get" action="ServletCurso" novalidate>
            <div class=" card-footer">
					 <div class="form-row">
                        <div class="col-md-6 mb-3">
                            <label for="txtID">ID</label>
                            <input class="form-control disabled" type="text" value="<%=ID%>" name="txtID" id="txtID">
                        </div>
                    </div>
                    
                    
                    <div class="form-row">
                        <div class="col-md-6 mb-3">
                            <label for="txtMateria">Materia</label>
                            <label class="form-control disabled" id="txtMateria"><%=materia%></label>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="txtDocente">Docente</label>
                            <label class="form-control disabled" id="txtDocente"><%=docente%></label>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="col-md-6 mb-3">
                            <label for="txtAnio">A&ntilde;o</label>
                            <label class="form-control disabled" id="txtAnio"><%=anio%></label>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="txtCuatrimestre">Cuatrimestre</label>
                            <label class="form-control disabled" id="txtCuatrimestre"><%=cuatrimestre%></label>
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
<!-- Button trigger modal -->
<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#staticBackdrop">
    Eliminar
  </button>
  
  <!-- Modal -->
  <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="staticBackdropLabel">Eliminar</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
            Se eliminar a Nombre, Apellido. Desea continuar?
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
          <button type="submit" name ="btnEliminarCurso" class="btn btn-danger">Confirmar</button>
        </div>
      </div>
    </div>
  </div>
 <br>
                        <br>
                    </form>
                </div>
</form>
            </div>
            <div class="col-1"></div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        
        <script src="js/bootstrap.min.js"></script> 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>      
        <script>
        document.getElementById('txtID').readOnly = true;
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
        
        var error = "<%=error%>";
        if(error != "null")
       	{
      		alert(error);
       	}
        </script>
    </body>
    <footer>
        <div class="row divMarco"></div>
    </footer>
</html>