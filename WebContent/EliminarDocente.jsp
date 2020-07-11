<%@page import="Entidades.Docente"%>
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
  	Docente d = null;
  	if (request.getAttribute("Docente") != null)
	{
    	d = (Docente)request.getAttribute("Docente");
	}
%>

<div class="row">
    <div class=" col-1"></div>
    <div class="col-10 card formulario">
        <div class="card-header text-center">
            <h5 class="card-title">Eliminar docente</h5>
        </div>
        <div class=" card-body">
            <form class="needs-validation" name="bajaDocente" action="ServletDocente?Baja=baja" method="get"novalidate>
                <div class="form-row">
                    <div class="col-md-3 mb-3">
                        <label for="txtLegajo">Legajo</label>
                        <input type="number" class="form-control" id="txtLegajo" name="txtLegajo" value="<%=d!=null?d.getLegajo():"" %>" required>                         
                        <div class="valid-feedback">Completo!</div>
                        <div class="invalid-feedback"> Por favor completar este dato.</div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="txtdni">DNI</label>
                        <input type="number" class="form-control" id="txtdni" name="txtDni" value="<%=d!=null?d.getDni():""%>" required>                         
                        <div class="valid-feedback">Completo!</div>
                        <div class="invalid-feedback"> Por favor completar este dato.</div>
                    </div>
                    <div class="col-md-3 mb-3 align-items-end input-group">
                        <button class="btn btn-info align-items-end" type="submit" name="btnBuscarDocenteBaja" value="buscar">Buscar</button>
                    </div>
                </div>
            
        </div>
        <div class=" card-footer">
                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <label for="txtNombre">Nombre</label>
                        <label class="form-control disabled" id="txtNombre" name="txtNombre" ><%=d != null ? d.getNombre():""%></label>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="txtApellido">Apellido</label>
                        <label class="form-control disabled" id="txtApellido" name="txtApellido"><%=d!=null?d.getApellido():""%></label>
                    </div>
                </div>

                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <label for="txtTelefono">Telefono</label>
                        <label class="form-control disabled" id="txtTelefono" name="txtTelefono"> <%=d!=null?d.getTelefono():""%> </label>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="txtEmail">Email</label>
                        <label class="form-control disabled" id="txtEmail" name="txtEmail"> <%=d!=null?d.getEmail():""%> </label>
                    </div>
                </div>

                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <label for="txtFechaNac">Fecha de nacimiento</label>
                        <label class="form-control disabled" id="txtFechaNac" name="txtFechaNac"> <%=d!=null?d.getNacimiento() :""%> </label>
                    </div>
                </div>

                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <label for="txtCalle">Calle</label>
                        <label class="form-control disabled" id="txtCalle" name="txtCalle"> <%=d != null ? d.getCalle() : ""  %> </label>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="txtCalle">Número</label>
                        <label class="form-control disabled" id="txtNumero" name="txtNumero"> <%=d != null ? d.getNumero() : ""  %> </label>
                    </div>
                </div>

                <div class="form-row">
                    <div class="col-md-6 mb-3">
                        <label for="txtLocalidad">Localidad</label>
                        <label class="form-control disabled" id="txtLocalidad" name="txtLocalidad"> <%=d!=null?d.getLocalidad().getNombre():""%> </label>                      
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="txtProvincia">Provicia</label>
                        <label class="form-control disabled" id="ddlProvincia" name="ddlProvincia"> <%=d != null ? d.getProvincia().getNombre() : ""  %> </label>
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
<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#staticBackdrop" type="submit" name="btnDocenteBaja" value="baja">
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
            Se eliminara a <%= d != null ? d.getApellido()+", "+d.getNombre(): "" %> Desea continuar?
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
          <button type="button" class="btn btn-danger" type="submit" name="btnConfirmarDocenteBaja" value="aceptar" >Confirmar</button>
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