package hei.caulier.projet.entities;

public class Machine {

	private Integer idMachine;
	private String nomMachine;
	
	public Machine(Integer idMachine, String nomMachine) {
		super();
		this.idMachine = idMachine;
		this.nomMachine = nomMachine;
	}

	public Integer getIdMachine() {
		return idMachine;
	}

	public void setIdMachine(Integer idMachine) {
		this.idMachine = idMachine;
	}

	public String getNomMachine() {
		return nomMachine;
	}

	public void setNomMachine(String nomMachine) {
		this.nomMachine = nomMachine;
	}
	
	
}
