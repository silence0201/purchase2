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
 * Description: Item
 * Author: silence
 * Update: silence(2016-05-01 09:20)
 */
@Entity
@Table(name = "item")
public class Item {
    private Integer itemId;   //商品编号
    private String itemName;  //商品名称
    private double avePrice;  //商品价格
    private int inventory;  //商品库存

    @Id
    @Column(name = "itemID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "itemName")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "avePrice")
    public double getAvePrice() {
        return avePrice;
    }

    public void setAvePrice(double avePrice) {
        this.avePrice = avePrice;
    }

    @Basic
    @Column(name = "inventory")
    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemId == item.itemId &&
                Double.compare(item.avePrice, avePrice) == 0 &&
                inventory == item.inventory &&
                Objects.equals(itemName, item.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName, avePrice, inventory);
    }

    @Override
    public String toString() {
        return "Item{" +
                "avePrice=" + avePrice +
                ", itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}
