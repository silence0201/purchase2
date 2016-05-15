package service.impl;

import dao.RequestDao;
import dao.impl.RequestDaoImpl;
import entity.Request;
import service.IDiVManagerService;

import java.util.ArrayList;

/**
 * Description: IDivManagerServiceImpl
 * Author: silence
 * Update: silence(2016-05-12 17:37)
 */
public class IDivManagerServiceImpl implements IDiVManagerService {

    RequestDao requestDao  ;

    @Override
    public boolean handle(Request request) {
        return false;
    }

    @Override
    public Request getRequestInfo(int requestID) {
        return null;
    }

    @Override
    public ArrayList<String> getCheckNotice(String position) {
        requestDao = new RequestDaoImpl() ;
        ArrayList<String> checks = requestDao.checkRequest(position) ;

        return checks;
    }

    @Override
    public ArrayList<Request> getRequesList(String position) {
        requestDao = new RequestDaoImpl() ;

        ArrayList<Request> requests = requestDao.requestList("D00001",position) ;

        return requests;
    }

    @Override
    public ArrayList<Request> getCheckList(String divManagerID) {
        requestDao = new RequestDaoImpl() ;

        ArrayList<Request> requests = requestDao.checkRequestList(divManagerID) ;

        return requests;
    }

    @Override
    public int getCountOfTime(String userID) {
        requestDao = new RequestDaoImpl() ;

        int count = requestDao.countOfTime(userID) ;

        return count;
    }

    @Override
    public double getSumOfMoney(String userID) {
        requestDao = new RequestDaoImpl() ;

        double sum = requestDao.countOfMoney(userID) ;

        return sum;
    }

    @Override
    public int getCountOfCheck(String userID) {
        requestDao = new RequestDaoImpl() ;

        int count = requestDao.countOfCheck(userID) ;

        return count;
    }

    @Override
    public int getCountOfCheckPart(String position) {
        requestDao = new RequestDaoImpl() ;

        int count = requestDao.countOfDepart(position) ;

        return count;
    }
}
