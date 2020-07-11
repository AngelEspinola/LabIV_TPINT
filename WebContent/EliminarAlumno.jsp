<%@page import="Entidades.Alumno"%>
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
        .fondo{ background-color: #E8EAF6; }
        .formulario{ background-color: #ffffff; }
    </style>
</head>
<body class="fondo">
<jsp:include page="Menu.jsp"></jsp:include>
<div class="row">
    <br>
</div>
<%
  	Alumno a = null;
  	if (request.getAttribute("Alumno") != null)
	{
    	a=(Alumno)request.getAttribute("Alumno");
	}
%>

<div class="row">
    <div class=" col-1"></div>
    <div class="col-10 card formulario">
        <div class="card-header text-center">
            <h5 class="card-title">Eliminar alumno</h5>
        </div>
        <div class=" card-body">
            <form class="needs-validation" name="bajaAlumno" action="ServletAlumno?Baja=baja" method="get"novalidate>
                <div class="form-row">
                    <div class="col-md-3 mb-3">
                        <label for="txtLegajo">Legajo</label>
                        <input type="number" class="form-control" id="txtLegajo" name="txtLegajo" value="<%=a!=null?a.getLegajo():""%>" required>                         
                        <div class="valid-feedback">Completo!</div>
                        <div class="invalid-feedback"> Por favor completar este dato.</div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="txtdni">DNI</label>
                        <input type="number" class="form-control" id="txtdni" name="txtDni" value="<%=a!=null?a.getDni():""%>" required>                         
                        <div class="valid-feedback">Completo!</div>
                        <div class="invalid-feedback"> Por favor completar este dato.</div>
                    </div>
                    <div class="col-md-3 mb-3 align-items-end input-group">
                        <button class="btn btn-info align-items-end" type="submit" name="btnBuscarAlumnoBaja" value="buscar">Buscar</button>
                    </div>
                </div>
            
        </div>
        <div class=" card-footer">
                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <label for="txtNombre">Nombre</label>
                        <label class="form-control disabled" id="txtNombre" name="txtNombre" ><%=a!=null ?a.getNombre():""%></label>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="txtApellido">Apellido</label>
                        <label class="form-control disabled" id="txtApellido" name="txtApellido"><%=a!=null?a.getApellido():""%></label>
                    </div>
                </div>

                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <label for="txtTelefono">Telefono</label>
                        <label class="form-control disabled" id="txtTelefono" name="txtTelefono"> <%=a!=null?a.getTelefono():""%> </label>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="txtEmail">Email</label>
                        <label class="form-control disabled" id="txtEmail" name="txtEmail"> <%=a!=null?a.getEmail():""%> </label>
                    </div>
                </div>

                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <label for="txtFechaNac">Fecha de nacimiento</label>
                        <label class="form-control disabled" id="txtFechaNac" name="txtFechaNac"> <%=a!=null?a.getNacimiento():""%> </label>
                    </div>
                </div>

                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <label for="txtCalle">Calle</label>
                        <label class="form-control disabled" id="txtCalle" name="txtCalle"> <%=a!=null?a.getCalle():""%> </label>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="txtCalle">Número</label>
                        <label class="form-control disabled" id="txtNumero" name="txtNumero"> <%=a!=null?a.getNumero():""%> </label>
                    </div>
                </div>

                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <label for="txtLocalidad">Localidad</label>
                        <label class="form-control disabled" id="txtLocalidad" name="txtLocalidad"> <%=a!=null?a.getLocalidad().getNombre():""%> </label>                      
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="txtProvincia">Provicia</label>
                        <label class="form-control disabled" id="ddlProvincia" name="ddlProvincia"> <%=a!=null?a.getProvincia().getNombre():""%> </label>
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
<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#staticBackdrop" type="submit" name="btnAlumnoBaja" value="baja">
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
            Se eliminara a <%= a!=null?a.getApellido()+", "+a.getNombre()+".": "" %> Desea continuar?
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
          <button type="button" class="btn btn-danger" type="submit" name="btnConfirmarAlumnoBaja" value="aceptar" >Confirmar</button>
        </div>
      </div>
    </div>
  </div>

                    <br>
                    <br>
				</form>
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