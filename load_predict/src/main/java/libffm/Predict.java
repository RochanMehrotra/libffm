package libffm;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.log;
import static libffm.Load_model.ffm_load_model;

public class Predict {

    public static void predict(String test_path, String model_path, String output_path) {
        String[] line;
        float y;
        Ffm_model model = ffm_load_model(model_path);
        File file = new File(test_path);
        Double loss=0.0;
        int num_rows=0;
        Path out_path= Paths.get(output_path);
        try (Scanner sc = new Scanner(file);
             BufferedWriter writer =Files.newBufferedWriter(out_path)) {
            while (sc.hasNextLine()) {
                num_rows++;
                line = sc.nextLine().split("\t");
                y = Integer.parseInt(line[0]) > 0 ? 1.0f : -1.0f;
                String[] rawNodes = Arrays.copyOfRange(line, 1, line.length);
                double y_bar = Ffm.ffm_predict(rawNodes,model);
                loss -= y == 1 ? log(y_bar) : log(1 - y_bar);
                writer.write(Double.toString(y_bar)+"\n");

            }
            loss/=num_rows;
            System.out.println(num_rows);
            System.out.println("loss"+loss);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
