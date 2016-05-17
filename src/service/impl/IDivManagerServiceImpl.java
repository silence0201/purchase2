package service.impl;

import dao.RequestDao;
import dao.impl.RequestDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Request;
import entity.User;
import service.IDiVManagerService;
import util.DateUtil;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Description: IDivManagerServiceImpl
 * Author: silence
 * Update: silence(2016-05-12 17:37)
 */
public class IDivManagerServiceImpl implements IDiVManagerService {

    RequestDao requestDao  ;


    @Override
    public boolean handle(String requestID, String staus, String reason,String auditor) {
        requestDao = new RequestDaoImpl() ;

        Request request = requestDao.requestInfo(Integer.parseInt(requestID)) ;
        request.setAuditor(new UserDaoImpl().getByID(auditor));
        request.setAuditTime(new Date(DateUtil.currentDate()));
        request.setReason(reason);
        request.setRequestStatus(staus);

        boolean flag = requestDao.modifyRequest(request) ;

        return flag;
    }

    @Override
    public Request getRequestInfo(int requestID) {
        requestDao = new RequestDaoImpl() ;

        Request request = requestDao.requestInfo(requestID) ;

        return request;
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
