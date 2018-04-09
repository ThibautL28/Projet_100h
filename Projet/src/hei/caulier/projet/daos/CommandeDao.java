package hei.caulier.projet.daos;

import java.sql.*;

import hei.caulier.projet.DataSourceProvider;
import hei.caulier.projet.entities.Commande;
import hei.caulier.projet.exceptions.ProjectRuntimeException;
import hei.caulier.services.AdresseService;
import hei.caulier.services.ClientService;
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
                            ClientService.getInstance().getClient(resultSet.getInt("idClient")),
                            AdresseService.getInstance().getAdresse(resultSet.getInt("idAdresse")),
                            MachineService.getInstance().getMachine(resultSet.getInt("idMachine")),
                            resultSet.getDate("dateCom").toLocalDate(),
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
                            resultSet.getString("decoupe"));
                }
            }
        } catch (SQLException e) {
            throw new ProjectRuntimeException("Erreur en essayant d'obtenir la commande", e);
        }
        return null;
    }
	
	public void addCommande(Commande newCommande) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO commande(idClient, idAdresse, idMachine, dateCom, codeAchat, modeLivraison, typeImpression, sensImpressionRecto, sensImpressionVerso, tailleBobine, diamMandrin, diamExtBobine, developpement, cliche, epaisseur, matiere, observations, nbEtiquettes, rectoMatiere, versoMatiere, matiereImpression, matiereCollage, decoupe) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, ClientService.getInstance().getClientId(newCommande.getClient()));
            statement.setInt(2, AdresseService.getInstance().getAdresseId(newCommande.getAdresse()));
            statement.setInt(3, MachineService.getInstance().getMachineId(newCommande.getMachine()));
            statement.setDate(4, Date.valueOf(newCommande.getDateCom()));
            statement.setInt(5, newCommande.getCodeAchat());
            statement.setString(6, newCommande.getModeLivraison());
            statement.setString(7, newCommande.getTypeImpression());
            statement.setInt(8, newCommande.getSensImpressionRecto());
            statement.setInt(9, newCommande.getSensImpressionVerso());
            statement.setFloat(10,  newCommande.getTailleBobine());
            statement.setFloat(11,  newCommande.getDiamMandrin());
            statement.setFloat(12,  newCommande.getDiamExtBobine());
            statement.setString(13, newCommande.getDeveloppement());
            statement.setString(14, newCommande.getCliche());
            statement.setFloat(15,  newCommande.getEpaisseur());
            statement.setString(16, newCommande.getMatiere());
            statement.setString(17, newCommande.getObservations());
            statement.setInt(18, newCommande.getNbEtiquettes());
            statement.setString(19, newCommande.getRectoMatiere());
            statement.setString(20, newCommande.getVersoMatiere());
            statement.setString(21, newCommande.getMatiereImpression());
            statement.setString(22, newCommande.getMatiereCollage());
            statement.setString(23, newCommande.getDecoupe());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ProjectRuntimeException("Erreur en essayant d'ajouter une commande", e);
        }
    }
	
	public Integer getCommandeId(Commande commande){
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM commande WHERE idClient = ? AND idAdresse = ? AND dateCom = ? AND codeAchat = ?")) {
            statement.setInt(1, ClientService.getInstance().getClientId(commande.getClient()));
            statement.setInt(2, AdresseService.getInstance().getAdresseId(commande.getAdresse()));
            statement.setDate(3, Date.valueOf(commande.getDateCom()));
            statement.setInt(4, commande.getCodeAchat());
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
}
