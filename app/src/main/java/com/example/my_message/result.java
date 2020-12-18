package com.example.my_message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import static com.example.my_message.getting_inputs.EXTRA_MESSAGE;
import static com.example.my_message.getting_inputs.hierarchy_main;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        String message =intent.getStringExtra(EXTRA_MESSAGE);

    }
    @Override
    protected void onResume() {

        super.onResume();
        int numalt=hierarchy_main.getNb_alternatives();
        double piValue=0;
        String display="";
        ScrollView sv=new ScrollView(this);
        final LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        TextView title=new TextView(this);
        TextView[] result_array=new TextView[numalt];
        TextView forShow=new TextView(this);
        sv.addView(ll);
        display=" The results are:";
        title.setText(display);
        title.setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_Large);
        ll.addView(title);
        for(int i=0;i<numalt;i++)
        {
            Alternative bestAltName=(Alternative) hierarchy_main.getAlternatives().get(i);
            piValue=hierarchy_main.Pi(i);
            String name=String.valueOf(piValue);
            name=bestAltName.getName()+" : "+name;
            result_array[i]=new TextView(this);
            result_array[i].setText(name);
            result_array[i].setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_SearchResult_Title);
            result_array[i].setPadding(60,60,16,0);
            ll.addView(result_array[i]);
        }
        int bestAlt=hierarchy_main.bestAlternative();
        Alternative bestAltName=(Alternative) hierarchy_main.getAlternatives().get(bestAlt);
        display=bestAltName.getName();
        display+=" is best choice.";

        forShow.setText(display);
        forShow.setTextAppearance(getApplicationContext(),R.style.TextAppearance_AppCompat_SearchResult_Title);
        forShow.setPadding(60,60,16,0);
        ll.addView(forShow);
        this.setContentView(sv);
    }
    /*@Override
    protected void onPause() {

        super.onPause();
        Vector v=new Vector();
        hierarchy_main.getGoal().setSons(null);
        int nalt=hierarchy_main.getNb_alternatives();
        for(int i=0;i<nalt;i++)
        {
            Alternative alt=(Alternative) hierarchy_main.getAlternatives().get(i);
            hierarchy_main.delAlternative(alt);
        }
        for(int i=0;i<numCri;i++)
        {
            Criterium cri=(Criterium) hierarchy_main.getGoal().getSons().get(i);
            hierarchy_main.delCriterium(cri);
        }
    }*/
}
