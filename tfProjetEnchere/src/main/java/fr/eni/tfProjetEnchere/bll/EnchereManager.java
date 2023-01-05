package fr.eni.tfProjetEnchere.bll;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import fr.eni.tfProjetEnchere.bo.ArticleVendu;
import fr.eni.tfProjetEnchere.bo.Enchere;
import fr.eni.tfProjetEnchere.bo.Utilisateur;
import fr.eni.tfProjetEnchere.dal.DALException;
import fr.eni.tfProjetEnchere.dal.DAO.EnchereDAO;
import fr.eni.tfProjetEnchere.dal.JDBC.EnchereDAOJDBCImpl;

public class EnchereManager {
	 public static EnchereDAO dao = new EnchereDAOJDBCImpl();

	    public void createEnchere(Enchere enchere) throws DALException, SQLException {
	        dao.newEnchere(enchere);
	    }

	    public List<Enchere> selectAllEncheres() throws DALException, SQLException {
	        return dao.selectAllEncheres();
	    }

	    public List<Enchere> selectMesEncheres(int idUtilisateur) throws DALException, SQLException {
	        return dao.selectMesEncheres(idUtilisateur);
	    }

	    public Enchere bestEnchere(int noArticle) throws DALException, SQLException {
	        return dao.bestEnchere(noArticle);
	    }

}
