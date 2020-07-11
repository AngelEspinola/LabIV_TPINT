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
   		<a class="navbar-brand" href="Main.jsp">Inicio</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
       	<span class="navbar-toggler-icon"></span>
       	</button>
   
       	<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
       	<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
           <li class="nav-item dropdown">
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
	                   <a class="dropdown-item" href="ServletCurso?Param=altaCurso">Alta</a>
	                   <a class="dropdown-item" href="ServletCurso?Param=bajaCurso">Baja</a>
                   <a class="dropdown-item" href="ServletCurso?Param=modificarCurso">Modificacion</a>
                   <div class="dropdown-divider"></div>
                   <a class="dropdown-item" href="ServletCurso?Param=listarCursos">Listado</a>
                   <div class="dropdown-divider"></div>
                   <a class="dropdown-item" href="ServletCalificar?IDDocente=1">Calificar Alumnos</a>
               	   </div>
				</li>
           <li class="nav-item dropdown">
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
       	
       	 <!-- <nav class="navbar navbar-expand-lg navbar-light bg-light container-fluid">-->
			
	            <!-- Nav Item - User Information -->
	            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
	            <li class="nav-item dropdown">
	              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                <span style="color:blue;"><%=d.toString()%></span>
	                <!-- <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60"> -->
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
	                  Cerrar sesión
	                </a>
	              </div>
	            </li>
	            </ul>
	     </nav>
	     <!-- End of Topbar -->
		</div>
	 <!-- </nav>-->
</div>
</header>
  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="ServletLogin?Param=1">Logout</a>
        </div>
      </div>
    </div>
  </div>
</body>
</html>