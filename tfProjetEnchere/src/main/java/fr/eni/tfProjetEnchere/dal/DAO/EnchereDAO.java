package fr.eni.tfProjetEnchere.dal.DAO;

import java.sql.SQLException;
import java.util.List;

import fr.eni.tfProjetEnchere.bo.Enchere;
import fr.eni.tfProjetEnchere.dal.DALException;

public interface EnchereDAO {
	public List<Enchere> selectAllEnchere() throws DALException, SQLException;
	public List<Enchere> selectEnchereByNameDisconected(String filtreNom) throws DALException, SQLException;
	public List<Enchere> selectEnchereByCategorieDisconected(int filtreIdCategorie) throws DALException, SQLException;
	public List<Enchere> selectEnchereByNameByCategorieDisconect(String filtreNom, int filtreIdCategorie) throws DALException, SQLException;
	public List<Enchere> selectEnchereByNameConected(String filtreNom, int idUtilisateur) throws DALException, SQLException;
	public List<Enchere> selectEnchereByCategorieConected(int filtreIdCategorie, int idUtilisateur) throws DALException, SQLException;
	public List<Enchere> selectEnchereByNameByCategorieConected(String filtreNom, int filtreIdCategorie, int idUtilisateur ) throws DALException, SQLException;
	public void newEnchere(int noArticle, int montantEnchere, int idUtilisateur) throws DALException, SQLException;
	
	

}
