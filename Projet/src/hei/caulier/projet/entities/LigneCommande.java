package hei.caulier.projet.entities;

public class LigneCommande {

	private Integer idLigne;
	private Commande commande;
	private Article article;
	private Float largeur;
	private Integer nbCouleurs;
	private String modele;
	private String refPantones;
	private Integer nbBobines;
	private Float metreTotal;
	private String couleurs;
	private String aplat;
	private Integer nbLangues;
	private String variete;
	private String calibre;
	private String poids;
	private String origine;
	private Boolean traitement;
	private String categorie;
	private Boolean pointVert;
	private String numLot;
	private String codeBarre;
	private Float etiquetteTotal;
	public LigneCommande(Integer idLigne, Commande commande, Article article, Float largeur, Integer nbCouleurs,
			String modele, String refPantones, Integer nbBobines, Float metreTotal, String couleurs, String aplat,
			Integer nbLangues, String variete, String calibre, String poids, String origine, Boolean traitement,
			String categorie, Boolean pointVert, String numLot, String codeBarre, Float etiquetteTotal) {
		super();
		this.idLigne = idLigne;
		this.commande = commande;
		this.article = article;
		this.largeur = largeur;
		this.nbCouleurs = nbCouleurs;
		this.modele = modele;
		this.refPantones = refPantones;
		this.nbBobines = nbBobines;
		this.metreTotal = metreTotal;
		this.couleurs = couleurs;
		this.aplat = aplat;
		this.nbLangues = nbLangues;
		this.variete = variete;
		this.calibre = calibre;
		this.poids = poids;
		this.origine = origine;
		this.traitement = traitement;
		this.categorie = categorie;
		this.pointVert = pointVert;
		this.numLot = numLot;
		this.codeBarre = codeBarre;
		this.etiquetteTotal = etiquetteTotal;
	}
	public Integer getIdLigne() {
		return idLigne;
	}
	public void setIdLigne(Integer idLigne) {
		this.idLigne = idLigne;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public Float getLargeur() {
		return largeur;
	}
	public void setLargeur(Float largeur) {
		this.largeur = largeur;
	}
	public Integer getNbCouleurs() {
		return nbCouleurs;
	}
	public void setNbCouleurs(Integer nbCouleurs) {
		this.nbCouleurs = nbCouleurs;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public String getRefPantones() {
		return refPantones;
	}
	public void setRefPantones(String refPantones) {
		this.refPantones = refPantones;
	}
	public Integer getNbBobines() {
		return nbBobines;
	}
	public void setNbBobines(Integer nbBobines) {
		this.nbBobines = nbBobines;
	}
	public Float getMetreTotal() {
		return metreTotal;
	}
	public void setMetreTotal(Float metreTotal) {
		this.metreTotal = metreTotal;
	}
	public String getCouleurs() {
		return couleurs;
	}
	public void setCouleurs(String couleurs) {
		this.couleurs = couleurs;
	}
	public String getAplat() {
		return aplat;
	}
	public void setAplat(String aplat) {
		this.aplat = aplat;
	}
	public Integer getNbLangues() {
		return nbLangues;
	}
	public void setNbLangues(Integer nbLangues) {
		this.nbLangues = nbLangues;
	}
	public String getVariete() {
		return variete;
	}
	public void setVariete(String variete) {
		this.variete = variete;
	}
	public String getCalibre() {
		return calibre;
	}
	public void setCalibre(String calibre) {
		this.calibre = calibre;
	}
	public String getPoids() {
		return poids;
	}
	public void setPoids(String poids) {
		this.poids = poids;
	}
	public String getOrigine() {
		return origine;
	}
	public void setOrigine(String origine) {
		this.origine = origine;
	}
	public Boolean getTraitement() {
		return traitement;
	}
	public void setTraitement(Boolean traitement) {
		this.traitement = traitement;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public Boolean getPointVert() {
		return pointVert;
	}
	public void setPointVert(Boolean pointVert) {
		this.pointVert = pointVert;
	}
	public String getNumLot() {
		return numLot;
	}
	public void setNumLot(String numLot) {
		this.numLot = numLot;
	}
	public String getCodeBarre() {
		return codeBarre;
	}
	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}
	public Float getEtiquetteTotal() {
		return etiquetteTotal;
	}
	public void setEtiquetteTotal(Float etiquetteTotal) {
		this.etiquetteTotal = etiquetteTotal;
	}
}
