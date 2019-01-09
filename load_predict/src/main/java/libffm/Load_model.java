package libffm;


import java.io.*;


import static libffm.Ffm.get_k_aligned;

public class Load_model {
    public static Ffm_model ffm_load_model(String path) {
        int n=0;
        int m=0;
        int k=0;
        boolean norm=false;
        float[] weights=new float[0];
        try(InputStream inputstream = new FileInputStream(path);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputstream)))
        {
            String[] firstLine;
            firstLine = bufferedReader.readLine().split(",");
            n=Integer.parseInt(firstLine[0]);
            m=Integer.parseInt(firstLine[1]);
            k=Integer.parseInt(firstLine[2]);
            norm=firstLine[3].equals("1") ? true : false;
            int x=0;
            weights=new float[n*m*get_k_aligned(k)*2+1];
            String l;
            while( (l = bufferedReader.readLine()) != null ){
                weights[x]=Float.parseFloat(l);
                x++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Ffm_model model = new Ffm_model(n, m, k, weights, norm);

        return model;
    }
}
