package bean;

import entity.Export;
import entity.Import;
import entity.Item;

import java.util.ArrayList;

/**
 * Description: 获取采购部门需要的相关数据
 * Author: silence
 * Update: silence(2016-05-01 16:40)
 */
public class stockManBean {

    //获取物品的相关信息
    public ArrayList<Item> getItemsInfo(){
        return null ;
    }

    //获取入库列表
    public ArrayList<Import> getImportList(String stockManID){
        return null ;
    }

    //获取出库列表
    public ArrayList<Export> getExportList(String stockManID){
        return null ;
    }

    //获取一月内入库次数
    public int getcountOfImport(String stockManID){
        return 0 ;
    }

    //获取一月内入库次数
    public int getCountOfExport(String stockManID){
        return 0 ;
    }
}
