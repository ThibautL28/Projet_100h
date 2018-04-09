package hei.caulier.services;

import java.util.List;

import hei.caulier.projet.daos.MachineDao;
import hei.caulier.projet.entities.Machine;

public class MachineService {

	private MachineDao machineDao = new MachineDao();
    private static class MachineServiceHolder {
        private static MachineService instance = new MachineService();
    }
    
    public static MachineService getInstance() {
        return MachineServiceHolder.instance;
    }

    private MachineService() {
    }

    public List<Machine> listAllMachines() {
            return machineDao.listMachines();
    }
    public Machine getMachine(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException("L'id de la machine n'est pas specifie");
        }
        return machineDao.getMachine(id);
    }
    
    public Integer getMachineId(Machine machine) {
        return machineDao.getMachineId(machine);
    }

    public void addMachine(Machine newMachine) {
        if(newMachine == null){
            throw new IllegalArgumentException("Une machine doit etre fournie");
        }
        if(newMachine.getNomMachine() == null || "".equals(newMachine.getNomMachine())) {
            throw new IllegalArgumentException("Une machine doit avoir un nom");
        }

        machineDao.addMachine(newMachine);
    }
}
