package dao;

import entity.User;

/**
 * Description: UserDao
 * Author: silence
 * Update: silence(2016-05-01 14:38)
 */
public interface UserDao {
    public abstract User getByID(String userID) ;
}
