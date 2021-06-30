package dev.adventure.daos;

import dev.adventure.entities.Manager;

import java.util.List;

public interface ManagerDao {

    Manager createManager(Manager manager);

    Manager selectManagerByID(int managerID);

    List<Manager> getAllManagers();

    Manager updateManager(Manager manager);

    boolean deleteManagerByID(int managerID);

}
