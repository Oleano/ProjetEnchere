package fr.eni.tfProjetEnchere.dal.DAO;

import java.sql.SQLException;
import java.util.List;

import fr.eni.tfProjetEnchere.bo.Enchere;
import fr.eni.tfProjetEnchere.dal.DALException;

public interface EnchereDAO {
	public List<Enchere> selectAllEncheres() throws DALException, SQLException;

	public List<Enchere> selectMesEncheres(int idUtilisateur) throws DALException, SQLException;

	public void newEnchere(int noArticle, int montantEnchere, int idUtilisateur) throws DALException, SQLException;

	public Enchere bestEnchere(int noArticle) throws DALException, SQLException;
}
