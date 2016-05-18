package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Item;
import entity.Order;
import entity.Plan;
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
        Order order = service.makeOrder(planID,user.getUserId()) ;

        ActionContext.getContext().put("checkList",checkList);
        ActionContext.getContext().put("orderList",orderList);
        ActionContext.getContext().put("order",order);

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
                sumOfMoney += o.getPlan().getTotalCost() ;
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
