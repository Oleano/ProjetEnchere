package Servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.tfProjetEnchere.bll.ArticleVenduManager;
import fr.eni.tfProjetEnchere.bll.BLLException;
import fr.eni.tfProjetEnchere.bo.ArticleVendu;
import fr.eni.tfProjetEnchere.dal.DALException;

/**
 * Servlet implementation class VenteArticleServlet
 */
@WebServlet("/VenteArticleServlet")
public class VenteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Récupération Paramètres Formulaire Articles
		ArticleVendu newArticle = new ArticleVendu(req.getParameter("registerArticle"),
				req.getParameter("registerDescription"), LocalDate.parse(req.getParameter("registerDateDebut")),
				LocalDate.parse(req.getParameter("registerDateFin")),
				Integer.parseInt(req.getParameter("registerMisaAPrix")),
				Integer.parseInt(req.getParameter("registerNoUtilisateur")),
				Integer.parseInt(req.getParameter("registerCategorie"))

		);

		session = req.getSession();
		session.setAttribute("article", newArticle);

		try {
			ArticleVenduManager.createArticleVendu(newArticle);
		} catch (BLLException | DALException e) {
			throw new RuntimeException(e);
		}

		resp.sendRedirect(req.getContextPath() + "/eniencheres");

	}

}
