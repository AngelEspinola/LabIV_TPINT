package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Docente;
import DaoImpl.DocenteDaoImpl;
import Entidades.AlumnoNotas;
import DaoImpl.AlumnoNotasDaoImpl;

@WebServlet("/ServletCalificar")
public class ServletCalificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ServletCalificar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Docente docente = null;
		DocenteDaoImpl DDAO = new DocenteDaoImpl();
		AlumnoNotasDaoImpl ADAO = new AlumnoNotasDaoImpl();
		ArrayList<AlumnoNotas> ListAlumnoNotas = null;
		int idCurso;
		if(request.getParameter("IDDocente") != null)
		{
			int ID = Integer.parseInt(request.getParameter("IDDocente"));
			System.out.println("ID: "+ID);
			docente = DDAO.readID(ID);
			if(docente != null && docente.getID() != 0)
			{
				System.out.println("Alumnos del curso de "+ docente.getNombre());
				idCurso = 1;
				//Cargo los alumnos del curso idCurso
				ListAlumnoNotas = ADAO.readAll(idCurso);
				System.out.println("Cantidad de alumnos: "+ListAlumnoNotas.size());
				getServletContext().setAttribute("ListAlumnoNotas", ListAlumnoNotas);
				getServletContext().setAttribute("Docente", docente);
				RequestDispatcher rq=request.getRequestDispatcher("/DocenteCurso.jsp");
				rq.include(request, response);
			}
		}
		else if(request.getParameter("btnCalificarCurso_Listado") != null)
		{
			try
			{
				request.getSession().setAttribute("idCurso", "");
				idCurso = Integer.parseInt(request.getParameter("IDCurso"));
				request.getSession().setAttribute("idCurso", idCurso);
				ListAlumnoNotas = ADAO.readAll(idCurso);
				System.out.println("Cantidad de alumnos: "+ListAlumnoNotas.size());
				
				request.setAttribute("ListAlumnoNotas", ListAlumnoNotas);
				RequestDispatcher rq=request.getRequestDispatcher("/DocenteCurso.jsp");
				rq.include(request, response);
			}
			catch(Exception e)
			{
				String error = "Whoops! Algo fallo al procesar la peticion. Por favor intente nuevamente mas tarde.";
				System.out.println(error);
				request.setAttribute("Error", error);
				RequestDispatcher rq=request.getRequestDispatcher("/ServletReporte");
				rq.include(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet doPost.");
		String redirectJSP = "";
		AlumnoNotasDaoImpl ADAO = new AlumnoNotasDaoImpl();
		ArrayList<AlumnoNotas> ListAlumnoNotas = null;
		AlumnoNotas aux = new AlumnoNotas();
		String estado = "";
		int idCurso;
		int parcialUno;
		int parcialDos;
		int recuUno;
		int recuDos;
		if(request.getParameter("btnCalificarCurso") != null)
		{
			idCurso = Integer.parseInt(request.getSession().getAttribute("idCurso").toString());
			if(idCurso != 0)
			{
				//Cargo los alumnos //Cargo los alumnos del curso idCurso
				ListAlumnoNotas = ADAO.readAll(idCurso);
				System.out.println("Cantidad de alumnos: "+ListAlumnoNotas.size());
			
				for(AlumnoNotas a : ListAlumnoNotas)
				{
					System.out.println(a.getNombre());
					parcialUno = Integer.parseInt(request.getParameter("txt1Nota"+Integer.toString(a.getIdAlumno())));
					System.out.println("Nota 1� parcial: "+ parcialUno);
				
				
					parcialDos = Integer.parseInt(request.getParameter("txt2Nota"+Integer.toString(a.getIdAlumno())));
					System.out.println("Nota 2� parcial:"+ parcialDos);
				
				
					recuUno = Integer.parseInt(request.getParameter("txt1Recu"+Integer.toString(a.getIdAlumno())));
					System.out.println("Nota 1� Recuperatorio: "+ recuUno);
				
				
					recuDos = Integer.parseInt(request.getParameter("txt2Recu"+Integer.toString(a.getIdAlumno())));
					System.out.println("Nota 2� Recuperatorio: "+ recuDos);
					
					estado = a.getEstado(); 
					System.out.println("Estado: "+ estado);
					if( request.getParameter("dllEstado"+Integer.toString(a.getIdAlumno()))!=null)
					{
						System.out.println("Estado: "+ request.getParameter("dllEstado"+Integer.toString(a.getIdAlumno())));
						estado = Integer.parseInt(request.getParameter("dllEstado"+Integer.toString(a.getIdAlumno()))) == 1?"Libre":"Regular";
					}
				
					System.out.println("Estado: "+ estado);
					
					//cargo el objeto
					aux.setIdAlumno(a.getIdAlumno());
					aux.setIdCurso(a.getIdCurso());
					aux.setNota1(parcialUno);
					aux.setNota2(parcialDos);
					aux.setRecuperatorio1(recuUno);
					aux.setRecuperatorio2(recuDos);
					aux.setEstado(estado);
					if( ADAO.modify(aux))
					{
						String success  = "�Se ha calificado al curso con exito!";
						System.out.println(success);
						request.setAttribute("Exito", success);
						redirectJSP = "/Exito.jsp";
					}
					else
					{
						String error = "Whoops! No pudimos calificar al curso correctamente. Por favor intente nuevamente";
	        			System.out.println(error);
	        			request.setAttribute("Error", error);
	        			redirectJSP = "/DocenteCurso.jsp";
					}
				}
		
			}
		}
		
		RequestDispatcher rq = request.getRequestDispatcher(redirectJSP);
    	rq.include(request, response);
	}
}
