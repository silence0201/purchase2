package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Description: Provider
 * Author: silence
 * Update: silence(2016-05-01 09:31)
 */
@Entity
@Table(name = "provider")
public class Provider {
    private Integer providerId;
    private String providerName;
    private String contant;
    private String tele;
    private String provinces;
    private String address;

    @Id
    @Column(name = "providerID")
    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    @Basic
    @Column(name = "providerName")
    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    @Basic
    @Column(name = "contant")
    public String getContant() {
        return contant;
    }

    public void setContant(String contant) {
        this.contant = contant;
    }

    @Basic
    @Column(name = "tele")
    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    @Basic
    @Column(name = "provinces")
    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return providerId == provider.providerId &&
                Objects.equals(providerName, provider.providerName) &&
                Objects.equals(contant, provider.contant) &&
                Objects.equals(tele, provider.tele) &&
                Objects.equals(provinces, provider.provinces) &&
                Objects.equals(address, provider.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(providerId, providerName, contant, tele, provinces, address);
    }
}
