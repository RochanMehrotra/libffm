package libffm;

import static libffm.Load_model.ffm_load_model;
import static libffm.Predict.predict;


public class Tester
{
    public static void main( String[] args )
    {
        if (args.length < 3) {
            System.out.println("correct input format is :\n ./ffm-predict <path-to-model> <testset-path> <output-path>");
        } else {
            String modelPath=args[0];
            String testPath=args[1];
            String outputPath=args[2];
            ffm_load_model(modelPath);
            predict(testPath,modelPath,outputPath);

        }
        }
}
