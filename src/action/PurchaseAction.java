package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Item;
import entity.Order;
import entity.Plan;
import entity.Provider;
import entity.Provideritem;
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

    //显示采购需求单的详细信息
    public String requestInfo(){
        service = new IPurchaseServiceImpl() ;
        User user = (User) ActionContext.getContext().getSession().get("user") ;

        ArrayList<Request> checkList = service.getCheckList(user.getUserId()) ;
        ArrayList<Order> orderList = service.getOrderList(user.getUserId()) ;
        Map pragram = ActionContext.getContext().getParameters() ;
        String requestID = ((String[]) pragram.get("requestID"))[0];
        Request check = service.getRequestByID(requestID) ;
        ActionContext.getContext().put("check",check);
        ActionContext.getContext().put("checkList",checkList);
        ActionContext.getContext().put("orderList",orderList);

        return SUCCESS ;
    }

    //处理审核
    public String handle(){

        service = new IPurchaseServiceImpl() ;
        User user = (User) ActionContext.getContext().getSession().get("user");
        Map pragram = ActionContext.getContext().getParameters() ;

        String requestID = ((String[]) pragram.get("requestID"))[0];
        String status = ((String[]) pragram.get("status"))[0];
        String reason = ((String[]) pragram.get("reason"))[0];
        String userID = user.getUserId() ;

        boolean flag = service.handle(requestID,status,reason,userID) ;

        if (flag){
            return SUCCESS ;
        }
        return ERROR ;

    }

    //初始化计划采购页面
    public String initPlanHandle(){

        service = new IPurchaseServiceImpl() ;
        User user = (User) ActionContext.getContext().getSession().get("user") ;

        ArrayList<Request> checkList = service.getCheckList(user.getUserId()) ;
        ArrayList<Order> orderList = service.getOrderList(user.getUserId()) ;
        Map pragram = ActionContext.getContext().getParameters() ;
        String planID = ((String[]) pragram.get("planID"))[0];
        Plan plan = service.getPlanByID(planID) ;

        String itemID = plan.getItem().getItemId().toString();
        ArrayList<Provideritem> provideritems = service.getItemProviders(itemID) ;

        ActionContext.getContext().put("provideritems",provideritems);

        ActionContext.getContext().put("checkList",checkList);
        ActionContext.getContext().put("orderList",orderList);
        ActionContext.getContext().put("plan",plan);

        return SUCCESS ;
    }

    //进行采购处理
    public String planHandle(){
        service = new IPurchaseServiceImpl() ;
        User user = (User) ActionContext.getContext().getSession().get("user") ;

        ArrayList<Request> checkList = service.getCheckList(user.getUserId()) ;
        ArrayList<Order> orderList = service.getOrderList(user.getUserId()) ;
        Map pragram = ActionContext.getContext().getParameters() ;
        String planID = ((String[]) pragram.get("planID"))[0];
        String provideritemID = ((String[]) pragram.get("provideritemID"))[0];
        Order order = service.makeOrder(planID,user.getUserId(),provideritemID) ;

        ActionContext.getContext().put("checkList",checkList);
        ActionContext.getContext().put("orderList",orderList);
        ActionContext.getContext().put("order",order);

        return SUCCESS ;
    }

    //初始化供应商详细信息
    public String providerInfo(){
        service = new IPurchaseServiceImpl() ;

        Map pragram = ActionContext.getContext().getParameters() ;
        String providerID = ((String[]) pragram.get("providerID"))[0];
        Provider provider = null;
        ArrayList<Provideritem> provideritems = service.getProvideritemByProviderID(providerID)  ;

        if (provideritems == null){
            return INPUT ;
        }
        provider = service.getProviderByID(providerID);
        int countA = 0 ;
        int countB = 0 ;
        int countC = 0 ;
        for (Provideritem provideritem : provideritems ){
            if ("A".equals(provideritem.getQuality())){
                countA++ ;
            }
            if ("B".equals(provideritem.getQuality())){
                countB++ ;
            }
            if ("C".equals(provideritem.getQuality())){
                countC++ ;
            }
        }

        ActionContext.getContext().put("countA",countA);
        ActionContext.getContext().put("countB",countB);
        ActionContext.getContext().put("countC",countC);

        ActionContext.getContext().put("provider",provider);
        ActionContext.getContext().put("providerItems",provideritems);

        return SUCCESS ;
    }

    //初始化供应商修改页面
    public String initProviderModify(){
        service = new IPurchaseServiceImpl() ;

        Map pragram = ActionContext.getContext().getParameters() ;
        String providerID = ((String[]) pragram.get("providerID"))[0];
        Provider provider = null;
        ArrayList<Provideritem> provideritems = service.getProvideritemByProviderID(providerID)  ;

        if (provideritems == null){
            return INPUT ;
        }
        provider = service.getProviderByID(providerID);
        int countA = 0 ;
        int countB = 0 ;
        int countC = 0 ;
        for (Provideritem provideritem : provideritems ){
            if ("A".equals(provideritem.getQuality())){
                countA++ ;
            }
            if ("C".equals(provideritem.getQuality())){
                countC++ ;
            }
            if ("B".equals(provideritem.getQuality())){
                countB++ ;
            }

        }
        ActionContext.getContext().put("countA",countA);
        ActionContext.getContext().put("countB",countB);
        ActionContext.getContext().put("countC",countC);

        ActionContext.getContext().put("provider",provider);

        return SUCCESS ;
    }

    //修改供应商信息
    public String providerModify(){
        service = new IPurchaseServiceImpl() ;

        Map pragram = ActionContext.getContext().getParameters() ;
        String providerID = ((String[]) pragram.get("providerID"))[0];
        String providerName = ((String[]) pragram.get("providerName"))[0];
        String provinces = ((String[]) pragram.get("provinces"))[0];
        String contant = ((String[]) pragram.get("contant"))[0];
        String tele = ((String[]) pragram.get("tele"))[0];
        String address = ((String[]) pragram.get("address"))[0];
        boolean flag = service.modifyProvider(providerID,providerName,provinces,contant,tele,address) ;
        if (flag){
            return SUCCESS ;
        }
        return ERROR ;
    }

    //删除供应商
    public String providerDel(){
        service = new IPurchaseServiceImpl() ;

        Map pragram = ActionContext.getContext().getParameters() ;
        String providerID = ((String[]) pragram.get("providerID"))[0];

        boolean flag = service.delProvider(providerID) ;

        if (flag){
            return SUCCESS ;
        }
        return ERROR ;
    }

    //初始化增加供应商
    public String initProviderAdd(){

        service = new IPurchaseServiceImpl() ;

        ArrayList<Provider> suppliers = service.getProviders() ;

        Map<String,Integer> classal = new HashMap<>() ;

        for (Provider pro : suppliers){
            Integer count = classal.get(pro.getProvinces()) ;
            if (count == null){
                count = new Integer(1) ;
                classal.put(pro.getProvinces(),count) ;
            }else {
                count++ ;
                classal.put(pro.getProvinces(),count) ;
            }
        }

        ActionContext.getContext().put("classal",classal);


        return SUCCESS ;
    }

    //增加供应商
    public String providerAdd(){
        service = new IPurchaseServiceImpl() ;

        Map pragram = ActionContext.getContext().getParameters() ;
        String providerName = ((String[]) pragram.get("providerName"))[0];
        String provinces = ((String[]) pragram.get("provinces"))[0];
        String contant = ((String[]) pragram.get("contant"))[0];
        String tele = ((String[]) pragram.get("tele"))[0];
        String address = ((String[]) pragram.get("address"))[0];

        boolean flag =service.addProvider(providerName,provinces,contant,tele,address) ;
        if (flag){
            return SUCCESS ;
        }
        return ERROR ;
    }

    //删除指定供应商的编号
    public String providerItemDel(){
        service = new IPurchaseServiceImpl() ;

        Map pragram = ActionContext.getContext().getParameters() ;
        String providerItemID = ((String[]) pragram.get("providerItemID"))[0];

        boolean flag = service.delProviderItem(providerItemID) ;
        if (flag){
            return SUCCESS ;
        }
        return ERROR ;
    }

    //修改指定供应商供应商品的信息
    public String initProvideItemModify(){
        service = new IPurchaseServiceImpl() ;

        Map pragram = ActionContext.getContext().getParameters() ;
        String providerItemID = ((String[]) pragram.get("providerItemID"))[0];

        Provideritem provideritem = service.getProviderItemByID(providerItemID) ;
        ArrayList<Provideritem> provideritems = service.getProvideritemByProviderID(provideritem.getProvider().getProviderId().toString());  ;

        if (provideritems == null){
            return INPUT ;
        }
        int countA = 0 ;
        int countB = 0 ;
        int countC = 0 ;
        for (Provideritem p : provideritems ){
            if ("C".equals(p.getQuality())){
                countC++ ;
            }
            if ("B".equals(p.getQuality())){
                countB++ ;
            }
            if ("A".equals(p.getQuality())){
                countA++ ;
            }
        }
        ActionContext.getContext().put("countA",countA);
        ActionContext.getContext().put("countB",countB);
        ActionContext.getContext().put("countC",countC);
        ActionContext.getContext().put("provideritem",provideritem);

        return SUCCESS ;
    }

    //修改指定供应商供应商品的信息
    public String providerItemModify(){
        service = new IPurchaseServiceImpl() ;

        Map pragram = ActionContext.getContext().getParameters() ;
        String providerItemID = ((String[]) pragram.get("providerItemID"))[0];
        String price = ((String[]) pragram.get("price"))[0];
        String quality = ((String[]) pragram.get("quality"))[0];

        boolean flag = service.modifyProviderItem(providerItemID,price,quality) ;

        if (flag){
            return SUCCESS ;
        }

        return ERROR ;
    }

    //初始化修改增加供应商信息页面
    public String initProviderItemAdd(){
        service = new IPurchaseServiceImpl() ;

        Map pragram = ActionContext.getContext().getParameters() ;
        String providerID = ((String[]) pragram.get("providerID"))[0];
        ArrayList<Provideritem> provideritems = service.getProvideritemByProviderID(providerID);  ;

        if (provideritems == null || provideritems.size() == 0){
            return INPUT ;
        }
        int countA = 0 ;
        int countB = 0 ;
        int countC = 0 ;
        for (Provideritem p : provideritems ){
            if ("C".equals(p.getQuality())){
                countC++ ;
            }
            if ("A".equals(p.getQuality())){
                countA++ ;
            }
            if ("B".equals(p.getQuality())){
                countB++ ;
            }
        }
        Provider provider = provideritems.get(0).getProvider() ;
        ActionContext.getContext().put("countA",countA);
        ActionContext.getContext().put("countB",countB);
        ActionContext.getContext().put("countC",countC);
        ActionContext.getContext().put("provider",provider);


        return SUCCESS ;
    }

    //添加供应商商品信息
    public String providerItemAdd(){
        service = new IPurchaseServiceImpl() ;

        Map pragram = ActionContext.getContext().getParameters() ;
        String providerID = ((String[]) pragram.get("providerID"))[0];
        String itemName = ((String[]) pragram.get("itemName"))[0];
        String price = ((String[]) pragram.get("price"))[0];
        String quality = ((String[]) pragram.get("quality"))[0];

        boolean flag = service.addProviderItem(providerID,itemName,price,quality) ;

        if (flag){
            return SUCCESS ;
        }
        return ERROR ;
    }


    //跳转到通知页面
    public String notice(){
        service = new IPurchaseServiceImpl() ;
        User user = (User) ActionContext.getContext().getSession().get("user") ;

        ArrayList<String> checkNotice = service.getCheckNotices() ;

        boolean flag =  service.creatrPlan() ;
        ArrayList<String> needPlans = service.getPurchaseNotices() ;
        int count = checkNotice.size() + needPlans.size() ;
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
                sumOfMoney += order.getTotalCost() ;
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

    //查看订单的详细信息
    public String moreOrderInfo(){
        service = new IPurchaseServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;
        ArrayList<Order> orderList = service.getOrderList(user.getUserId()) ;
        Map pragram = ActionContext.getContext().getParameters() ;
        String orderID = ((String[]) pragram.get("orderID"))[0];
        Order arder = null ;
        int countOfTime = 0 ;
        int count = 0 ;
        double sumOfMoney = 0 ;
        for (Order o : orderList){
            if (o.getOrderTime().after(DateUtil.getStartOfMonth())){
                countOfTime++ ;
                sumOfMoney += o.getTotalCost() ;
            }
            if (o.getOrderId().equals(new Integer(orderID))){
                arder = o ;
            }
            count++ ;
        }

        ActionContext.getContext().put("arder",arder);
        ActionContext.getContext().put("sumOfMoney",sumOfMoney);
        ActionContext.getContext().put("countOfTime",countOfTime);
        ActionContext.getContext().put("count",count);
        if (arder == null){
            return INPUT ;
        }
        return SUCCESS ;
    }

    //查看审核的详细信息
    public String moreCheckInfo(){
        service = new IPurchaseServiceImpl() ;
        User user = (User) ActionContext.getContext().getSession().get("user") ;
        ArrayList<Request> checkList = service.getCheckList(user.getUserId()) ;
        Map pragram = ActionContext.getContext().getParameters() ;
        String requestID = ((String[]) pragram.get("requestID"))[0];
        int countOfTime = 0 ;
        double sumOfMoney = 0 ;
        int count = 0 ;
        Date start = DateUtil.getStartOfMonth() ;
        Request ch = new Request() ;
        for (Request check : checkList ){
            if (check.getAuditTime().after(start)){
                countOfTime++ ;
                sumOfMoney += check.getTotalCost() ;
            }
            if (check.getRequestId().equals(new Integer(requestID))){
                ch = check ;
            }
            count ++ ;
        }

        ActionContext.getContext().put("check",ch);
        ActionContext.getContext().put("count",count);
        ActionContext.getContext().put("countOfTime",countOfTime);
        ActionContext.getContext().put("sumOfMoney",sumOfMoney);
        return SUCCESS ;
    }


}
