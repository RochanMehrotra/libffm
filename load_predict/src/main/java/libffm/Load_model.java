package libffm;

import com.sun.deploy.util.ArrayUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static libffm.Ffm.get_k_aligned;

public class Load_model {
    public static Ffm_model ffm_load_model(String path) {

        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] splited = data.split("\n");
        String[] nmkN = splited[0].split(",");
        float[] weights=new float[splited.length];
        for (int s = 1; s < splited.length; s++) {
            weights[s-1]=Float.parseFloat(splited[s]);
        }

        boolean norm = nmkN[3].equals("1") ? true : false;
        Ffm_model model = new Ffm_model(Integer.parseInt(nmkN[0]), Integer.parseInt(nmkN[1]), Integer.parseInt(nmkN[2]),
                weights, norm);

        return model;
    }
}
