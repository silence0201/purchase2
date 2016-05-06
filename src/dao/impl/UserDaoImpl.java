package dao.impl;

import dao.UserDao;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * Description: UserDaoImpl
 * Author: silence
 * Update: silence(2016-05-06 16:33)
 */
public class UserDaoImpl implements UserDao {

    @Override
    public User getByID(String userID) {
        //创建Session
        Session session = HibernateUtil.getSession() ;

        //启动事务
        Transaction tc = session.beginTransaction() ;

        User user = (User)session.get(User.class,userID) ;

        //提交事务
        tc.commit();

        //关闭session
        session.close() ;

        return user;
    }
}
