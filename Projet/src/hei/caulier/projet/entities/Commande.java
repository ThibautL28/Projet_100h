package hei.caulier.projet.entities;

import java.time.LocalDate;

public class Commande {

	private Integer idCom;
	private Adresse adresse;
	private Machine machine;
	private LocalDate dateCom;
	private Integer codeAchat;
	private String modeLivraison;
	private String typeImpression;
	private Integer sensImpressionRecto;
	private Integer sensImpressionVerso;
	private Float tailleBobine;
	private Float diamMandrin;
	private Float diamExtBobine;
	private String developpement;
	private String cliche;
	private Float epaisseur;
	private String matiere;
	private String observations;
	private Integer nbEtiquettes;
	private String rectoMatiere;
	private String versoMatiere;
	private String matiereImpression;
	private String matiereCollage;
	private String decoupe;
	public Commande(Integer idCom, Adresse adresse, Machine machine, LocalDate dateCom, 
			Integer codeAchat, String modeLivraison, String typeImpression, Integer sensImpressionRecto,
			Integer sensImpressionVerso, Float tailleBobine, Float diamMandrin, Float diamExtBobine,
			String developpement, String cliche, Float epaisseur, String matiere, String observations,
			Integer nbEtiquettes, String rectoMatiere, String versoMatiere, String matiereImpression,
			String matiereCollage, String decoupe) {
		super();
		this.idCom = idCom;
		this.adresse = adresse;
		this.machine = machine;
		this.dateCom = dateCom;
		this.codeAchat = codeAchat;
		this.modeLivraison = modeLivraison;
		this.typeImpression = typeImpression;
		this.sensImpressionRecto = sensImpressionRecto;
		this.sensImpressionVerso = sensImpressionVerso;
		this.tailleBobine = tailleBobine;
		this.diamMandrin = diamMandrin;
		this.diamExtBobine = diamExtBobine;
		this.developpement = developpement;
		this.cliche = cliche;
		this.epaisseur = epaisseur;
		this.matiere = matiere;
		this.observations = observations;
		this.nbEtiquettes = nbEtiquettes;
		this.rectoMatiere = rectoMatiere;
		this.versoMatiere = versoMatiere;
		this.matiereImpression = matiereImpression;
		this.matiereCollage = matiereCollage;
		this.decoupe = decoupe;
	}
	public Integer getIdCom() {
		return idCom;
	}
	public void setIdCom(Integer idCom) {
		this.idCom = idCom;
	}
	public Machine getMachine() {
		return machine;
	}
	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	public LocalDate getDateCom() {
		return dateCom;
	}
	public void setDateCom(LocalDate dateCom) {
		this.dateCom = dateCom;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public Integer getCodeAchat() {
		return codeAchat;
	}
	public void setCodeAchat(Integer codeAchat) {
		this.codeAchat = codeAchat;
	}
	public String getModeLivraison() {
		return modeLivraison;
	}
	public void setModeLivraison(String modeLivraison) {
		this.modeLivraison = modeLivraison;
	}
	public String getTypeImpression() {
		return typeImpression;
	}
	public void setTypeImpression(String typeImpression) {
		this.typeImpression = typeImpression;
	}
	public Integer getSensImpressionRecto() {
		return sensImpressionRecto;
	}
	public void setSensImpressionRecto(Integer sensImpressionRecto) {
		this.sensImpressionRecto = sensImpressionRecto;
	}
	public Integer getSensImpressionVerso() {
		return sensImpressionVerso;
	}
	public void setSensImpressionVerso(Integer sensImpressionVerso) {
		this.sensImpressionVerso = sensImpressionVerso;
	}
	public Float getTailleBobine() {
		return tailleBobine;
	}
	public void setTailleBobine(Float tailleBobine) {
		this.tailleBobine = tailleBobine;
	}
	public Float getDiamMandrin() {
		return diamMandrin;
	}
	public void setDiamMandrin(Float diamMandrin) {
		this.diamMandrin = diamMandrin;
	}
	public Float getDiamExtBobine() {
		return diamExtBobine;
	}
	public void setDiamExtBobine(Float diamExtBobine) {
		this.diamExtBobine = diamExtBobine;
	}
	public String getDeveloppement() {
		return developpement;
	}
	public void setDeveloppement(String developpement) {
		this.developpement = developpement;
	}
	public String getCliche() {
		return cliche;
	}
	public void setCliche(String cliche) {
		this.cliche = cliche;
	}
	public Float getEpaisseur() {
		return epaisseur;
	}
	public void setEpaisseur(Float epaisseur) {
		this.epaisseur = epaisseur;
	}
	public String getMatiere() {
		return matiere;
	}
	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public Integer getNbEtiquettes() {
		return nbEtiquettes;
	}
	public void setNbEtiquettes(Integer nbEtiquettes) {
		this.nbEtiquettes = nbEtiquettes;
	}
	public String getRectoMatiere() {
		return rectoMatiere;
	}
	public void setRectoMatiere(String rectoMatiere) {
		this.rectoMatiere = rectoMatiere;
	}
	public String getVersoMatiere() {
		return versoMatiere;
	}
	public void setVersoMatiere(String versoMatiere) {
		this.versoMatiere = versoMatiere;
	}
	public String getMatiereImpression() {
		return matiereImpression;
	}
	public void setMatiereImpression(String matiereImpression) {
		this.matiereImpression = matiereImpression;
	}
	public String getMatiereCollage() {
		return matiereCollage;
	}
	public void setMatiereCollage(String matiereCollage) {
		this.matiereCollage = matiereCollage;
	}
	public String getDecoupe() {
		return decoupe;
	}
	public void setDecoupe(String decoupe) {
		this.decoupe = decoupe;
	}
	
	
}
