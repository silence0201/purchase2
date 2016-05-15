package dao;

import entity.Plan;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Description: PlanDao
 * Author: silence
 * Update: silence(2016-05-01 15:32)
 */
public interface PlanDao {
    public abstract boolean addPlan(Plan plan) ;  //添加采购计划
    public abstract ArrayList<Plan> planList() ;  //获得采购计划列表
    public abstract ArrayList<String> needOrderPlan() ; //需要采购的计划单
    public abstract Plan planInfo(Integer planID) ;   //获取采购计划的详细信息
}
