package fr.eni.tfProjetEnchere.dal.JDBC;

import java.sql.SQLException;
import java.util.List;

import fr.eni.tfProjetEnchere.bo.ArticleVendu;
import fr.eni.tfProjetEnchere.dal.DALException;
import fr.eni.tfProjetEnchere.dal.DAO.ArticleDAO;

public class test {

	public static void main(String[] args) {
		
		ArticleDAO articleVendu = new ArticleDAOJDBCImpl();
		try {
			List<ArticleVendu> listArticle = articleVendu.selectAllArticle();
			for(int i = 0; i< listArticle.size();i++)
				System.out.println(listArticle.get(i));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
