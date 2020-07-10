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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet doPost.");
		Docente docente = null;
		DocenteDaoImpl DDAO = new DocenteDaoImpl();
		AlumnoNotasDaoImpl ADAO = new AlumnoNotasDaoImpl();
		ArrayList<AlumnoNotas> ListAlumnoNotas = null;
		AlumnoNotas aux = new AlumnoNotas();
		String estado = "";
		int idCurso;
		int parcialUno;
		int parcialDos;
		int recuUno;
		int recuDos;
		if(request.getParameter("IDDocente") != null)
		{
			int ID = Integer.parseInt(request.getParameter("IDDocente"));
			System.out.println("ID: "+ID);
			docente = DDAO.readID(ID);
			if(docente != null && docente.getID() != 0)
			{
				idCurso = 1;
				System.out.println("Alumnos del curso de "+ docente.getNombre());
				//Cargo los alumnos //Cargo los alumnos del curso idCurso
				ListAlumnoNotas = ADAO.readAll(idCurso);
				System.out.println("Cantidad de alumnos: "+ListAlumnoNotas.size());
				getServletContext().setAttribute("ListAlumnoNotas", ListAlumnoNotas);
				getServletContext().setAttribute("Docente", docente);
			
				for(AlumnoNotas a : ListAlumnoNotas)
				{
					System.out.println(a.getNombre());
					parcialUno = Integer.parseInt(request.getParameter("txt1Nota"+Integer.toString(a.getIdAlumno())));
					System.out.println("Nota 1° parcial: "+ parcialUno);
				
				
					parcialDos = Integer.parseInt(request.getParameter("txt2Nota"+Integer.toString(a.getIdAlumno())));
					System.out.println("Nota 2° parcial:"+ parcialDos);
				
				
					recuUno = Integer.parseInt(request.getParameter("txt1Recu"+Integer.toString(a.getIdAlumno())));
					System.out.println("Nota 1° Recuperatorio: "+ recuUno);
				
				
					recuDos = Integer.parseInt(request.getParameter("txt2Recu"+Integer.toString(a.getIdAlumno())));
					System.out.println("Nota 2° Recuperatorio: "+ recuDos);
					
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
						System.out.println("Mofificación exitosa");
					}
					else if( ADAO.modify(aux))
					{
						System.out.println("Fallo mofificación por segunda vez");
					}
				}
		
				//Actualizo la lista con las notas cargadas
				ListAlumnoNotas = ADAO.readAll(idCurso);
				getServletContext().setAttribute("ListAlumnoNotas", ListAlumnoNotas);
			}
		}
		RequestDispatcher rq=request.getRequestDispatcher("/DocenteCurso.jsp");
		rq.include(request, response);
	}
}
