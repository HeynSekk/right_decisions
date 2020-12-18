package com.example.my_message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {
    //Button button2=findViewById(R.id.button2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        GridLayout gl=new GridLayout(this);
        int col=2,row=3;
        gl.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        gl.setColumnCount(col);
        gl.setRowCount(row+1);
        TextView tvTest;
        for(int i=0,c=0,r=0;i<6;i++,c++)
        {
            if(c==col)
            {
                c=0;
                r++;
            }
            tvTest=new TextView(this);
            tvTest.setText("This is me");
            gl.addView(tvTest,i);
            int rightIc = 0;
            tvTest.setCompoundDrawablesWithIntrinsicBounds(rightIc,0,0,0);
            GridLayout.LayoutParams lp=new GridLayout.LayoutParams();
            lp.height= GridLayout.LayoutParams.WRAP_CONTENT;
            lp.width=GridLayout.LayoutParams.WRAP_CONTENT;
            lp.rightMargin=5;
            lp.topMargin=5;
            lp.setGravity(Gravity.CENTER);
            lp.columnSpec=GridLayout.spec(r);
            tvTest.setLayoutParams(lp);
        }
        this.setContentView(gl);

    }
}
