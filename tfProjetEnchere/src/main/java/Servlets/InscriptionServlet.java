package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.tfProjetEnchere.bll.BLLException;
import fr.eni.tfProjetEnchere.bll.UtilisateurManager;
import fr.eni.tfProjetEnchere.bo.Utilisateur;
import fr.eni.tfProjetEnchere.dal.DALException;

@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean passwordAllowed;
	private final UtilisateurManager utilisateurManager = new UtilisateurManager();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupération Paramètres Formulaire Inscription
		Utilisateur newUser = new Utilisateur(request.getParameter("registerPseudo"),
				request.getParameter("registerPrenom"), request.getParameter("registerNom"),
				request.getParameter("registerTelephone"), request.getParameter("registerEmail"),
				request.getParameter("registerPassword"), request.getParameter("registerCodePostal"),
				request.getParameter("registerVille"), request.getParameter("registerRue"));

		// Si Password = Password 2 alors on AJOUTE un Utilisateur
		if (request.getParameter("registerPassword").equals(request.getParameter("registerPassword2"))) {
			try {
				utilisateurManager.newUtilisateur(newUser);
			} catch (BLLException e) {
				e.printStackTrace();
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			passwordAllowed = false;
			this.getServletContext().setAttribute("errorPassword", passwordAllowed);
			response.sendRedirect(request.getContextPath() + "/inscription");
		} else {
			passwordAllowed = true;
			this.getServletContext().setAttribute("errorPassword", passwordAllowed);
			response.sendRedirect(request.getContextPath() + "/inscription");
		}
	}

}
