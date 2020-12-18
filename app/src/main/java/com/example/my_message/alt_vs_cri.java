package com.example.my_message;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import static com.example.my_message.getting_inputs.EXTRA_MESSAGE;
import static com.example.my_message.getting_inputs.hierarchy_main;

public class alt_vs_cri extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alt_vs_cri);
        Intent intent = getIntent();
        String message =intent.getStringExtra(EXTRA_MESSAGE);
    }
    @Override
    protected void onResume() {

        super.onResume();
        int n = hierarchy_main.getGoal().getNb_sons();
        int noAlt=hierarchy_main.getNb_alternatives();
        int noEt=noAlt * (noAlt - 1) / 2;
        noEt=noEt*n;
        ScrollView sv=new ScrollView(this);
        final LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        TextView title=new TextView(this);
        TextView subTitle=new TextView(this);
        View[] v=new View[n];
        TextView[] title_array=new TextView[noEt];
        TextView[] tv_array=new TextView[noEt];

        TextView[] cri_array=new TextView[n];
        final Spinner[] sp_array=new Spinner[noEt];
        sv.addView(ll);

        String forShow="";
        forShow="Let's compare alternatives with respect to criteria.";
        title.setText(forShow);
        title.setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_SearchResult_Title);
        title.setPadding(16,60,16,0);
        forShow="Select using drop-down menu.";
        subTitle.setText(forShow);
        subTitle.setPadding(16,10,0,0);
        ll.addView(title);
        subTitle.setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_Medium);
        ll.addView(subTitle);
        int count=0;
        forShow="";
        final String[] weights={"Extremely","Intermediate between","Very strongly","Intermediate between","Stron" +
                "gly","Intermediate between","Slightly","Intermediate between","Equally","Intermediate between","Slightly l" +
                "ess","Intermediate between","Strongly less" +
                "","Intermediate between","Very Strongly less","Intermediate between","Extremely less"};
        final int[] w_array={8,7,6,5,4,3,2,1,0,-1,-2,-3,-4,-5,-6,-7,-8};
        ArrayAdapter arrAdap=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,weights);
        for(int k=0;k<hierarchy_main.getGoal().getNb_sons();k++) {
            Criterium c=(Criterium) hierarchy_main.getGoal().getSubcriteriumAt(k);
            forShow=" With respect to #"+c.getName();
            cri_array[k]=new TextView(this);
            cri_array[k].setText(forShow);
            cri_array[k].setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_Large);
            cri_array[k].setPadding(16,60,16,30);
            ll.addView(cri_array[k]);

            //PairwiseComparisonMatrix p = hierarchy_main.getGoal().getSubcriteriumAt(k).getP();
            for (int i = 0; i < hierarchy_main.getNb_alternatives(); i++) {
                for (int j = i + 1; j < hierarchy_main.getNb_alternatives(); j++) {
                    Alternative a1=(Alternative) hierarchy_main.getAlternatives().get(i);
                    Alternative a2=(Alternative) hierarchy_main.getAlternatives().get(j);
                    forShow="\""+a1.getName()+"\" VS \""+a2.getName()+"\"";
                    title_array[count]=new TextView(this);
                    title_array[count].setText(forShow);
                    title_array[count].setPadding(16,30,16,10);
                    title_array[count].setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_SearchResult_Title);
                    ll.addView(title_array[count]);
                    forShow="(\""+a1.getName()+"\" is ....... favored over \""+a2.getName()+"\" in "+c.getName()+")";
                    tv_array[count]=new TextView(this);
                    tv_array[count].setText(forShow);
                    tv_array[count].setPadding(16,0,16,30);
                    tv_array[count].setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_Medium);
                    ll.addView(tv_array[count]);
                    sp_array[count]=new Spinner(this);
                    sp_array[count].setAdapter(arrAdap);
                    sp_array[count].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));


                    ll.addView(sp_array[count]);
                    count++;
                }
            }
            v[k]=new View(this);
            v[k].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,5));
            v[k].setBackgroundColor(Color.parseColor("#B3B3B3"));
            v[k].setPadding(16,30,16,30);
            ll.addView(v[k]);
        }
        Button doneBtn=new Button(this);
        doneBtn.setText("Next");
        doneBtn.getBackground().setColorFilter(ContextCompat.getColor(this,R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
        doneBtn.setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_Widget_Button_Inverse);

        ll.addView(doneBtn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count=0;
                for(int k=0;k<hierarchy_main.getGoal().getNb_sons();k++)
                {
                    PairwiseComparisonMatrix p = hierarchy_main.getGoal().getSubcriteriumAt(k).getP();
                    for (int i = 0; i < hierarchy_main.getNb_alternatives(); i++) {
                        for (int j = i + 1; j < hierarchy_main.getNb_alternatives(); j++) {

                            String str = sp_array[count].getSelectedItem().toString();
                            int val=sp_array[count].getSelectedItemPosition();
                            val=w_array[val];
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
                }
                Intent intent2 = new Intent(alt_vs_cri.this, result.class);
                intent2.putExtra(EXTRA_MESSAGE, "abc");
                startActivity(intent2);

            }
        });
        this.setContentView(sv);
    }
}
