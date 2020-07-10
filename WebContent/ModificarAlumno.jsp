<%@page import="Entidades.Usuario"%>
<%@page import="Entidades.Docente"%>
<%@page import="Entidades.Alumno"%>
<%@page import="Entidades.Provincia"%>
<%@page import="Entidades.Localidad"%>
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
    <title>TP Final - Inicio</title>
    <style>
        .fondo{ background-color:#E8EAF6;}
        .formulario{ background-color: #ffffff; }
    </style>
</head>
<body class="fondo">
<jsp:include page="Menu.jsp"></jsp:include>
<%
  	Usuario u = null;
	Docente d = null;
	Alumno  a = null;
	Provincia provincia = null;
	Localidad localidad = null;
	ArrayList<Provincia> provincias = null;
	ArrayList<Localidad> localidades = null;
	if (application.getAttribute("Provincias") != null)
	{
		provincias = (ArrayList<Provincia>)application.getAttribute("Provincias");
	}
	if (application.getAttribute("Localidades") != null)
	{
		localidades = (ArrayList<Localidad>)application.getAttribute("Localidades");
	}
	if (application.getAttribute("Alumno") != null)
	{
    	a = (Alumno)application.getAttribute("Alumno");
    	localidad = a.getLocalidad();
    	provincia= a.getProvincia();
	}
%>

   <div class="row">
       <br>
   </div>
   <div class="row">
       <div class=" col-1"></div>
       <div class="col-10 card formulario">
           <div class="card-header text-center">
               <h5 class="card-title">Modificar alumno</h5>
           </div>
           <div class=" card-body">
               <form class="needs-validation" name="buscarAlumno1" action="ServletAlumnoModificar?Modificar=modificar" method="get" novalidate>
                   <div class="form-row">
                       <div class="col-md-3 mb-3">
                           <label for="txtLegajo">Legajo</label>
                           <input type="number" class="form-control" id="txtLegajo" name="txtLegajo" value="<%=a.getLegajo()%>" required>
                           <div class="valid-feedback">Completo!</div>
                           <div class="invalid-feedback"> Por favor completar este dato.</div>
                       </div>
                       <div class="col-md-3 mb-3">
                           <label for="txtDni">DNI</label>
                           <input type="number" class="form-control" id="txtDni" name="txtDni" value="<%=a.getDni()%>" required>
                           <div class="valid-feedback">Completo!</div>
                           <div class="invalid-feedback"> Por favor completar este dato.</div>
                       </div>
                       <div class="col-md-3 mb-3 align-items-end input-group">
                           <button class="btn btn-info align-items-end" type="submit" name="btnBuscarAlumnoModificar" value="buscar">Buscar</button>
                       </div>
                   </div>
               </form>
           </div>
           <br>
           <div class=" card-footer">
               <form class="needs-validation" name="modificarAlumno" action="ServletAlumnoModificar?Modificar=modificar" method="get" novalidate>
               	<div class="form-row">
                       <div class="col-md-3 mb-3">
                           <label for="txtLegajoNew">Legajo</label>
                           <input type="number" class="form-control" id="txtLegajoNew" name="txtLegajoNew" value="<%=a.getLegajo()%>" required>
                           <div class="valid-feedback">Completo!</div>
                           <div class="invalid-feedback"> Por favor completar este dato.</div>
                       </div>
                       <div class="col-md-3 mb-3">
                           <label for="txtDniNew">DNI</label>
                           <input type="number" class="form-control" id="txtDniNew" name="txtDniNew" value="<%=a.getDni()%>" required>
                           <div class="valid-feedback">Completo!</div>
                           <div class="invalid-feedback"> Por favor completar este dato.</div>
                       </div>
                   </div>
                   
                   <div class="form-row">
                       <div class="col-md-6 mb-3">
                           <label for="txtNombre">Nombre</label>
                           <input type="text" class="form-control" id="txtNombre" name="txtNombre" value="<%=a.getNombre()%>" required>
                           <div class="valid-feedback">Completo!</div>
                           <div class="invalid-feedback"> Por favor completar este dato.</div>
                       </div>
                       <div class="col-md-6 mb-3">
                           <label for="txtApellido">Apellido</label>
                           <input type="text" class="form-control" id="txtApellido" name="txtApellido" value="<%=a.getApellido()%>" required>
                           <div class="valid-feedback">Completo!</div>
                           <div class="invalid-feedback"> Por favor completar este dato.</div>
                       </div>
                   </div>
                   <div class="form-row">
                       <div class="col-md-6 mb-3">
                           <label for="txtTelefono">Telefono</label>
                           <input type="tel" class="form-control" id="txtTelefono" name="txtTelefono" value="<%=a.getTelefono()%>" required>
                           <div class="valid-feedback">Completo!</div>
                           <div class="invalid-feedback"> Por favor completar este dato.</div>
                       </div>
                       <div class="col-md-6 mb-3">
                           <label for="txtEmail">Email</label>
                           <input type="email" class="form-control" id="txtEmail" name="txtEmail" value="<%=a.getEmail()%>" required>
                           <div class="valid-feedback">Completo!</div>
                           <div class="invalid-feedback"> Por favor completar este dato.</div>
                       </div>
                   </div>

                   <div class="form-row">
                       <div class="col-md-6 mb-3">
                           <label for="txtFechaNac">Fecha de nacimiento</label>
                           <input type="date" class="form-control" id="txtFechaNac" name="txtFechaNac" value="<%=a.getNacimiento()%>" required>
                           <div class="valid-feedback">Completo!</div>
                           <div class="invalid-feedback"> Por favor completar este dato.</div>
                       </div>
                   </div>

                   <div class="form-row">
                       <div class="col-md-6 mb-3">
                           <label for="txtCalle">Calle</label>
                           <input type="text" class="form-control" id="txtCalle" name="txtCalle" value="<%=a.getCalle()%>" required>
                           <div class="valid-feedback">Completo!</div>
                           <div class="invalid-feedback"> Por favor completar este dato.</div>
                       </div>
                       <div class="col-md-3 mb-3">
                           <label for="txtNumero">Numero</label>
                           <input type="number" class="form-control" id="txtNumero" name="txtNumero" value="<%=a.getNumero()%>"required>
                           <div class="valid-feedback">Completo!</div>
                           <div class="invalid-feedback"> Por favor completar este dato.</div>
                       </div>
                   </div>

                   <!-- <div class="form-row"> -->
              <div class="col-md-6 mb-3">
                 <div class="input-group">
                   	<div class="input-group-prepend">
                   		<label class="input-group-text" for="ddlProvinciaModificar">Provincia</label>
                    </div>
                       <select class="custom-select" id="ddlProvinciaModificar" name="ddlProvinciaModificar" onChange="javascript:document.modificarAlumno.submit();" required>
                           <option selected disabled value="<%=provincia!=null?provincia.getId():"" %>" ><%=provincia!=null?provincia.getNombre():"Selecciona..." %></option>
                           <%
                           if ( provincias != null)
                       	{
                           	for(Provincia p : provincias)
                              	{
                           %>
                                   <option value="<%=p.getId()%>"> <%=p.getNombre()%> </option>
                           <%
                               }
                           }
                            else
                           {
                           %>
                               <option value="" > Selecciona... </option>
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
         			<label class="input-group-text" for="ddlLocalidad">Localidad</label>
			    </div>
                    <!-- <input type="text" class="form-control" id="ddlLocalidad"  required> -->
                    <select class="custom-select" id="ddlLocalidad" name="ddlLocalidad" required>
                    <!--    <option selected disabled value="application.getAttribute("LocalidadDoc")%>" >Selecciona...</option>-->
                        <option selected disabled value="<%=localidad!=null?localidad.getId():"" %>" ><%=localidad!=null?localidad.getNombre():"Selecciona..." %></option>
                        <%
                        if (localidades != null)
                    	{
                        	for(Localidad l : localidades)
                        	{
                        %>
                        		<option value="<%=l.getId()%>"> <%=l.getNombre()%> </option>
                        <%
                        	}
                    	}
                        else
                        {
                        %>
                        <option value="" > Selecciona... </option>
                   	<%
                    }
                    %>
                    </select>
                    <div class="valid-feedback">Completo!</div>
                    <div class="invalid-feedback"> Por favor completar este dato.</div>
                  </div>
               </div>
              <button class="btn btn-success" type="submit" name="btnModificarAlumno" value="aceptar">Guardar</button>
              <br>
              <br>
           </form>
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