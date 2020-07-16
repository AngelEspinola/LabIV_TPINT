package Servlets;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.*;
import DaoImpl.*;

@WebServlet("/ServletCurso")
public class ServletCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ServletCurso() {
    	super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletCurso - Servlet doGet");
		Usuario usuario = (Usuario)request.getSession().getAttribute("Login");
		
		DocenteDaoImpl docenteDao = new DocenteDaoImpl();
		AlumnoDaoImpl alumnoDao = new AlumnoDaoImpl();
		MateriaDaoImpl materiaDao = new MateriaDaoImpl();
		CursoDaoImpl cursoDao = new CursoDaoImpl();
		AlumnoNotasDaoImpl cursoAlumno = new AlumnoNotasDaoImpl();
		
		String redirectJSP = "";
		if(request.getParameter("Param") != null)
		{
			//Cargo las Materias
			materiaDao = new MateriaDaoImpl();
			ArrayList<Materia> ListMateria = materiaDao.readAll();
			System.out.println("Se trajeron " + ListMateria.size() + " materias de la BBDD");
			request.getSession().setAttribute("Materias", ListMateria);
			
			//Cargo los docentes
			docenteDao = new DocenteDaoImpl();
			ArrayList<Docente> ListDocente = docenteDao.readAll();
			System.out.println("Se trajeron " + ListDocente.size() + " docentes de la BBDD");
			request.getSession().setAttribute("Docentes", ListDocente);
			
			
			//Cargo los alumnos
			ArrayList<Alumno> ListAlumno = alumnoDao.readAll();
			System.out.println("Se trajeron " + ListAlumno.size() + " alumnos de la BBDD");
			request.getSession().setAttribute("Alumnos", ListAlumno);
			
			cursoDao = new CursoDaoImpl();
			ArrayList<Curso> ListCurso;
			if(usuario.getRol() == 1)
			{
				ListCurso = cursoDao.readAll();
				System.out.println("Se trajeron " + ListCurso.size() + " cursos de la BBDD");
				request.getSession().setAttribute("Cursos", ListCurso);				
			}
			else
			{
				ListCurso = cursoDao.readAll(usuario.getID_docente());
				System.out.println("Se trajeron " + ListCurso.size() + " cursos de la BBDD");
				request.getSession().setAttribute("Cursos", ListCurso);				
			}
			
			String param = request.getParameter("Param");
			if(param.equals("altaCurso"))
			{
	        	System.out.println(request.getParameter("Param"));				
				redirectJSP = "/AltaCurso.jsp";
			}
			
			if(param.equals("bajaCurso"))
	        {
	        	System.out.println(request.getParameter("Param"));
	        	redirectJSP = "/EliminarCurso.jsp";
	        }
			if(param.equals("modificarCurso"))
	        {
	        	System.out.println(request.getParameter("Param"));
	        	redirectJSP = "/ModificarCurso.jsp";
	        }
			if(param.equals("listarCursos"))
	        {
	        	System.out.println(request.getParameter("Param"));
	        	redirectJSP = "/ListarCursos.jsp";
	        }
			if(param.equals("inscripcionCurso"))
	        {
	        	System.out.println(request.getParameter("Param"));
	        	redirectJSP = "/InscripcionCurso.jsp";
	        }
		}
        
		else if(request.getParameter("btnAltaCurso") != null)
		{
        	System.out.println("Accion boton 'Agregar curso'");
			Curso curso = new Curso();
			try
			{
				curso.setDocente(docenteDao.readID(Integer.parseInt(request.getParameter("ddlProfesor"))));
				//linea
				curso.setMateria(materiaDao.read(Integer.parseInt(request.getParameter("ddlMateria"))));
				curso.setCuatrimestre(Integer.parseInt(request.getParameter("ddlCuatrimestre")));
				curso.setAnio(Integer.parseInt(request.getParameter("txtAnio")));
				
				if(cursoDao.insert(curso))
				{
					String success  = "El curso ha sido generado con exito!";
					System.out.println(success);
					request.setAttribute("Exito", success);
					redirectJSP = "/Exito.jsp";
				}
				else
				{
					String error = "Ocurrio un error al crear el curso. Por favor revise los valores e intente nuevamente";
					System.out.println(error);
					request.setAttribute("Error", error);
					redirectJSP = "/AltaCurso.jsp";
				}
			}
			catch (Exception e)
			{
				String error = "Whoops! Algo fallo al intentar generar el curso. Por favor intente nuevamente";
				System.out.println(error);
				request.setAttribute("Error", error);
				redirectJSP = "/AltaCurso.jsp";
			}

		}
        
        else if(request.getParameter("btnGuardarCurso") != null)
		{
        	try
        	{        		
        		System.out.println("Accion boton 'Guardar curso' (Modificar curso)");
        		Curso curso = new Curso();
        		curso.setID(Integer.parseInt(request.getParameter("txtID")));
        		curso.setDocente(docenteDao.readID(Integer.parseInt(request.getParameter("ddlProfesor"))));
        		//linea
        		curso.setMateria(materiaDao.read(Integer.parseInt(request.getParameter("ddlMateria"))));
        		curso.setCuatrimestre(Integer.parseInt(request.getParameter("ddlCuatrimestre")));
        		
        		String anioNuevo = request.getParameter("txtAnioNuevo");
        		curso.setAnio(Integer.parseInt(anioNuevo));
        		
        		if(cursoDao.modify(curso))
        		{
        			String success  = "El curso ha sido actualizado con exito!";
					System.out.println(success);
					request.setAttribute("Exito", success);
					redirectJSP = "/Exito.jsp";
        		}
        		else
        		{
        			String error = "Error al modificar curso, por favor revise los campos e intente nuevamente.";
        			System.out.println(error);
        			request.setAttribute("Error", error);
        			redirectJSP = "/ModificarCurso.jsp";
        		}
        	}
        	catch(Exception e)
        	{
        		String error = "Error al modificar curso, por favor revise los campos e intente nuevamente.";
    			System.out.println(error);
    			request.setAttribute("Error", error);
    			redirectJSP = "/ModificarCurso.jsp";
        	}
		}
        
        else if(request.getParameter("btnBurscarCurso_ModificarCurso") != null)
        {
        	try
        	{
        		System.out.println("btnBurscarCurso_ModificarCurso");
        		//String docente = "2";
        		String cuatrimestre = request.getParameter("txtCuatrimestre");
        		String anio = request.getParameter("txtAnio");
        		String materia = request.getParameter("txtMateria");
        		
        		Curso curso = new Curso();
        		curso.setMateria(materiaDao.read(Integer.parseInt(materia)));
        		//curso.setDocente(docenteDao.read(Integer.parseInt(docente)));
        		curso.setCuatrimestre(Integer.parseInt(cuatrimestre));
        		curso.setAnio(Integer.parseInt(anio));
        		
        		Boolean result = false;
        		result = cursoDao.read(curso);
        		
        		if(result == true)
        		{
        			request.setAttribute("Result_ID", curso.getID());
        			request.setAttribute("Result_Cuatrimestre", curso.getCuatrimestre());
        			request.setAttribute("Result_Anio", curso.getAnio());
        			request.setAttribute("Result_Docente", curso.getDocente().getID());
        			request.setAttribute("Result_Materia", curso.getMateria().getId());
        		}
        		else
        		{
        			String error = "No se encontro curso en los registros, por favor revise los datos e intente nuevamente.";
        			request.setAttribute("Error", error);
        			System.out.println(error);
        		}
        		
        	}
        	catch(Exception e)
        	{
        		String error = "Whoops! Algo fallo al procesas la solicitud. Por favor revise los valores e intente nuevamente";
    			request.setAttribute("Error", error);
    			System.out.println(error);
        	}
        	
        		redirectJSP = "/ModificarCurso.jsp";    
        }

        else if(request.getParameter("btnBurscarCurso_EliminarCurso") != null)
        {
        	System.out.println("btnBuscarCurso");
        	//String docente = "2";
        	String cuatrimestre = request.getParameter("txtCuatrimestre");
        	String a�o = request.getParameter("txtAnio");
        	String materia = request.getParameter("txtMateria");
        	
        	Curso curso = new Curso();
        	curso.setMateria(materiaDao.read(Integer.parseInt(materia)));
        	//curso.setDocente(docenteDao.read(Integer.parseInt(docente)));
        	curso.setCuatrimestre(Integer.parseInt(cuatrimestre));
        	curso.setAnio(Integer.parseInt(a�o));
        	
        	Boolean result = false;
        	try {
        		result = cursoDao.read(curso);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
        	if(result == true)
        	{
        		request.setAttribute("Result_ID", curso.getID());
        		//request.setAttribute("Result_Docente", curso.getDocente().getApellido());
        		request.setAttribute("Result_Materia", curso.getMateria().getNombre());
        		request.setAttribute("Result_Cuatrimestre", curso.getCuatrimestre());
        		request.setAttribute("Result_Anio", curso.getAnio());
        		
        		
        		if(request.getParameter("btnBurscarCurso_EliminarCurso") != null)
        		{
        			request.setAttribute("Result_Docente", curso.getDocente().getApellido() + ", " + curso.getDocente().getNombre());
        			request.setAttribute("Result_Materia", curso.getMateria().getNombre());
        		}        		
        	}
        	else
        	{
        		String error = "No se encontro curso en nuestros registros. Por favor, revise los valores y vuenva a intentar.";
        		System.out.println(error);
        		request.setAttribute("Error", error);
        	}
        	
        	redirectJSP = "/EliminarCurso.jsp";
        }
        
        else if(request.getParameter("btnBurscarCurso_Inscripcion") != null)
        {
        	try
        	{
        		System.out.println("btnBurscarCurso_Inscripcion");
        		//String docente = "2";
        		String cuatrimestre = request.getParameter("txtCuatrimestre");
        		String a�o = request.getParameter("txtAnio");
        		String materia = request.getParameter("txtMateria");
        		
        		Curso curso = new Curso();
        		curso.setMateria(materiaDao.read(Integer.parseInt(materia)));
        		//curso.setDocente(docenteDao.read(Integer.parseInt(docente)));
        		curso.setCuatrimestre(Integer.parseInt(cuatrimestre));
        		curso.setAnio(Integer.parseInt(a�o));
        		
        		Boolean result = false;
        		if(usuario.getRol() == 1)
        		{
        			result = cursoDao.read(curso);        			
        		}
        		else
        		{
        			result = cursoDao.read(curso, usuario.getID_docente());
        		}
        		
        		if(result == true)
        		{
        			request.setAttribute("Curso", curso);
        		}
        		else
        		{
        			String error = "No se encontro el curso en la base de datos, por favor corrija los valores.";
        			System.out.println(error);
        			request.setAttribute("Error", error);
        		}
        	}
        	catch(Exception e)
        	{
        		String error = "Whoops! Algo fallo al procesar la solicitud. Por favor revise los datos e intente nuevamente.";
    			System.out.println(error);
    			request.setAttribute("Error", error);
        	}
        	
        		redirectJSP = "/InscripcionCurso.jsp";    
        }
        else if(request.getParameter("btnInscribirACurso") != null)
		{
        	try
        	{
        		System.out.println("Accion boton 'Inscribir curso'");
        		ArrayList <Alumno> alumnos = (ArrayList<Alumno>)request.getSession().getAttribute("Alumnos");
        		int idCurso = Integer.parseInt(request.getParameter("txtID").toString());
        		int idx = 0;
        		for(Alumno a : alumnos)
        		{
        			
        			String cbxName = "cbx" + idx;
        			if(request.getParameter(cbxName) != null)
        			{
        				if(!cursoAlumno.insert(idCurso,a.getID()))
        				{
        					System.out.println("Fall� la inscripcion de alumno: " + a.getApellido() + a.getNombre() + ". Legajo " + a.getLegajo() );
        				}        			
        			}
        			idx++;
        		}
        		String success  = "�Se han inscripto los alumnos con exito!";
				System.out.println(success);
				request.setAttribute("Exito", success);
				redirectJSP = "/Exito.jsp";
        	}
        	catch(Exception e)
        	{
        		String error = "Whoops! Algo fallo al procesar la solicitud. Por favor revise los datos e intente nuevamente.";
        		System.out.println(error);
        		request.setAttribute("Error", error);
        		redirectJSP = "/InscripcionCurso.jsp";
        	}

		}
        else if(request.getParameter("btnEliminarCurso") != null)
        {
        	try
        	{
        		System.out.println("Accion boton 'Baja curso'");
        		String ID = request.getParameter("txtID");
        		
        		if(cursoDao.baja(ID))
        		{
        			String success  = "�Se ha eliminado el curso con exito!";
        			System.out.println(success);
        			request.setAttribute("Exito", success);
        			redirectJSP = "/Exito.jsp";
        		}
        		else
        		{
        			String error = "Whoops! No pudimos dar de baja el curso, por favor intente nuevamente.";
        			System.out.println(error);
        			request.setAttribute("Error", error);
        			redirectJSP = "/EliminarCurso.jsp";
        		}        		        		
        	}
        	catch(Exception e)
        	{
        		String error = "Whoops! Algo fallo al procesar la solicitud. Por favor revise los datos e intente nuevamente.";
        		System.out.println(error);
        		request.setAttribute("Error", error);
        		redirectJSP = "/EliminarCurso.jsp";
        	}
        	
        }
        

    	RequestDispatcher rq = request.getRequestDispatcher(redirectJSP);
    	rq.include(request, response);
        
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
