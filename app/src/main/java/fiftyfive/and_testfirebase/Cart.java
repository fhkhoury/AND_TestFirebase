package fiftyfive.and_testfirebase;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francois on 12/08/2016.
 */
public class Cart {

    Integer numberOfProducts;
    Double total;
    ArrayList<Item> listeProduits;

    public  Cart(){
        numberOfProducts=0;
        total=0.0;
        listeProduits = new ArrayList<Item>();
    }

    public void addItem(Item item){
        numberOfProducts = numberOfProducts+1 ;
        total = total + item.price;
        listeProduits.add(item);
    }

    public void deleteItem(){

    }

    public int getNumberOfArticles(){
        return numberOfProducts;
    }

    public Item getItem(int index){
        return (Item) listeProduits.get(index);
    }

    public ArrayList transformCartToArrayList(Cart cart){
        ArrayList panier = new ArrayList();
        for (int i=0; i<cart.getNumberOfArticles(); i++){
            panier.add(cart.getItem(i));
            Log.d("Array", panier.get(i).toString());
        }
        return panier;
    }

    public ArrayList<Bundle> cart2Bundles(Cart cart) {
        ArrayList<Bundle> cartBundled = new ArrayList<Bundle>();
        for (Integer i=0; i<cart.numberOfProducts; i++){
            cartBundled.add(cart.getItem(i).itemToBundle());
        }
        return cartBundled;
    }

}
