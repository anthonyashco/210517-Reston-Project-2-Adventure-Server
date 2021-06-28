package dev.adventure.daotests;

import dev.adventure.daos.PlanDao;
import dev.adventure.daos.PlanDaoImp;
import dev.adventure.entities.Plan;
import dev.adventure.exceptions.EntityNotFoundException;
import dev.adventure.services.PlanService;
import dev.adventure.services.PlanServiceImp;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PlanDaoTests {

    static PlanDao planDao = new PlanDaoImp();


    Plan testPlan = new Plan(0, "gold plan", "Premium", 500, 100);

    // this test is used to make sure test plans can be added to the database
    @Test()
    void createPlan() {
        Plan result = planDao.createPlan(testPlan);
        Assert.assertNotEquals(result.getPlanID(), 0);
    }

    @Test()
    void selectPlanByID() {
        Plan selectedResult = planDao.getPlanByID(1);
        Assert.assertEquals(selectedResult.getPlanID(), 1);
    }

    @Test()
    void selectAllPlans() {
        Plan testPlan2 = new Plan(0, "silver plan", "Mid-Tier", 700, 70);
        Plan testPlan3 = new Plan(0, "bronze plan", "Entry", 300, 30);
        planDao.createPlan(testPlan2);
        planDao.createPlan(testPlan3);
        List<Plan> plans = planDao.getAllPlans();
        Assert.assertTrue(plans.size() >= 3);

    }

    @Test(expectedExceptions={EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "There was an error trying to get the plan")
    void selectPlanByIDFail() {
        Plan selectedResultFail = planDao.getPlanByID(50);

    }

    @Test(expectedExceptions={EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "There was an error trying to get the plans")
    void selectAllPlansFail() {
        PlanDao planDaoMock = Mockito.mock(PlanDao.class);
        PlanService planServiceMock = new PlanServiceImp(planDaoMock);
        Mockito.when(planServiceMock.getAllPlans()).thenThrow(new EntityNotFoundException("There was an error trying to get the plans"));
        planServiceMock.getAllPlans();
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
