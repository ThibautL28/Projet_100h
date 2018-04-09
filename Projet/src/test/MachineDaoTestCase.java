package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import hei.caulier.projet.DataSourceProvider;
import hei.caulier.projet.daos.MachineDao;
import hei.caulier.projet.entities.Article;
import hei.caulier.projet.entities.Client;
import hei.caulier.projet.entities.LigneCommande;
import hei.caulier.projet.entities.Machine;

public class MachineDaoTestCase extends AbstractDaoTestCase{

private MachineDao machineDao = new MachineDao();
		
	@Override
	public void insertDataSet(Statement statement) throws Exception {
			statement.executeUpdate("INSERT INTO machine(idMachine, nomMachine) VALUES(1, 'OMET')");
			statement.executeUpdate("INSERT INTO machine(idMachine, nomMachine) VALUES(2, 'SIAT')");
		}
	
	@Test
	public void shouldListMachines() throws Exception {
		List<Machine> machines = machineDao.listMachines();
        Assertions.assertThat(machines).hasSize(2);
        Assertions.assertThat(machines).extracting("idMachine", "nomMachine").contains(
                Assertions.tuple(1, "OMET"),
                Assertions.tuple(2, "SIAT")
        );
	}
	
	@Test
	public void shouldGetMachine() throws Exception {
        Machine machine = machineDao.getMachine(1);
        
        Assertions.assertThat(machine).isNotNull();
        Assertions.assertThat(machine.getIdMachine()).isEqualTo(1);
        Assertions.assertThat(machine.getNomMachine()).isEqualTo("OMET");
	}
	
	@Test
	 public void shouldAddMachine() throws Exception {
	    Machine newMachine = new Machine(null, "KBA-Flexotecnica");
	    machineDao.addMachine(newMachine);
	    try(Connection connection = DataSourceProvider.getDataSource().getConnection();
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT * FROM machine WHERE nomMachine='KBA-Flexotecnica'")){
	            Assertions.assertThat(resultSet.next()).isTrue();
	            Assertions.assertThat(resultSet.getInt("idMachine")).isNotNull();
	            Assertions.assertThat(resultSet.getString("nomMachine")).isEqualTo("KBA-Flexotecnica");
	            Assertions.assertThat(resultSet.next()).isFalse();
	        }
	    }
}
