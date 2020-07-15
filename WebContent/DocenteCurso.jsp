<%@page import="Entidades.Docente"%>
<%@page import="Entidades.AlumnoNotas"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--Referencia en linea-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.6.2/css/buttons.bootstrap4.min.css">
        
        <!--Referencia local-->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <title>Calificar curso</title>

        <style>
            .fondo{ background-color: #E8EAF6; }
            .formulario{ background-color: #ffffff; }
        </style>

    </head>
<body class="fondo">
<jsp:include page="Menu.jsp"></jsp:include>
<%
  	ArrayList<AlumnoNotas> alumnoNotas = null;
  	if (request.getAttribute("ListAlumnoNotas") != null)
	{
  		alumnoNotas = (ArrayList<AlumnoNotas>)request.getAttribute("ListAlumnoNotas");
	}
  	Docente d = null;
  	if (session.getAttribute("User") != null)
	{
    	d = (Docente)session.getAttribute("User");
	}
%>
    <div class="row">
        <br>
    </div>
    <div class="row">
        <div class=" col-1"></div>
        <div class="col-10 card formulario">
            <div class="card-header">
                <h6 class="card-title"><%=d!=null?d.getApellido(): ""%></h6>
            </div>
            <div class="card-header text-center">
                <h5 class="card-title">Curso</h5>
            </div>
            <div class=" card-body">
            <form class="needs-validation" name="CalificarCurso" action="ServletCalificar" method="post" novalidate>
                <table id="example" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                        <tr>
                            <th>Legajo</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>1° Parcial</th>
                            <th>2° Parcial</th>
                            <th>1° Recuperatorio</th>
                            <th>2° Recuperatorio</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    
                    <tbody>
                    <%
                  	if (alumnoNotas != null)
              		{
                  		for(AlumnoNotas a : alumnoNotas)
                  		{
               		%>
	                    <tr>
	                       <td><%=a.getLegajo()	%></td>
	                       <td><%=a.getNombre()	%></td>
	                       <td><%=a.getApellido()%></td>
	                       <td><input type="number" min="0" max="10" step="1" class="form-control" id="<%="txt1Nota"+a.getIdAlumno()%>" name="<%="txt1Nota"+a.getIdAlumno()%>" value="<%=a.getNota1()%>"></td>
	                       <td><input type="number" min="0" max="10" step="1" class="form-control" id="<%="txt2Nota"+a.getIdAlumno()%>" name="<%="txt2Nota"+a.getIdAlumno()%>" value="<%=a.getNota2()%>"></td>
	                       <td><input type="number" min="0" max="10" step="1" class="form-control" id="<%="txt1Recu"+a.getIdAlumno()%>" name="<%="txt1Recu"+a.getIdAlumno()%>" value="<%=a.getRecuperatorio1()%>"></td>
	                       <td><input type="number" min="0" max="10" step="1" class="form-control" id="<%="txt2Recu"+a.getIdAlumno()%>" name="<%="txt2Recu"+a.getIdAlumno()%>" value="<%=a.getRecuperatorio2()%>"></td>
	                       <td>
	                       <div class="input-group">
	                           <select class="custom-select" id="<%="dllEstado"+a.getIdAlumno()%>" name="<%="dllEstado"+a.getIdAlumno()%>">
	                           <option selected disabled value="<%=a!=null?a.getEstadoInt():"" %>" ><%=a.getEstado()!=null?a.getEstado():"Selecciona..." %></option>
	                               <option value="1">Libre</option>
	                               <option value="2">Regular</option>
	                           </select>
	                       </div>
	                       </td>
	                   	</tr>
               		<%
              			}
              		}
                    %>
                    </tbody>
                </table>
                <button class="btn btn-success" type="submit" name="btnCalificarCurso" value="aceptar">Guardar todo</button>
                </form>
            </div>
            <div class=" card-footer"></div> 
        </div>
        <div class="col-1"></div>  
    </div>
    <br>
    
    <div style="width:100%; height:100%">
    </div>


    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    
    <!--Tabla-->
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.6.2/js/dataTables.buttons.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.bootstrap4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.html5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.print.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.colVis.min.js"></script>

    <script>
        //Se encarga del comportamiento de la grilla
        $(document).ready(function() {
            var table = $('#example').DataTable( {
                lengthChange: false,
                buttons: [ 'copy', 'excel', /*'pdf',*/ 'colvis' ]
            } );
        
            table.buttons().container()
                .appendTo( '#example_wrapper .col-md-6:eq(0)' );
        } );
    </script>     
    
    <!--Validaciones en formulario-->
    <script>
        //Se encarga de validar e informar por pantalla que los campos no esten vacios
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