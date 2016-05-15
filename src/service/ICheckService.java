package service;

import entity.User;

/**
 * Description: ICheckService
 * Author: silence
 * Update: silence(2016-05-01 17:22)
 */
public interface ICheckService {

    //登陆验证service
    public abstract User loginCheck(String userID, String password) ;
}
