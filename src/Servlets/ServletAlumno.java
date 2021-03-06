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
			if(request.getParameter("txtFechaNac")!=null)
			{
				System.out.println("Fecha: "+request.getParameter("txtFechaNac"));
			}
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
				System.out.println("Alta con �xito.");
			}
			else
			{
				System.out.println("Fall� la modificaci�n.");
			}
			RequestDispatcher rq=request.getRequestDispatcher("Main.jsp");
			rq.include(request, response);
		}
		
		//-----------------------------------------------BAJA---------------------------------------------------
		else if( request.getParameter("Baja") != null)
		{
			System.out.println("Baja");
			RequestDispatcher rq=request.getRequestDispatcher("/EliminarAlumno.jsp");
	       	rq.include(request, response);
		}
		else if( request.getParameter("btnBuscarAlumnoBaja") != null)
		{
			System.out.println("btnBuscarAlumnoBaja != null");
			System.out.println("txtDni:"+ request.getParameter("txtDni"));
			System.out.println("txtLegajo:"+ request.getParameter("txtLegajo"));
			alumno.setLegajo(request.getParameter("txtLegajo"));
			alumno.setDni(request.getParameter("txtDni"));
			
			alumno = DDao.buscarAlumno(alumno);
			if(alumno != null)
			{
				request.setAttribute("Alumno", alumno);
				getServletContext().setAttribute("Alumno",alumno);
			}
			else
			{
				request.setAttribute("Alumno", new Alumno());
				getServletContext().setAttribute("Alumno", new Alumno());
			}
			/*
			System.out.println("alumno DNI:"+ alumno.getDni());
			System.out.println("alumno Leg:"+ alumno.getLegajo());
			System.out.println("alumno Nombre:"+ alumno.getNombre());
			*/
			RequestDispatcher rq=request.getRequestDispatcher("/EliminarAlumno.jsp");
			rq.include(request, response);
		}
		else if( request.getParameter("btnConfirmarAlumnoBaja") != null)
		{
			System.out.println("btnAlumnoBaja != null");
			
			System.out.println("txtDni:"+ request.getParameter("txtDni"));
			
			alumno = (Alumno)getServletContext().getAttribute("Alumno");
			//alumno.setLegajo(request.getParameter("txtLegajo"));
			//alumno.setDni(request.getParameter("txtDni"));
			alumno.setEstado(0);
			
			if(DDao.baja(alumno))
			{
				System.out.println("Baja logica con exito.");
			}
			else
			{
				System.out.println("Fall� la baja logica.");
			}
			RequestDispatcher rq=request.getRequestDispatcher("/Main.jsp");
			rq.include(request, response);
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
