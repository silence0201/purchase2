package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    private Integer providerId;   //供应商编号
    private String providerName;   //供应商名称
    private String contant;  //供应商联系人
    private String tele;  //供应商联系电话
    private String provinces;  //供应商所属的省份
    private String address;  //供应商所属的地址
    private String status ; //供应商的状态

    @Id
    @Column(name = "providerID")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provider)) return false;
        Provider provider = (Provider) o;
        return Objects.equals(getProviderId(), provider.getProviderId()) &&
                Objects.equals(getProviderName(), provider.getProviderName()) &&
                Objects.equals(getContant(), provider.getContant()) &&
                Objects.equals(getTele(), provider.getTele()) &&
                Objects.equals(getProvinces(), provider.getProvinces()) &&
                Objects.equals(getAddress(), provider.getAddress()) &&
                Objects.equals(getStatus(), provider.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProviderId(), getProviderName(), getContant(), getTele(), getProvinces(), getAddress(), getStatus());
    }

    @Override
    public String toString() {
        return "Provider{" +
                "address='" + address + '\'' +
                ", providerId=" + providerId +
                ", providerName='" + providerName + '\'' +
                ", contant='" + contant + '\'' +
                ", tele='" + tele + '\'' +
                ", provinces='" + provinces + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
