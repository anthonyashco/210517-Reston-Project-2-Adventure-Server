package dev.adventure.services;

import dev.adventure.daos.ManagerDao;
import dev.adventure.entities.Manager;

import java.util.List;

public class ManagerServiceImp implements ManagerService{

    private ManagerDao managerDao;

    public ManagerServiceImp(ManagerDao managerDao){
        this.managerDao = managerDao;
    }

    @Override
    public Manager createManager(Manager manager) {
        return managerDao.createManager(manager);
    }

    @Override
    public Manager getManagerByID(int managerID) {
        return managerDao.selectManagerByID(managerID);
    }

    @Override
    public List<Manager> getAllManagers() {
        return managerDao.getAllManagers();
    }

    @Override
    public Manager updateManger(Manager manager) {
        Manager check = managerDao.selectManagerByID(manager.getId());
        return managerDao.updateManager(manager);
    }

    @Override
    public boolean deleteManagerByID(int managerID) {
        Manager check = managerDao.selectManagerByID(managerID);
        return managerDao.deleteManagerByID(managerID);
    }
}
