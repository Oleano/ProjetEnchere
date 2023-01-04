package fr.eni.tfProjetEnchere.bll;



public class FactoryBLL {

    public static UtilisateurManager getUtilisateursManager() {
        return new UtilisateurManager();
    }

    public static ArticleVenduManager getArticleVenduManager(){return new ArticleVenduManager();}
    public static EnchereManager getEnchereManager(){return new EnchereManager();}
    public static CategorieManager getCategoriesManager(){return new CategorieManager();
    }
    public  static RetraitManager getRetraitManager(){return new RetraitManager();}
}