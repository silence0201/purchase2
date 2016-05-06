package dao.impl;

import dao.ImportDao;
import entity.Import;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;

/**
 * Description: ImportDaoImpl
 * Author: silence
 * Update: silence(2016-05-07 00:57)
 */
public class ImportDaoImpl implements ImportDao {
    @Override
    public int count(String stockManID) {
        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        String hql  = "select count(export) from Export export" +
                " where export.stockMan.userId=:userID" ;
        Query query = session.createQuery(hql) ;
        query.setParameter("userID",stockManID) ;

        Number num = (Number) query.uniqueResult();
        int count = num.intValue() ;

        tx.commit();
        session.close() ;
        return count;
    }

    @Override
    public ArrayList<Import> importsList(String stockManID) {
        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        String hql = "select import from Import import" +
                " where import.stockMan.userId=:userID";
        Query query = session.createQuery(hql) ;
        query.setParameter("userID",stockManID) ;

        ArrayList<Import> imports = (ArrayList<Import>) query.list();

        tx.commit();
        session.close() ;
        return imports;
    }

    @Override
    public boolean add(Import impart) {
        Session session = HibernateUtil.getSession() ;

        try {
            session.beginTransaction() ;
            session.save(impart) ;
            session.getTransaction().commit();
            return true ;
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close() ;
        }
        return false;
    }
}
