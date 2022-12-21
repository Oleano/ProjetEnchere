package fr.eni.tfProjetEnchere.dal.DAO;

import java.sql.SQLException;
import java.util.List;

import fr.eni.tfProjetEnchere.bo.ArticleVendu;
import fr.eni.tfProjetEnchere.dal.DALException;

public interface ArticleDAO {
	public List<ArticleVendu> selectAllArticle() throws DALException, SQLException;
	public ArticleVendu selectArticleById(int noArticle) throws DALException, SQLException;
	public List<ArticleVendu> selectArticleByNameDisconected(String filtreNom) throws DALException, SQLException;
	public List<ArticleVendu> selectArticleByCategorieDisconected(int filtreIdCategorie) throws DALException, SQLException;
	public List<ArticleVendu> selectArticleByNameConected(String filtreNom, int idUtilisateur) throws DALException, SQLException;
	public List<ArticleVendu> selectArticleByCategorieConected(int filtreIdCategorie, int idUtilisateur) throws DALException, SQLException;
	public void newArticle(ArticleVendu article) throws DALException, SQLException;
	public void modifyArticle(ArticleVendu noArticle) throws DALException, SQLException;
	public void deleteArticle(ArticleVendu noArticle) throws DALException, SQLException;

}
