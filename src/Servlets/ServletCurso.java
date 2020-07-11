package Servlets;

import java.io.IOException;
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
		DocenteDaoImpl docenteDao = new DocenteDaoImpl();
		MateriaDaoImpl materiaDao = new MateriaDaoImpl();
		CursoDaoImpl cursoDao = new CursoDaoImpl();
		
		String redirectJSP = "";
		if(request.getParameter("Param") != null)
		{
			String param = request.getParameter("Param");
			if(param.equals("altaCurso"))
			{				
				//Cargo las Materias
				materiaDao = new MateriaDaoImpl();
				ArrayList<Materia> ListMateria = materiaDao.readAll();
				System.out.println("Se trajeron " + ListMateria.size() + " materias de la BBDD");
				request.setAttribute("Materias", ListMateria);
				//Cargo los docentes
				docenteDao = new DocenteDaoImpl();
				ArrayList<Docente> ListDocente = docenteDao.readAll();
				System.out.println("Se trajeron " + ListDocente.size() + " docentes de la BBDD");
				request.setAttribute("Docentes", ListDocente);
				redirectJSP = "/AltaCurso.jsp";
			}
			
			if(param.equals("bajaCurso"))
	        {
	        	System.out.println(request.getParameter("Param"));
	        	redirectJSP = "/EliminarCurso.jsp";
	        }
		}
        
        if(request.getParameter("btnAltaCurso") != null)
		{
        	System.out.println("Accion boton 'Agregar curso'");
			Curso curso = new Curso();
			curso.setDocente(docenteDao.readID(Integer.parseInt(request.getParameter("ddlProfesor"))));
			//linea
			curso.setMateria(materiaDao.read(Integer.parseInt(request.getParameter("ddlMateria"))));
			curso.setCuatrimestre(Integer.parseInt(request.getParameter("ddlCuatrimestre")));
			curso.setAño(Integer.parseInt(request.getParameter("txtAnio")));

			if(cursoDao.insert(curso))
			{
				System.out.println("Curso ingresado con exito!");
			}
			else
			{
				System.out.println("Falló el ingreso.");
			}
			redirectJSP = "/AltaCurso.jsp";
		}
        if(request.getParameter("btnBurscarCurso") != null)
        {
        	System.out.println("btnBuscarCurso");
        	String docente = "2";
        	String cuatrimestre = request.getParameter("txtCuatrimestre");
        	String año = request.getParameter("txtAnio");
        	String materia = request.getParameter("txtMateria");
        	
        	Curso curso = new Curso();
        	curso.setMateria(materiaDao.read(Integer.parseInt(materia)));
        	curso.setDocente(docenteDao.readID(Integer.parseInt(docente)));
        	curso.setCuatrimestre(Integer.parseInt(cuatrimestre));
        	curso.setAño(Integer.parseInt(año));
        	
        	curso = cursoDao.read(curso);
    		//linea
    		
    		request.setAttribute("Result_ID", curso.getID());
    		request.setAttribute("Result_Docente", curso.getDocente().getApellido());
    		request.setAttribute("Result_Materia", curso.getMateria().getNombre());
    		request.setAttribute("Result_Cuatrimestre", curso.getCuatrimestre());
    		request.setAttribute("Result_Año", curso.getAño());
    		
        	redirectJSP = "/EliminarCurso.jsp";
        }
        
        if(request.getParameter("btnEliminarCurso") != null)
        {
        	System.out.println("Accion boton 'Baja curso'");
    		String ID = request.getParameter("txtID");
    		
    		if(cursoDao.baja(ID))
    		{
    			System.out.println("Curso dado de baja con exito!");
    		}
    		else
    		{
    			System.out.println("Whoops! No pudimos dar de baja el curso, por favor intente nuevamente.");
    		}        		
        	
        	redirectJSP = "/EliminarCurso.jsp";
        }
        

    	RequestDispatcher rq = request.getRequestDispatcher(redirectJSP);
    	rq.include(request, response);
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
