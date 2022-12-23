package fr.eni.tfProjetEnchere.bo;

public class Retrait {
	private String rue;
	private String codePostal;
	private String ville;
	private int noArticle;

	public Retrait() {
		super();
	}
	
	public Retrait(String rue, String codePostal, String ville, int noArticle) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.noArticle = noArticle;
	}

	public Retrait(String rue, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getnoArticle() {
		return noArticle;
	}
	
	public void setNoArticle() {
		this.noArticle = noArticle;
	}
	public void setArticleVendu(int noArticle) {
		this.noArticle = noArticle;
	}

	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + ", noArticle="
				+ noArticle + "]";
	}

}
