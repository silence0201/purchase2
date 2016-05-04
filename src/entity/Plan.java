package entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * Description: Plan
 * Author: silence
 * Update: silence(2016-05-01 09:30)
 */
@Entity
@Table(name = "plan")
public class Plan {
    private Integer planId;
    private int number;
    private double totalCost;
    private String planStauts;
    private Date planTime;
    private Item item ;
    private List<Request> requests ;


    @Id
    @Column(name = "planID")
    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    @Basic
    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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
    @Column(name = "planStauts")
    public String getPlanStauts() {
        return planStauts;
    }

    public void setPlanStauts(String planStauts) {
        this.planStauts = planStauts;
    }

    @Basic
    @Column(name = "planTime")
    public Date getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER )
    @JoinColumn(name = "itemID")
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @OneToMany(fetch = FetchType.EAGER,targetEntity = Request.class)
    @JoinColumns(value = {@JoinColumn(name = "planID",referencedColumnName = "planID")})
    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plan)) return false;
        Plan plan = (Plan) o;
        return getNumber() == plan.getNumber() &&
                Double.compare(plan.getTotalCost(), getTotalCost()) == 0 &&
                Objects.equals(getPlanId(), plan.getPlanId()) &&
                Objects.equals(getPlanStauts(), plan.getPlanStauts()) &&
                Objects.equals(getPlanTime(), plan.getPlanTime()) &&
                Objects.equals(getItem(), plan.getItem()) &&
                Objects.equals(getRequests(), plan.getRequests());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlanId(), getNumber(), getTotalCost(), getPlanStauts(), getPlanTime(), getItem(), getRequests());
    }
}
