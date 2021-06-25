package dev.adventure.daos;

import dev.adventure.entities.Plan;
import dev.adventure.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanDaoImp implements PlanDao {

    @Override
    public Plan createPlan(Plan plan) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "insert into plan values(default, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, plan.getName());
            ps.setString(2, plan.getType());
            ps.setFloat(3, plan.getDeductible());
            ps.setFloat(4, plan.getPremium());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            plan.setPlanID(key);
            return plan;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("There was an error trying to create a new Plan");
            return null;
        }
    }

    @Override
    public Plan getPlanByID(int planID) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from plan where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, planID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Plan plan = new Plan();
            plan.setPlanID(rs.getInt("id"));
            plan.setName(rs.getString("plan_name"));
            plan.setType(rs.getString("plan_type"));
            plan.setDeductible(rs.getFloat("deductible"));
            plan.setPremium(rs.getFloat("premium"));
            return plan;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("There was an error trying to get the plan");
            return null;
        }
    }

    @Override
    public List<Plan> getAllPlans() {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from plan";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Plan> plans = new ArrayList<>();
            while (rs.next()) {
                Plan plan = new Plan(
                        rs.getInt("id"),
                        rs.getString("plan_name"),
                        rs.getString("plan_type"),
                        rs.getFloat("deductible"),
                        rs.getFloat("premium")
                );
                plans.add(plan);
            }
            return plans;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("There was an error trying to get the plans");
            return null;
        }

    }

    // these methods are for stretch goals
//    @Override
//    public Plan updatePlan(Plan plan) {
//        return null;
//    }
//
//    @Override
//    public boolean deletePlanByID(int planID) {
//        return false;
//    }

}
