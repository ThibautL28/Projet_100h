package hei.caulier.projet.daos;

import java.sql.*;

import hei.caulier.projet.DataSourceProvider;
import hei.caulier.projet.entities.Commande;
import hei.caulier.projet.exceptions.ProjectRuntimeException;
import hei.caulier.services.AdresseService;
import hei.caulier.services.MachineService;

public class CommandeDao {
	
	public Commande getCommande(Integer id) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM commande WHERE idCom = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return new Commande(
                            resultSet.getInt("idCom"),
                            AdresseService.getInstance().getAdresse(resultSet.getInt("idAdresse")),
                            MachineService.getInstance().getMachine(resultSet.getInt("idMachine")),
                            resultSet.getDate("dateCom").toLocalDate(),
                            resultSet.getString("depart"),
                            resultSet.getInt("codeAchat"),
                            resultSet.getString("modeLivraison"),
                            resultSet.getString("typeImpression"),
                            resultSet.getInt("sensImpressionRecto"),
                            resultSet.getInt("sensImpressionVerso"),
                            resultSet.getFloat("tailleBobine"),
                            resultSet.getFloat("diamMandrin"),
                            resultSet.getFloat("diamExtBobine"),
                            resultSet.getString("developpement"),
                            resultSet.getString("cliche"),
                            resultSet.getFloat("epaisseur"),
                            resultSet.getString("matiere"),
                            resultSet.getString("observations"),
                            resultSet.getInt("nbEtiquettes"),
                            resultSet.getString("rectoMatiere"),
                            resultSet.getString("versoMatiere"),
                            resultSet.getString("matiereImpression"),
                            resultSet.getString("matiereCollage"),
                            resultSet.getString("decoupe"),
                    		resultSet.getInt("coteImpression"));
                }
            }
        } catch (SQLException e) {
            throw new ProjectRuntimeException("Erreur en essayant d'obtenir la commande", e);
        }
        return null;
    }
	
	public void addCommande(Commande newCommande) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO commande(idAdresse, idMachine, dateCom, depart, codeAchat, modeLivraison, typeImpression, sensImpressionRecto, sensImpressionVerso, tailleBobine, diamMandrin, diamExtBobine, developpement, cliche, epaisseur, matiere, observations, nbEtiquettes, rectoMatiere, versoMatiere, matiereImpression, matiereCollage, decoupe, coteImpression) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, AdresseService.getInstance().getAdresseId(newCommande.getAdresse()));
            statement.setInt(2, MachineService.getInstance().getMachineId(newCommande.getMachine()));
            statement.setDate(3, Date.valueOf(newCommande.getDateCom()));
            hei.caulier.projet.Helper.setStringOrNull(statement, 4, newCommande.getDepart());
            hei.caulier.projet.Helper.setIntOrNull(statement, 5, newCommande.getCodeAchat());
            hei.caulier.projet.Helper.setStringOrNull(statement, 6, newCommande.getModeLivraison());
            hei.caulier.projet.Helper.setStringOrNull(statement, 7, newCommande.getTypeImpression());
            hei.caulier.projet.Helper.setIntOrNull(statement, 8, newCommande.getSensImpressionRecto());
            hei.caulier.projet.Helper.setIntOrNull(statement, 9, newCommande.getSensImpressionVerso());
            hei.caulier.projet.Helper.setFloatOrNull(statement, 10, newCommande.getTailleBobine());
            hei.caulier.projet.Helper.setFloatOrNull(statement, 11, newCommande.getDiamMandrin());
            hei.caulier.projet.Helper.setFloatOrNull(statement, 12, newCommande.getDiamExtBobine());
            hei.caulier.projet.Helper.setStringOrNull(statement, 13, newCommande.getDeveloppement());
            hei.caulier.projet.Helper.setStringOrNull(statement, 14, newCommande.getCliche());
            hei.caulier.projet.Helper.setFloatOrNull(statement, 15, newCommande.getEpaisseur());
            hei.caulier.projet.Helper.setStringOrNull(statement, 16, newCommande.getMatiere());
            hei.caulier.projet.Helper.setStringOrNull(statement, 17, newCommande.getObservations());
            hei.caulier.projet.Helper.setIntOrNull(statement, 18, newCommande.getNbEtiquettes());
            hei.caulier.projet.Helper.setStringOrNull(statement, 19, newCommande.getRectoMatiere());
            hei.caulier.projet.Helper.setStringOrNull(statement, 20, newCommande.getVersoMatiere());
            hei.caulier.projet.Helper.setStringOrNull(statement, 21, newCommande.getMatiereImpression());
            hei.caulier.projet.Helper.setStringOrNull(statement, 22, newCommande.getMatiereCollage());
            hei.caulier.projet.Helper.setStringOrNull(statement, 23, newCommande.getDecoupe());
            hei.caulier.projet.Helper.setIntOrNull(statement, 24, newCommande.getCoteImpression());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ProjectRuntimeException("Erreur en essayant d'ajouter une commande", e);
        }
    }
	
	public Integer getCommandeId(Commande commande){
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM commande WHERE idAdresse = ? AND dateCom = ? AND codeAchat = ?")) {
            statement.setInt(1, AdresseService.getInstance().getAdresseId(commande.getAdresse()));
            statement.setDate(2, Date.valueOf(commande.getDateCom()));
            statement.setInt(3, commande.getCodeAchat());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getInt("idCom");
                }
            }
        } catch (SQLException e) {
            throw new ProjectRuntimeException("Erreur en essayant d'obtenir l'id de la commande", e);
        }
        return null;
    }
	
	public Integer getNewCommandeId() {
        try {
        	Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM commande ORDER BY idCom DESC LIMIT 1");
            while (resultSet.next()) {
                return resultSet.getInt("idCom");
            }
        } catch (SQLException e) {
            throw new ProjectRuntimeException("Erreur en essayant d'obtenir l'id de la derniere commande", e);
        }
		return null;
	}
}
