package service;

import entity.Request;

/**
 * Description: IManagerService
 * Author: silence
 * Update: silence(2016-05-01 17:41)
 */
public interface IManagerService {
    //需求审核处理
    public abstract boolean handle(Request request) ;

    //需求信息获取
    public abstract Request getRequestInfo (String requestID) ;
}
