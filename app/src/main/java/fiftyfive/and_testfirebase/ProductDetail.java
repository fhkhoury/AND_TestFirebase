package fiftyfive.and_testfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

public class ProductDetail extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    Intent zeIntent = new Intent();
    Bundle zeBundle = new Bundle();
    Item itemSelected ;
    Cart cart = new Cart(); // pensez à récupérer le cart entre les activités
    Bundle firebaseTagBundle = new Bundle();
    //les textviews


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        //Retrieve the bundle parse from Product List and convert it to item
        zeIntent = getIntent();
        zeBundle = zeIntent.getBundleExtra("item");
        Log.d("INFO: ", "item récupéré.");
        itemSelected = new Item(zeBundle.getString("sku"), zeBundle.getString("name"), zeBundle.getString("category"), zeBundle.getString("brand"), zeBundle.getString("variant"), zeBundle.getDouble("price"));
        //Obtain the FirebaseAnalytics instance
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        // FIre the Firebase Analytics tag
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, itemSelected.sku);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
        Log.d("TAG: ", "VIEW_ITEM sent.");

        //les layouts
        LinearLayout zeLayout = (LinearLayout)findViewById(R.id.zeLayout);
        //les textviews
        TextView productName = (TextView)findViewById(R.id.name);
        assert productName != null;
        productName.setText(itemSelected.name);

        TextView productCategory = (TextView)findViewById(R.id.category);
        assert productCategory != null;
        productCategory.setText(itemSelected.category);

        TextView productVariant = (TextView)findViewById(R.id.variant);
        assert productVariant != null;
        productVariant.setText(itemSelected.variant);

        TextView productBrand = (TextView)findViewById(R.id.brand);
        assert  productBrand != null;
        productBrand.setText(itemSelected.brand);

        TextView productPrice = (TextView)findViewById(R.id.price);
        assert productPrice != null ;
        productPrice.setText(String.valueOf(itemSelected.price));


        Button buttonAddToCart = (Button)findViewById(R.id.button_addToCart);

        if (buttonAddToCart != null) {
            buttonAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cart.addItem(itemSelected);
                    firebaseTagBundle.putString(FirebaseAnalytics.Param.PRODUCT_ID, itemSelected.sku);
                    firebaseTagBundle.putString(FirebaseAnalytics.Param.PRODUCT_NAME, itemSelected.name);
                    firebaseTagBundle.putString(FirebaseAnalytics.Param.PRODUCT_CATEGORY, itemSelected.category);
                    firebaseTagBundle.putDouble(FirebaseAnalytics.Param.PRICE, itemSelected.price);
                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, firebaseTagBundle);
                    Toast.makeText(getApplicationContext(), itemSelected.name +" has been added to cart.", Toast.LENGTH_SHORT).show();
                    Log.d("TAG: ", "ADD_TO_CART sent.");
                }
            });
        }


        //if Cart non nul alors afficher le bouton View Cart
        if (cart != null){
            Button buttonViewCart = (Button)findViewById(R.id.button_viewCart);
            if (buttonViewCart != null) {
                //assert zeLayout != null;
                //zeLayout.addView(buttonViewCart, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                buttonViewCart.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        // TODO: A REVOIR
                        //Intent zeIntent = new Intent(ProductDetail.this, CartDetailsActivity.class);
                        //ArrayList<Bundle> zeBundle = cart.cart2Bundles(cart);
                        //zeIntent.putParcelableArrayListExtra("panier", zeBundle);
                        //startActivityForResult(zeIntent, 0);
                    }
                });
            }
        }
    }


}
