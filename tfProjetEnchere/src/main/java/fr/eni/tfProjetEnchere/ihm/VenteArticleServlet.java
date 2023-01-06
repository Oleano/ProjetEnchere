package fr.eni.tfProjetEnchere.ihm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.tfProjetEnchere.bll.ArticleVenduManager;
import fr.eni.tfProjetEnchere.bll.BLLException;
import fr.eni.tfProjetEnchere.bll.CategorieManager;
import fr.eni.tfProjetEnchere.bll.UtilisateurManager;
import fr.eni.tfProjetEnchere.bo.ArticleVendu;
import fr.eni.tfProjetEnchere.bo.Categorie;
import fr.eni.tfProjetEnchere.bo.Utilisateur;
import fr.eni.tfProjetEnchere.dal.DALException;

/**
 * Servlet implementation class VenteArticleServlet
 */
@WebServlet("/VenteArticleServlet")
public class VenteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CategorieManager categorie = new CategorieManager();

	HttpSession session;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("liste", categorie.getAllCategories());
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp").forward(request, response);
	}

	@SuppressWarnings("static-access")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Categorie cat = null;
		Utilisateur user = null;
		try {
			 cat = new CategorieManager().getCategorieByNo(Integer.parseInt(req.getParameter("registerCategorie")));
			// user = new UtilisateurManager().selectUtilisateurById(Integer.parseInt(req.getParameter("user")));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Récupération Paramètres Formulaire Articles
		ArticleVendu newArticle = new ArticleVendu(req.getParameter("registerArticle"),
				req.getParameter("registerDescription"), LocalDate.parse(req.getParameter("registerDateDebut")),
				LocalDate.parse(req.getParameter("registerDateFin")),
				Integer.parseInt(req.getParameter("registerMisaAPrix")),
				(Utilisateur) (req.getSession().getAttribute("user")),
				cat

		);

		session = req.getSession();
		session.setAttribute("article", newArticle);

		try {
			ArticleVenduManager.createArticleVendu(newArticle);
		} catch (BLLException | DALException e) {
			throw new RuntimeException(e);
		}

		resp.sendRedirect(req.getContextPath() + "/EnchereServlet");

	}

}
