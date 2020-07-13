package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoImpl.UsuarioDaoImpl;
import DaoImpl.DocenteDaoImpl;
import Entidades.Usuario;
import Entidades.Docente;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletLogin() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletLogin doget");
		if( request.getSession().getAttribute("Login") !=null)
		{
			request.getSession().removeAttribute("Login");
		}
		
		if( request.getSession().getAttribute("User") !=null)
		{
			request.getSession().removeAttribute("User");
		}
		
		RequestDispatcher rq = request.getRequestDispatcher("/Login.jsp");
		rq.include(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletLogin dopost");
		Usuario login = null;
		Docente user = new Docente();
		UsuarioDaoImpl UDAO = new UsuarioDaoImpl();
		DocenteDaoImpl DDAO = new DocenteDaoImpl();
		if( request.getParameter("btnLogin") != null)
		{
			request.setAttribute("Error", null);
			System.out.println("btnLogin != null");
			System.out.println("txtName:"+ request.getParameter("txtName"));
			System.out.println("txtPass:"+ request.getParameter("txtPass"));
			String name = request.getParameter("txtName");
			String pass = request.getParameter("txtPass");
			
			login = UDAO.buscarUsuario(name, pass);
			if(login !=null)
			{
				//System.out.println("name:"+ login.getName());
				//System.out.println("pass:"+ login.getPass());
				//System.out.println("AlumnoDni:"+ alumno.getDni());
				//System.out.println("txtDni:"+ request.getParameter("txtDni"))
				//if(login.getName() == request.getParameter("txtName") && login.getPass() == request.getParameter("txtPass"))
					request.getSession().setAttribute("Login",login);
					if(login.getRol() == 2)
					{
						user = DDAO.read(login.getDni());
						request.getSession().setAttribute("User", user);										
					}
					RequestDispatcher rq = request.getRequestDispatcher("/Main.jsp");
					rq.include(request, response);
				
				
			}
			else
			{
				String log = "Usuario o contraseña invalidos";
				request.setAttribute("Error",log);
				RequestDispatcher rq = request.getRequestDispatcher("/Login.jsp");
				rq.include(request, response);
			}
		}
		else
		{
			RequestDispatcher rq = request.getRequestDispatcher("/Login.jsp");
			rq.include(request, response);
		}
	}
}
