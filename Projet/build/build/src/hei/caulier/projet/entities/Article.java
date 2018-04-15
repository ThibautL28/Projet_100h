package hei.caulier.projet.entities;

public class Article {
	private Integer idArticle;
	private String refArticle;
	private String nomArticle;
	
	public Article(Integer idArticle, String refArticle, String nomArticle) {
		super();
		this.idArticle = idArticle;
		this.refArticle = refArticle;
		this.nomArticle = nomArticle;
	}

	public Integer getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}

	public String getRefArticle() {
		return refArticle;
	}

	public void setRefArticle(String refArticle) {
		this.refArticle = refArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	
	
	
}
