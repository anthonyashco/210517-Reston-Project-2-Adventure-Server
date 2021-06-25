package dev.adventure.services;

import dev.adventure.daos.PlanDao;
import dev.adventure.entities.Plan;

import java.util.List;

public class PlanServiceImp implements PlanService{

    private PlanDao planDao;

    public PlanServiceImp(PlanDao planDao) {
        this.planDao = planDao;
    }

    @Override
    public Plan getPlanByID(int planID) {
        return planDao.getPlanByID(planID);
    }


    @Override
    public List<Plan> getAllPlans() {
        return planDao.getAllPlans();
    }
}
