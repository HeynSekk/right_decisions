package com.example.my_message;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import static com.example.my_message.getting_inputs.EXTRA_MESSAGE;
import static com.example.my_message.getting_inputs.hierarchy_main;

public class Comparison extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison);
        Intent intent = getIntent();
        String message =intent.getStringExtra(EXTRA_MESSAGE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        int n = hierarchy_main.getGoal().getNb_sons();
        int k = n * (n - 1) / 2;
        ScrollView sv=new ScrollView(this);
        final LinearLayout ll=new LinearLayout(this);
        TextView title=new TextView(this);
        TextView subTitle=new TextView(this);
        TextView[] title_array=new TextView[k];
        TextView[] tv_array=new TextView[k];
        final Spinner[] sp_array=new Spinner[k];
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);
        int count=0;
        final String[] weights={"Extremely","Intermediate between","Very strongly","Intermediate between","Stron" +
                "gly","Intermediate between","Slightly","Intermediate between","Equally","Intermediate between","Slightly l" +
                "ess","Intermediate between","Strongly less" +
                "","Intermediate between","Very Strongly less","Intermediate between","Extremely less"};
        final int[] w_array={8,7,6,5,4,3,2,1,0,-1,-2,-3,-4,-5,-6,-7,-8};
        ArrayAdapter arrAdap=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,weights);
        String forShow="";
        forShow=" Let's compare criteria / standards.";
        title.setText(forShow);
        title.setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_SearchResult_Title);
        title.setPadding(16,60,16,0);
        forShow=" Select using drop-down menu.";
        subTitle.setText(forShow);
        subTitle.setPadding(16,16,0,0);

        ll.addView(title);
        subTitle.setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_Medium);
        ll.addView(subTitle);
        for (int i = 0; i < hierarchy_main.getGoal().getNb_sons(); i++) {
            for (int j = i + 1; j < hierarchy_main.getGoal().getNb_sons(); j++)
            {

                Criterium c1=(Criterium) hierarchy_main.getGoal().getSubcriteriumAt(i);
                Criterium c2=(Criterium) hierarchy_main.getGoal().getSubcriteriumAt(j);
                forShow="\""+c1.getName()+"\" VS \""+c2.getName()+"\"";
                title_array[count]=new TextView(this);
                title_array[count].setText(forShow);
                title_array[count].setPadding(16,30,16,10);
                title_array[count].setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_SearchResult_Title);
                ll.addView(title_array[count]);

                tv_array[count]=new TextView(this);
                forShow=" (\""+c1.getName()+"\" is ........ favored over \""+c2.getName()+"\" for the goal.)";
                tv_array[count].setText(forShow);
                tv_array[count].setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_Medium);
                tv_array[count].setPadding(16,0,16,0);
                sp_array[count]=new Spinner(this);

                sp_array[count].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                sp_array[count].setAdapter(arrAdap);

                ll.addView(tv_array[count]);
                ll.addView(sp_array[count]);
                count++;
            }
        }
        Button[] ba=new Button[2];
        ba[0]=new Button(this);
        ba[0].setText("Next");
        ba[0].getBackground().setColorFilter(ContextCompat.getColor(this,R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
        ba[0].setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_Widget_Button_Inverse);
        if (ba[0].getParent()!=null)
        {
            ((ViewGroup)ba[0].getParent()).removeView(ba[0]);
        }

        ll.addView(ba[0]);

        ba[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PairwiseComparisonMatrix p = hierarchy_main.getGoal().getP();
                int count=0;

                for (int i = 0; i < hierarchy_main.getGoal().getNb_sons(); i++) {
                    for (int j = i + 1; j < hierarchy_main.getGoal().getNb_sons(); j++) {


                        int val=sp_array[count].getSelectedItemPosition();
                        val=w_array[val];
                        //int val=Integer.parseInt(str);
                        if (val == 0) {
                            p.set(i, j, 1);
                        } else if (val < 0) {
                            p.set(i, j, 1.0 / (-val + 1.0));
                        } else if (val > 0) {
                            p.set(i, j, (val + 1));
                        }
                        count++;
                    }
                }
                Intent intent2 = new Intent(Comparison.this, alt_vs_cri.class);
                intent2.putExtra(EXTRA_MESSAGE, "abc");
                startActivity(intent2);

            }
        });
        this.setContentView(sv);


    }

}
