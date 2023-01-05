package fr.eni.tfProjetEnchere.ihm;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.tfProjetEnchere.bll.UtilisateurManager;
import fr.eni.tfProjetEnchere.bo.Utilisateur;
import fr.eni.tfProjetEnchere.dal.DALException;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/ConnexionServlet")
public class ConnexionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final UtilisateurManager utilisateurManager = new UtilisateurManager();

	HttpSession session;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		session = request.getSession();

		// ?action=deconnexion
		String action = request.getParameter("action");
		if ("deconnexion".equals(action)) {
			doDeconnexion(request, response);
			return;
		}

		request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Utilisateur user;

		try {
			user = utilisateurManager.selectUtilisateurByLogin(req.getParameter("pseudo"),
					req.getParameter("password"));
			if (user != null) {

				session.setAttribute("user", user);

				this.getServletContext().setAttribute("isAllowed", true);
				this.getServletContext().setAttribute("isNotAllowed", false);
				this.getServletContext().setAttribute("errorPassword", false);
				resp.sendRedirect(req.getContextPath() + "/tfprojetenchere");
			} else {
				req.setAttribute("isAllowed", false);
				this.getServletContext().setAttribute("isNotAllowed", true);
				resp.sendRedirect(req.getContextPath() + "/connexion");
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doDeconnexion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		session.invalidate();

		this.getServletContext().setAttribute("isAllowed", false);

		resp.sendRedirect(req.getContextPath() + "/connexion");

	}

}
