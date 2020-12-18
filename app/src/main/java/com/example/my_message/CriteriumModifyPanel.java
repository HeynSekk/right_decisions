package com.example.my_message;
public class CriteriumModifyPanel {
    private Hierarchy h;
    private Criterium c;
    private int size;

    public CriteriumModifyPanel(Criterium c, Hierarchy h) {
        super();
        this.c = c;
        this.h = h;


        if (c != null) {
            int n = c.getNb_sons();
            this.size = n;
            //System.out.println("NB SONS"+n);
            n = n * (n - 1) / 2;
            //System.out.println("NB PairwiseComparison"+n);
            for (int i = 0; i < c.getNb_sons(); i++) {
                for (int j = i + 1; j < c.getNb_sons(); j++) {
                    int val = 0;
                    //System.out.println("comparison value"+val);
                    PairwiseComparisonMatrix p = c.getP();
                    if (val == 0) {
                        p.set(i, j, 1);
                    } else if (val < 0) {
                        p.set(i, j, 1.0 / (-val + 1.0));
                    } else if (val > 0) {
                        p.set(i, j, (val + 1));
                    }
                }
            }
        }
    }
}
