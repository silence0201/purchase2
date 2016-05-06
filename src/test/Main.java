package test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import util.HibernateUtil;

import java.util.Map;

/**
 * Description: Main
 * Author: silence
 * Update: silence(2016-05-06 16:51)
 */
public class Main {
    public static void main(String[] args) {
        final Session session = HibernateUtil.getSession() ;
        try {
            System.out.println("querying all the managed entities...");
            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
            for (Object key : metadataMap.keySet()) {
                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
                final String entityName = classMetadata.getEntityName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        } finally {
            session.close();
        }
    }
}
