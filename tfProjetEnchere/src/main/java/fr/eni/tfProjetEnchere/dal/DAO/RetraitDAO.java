package fr.eni.tfProjetEnchere.dal.DAO;

import java.sql.SQLException;
import java.util.List;

import fr.eni.tfProjetEnchere.bo.ArticleVendu;
import fr.eni.tfProjetEnchere.bo.Retrait;
import fr.eni.tfProjetEnchere.dal.DALException;

public interface RetraitDAO {
	public List<Retrait> selectAllRetrait() throws DALException, SQLException;
	public Retrait selectRetraitById(int noArticle) throws DALException, SQLException;
	public boolean retraitValide(int noArticle) throws DALException, SQLException;
	
	public void newRetrait(Retrait retrait) throws DALException, SQLException;
	public void modifyRetrait(ArticleVendu noArticleVendu) throws DALException, SQLException;
	public Retrait deleteRetrait(ArticleVendu noArticleVendu) throws DALException, SQLException;
	

}
