package fr.eni.tfProjetEnchere.bll;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;

import fr.eni.tfProjetEnchere.bo.Utilisateur;
import fr.eni.tfProjetEnchere.dal.DALException;
import fr.eni.tfProjetEnchere.dal.DAO.UtilisateurDAO;
import fr.eni.tfProjetEnchere.dal.JDBC.UtilisateurDAOJDBCImpl;

@SuppressWarnings("serial")
public class UtilisateurManager extends HttpServlet {
	private static UtilisateurDAO dao = new UtilisateurDAOJDBCImpl();

	public void newUtilisateur(Utilisateur utilisateur) throws BLLException, DALException, SQLException {
		BLLException bllException = validateUtilisateur(utilisateur);
		if (bllException.hasErrors()) {
			throw bllException;
		} else {
			dao.newUtilisateur(utilisateur);
		}
	}

	public Utilisateur selectUtilisateurByLogin(String pseudoOuEmail, String motDePasse)
			throws DALException, SQLException {
		return dao.selectUtilisateurByLogin(pseudoOuEmail, motDePasse);

	}

	public static Utilisateur selectUtilisateurById(int id) throws DALException, SQLException {
		return dao.selectUtilisateurById(id);
	}

	public Utilisateur selectUtilisateurByPseudo(String pseudo) throws DALException, SQLException {
		return dao.selectUtilisateurByPseudo(pseudo);
	}

	public List<Utilisateur> getAllUtilisateur() throws DALException, SQLException {
		return dao.selectAllUtilisateur();
	}

	public void modifyUtilisateur(Utilisateur utilisateur) throws BLLException, DALException, SQLException {
		BLLException bllException = validateUtilisateur(utilisateur);
		if (bllException.hasErrors()) {
			throw bllException;
		} else {
			dao.modifyUtilisateur(utilisateur);
		}
	}

	public void deleteUtilisateur(int id) throws DALException, SQLException {
		dao.deleteUtilisateur(id);
	}

	private BLLException validateUtilisateur(Utilisateur utilisateur) {

		BLLException bllException = new BLLException();

		if (utilisateur.getPseudo().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_PSEUDO_UTILISATEUR);
		}

		if (utilisateur.getNom().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_NOM_UTILISATEUR);
		}
		if (utilisateur.getPrenom().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_PRENOM_UTILISATEUR);
		}
		if (utilisateur.getEmail().length() > 40) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_EMAIL_UTILISATEUR);
		}

		if (utilisateur.getTelephone().length() > 15) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_TELEPHONE_UTILISATEUR);
		}

		if (utilisateur.getRue().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_RUE_UTILISATEUR);
		}
		if (utilisateur.getCodePostal().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_CODE_POSTAL_UTILISATEUR);
		}
		if (utilisateur.getVille().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_VILLE_UTILISATEUR);
		}

		return bllException;
	}
}
