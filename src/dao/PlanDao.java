package dao;

import entity.Plan;

import java.util.ArrayList;

/**
 * Description: PlanDao
 * Author: silence
 * Update: silence(2016-05-01 15:32)
 */
public interface PlanDao {
    public abstract boolean addPlan(Plan plan) ;  //添加采购计划
    public abstract ArrayList<Plan> planList() ;  //获得采购计划列表
}
