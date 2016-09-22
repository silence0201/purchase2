package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

/**
 * Description: Order
 * Author: silence
 * Update: silence(2016-05-01 09:30)
 */
@Entity
@Table(name = "orders")
public class Order {
    private Integer orderId;   //订单编号
    private Date orderTime;   //订单生成时间
    private String orderStatus;  //订单状态
    private Plan plan ;   //订单对应计划单
    private User orderMan ;  //采购人
    private Provideritem provideritem ; //采购商品的信息
    private double totalCost;   //商品金额

    @Id
    @Column(name = "orderID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "orderTime")
    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Basic
    @Column(name = "orderStatus")
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderManID")
    public User getOrderMan() {
        return orderMan;
    }

    public void setOrderMan(User orderMan) {
        this.orderMan = orderMan;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "planID")
    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "providerItemID")
    public Provideritem getProvideritem() {
        return provideritem;
    }

    public void setProvideritem(Provideritem provideritem) {
        this.provideritem = provideritem;
    }

    @Basic
    @Column(name = "totalCost")
    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Double.compare(order.getTotalCost(), getTotalCost()) == 0 &&
                Objects.equals(getOrderId(), order.getOrderId()) &&
                Objects.equals(getOrderTime(), order.getOrderTime()) &&
                Objects.equals(getOrderStatus(), order.getOrderStatus()) &&
                Objects.equals(getPlan(), order.getPlan()) &&
                Objects.equals(getOrderMan(), order.getOrderMan()) &&
                Objects.equals(getProvideritem(), order.getProvideritem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getOrderTime(), getOrderStatus(), getPlan(), getOrderMan(), getProvideritem(), getTotalCost());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderTime=" + orderTime +
                ", orderStatus='" + orderStatus + '\'' +
                ", plan=" + plan +
                ", orderMan=" + orderMan +
                ", provideritem=" + provideritem +
                ", totalCost=" + totalCost +
                '}';
    }
}
