public class Verify {
    public boolean testForExact()
    {
        TravelingSalesmenProblem problem = new TravelingSalesmenProblem();

        int[][] distances = problem.generateRandomCircularGraphCostMatrix(5,100);

        returnVals returns = problem.greedyTravelingSalesmen(distances);
        System.out.println("***************** The sequence of vertices and coordinates ****************");
        for(int i = 0; i < returns.path.length; i++)
        {
            System.out.println(i + " goes to " + returns.path[i] + " coordinates " + "distances[" + i + "]" + "["+returns.path[i] +"] is " + distances[i][returns.path[i]] );
        }

        System.out.println("************************* cost matrix *********************");
        problem.printMatrice(distances);


        System.out.println("*************** path algorithm found ******************");
        double[][] distants = new double[distances[0].length][distances[0].length];
        for(int i = 0; i < distances[0].length; i++)
        {
            for(int j = 0; j < distances[0].length; j++)
            {
                distants[i][j] = distances[i][j];
            }
        }

        TspDynamicProgrammingRecursive dynamic = new TspDynamicProgrammingRecursive(0,distants);
        returnVals returnsFromExact = new returnVals(dynamic.getTourCost(),dynamic.getTour());
        for(int city: returnsFromExact.pathExacts)
        {
            System.out.println("place " + city + "goes to");
        }
        System.out.println("The distance " + returnsFromExact.distance);

        System.out.println();

        return true;
    }

    public boolean verifyHeuristic()
    {
        TravelingSalesmenProblem problem = new TravelingSalesmenProblem();
        int[][] distances = problem.generateRandomCircularGraphCostMatrix(5,100);
        double[][] distants = new double[distances[0].length][distances[0].length];
        for(int i = 0; i < distants[0].length; i++)
        {
            for(int j = 0; j < distants[0].length; j++)
            {
                distants[i][j] = (double)distances[i][j];
            }
        }
        TspDynamicProgrammingRecursive dynamic = new TspDynamicProgrammingRecursive(0,distants);

        returnVals returns = new returnVals(dynamic.getTourCost(),dynamic.getTour());

        returnVals returnss = problem.greedyTravelingSalesmen(distances);

        boolean isSame = true;
        int d = 0;
        for(int val : returns.pathExacts)
        {
            System.out.println("val is "+ val + " heuristic is " + d);
                if(val != d)
                {
                    isSame = false;
                    break;
                }

                d = returnss.path[d];

        }

        if(returns.distanceDouble != returnss.distance)
        {
            isSame = false;
        }

        if(isSame)
        {
            System.out.println("the function works");
        }
        else
        {
            System.out.println("the function didn't work");
        }

        return isSame;
    }
}
