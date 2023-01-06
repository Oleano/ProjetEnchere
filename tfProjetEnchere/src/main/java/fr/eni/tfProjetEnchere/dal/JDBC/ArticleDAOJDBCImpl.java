package fr.eni.tfProjetEnchere.dal.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.tfProjetEnchere.bo.ArticleVendu;
import fr.eni.tfProjetEnchere.dal.DALException;
import fr.eni.tfProjetEnchere.dal.DAO.ArticleDAO;

public class ArticleDAOJDBCImpl implements ArticleDAO {
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS ";
	private static final String SELECT_BY_NO = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
	private static final String SELECT_BY_NAME = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE '%?%'";
	private static final String SELECT_BY_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie = ?";
	private static final String SELECT_ALL_BY_UTILISATEURS = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?";
	private static final String NEW_ARTICLE = "INSERT INTO ARTICLES_VENDUS values (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String MODIFY_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_enchere = ?, date_fin_enchere = ?, prix_initial = ?, prix_vente = ?, no_utilisateur = ?, no_categorie = ? WHERE no_article = ?";
	private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";

	@Override
	public List<ArticleVendu> selectAllArticle() throws DALException, SQLException {
		List<ArticleVendu> listeArticlesVendus = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeArticlesVendus.add(new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getObject("date_debut_encheres", LocalDate.class),
						rs.getObject("date_fin_encheres", LocalDate.class), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						new UtilisateurDAOJDBCImpl().selectUtilisateurById(rs.getInt("no_utilisateur")),
						new CategorieDAOJDBCImpl().selectCategorieByNo(rs.getInt("no_categorie"))));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeArticlesVendus;
	}

	@Override
	public ArticleVendu selectArticleById(int noArticle) throws DALException, SQLException {
		ArticleVendu articleVendu = new ArticleVendu();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NO);
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articleVendu.setNoArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDebutEnchere(rs.getObject("date_debut_encheres", LocalDate.class));
				articleVendu.setFinEnchere(rs.getObject("date_fin_encheres", LocalDate.class));
				articleVendu.setMisAPrix(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu
						.setVendeur(new UtilisateurDAOJDBCImpl().selectUtilisateurById(rs.getInt("no_utilisateur")));
				articleVendu.setCategorie(new CategorieDAOJDBCImpl().selectCategorieByNo(rs.getInt("no_categorie")));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleVendu;
	}

	@Override
	public List<ArticleVendu> selectArticleByName(String filtreNom) throws DALException, SQLException {
		List<ArticleVendu> listeArticlesVendus = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NAME);
			pstmt.setString(1, filtreNom);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeArticlesVendus.add(new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getObject("date_debut_encheres", LocalDate.class),
						rs.getObject("date_fin_encheres", LocalDate.class), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						new UtilisateurDAOJDBCImpl().selectUtilisateurById(rs.getInt("no_utilisateur")),
						new CategorieDAOJDBCImpl().selectCategorieByNo(rs.getInt("no_categorie"))));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeArticlesVendus;

	}

	@Override
	public List<ArticleVendu> selectArticleByCategorie(int filtreIdCategorie) throws DALException, SQLException {
		List<ArticleVendu> listeArticlesVendus = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_CATEGORIE);
			pstmt.setInt(1, filtreIdCategorie);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeArticlesVendus.add(new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getObject("date_debut_encheres", LocalDate.class),
						rs.getObject("date_fin_encheres", LocalDate.class), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						new UtilisateurDAOJDBCImpl().selectUtilisateurById(rs.getInt("no_utilisateur")),
						new CategorieDAOJDBCImpl().selectCategorieByNo(rs.getInt("no_categorie"))));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeArticlesVendus;

	}

	@Override
	public List<ArticleVendu> selectAllArticleByUtilisateur(int idUtilisateur) throws DALException, SQLException {
		List<ArticleVendu> listeArticlesVendus = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_BY_UTILISATEURS);
			pstmt.setInt(1, idUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeArticlesVendus.add(new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getObject("date_debut_encheres", LocalDate.class),
						rs.getObject("date_fin_encheres", LocalDate.class), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						new UtilisateurDAOJDBCImpl().selectUtilisateurById(rs.getInt("no_utilisateur")),
						new CategorieDAOJDBCImpl().selectCategorieByNo(rs.getInt("no_categorie"))));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeArticlesVendus;
	}

	@Override
	public void newArticle(ArticleVendu article) throws DALException, SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(NEW_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(article.getDebutEnchere()));
			pstmt.setDate(4, java.sql.Date.valueOf(article.getFinEnchere()));
			pstmt.setInt(5, article.getMisAPrix());
			pstmt.setInt(6, article.getPrixVente());
			pstmt.setInt(7, article.getVendeur().getId());
			pstmt.setInt(8, article.getCategorie().getNoCategorie());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				article.setNoArticle(rs.getInt(1));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modifyArticle(ArticleVendu noArticle) throws DALException, SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(MODIFY_ARTICLE);
			pstmt.setString(1, noArticle.getNomArticle());
			pstmt.setString(2, noArticle.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(noArticle.getDebutEnchere()));
			pstmt.setDate(4, java.sql.Date.valueOf(noArticle.getFinEnchere()));
			pstmt.setInt(5, noArticle.getMisAPrix());
			pstmt.setInt(6, noArticle.getPrixVente());
			pstmt.setInt(7, noArticle.getVendeur().getId());
			pstmt.setInt(8, noArticle.getCategorie().getNoCategorie());

			pstmt.executeUpdate();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteArticle(int noArticle) throws DALException, SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
			pstmt.setInt(1, noArticle);

			pstmt.executeUpdate();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
