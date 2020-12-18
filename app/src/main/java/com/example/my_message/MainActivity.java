package com.example.my_message;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static com.example.my_message.getting_inputs.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void start(View view) {
        Intent intent = new Intent(this, getting_inputs.class);
        intent.putExtra(EXTRA_MESSAGE, "abc");
        startActivity(intent);
    }
    public void howTU(View view) {
        Intent intent2 = new Intent(this, htu.class);
        intent2.putExtra(EXTRA_MESSAGE, "abc");
        startActivity(intent2);
    }
}
