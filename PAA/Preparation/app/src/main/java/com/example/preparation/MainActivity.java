package com.example.preparation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
//    Handler handler;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.output = findViewById(R.id.result);
    }

    public void onClick(View view) {
//        this.handler = new Handler();
//
//        new Thread(() -> {
//            for (int i = 0; i <= 100; i++) {
//                final int progress = i;
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                //Update the value background thread to UI thread
//                handler.post(() -> output.setText(progress + ":"));
//            }
//        }).start();

//        AsyncTaskCustom asyncTask = new AsyncTaskCustom(this.output);
//        asyncTask.execute("https://www.tutorialspoint.com/images/tp-logo-diamond.png");
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            InputStream inputStream = getAssets().open("test.xml");
            parser.setInput(inputStream, null);
 
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();

                eventType=parser.next();
            }

        } catch (Exception e) {

        }
    }
}