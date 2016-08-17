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
        bundle1.putString(FirebaseAnalytics.Param.VALUE, "HomePage");// TODO: Check si ça fonctionne
        bundle1.putString("screenName", "HomePage");
        mFirebaseAnalytics.logEvent("openScreen", bundle1);
        Log.d("TAG: ", "openScreen - HomePage sent.");

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

    //View your cart
    public void viewCart (View v) {
        Intent intent = new Intent(MainActivity.this, CartDetailsActivity.class);
        //intent.putExtra("cart", (Parcelable) cart.listeProduits);//pour passer le cart à envoyer // TODO: C'est la classe cart details qui va récupérer le panier
        startActivity(intent);
    }

    //View product list
    public void viewProductsList(View v){
        Intent zeIntent = new Intent(MainActivity.this, ProductsListActivity.class);
        startActivity(zeIntent);
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
