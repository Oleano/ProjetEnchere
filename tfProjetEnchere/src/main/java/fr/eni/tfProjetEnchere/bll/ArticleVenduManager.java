package fr.eni.tfProjetEnchere.bll;

import fr.eni.tfProjetEnchere.bo.ArticleVendu;
import fr.eni.tfProjetEnchere.bo.Categorie;
import fr.eni.tfProjetEnchere.bo.Utilisateur;
import fr.eni.tfProjetEnchere.dal.DALException;
import fr.eni.tfProjetEnchere.dal.DAO.ArticleDAO;
import fr.eni.tfProjetEnchere.dal.JDBC.ArticleDAOJDBCImpl;

import java.util.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ArticleVenduManager {
	private static ArticleDAOJDBCImpl dao;

	public static void createArticleVendu(ArticleVendu articleVendu) throws BLLException, DALException {
		BLLException bllException = validateArticleVendu(articleVendu);
		if (bllException.hasErrors()) {
			throw bllException;
		} else {
			try {
				dao.newArticle(articleVendu);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArticleVendu getArticleById(int id) throws DALException, BLLException {
		ArticleVendu articleVendu;
		articleVendu = null;
		try {
			articleVendu = dao.selectArticleById(id);
			if (articleVendu == null) {
				BLLException bllException = new BLLException();
				bllException.addError(ErrorCodesBLL.ERROR_NO_RESULTS);
				throw bllException;

			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return articleVendu;
}

	public void updateArticle(ArticleVendu articleToUpdate) throws BLLException, DALException {
		BLLException bllException = validateArticleVendu(articleToUpdate);
		if (bllException.hasErrors()) {
			throw bllException;
		} else {
			try {
				dao.modifyArticle(articleToUpdate);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void deleteArticle(int articleVendu) throws DALException {
		try {
			dao.deleteArticle(articleVendu);
		} catch (DALException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public List<ArticleVendu> selectAllArticles() throws DALException, BLLException, SQLException {
		List<ArticleVendu> articlesVendus = dao.selectAllArticle();
		
		
		if (articlesVendus.isEmpty()) {
			BLLException bllException = new BLLException();
			bllException.addError(ErrorCodesBLL.ERROR_NO_RESULTS);
			throw bllException;
		} else {
			return articlesVendus;
		}
	}

	public List<ArticleVendu> getArticlesFromCategory(int categorie) throws DALException, BLLException, SQLException {
		List<ArticleVendu> articlesVendus = dao.selectAllArticle();
		
		try {
			articlesVendus = dao.selectArticleByCategorie(categorie);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (articlesVendus.isEmpty()) {
			BLLException bllException = new BLLException();
			bllException.addError(ErrorCodesBLL.ERROR_NO_RESULTS);
			throw bllException;
		} else {
			return articlesVendus;
		}
	}

	public List<ArticleVendu> getArticlesFilterByNomArticle(String filter) throws DALException, BLLException {
		List<ArticleVendu> articlesVendus;
		articlesVendus = null;
		try {
			articlesVendus = dao.selectArticleByName(filter);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (articlesVendus.isEmpty()) {
			BLLException bllException = new BLLException();
			bllException.addError(ErrorCodesBLL.ERROR_NO_RESULTS);
			throw bllException;
		} else {
			return articlesVendus;
		}
	}

	private static BLLException validateArticleVendu(ArticleVendu articleVendu) {
		BLLException bllException = new BLLException();
		if (articleVendu.getNomArticle().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_NOM_ARTICLE);
		}
		if (articleVendu.getDescription().length() > 300) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_DESCRIPTION_ARTICLE);
		}
		if (articleVendu.getDebutEnchere().isAfter(articleVendu.getFinEnchere())) {
			bllException.addError(ErrorCodesBLL.ERROR_START_DATE_AFTER_END_DATE);
		}
		return bllException;
	}
}
