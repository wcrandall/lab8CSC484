import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class RunFullExpirament {



    /* define constants */

    static int numberOfTrials = 100;

    static int MAXNUMBEROFPLACES  = 30;

    static int MINNUMBEROFPLACES  =  4;

    // static int SIZEINCREMENT =  10000000; // not using this since we are doubling the size each time



    static String ResultsFolderPath = "/home/wyatt/Results/lab8/greedy/"; // pathname to results folder

    static FileWriter resultsFile;

    static PrintWriter resultsWriter;

    //function to run full experiment
    static void runFullExperiment(String resultsFileName) throws IOException {


        //trying to write to file and displaying error message if it fails
        try {

            resultsFile = new FileWriter(ResultsFolderPath + resultsFileName);

            resultsWriter = new PrintWriter(resultsFile);

        } catch(Exception e) {

            System.out.println("*****!!!!!  Had a problem opening the results file "+ResultsFolderPath+resultsFileName);

            return; // not very foolproof... but we do expect to be able to create/open the file...

        }


        // instantiating stopwatch class
        ThreadCpuStopWatch BatchStopwatch = new ThreadCpuStopWatch(); // for timing an entire set of trials

        ThreadCpuStopWatch TrialStopwatch = new ThreadCpuStopWatch(); // for timing an individual trial


        //printing to file
        resultsWriter.println("#InputSize    AverageTime    HowManyBitsInputSizeIs"); // # marks a comment in gnuplot data
        //flushing so it immediately goes to file and not a queue
        resultsWriter.flush();

        // start at fib number 0 and goes to the number in the sequence specified by MAXFIBONUMBERSIZE
        for(int numberOfPlacesAt=MINNUMBEROFPLACES;numberOfPlacesAt<=MAXNUMBEROFPLACES; numberOfPlacesAt++) {

            // progress message...
            System.out.println("Running test for numberOfPlacesAt "+numberOfPlacesAt+" ... ");

            long batchElapsedTime = 0;

            //forcing garbage collection
            System.gc();
            //String stringSending2 = Files.readString(Path.of("/home/wyatt/Desktop/tomSawyer.txt"));
            // run the trials
            TravelingSalesmenProblem problem = new TravelingSalesmenProblem();
            int[][] hold = problem.generateRandomCircularGraphCostMatrix(numberOfPlacesAt,100);
            double[][] distances = new double[hold[0].length][hold[0].length];

            for(int i = 0; i < hold[0].length; i++)
            {
                for(int j = 0; j < hold[0].length; j++)
                {
                    distances[i][j] = hold[i][j];
                }
            }




            for (int trial = 0; trial < numberOfTrials; trial++) {
               // TspDynamicProgrammingRecursive dynamic = new TspDynamicProgrammingRecursive(0,distances);
                TrialStopwatch.start();
               // bruteForceTsp.tsp(distances);
                //dynamic.getTour();
                problem.greedyTravelingSalesmen(hold);
                batchElapsedTime = batchElapsedTime + TrialStopwatch.elapsedTime();

            }

            // calculate the average time per trial in this batch
            double averageTimePerTrialInBatch = (double) batchElapsedTime / (double)numberOfTrials;



            /* print data for this size of input */

            resultsWriter.printf("%12d  %15.2f \n",numberOfPlacesAt, averageTimePerTrialInBatch);
            //using flush so it immediately writes to file and does not go to queue
            resultsWriter.flush();

            System.out.println(" ....done.");

        }

    }

    private static String createStringEqualLengths(int howLong)
    {
        String build = "";
        for (int i = 0; i < howLong; i ++)
        {
            build+='x';
        }
        return build;
    }

    // function to generate a random string of length n
    //https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    // a function to find the amount of bits of x (i. e. find n)
    public static long countBits(String numberToCountBitsOf)
    {
        byte[] toGetBits = numberToCountBitsOf.getBytes();
        int returnValue = toGetBits.length * 8;
        return returnValue;
    }

}
