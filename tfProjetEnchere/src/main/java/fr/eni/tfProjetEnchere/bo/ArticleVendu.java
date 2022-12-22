package fr.eni.tfProjetEnchere.bo;

import java.time.LocalDate;
import java.util.List;

public class ArticleVendu {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate debutEnchere;
	private LocalDate finEnchere;
	private int misAPrix;
	private int prixVente;
	private String etatVente;
	private int vendeur;
	private List<Enchere> enchere;
	private int categorie;
	private Retrait retrait;
	
	public ArticleVendu() {
		super();
	}

	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate debutEnchere,
			LocalDate finEnchere, int misAPrix, int prixVente, String etatVente, int vendeur,
			List<Enchere> enchere, int categorie, Retrait retrait) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.debutEnchere = debutEnchere;
		this.finEnchere = finEnchere;
		this.misAPrix = misAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.vendeur = vendeur;
		this.enchere = enchere;
		this.categorie = categorie;
		this.retrait = retrait;
	}

	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate debutEnchere,
			LocalDate finEnchere, int misAPrix, int prixVente, String etatVente) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.debutEnchere = debutEnchere;
		this.finEnchere = finEnchere;
		this.misAPrix = misAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}
	

	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate debutEnchere,
			LocalDate finEnchere, int misAPrix, int prixVente, int vendeur, int categorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.debutEnchere = debutEnchere;
		this.finEnchere = finEnchere;
		this.misAPrix = misAPrix;
		this.prixVente = prixVente;
		this.vendeur = vendeur;
		this.categorie = categorie;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDebutEnchere() {
		return debutEnchere;
	}

	public void setDebutEnchere(LocalDate debutEnchere) {
		this.debutEnchere = debutEnchere;
	}

	public LocalDate getFinEnchere() {
		return finEnchere;
	}

	public void setFinEnchere(LocalDate finEnchere) {
		this.finEnchere = finEnchere;
	}

	public int getMisAPrix() {
		return misAPrix;
	}

	public void setMisAPrix(int misAPrix) {
		this.misAPrix = misAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public int getVendeur() {
		return vendeur;
	}

	public void setVendeur(int vendeur) {
		this.vendeur = vendeur;
	}

	public List<Enchere> getEnchere() {
		return enchere;
	}

	public void setEnchere(List<Enchere> enchere) {
		this.enchere = enchere;
	}

	public int getCategorie() {
		return categorie;
	}

	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}
	

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", debutEnchere=" + debutEnchere + ", finEnchere=" + finEnchere + ", misAPrix=" + misAPrix
				+ ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", utilisateur=" + vendeur
				+ ", enchere=" + enchere + ", categorie=" + categorie + ", retrait=" + retrait + "]";
	}
	
}
