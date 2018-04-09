package hei.caulier.services;

import java.util.List;

import hei.caulier.projet.daos.ClientDao;
import hei.caulier.projet.entities.Client;

public class ClientService {
	
	private ClientDao clientDao = new ClientDao();
    private static class ClientServiceHolder {
        private static ClientService instance = new ClientService();
    }
    
    public static ClientService getInstance() {
        return ClientServiceHolder.instance;
    }

    private ClientService() {
    }

    public List<Client> listAllClients() {
            return clientDao.listClients();
    }
    public Client getClient(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException("L'id du client n'est pas specifie");
        }
        return clientDao.getClient(id);
    }
    
    public Integer getClientId(Client client) {
        return clientDao.getClientId(client);
    }

    public void addAdresse(Client newClient) {
        if(newClient == null){
            throw new IllegalArgumentException("Un client doit etre fourni");
        }
        if(newClient.getNomClient() == null || "".equals(newClient.getNomClient())) {
            throw new IllegalArgumentException("Un client doit avoir un nom");
        }

        clientDao.addClient(newClient);
    }
}
