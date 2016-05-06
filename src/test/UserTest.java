package test;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;

/**
 * Description: UserTest
 * Author: silence
 * Update: silence(2016-05-06 16:47)
 */
public class UserTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl() ;

        User user  = userDao.getByID("D00001") ;

        System.out.println(user);
    }
}
