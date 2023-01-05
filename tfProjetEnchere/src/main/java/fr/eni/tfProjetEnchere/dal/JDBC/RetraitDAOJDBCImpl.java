package fr.eni.tfProjetEnchere.dal.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import fr.eni.tfProjetEnchere.bo.Retrait;
import fr.eni.tfProjetEnchere.dal.DALException;
import fr.eni.tfProjetEnchere.dal.DAO.RetraitDAO;

public class RetraitDAOJDBCImpl implements RetraitDAO {
	private static final String SELECT_ALL_RETRAIT = "SELECT * FROM RETRAITS";
	private static final String SELECT_RETRAIT_BY_ID = "SELECT * FROM RETRAIT WHERE no_article = ?";
	private static final String NEW_RETRAIT = "INSERT INTO RETRAIT VALUES(?, ?, ?, ?)";
	private static final String MODIFY_RETRAIT = "UPDATE RETRAIT SET no_article = ?, rue = ?, code_postal = ?, ville = ?";
	private static final String DELETE_RETRAIT = "DELETE FROM RETRAIT WHERE no_article = ?";

	@Override
	public List<Retrait> selectAllRetrait() throws DALException, SQLException {
		List<Retrait> listeRetraits = new ArrayList<Retrait>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_RETRAIT);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeRetraits.add(new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville")));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeRetraits;
	}

	@Override
	public Retrait selectRetraitById(int noArticle) throws DALException, SQLException {
		Retrait retrait = new Retrait();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_RETRAIT_BY_ID);
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				retrait.setRue("rue");
				retrait.setCodePostal("code_postal");
				retrait.setVille("ville");

			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retrait;
	}

	@Override
	public void newRetrait(Retrait retrait) throws DALException, SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(NEW_RETRAIT);
			pstmt.setInt(1, retrait.getnoArticle());
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());

			pstmt.executeUpdate();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modifyRetrait(Retrait retrait) throws DALException, SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(MODIFY_RETRAIT);
			pstmt.setInt(1, retrait.getnoArticle());
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());

			pstmt.executeUpdate();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRetrait(int noArticle) throws DALException, SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_RETRAIT);
			pstmt.setInt(1, noArticle);

			pstmt.executeUpdate();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
