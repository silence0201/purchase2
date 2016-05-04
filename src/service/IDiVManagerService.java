package service;

import entity.Request;

/**
 * Description: IDiVManagerService
 * Author: silence
 * Update: silence(2016-05-01 17:38)
 */
public interface IDiVManagerService {

    //审核处理
    public abstract boolean handle(Request request) ;

    //获取申请单的详细信息
    public abstract Request getRequestInfo(int requestID) ;
}
