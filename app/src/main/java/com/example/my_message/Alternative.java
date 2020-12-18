// Abstract Data Type
package com.example.my_message;


//imports
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

//import com.sun.java.swing.*; //Used by JDK 1.2 Beta 4 and all
//Swing releases before Swing 1.1 Beta 3.


/**
 * Alternative class
 * @author  Maxime MORGE <A HREF="mailto:morge@emse.fr">morge@emse.fr</A>
 * @version Fev 13, 2003
 * @version March 26, 2003 final one
 * @version July 21, 2003
 */
public class Alternative extends Activity implements Serializable,Cloneable{

    //ATTRIBUTS

    //useful to have always a new criterium with a new name
    private static int newAlternativeSuffix = 1;


    /**
     * Creates a new  <code>Alternative</code> instance.
     *
     */
    public Alternative(){
        super();
        this.setName("My alternative"+newAlternativeSuffix++);
        try{
            this.setUrl(new URL("http://messel.emse.fr/~mmorge"));}
        catch(MalformedURLException e){
            System.err.println("Error : MalformedURLException"+e);
            System.exit(-1);
        }

    }
    public String print(){
        String s=new String();
        s=s+"Name of this alternative                  : "+getName()+"\n";
        s=s+"URL of this alternative                   : "+getUrl().toString()+"\n";
        return s;
    }
    public boolean equals(Alternative alt){
        return name.equals(alt.getName());
    }


    public double S(Criterium c,Hierarchy h){
        if (c.equals(h.getGoal()))
            throw new IllegalArgumentException("the influence of the goal on the alternative according to the hierarchy can not be calculated");
        int index_alt=0;
        Vector alts=h.getAlternatives();
        for(int i=0;i<h.getNb_alternatives();i++){
            Alternative alt=(Alternative) alts.get(i);
            if (this.equals(alt)) index_alt=i;
        }
        return(c.Jstar(index_alt)*h.V(c)/h.Pi(index_alt)  );
    }
}


  

