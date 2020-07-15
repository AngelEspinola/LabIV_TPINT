package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoImpl.AlumnoDaoImpl;
import DaoImpl.LocalidadDaoImpl;
import DaoImpl.ProvinciaDaoImpl;
import Entidades.Usuario;
import Entidades.Alumno;
import Entidades.Localidad;
import Entidades.Provincia;


@WebServlet("/ServletAlumnoModificar")
public class ServletAlumnoModificar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletAlumnoModificar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet doGet");
		
		if (request.getSession().getAttribute("Login") != null)
		{
			Usuario login = (Usuario)request.getSession().getAttribute("Login");
			System.out.println("Perfil: "+login);
			if(login.getRol() == 1)
			{
				System.out.println("Perfil Administrador");
				Alumno alumno = new Alumno();
				Provincia provincia = null;
				Localidad localidad = null;
				ArrayList<Provincia> provincias = null;
				ArrayList<Localidad> localidades = null;
				AlumnoDaoImpl DDao = new AlumnoDaoImpl();
				ProvinciaDaoImpl PDao = new ProvinciaDaoImpl();
				LocalidadDaoImpl LDao = new LocalidadDaoImpl();
				
				if( request.getParameter("Modificar") != null)
				{
					System.out.println("Modificar");
					alumno = new Alumno();
					provincias = new ArrayList<Provincia>();
					localidades = new ArrayList<Localidad>();
					provincias = PDao.readAll();
					localidades = LDao.readAll();
					
					//Inicializo var Application
					getServletContext().setAttribute("LegajoDoc", "");
					getServletContext().setAttribute("DNIDoc", "");
					getServletContext().setAttribute("NombreDoc", "");
					getServletContext().setAttribute("ApellidoDoc", "");
					getServletContext().setAttribute("NacimientoDoc", "");
					getServletContext().setAttribute("CalleDoc", "");
					getServletContext().setAttribute("NumeroDoc", "");
					getServletContext().setAttribute("ProvinciaDoc", "");
					getServletContext().setAttribute("LocalidadDoc", "");
					getServletContext().setAttribute("EmailDoc", "");
					getServletContext().setAttribute("TelefonoDoc", "");
					
					getServletContext().setAttribute("Alumno", alumno);
					getServletContext().setAttribute("Provincias", provincias);
					getServletContext().setAttribute("Localidades", localidades);
					RequestDispatcher rq=request.getRequestDispatcher("/ModificarAlumno.jsp");
					rq.include(request, response);
				}
				else if( request.getParameter("btnBuscarAlumnoModificar") != null)
				{
					try
					{	
						System.out.println("btnBuscarAlumnoModificar != null");
						System.out.println("txtLegajo:"+ request.getParameter("txtLegajo"));
						System.out.println("txtDni:"+ request.getParameter("txtDni"));
						alumno.setLegajo(request.getParameter("txtLegajo"));
						alumno.setDni(request.getParameter("txtDni"));
						
						alumno = DDao.buscarAlumno(alumno);
						if(alumno !=null)
						{
							/*
							System.out.println("AlumnoLegajo:"+ alumno.getLegajo());
							System.out.println("txtLegajo:"+ Integer.parseInt(request.getParameter("txtLegajo")));
							System.out.println("AlumnoDni:"+ alumno.getDni());
							System.out.println("txtDni:"+ request.getParameter("txtDni"))*/
							if(alumno.getDni() == request.getParameter("txtDni") && alumno.getLegajo() == request.getParameter("txtLegajo"))
							{
								getServletContext().setAttribute("LegajoDoc",request.getParameter("txtLegajo"));
								getServletContext().setAttribute("DNIDoc",request.getParameter("txtDni"));
								getServletContext().setAttribute("DNIOrigin", request.getParameter("txtDni"));
							}
							getServletContext().setAttribute("DNIOrigin", alumno.getDni());
							
							provincias  = new ArrayList<Provincia>();
							localidades = new ArrayList<Localidad>();
							provincias  = PDao.readAll();
							localidades = LDao.readAll();
							getServletContext().setAttribute("Provincias", provincias);
							getServletContext().setAttribute("Localidades", localidades);
							getServletContext().setAttribute("Provincia", alumno.getProvincia());
							getServletContext().setAttribute("Localidad", alumno.getLocalidad());
							
							getServletContext().setAttribute("Alumno", alumno);				
						}
						else
						{
							String error = "No se encontro el alumno en nuestros registros. Por favor revise los datos e intente nuevamente.";
							System.out.println(error);
							request.setAttribute("Error", error);
						}
					}
					catch(Exception e)
					{
						String error = "Whoops! Algo fallo al procesas la solicitud. Por favor revise los valores e intente nuevamente";
						System.out.println(error);
						request.setAttribute("Error", error);
					}
					RequestDispatcher rq=request.getRequestDispatcher("/ModificarAlumno.jsp");
					rq.include(request, response);
				}
				else if( request.getParameter("ddlProvinciaModificar") != null)
				{
					System.out.println("ddlProvinciaModificar: "+request.getParameter("ddlProvinciaModificar"));
					if ( getServletContext().getAttribute("Alumno") != null)
					{
						alumno = (Alumno)getServletContext().getAttribute("Alumno");
					}
					provincias = new ArrayList<Provincia>();
					provincias = PDao.readAll();
					request.setAttribute("Provincias", provincias);
					int idProvincia = Integer.parseInt( request.getParameter("ddlProvinciaModificar"));
					provincia = PDao.readOne(idProvincia);
					getServletContext().setAttribute("ProvinciaDoc", request.getParameter("ddlProvinciaModificar"));
					
					alumno.setProvincia(provincia);
					localidades = new ArrayList<Localidad>();
					localidades = LDao.readAll(idProvincia);
					
					//cargo var Application
					System.out.println("txtLegajo: "+request.getParameter("txtLegajoNew"));
					System.out.println("ddlProvincia: "+request.getParameter("ddlProvinciaModificar"));
					System.out.println("ddlLocalidad: "+request.getParameter("ddlLocalidad"));
					System.out.println("txtLegajoNew: "+request.getParameter("txtLegajoNew"));
					System.out.println("txtDniNew: "+request.getParameter("txtDniNew"));
					System.out.println("txtNombre: "+request.getParameter("txtNombre"));
					
					alumno.setLegajo(request.getParameter("txtLegajoNew"));
					alumno.setDni(request.getParameter("txtDniNew"));
					alumno.setNombre(request.getParameter("txtNombre"));
					alumno.setApellido(request.getParameter("txtApellido"));
					alumno.setNacimiento(request.getParameter("txtFechaNac"));
					alumno.setCalle(request.getParameter("txtCalle"));
					alumno.setNumero(request.getParameter("txtNumero"));
					alumno.setEmail(request.getParameter("txtEmail"));
					alumno.setTelefono(request.getParameter("txtTelefono"));
					
					getServletContext().setAttribute("Alumno", alumno);
					getServletContext().setAttribute("Provincias", provincias);
					getServletContext().setAttribute("Localidades", localidades);
					
					RequestDispatcher rq=request.getRequestDispatcher("/ModificarAlumno.jsp");
					rq.include(request, response);
				}
				else if( request.getParameter("btnModificarAlumno") != null)
				{
					try
					{
						Alumno aux = new Alumno();
						aux = (Alumno)getServletContext().getAttribute("Alumno");
						System.out.println("btnModificarAlumno != null");
						
						System.out.println("txtDni:"+ request.getParameter("txtDniNew"));
						System.out.println("ddlProvincia:"+ request.getParameter("ddlProvinciaModificar"));
						System.out.println("ddlLocalidad:"+ request.getParameter("ddlLocalidad"));
						
						alumno.setLegajo( request.getParameter("txtLegajoNew") !=null?request.getParameter("txtLegajoNew"):aux.getLegajo());
						alumno.setDni(request.getParameter("txtDniNew") !=null? request.getParameter("txtDniNew"):aux.getDni());
						alumno.setNombre(request.getParameter("txtNombre") !=null?request.getParameter("txtNombre"):aux.getNombre());
						alumno.setApellido(request.getParameter("txtApellido") !=null?request.getParameter("txtApellido"):aux.getApellido());
						alumno.setNacimiento(request.getParameter("txtFechaNac") !=null?request.getParameter("txtFechaNac"):aux.getNacimiento());
						alumno.setCalle(request.getParameter("txtCalle") !=null?request.getParameter("txtCalle"):aux.getCalle());
						alumno.setNumero(request.getParameter("txtNumero") !=null?request.getParameter("txtNumero"):aux.getNumero());
						localidad = request.getParameter("ddlLocalidad") !=null?new Localidad(Integer.parseInt(request.getParameter("ddlLocalidad"))):new Localidad(aux.getLocalidad().getId());
						provincia = request.getParameter("ddlProvinciaModificar") !=null?new Provincia(Integer.parseInt(request.getParameter("ddlProvinciaModificar"))):new Provincia(aux.getProvincia().getId());
						alumno.setLocalidad(localidad);
						alumno.setProvincia(provincia);
						alumno.setEmail(request.getParameter("txtEmail") !=null?request.getParameter("txtEmail"):aux.getEmail());
						alumno.setTelefono(request.getParameter("txtTelefono") !=null?request.getParameter("txtTelefono"):aux.getTelefono());
						String dniOrigin = getServletContext().getAttribute("DNIOrigin").toString();
						if(DDao.modify(alumno, dniOrigin))
						{
							String success  = "¡El alumno ha sido modificado con exito!";
							System.out.println(success);
							request.setAttribute("Exito", success);
							RequestDispatcher rq=request.getRequestDispatcher("/Exito.jsp");
							rq.include(request, response);
							return;
						}
						else
						{
							String error = "Error al modificar alumno en nuestros registros. Por favor revise los datos e intente nuevamente.";
							System.out.println(error);
							request.setAttribute("Error", error);
						}
					}
					catch(Exception e)
					{
						String error = "Whoops! Algo fallo al procesas la solicitud. Por favor revise los valores e intente nuevamente";
						System.out.println(error);
						request.setAttribute("Error", error);
					}
					RequestDispatcher rq=request.getRequestDispatcher("ModificarAlumno.jsp");
					rq.include(request, response);
				}
				else
				{
					System.out.println("listar");
					//Cargo los alumnos
					ArrayList<Alumno> ListAlumno = DDao.readAll();
					System.out.println(ListAlumno.size());
					request.setAttribute("Alumnos", ListAlumno);
					RequestDispatcher rq=request.getRequestDispatcher("/ListarAlumnos.jsp");
					rq.include(request, response);
				}
			}
			else
			{
				String log = "Lo siento, no tiene permiso Administrador.";
				request.setAttribute("Log",log);
				RequestDispatcher rq = request.getRequestDispatcher("/Main.jsp");
				rq.include(request, response);				
			}
		}
		else
		{
			String log = "Lo siento, debe iniciar Sesión.";
			request.setAttribute("Log",log);
			RequestDispatcher rq = request.getRequestDispatcher("/Login.jsp");
			rq.include(request, response);			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
