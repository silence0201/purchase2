package test;

import dao.ItemDao;
import dao.impl.ItemDaoImpl;
import entity.Item;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Description: ItemTest
 * Author: silence
 * Update: silence(2016-05-06 22:32)
 */
public class ItemTest {
    public static void main(String[] args) {
        ItemDao itemDao = new ItemDaoImpl() ;

        ArrayList<Item> items = itemDao.itemInfo() ;

        Iterator<Item> itemIterator = items.iterator() ;

        while (itemIterator.hasNext()){
            Item item = itemIterator.next() ;
            System.out.println(item);
        }
    }
}
