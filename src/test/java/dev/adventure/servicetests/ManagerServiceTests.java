package dev.adventure.servicetests;

import dev.adventure.daos.ManagerDao;
import dev.adventure.daos.ManagerDaoImp;
import dev.adventure.entities.Manager;
import dev.adventure.exceptions.EntityNotFoundException;
import dev.adventure.services.ManagerService;
import dev.adventure.services.ManagerServiceImp;
import dev.adventure.utils.ConnectionUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManagerServiceTests {

    Manager badManager = new Manager();

    ManagerDao managerDao = new ManagerDaoImp();
    ManagerService managerService = new ManagerServiceImp(managerDao);

    static void resetManagers(){
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "drop table if exists managers;\n" +
                    "create table managers(\n" +
                    "\tid serial,\n" +
                    "\t\"name\" varchar(50),\n" +
                    "\tusername varchar(50),\n" +
                    "\tpassword_hash varchar(200),\n" +
                    "\tpassword_salt varchar(200)\n" +
                    ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
            System.out.println("managers table was reset successfully");
        } catch (SQLException m) {
            System.out.println("managers table was not reset");
        }
    }

    @BeforeClass
    void setup(){
        resetManagers();
    }

    @AfterClass
    void teardown(){
        resetManagers();
    }

    @Test(priority = 1, expectedExceptions = {EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "There was an error finding the manager")
    void getManagerByIDFail(){
        managerService.getManagerByID(badManager.getId());
    }

    @Test(priority = 2, expectedExceptions = {EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "Manager information could not be found at this time")
    void getAllManagersFail(){
        managerService.getAllManagers();
    }

    @Test(priority = 3, expectedExceptions = {EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "There was an error finding the manager")
    void updateManagerFail(){
        managerService.updateManger(badManager);
    }

    @Test(priority = 4, expectedExceptions = {EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "There was an error finding the manager")
    void deleteManagerFail(){
        managerService.deleteManagerByID(badManager.getId());
    }

}
