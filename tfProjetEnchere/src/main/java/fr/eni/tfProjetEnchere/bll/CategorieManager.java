package fr.eni.tfProjetEnchere.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.tfProjetEnchere.bo.Categorie;
import fr.eni.tfProjetEnchere.dal.DALException;
import fr.eni.tfProjetEnchere.dal.DAO.CategorieDAO;
import fr.eni.tfProjetEnchere.dal.JDBC.CategorieDAOJDBCImpl;

public class CategorieManager {
	private static CategorieDAO dao = new CategorieDAOJDBCImpl();

	public void createCategorie(Categorie categorie) throws DALException, BLLException {
		BLLException bllException = new BLLException();
		if (categorie.getLibelle().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_LIBELLE_CATEGORIE);
		}

		if (bllException.hasErrors()) {
			throw bllException;
		} else {
			try {
				dao.newCategorie(categorie);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Categorie getCategorieByNo(int id) throws DALException, SQLException {

		return dao.selectCategorieByNo(id);

	}

	public List<Categorie> getAllCategories() throws DALException, SQLException {
		return dao.selectAllCategorie();
	}
}
