package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import hei.caulier.projet.DataSourceProvider;
import hei.caulier.projet.daos.CommandeDao;
import hei.caulier.projet.entities.Commande;
import hei.caulier.services.AdresseService;
import hei.caulier.services.MachineService;

public class CommandeDaoTestCase extends AbstractDaoTestCase {

private CommandeDao commandeDao = new CommandeDao();
	
	@Override
	public void insertDataSet(Statement statement) throws Exception {
		statement.executeUpdate("INSERT INTO client(idClient, nomClient) VALUES(1, 'premier client')");
		statement.executeUpdate("INSERT INTO client(idClient, nomClient) VALUES(2, 'deuxieme client')");
		statement.executeUpdate("INSERT INTO machine(idMachine, nomMachine) VALUES(1, 'OMET')");
		statement.executeUpdate("INSERT INTO machine(idMachine, nomMachine) VALUES(2, 'SIAT')");
		statement.executeUpdate("INSERT INTO adresse(idAdresse, idClient, adresseClient) VALUES(1, 1, 'premiere adresse')");
		statement.executeUpdate("INSERT INTO adresse(idAdresse, idClient, adresseClient) VALUES(2, 2, 'deuxieme adresse')");
		statement.executeUpdate("INSERT INTO commande(idCom, idAdresse, idMachine, dateCom, codeAchat, modeLivraison, typeImpression, sensImpressionRecto, sensImpressionVerso, tailleBobine, diamMandrin, diamExtBobine, developpement, cliche, epaisseur, matiere, observations, nbEtiquettes, rectoMatiere, versoMatiere, matiereImpression, matiereCollage, decoupe) VALUES(1, 1, 1, '2018-01-01', 11, 'mode livraison 1', 'type impression 1', 5, 5, 11.1, 5.5, 6.5, 'developpement 1', 'cliche 1', 4.2, 'matiere 1', 'observations de la commande 1', 100, 'rectomatiere 1', 'versomatiere 1', 'matiere impression 1', 'matiere collage 1', 'decoupe 1')");
		statement.executeUpdate("INSERT INTO commande(idCom, idAdresse, idMachine, dateCom, codeAchat, modeLivraison, typeImpression, sensImpressionRecto, sensImpressionVerso, tailleBobine, diamMandrin, diamExtBobine, developpement, cliche, epaisseur, matiere, observations, nbEtiquettes, rectoMatiere, versoMatiere, matiereImpression, matiereCollage, decoupe) VALUES(2, 2, 2, '2018-02-02', 22, 'mode livraison 2', 'type impression 2', 4, 7, 22.2, 7.5, 6.2, 'developpement 2', 'cliche 2', 5.1, 'matiere 2', 'observations de la commande 2', 200, 'rectomatiere 2', 'versomatiere 2', 'matiere impression 2', 'matiere collage 2', 'decoupe 2')");
	}
	
	@Test
	public void shouldGetCommande() throws Exception {
        // WHEN
        Commande commande = commandeDao.getCommande(1);
        // THEN
        Assertions.assertThat(commande).isNotNull();
        Assertions.assertThat(commande.getIdCom()).isEqualTo(1);
        Assertions.assertThat(commande.getAdresse().getIdAdresse()).isEqualTo(1);
        Assertions.assertThat(commande.getAdresse().getClient().getNomClient()).isEqualTo("premier client");
        Assertions.assertThat(commande.getMachine().getIdMachine()).isEqualTo(1);
        Assertions.assertThat(commande.getDateCom()).isEqualTo(LocalDate.of(2018, 01, 01));
        Assertions.assertThat(commande.getCodeAchat()).isEqualTo(11);
        Assertions.assertThat(commande.getModeLivraison()).isEqualTo("mode livraison 1");
        Assertions.assertThat(commande.getTypeImpression()).isEqualTo("type impression 1");
        Assertions.assertThat(commande.getSensImpressionRecto()).isEqualTo(5);
        Assertions.assertThat(commande.getSensImpressionVerso()).isEqualTo(5);
        Assertions.assertThat(commande.getTailleBobine()).isEqualTo((float)11.1);
        Assertions.assertThat(commande.getDiamMandrin()).isEqualTo((float)5.5);
        Assertions.assertThat(commande.getDiamExtBobine()).isEqualTo((float)6.5);
        Assertions.assertThat(commande.getDeveloppement()).isEqualTo("developpement 1");
        Assertions.assertThat(commande.getCliche()).isEqualTo("cliche 1");
        Assertions.assertThat(commande.getEpaisseur()).isEqualTo((float)4.2);
        Assertions.assertThat(commande.getMatiere()).isEqualTo("matiere 1");
        Assertions.assertThat(commande.getObservations()).isEqualTo("observations de la commande 1");
        Assertions.assertThat(commande.getNbEtiquettes()).isEqualTo(100);
        Assertions.assertThat(commande.getRectoMatiere()).isEqualTo("rectomatiere 1");
        Assertions.assertThat(commande.getVersoMatiere()).isEqualTo("versomatiere 1");
        Assertions.assertThat(commande.getMatiereImpression()).isEqualTo("matiere impression 1");
        Assertions.assertThat(commande.getMatiereCollage()).isEqualTo("matiere collage 1");
        Assertions.assertThat(commande.getDecoupe()).isEqualTo("decoupe 1");
    }
	
	@Test
	 public void shouldAddCommande() throws Exception {
	    Commande newCommande = new Commande(null, AdresseService.getInstance().getAdresse(1), MachineService.getInstance().getMachine(1), LocalDate.of(2020, 03, 03), 11, "mode livraison 3", "type impression 3", 5, 5, (float)11.1, (float)5.5, (float)6.5, "developpement 3", "cliche 3", (float)4.2, "matiere 3", "observations de la commande 3", 100, "rectomatiere 3", "versomatiere 3", "matiere impression 3", "matiere collage 3", "decoupe 3");
	    commandeDao.addCommande(newCommande);
	    try(Connection connection = DataSourceProvider.getDataSource().getConnection();
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT * FROM commande WHERE modeLivraison='mode livraison 3'")){
	            Assertions.assertThat(resultSet.next()).isTrue();
	            Assertions.assertThat(resultSet.getInt("idCom")).isNotNull();
	            Assertions.assertThat(resultSet.getInt("idAdresse")).isEqualTo(1);
	            Assertions.assertThat(resultSet.getInt("idMachine")).isEqualTo(1);
	            Assertions.assertThat(resultSet.getDate("dateCom").toLocalDate()).isEqualTo(LocalDate.of(2020,03,03));
	            Assertions.assertThat(resultSet.getInt("codeAchat")).isEqualTo(11);
	            Assertions.assertThat(resultSet.getString("modeLivraison")).isEqualTo("mode livraison 3");
	            Assertions.assertThat(resultSet.getString("typeImpression")).isEqualTo("type impression 3");
	            Assertions.assertThat(resultSet.getInt("sensImpressionRecto")).isEqualTo(5);
	            Assertions.assertThat(resultSet.getInt("sensImpressionVerso")).isEqualTo(5);
	            Assertions.assertThat(resultSet.getFloat("tailleBobine")).isEqualTo((float)11.1);
	            Assertions.assertThat(resultSet.getFloat("diamMandrin")).isEqualTo((float)5.5);
	            Assertions.assertThat(resultSet.getFloat("diamExtBobine")).isEqualTo((float)6.5);
	            Assertions.assertThat(resultSet.getString("developpement")).isEqualTo("developpement 3");
	            Assertions.assertThat(resultSet.getString("cliche")).isEqualTo("cliche 3");
	            Assertions.assertThat(resultSet.getFloat("epaisseur")).isEqualTo((float)4.2);
	            Assertions.assertThat(resultSet.getString("matiere")).isEqualTo("matiere 3");
	            Assertions.assertThat(resultSet.getString("observations")).isEqualTo("observations de la commande 3");
	            Assertions.assertThat(resultSet.getInt("nbEtiquettes")).isEqualTo(100);
	            Assertions.assertThat(resultSet.getString("rectoMatiere")).isEqualTo("rectomatiere 3");
	            Assertions.assertThat(resultSet.getString("versoMatiere")).isEqualTo("versomatiere 3");
	            Assertions.assertThat(resultSet.getString("matiereImpression")).isEqualTo("matiere impression 3");
	            Assertions.assertThat(resultSet.getString("matiereCollage")).isEqualTo("matiere collage 3");
	            Assertions.assertThat(resultSet.getString("decoupe")).isEqualTo("decoupe 3");
	            Assertions.assertThat(resultSet.next()).isFalse();
	        }
	    }
}
