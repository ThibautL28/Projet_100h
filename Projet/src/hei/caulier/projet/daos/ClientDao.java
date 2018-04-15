package hei.caulier.projet.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hei.caulier.projet.DataSourceProvider;
import hei.caulier.projet.entities.Client;
import hei.caulier.projet.exceptions.ProjectRuntimeException;

public class ClientDao {

	 public List<Client> listClients() {

	        List<Client> clients = new ArrayList<>();
	        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery("SELECT * FROM client ORDER BY idClient")) {
	             while (resultSet.next()) {
	                clients.add(
	                        new Client(
	                                resultSet.getInt("idClient"),
	                                resultSet.getString("nomClient")
	                 ));
	            }
	        } catch (SQLException e) {
	            throw new ProjectRuntimeException("Erreur en essayant d'obtenir les clients", e);
	        }

	        return clients;
	    }		 
	    	
	 
	 public Client getClient(Integer id) {
	        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
	             PreparedStatement statement = connection.prepareStatement("SELECT * FROM client WHERE idClient = ?")) {
	            statement.setInt(1, id);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                while (resultSet.next()) {
	                    return new Client(
	                            resultSet.getInt("idClient"),
	                            resultSet.getString("nomClient"));
	                }
	            }
	        } catch (SQLException e) 
	        {
				throw new ProjectRuntimeException("Erreur en essayant d'obtenir le client", e);
	        }
	    	return null;
	    }
	 
	 public void addClient(Client newClient) {
	        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
	             PreparedStatement statement = connection.prepareStatement("INSERT INTO client(nomClient) VALUES (?)")) {
	            statement.setString(1, newClient.getNomClient());
	            statement.executeUpdate();
	        } catch (SQLException e) {
				throw new ProjectRuntimeException("Erreur en essayant d'ajouter un client", e);
			}
	    }
	 
	 public Integer getClientId(Client client){
	        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
	             PreparedStatement statement = connection.prepareStatement("SELECT * FROM client WHERE nomClient = ?")) {
	            statement.setString(1, client.getNomClient());
	            try (ResultSet resultSet = statement.executeQuery()) {
	                while (resultSet.next()) {
	                    return resultSet.getInt("idClient");
	                }
	            }
	        } catch (SQLException e) {
	            throw new ProjectRuntimeException("Erreur en essayant d'obtenir l'id du client", e);
	        }
	        return null;
	    }
	 
}