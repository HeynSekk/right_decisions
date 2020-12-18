package com.example.my_message;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.my_message.getting_inputs.EXTRA_MESSAGE;

public class htu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htu);
        Intent intent = getIntent();
        String message =intent.getStringExtra(EXTRA_MESSAGE);
    }
}
