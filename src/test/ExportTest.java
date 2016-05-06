package test;

import dao.ExportDao;
import dao.impl.ExportDaoImpl;
import entity.Export;

import java.util.ArrayList;

/**
 * Description: ExportTest
 * Author: silence
 * Update: silence(2016-05-06 23:46)
 */
public class ExportTest {
    public static void main(String[] args) {
//        count();
        exports();

    }
    public static void count(){
        ExportDao exportDao = new ExportDaoImpl() ;
        System.out.println(exportDao.count("S00001"));
    }
    public static  void exports(){
        ExportDao exportDao = new ExportDaoImpl() ;
        ArrayList<Export> exports = exportDao.exportList("S00001") ;

        for (Export export : exports){
            System.out.println(export);
        }
    }
}
