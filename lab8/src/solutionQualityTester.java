import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class solutionQualityTester {
    public double[]  solutionQualityRatios;
    public double averageSolutionQualityRatio;
    static FileWriter resultsFile;

    static PrintWriter resultsWriter;
    String directory = "/home/wyatt/Results/lab8/sqr/sqr.txt";
    public double testQuality()
    {
        solutionQualityRatios = new double[20];

        TravelingSalesmenProblem problem = new TravelingSalesmenProblem();
        for(int i = 4; i < 20; i++)
        {
          double[] tmpratios = new double[20];
            int[][] distances = problem.generateRandomEuclideanCostMatrix(i, 100, 100);
            double[][] distants = new double[distances[0].length][distances[0].length];

            // my brute and dynamic work with doubles so I copy the int array to double for them
            for (int j = 0; j < distances[0].length; j++) {
                for (int k = 0; k < distances[0].length; k++) {
                    distants[j][k] = distances[j][k];

                }
            }

            for(int trials = 4; trials <20; trials++) {



                returnVals returns = problem.greedyTravelingSalesmen(distances);
                TspDynamicProgrammingRecursive dynamic = new TspDynamicProgrammingRecursive(0, distants);
                double costOfNormal = dynamic.getTourCost();
                // get all the trials ratios for test than find averages
                tmpratios[trials] = (double)costOfNormal/ (double)returns.distance;
            }
            solutionQualityRatios[i] = Arrays.stream(tmpratios).average().orElse(Double.NaN);
        }

        try {

            resultsFile = new FileWriter(directory);

            resultsWriter = new PrintWriter(resultsFile);

        } catch(Exception e) {

            System.out.println("*****!!!!!  Had a problem opening the results file "+directory);

            return 0; // not very foolproof... but we do expect to be able to create/open the file...

        }


        //printing to file
        resultsWriter.println("#InputSize    AverageTime    HowManyBitsInputSizeIs"); // # marks a comment in gnuplot data
        //flushing so it immediately goes to file and not a queue
        resultsWriter.flush();

        for(int i = 0; i < solutionQualityRatios.length;i++)
        {
            int toPrint = i +4;
            resultsWriter.printf("%d %15.2f\n", toPrint, solutionQualityRatios[i]);
            resultsWriter.flush();
        }


        double averageOverall = Arrays.stream(solutionQualityRatios).average().orElse(Double.NaN);
        System.out.println("the solution quality ratio for my greedy TSP is " + averageOverall);
        return averageOverall;
    }
}
