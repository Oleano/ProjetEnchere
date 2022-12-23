package fr.eni.tfProjetEnchere.bo;

import java.time.LocalDate;

public class Enchere {
	private LocalDate dateEnchere;
	private int montantEnchere;
	private int noEncherisseur;
	private int noArticleVendu;

	public Enchere() {
		super();
	}

	public Enchere(LocalDate dateEnchere, int montantEnchere, int noEncherisseur, int noArticleVendu) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noEncherisseur = noEncherisseur;
		this.noArticleVendu = noArticleVendu;
	}

	public Enchere(LocalDate dateEnchere, int montantEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public int getEncherisseur() {
		return noEncherisseur;
	}

	public void setEncherisseur(int noEncherisseur) {
		this.noEncherisseur = noEncherisseur;
	}

	public int getArticleVendu() {
		return noArticleVendu;
	}

	public void setArticleVendu(int noArticleVendu) {
		this.noArticleVendu = noArticleVendu;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", noEncherisseur="
				+ noEncherisseur + ", noArticleVendu=" + noArticleVendu + "]";
	}

}
