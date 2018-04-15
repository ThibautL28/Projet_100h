package hei.caulier.projet.entities;

public class Client {
	private Integer idClient;
	private String nomClient;
	
	public Client(Integer idClient, String nomClient) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;
	}
	
	public Integer getIdClient() {
		return idClient;
	}
	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
}
