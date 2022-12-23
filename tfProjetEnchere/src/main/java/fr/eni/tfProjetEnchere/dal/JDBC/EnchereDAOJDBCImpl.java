package fr.eni.tfProjetEnchere.dal.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import fr.eni.tfProjetEnchere.bo.ArticleVendu;
import fr.eni.tfProjetEnchere.bo.Enchere;
import fr.eni.tfProjetEnchere.dal.DALException;
import fr.eni.tfProjetEnchere.dal.DAO.EnchereDAO;

public class EnchereDAOJDBCImpl implements EnchereDAO {
	private static final String SELECT_ALL_ENCHERE = "SELECT * FROM ENCHERES";
	private static final String SELECT_MES_ENCHERE = "SELECT * FROM ENCHERES WHERE no_utilisateur";
	private static final String NEW_ENCHERE = "INSERT INTO ENCHERES VALUES(?, ?, ?, ?)";
	private static final String BEST_ENCHERE = "SELECT no_article, MAX(montant_enchere) AS best_enchere FROM ENCHERES WHERE no_article = ? GROUP BY no_article DESC";

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

	@Override
	public List<Enchere> selectMesEncheres(int idUtilisateur) throws DALException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void newEnchere(int noArticle, int montantEnchere, int idUtilisateur) throws DALException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Enchere bestEnchere(int noArticle) throws DALException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
