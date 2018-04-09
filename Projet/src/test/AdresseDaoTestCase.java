package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import hei.caulier.projet.DataSourceProvider;
import hei.caulier.projet.daos.AdresseDao;
import hei.caulier.projet.entities.Adresse;
import hei.caulier.services.ClientService;

public class AdresseDaoTestCase extends AbstractDaoTestCase{

	private AdresseDao adresseDao = new AdresseDao();
	
	@Override
	public void insertDataSet(Statement statement) throws Exception {
		statement.executeUpdate("INSERT INTO client(idClient, nomClient) VALUES(1, 'premier client')");
		statement.executeUpdate("INSERT INTO client(idClient, nomClient) VALUES(2, 'deuxieme client')");
		statement.executeUpdate("INSERT INTO adresse(idAdresse, idClient, adresseClient) VALUES(1, 2, 'premiere adresse')");
		statement.executeUpdate("INSERT INTO adresse(idAdresse, idClient, adresseClient) VALUES(2, 1, 'deuxieme adresse')");
		statement.executeUpdate("INSERT INTO adresse(idAdresse, idClient, adresseClient) VALUES(3, 2, 'troisieme adresse')");
	}
	
	@Test
	public void shouldListAdresses() throws Exception {
		
		List<Adresse> adresses = adresseDao.listAdresses();
		
		Assertions.assertThat(adresses).hasSize(3);
        Assertions.assertThat(adresses).extracting("idAdresse", "client.idClient", "client.nomClient", "adresseClient").containsOnly(
        		Assertions.tuple(1, 2, "deuxieme client", "premiere adresse"),
                Assertions.tuple(2, 1, "premier client", "deuxieme adresse"),
                Assertions.tuple(3, 2, "deuxieme client", "troisieme adresse")
        );
	}
	
	 @Test
	    public void shouldGetAdresse() throws Exception {
	        Adresse adresse = adresseDao.getAdresse(1);
	        Assertions.assertThat(adresse).isNotNull();
	        Assertions.assertThat(adresse.getIdAdresse()).isEqualTo(1);
	        Assertions.assertThat(adresse.getClient().getIdClient()).isEqualTo(2);
	        Assertions.assertThat(adresse.getAdresseClient()).isEqualTo("premiere adresse");
	    }
	 
	 @Test
	    public void shouldAddAdresse() throws Exception {
	        // GIVEN
	        Adresse newAdresse = new Adresse(null, ClientService.getInstance().getClient(1), "Ma nouvelle adresse");
	        // WHEN
	        adresseDao.addAdresse(newAdresse);
	        // THEN
	        try(Connection connection = DataSourceProvider.getDataSource().getConnection();
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT * FROM adresse WHERE adresseClient='Ma nouvelle adresse'")){
	            Assertions.assertThat(resultSet.next()).isTrue();
	            Assertions.assertThat(resultSet.getInt("idAdresse")).isNotNull();
	            Assertions.assertThat(resultSet.getString("adresseClient")).isEqualTo("Ma nouvelle adresse");
	            Assertions.assertThat(resultSet.getInt("idClient")).isEqualTo(1);
	            Assertions.assertThat(resultSet.next()).isFalse();
	        }
	    }
}
