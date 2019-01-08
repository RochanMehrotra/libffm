package libffm;

public class Ffm_node {
    private int f; // field index
    private int j; // feature index
    private float v; // value

    //1:20:1->feild:feature:value
    Ffm_node(int f,int j,float v){
        this.f=f;
        this.j=j;
        this.v=v;

    }

    public int getF() {
        return f;
    }

    public int getJ() {
        return j;
    }

    public float getV() {
        return v;
    }
}
