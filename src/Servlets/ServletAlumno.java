package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Provincia;
import Entidades.Localidad;
import Entidades.Alumno;
import DaoImpl.ProvinciaDaoImpl;
import DaoImpl.LocalidadDaoImpl;
import DaoImpl.AlumnoDaoImpl;

@WebServlet("/ServletAlumno")
public class ServletAlumno extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public ServletAlumno() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	System.out.println("Servlet doGet");
		Alumno alumno = new Alumno();
		Provincia provincia = null;
		Localidad localidad = null;
		ArrayList<Provincia> provincias = null;
		ArrayList<Localidad> localidades = null;
		AlumnoDaoImpl DDao = new AlumnoDaoImpl();
		ProvinciaDaoImpl PDao = new ProvinciaDaoImpl();
		LocalidadDaoImpl LDao = new LocalidadDaoImpl();
		//-------------------------------------------------------ALTA----------------------------------------------------
		if( request.getParameter("Alta") != null)
		{
			System.out.println("Alta");
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
			RequestDispatcher rq=request.getRequestDispatcher("/AltaAlumno.jsp");
	       	rq.include(request, response);
		}
		else if( request.getParameter("ddlProvincia") != null)
		{
			System.out.println("ddlProvincia: "+request.getParameter("ddlProvincia"));
			if ( getServletContext().getAttribute("Alumno") != null)
			{
				alumno = (Alumno)getServletContext().getAttribute("Alumno");
			}
			provincias = new ArrayList<Provincia>();
			provincias = PDao.readAll();
			
			int idProvincia = Integer.parseInt( request.getParameter("ddlProvincia"));
			provincia = PDao.readOne(idProvincia);
			getServletContext().setAttribute("ProvinciaDoc", request.getParameter("ddlProvincia"));
			
			alumno.setProvincia(provincia);
			localidades = new ArrayList<Localidad>();
			localidades = LDao.readAll(idProvincia);
			
			alumno.setLegajo(    request.getParameter("txtLegajo")!=""?request.getParameter("txtLegajo"):"");
			alumno.setDni(       request.getParameter("txtDni")!=""?request.getParameter("txtDni"):"");
			alumno.setNombre(    request.getParameter("txtNombre")!=""?request.getParameter("txtNombre"):"");
			alumno.setApellido(  request.getParameter("txtApellido")!=""?request.getParameter("txtApellido"):"");
			alumno.setNacimiento(request.getParameter("txtFechaNac")!=""?request.getParameter("txtFechaNac"):"");
			alumno.setCalle(     request.getParameter("txtCalle")!=""?request.getParameter("txtCalle"):"");
			alumno.setNumero(	 request.getParameter("txtNumero")!=""?request.getParameter("txtNumero"):"");
			alumno.setEmail(     request.getParameter("txtEmail")!=""?request.getParameter("txtEmail"):"");
			alumno.setTelefono(  request.getParameter("txtTelefono")!=""?request.getParameter("txtTelefono"):"");

			getServletContext().setAttribute("Alumno", alumno);
	    	getServletContext().setAttribute("Provincias", provincias);
	    	getServletContext().setAttribute("Localidades", localidades);
	    	
	    	RequestDispatcher rq=request.getRequestDispatcher("/AltaAlumno.jsp");
        	rq.include(request, response);
		}
		else if( request.getParameter("btnAltaAlumno") != null)
		{
			try
			{
				System.out.println("btnAltaAlumno != null");
				Alumno aux = new Alumno();
				aux = (Alumno)getServletContext().getAttribute("Alumno");
				
				System.out.println("txtDni:"+ request.getParameter("txtDni"));
				System.out.println("ddlProvincia:"+ request.getParameter("ddlProvincia"));
				System.out.println("ddlLocalidad:"+ request.getParameter("ddlLocalidad"));
				
				alumno.setLegajo( request.getParameter("txtLegajo") !=null?request.getParameter("txtLegajo"):aux.getLegajo());
				alumno.setDni(request.getParameter("txtDni") !=null? request.getParameter("txtDni"):aux.getDni());
				alumno.setNombre(request.getParameter("txtNombre") !=null?request.getParameter("txtNombre"):aux.getNombre());
				alumno.setApellido(request.getParameter("txtApellido") !=null?request.getParameter("txtApellido"):aux.getApellido());
				alumno.setNacimiento(request.getParameter("txtFechaNac") !=null?request.getParameter("txtFechaNac"):aux.getNacimiento());
				alumno.setCalle(request.getParameter("txtCalle") !=null?request.getParameter("txtCalle"):aux.getCalle());
				alumno.setNumero(request.getParameter("txtNumero") !=null?request.getParameter("txtNumero"):aux.getNumero());
				localidad = request.getParameter("ddlLocalidad") !=null?new Localidad(Integer.parseInt(request.getParameter("ddlLocalidad"))):new Localidad(aux.getLocalidad().getId());
				provincia = request.getParameter("ddlProvincia") !=null?new Provincia(Integer.parseInt(request.getParameter("ddlProvincia"))):new Provincia(aux.getProvincia().getId());
				alumno.setLocalidad(localidad);
				alumno.setProvincia(provincia);
				alumno.setEmail(request.getParameter("txtEmail") !=null?request.getParameter("txtEmail"):aux.getEmail());
				alumno.setTelefono(request.getParameter("txtTelefono") !=null?request.getParameter("txtTelefono"):aux.getTelefono());
				if(DDao.insert(alumno))
				{
					String success  = "El alumno ha sido generado con exito!";
					System.out.println(success);
					request.setAttribute("Exito", success);
					RequestDispatcher rq=request.getRequestDispatcher("/Exito.jsp");
					rq.include(request, response);
					return;
				}
				else
				{
					String error = "Error al dar de alta alumno. Por favor revise los datos ingresados e intente nuevamente.";
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
			RequestDispatcher rq=request.getRequestDispatcher("AltaAlumno.jsp");
			rq.include(request, response);
		}
		
		//-----------------------------------------------BAJA---------------------------------------------------
		else if( request.getParameter("Baja") != null)
		{
			System.out.println("Baja");
			getServletContext().setAttribute("Alumno", null);
			RequestDispatcher rq=request.getRequestDispatcher("/EliminarAlumno.jsp");
	       	rq.include(request, response);
		}
		else if( request.getParameter("btnBuscarAlumnoBaja") != null)
		{
			try
			{
				System.out.println("btnBuscarAlumnoBaja != null");
				System.out.println("txtDni:"+ request.getParameter("txtDni"));
				System.out.println("txtLegajo:"+ request.getParameter("txtLegajo"));
				alumno.setLegajo(request.getParameter("txtLegajo"));
				alumno.setDni(request.getParameter("txtDni"));
				
				alumno = DDao.buscarAlumno(alumno);
				if(alumno != null)
				{
					getServletContext().setAttribute("Alumno", alumno);
				}
				else
				{
					//getServletContext().setAttribute("Alumno", new Alumno());
					String error = "No se encuentra alumno en nuestros registros. Por favor revise los datos ingresados e intente nuevamente.";
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
			
			RequestDispatcher rq=request.getRequestDispatcher("/EliminarAlumno.jsp");
			rq.include(request, response);
		}
		else if( request.getParameter("btnAlumnoBaja") != null)
		{
			try
			{
				System.out.println("btnAlumnoBaja != null");				
				
				if (getServletContext().getAttribute("Alumno") != null)
				{
					alumno = (Alumno)getServletContext().getAttribute("Alumno");
					alumno.setEstado(false);
					
					if(DDao.baja(alumno))
					{
						String success  = "El alumno ha sido eliminado con exito!";
						System.out.println(success);
						request.setAttribute("Exito", success);
						RequestDispatcher rq=request.getRequestDispatcher("/Exito.jsp");
						rq.include(request, response);
						return;
					}
					else
					{
						String error = "Error al eliminar alumno en nuestros registros. Por favor revise los datos ingresados e intente nuevamente.";
						System.out.println(error);
						request.setAttribute("Error", error);
					}
				}
				else
				{
					String error = "No hay ningun alumno cargado. Por favor primero busque el alumno que desea dar de baja.";
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
			RequestDispatcher rq=request.getRequestDispatcher("/EliminarAlumno.jsp");//Primero deberia ir a una pagina de aviso
			rq.include(request, response);
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
