package fiftyfive.and_testfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessagingService;

import static fiftyfive.and_testfirebase.MyFirebaseMessagingService.*;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Send a notification
        //MyFirebaseMessagingService.sendNotification("Mon MEssage");

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Button shareImg = (Button) findViewById(R.id.button_sendEvent);
        if (shareImg != null)
            shareImg.setOnClickListener(shareImgListener);

        Button sendEyeColor = (Button) findViewById(R.id.button_sendEyeColor);
        if (sendEyeColor != null)
            sendEyeColor.setOnClickListener(sendEyeColorListener);
    }

    View.OnClickListener shareImgListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText img_nameInput= (EditText)findViewById(R.id.editText);
            String img_name = img_nameInput.getText().toString();
            img_nameInput.setText("");

            Bundle params = new Bundle();
            params.putString("image_name", img_name);
            params.putString("full_text", "example of sharing an image");
            mFirebaseAnalytics.logEvent("share_image", params);
        }
    };

    View.OnClickListener sendEyeColorListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText eye_colorInput= (EditText)findViewById(R.id.editText2);
            String eye_color = eye_colorInput.getText().toString();
            eye_colorInput.setText("");

            mFirebaseAnalytics.setUserProperty("eye_color", eye_color);
        }
    };

}
