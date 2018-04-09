package hei.caulier.projet.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hei.caulier.projet.DataSourceProvider;
import hei.caulier.projet.entities.Adresse;
import hei.caulier.projet.exceptions.ProjectRuntimeException;
import hei.caulier.services.ClientService;

public class AdresseDao {

    public List<Adresse> listAdresses() {
    	List<Adresse> adresses = new ArrayList<>();

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM adresse ORDER BY idAdresse")) {
            while (resultSet.next()) {
                adresses.add(new Adresse(
                        resultSet.getInt("idAdresse"),
                        ClientService.getInstance().getClient(resultSet.getInt("idClient")),
                        resultSet.getString("adresseClient")
                ));
            }
        } catch (SQLException e) 
        {
			throw new ProjectRuntimeException("Erreur en essayant de lister les adresses", e);
		}
    	return adresses;
    }

    public Adresse getAdresse(Integer id) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM adresse WHERE idAdresse = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return new Adresse(
                            resultSet.getInt("idAdresse"),
                            ClientService.getInstance().getClient(resultSet.getInt("idClient")),
                            resultSet.getString("adresseClient"));
                }
            }
        } catch (SQLException e) 
        {
			throw new ProjectRuntimeException("Erreur en essayant d'obtenir l'adresse", e);
        }
    	return null;
    }

    public void addAdresse(Adresse newAdresse) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO adresse(idClient, adresseClient) VALUES (?, ?)")) {
            statement.setInt(1, ClientService.getInstance().getClientId(newAdresse.getClient()));
        	statement.setString(2, newAdresse.getAdresseClient());
            statement.executeUpdate();
        } catch (SQLException e) {
			throw new ProjectRuntimeException("Erreur en essayant d'ajouter une adresse", e);
		}
    }

    public Integer getAdresseId(Adresse adresse){
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM adresse WHERE adresseClient = ?")) {
            statement.setString(1, adresse.getAdresseClient());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getInt("idAdresse");
                }
            }
        } catch (SQLException e) {
            throw new ProjectRuntimeException("Erreur en essayant d'obtenir l'id de l'adresse", e);
        }
        return null;
    }
}

