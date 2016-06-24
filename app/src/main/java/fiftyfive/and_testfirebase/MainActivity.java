package fiftyfive.and_testfirebase;

import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        Button sendEvent = (Button)findViewById(R.id.button_sendEvent);
        if (sendEvent != null)
            sendEvent.setOnClickListener(eventSender);

        //Send a notification
        //MyFirebaseMessagingService.sendNotification("Mon MEssage");
    }

    View.OnClickListener eventSender = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "eventSender");
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "button");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        }
    };

}
