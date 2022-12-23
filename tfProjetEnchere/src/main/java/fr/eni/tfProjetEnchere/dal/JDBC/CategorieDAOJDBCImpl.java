package fr.eni.tfProjetEnchere.dal.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.tfProjetEnchere.bo.Categorie;
import fr.eni.tfProjetEnchere.dal.DALException;
import fr.eni.tfProjetEnchere.dal.DAO.CategorieDAO;

public class CategorieDAOJDBCImpl implements CategorieDAO {
	private static final String NEW_CATEGORIE = "INSERT INTO CATEGORIE VALUES (?, ?)";
	private static final String MODIFY_CATEGORIE = "UPDATE CATEGORIE SET no_categorie = ?, libelle = ?";
	private static final String DELETE_CATEGORIE = "DELETE FROM CATEGORIE WHERE no_categorie = ?";
	private static final String SELECT_ALL_CATEGORIE = "SELECT * FROM CATEGORIE";
	private static final String SELECT_CATEGORIE_BY_NO = "SELECT * FROM CATEGORIE WHERE no_categorie = ?";
	private static final String SELECT_CATEGORIE_BY_NAME = "SELECT * FROM CATEGORIE WHERE libelle = ?";

	@Override
	public void newCategorie(Categorie categorie) throws DALException, SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(NEW_CATEGORIE);
			pstmt.setInt(1, categorie.getNoCategorie());
			pstmt.setString(2, categorie.getLibelle());

			pstmt.executeUpdate();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modifyCategorie(Categorie categorie) throws DALException, SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(MODIFY_CATEGORIE);
			pstmt.setInt(1, categorie.getNoCategorie());
			pstmt.setString(2, categorie.getLibelle());

			pstmt.executeUpdate();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteCategorie(int noCategorie) throws DALException, SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_CATEGORIE);
			pstmt.setInt(1, noCategorie);
			pstmt.executeUpdate();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Categorie> selectAllCategorie() throws DALException, SQLException {
		List<Categorie> listeCategories = new ArrayList<Categorie>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_CATEGORIE);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeCategories.add(new Categorie(rs.getInt("no_categorie"), rs.getString("libelle")));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeCategories;
	}

	@Override
	public Categorie selectCategorieByNo(int noCategorie) throws DALException, SQLException {
		Categorie categorie = new Categorie();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIE_BY_NO);
			pstmt.setInt(1, noCategorie);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categorie;

	}

	@Override
	public List<Categorie> selectCategorieByName(String libelle) throws DALException, SQLException {
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIE_BY_NAME);
			pstmt.setString(2, libelle);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeCategorie.add(new Categorie(rs.getInt("no_categorie"), rs.getString("libelle")));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeCategorie;
	}

}
