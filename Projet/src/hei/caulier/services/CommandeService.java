package hei.caulier.services;


import hei.caulier.projet.daos.CommandeDao;
import hei.caulier.projet.entities.Commande;

public class CommandeService {

	private CommandeDao commandeDao = new CommandeDao();
	
    private static class CommandeServiceHolder {
        private static CommandeService instance = new CommandeService();
    }
    
    public static CommandeService getInstance() {
        return CommandeServiceHolder.instance;
    }

    private CommandeService() {
    }

    /*public List<Commande> listAllCommandes() {
            return commandeDao.listCommandes();
    }*/
    
    public Commande getCommande(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException("L'id de la commande n'est pas specifie");
        }
        return commandeDao.getCommande(id);
    }
    
    public Integer getCommandeId(Commande commande) {
        return commandeDao.getCommandeId(commande);
    }


    public void addCommande(Commande newCommande) {
        if(newCommande == null){
            throw new IllegalArgumentException("Une commande doit etre fournie");
        }
        commandeDao.addCommande(newCommande);
    }
}
