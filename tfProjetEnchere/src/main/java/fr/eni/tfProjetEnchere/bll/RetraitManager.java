package fr.eni.tfProjetEnchere.bll;

import java.sql.SQLException;

import fr.eni.tfProjetEnchere.bo.Retrait;
import fr.eni.tfProjetEnchere.dal.DALException;
import fr.eni.tfProjetEnchere.dal.DAO.RetraitDAO;
import fr.eni.tfProjetEnchere.dal.JDBC.RetraitDAOJDBCImpl;

public class RetraitManager {
	private static RetraitDAO dao = new RetraitDAOJDBCImpl();

	public void createRetrait(Retrait retrait) throws DALException, BLLException, SQLException {
		BLLException bllException = validateRetrait(retrait);
		if (bllException.hasErrors()) {
			throw bllException;
		} else {
			dao.newRetrait(retrait);
		}
	}

	public Retrait selectRetraitById(int noArticle) throws DALException, SQLException {
		return dao.selectRetraitById(noArticle);
	}

	public void updateRetrait(Retrait retrait) throws DALException, BLLException, SQLException {
		BLLException bllException = validateRetrait(retrait);
		if (bllException.hasErrors()) {
			throw bllException;
		} else {
			dao.modifyRetrait(retrait);
		}
	}

	public void deleteRetrait(int retrait) throws DALException, SQLException {
		dao.deleteRetrait(retrait);
	}

	private BLLException validateRetrait(Retrait retraitToValidate) {
		BLLException bllException = new BLLException();
		if (retraitToValidate.getRue().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_RUE_RETRAIT);
		}
		if (retraitToValidate.getCodePostal().length() > 15) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_CODE_POSTAL_RETRAIT);
		}
		if (retraitToValidate.getVille().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_VILLE_RETRAIT);
		}
		return bllException;
	}
}
