package hei.caulier.projet.daos;

import hei.caulier.projet.DataSourceProvider;
import hei.caulier.projet.Helper;
import hei.caulier.projet.entities.LigneCommande;
import hei.caulier.projet.exceptions.ProjectRuntimeException;
import hei.caulier.services.ArticleService;
import hei.caulier.services.CommandeService;

import java.sql.*;

public class LigneCommandeDao {

	public LigneCommande getLigneCommande(Integer id) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM lignecommande WHERE idLigne = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return new LigneCommande(
                            resultSet.getInt("idLigne"),
                            CommandeService.getInstance().getCommande(resultSet.getInt("idCom")),
                            ArticleService.getInstance().getArticle(resultSet.getInt("idArticle")),
                            resultSet.getFloat("largeur"),
                            resultSet.getInt("nbCouleurs"),
                            resultSet.getString("modele"),
                            resultSet.getString("refPantones"),
                            resultSet.getInt("nbBobines"),
                            resultSet.getFloat("metreTotal"),
                            resultSet.getString("couleurs"),
                            resultSet.getString("aplat"),
                            resultSet.getInt("nbLangues"),
                            resultSet.getString("variete"),
                            resultSet.getString("calibre"),
                            resultSet.getString("poids"),
                            resultSet.getString("origine"),
                            resultSet.getBoolean("traitement"),
                            resultSet.getString("categorie"),
                            resultSet.getBoolean("pointVert"),
                            resultSet.getString("numLot"),
                            resultSet.getString("codeBarre"),
                            resultSet.getFloat("etiquetteTotal"));      
                }
            }
        } catch (SQLException e) {
            throw new ProjectRuntimeException("Erreur en essayant d'obtenir la ligne de la commande", e);
        }
        return null;
    }
	
	public void addLigneCommande(LigneCommande newLigneCommande) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO lignecommande(idCom, idArticle, largeur, nbCouleurs, modele, refPantones, nbBobines, metreTotal, couleurs, aplat, nbLangues, variete, calibre, poids, origine, traitement, categorie, pointVert, numLot, codeBarre, etiquetteTotal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
        	statement.setInt(1, CommandeService.getInstance().getCommandeId(newLigneCommande.getCommande()));
            statement.setInt(2, ArticleService.getInstance().getArticleId(newLigneCommande.getArticle()));
            Helper.setFloatOrNull(statement, 3, newLigneCommande.getLargeur());
            Helper.setIntOrNull(statement, 4, newLigneCommande.getNbCouleurs());
            Helper.setStringOrNull(statement, 5, newLigneCommande.getModele());
            Helper.setStringOrNull(statement, 6, newLigneCommande.getRefPantones());
            Helper.setIntOrNull(statement, 7, newLigneCommande.getNbBobines());
            Helper.setFloatOrNull(statement, 8,  newLigneCommande.getMetreTotal());
            Helper.setStringOrNull(statement, 9, newLigneCommande.getCouleurs());
            Helper.setStringOrNull(statement, 10, newLigneCommande.getAplat());
            Helper.setIntOrNull(statement, 11, newLigneCommande.getNbLangues());
            Helper.setStringOrNull(statement, 12, newLigneCommande.getVariete());
            Helper.setStringOrNull(statement, 13, newLigneCommande.getCalibre());
            Helper.setStringOrNull(statement, 14, newLigneCommande.getPoids());
            Helper.setStringOrNull(statement, 15, newLigneCommande.getOrigine());
            Helper.setBooleanOrNull(statement, 16, newLigneCommande.getTraitement());
            Helper.setStringOrNull(statement, 17, newLigneCommande.getCategorie());
            Helper.setBooleanOrNull(statement, 18, newLigneCommande.getPointVert());
            Helper.setStringOrNull(statement, 19, newLigneCommande.getNumLot());
            Helper.setStringOrNull(statement, 20, newLigneCommande.getCodeBarre());
            Helper.setFloatOrNull(statement, 21, newLigneCommande.getEtiquetteTotal());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ProjectRuntimeException("Erreur en essayant d'ajouter une ligne", e);
        }
    }
	
	public Integer getLigneCommandeId(LigneCommande ligneCommande){
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM lignecommande WHERE idCommande = ? AND idArticle = ? AND numLot = ?")) {
            statement.setInt(1, CommandeService.getInstance().getCommandeId(ligneCommande.getCommande()));
            statement.setInt(2, ArticleService.getInstance().getArticleId(ligneCommande.getArticle()));
            statement.setString(3, ligneCommande.getNumLot());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getInt("idLigne");
                }
            }
        } catch (SQLException e) {
            throw new ProjectRuntimeException("Erreur en essayant d'obtenir l'id de la ligne de la commande", e);
        }
        return null;
    }
}
