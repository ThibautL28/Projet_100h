package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;


import hei.caulier.projet.DataSourceProvider;
import hei.caulier.projet.daos.LigneCommandeDao;
import hei.caulier.projet.entities.LigneCommande;
import hei.caulier.services.ArticleService;
import hei.caulier.services.CommandeService;

public class LigneCommandeDaoTestCase extends AbstractDaoTestCase {

private LigneCommandeDao ligneCommandeDao = new LigneCommandeDao();
	
	@Override
	public void insertDataSet(Statement statement) throws Exception {
		statement.executeUpdate("INSERT INTO client(idClient, nomClient) VALUES(1, 'premier client')");
		statement.executeUpdate("INSERT INTO client(idClient, nomClient) VALUES(2, 'deuxieme client')");
		statement.executeUpdate("INSERT INTO machine(idMachine, nomMachine) VALUES(1, 'OMET')");
		statement.executeUpdate("INSERT INTO machine(idMachine, nomMachine) VALUES(2, 'SIAT')");
		statement.executeUpdate("INSERT INTO machine(idMachine, nomMachine) VALUES(3, 'FLEXOTECNICA')");
		statement.executeUpdate("INSERT INTO adresse(idAdresse, idClient, adresseClient) VALUES(1, 1, 'premiere adresse')");
		statement.executeUpdate("INSERT INTO adresse(idAdresse, idClient, adresseClient) VALUES(2, 2, 'deuxieme adresse')");
		statement.executeUpdate("INSERT INTO commande(idCom, idAdresse, idMachine, dateCom, codeAchat, modeLivraison, typeImpression, sensImpressionRecto, sensImpressionVerso, tailleBobine, diamMandrin, diamExtBobine, developpement, cliche, epaisseur, matiere, observations, nbEtiquettes, rectoMatiere, versoMatiere, matiereImpression, matiereCollage, decoupe) VALUES(1, 1, 1, '2018-01-01', 11, 'mode livraison 1', 'type impression 1', 5, 5, 11.1, 5.5, 6.5, 'developpement 1', 'cliche 1', 4.2, 'matiere 1', 'observations de la commande 1', 100, 'rectomatiere 1', 'versomatiere 1', 'matiere impression 1', 'matiere collage 1', 'decoupe 1')");
		statement.executeUpdate("INSERT INTO commande(idCom, idAdresse, idMachine, dateCom, codeAchat, modeLivraison, typeImpression, sensImpressionRecto, sensImpressionVerso, tailleBobine, diamMandrin, diamExtBobine, developpement, cliche, epaisseur, matiere, observations, nbEtiquettes, rectoMatiere, versoMatiere, matiereImpression, matiereCollage, decoupe) VALUES(2, 2, 2, '2018-02-02', 22, 'mode livraison 2', 'type impression 2', 4, 7, 22.2, 7.5, 6.2, 'developpement 2', 'cliche 2', 5.1, 'matiere 2', 'observations de la commande 2', 200, 'rectomatiere 2', 'versomatiere 2', 'matiere impression 2', 'matiere collage 2', 'decoupe 2')");
		statement.executeUpdate("LOAD DATA LOCAL INFILE\r\n" + 
				"'C:/Users/Thibaut Lamonier/Desktop/HEI4/Projet 100h/divalto/articlesmodif.csv' \r\n" + 
				"INTO TABLE testprojet2.article\r\n" + 
				"FIELDS TERMINATED BY ';'\r\n" + 
				"ENCLOSED BY '' \r\n" + 
				"LINES TERMINATED BY '\\r\\n';");
		statement.executeUpdate("INSERT INTO lignecommande(idLigne, idCom, idArticle, largeur, nbCouleurs, modele, refPantones, nbBobines, metreTotal, couleurs, aplat, nbLangues, variete, calibre, poids, origine, traitement, categorie, pointVert, numLot, codeBarre, etiquetteTotal) VALUES(1, 1, 1, 12.3, 5, 'modele 1', 'refPantones 1', 6, 8.5, 'couleurs 1', 'aplat 1', 3, 'variete 1', 'calibre 1', 'poids 1', 'origine 1', 0, 'categ 1', 0, 'numLot 1', 'codeBarre 1', 22.2)");
		statement.executeUpdate("INSERT INTO lignecommande(idLigne, idCom, idArticle, largeur, nbCouleurs, modele, refPantones, nbBobines, metreTotal, couleurs, aplat, nbLangues, variete, calibre, poids, origine, traitement, categorie, pointVert, numLot, codeBarre, etiquetteTotal) VALUES(2, 2, 2, 11.5, 5, 'modele 2', 'refPantones 2', 7, 9.5, 'couleurs 2', 'aplat 2', 3, 'variete 2', 'calibre 2', 'poids 2', 'origine 2', 1, 'categ 2', 1, 'numLot 2', 'codeBarre 2', 33.3)");
	}
	
	@Test
	public void shouldGetLigneCommande() throws Exception {
        LigneCommande lignecommande = ligneCommandeDao.getLigneCommande(1);
        
        Assertions.assertThat(lignecommande).isNotNull();
        Assertions.assertThat(lignecommande.getIdLigne()).isEqualTo(1);
        Assertions.assertThat(lignecommande.getCommande().getIdCom()).isEqualTo(1);
        Assertions.assertThat(lignecommande.getArticle().getIdArticle()).isEqualTo(1);
        Assertions.assertThat(lignecommande.getLargeur()).isEqualTo((float)12.3);
        Assertions.assertThat(lignecommande.getNbCouleurs()).isEqualTo(5);
        Assertions.assertThat(lignecommande.getModele()).isEqualTo("modele 1");
        Assertions.assertThat(lignecommande.getRefPantones()).isEqualTo("refPantones 1");
        Assertions.assertThat(lignecommande.getNbBobines()).isEqualTo(6);
        Assertions.assertThat(lignecommande.getMetreTotal()).isEqualTo((float)8.5);
        Assertions.assertThat(lignecommande.getCouleurs()).isEqualTo("couleurs 1");
        Assertions.assertThat(lignecommande.getAplat()).isEqualTo("aplat 1");
        Assertions.assertThat(lignecommande.getNbLangues()).isEqualTo(3);
        Assertions.assertThat(lignecommande.getVariete()).isEqualTo("variete 1");
        Assertions.assertThat(lignecommande.getCalibre()).isEqualTo("calibre 1");
        Assertions.assertThat(lignecommande.getPoids()).isEqualTo("poids 1");
        Assertions.assertThat(lignecommande.getOrigine()).isEqualTo("origine 1");
        Assertions.assertThat(lignecommande.getTraitement()).isEqualTo(false);
        Assertions.assertThat(lignecommande.getCategorie()).isEqualTo("categ 1");
        Assertions.assertThat(lignecommande.getPointVert()).isEqualTo(false);
        Assertions.assertThat(lignecommande.getNumLot()).isEqualTo("numLot 1");
        Assertions.assertThat(lignecommande.getCodeBarre()).isEqualTo("codeBarre 1");
        Assertions.assertThat(lignecommande.getEtiquetteTotal()).isEqualTo((float)22.2);
	}
	
	@Test
	 public void shouldAddLigneCommande() throws Exception {
	    LigneCommande newLigneCommande = new LigneCommande(null, CommandeService.getInstance().getCommande(1), ArticleService.getInstance().getArticle(1), (float)19.1 , 4, "modele 3", "refPantones 3", 8, (float)13.5, "couleurs 3", "aplat 3", 5, "variete 3", "calibre 3", "poids 3", "origine 3", true, "categ 3", true, "numLot 3", "codeBarre 3", (float)123.6);
	    ligneCommandeDao.addLigneCommande(newLigneCommande);
	    try(Connection connection = DataSourceProvider.getDataSource().getConnection();
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT * FROM lignecommande WHERE modele='modele 3'")){
	            Assertions.assertThat(resultSet.next()).isTrue();
	            Assertions.assertThat(resultSet.getInt("idLigne")).isNotNull();
	            Assertions.assertThat(resultSet.getInt("idCom")).isEqualTo(1);
	            Assertions.assertThat(resultSet.getInt("idArticle")).isEqualTo(1);
	            Assertions.assertThat(resultSet.getFloat("largeur")).isEqualTo((float)19.1);
	            Assertions.assertThat(resultSet.getInt("nbCouleurs")).isEqualTo(4);
	            Assertions.assertThat(resultSet.getString("modele")).isEqualTo("modele 3");
	            Assertions.assertThat(resultSet.getString("refPantones")).isEqualTo("refPantones 3");
	            Assertions.assertThat(resultSet.getInt("nbBobines")).isEqualTo(8);
	            Assertions.assertThat(resultSet.getFloat("metreTotal")).isEqualTo((float)13.5);
	            Assertions.assertThat(resultSet.getString("couleurs")).isEqualTo("couleurs 3");
	            Assertions.assertThat(resultSet.getString("aplat")).isEqualTo("aplat 3");
	            Assertions.assertThat(resultSet.getInt("nblangues")).isEqualTo(5);
	            Assertions.assertThat(resultSet.getString("variete")).isEqualTo("variete 3");
	            Assertions.assertThat(resultSet.getString("calibre")).isEqualTo("calibre 3");
	            Assertions.assertThat(resultSet.getString("poids")).isEqualTo("poids 3");
	            Assertions.assertThat(resultSet.getString("origine")).isEqualTo("origine 3");
	            Assertions.assertThat(resultSet.getBoolean("traitement")).isEqualTo(true);
	            Assertions.assertThat(resultSet.getString("categorie")).isEqualTo("categ 3");
	            Assertions.assertThat(resultSet.getBoolean("pointVert")).isEqualTo(true);
	            Assertions.assertThat(resultSet.getString("numLot")).isEqualTo("numLot 3");
	            Assertions.assertThat(resultSet.getString("codeBarre")).isEqualTo("codeBarre 3");
	            Assertions.assertThat(resultSet.getFloat("etiquetteTotal")).isEqualTo((float)123.6);
	    }
}
	@Test
	public void shouldListLignesCommande() throws Exception {
		List<LigneCommande> lignescommande = ligneCommandeDao.listLignesCommande(1);
        Assertions.assertThat(lignescommande).hasSize(1);
        Assertions.assertThat(lignescommande).extracting("idLigne", "commande.idCom", "article.idArticle").contains(
                Assertions.tuple(1, 1, 1)
        );
	}
	
}
