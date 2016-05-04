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
 * Description: Export
 * Author: silence
 * Update: silence(2016-05-01 09:19)
 */
@Entity
@Table(name = "export")
public class Export {
    private Integer exportId;
    private Date exportTime;
    private User stockMan ;
    private Request request ;

    @Id
    @Column(name = "exportID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getExportId() {
        return exportId;
    }

    public void setExportId(Integer exportId) {
        this.exportId = exportId;
    }

    @Basic
    @Column(name = "exportTime")
    public Date getExportTime() {
        return exportTime;
    }

    public void setExportTime(Date exportTime) {
        this.exportTime = exportTime;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stockManID")
    public User getStockMan() {
        return stockMan;
    }

    public void setStockMan(User stockMan) {
        this.stockMan = stockMan;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "requestID")
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Export)) return false;
        Export export = (Export) o;
        return getExportId() == export.getExportId() &&
                Objects.equals(getExportTime(), export.getExportTime()) &&
                Objects.equals(getStockMan(), export.getStockMan()) &&
                Objects.equals(getRequest(), export.getRequest());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExportId(), getExportTime(), getStockMan(), getRequest());
    }
}
