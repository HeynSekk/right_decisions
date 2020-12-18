package com.example.my_message;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;



public class DisplayMessageactivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_messageactivity2);

        // Get the Intent that started this activity and extract the string
        /*Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String i="This is me";
        TextView textView = findViewById(R.id.textView2);
        if(extras!=null)
        {
            String j =(String) extras.get(EXTRA_MESSAGE);
            textView.setText(j);
        }
        else
        {
            textView.setText(i);
        }*/

        // Capture the layout's TextView and set the string as its text

    }
}
