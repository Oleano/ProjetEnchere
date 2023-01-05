package fr.eni.tfProjetEnchere.ihm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class testServlet
 */
@WebServlet("/testServlet")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
				
				try {
					Context context = new InitialContext();
					DataSource dataSource = (DataSource) context.lookup("java:comp/env/pool_cnx");
					Connection cnx = dataSource.getConnection();
					out.print("la connexion est" + (cnx.isClosed() ? "fermÃ©e" : "ouverte" + "."));
					
					cnx.close();
					
				} catch (NamingException | SQLException e) {
					e.printStackTrace();
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					out.println("Une erreur est survenue");
				}
				
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}