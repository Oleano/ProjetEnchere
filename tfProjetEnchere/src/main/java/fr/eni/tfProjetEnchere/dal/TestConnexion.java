package fr.eni.tfProjetEnchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import fr.eni.tfProjetEnchere.bo.ArticleVendu;
import fr.eni.tfProjetEnchere.dal.JDBC.CategorieDAOJDBCImpl;
import fr.eni.tfProjetEnchere.dal.JDBC.UtilisateurDAOJDBCImpl;

public class TestConnexion {

	public static void main(String[] args) throws DALException, SQLException {
		Connection cnx = ConnexionDB.getConnection();
		if (cnx != null) {
			System.out.println("Connexion établie avec succès !");

			ArticleVendu articleVendu = new ArticleVendu("Maison", "Maison 3 chambres", LocalDate.of(2022, 01, 02),
					LocalDate.of(2022, 01, 10), 250000, 300000, new UtilisateurDAOJDBCImpl().selectUtilisateurById(1),
					new CategorieDAOJDBCImpl().selectCategorieByNo(1));

			try {
				PreparedStatement pstmt = cnx.prepareStatement(
						"INSERT INTO ARTICLES_VENDUS values (?, ?, ?, ?, ?, ?, ?, ?)",
						PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, articleVendu.getNomArticle());
				pstmt.setString(2, articleVendu.getDescription());
				pstmt.setDate(3, java.sql.Date.valueOf(articleVendu.getDebutEnchere()));
				pstmt.setDate(4, java.sql.Date.valueOf(articleVendu.getFinEnchere()));
				pstmt.setInt(5, articleVendu.getMisAPrix());
				pstmt.setInt(6, articleVendu.getPrixVente());
				pstmt.setInt(7, articleVendu.getVendeur().getId());
				pstmt.setInt(8, articleVendu.getCategorie().getNoCategorie());

				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					articleVendu.setNoArticle(rs.getInt(1));
				}
				cnx.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
