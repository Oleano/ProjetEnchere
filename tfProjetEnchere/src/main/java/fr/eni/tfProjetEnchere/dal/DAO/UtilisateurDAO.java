package fr.eni.tfProjetEnchere.dal.DAO;

import java.sql.SQLException;
import java.util.List;

import fr.eni.tfProjetEnchere.bo.Utilisateur;
import fr.eni.tfProjetEnchere.dal.DALException;

public interface UtilisateurDAO {
	public List<Utilisateur> selectAllUtilisateur() throws DALException, SQLException;
	public Utilisateur selectUtilisateurById(int id) throws DALException, SQLException;
	public Utilisateur selectUtilisateurByPseudo(String pseudo) throws DALException, SQLException;
	
	public void newUtilisateur(Utilisateur utilisateur) throws DALException, SQLException;
	public void modifyUtilisateur(Utilisateur id) throws DALException, SQLException;
	public void deleteUtilisateur(int id) throws DALException, SQLException;
	
	public Utilisateur selectLogin(String pseudoOuEmail, String password) throws DALException, SQLException;
	public Utilisateur selectLoginMdpOublie(String pseudoOuEmail) throws DALException, SQLException;

}
