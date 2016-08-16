package fiftyfive.and_testfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

public class CartDetailsActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    Cart cart = new Cart();
    ListView mListView;
    ArrayList panier = new ArrayList<Item>();
    Bundle zeBundle = new Bundle();
    Intent zeIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_details);

        zeIntent = getIntent();

        Log.d("ACTION: ", "Bundle récupéré");
        Log.d("INFO: ", zeBundle.getString("screenName"));

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // send a hit to GA to log the screen name
        zeBundle.putString("screenName", "Cart details");
        mFirebaseAnalytics.logEvent("openScreen", zeBundle);
        Log.d("TAG: ", "openScreen - Cart details sent.");
        Log.d("INFO: ", zeBundle.getString("screenName"));

        //Récupérer  la listView
        mListView = (ListView) findViewById(R.id.listView);
        //remplir la liste avec le panier
        for (int i=0; i<cart.getNumberOfArticles(); i++){
            panier.add(cart.getItem(i).name);
        }
        //afficjer la liste
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CartDetailsActivity.this, android.R.layout.simple_list_item_1, panier);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(CartDetailsActivity.this,simple_list_item_1, panier.toString());
        mListView.setAdapter(adapter);

    }
}
