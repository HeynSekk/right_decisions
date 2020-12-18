package com.example.my_message;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import static com.example.my_message.getting_inputs.EXTRA_MESSAGE;
import static com.example.my_message.getting_inputs.hierarchy_main;

public class subCri extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_cri);

        Intent intent = getIntent();
        String message =intent.getStringExtra(EXTRA_MESSAGE);

    }
    @Override
    protected void onResume() {

        super.onResume();
        String forShow="";
        int n=0;
        n=hierarchy_main.getGoal().getNb_sons();
        ScrollView sv=new ScrollView(this);
        final LinearLayout ll=new LinearLayout(this);
        final TextView warning=new TextView(this);
        TextView[] tv=new TextView[n];
        final LinearLayout[] llEach=new LinearLayout[n];
        EditText[] et=new EditText[n];
        Button[] btn=new Button[n];
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.setPadding(16,16,16,0);
        sv.addView(ll);
        forShow=" *Optional. You can skip this section if you don't have sub-criteria.";
        warning.setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_Medium);
        warning.setText(forShow);
        ll.addView(warning);
        final LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i=0;i<n;i++)
        {
            tv[i]=new TextView (this);
            Criterium c=(Criterium) hierarchy_main.getGoal().getSubcriteriumAt(i);
            forShow="What are sub-criteria for "+c.getName()+"?";
            tv[i].setText(forShow);
            tv[i].setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_SearchResult_Title);
            ll.addView(tv[i]);
            llEach[i]=new LinearLayout(this);
            llEach[i].setOrientation(LinearLayout.HORIZONTAL);
            et[i]=new EditText(this);
            et[i].setLayoutParams(lp);
            forShow="Enter a sub-criterion";
            et[i].setHint(forShow);
            //et[i].setPadding(26,26,26,0);
            btn[i]=new Button(this);
            btn[i].setText("Add");
            llEach[i].addView(et[i]);
            llEach[i].addView(btn[i]);
            ll.addView(llEach[i]);
        }
        for(int i=0;i<n;i++)
        {
            final int finalI = i;
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Criterium c = new Criterium();
                    c.setName("AA");
                    hierarchy_main.getGoal().getSubcriteriumAt(finalI).addSubcriterium(hierarchy_main.getGoal().getSubcriteriumAt(finalI), c);
                    //String str=hierarchy_main.getGoal().getSubcriteriumAt(finalI).getSubcriteriumAt(finalI).print();
                    //warning.setText(str);
                }
            });
        }

        this.setContentView(sv);

    }
}
