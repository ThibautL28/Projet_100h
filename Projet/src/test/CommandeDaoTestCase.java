package test;

import java.sql.Statement;
import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import hei.caulier.projet.daos.CommandeDao;
import hei.caulier.projet.entities.Commande;

public class CommandeDaoTestCase extends AbstractDaoTestCase {

private CommandeDao commandeDao = new CommandeDao();
	
	@Override
	public void insertDataSet(Statement statement) throws Exception {
		statement.executeUpdate("INSERT INTO client(idClient, nomClient) VALUES(1, 'premier client')");
		statement.executeUpdate("INSERT INTO client(idClient, nomClient) VALUES(2, 'deuxieme client')");
		statement.executeUpdate("INSERT INTO machine(idMachine, nomMachine) VALUES(1, 'OMET')");
		statement.executeUpdate("INSERT INTO machine(idMachine, nomMachine) VALUES(2, 'SIAT')");
		statement.executeUpdate("INSERT INTO commande(idCom, idClient, idMachine, adresse, dateCom, codeAchat, modeLivraison, typeImpression, sensImpressionRecto, sensImpressionVerso, tailleBobine, diamMandrin, diamExtBobine, developpement, cliche, epaisseur, matiere, observations, nbEtiquettes, rectoMatiere, versoMatiere, matiereImpression, matiereCollage, decoupe) VALUES(1, 1, 1, 'adresse 1', '2018-01-01', 11, 'mode livraison 1', 'type impression 1', 5, 5, 11.1, 5.5, 6.5, 'developpement 1', 'cliche 1', 4.2, 'matiere 1', 'observations de la commande 1', 100, 'rectomatiere 1', 'versomatiere 1', 'matiere impression 1', 'matiere collage 1', 'decoupe 1')");
		statement.executeUpdate("INSERT INTO commande(idCom, idClient, idMachine, adresse, dateCom, codeAchat, modeLivraison, typeImpression, sensImpressionRecto, sensImpressionVerso, tailleBobine, diamMandrin, diamExtBobine, developpement, cliche, epaisseur, matiere, observations, nbEtiquettes, rectoMatiere, versoMatiere, matiereImpression, matiereCollage, decoupe) VALUES(2, 2, 2, 'adresse 2', '2018-02-02', 22, 'mode livraison 2', 'type impression 2', 4, 7, 22.2, 7.5, 6.2, 'developpement 2', 'cliche 2', 5.1, 'matiere 2', 'observations de la commande 2', 200, 'rectomatiere 2', 'versomatiere 2', 'matiere impression 2', 'matiere collage 2', 'decoupe 2')");
	}
	
	@Test
	public void shouldGetClient() throws Exception {
        // WHEN
        Commande commande = commandeDao.getCommande(1);
        // THEN
        Assertions.assertThat(commande).isNotNull();
        Assertions.assertThat(commande.getIdCom()).isEqualTo(1);
        Assertions.assertThat(commande.getClient().getIdClient()).isEqualTo(1);
        Assertions.assertThat(commande.getMachine().getIdMachine()).isEqualTo(1);
        Assertions.assertThat(commande.getAdresse()).isEqualTo("adresse 1");
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
}
