package dao.impl;

import dao.PlanDao;
import entity.Plan;
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
}
