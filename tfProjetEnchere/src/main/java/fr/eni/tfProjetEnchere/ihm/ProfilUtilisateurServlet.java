package fr.eni.tfProjetEnchere.ihm;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.tfProjetEnchere.bll.BLLException;
import fr.eni.tfProjetEnchere.bll.UtilisateurManager;
import fr.eni.tfProjetEnchere.bo.Utilisateur;
import fr.eni.tfProjetEnchere.dal.DALException;

@WebServlet("/ProfilUtilisateurServlet")
public class ProfilUtilisateurServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager;
	private HttpSession session;
	private Utilisateur user;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if ("supprimer".equals(action)) {
			doDelete(req, resp);
			return;
		}
		req.getRequestDispatcher("/WEB-INF/monProfil.jsp").forward(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("noUtilisateur"));
		try {
			utilisateurManager.deleteUtilisateur(id);
			session = req.getSession();
			session.invalidate();
			this.getServletContext().setAttribute("isAllowed", false);

		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect(req.getContextPath() + "/tfProjetEnchere");
	}

	@SuppressWarnings("static-access")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			user = utilisateurManager.selectUtilisateurById(Integer.parseInt(req.getParameter("noUtilisateur")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session = req.getSession();

		try {
			if (!user.getMotDePasse().equals(req.getParameter("profilPassword"))
					|| !req.getParameter("profilNewPassword").equals(req.getParameter("profilPasswordConf"))) {
				System.out.println(req.getParameter("profilPassword"));

				this.getServletContext().setAttribute("errorPasswordProfil", true);
				System.out.println(user.getMotDePasse());
				resp.sendRedirect(req.getContextPath() + "/ProfilUtilisateurServlet");
			} else {
				user = new Utilisateur(Integer.parseInt(req.getParameter("noUtilisateur")),
						req.getParameter("profilPseudo"), req.getParameter("profilNom"),
						req.getParameter("profilPrenom"), req.getParameter("profilEmail"),
						req.getParameter("profilTelephone"), req.getParameter("profilRue"),
						req.getParameter("profilCodePostal"), req.getParameter("profilVille"),
						req.getParameter("profilNewPassword"), Integer.parseInt(req.getParameter("profilCredit")));
				utilisateurManager.modifyUtilisateur(user);
				this.getServletContext().setAttribute("errorPasswordProfil", false);

				session.setAttribute("user", user);
				resp.sendRedirect(req.getContextPath() + "/tfProjetEnchere");
			}
		} catch (BLLException | DALException | SQLException e) {
			e.printStackTrace();
		}

	}

}
