package fiftyfive.and_testfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessagingService;

import static fiftyfive.and_testfirebase.MyFirebaseMessagingService.*;

public class MainActivity extends AppCompatActivity {

	private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
		mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


		// send a hit
        Bundle bundle = new Bundle();
		mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);

		


        //Send a notification
        //MyFirebaseMessagingService.sendNotification("Mon MEssage");
        
    }

    //méthode pour faire partir un event après avoir cliqué sur un bouton
    public void clickToFire(){
        Bundle myBundle = new Bundle();
    	mFirebaseAnalytics.logEvent("click2Fire", myBundle);
    }

}
