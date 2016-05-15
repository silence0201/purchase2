package dao.impl;

import dao.PlanDao;
import entity.Plan;
import entity.Request;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;

/**
 * Description: PlanDaoImpl
 * Author: silence
 * Update: silence(2016-05-07 00:54)
 */
public class PlanDaoImpl implements PlanDao {
    @Override
    public boolean addPlan(Plan plan) {
        Session session = HibernateUtil.getSession() ;
        try {
            session.beginTransaction() ;
            session.save(plan) ;
            for (Request request : plan.getRequests()){
                request.setRequestStatus("计划");
                session.update(request);
            }
            session.getTransaction().commit();
            return true ;
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close() ;
        }
        return false;
    }

    @Override
    public ArrayList<Plan> planList() {
        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        String hql = "from Plan " ;
        Query query = session.createQuery(hql) ;

        ArrayList<Plan> plans = (ArrayList<Plan>) query.list();

        tx.commit();
        session.close() ;

        return plans ;
    }

    @Override
    public ArrayList<String> needOrderPlan() {
        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        String hql = "select plan.planId from Plan plan where plan.planStauts=:status" ;
        Query query = session.createQuery(hql) ;
        query.setParameter("status","需受理") ;

        ArrayList<String> planIDs = (ArrayList<String>) query.list();

        tx.commit();
        session.clear();

        return planIDs;
    }

    @Override
    public Plan planInfo(Integer planID) {

        Session session = HibernateUtil.getSession() ;

        Plan plan = (Plan) session.get(Plan.class,planID);

        return plan;
    }
}
