package libffm;

import java.util.ArrayList;
import static java.lang.Math.ceil;
import static java.lang.Math.round;
import static java.lang.StrictMath.exp;
import static libffm.Constants.kALIGN;

public class Ffm {

    public static int get_k_aligned(int k) {return (int) round(ceil((float)k / kALIGN) * kALIGN); }

    public static float wTx(ArrayList<Ffm_node> node, float r, Ffm_model model) {
        float t = 0;
        int align0 = 2 * get_k_aligned(model.k);
        int align1 = model.m * align0;
        for (int x = 0; x < node.size();x ++) {
            int j1 = node.get(x).getJ();
            int f1 = node.get(x).getF();
            float v1 = node.get(x).getV();
            if (j1 >= model.n || f1 >= model.m)
                continue;

            for (int y = x+1; y < node.size(); y++) {
                int j2 = node.get(y).getJ();
                int f2 = node.get(y).getF();
                float v2 = node.get(y).getV();
                if (j2 >= model.n || f2 >= model.m)
                    continue;
                float v = v1 * v2 * r;
                int w1 = j1 * align1 + f2 * align0;
                int w2 = j2 * align1 + f1 * align0;

                for(int d = 0; d < align0; d += kALIGN * 2) {
                    t += model.W[w1+d] * model.W[w2+d] * v;
                }
            }

        }
        return t;
    }



    public static ArrayList<Ffm_node> get_node(String[] row){
        int c=0;
        ArrayList<Ffm_node> x = new ArrayList<>();
        String field_char, idx_char, value_char;
        while (c < row.length) {
            field_char = row[c].split(":")[0];
            idx_char = row[c].split(":")[1];
            value_char = row[c].split(":")[2];
            c++;
            Ffm_node n = new Ffm_node(Integer.parseInt(field_char), Integer.parseInt(idx_char)
                    , Integer.parseInt(value_char));
            x.add(n);

        }
        return x;
    }


    public static double ffm_predict(String[] row, Ffm_model model) {
        float r = 1;
        float t = 0;
        ArrayList<Ffm_node> node=get_node(row);
        if (model.normalization) {
            r = 0;
            for (int x = 0; x < node.size();x++ ) {
                r+=node.get(x).getV()*node.get(x).getV();
            }
            r = 1 / r;
            t=wTx(node, r, model);
        }

        return 1/(1+exp(-t));

    }
}
