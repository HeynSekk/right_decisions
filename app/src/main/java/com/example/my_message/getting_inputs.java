package com.example.my_message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class getting_inputs extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.my_message.MESSAGE";
    String showMsg;
    String showMsg2;
    public static String lastAlt;
    public static String lastCri;
    public static int numCri;
    public static Hierarchy hierarchy_main;
    //Alternative temp_alternative;
    //Criterium temp_cri;
    void setShowMsg(String str)
    {
        this.showMsg=str;
    }
    void setShowMsg2(String str)
    {
        this.showMsg2=str;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_inputs);
        Intent intent = getIntent();
        String message =intent.getStringExtra(EXTRA_MESSAGE);


    }
    @Override
    protected void onResume() {
        super.onResume();
        hierarchy_main=new Hierarchy();
        hierarchy_main.getGoal().setSons(null);
        lastAlt="";
        lastCri="";
        setShowMsg("You added: ");
        setShowMsg2("You added: ");
        this.numCri=0;
        Button btn1=(Button) findViewById(R.id.button);
        Button btn2=(Button) findViewById(R.id.button3);
        Button btnDelete=(Button) findViewById(R.id.btnDelete);
        Button btnDelete2=(Button) findViewById(R.id.btnDelete2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alternative temp_alternative=new Alternative();
                EditText editText = (EditText) findViewById(R.id.editText);
                TextView youadd1=(TextView) findViewById(R.id.youadd1);
                String message = editText.getText().toString();
                temp_alternative.setName(message);
                boolean flag=false;
                int n=hierarchy_main.getNb_alternatives();
                n--;
                for(int i=0;i<n;i++)
                {
                    if(temp_alternative.equals((Alternative) hierarchy_main.getAlternatives().get(i)))
                    {
                        flag=true;
                        break;
                    }
                }

                //int n=hierarchy_main.getNb_alternatives();
                if(!message.isEmpty() && flag==false)
                {
                    editText.getText().clear();
                    hierarchy_main.addAlternative(temp_alternative);
                    showMsg+=message+" , ";
                    youadd1.setText(showMsg);
                    lastAlt=message;
                }

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = (EditText) findViewById(R.id.editText2);
                TextView youadd2=(TextView) findViewById(R.id.youadd2);
                Criterium temp_cri=new Criterium();
                String message = editText.getText().toString();
                temp_cri.setName(message);
                boolean flag2=false;
                int n=numCri-1;
                for(int i=0;i<n;i++)
                {
                    if(temp_cri.equals((Criterium)  hierarchy_main.getGoal().getSons().get(i)))
                    {
                        flag2=true;
                        break;
                    }
                }


                if(!message.isEmpty()&& flag2==false)
                {

                    editText.getText().clear();
                    numCri=numCri+1;
                    hierarchy_main.addSubcriterium(hierarchy_main.getGoal(),temp_cri,hierarchy_main.getAlternatives(),hierarchy_main.getNb_alternatives());
                    showMsg2+=message+" , ";
                    youadd2.setText(showMsg2);
                    lastCri=message;
                }

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int n=hierarchy_main.getNb_alternatives();
                n--;
                Alternative alt=(Alternative) hierarchy_main.getAlternatives().get(n);
                hierarchy_main.delAlternative(alt);
            }
        });
        btnDelete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int n=numCri-1;
                Criterium cri=(Criterium) hierarchy_main.getGoal().getSons().get(n);
                hierarchy_main.delCriterium(cri);
            }
        });

    }

    public void sendMessage3(View view) {
        Intent intent = new Intent(this, Comparison.class);
        intent.putExtra(EXTRA_MESSAGE, "abc");
        //TextView youadd1=(TextView) findViewById(R.id.youadd1);
        int n=hierarchy_main.getNb_alternatives();
        //int n2=hierarchy_main.getGoal().getSons().size();
        //Criterium cTest=(Criterium) hierarchy_main.getGoal().getSubcriteriumAt(0);
        if(n>1 && numCri>0){startActivity(intent);}

        //showMsg="noAlt is "+String.valueOf(n);
        //youadd1.setText(showMsg);



    }


}
