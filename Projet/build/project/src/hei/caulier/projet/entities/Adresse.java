package hei.caulier.projet.entities;


public class Adresse {
	private Integer idAdresse;
	private Client client;
	private String adresseClient;
	
	public Adresse(Integer idAdresse, Client client, String adresseClient) {
		super();
		this.idAdresse = idAdresse;
		this.client = client;
		this.adresseClient = adresseClient;
	}
	public Integer getIdAdresse() {
		return idAdresse;
	}
	public void setIdAdresse(Integer idAdresse) {
		this.idAdresse = idAdresse;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getAdresseClient() {
		return adresseClient;
	}
	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
	}
}
