package fiftyfive.and_testfirebase;

import android.content.Intent;
import android.widget.SimpleExpandableListAdapter;

/**
 * Created by Francois on 12/08/2016.
 */
public class Item {

    String sku;
    String name;
    String category;
    String brand;
    String variant;
    double price;

    public Item getItemDetails(String itemName){
        Item item = new Item();
        switch (itemName) {
            case "Playstation - 400€":
                item.sku = "1234";
                item.name = itemName;
                item.category="console";
                item.brand = "Sony";
                item.variant="classic";
                item.price=399.99;
                break;
            case "X-Box - 300€":
                item.sku = "5678";
                item.name = itemName;
                item.category="console";
                item.brand = "Microsoft";
                item.variant="30 Go";
                item.price=295.99;
                break;
            case "PSP - 100€":
                item.sku = "1357";
                item.name = itemName;
                item.category="console";
                item.brand = "Sony";
                item.variant="Street version";
                item.price=99.99;
                break;
        }
        return item;
    }

}
