package hei.caulier.projet.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hei.caulier.projet.DataSourceProvider;
import hei.caulier.projet.entities.Machine;
import hei.caulier.projet.exceptions.ProjectRuntimeException;



public class MachineDao {

	public List<Machine> listMachines() {
		 
    	List<Machine> machines = new ArrayList<>();

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM machine ORDER BY idMachine")) {
            while (resultSet.next()) {
                machines.add(new Machine(
                        resultSet.getInt("idMachine"),
                        resultSet.getString("nomMachine")));
            }
        } catch (SQLException e) 
        {
			throw new ProjectRuntimeException("Erreur en essayant de lister les machines", e);
		}
    	return machines;
 	}
 
	public Machine getMachine(Integer id) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM machine WHERE idMachine = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return new Machine(
                            resultSet.getInt("idMachine"),
                            resultSet.getString("nomMachine"));
                }
            }
        } catch (SQLException e) 
        {
			throw new ProjectRuntimeException("Erreur en essayant d'obtenir la machine", e);
        }
    	return null;
    }
 
	public void addMachine(Machine newMachine) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO machine(nomMachine) VALUES (?)")) {
            statement.setString(1, newMachine.getNomMachine());
            statement.executeUpdate();
        } catch (SQLException e) {
			throw new ProjectRuntimeException("Erreur en essayant d'ajouter une machine", e);
		}
    }
 
	public Integer getMachineId(Machine machine){
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM machine WHERE nomMachine = ?")) {
            statement.setString(1, machine.getNomMachine());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getInt("idMachine");
                }
            }
        } catch (SQLException e) {
            throw new ProjectRuntimeException("Erreur en essayant d'obtenir l'id de la machine", e);
        }
        return null;
    }
 
 	//delete ?
}
