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
import Entidades.Docente;
import DaoImpl.ProvinciaDaoImpl;
import DaoImpl.LocalidadDaoImpl;
import DaoImpl.DocenteDaoImpl;

@WebServlet("/ServletDocente")
public class ServletDocente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ServletDocente() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet doGet");
		Docente docente = new Docente();
		Provincia provincia = null;
		Localidad localidad = null;
		ArrayList<Provincia> provincias = null;
		ArrayList<Localidad> localidades = null;
		DocenteDaoImpl DDao = new DocenteDaoImpl();
		ProvinciaDaoImpl PDao = new ProvinciaDaoImpl();
		LocalidadDaoImpl LDao = new LocalidadDaoImpl();
		String error = "";

		if( request.getParameter("btnAltaDocente") != null)
		{
			try
			{
				System.out.println("btnAltaDocente != null");
				System.out.println("ddlProvinciaApli:"+ getServletContext().getAttribute("ProvinciaDoc"));
				System.out.println("ddlLocalidad:"+request.getParameter("ddlLocalidad"));
				
				System.out.println("txtDni:"+ request.getParameter("txtDni"));
				docente.setLegajo(request.getParameter("txtLegajo"));
				docente.setDni(request.getParameter("txtDni"));
				docente.setNombre(request.getParameter("txtNombre"));
				docente.setApellido(request.getParameter("txtApellido"));
				//Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtFechaNac"));
				docente.setNacimiento(request.getParameter("txtFechaNac"));
				docente.setCalle(request.getParameter("txtCalle"));
				docente.setNumero(request.getParameter("txtNumero"));
				docente.setLocalidad( LDao.readOne(Integer.parseInt(request.getParameter("ddlLocalidad"))));
				docente.setProvincia(PDao.readOne(Integer.parseInt(getServletContext().getAttribute("ProvinciaDoc").toString())));
				docente.setEmail(request.getParameter("txtEmail"));
				docente.setTelefono(request.getParameter("txtTelefono"));
				
				if(DDao.insert(docente))
				{
					String success  = "�El docente ha sido generado con exito!";
					System.out.println(success);
					request.setAttribute("Exito", success);
					RequestDispatcher rq=request.getRequestDispatcher("/Exito.jsp");
					rq.include(request, response);
					return;
				}
				else
				{
					error = "Error al generar docente. Por favor intentelo nuevamente";
					System.out.println(error);
					request.setAttribute("Error", error);
				}				
			}
			catch(Exception e)
			{
				error = "Whoops! Algo fallo al procesar la peticion. Por favor revise los valores e intentelo nuevamente";
				System.out.println(error);
				request.setAttribute("Error", error);
			}
			
			RequestDispatcher rq=request.getRequestDispatcher("/AltaDocente.jsp");
			rq.include(request, response);
		}
		
		else if( request.getParameter("btnBuscarDocenteBaja") != null)
		{
			try
			{
				System.out.println("btnBuscarDocenteBaja != null");
				System.out.println("txtDni:"+ request.getParameter("txtDni"));
				System.out.println("txtLegajo:"+ request.getParameter("txtLegajo"));
				docente.setLegajo(request.getParameter("txtLegajo"));
				docente.setDni(request.getParameter("txtDni"));
				
				docente = DDao.buscarDocente(docente);
				if(docente != null)
				{
					getServletContext().setAttribute("Docente", docente);
				}
				else
				{
					//getServletContext().setAttribute("Docente", new Docente());
					error = "No se encontro el docente en nuestros registros. Por favor revise los valores e intente nuevamente.";
					System.out.println(error);
					request.setAttribute("Error", error);
				}
				/*
				System.out.println("docente DNI:"+ docente.getDni());
				System.out.println("docente Leg:"+ docente.getLegajo());
				System.out.println("docente Nombre:"+ docente.getNombre());
				 */				
			}
			catch(Exception e)
			{
				error = "Whoops! Algo fallo al procesar la peticion. Por favor revise los valores e intentelo nuevamente";
				System.out.println(error);
				request.setAttribute("Error", error);
			}
			RequestDispatcher rq=request.getRequestDispatcher("/EliminarDocente.jsp");
			rq.include(request, response);
		}
		else if( request.getParameter("btnDocenteBaja") != null)
		{
			try
			{
				if (getServletContext().getAttribute("Docente") != null)
				{
					docente = (Docente)getServletContext().getAttribute("Docente");
					docente.setEstado(false);
					
					if(DDao.baja(docente))
					{
						String success  = "�El docente ha sido eliminado con exito!";
						System.out.println(success);
						request.setAttribute("Exito", success);
						RequestDispatcher rq=request.getRequestDispatcher("/Exito.jsp");
						rq.include(request, response);
						return;
					}
					else
					{
						error = "Error al dar de baja docente. Por favor revise los datos e intente nuevamente.";
						System.out.println(error);
						request.setAttribute("Error", error);
					}
				}
				else
				{
					error = "No hay ningun docente cargado. Por favor primero busque el docente que desea dar de baja.";
					System.out.println(error);
					request.setAttribute("Error", error);
				}
			}
			catch(Exception e)
			{
				error = "Whoops! Algo fallo al procesar la peticion. Por favor revise los valores e intentelo nuevamente";
				System.out.println(error);
				request.setAttribute("Error", error);
			}
			System.out.println("btnDocenteBaja != null");
			
			
			RequestDispatcher rq=request.getRequestDispatcher("/EliminarDocente.jsp");
			rq.include(request, response);
		}
		
		
		else if( request.getParameter("Alta") != null)
		{
			try
			{
				System.out.println("Alta");
				getServletContext().setAttribute("Docente", null);
				provincias = new ArrayList<Provincia>();
				localidades = new ArrayList<Localidad>();
				provincias = PDao.readAll();
				request.setAttribute("Provincias", provincias);
				
				if( request.getParameter("ddlProvincia") != null)
				{
					System.out.println("ddlProvincia: "+request.getParameter("ddlProvincia"));
					int idProvincia = Integer.parseInt( request.getParameter("ddlProvincia"));
					provincia = PDao.readOne(idProvincia);
					localidades = LDao.readAll(idProvincia);
				}
				else
				{
					System.out.println("ddlProvincia: null");
					localidades = LDao.readAll();				
				}
				if( request.getParameter("ddlLocalidad") != null)
				{
					System.out.println("ddlLocalidad: "+request.getParameter("ddlLocalidad"));
					int idLocalidad = Integer.parseInt( request.getParameter("ddlLocalidad"));
					localidad = LDao.readOne(idLocalidad);
					provincia = PDao.readOne(localidad.getIdProvincia());
				}
				
				//Inicializo var Application
				System.out.println("cargo var Application");
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
				
				
				request.setAttribute("Provincias", provincias);
				request.setAttribute("Localidades", localidades);
				request.setAttribute("Provincia", provincia);
				request.setAttribute("Localidad", localidad);
			}
			catch(Exception e)
			{
				error = "Whoops! Algo fallo al procesar la peticion. Por favor revise los valores e intentelo nuevamente";
				System.out.println(error);
				request.setAttribute("Error", error);
			}
			RequestDispatcher rq=request.getRequestDispatcher("/AltaDocente.jsp");
        	rq.include(request, response);
		}
		else if( request.getParameter("ddlProvincia") != null)
		{
			System.out.println("ddlProvincia: "+request.getParameter("ddlProvincia"));
			if ( getServletContext().getAttribute("Docente") != null)
			{
				docente = (Docente)getServletContext().getAttribute("Docente");
			}
			provincias = new ArrayList<Provincia>();
			provincias = PDao.readAll();
			request.setAttribute("Provincias", provincias);
			int idProvincia = Integer.parseInt( request.getParameter("ddlProvincia"));
			provincia = PDao.readOne(idProvincia);
			getServletContext().setAttribute("ProvinciaDoc", request.getParameter("ddlProvincia"));
			
			docente.setProvincia(provincia);
			localidades = new ArrayList<Localidad>();
			localidades = LDao.readAll(idProvincia);

			request.setAttribute("Localidades", localidades);
			request.setAttribute("Provincia", provincia);
			
			//cargo var Application
			System.out.println("txtLegajo: "+request.getParameter("txtLegajo"));
			System.out.println("ddlProvincia: "+request.getParameter("ddlProvincia"));
			System.out.println("ddlLocalidad: "+request.getParameter("ddlLocalidad"));
			getServletContext().setAttribute("LegajoDoc", !request.getParameter("txtLegajo").isEmpty() ? request.getParameter("txtLegajo") : "");
	    	getServletContext().setAttribute("DNIDoc", !request.getParameter("txtDni").isEmpty() ? request.getParameter("txtDni") : "");
	    	getServletContext().setAttribute("NombreDoc", !request.getParameter("txtNombre").isEmpty() ? request.getParameter("txtNombre") : "");
	    	getServletContext().setAttribute("ApellidoDoc", !request.getParameter("txtApellido").isEmpty() ? request.getParameter("txtApellido") : "");
	    	getServletContext().setAttribute("NacimientoDoc", !request.getParameter("txtFechaNac").isEmpty() ? request.getParameter("txtFechaNac") : "");
	    	getServletContext().setAttribute("CalleDoc", !request.getParameter("txtCalle").isEmpty() ? request.getParameter("txtCalle") : "");
	    	getServletContext().setAttribute("NumeroDoc", !request.getParameter("txtNumero").isEmpty() ? request.getParameter("txtNumero") : "");
	    	getServletContext().setAttribute("LocalidadDoc", request.getParameter("ddlLocalidad") != null ? request.getParameter("ddlLocalidad") : "");
	    	getServletContext().setAttribute("EmailDoc", !request.getParameter("txtEmail").isEmpty() ? request.getParameter("txtEmail") : "");
	    	getServletContext().setAttribute("TelefonoDoc", !request.getParameter("txtTelefono").isEmpty() ? request.getParameter("txtTelefono") : "");
	    	getServletContext().setAttribute("Docente", docente);
	    	getServletContext().setAttribute("Provincias", provincias);
	    	getServletContext().setAttribute("Localidades", localidades);
	    	
	    	RequestDispatcher rq=request.getRequestDispatcher("/AltaDocente.jsp");
        	rq.include(request, response);
		}
		else if( request.getParameter("Baja") != null)
		{
			System.out.println("Baja");
			getServletContext().setAttribute("Docente", null);
			RequestDispatcher rq=request.getRequestDispatcher("/EliminarDocente.jsp");
	       	rq.include(request, response);
		}
		else if( request.getParameter("Modificar") != null)
		{
			System.out.println("Modificar");
			docente = new Docente();
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
			
	    	getServletContext().setAttribute("Docente", docente);
	    	getServletContext().setAttribute("Provincias", provincias);
	    	getServletContext().setAttribute("Localidades", localidades);
			RequestDispatcher rq=request.getRequestDispatcher("/ModificarDocente.jsp");
	       	rq.include(request, response);
		}
		else if( request.getParameter("btnBuscarDocenteModificar") != null)
		{
			try
			{
				System.out.println("btnBuscarDocenteModificar != null");
				System.out.println("txtLegajo:"+ request.getParameter("txtLegajo"));
				System.out.println("txtDni:"+ request.getParameter("txtDni"));
				docente.setLegajo(request.getParameter("txtLegajo"));
				docente.setDni(request.getParameter("txtDni"));
				
				docente = DDao.buscarDocente(docente);
				if(docente !=null)
				{
					/*
				System.out.println("DocenteLegajo:"+ docente.getLegajo());
				System.out.println("txtLegajo:"+ Integer.parseInt(request.getParameter("txtLegajo")));
				System.out.println("DocenteDni:"+ docente.getDni());
				System.out.println("txtDni:"+ request.getParameter("txtDni"))*/
					if(docente.getDni() == request.getParameter("txtDni") && docente.getLegajo() == request.getParameter("txtLegajo"))
					{
						getServletContext().setAttribute("LegajoDoc",request.getParameter("txtLegajo"));
						getServletContext().setAttribute("DNIDoc",request.getParameter("txtDni"));
						getServletContext().setAttribute("DNIOrigin", request.getParameter("txtDni"));
					}
					getServletContext().setAttribute("DNIOrigin", docente.getDni());
					
					provincias  = new ArrayList<Provincia>();
					localidades = new ArrayList<Localidad>();
					provincias  = PDao.readAll();
					localidades = LDao.readAll();
					getServletContext().setAttribute("Provincias", provincias);
					getServletContext().setAttribute("Localidades", localidades);
					getServletContext().setAttribute("Provincia", docente.getProvincia());
					getServletContext().setAttribute("Localidad", docente.getLocalidad());
					
					getServletContext().setAttribute("Docente", docente);				
				}
				else
				{
					error = "No se encuentra docente. Por favor revise los valores e intente nuevamente.";
					System.out.println(error);
					request.setAttribute("Error", error);
				}
			}
			catch(Exception e)
			{
				error = "Whoops! Algo fallo al procesar la peticion. Por favor revise los valores e intentelo nuevamente";
				System.out.println(error);
				request.setAttribute("Error", error);
			}
			RequestDispatcher rq=request.getRequestDispatcher("/ModificarDocente.jsp");
			rq.include(request, response);
		}
		else if( request.getParameter("ddlProvinciaModificar") != null)
		{
			System.out.println("ddlProvinciaModificar: "+request.getParameter("ddlProvinciaModificar"));
			if ( getServletContext().getAttribute("Docente") != null)
			{
				docente = (Docente)getServletContext().getAttribute("Docente");
			}
			provincias = new ArrayList<Provincia>();
			provincias = PDao.readAll();
			request.setAttribute("Provincias", provincias);
			int idProvincia = Integer.parseInt( request.getParameter("ddlProvinciaModificar"));
			provincia = PDao.readOne(idProvincia);
			getServletContext().setAttribute("ProvinciaDoc", request.getParameter("ddlProvinciaModificar"));
			
			docente.setProvincia(provincia);
			localidades = new ArrayList<Localidad>();
			localidades = LDao.readAll(idProvincia);
			
			//cargo var Application
			System.out.println("txtLegajo: "+request.getParameter("txtLegajoNew"));
			System.out.println("ddlProvincia: "+request.getParameter("ddlProvinciaModificar"));
			System.out.println("ddlLocalidad: "+request.getParameter("ddlLocalidad"));
			System.out.println("txtLegajoNew: "+request.getParameter("txtLegajoNew"));
			System.out.println("txtDniNew: "+request.getParameter("txtDniNew"));
			System.out.println("txtNombre: "+request.getParameter("txtNombre"));
			
			docente.setLegajo(request.getParameter("txtLegajoNew"));
			docente.setDni(request.getParameter("txtDniNew"));
			docente.setNombre(request.getParameter("txtNombre"));
			docente.setApellido(request.getParameter("txtApellido"));
			docente.setNacimiento(request.getParameter("txtFechaNac"));
			docente.setCalle(request.getParameter("txtCalle"));
			docente.setNumero(request.getParameter("txtNumero"));
			docente.setEmail(request.getParameter("txtEmail"));
			docente.setTelefono(request.getParameter("txtTelefono"));

			getServletContext().setAttribute("Docente", docente);
	    	getServletContext().setAttribute("Provincias", provincias);
	    	getServletContext().setAttribute("Localidades", localidades);
	    	
	    	RequestDispatcher rq=request.getRequestDispatcher("/ModificarDocente.jsp");
        	rq.include(request, response);
		}
		else if( request.getParameter("btnModificarDocente") != null)
		{
			try
			{
				Docente aux = new Docente();
				aux = (Docente)getServletContext().getAttribute("Docente");
				System.out.println("btnModificarDocente != null");
				
				System.out.println("txtDni:"+ request.getParameter("txtDniNew"));
				System.out.println("ddlProvincia:"+ request.getParameter("ddlProvinciaModificar"));
				System.out.println("ddlLocalidad:"+ request.getParameter("ddlLocalidad"));
				
				docente.setLegajo( request.getParameter("txtLegajoNew") !=null?request.getParameter("txtLegajoNew"):aux.getLegajo());
				docente.setDni(request.getParameter("txtDniNew") !=null? request.getParameter("txtDniNew"):aux.getDni());
				docente.setNombre(request.getParameter("txtNombre") !=null?request.getParameter("txtNombre"):aux.getNombre());
				docente.setApellido(request.getParameter("txtApellido") !=null?request.getParameter("txtApellido"):aux.getApellido());
				docente.setNacimiento(request.getParameter("txtFechaNac") !=null?request.getParameter("txtFechaNac"):aux.getNacimiento());
				docente.setCalle(request.getParameter("txtCalle") !=null?request.getParameter("txtCalle"):aux.getCalle());
				docente.setNumero(request.getParameter("txtNumero") !=null?request.getParameter("txtNumero"):aux.getNumero());
				localidad = request.getParameter("ddlLocalidad") !=null?new Localidad(Integer.parseInt(request.getParameter("ddlLocalidad"))):new Localidad(aux.getLocalidad().getId());
				provincia = request.getParameter("ddlProvinciaModificar") !=null?new Provincia(Integer.parseInt(request.getParameter("ddlProvinciaModificar"))):new Provincia(aux.getProvincia().getId());
				docente.setLocalidad(localidad);
				docente.setProvincia(provincia);
				docente.setEmail(request.getParameter("txtEmail") !=null?request.getParameter("txtEmail"):aux.getEmail());
				docente.setTelefono(request.getParameter("txtTelefono") !=null?request.getParameter("txtTelefono"):aux.getTelefono());
				String dniOrigin = getServletContext().getAttribute("DNIOrigin").toString();
				if(DDao.modify(docente, dniOrigin))
				{
					String success  = "�El docente ha sido modificado con exito!";
					System.out.println(success);
					request.setAttribute("Exito", success);
					RequestDispatcher rq=request.getRequestDispatcher("/Exito.jsp");
					rq.include(request, response);
					return;
				}
				else
				{
					error = "Error al modificar docente en nuestros registros. Por favor revise los valores e intente nuevamente.";
					System.out.println(error);
					request.setAttribute("Error", error);
				}
			}
			catch(Exception e)
			{
				error = "Whoops! Algo fallo al procesar la peticion. Por favor revise los valores e intentelo nuevamente";
				System.out.println(error);
				request.setAttribute("Error", error);
			}
			RequestDispatcher rq=request.getRequestDispatcher("ServletReporte");
			rq.include(request, response);
		}
		else
		{
			System.out.println("listar");
			//Cargo los docentes
			ArrayList<Docente> ListDocente = DDao.readAll();
			System.out.println(ListDocente.size());
			request.setAttribute("Docentes", ListDocente);
			RequestDispatcher rq=request.getRequestDispatcher("/ListarDocentes.jsp");
        	rq.include(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
