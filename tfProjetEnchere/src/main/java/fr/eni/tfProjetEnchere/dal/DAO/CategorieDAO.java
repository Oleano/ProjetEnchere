package fr.eni.tfProjetEnchere.dal.DAO;

import java.sql.SQLException;
import java.util.List;

import fr.eni.tfProjetEnchere.bo.Categorie;
import fr.eni.tfProjetEnchere.dal.DALException;

public interface CategorieDAO {
	public void newCategorie(Categorie categorie) throws DALException, SQLException;

	public void modifyCategorie(Categorie noCategorie) throws DALException, SQLException;

	public void deleteCategorie(int noCategorie) throws DALException, SQLException;

	public List<Categorie> selectAllCategorie() throws DALException, SQLException;

	public Categorie selectCategorieByNo(int noCategorie) throws DALException, SQLException;

	public List<Categorie> selectCategorieByName(String libelle) throws DALException, SQLException;

}
