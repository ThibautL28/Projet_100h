package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import hei.caulier.projet.DataSourceProvider;
import hei.caulier.projet.daos.ClientDao;
import hei.caulier.projet.entities.Client;

public class ClientDaoTestCase extends AbstractDaoTestCase {

private ClientDao clientDao = new ClientDao();
	
	@Override
	public void insertDataSet(Statement statement) throws Exception {
		statement.executeUpdate("INSERT INTO client(idClient, nomClient) VALUES(1, 'premier client')");
		statement.executeUpdate("INSERT INTO client(idClient, nomClient) VALUES(2, 'deuxieme client')");
		statement.executeUpdate("INSERT INTO client(idClient, nomClient) VALUES(3, 'troisieme client')");
	}
	
	@Test
    public void shouldListClients() throws Exception {
        List<Client> clients = clientDao.listClients();
        Assertions.assertThat(clients).hasSize(3);
        Assertions.assertThat(clients).extracting("idClient", "nomClient").containsOnly(
                Assertions.tuple(1, "premier client"),
                Assertions.tuple(2, "deuxieme client"),
                Assertions.tuple(3, "troisieme client")
        );
    }
	
	@Test
    public void shouldGetClient() throws Exception {
        Client client = clientDao.getClient(1);
        Assertions.assertThat(client).isNotNull();
        Assertions.assertThat(client.getIdClient()).isEqualTo(1);
        Assertions.assertThat(client.getNomClient()).isEqualTo("premier client");
    }
	
	 @Test
	 public void shouldAddClient() throws Exception {
	    Client newClient = new Client(null, "Mon nouveau client");
	    clientDao.addClient(newClient);
	    try(Connection connection = DataSourceProvider.getDataSource().getConnection();
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT * FROM client WHERE nomClient='Mon nouveau client'")){
	            Assertions.assertThat(resultSet.next()).isTrue();
	            Assertions.assertThat(resultSet.getInt("idClient")).isNotNull();
	            Assertions.assertThat(resultSet.getString("nomClient")).isEqualTo("Mon nouveau client");
	            Assertions.assertThat(resultSet.next()).isFalse();
	        }
	    }
}
