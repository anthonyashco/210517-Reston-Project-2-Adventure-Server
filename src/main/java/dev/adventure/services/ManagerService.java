package dev.adventure.services;

import dev.adventure.entities.Manager;

import java.util.List;

public interface ManagerService {

    Manager createManager(Manager manager);

    Manager getManagerByID(int managerID);

    List<Manager> getAllManagers();

    Manager updateManger(Manager manager);

    boolean deleteManagerByID(int managerID);

}
