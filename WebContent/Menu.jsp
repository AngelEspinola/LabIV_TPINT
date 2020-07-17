<%@page import="Entidades.Usuario"%>
<%@page import="Entidades.Docente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es-ES">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
  	Usuario u = null;
  	if (session.getAttribute("Login") != null)
	{
  		u = (Usuario)session.getAttribute("Login");
	}
  	Docente d = null;
  	if (session.getAttribute("User") != null)
	{
    	d = (Docente)session.getAttribute("User");
	}
%>

<header>
<div>
	<nav class="navbar navbar-expand-lg navbar-light bg-light container-fluid">
   		<a class="navbar-brand" href="ServletReporte">Inicio</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
       	<span class="navbar-toggler-icon"></span>
       	</button>
       	<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
       	<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
           <li class="nav-item dropdown" <%=u.getRol()==1?"":"hidden=\"true\""%>>
               <a class="nav-link dropdown-toggle" href="#" id="ddlAlumnos" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                   Alumnos
               </a>
               <div class="dropdown-menu" aria-labelledby="ddlAlumnos">
                   <a class="dropdown-item" href="ServletAlumno?Alta=alta">Alta</a>
                   <a class="dropdown-item" href="ServletAlumno?Baja=baja">Baja</a>
                   <a class="dropdown-item" href="ServletAlumnoModificar?Modificar=modificar">Modificacion</a>
                   <div class="dropdown-divider"></div>
                   <a class="dropdown-item" href="ServletAlumnoModificar?ListarAlumnos=listar">Listado</a>
	           </div>
           </li>
				<li class="nav-item dropdown">
	               <a class="nav-link dropdown-toggle" href="#" id="ddlCursos" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                   Cursos
	               </a>
	               <div class="dropdown-menu" aria-labelledby="ddlCursos">
	                   	<a class="dropdown-item" href="ServletCurso?Param=altaCurso" <%=u.getRol()==1?"":"hidden=\"true\""%>>Alta</a>
	                   	<a class="dropdown-item" href="ServletCurso?Param=bajaCurso" <%=u.getRol()==1?"":"hidden=\"true\""%>>Baja</a>
                   		<a class="dropdown-item" href="ServletCurso?Param=modificarCurso" <%=u.getRol()==1?"":"hidden=\"true\""%>>Modificacion</a>
                   <div class="dropdown-divider"></div>
                   	<a class="dropdown-item" href="ServletCurso?Param=listarCursos">Listado</a>
                   <div class="dropdown-divider"></div>
                   	<a class="dropdown-item" href="ServletCurso?Param=inscripcionCurso">Inscripcion a curso</a>
               	   </div>
				</li>
           <li class="nav-item dropdown" <%=u.getRol()==1?"":"hidden=\"true\""%>>
               <a class="nav-link dropdown-toggle" href="#" id="ddlDocentes" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                   Docentes
               </a>
               <div class="dropdown-menu" aria-labelledby="ddlDocentes">
                   <a class="dropdown-item" href="ServletDocente?Alta=alta">Alta</a>
                   <a class="dropdown-item" href="ServletDocente?Baja=baja">Baja</a>
                   <a class="dropdown-item" href="ServletDocente?Modificar=modificar">Modificacion</a>
                   <div class="dropdown-divider"></div>
                   <a class="dropdown-item" href="ServletDocente?Listar=listar">Listado</a>
               </div>
           </li>
       	</ul>
	
	    <!-- Nav Item - User Information -->
        <ul class="form-inline my-2 my-lg-0">
        <div class="form-inline my-2 my-lg-0">
        <li class="nav-item dropdown" >
          <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span style="color:blue;"><%=d != null ? d.toString() : "Admin"%></span>
            <!-- <img class="img-profile rounded-circle" src="https://frgp.cvg.utn.edu.ar/pluginfile.php/63798/user/icon/snap/f1?rev=1246421"> -->
          </a>
          <!-- Dropdown - User Information -->
          <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
            <a class="dropdown-item" href="#">
              <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
              <span>Perfil <%=u.getRolDescripcion()%></span>
            </a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
              <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
              Cerrar sesion
            </a>
          </div>
        </li>
        </div>
    	</ul>
    	</div>
	</nav>
	     <!-- End of Topbar -->
</div>

</header>
  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">&iquest;Listo para salir?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">No</span>
          </button>
        </div>
        <div class="modal-body">Seleccione "Cerrar sesion" a continuacion si esta listo para finalizar su sesion actual.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
          <a class="btn btn-primary" href="ServletLogin?Param=1">Cerrar sesion</a>
        </div>
      </div>
    </div>
  </div>
</body>
</html>