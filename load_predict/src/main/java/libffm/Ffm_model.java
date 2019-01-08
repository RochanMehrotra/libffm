package libffm;

import java.util.ArrayList;

public class Ffm_model {

    int n; // number of features
    int m; // number of fields
    int k; // number of latent factors
    float[] W;//Weights
    boolean normalization;

    public Ffm_model(int n, int m, int k, float[] w, boolean normalization) {
        this.n = n;
        this.m = m;
        this.k = k;
        W = w;
        this.normalization = normalization;
    }

}
