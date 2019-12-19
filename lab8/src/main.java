import java.io.IOException;
import java.util.List;

public class main {
    public static void main(String args[]) throws IOException {


        solutionQualityTester tester = new solutionQualityTester();
        double average = tester.testQuality();
        System.exit(0);

        for(int i = 0; i < 3; i ++)
        {
            RunFullExpirament.runFullExperiment("greedy" + i + ".txt");
        }
    }
}
