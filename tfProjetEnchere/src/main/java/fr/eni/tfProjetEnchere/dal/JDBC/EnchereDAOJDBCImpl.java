package fr.eni.tfProjetEnchere.dal.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import fr.eni.tfProjetEnchere.bo.Enchere;
import fr.eni.tfProjetEnchere.dal.DALException;
import fr.eni.tfProjetEnchere.dal.DAO.EnchereDAO;

public class EnchereDAOJDBCImpl implements EnchereDAO {
	private static final String SELECT_ALL_ENCHERE = "SELECT * FROM ENCHERES";
	private static final String SELECT_MES_ENCHERE = "SELECT * FROM ENCHERES WHERE no_utilisateur";
	private static final String NEW_ENCHERE = "INSERT INTO ENCHERES VALUES(?, ?, ?, ?)";
	private static final String BEST_ENCHERE = "SELECT no_article, MAX(montant_enchere) AS best_enchere FROM ENCHERES WHERE no_article = ? ORDER BY no_article DESC";

	@Override
	public List<Enchere> selectAllEncheres() throws DALException, SQLException {
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ENCHERE);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeEncheres.add(new Enchere(rs.getObject("date_enchere", LocalDate.class),
						rs.getInt("montant_enchere"), rs.getInt("no_article"), rs.getInt("no_utilisateur")));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeEncheres;
	}

	// modifications du 27/12

	@Override
	public List<Enchere> selectMesEncheres(int idUtilisateur) throws DALException, SQLException {
		List<Enchere> listeMesEncheres = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_MES_ENCHERE);
			pstmt.setInt(1, idUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeMesEncheres.add(new Enchere(rs.getObject("date_enchere", LocalDate.class),
						rs.getInt("montant_enchere"), rs.getInt("no_article"), rs.getInt("no_utilisateur")));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeMesEncheres;
	}

	// 27/12 : modification des paramètres de la méthode -> (int noArticle, int
	// montantEnchere, int idUtilisateur) remplacés par l'objet "Enchere"
	@Override
	public void newEnchere(Enchere enchere) throws DALException, SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(NEW_ENCHERE);
			pstmt.setDate(1, java.sql.Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(2, enchere.getMontantEnchere());
			pstmt.setInt(3, enchere.getArticleVendu());
			pstmt.setInt(4, enchere.getEncherisseur());

			pstmt.executeUpdate();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// modifications du 27/12

	@Override
	public Enchere bestEnchere(int noArticle) throws DALException, SQLException {
		Enchere bestEnchere = new Enchere();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(BEST_ENCHERE);
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bestEnchere.setDateEnchere(rs.getObject("date_enchere", LocalDate.class));
				bestEnchere.setMontantEnchere(rs.getInt("montant_enchere"));
				bestEnchere.setEncherisseur(rs.getInt("no_utilisateur"));
				bestEnchere.setArticleVendu(rs.getInt("no_article"));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bestEnchere;
	}

}
