package service;

import entity.Request;

/**
 * Description: IRequestService
 * Author: silence
 * Update: silence(2016-05-01 17:24)
 */
public interface IRequestService {

    //查询指定的申请单信息
    public abstract Request getRequestInfo(String requestID) ;

    //修改申请单信息
    public abstract boolean modifyRequest(Request request) ;

    //添加申请单信息
    public abstract boolean addRequest(Request request) ;
}
