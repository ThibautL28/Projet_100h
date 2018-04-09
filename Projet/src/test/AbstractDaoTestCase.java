package test;

import java.sql.Statement;
import org.junit.Before;
import java.sql.Connection;
import hei.caulier.projet.DataSourceProvider;

public abstract class AbstractDaoTestCase {

	@Before
	public void initDatabase() throws Exception {
		try(
				Connection connection = DataSourceProvider.getDataSource().getConnection();
				Statement statement = connection.createStatement()){
			statement.executeUpdate("DELETE FROM commande");
			statement.executeUpdate("DELETE FROM adresse");
			statement.executeUpdate("DELETE FROM client");
			statement.executeUpdate("DELETE FROM machine");
			statement.executeUpdate("DELETE FROM lignecommande");
			statement.executeUpdate("DELETE FROM article");

	        this.insertDataSet(statement);
		}
	}
	
	public abstract void insertDataSet(Statement statement) throws Exception;
}
