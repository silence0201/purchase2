package dao.impl;

import com.sun.org.apache.regexp.internal.RE;
import dao.ExportDao;
import entity.Export;
import entity.Item;
import entity.Request;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.DateUtil;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.Date;

/**
 * Description: ExportDaoImpl
 * Author: silence
 * Update: silence(2016-05-06 23:35)
 */
public class ExportDaoImpl implements ExportDao{
    @Override
    public int count(String stockManID) {
        int count = 0 ;
        Session session = HibernateUtil.getSession() ;

        Date startDate = DateUtil.getStartOfMonth() ;
        Transaction tx =  session.beginTransaction() ;
        String hql = "select count(export) from Export export " +
                "where export.exportTime>=:exportDate and export.stockMan.userId=:userID" ;

        Query query = session.createQuery(hql) ;
        query.setParameter("exportDate",startDate) ;
        query.setParameter("userID",stockManID) ;

        Number num = (Number) query.uniqueResult() ;
        count = num.intValue() ;

        tx.commit();
        session.close() ;

        return count;
    }

    @Override
    public ArrayList<Export> exportList(String stockManID) {
        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        String hql = "select export from Export export " +
                "where export.stockMan.userId =:userID" +
                " order by export.exportTime desc ";

        Query query = session.createQuery(hql) ;
        query.setParameter("userID",stockManID) ;
        ArrayList<Export> result = (ArrayList<Export>) query.list();

        tx.commit();
        session.close() ;

        return result;
    }

    @Override
    public boolean add(Export export) {
        Session session = HibernateUtil.getSession() ;
        Request request = export.getRequest() ;
        request.setRequestStatus("完成");
        Item item = request.getItem() ;
        int invertory = item.getInventory() - request.getNumber() ;
        item.setInventory(invertory);
        try {
            session.beginTransaction() ;
            session.update(item);
            session.update(request);
            session.save(export) ;
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
