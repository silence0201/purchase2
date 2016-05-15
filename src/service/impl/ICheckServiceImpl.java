package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.ICheckService;

/**
 * Description: ICheckServiceImpl
 * Author: silence
 * Update: silence(2016-05-08 15:33)
 */
public class ICheckServiceImpl implements ICheckService {

    private UserDao dao ;

    public UserDao getDao() {
        return dao;
    }

    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public User loginCheck(String userID, String password) {
        dao = new UserDaoImpl() ;
        User user = dao.getByID(userID) ;

        if (user == null){
            return null ;
        }

        if (user.getPassWord().equals(password)){
            return user ;
        }
        return null;
    }
}
