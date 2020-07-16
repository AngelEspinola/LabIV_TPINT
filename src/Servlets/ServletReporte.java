package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Curso;
import Entidades.Materia;
import DaoImpl.CursoDaoImpl;
import DaoImpl.MateriaDaoImpl;

@WebServlet("/ServletReporte")
public class ServletReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ServletReporte() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet doGet Reporte");
		CursoDaoImpl CDAO = new CursoDaoImpl();
		ArrayList<Curso> listaCurso = CDAO.readAll();
		if(listaCurso != null)
		{
			getServletContext().setAttribute("Cursos",listaCurso);
		}
		RequestDispatcher rq=request.getRequestDispatcher("/Main.jsp");
       	rq.include(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet doPost Reporte");
		CursoDaoImpl CDAO = new CursoDaoImpl();
		MateriaDaoImpl MDAO = new MateriaDaoImpl();
		Materia materia = null;
		ArrayList<Curso> listaCurso = null;
		ArrayList<Materia> listaMateria = null;
		listaMateria = MDAO.readAll();
		getServletContext().setAttribute("Materias", listaMateria);
		getServletContext().setAttribute("txtDesde", "");
		getServletContext().setAttribute("txtHasta", "");
		if( request.getParameter("btnFiltro") != null)
		{
			System.out.println("btnFiltro");
			System.out.println("txtDesde: "+ request.getParameter("txtDesde"));
			System.out.println("txtHasta: "+ request.getParameter("txtHasta"));
			System.out.println("ddlMateria: "+ request.getParameter("ddlMateria"));
			String desde = "1";
			String hasta = "9999";
			if(request.getParameter("txtDesde")!=null && request.getParameter("txtDesde")!="")
			{
				desde = request.getParameter("txtDesde");
				getServletContext().setAttribute("Desde", request.getParameter("txtDesde"));
			}
			if(request.getParameter("txtHasta")!=null && request.getParameter("txtHasta")!="" )
			{
				hasta = request.getParameter("txtHasta");
				getServletContext().setAttribute("Hasta", request.getParameter("txtHasta"));
			}
			if(request.getParameter("ddlMateria")!=null && Integer.parseInt(request.getParameter("ddlMateria"))!=0 )
			{
				System.out.println("String ddlMateria!= 0");
				materia = MDAO.read(Integer.parseInt( request.getParameter("ddlMateria")));
				getServletContext().setAttribute("Materia", materia);
			}
			else if ( Integer.parseInt(request.getParameter("ddlMateria"))==0 || request.getParameter("ddlMateria")==null)
			{
				System.out.println("Int ddlMateria: 0");
				getServletContext().setAttribute("Materia", null);
			}
			if ( getServletContext().getAttribute("Materia") != null)
			{
				System.out.println("readFilter: ");
				materia = (Materia)getServletContext().getAttribute("Materia");
				listaCurso = CDAO.readFilter(Integer.parseInt(desde), Integer.parseInt(hasta), materia.getId());				
			}
			else
			{
				System.out.println("readFilterDate: ");
				listaCurso = CDAO.readFilterDate(Integer.parseInt(desde), Integer.parseInt(hasta));
			}
			System.out.println("Cantidad de cursos: "+listaCurso.size());
			if(listaCurso != null)
			{
				System.out.println("Cantidad de cursos: "+listaCurso.size());
				getServletContext().setAttribute("Cursos",listaCurso);
			}
			getServletContext().setAttribute("Materia", materia);
			RequestDispatcher rq=request.getRequestDispatcher("/Main.jsp");
	       	rq.include(request, response);
		}
		else
		{
			listaCurso = CDAO.readAll();
			if(listaCurso != null)
			{
				System.out.println("Cantidad de cursos: "+listaCurso.size());
				getServletContext().setAttribute("Cursos",listaCurso);
			}
			RequestDispatcher rq=request.getRequestDispatcher("/Main.jsp");
	       	rq.include(request, response);
		}
	}
}