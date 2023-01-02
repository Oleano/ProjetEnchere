package fr.eni.tfProjetEnchere.dal.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import fr.eni.tfProjetEnchere.bo.Utilisateur;
import fr.eni.tfProjetEnchere.dal.DALException;
import fr.eni.tfProjetEnchere.dal.DAO.UtilisateurDAO;

public class UtilisateurDAOJDBCImpl implements UtilisateurDAO {
	private static final String SELECT_ALL_UTILISATEURS = "SELECT * FROM UTILISATEURS";
	private static final String SELECT_UTILISATEUR_BY_ID = "SELECT * FROM UTILISATEUR WHERE no_utilisateur = ?";
	private static final String SELECT_UTILISATEUR_BY_PSEUDO = "SELECT * FROM UTILISATEUR WHERE pseudo = ?";

	private static final String NEW_UTILISATEUR = "INSERT INTO UTILISATEURS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String MODIFY_UTILISATEUR = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ? WHERE no_utilisateur = ?";
	private static final String DELETE_UTILISATEUR = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";

	private static final String SELECT_UTILISATEUR_BY_LOGIN = "SELECT * FROM UTILISATEURS WHERE (email =? OR pseudo = ?) AND mot_de_passe = ?";
	private static final String SELECT_UTILISATEUR_BY_LOGIN_MDP_OUBLIE = "SELECT mot_de_passe FROM UTILISATEURS WHERE email = ?";

	@Override
	public List<Utilisateur> selectAllUtilisateur() throws DALException, SQLException {
		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_UTILISATEURS);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeUtilisateurs.add(new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
						rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),
						rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur")));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeUtilisateurs;

	}

	@Override
	public Utilisateur selectUtilisateurById(int id) throws DALException, SQLException {
		Utilisateur user = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setCredit(rs.getInt("credit"));
				user.setAdministrateur(rs.getBoolean("administrateur"));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;

	}

	@Override
	public Utilisateur selectUtilisateurByPseudo(String pseudo) throws DALException, SQLException {
		Utilisateur user = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_PSEUDO);
			pstmt.setString(2, pseudo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setCredit(rs.getInt("credit"));
				user.setAdministrateur(rs.getBoolean("administrateur"));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void newUtilisateur(Utilisateur utilisateur) throws DALException, SQLException {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(NEW_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				utilisateur.setId(rs.getInt(1));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modifyUtilisateur(Utilisateur utilisateur) throws DALException, SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(MODIFY_UTILISATEUR);
			pstmt.setString(2, utilisateur.getPseudo());
			pstmt.setString(3, utilisateur.getNom());
			pstmt.setString(4, utilisateur.getPrenom());
			pstmt.setString(5, utilisateur.getEmail());
			pstmt.setString(6, utilisateur.getTelephone());
			pstmt.setString(7, utilisateur.getRue());
			pstmt.setString(8, utilisateur.getCodePostal());
			pstmt.setString(9, utilisateur.getVille());
			pstmt.setString(10, utilisateur.getMotDePasse());
			pstmt.setInt(11, utilisateur.getCredit());
			pstmt.setBoolean(12, utilisateur.isAdministrateur());

			pstmt.executeUpdate();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteUtilisateur(int id) throws DALException, SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_UTILISATEUR);
			pstmt.setInt(1, id);

			pstmt.executeUpdate();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Utilisateur selectUtilisateurByLogin(String pseudoOuEmail, String motDePasse)
			throws DALException, SQLException {
		Utilisateur user = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_LOGIN);
			pstmt.setString(2, pseudoOuEmail);
			pstmt.setString(5, pseudoOuEmail);
			pstmt.setString(10, motDePasse);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setCredit(rs.getInt("credit"));
				user.setAdministrateur(rs.getBoolean("administrateur"));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Utilisateur selectUtilisateurByLoginMdpOublie(String email) throws DALException, SQLException {
		Utilisateur user = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_LOGIN_MDP_OUBLIE);
			pstmt.setString(5, email);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setEmail(rs.getString("email"));
				user.setMotDePasse(rs.getString("mot_de_passe"));

			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
