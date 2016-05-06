package dao;

import entity.User;

/**
 * Description: UserDao
 * Author: silence
 * Update: silence(2016-05-01 14:38)
 */
public interface UserDao {
    //感觉id获得用户的信息
    public abstract User getByID(String userID) ;
}
