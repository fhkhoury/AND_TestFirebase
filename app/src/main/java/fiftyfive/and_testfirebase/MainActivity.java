package fiftyfive.and_testfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.messaging.FirebaseMessagingService;

import static fiftyfive.and_testfirebase.MyFirebaseMessagingService.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Send a notification
        //MyFirebaseMessagingService.sendNotification("Mon MEssage");
        
    }
}
