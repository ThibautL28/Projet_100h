package hei.caulier.services;

import hei.caulier.projet.daos.LigneCommandeDao;
import hei.caulier.projet.entities.LigneCommande;

public class LigneCommandeService {

private LigneCommandeDao ligneCommandeDao = new LigneCommandeDao();
	
    private static class LigneCommandeServiceHolder {
        private static LigneCommandeService instance = new LigneCommandeService();
    }
    
    public static LigneCommandeService getInstance() {
        return LigneCommandeServiceHolder.instance;
    }

    private LigneCommandeService() {
    }
    
    public LigneCommande getLigneCommande(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException("L'id de la ligne de la commande n'est pas specifie");
        }
        return ligneCommandeDao.getLigneCommande(id);
    }
    
    public Integer getLigneCommandeId(LigneCommande ligneCommande) {
        return ligneCommandeDao.getLigneCommandeId(ligneCommande);
    }


    public void addLigneCommande(LigneCommande newLigneCommande) {
        if(newLigneCommande == null){
            throw new IllegalArgumentException("Une ligne de la commande doit etre fournie");
        }
        ligneCommandeDao.addLigneCommande(newLigneCommande);
    }
}
