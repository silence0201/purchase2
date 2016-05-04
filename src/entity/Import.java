package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

/**
 * Description: Import
 * Author: silence
 * Update: silence(2016-05-01 09:20)
 */
@Entity
@Table(name = "import")
public class Import {
    private Integer importId;
    private Date importTime;
    private User stockMan ;
    private Order order ;

    @Id
    @Column(name = "importID")
    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }

    @Basic
    @Column(name = "ImportTime")
    public Date getImportTime() {
        return importTime;
    }

    public void setImportTime(Date importTime) {
        this.importTime = importTime;
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
    @JoinColumn(name = "orderID")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Import)) return false;
        Import anImport = (Import) o;
        return getImportId() == anImport.getImportId() &&
                Objects.equals(getImportTime(), anImport.getImportTime()) &&
                Objects.equals(getStockMan(), anImport.getStockMan()) &&
                Objects.equals(getOrder(), anImport.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getImportId(), getImportTime(), getStockMan(), getOrder());
    }
}
