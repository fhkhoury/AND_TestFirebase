package fiftyfive.and_testfirebase;

import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static com.google.firebase.crash.FirebaseCrash.*;
import com.google.firebase.crash.FirebaseCrash;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    Spinner catalogue;
    Cart cart = new Cart();
    public final static String SUPERBUNDLE = "DataLayer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // send a hard-coded hit to FB when the app is opened
        Bundle bundle = new Bundle();
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);

        Log.d("TAG: ", "APP_OPEN sent.");

        // send a hit to GA to log the screen name
        final Bundle bundle1 = new Bundle();
        bundle1.putString(FirebaseAnalytics.Param.VALUE, "HomePage");
        bundle1.putString("screenName", "HomePage");
        mFirebaseAnalytics.logEvent("openScreen", bundle1);
        Log.d("TAG: ", "openScreen - HomePage sent.");

        //checkons ce qu'il se passe dans le data layer
        Log.d("GUIGUI : ", bundle1.getString("screenName"));
        bundle1.putString("screenName","Blabla");
        Log.d("GUIGUI2 : ", bundle1.getString("screenName"));


        //Récupération du Spinner déclaré dans le fichier main.xml de res/layout
        catalogue = (Spinner) findViewById(R.id.spinner);
        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        List exempleList = new ArrayList();
        exempleList.add("Playstation - 400€");
        exempleList.add("X-Box - 300€");
        exempleList.add("PSP - 100€");

		/*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
		Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, exempleList );

        /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        catalogue.setAdapter(adapter);

        /*Button addToCart = (Button) findViewById(R.id.button_cliCkCart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewCart(bundle1, cart);
            }
        });*/

    }

    /*méthode pour faire partir un event après avoir cliqué sur un bouton
    //event codé en dur et envoyé sur Firebase
    public void click2Fire_hard(View v) {
        Bundle myBundle = new Bundle();
        mFirebaseAnalytics.logEvent("click2Fire_hard", myBundle);
    }*/



    //méthode pour faire partir un event après avoir cliqué sur un bouton
    //event codé pour utilisation via GTM et envoyé sur Firebase & GA
    public void click2Fire_GTM(View v) {
        Bundle thisBundle = new Bundle();
        thisBundle.putString("eventCategory", "clic");
        thisBundle.putString("eventAction", "fire");
        thisBundle.putString("eventLabel", "click2Fire_GTM");
        mFirebaseAnalytics.logEvent("click2Fire_GTM", thisBundle);
        Log.d("TAG: ", "Click2FIre_GTM sent.");
        Toast.makeText(getApplicationContext(), "Click2FIre_GTM sent.", Toast.LENGTH_SHORT).show();


    }

    // Make a crash
    public void crash(View v){
        report(new Exception("My first Android non-fatal error"));
        FirebaseCrash.log("App has crashed");
        Log.d("CRASH: ", "App has crashed, Buddy!");
        Toast.makeText(getApplicationContext(), "App has crashed, Buddy!", Toast.LENGTH_SHORT).show();
    }

    //Add to Cart
    public void addToCart(View v){
        String selectedItemName = catalogue.getSelectedItem().toString();
        Item item = new Item();
        item.getItemDetails(selectedItemName);
        //cart.addItem(item);
        // send a hard-coded hit to FB when the app is opened
        Bundle bundle = new Bundle();
        //Remplir le bundle avec les variables pour eeng
        bundle.putString(FirebaseAnalytics.Param.PRODUCT_ID, "123456");
        bundle.putString(FirebaseAnalytics.Param.PRODUCT_NAME, selectedItemName);
        bundle.putString(FirebaseAnalytics.Param.PRODUCT_CATEGORY, "Console");
        bundle.putDouble(FirebaseAnalytics.Param.PRICE, 399.99);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, bundle);
        //Log.d("INFO: ", item.sku);
        //Log.d("INFO: ", item.name);
        //Log.d("INFO: ", item.category);
        //Log.d("INFO: ", item.price);
        Log.d("TAG: ", "ADD_TO_CART sent.");
        Log.d("ACTION: ", selectedItemName +" has been added to cart.");
        Toast.makeText(getApplicationContext(), selectedItemName +" has been added to cart.", Toast.LENGTH_SHORT).show();
    }


    //View your cart
    public void viewCart (Bundle bundle, Cart cart){
        Intent intent = new Intent(MainActivity.this, CartDetailsActivity.class);
        intent.putExtra(SUPERBUNDLE, bundle); // pour passer le data layer
        //intent.putExtra("cart", (Parcelable) cart.listeProduits);//pour passer le cart à envoyer
        startActivityForResult(intent, 0);
        Log.d("INFO: ", bundle.getString("screenName"));
    }

    public String getPhoneNumber() {
        TelephonyManager tMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        return tMgr.getLine1Number();

    }


    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
