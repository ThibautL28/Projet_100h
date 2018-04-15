package hei.caulier.services;

import hei.caulier.projet.daos.AdresseDao;
import hei.caulier.projet.entities.Adresse;
import java.util.List;

public class AdresseService {
	
	private AdresseDao adresseDao = new AdresseDao();
    private static class AdresseServiceHolder {
        private static AdresseService instance = new AdresseService();
    }
    
    public static AdresseService getInstance() {
        return AdresseServiceHolder.instance;
    }

    private AdresseService() {
    }

    public List<Adresse> listAllAdresses() {
            return adresseDao.listAdresses();
    }
    public Adresse getAdresse(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException("L'id de l'adresse n'est pas specifie");
        }
        return adresseDao.getAdresse(id);
    }
    
    public Integer getAdresseId(Adresse adresse) {
        return adresseDao.getAdresseId(adresse);
    }
    public Integer getAdresseIdFromString(String adresse) {
        return adresseDao.getAdresseIdFromString(adresse);
    }


    public void addAdresse(Adresse newAdresse) {
        if(newAdresse == null){
            throw new IllegalArgumentException("Une adresse doit etre fournie");
        }
        if(newAdresse.getAdresseClient() == null || "".equals(newAdresse.getAdresseClient())) {
            throw new IllegalArgumentException("Une adresse doit faire au moins un caractere");
        }

        adresseDao.addAdresse(newAdresse);
    }

}
