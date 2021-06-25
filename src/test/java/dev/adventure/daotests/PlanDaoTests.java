package dev.adventure.daotests;

import dev.adventure.daos.PlanDao;
import dev.adventure.daos.PlanDaoImp;
import dev.adventure.entities.Plan;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PlanDaoTests {

    static PlanDao planDao = new PlanDaoImp();

/*    this block of code was used to reset the local database used for testing
    static void resetDatabase(){
        try(Connection connection = ConnectionUtility.createConnection()){
            String sql = "drop table if exists\"Project2\".plan";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
            sql = "create table \"Project2\".plan(\n" +
                    "\tplan_id serial primary key,\n" +
                    "\tplan_name varchar(50),\n" +
                    "\tplan_type varchar(50),\n" +
                    "\tdeductible float,\n" +
                    "\tpremium float\n" +
                    ")";
            ps = connection.prepareStatement(sql);
            ps.execute();
            System.out.println("plan table successfully reset");
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("could not reset plan table");
        }
    }
    @BeforeClass
    static void setup(){
        resetDatabase();
    }

    @AfterClass
    static void teardown(){
        resetDatabase();
    }

 */

    Plan testPlan = new Plan(0, "gold plan", "Premium", 500, 100);

    // this test is used to make sure test plans can be added to the database
    @Test(priority = 1)
    void createPlan() {
        Plan result = planDao.createPlan(testPlan);
        Assert.assertNotEquals(result.getPlanID(), 0);
    }

    @Test(priority = 2)
    void selectPlanByID() {
        Plan selectedResult = planDao.getPlanByID(1);
        Assert.assertEquals(selectedResult.getPlanID(), 1);
    }

    @Test(priority = 3)
    void selectAllPlans() {
        Plan testPlan2 = new Plan(0, "silver plan", "Mid-Tier", 700, 70);
        Plan testPlan3 = new Plan(0, "bronze plan", "Entry", 300, 30);
        planDao.createPlan(testPlan2);
        planDao.createPlan(testPlan3);
        List<Plan> plans = planDao.getAllPlans();
        Assert.assertTrue(plans.size() >= 3);

    }

/*     these tests bellow are for stretch goals
    @Test(priority=4)
    void updatePlan(){
        testPlan.setType("Top-Tier");
        planDao.updatePlan(testPlan);
        Plan updatedPlan = planDao.getPlanByID(1);
        Assert.assertEquals(updatedPlan.getType(),"Top-Tier");
    }

    @Test(priority=5)
    void deletePlan(){
        boolean planDeleted = planDao.deletePlanByID(1);
        Assert.assertTrue(planDeleted);
    }
*/

}
