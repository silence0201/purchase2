package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Item;
import entity.Order;
import entity.Provider;
import entity.Request;
import entity.User;
import service.IPurchaseService;
import service.impl.IPurchaseServiceImpl;
import util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    //跳转到通知页面
    public String notice(){
        service = new IPurchaseServiceImpl() ;
        User user = (User) ActionContext.getContext().getSession().get("user") ;

        ArrayList<String> checkNotice = service.getCheckNotices() ;
        int count = checkNotice.size() ;
        boolean flag =  service.creatrPlan() ;
//        System.out.println(flag);
        ArrayList<String> needPlans = service.getPurchaseNotices() ;
        ArrayList<Request> checkList = service.getCheckList(user.getUserId()) ;
        ArrayList<Order> orderList = service.getOrderList(user.getUserId()) ;

        ActionContext.getContext().put("checkNotice",checkNotice) ;
        ActionContext.getContext().getSession().put("count",count) ;
        ActionContext.getContext().put("needPlans",needPlans);
        ActionContext.getContext().put("checkList",checkList);
        ActionContext.getContext().put("orderList",orderList);

        return SUCCESS ;
    }

    //跳转到供应商管理页面
    public String supplier(){
        service = new IPurchaseServiceImpl() ;

        ArrayList<Provider> suppliers = service.getProviders() ;


        Map<String,Integer> classal = new HashMap<>() ;
        for (Provider pro : suppliers){
            Integer count = classal.get(pro.getProvinces()) ;
            if (count == null){
                count = new Integer(0) ;
                count++ ;
                classal.put(pro.getProvinces(),count) ;
            }else {
                count++ ;
                classal.put(pro.getProvinces(),count) ;
            }
        }

        ActionContext.getContext().put("suppliers",suppliers);
        ActionContext.getContext().put("classal",classal);

        return SUCCESS ;
    }

    //跳转到采购列表页面
    public String list(){
        service = new IPurchaseServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;
        ArrayList<Order> orderList = service.getOrderList(user.getUserId()) ;
        int countOfTime = 0 ;
        int count = 0 ;
        double sumOfMoney = 0 ;
        Date start = DateUtil.getStartOfMonth() ;
        for (Order order : orderList){
            if (order.getOrderTime().after(start)){
                countOfTime++ ;
                sumOfMoney += order.getPlan().getTotalCost() ;
            }
            count++ ;
        }

        ActionContext.getContext().put("orderList",orderList);
        ActionContext.getContext().put("sumOfMoney",sumOfMoney);
        ActionContext.getContext().put("countOfTime",countOfTime);
        ActionContext.getContext().put("count",count);

        return SUCCESS ;
    }

    //跳转到审查查看页面
    public String check(){
        service = new IPurchaseServiceImpl() ;
        User user = (User) ActionContext.getContext().getSession().get("user") ;
        ArrayList<Request> checkList = service.getCheckList(user.getUserId()) ;
        int countOfTime = 0 ;
        double sumOfMoney = 0 ;
        int count = 0 ;
        Date start = DateUtil.getStartOfMonth() ;
        for (Request check : checkList ){
            if (check.getAuditTime().after(start)){
                countOfTime++ ;
                sumOfMoney += check.getTotalCost() ;
            }
            count ++ ;
        }

        ActionContext.getContext().put("checkList",checkList);
        ActionContext.getContext().put("count",count);
        ActionContext.getContext().put("countOfTime",countOfTime);
        ActionContext.getContext().put("sumOfMoney",sumOfMoney);

        return SUCCESS ;
    }

}
