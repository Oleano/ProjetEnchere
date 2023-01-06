package fr.eni.tfProjetEnchere.ihm;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.tfProjetEnchere.bll.ArticleVenduManager;
import fr.eni.tfProjetEnchere.bll.BLLException;
import fr.eni.tfProjetEnchere.bll.EnchereManager;
import fr.eni.tfProjetEnchere.bll.RetraitManager;
import fr.eni.tfProjetEnchere.bll.UtilisateurManager;
import fr.eni.tfProjetEnchere.bo.ArticleVendu;
import fr.eni.tfProjetEnchere.bo.Enchere;
import fr.eni.tfProjetEnchere.bo.Retrait;
import fr.eni.tfProjetEnchere.bo.Utilisateur;
import fr.eni.tfProjetEnchere.dal.DALException;

@WebServlet("/articleDetail")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticleVenduManager articleVenduManager = new ArticleVenduManager();
	private RetraitManager retraitManager = new RetraitManager();
	private UtilisateurManager utilisateursManager = new UtilisateurManager();
	private ArticleVendu articleVendu;
	private Enchere enchere;
	private EnchereManager enchereManager = new EnchereManager() ;

	HttpSession session;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("getDetail".equals(action)) {

			try {
				getDetail(request, response);
			} catch (BLLException | DALException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;

		}
		
		request.getRequestDispatcher("/WEB-INF/articleDetail.jsp").forward(request, response);

	}

	@SuppressWarnings("static-access")
	protected void getDetail(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, BLLException, DALException, SQLException {
		int id = Integer.parseInt(req.getParameter("noArticle"));
		Retrait retrait = null;
		Utilisateur user;

		try {
			articleVendu = articleVenduManager.getArticleById(id);
			enchere = enchereManager.bestEnchere(id);
			retrait = retraitManager.selectRetraitById(id);
			user = utilisateursManager.selectUtilisateurById(articleVendu.getVendeur().getId());
			session = req.getSession();
			session.setAttribute("retrait", retrait);
			session.setAttribute("article", articleVendu);
			session.setAttribute("prixVente", enchere);
			session.setAttribute("user", user);
			(req.getRequestDispatcher("/WEB-INF/articleDetail.jsp")).forward(req, resp);
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer credit = Integer.parseInt(req.getParameter("creditEnchere"));
		try {
			enchere = new Enchere(articleVendu.getFinEnchere(), credit, articleVendu.getNoArticle(),
					articleVendu.getVendeur().getId());
			articleVendu = articleVenduManager.getArticleById(Integer.parseInt(req.getParameter("noArticle")));
		//	articleVendu = new ArticleVendu(Integer.parseInt(req.getParameter("noArticle")),
			//		articleVendu.getNomArticle(), articleVendu.getDescription(), articleVendu.getDebutEnchere(),
				//	articleVendu.getFinEnchere(), articleVendu.getMisAPrix(), enchere.getMontantEnchere(),
					//articleVendu.getVendeur(), articleVendu.getNoArticle());
			enchereManager.createEnchere(enchere);
		// articleVenduManager.updateArticle(articleVendu);
			session.setAttribute("prixVente", articleVendu.getPrixVente());
			resp.sendRedirect(req.getContextPath() + "/articleDetail");
		} catch (BLLException | NumberFormatException | DALException | SQLException e) {
			e.printStackTrace();
		}

	}
}
