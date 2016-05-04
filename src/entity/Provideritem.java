package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.criteria.Fetch;
import java.util.Objects;

/**
 * Description: Provideritem
 * Author: silence
 * Update: silence(2016-05-01 11:28)
 */
@Entity
@Table(name = "provideritem")
public class Provideritem {
    private Integer providerItemId;
    private String quality;
    private double price;
    private Provider provider ;
    private Item item ;

    @Id
    @Column(name = "providerItemID")
    public Integer getProviderItemId() {
        return providerItemId;
    }

    public void setProviderItemId(Integer providerItemId) {
        this.providerItemId = providerItemId;
    }

    @Basic
    @Column(name = "quality")
    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "itemID")
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "providerID")
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provideritem)) return false;
        Provideritem that = (Provideritem) o;
        return getProviderItemId() == that.getProviderItemId() &&
                Double.compare(that.getPrice(), getPrice()) == 0 &&
                Objects.equals(getQuality(), that.getQuality()) &&
                Objects.equals(getProvider(), that.getProvider()) &&
                Objects.equals(getItem(), that.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProviderItemId(), getQuality(), getPrice(), getProvider(), getItem());
    }
}