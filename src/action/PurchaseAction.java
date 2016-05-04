package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Item;
import entity.Provider;
import service.IPurchaseService;

/**
 * Description: PurchaseAction
 * Author: silence
 * Update: silence(2016-05-01 18:09)
 */
public class PurchaseAction  extends ActionSupport {

    private IPurchaseService service ;

    private int planID ;
    private Provider provider ;
    private Item ites ;
    private String quality ;
    private double price ;

    public Item getItes() {
        return ites;
    }

    public void setItes(Item ites) {
        this.ites = ites;
    }

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public IPurchaseService getService() {
        return service;
    }

    public void setService(IPurchaseService service) {
        this.service = service;
    }

    //显示采购需求单的详细信息
    public String info(){
        return SUCCESS ;
    }

    //处理采购
    public String handle(){
        return SUCCESS ;
    }

    //初始化添加供应商
    public String initAddProvider(){
        return SUCCESS ;
    }

    //添加供应商
    public String addProvider(){
        return SUCCESS  ;
    }


    //删除供应商
    public String delProvider(){
        return SUCCESS ;
    }

    //初始化修改供应商页面
    public String initModifyProvider(){
        return SUCCESS ;
    }

    //修改供应商
    public String modifyProvider(){
        return SUCCESS ;
    }

    //初始化增加商品
    public String initAddProviderItem(){
        return SUCCESS ;
    }

    //增加商品
    public String addPrviderItem(){
        return SUCCESS ;
    }

    //删除商品
    public String delProviderItem(){
        return SUCCESS ;
    }

    //初始化修改商品信息
    public String initModifyProviderItem(){
        return SUCCESS ;
    }

    //修改商品信息
    public String modifyProviderItem(){
        return SUCCESS ;
    }


}
