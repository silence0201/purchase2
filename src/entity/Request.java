package entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

/**
 * Description: Request
 * Author: silence
 * Update: silence(2016-05-01 09:31)
 */
@Entity
@Table(name = "request")
public class Request {
    private Integer requestId;   //申请单的编号
    private double number;  //申请商品的数量
    private double totalCost;   //申请商品的金额
    private Item item ;  //申请的物品
    private Date requestTime;  //申请的时间
    private String requestStatus;  //申请的状态
    private Date auditTime;  //审核的时间
    private String reason;  //理由
    private User requestMan ;   // 申请人
    private User auditor ;  //审核人

    @Id
    @Column(name = "requestID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    @Basic
    @Column(name = "number")
    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    @Basic
    @Column(name = "totalCost")
    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Basic
    @Column(name = "requestTime")
    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    @Basic
    @Column(name = "requestStatus")
    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Basic
    @Column(name = "auditTime")
    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER )
    @JoinColumn(name = "itemID")
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER )
    @JoinColumn(name = "requestManID")
    public User getRequestMan() {
        return requestMan;
    }

    public void setRequestMan(User requestMan) {
        this.requestMan = requestMan;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER )
    @JoinColumn(name = "auditorID")
    public User getAuditor() {
        return auditor;
    }

    public void setAuditor(User auditor) {
        this.auditor = auditor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return Double.compare(request.getNumber(), getNumber()) == 0 &&
                Double.compare(request.getTotalCost(), getTotalCost()) == 0 &&
                Objects.equals(getRequestId(), request.getRequestId()) &&
                Objects.equals(getItem(), request.getItem()) &&
                Objects.equals(getRequestTime(), request.getRequestTime()) &&
                Objects.equals(getRequestStatus(), request.getRequestStatus()) &&
                Objects.equals(getAuditTime(), request.getAuditTime()) &&
                Objects.equals(getReason(), request.getReason()) &&
                Objects.equals(getRequestMan(), request.getRequestMan()) &&
                Objects.equals(getAuditor(), request.getAuditor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequestId(), getNumber(), getTotalCost(), getItem(), getRequestTime(), getRequestStatus(), getAuditTime(), getReason(), getRequestMan(), getAuditor());
    }

    @Override
    public String toString() {
        return "Request{" +
                "auditor=" + auditor +
                ", requestId=" + requestId +
                ", number=" + number +
                ", totalCost=" + totalCost +
                ", item=" + item +
                ", requestTime=" + requestTime +
                ", requestStatus='" + requestStatus + '\'' +
                ", auditTime=" + auditTime +
                ", reason='" + reason + '\'' +
                ", requestMan=" + requestMan +
                '}';
    }
}
